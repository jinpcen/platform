package cn.cenjp.platform.service;

import cn.cenjp.platform.bean.User;
import cn.cenjp.platform.dao.RemenberMeDao;
import cn.cenjp.platform.dao.RoleDao;
import cn.cenjp.platform.dao.UserDao;
import cn.cenjp.platform.redis.RedisService;
import cn.cenjp.platform.Prefix.TokenPrefix;
import cn.cenjp.platform.Prefix.UserKey;
import cn.cenjp.platform.utils.MD5Util;
import com.alibaba.druid.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
public class UserService{

    public static final String COOKI_NAME_TOKEN = "remember-me";

    @Autowired
    UserDao userDao;

    @Autowired
    RedisService redisService;

    @Autowired
    RemenberMeDao remenberMeDao;

    @Autowired
    RoleDao roleDao;

    public User getUserByPhone(String userPhone){
        User user = redisService.get(UserKey.getByPhone,userPhone,User.class);
        if (user!=null)
            return user;
        else{
            user= userDao.selectByUserPhone(userPhone);
            redisService.set(UserKey.getByPhone,userPhone,user);
        }
        return user;
    }

    @Transactional
    public boolean register(User user){
        int insertUser = userDao.insertUser(user);
        if (insertUser>0)
            roleDao.insertRole(String.valueOf(user.getUser_id()),"ROLE_USER");
        return insertUser>0;
    }

    public User getByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }

        //先从缓存中获取登录用户所使用的手机号码，若为空则再从数据库中获取
        String phone = redisService.get(TokenPrefix.tokenPrefix,token,String.class);
        if (phone==null)
            phone = remenberMeDao.getByUsername(token);

        //若从数据库获取的phone不为空则把它缓存到redis中，否则则返回null
        if (phone!=null)
            redisService.set(TokenPrefix.tokenPrefix,token,phone);
        else return null;

        //从redis中缓存User,若User为空则再从数据库中获取
        User user = redisService.get(UserKey.getByPhone,phone, User.class);
        if (user==null)
            userDao.selectByUserPhone(phone);

        //若user不为空则把它缓存到redis中
        if (user!=null){
            redisService.set(UserKey.getByPhone,phone,user);
        }
        return user;
    }

    public String login(String userPhone,String Passowrd,HttpServletResponse response){
        User realuser = getUserByPhone(userPhone);
        if (realuser!=null){
            //密码错误
            String password = MD5Util.formPassToDBPass(Passowrd,realuser.getSalt()+"    "+realuser.getUser_password());
            System.out.println(password);
            if (!realuser.getUser_password().equals(password))
                return "0";
            //密码正确
           return "1";
        }
        //用户不存在
        return "3";
    }

    public int updatePass(String password,int id) {
        return userDao.updatePass(password,id);
    }

    public int updateInfo(User user) {
        return userDao.updateInfo(user);
    }
}
