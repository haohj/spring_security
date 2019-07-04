package com.hao.security.browser;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MyUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登陆用户名： {}", username);
        // 这里可以根据用户名到数据库中查询用户,获得数据库中得到的密码（这里不进行查询操作，使用固定代码）
        // 在实际的开发中，存到数据库的密码不是明文的，而是经过加密的
        String password = "123456";
        String encodedPassword = passwordEncoder.encode(password);
        log.info("加密后的密码为： {}", encodedPassword);
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
