package cn.cenjp.platform.config;

import cn.cenjp.platform.bean.Role;
import cn.cenjp.platform.dao.RoleDao;
import cn.cenjp.platform.dao.UserDao;
import cn.cenjp.platform.exception.GlobalException;
import cn.cenjp.platform.result.CodeMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        cn.cenjp.platform.bean.User user=null;

        //读取用户数据
        user = userDao.selectByUserPhone(phone);
        //判断用户是否存在，则不存在则抛出用户不存在异常
        if (user==null)
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        //从数据库中读取权限
        List<Role> roles = roleDao.getRoleByUserId(user.getUser_id());
        for (Role role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getRole_name()));
        }
        return new User(phone,user.getUser_password(), authorities);
    }

}
