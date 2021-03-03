package cn.nicecoder.barbersys.config;

import cn.nicecoder.barbersys.handler.MyAccessDeniedHandler;
import cn.nicecoder.barbersys.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @Description: 权限管理
 * @author: longt
 * @date: 2020/12/16 上午11:18
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private DataSource dataSource;

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
        http.formLogin()
                .loginPage("/login.html")
                //必须和表单提交的接口一样，获取执行自定义登陆逻辑
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/welcome",true)
                .failureUrl("/login.html?error=true")
        ;

        http.authorizeRequests()
                .antMatchers("/layuiadmin/**").permitAll()
                .antMatchers("/login.html").permitAll()
                .antMatchers("/admin/user/role").hasRole("admin")
                .anyRequest().authenticated();




        http.exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler);

        //记住我
        http.rememberMe()
                .tokenRepository(persistentTokenRepository())
                // 超时时间
                .tokenValiditySeconds(60)
                // 自定义登陆逻辑
                .userDetailsService(userDetailsService);

        http.csrf().disable()
                .headers().frameOptions().sameOrigin();
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
        /*auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("role1");*/
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // jdbcTokenRepository.setCreateTableOnStartup(true);
        return  jdbcTokenRepository;
    }

}
