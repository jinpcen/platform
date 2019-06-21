package cn.cenjp.platform.controller;

import cn.cenjp.platform.Prefix.CountPrefix;
import cn.cenjp.platform.bean.MySpike;
import cn.cenjp.platform.bean.SpikeMessage;
import cn.cenjp.platform.bean.User;
import cn.cenjp.platform.rabbitmq.MQSender;
import cn.cenjp.platform.Prefix.GoodVoKey;
import cn.cenjp.platform.redis.RedisService;
import cn.cenjp.platform.result.CodeMsg;
import cn.cenjp.platform.result.Result;
import cn.cenjp.platform.service.GoodService;
import cn.cenjp.platform.service.OrderService;
import cn.cenjp.platform.utils.GetDate;
import cn.cenjp.platform.vo.GoodVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;

import static cn.cenjp.platform.utils.MethodUtil.addToModel;


@Controller
public class spikeController implements InitializingBean{

    Logger logger= LoggerFactory.getLogger(spikeController.class);


    @Autowired
    RedisService redisService;

    @Autowired
    OrderService orderService;

    @Autowired
    GoodService goodService;

    @Autowired
    MQSender mqSender;

    /**
     * 系统初始化
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //加载所有的商品
        boolean allGoodByList = goodService.getAllGoodByList();
    }


    @RequestMapping("/gooddetail/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String todetail(@PathVariable("id") String id, Model model, User user){
        model.addAttribute("userName",user.getUser_name());

        //获取秒杀商品的信息
        GoodVo goodVo = goodService.getGoodVo(id);

        System.out.println(goodVo.toString());
        //把相应的信息添加到model中
        addToModel(model,goodVo,user);

        return "GoodInfo";
    }
    /**
     * 秒杀控制类
     * @param request
     * @param model
     * @param user 秒杀的用户的信息
     * @return
     */
    @RequestMapping("/doSpike")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    public Result<Integer> doSpike(HttpServletRequest request, Model model, User user){
        model.addAttribute("userName",user.getUser_name());

        String goodId = request.getParameter("goodId");
        String spikeId = request.getParameter("spikeId");
        //判断登录状态
        if (user==null){
            return Result.error(CodeMsg.SESSION_ERROR);
        }


        //预减库存
        Long count = redisService.decr(GoodVoKey.goodVoCount, "" + goodId);
        System.out.println(count);
        if (count<0){
            model.addAttribute("errmsg","秒杀已结束");
            return Result.error(CodeMsg.SPIKE_OVER);
        }


        //判断是否重复秒杀
        boolean reSpike = orderService.checkReSpike(user.getUser_id(), spikeId);
        if (!reSpike){
            model.addAttribute("errmsg","不可重复秒杀");
            return Result.error(CodeMsg.REPEATE_SPIKE);
        }

        //秒杀请求进入消息队列
        SpikeMessage spikeMessage=new SpikeMessage();
        spikeMessage.setGoodId(goodId);
        spikeMessage.setUser(user);
        spikeMessage.setSpikeId(spikeId);
        mqSender.sendSpileMessage(spikeMessage);
        return Result.success(0);
    }

    /**
     * -1:秒杀失败
     * 1：秒杀成功
     * 0：排队中
     * */
    @RequestMapping(value="/getResult", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    public Result<Integer> SpikeResult(Model model,User user,
                                      @RequestParam("spikeId")String spikeId, @RequestParam("goodId")String goodId) {
        model.addAttribute("userName",user.getUser_name());

        model.addAttribute("user", user);

        //判断是否秒杀成功
        boolean result  = orderService.checkReSpike(user.getUser_id(),spikeId);
        if (!result) return Result.success(1);

        //检查库存是否充足，若充足则继续等待，否则则返回秒杀失败。
        if(redisService.get(GoodVoKey.goodVoCount,""+goodId,Integer.class)>0)
            return Result.error(CodeMsg.SPIKE_WAIT);
        else return Result.error(CodeMsg.SPIKE_FAIL);
    }

    @RequestMapping(value = "/getOrderInfo",method = RequestMethod.GET)
    public String getOrderInfo(Model model,User user,
                               @RequestParam("goodId")String goodId,@RequestParam("spikeId")String spikeId){
        model.addAttribute("userName",user.getUser_name());

        MySpike mySpike = orderService.getOrderInfo(user.getUser_id(), spikeId);
        model.addAttribute("spike",mySpike);
        GoodVo goodVo = goodService.getGoodVo(goodId);
        model.addAttribute("good",goodVo);
        model.addAttribute("user",user);
        return "OrderInfo";
    }

    @RequestMapping(value = "/turnToPay",method = RequestMethod.GET)
    public String turnToPay(Model model,
                        @RequestParam("goodId")String goodId,@RequestParam("myspike_id")String myspikeId,User user) {
        model.addAttribute("userName",user.getUser_name());
        model.addAttribute("goodId",goodId);
        model.addAttribute("myspike_id",myspikeId);
        return "Pay";
    }


    @RequestMapping(value = "/doPay",method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public String doPay(Model model,User user,
                        @RequestParam("goodId")String goodId,@RequestParam("myspike_id")String myspikeId) {
        model.addAttribute("userName",user.getUser_name());
        boolean doPay = orderService.doPay(myspikeId);
        if (doPay){
            MySpike mySpike = orderService.getOrderInfoByMySpikeId(myspikeId);
            GoodVo goodVo = goodService.getGoodVo(goodId);

            //更新订单数
            redisService.incrAll(CountPrefix.spikeCountPrefix, GetDate.getDate());

            //更新交易额
            Double count = redisService.get(CountPrefix.spikeFeeCountPrefix, GetDate.getDate(), Double.class);
            if (count==null){
                redisService.set(CountPrefix.spikeFeeCountPrefix,GetDate.getDate(),goodVo.getSpike_price());
            }else {
                redisService.set(CountPrefix.spikeFeeCountPrefix,GetDate.getDate(),count+goodVo.getSpike_price());
            }


            model.addAttribute("spike",mySpike);
            model.addAttribute("good",goodVo);
            model.addAttribute("user",user);
        }
        return "OrderInfo";
    }
}
