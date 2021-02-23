package cn.nicecoder.barbersys.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Description: 权限管理
 * @author: longt
 * @date: 2020/12/16 上午11:18
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * @Description: 授权
     * @author: longt
     * @Param: [http]
     * @return: void
     * @date: 2020/12/16 上午11:19
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 链式编程
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/login").permitAll()
                .antMatchers("/admin/*").hasRole("role1")
                /*.and()
                .formLogin()
                .loginPage("/admin/login")*/
                .and()
                .csrf().disable()
                .headers().frameOptions().sameOrigin();
        http.formLogin();
        http.logout();
    }

    /**
     * @Description: 配置
     * @author: longt
     * @Param: [auth]
     * @return: void
     * @date: 2020/12/16 上午11:19
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("role1");
    }
}
