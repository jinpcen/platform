package cn.cenjp.platform.utils;

import cn.cenjp.platform.bean.*;
import cn.cenjp.platform.controller.spikeController;
import cn.cenjp.platform.vo.GoodVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

/**
 * 工具类
 */
public class MethodUtil {

    /**
     * 获取请求中所带的user信息
     * @param request
     * @return 返回该user信息
     */
    public static User getUserByRequest(HttpServletRequest request){
        User user=new User();
        user.setUser_phone(request.getParameter("telephone"));
        user.setUser_email(request.getParameter("email"));
        user.setUser_sex(request.getParameter("sex"));
        user.setUser_addr(request.getParameter("address"));
        user.setUser_name(request.getParameter("username"));
        user.setUser_password(request.getParameter("password"));
        return user;
    }

    public static MySpike getMySpike(Integer userId,String spileId,String address){
        MySpike mySpike = new MySpike();
        mySpike.setUser_id(userId);
        mySpike.setIfpay("0");
        mySpike.setMyspike_date(LocalDateTime.now());
        mySpike.setAddress(address);
        mySpike.setSpike_id(Integer.valueOf(spileId));
        return mySpike;
    }

    public static void addToModel(Model model, GoodVo goodVo,User user){
        long nowTime= new Date().getTime();
        long startTime = goodVo.getSpike_endTime().getTime();
        long endTime = goodVo.getSpike_startTime().getTime();
        if (nowTime>startTime){
            model.addAttribute("miaoshaStatus",2);
        }
        else if (nowTime<startTime&&nowTime>endTime)
            model.addAttribute("miaoshaStatus",1);
        else model.addAttribute("miaoshaStatus",0);


        model.addAttribute("endTime",goodVo.getSpike_endTime());
        model.addAttribute("startTime",goodVo.getSpike_startTime());
        model.addAttribute("good",goodVo);
        model.addAttribute("user",user);

    }

    public static Cart getCart(Integer user_id, String goodId, String goodName, String spikeId,
                               String spikePrice, String startTime, String endTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");
        LocalDateTime rs = LocalDateTime.parse(startTime,df);
        LocalDateTime et = LocalDateTime.parse(endTime,df);
        String[] split = spikePrice.split(":");
        Cart cart = new Cart();
        cart.setUserId(String.valueOf(user_id));
        cart.setGoodName(goodName);
        cart.setEndTime(et);
        cart.setGoodId(goodId);
        cart.setStartTime(rs);
        cart.setSpikePrice(Double.valueOf(split[1]));
        cart.setSpikeId(spikeId);
        return cart;
    }

    public static Good getGood(String goodName,String goodIntroduction,String goodOriginalPrice
    ,String goodKind,String goodImage){
        Good good = new Good();
        good.setGood_name(goodName);
        good.setGood_introduction(goodIntroduction);
        good.setGood_kind(Integer.valueOf(goodKind));
        good.setGood_originalPrice(Long.valueOf(goodOriginalPrice));
        good.setGood_image(goodImage);
        return good;
    }

    public static SpikeGoods getSpikeGood(Double spikePrice,String spikeStartTime,String spikeEndTime,
                                          String spikeCount,Good good,Integer userId,String goodImage){

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime st = LocalDateTime.parse(spikeStartTime,df);
        LocalDateTime et = LocalDateTime.parse(spikeEndTime,df);

        SpikeGoods spikeGoods = new SpikeGoods();
        spikeGoods.setGood_id(good.getGood_id());
        spikeGoods.setUser_id(userId);
        spikeGoods.setSpike_price(spikePrice);
        spikeGoods.setGood_id(good.getGood_id());
        spikeGoods.setSpike_endTime(et);
        spikeGoods.setSpike_startTime(st);
        spikeGoods.setSpike_count(Integer.valueOf(spikeCount));
        spikeGoods.setGood_image(goodImage);
        return spikeGoods;
    }

}
