package cn.cenjp.platform.controller;

import cn.cenjp.platform.Prefix.CountPrefix;
import cn.cenjp.platform.bean.Cart;
import cn.cenjp.platform.redis.RedisService;
import cn.cenjp.platform.service.CartService;
import cn.cenjp.platform.service.GoodService;
import cn.cenjp.platform.service.OrderService;
import cn.cenjp.platform.service.UserService;
import cn.cenjp.platform.utils.GetDate;
import cn.cenjp.platform.utils.MethodUtil;
import cn.cenjp.platform.vo.GoodVo;
import cn.cenjp.platform.vo.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import cn.cenjp.platform.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class userController {

    Logger logger = LoggerFactory.getLogger(userController.class);



    @Autowired
    UserService userService;

    @Autowired
    GoodService goodService;

    @Autowired
    CartService cartService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    OrderService orderService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/Register")
    public String Register() {
        return "Register";
    }


    @RequestMapping("/Index")
    public String toIndex(User user) {
        LocalDate now = LocalDate.now();
        System.out.println(user.getUser_id());
        redisService.offset(CountPrefix.dayUserCountPrefix, GetDate.getDate(), user.getUser_id());
        return "forward:/getList/1/1";
    }

    @RequestMapping("/")
    public String Index(User user) {
        Set<String> roles = AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            return "forward:/adminIndex";
        } else {
            LocalDate now = LocalDate.now();
            System.out.println(user.getUser_id());
            redisService.offset(CountPrefix.dayUserCountPrefix, GetDate.getDate(), user.getUser_id());
            return "forward:/getList/1/1";
        }
    }

    @RequestMapping("/Login")
    public String Login() {
        return "Login";
    }

    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    @ResponseBody
    public String doRegister(HttpServletRequest request) {
        User user = MethodUtil.getUserByRequest(request);
        user.setUser_password(passwordEncoder.encode(user.getUser_password()));
        boolean register = userService.register(user);
        if (register) {
            redisService.incrAll(CountPrefix.addUserCountPrefix, GetDate.getDate());
            return "1";
        }
        return "0";
    }


    @RequestMapping(value = "/getList/{goodKind}/{pageIndex}")
    public String toIndex(Model model, User user, @PathVariable("goodKind") String goodKind, @PathVariable("pageIndex") Integer pageIndex) {
        goodService.getAllGoodByList();
        model.addAttribute("userName", user.getUser_name());
        model.addAttribute("userId", user.getUser_id());
        PageHelper.startPage(pageIndex, 4);
        List<GoodVo> goodVoList = goodService.getGoodVoList(goodKind);
        PageInfo<GoodVo> pageInfo = new PageInfo<>(goodVoList);

        List<GoodVo> list = pageInfo.getList();
        model.addAttribute("navigatepageNums", pageInfo.getNavigatepageNums());
        model.addAttribute("list", list);
        model.addAttribute("nums", pageInfo.getPages());
        model.addAttribute("active", goodKind);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("goodVoList", pageInfo);
        return "Index";
    }


    @RequestMapping("/UserHome")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String toOrderInfo(User user, Model model) {
        model.addAttribute("userName", user.getUser_name());
        model.addAttribute("user", user);
        return "UserHome";
    }

    @RequestMapping("/getEditInfo")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getEditInfo(User user, Model model) {
        model.addAttribute("userName", user.getUser_name());
        model.addAttribute("user", user);
        return "EditInfo";
    }

    @RequestMapping("/getEditPassword")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getEditPassword(User user, Model model) {
        model.addAttribute("userName", user.getUser_name());
        model.addAttribute("user", user);
        return "EditPassword";
    }

    @RequestMapping(value = "/EditInfo", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    public int EditInfo(@RequestParam("username") String username, @RequestParam("sex") String sex
            , @RequestParam("addr") String addr, @RequestParam("phone") String phone
            , @RequestParam("email") String email, User user) {
        user.setUser_name(username);
        user.setUser_sex(sex);
        user.setUser_addr(addr);
        user.setUser_phone(phone);
        user.setUser_email(email);
        return userService.updateInfo(user) > 0 ? 1 : 0;
    }


    @RequestMapping(value = "/EditPassword", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public int EditPassword(User user, @RequestParam("password") String password, Model model) {
        model.addAttribute("userName", user.getUser_name());
        return userService.updatePass(passwordEncoder.encode(password), user.getUser_id()) > 0 ? 1 : 0;
    }


    @RequestMapping("/OrderManager")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String OrderManager(User user, Model model) {
        model.addAttribute("user", user);
        return "OrderManager";
    }


    /**
     * 获取用户的购物车
     *
     * @param user  请求得到用户的信息
     * @param model
     * @return 购物车信息 购物车总金额
     */
    @RequestMapping("/getCar")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getCar(User user, Model model) {
        model.addAttribute("userName", user.getUser_name());

        //获取用户的购物车信息
        List<Cart> goods = cartService.getCar(user.getUser_id());
        model.addAttribute("goods", goods);

        //统计购物车信息的所有商品价值总额
        Optional<Double> reduce = goods.stream()
                .map(Cart::getSpikePrice)
                .reduce(Double::sum);
        Double aDouble = reduce.orElse(0d);

        //统计商品的数量
        long count = goods.stream().count();

        model.addAttribute("sum", aDouble);
        model.addAttribute("size", count);
        return "Cart";
    }


    @RequestMapping("/joinCar")
    @ResponseBody
    public int joinCar(User user, Model model, @RequestParam("goodId") String goodId, @RequestParam("goodName") String goodName,
                       @RequestParam("spikeId") String spikeId, @RequestParam("spikePrice") String spikePrice,
                       @RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime) {
        model.addAttribute("userName", user.getUser_name());
        Cart cart = MethodUtil.getCart(user.getUser_id(), goodId, goodName, spikeId, spikePrice, startTime, endTime);
        return cartService.joinCar(cart);
    }


    @RequestMapping("/deleteCar/{spikeId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String deleteCar(User user, @PathVariable("spikeId") String spikeId, Model model) {
        model.addAttribute("userName", user.getUser_name());
        boolean isSuccess = cartService.deleteCar(String.valueOf(user.getUser_id()), spikeId);
        return "forward:/getCar";
    }


    @RequestMapping("/page/{pageIndex}")
    @ResponseBody
    public PageInfo<GoodVo> getByPagesNumber(@PathVariable("pageIndex") Integer pageIndex, @RequestParam("kind") Integer kind
            , Model model, User user) {
        model.addAttribute("userName", user.getUser_name());
        PageHelper.startPage(pageIndex, 2);
        List<GoodVo> goodVoList = goodService.getGoodVoList(String.valueOf(kind));
        PageInfo<GoodVo> pageInfo = new PageInfo<GoodVo>(goodVoList);
        return pageInfo;
    }

    @RequestMapping("/getOder")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getOder(User user, Model model) {
        model.addAttribute("userName", user.getUser_name());
        List<Order> order = orderService.getOrder(user.getUser_id());
        model.addAttribute("orders", order);
        return "myOrder";
    }

    @RequestMapping("/getIntroduction")
    public String getIntroduction(User user, Model model) {
        model.addAttribute("userName", user.getUser_name());
        return "introduction";
    }


}
