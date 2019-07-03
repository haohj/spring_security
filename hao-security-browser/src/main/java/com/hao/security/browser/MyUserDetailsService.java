package com.hao.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MyUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("表单用户名登陆：{}", username);
        String password = "123456";
        String encodedPassword = passwordEncoder.encode(password);
        logger.info("加密后的密码为：{}", encodedPassword);
        // 这里查询该账户是否过期，这里使用固定代码，假设没有过期
        boolean accountNonExpired = true;
        // 这里查询该账户被删除，假设没有被删除
        boolean enabled = true;
        // 这里查询该账户认证是否过期，假设没有过期
        boolean credentialsNonExpired = true;
        // 查询该账户是否被锁定，假设没有被锁定
        boolean accountNonLocked = true;
        // 关于密码的加密，应该是在创建用户的时候进行的，这里仅仅是举例模拟
        return new User(username, encodedPassword,
                enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
