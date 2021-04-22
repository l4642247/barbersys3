package cn.nicecoder.barbersys.config;

import cn.nicecoder.barbersys.handler.MyAccessDeniedHandler;
import cn.nicecoder.barbersys.handler.MyLogoutSuccessHandler;
import cn.nicecoder.barbersys.security.RoleBasedVoter;
import cn.nicecoder.barbersys.security.RoleSecurityMetadataSource;
import cn.nicecoder.barbersys.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

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
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RoleSecurityMetadataSource roleSecurityMetadataSource;

    /**
     * @Description: 授权
     * @author: longt
     * @Param: [http]
     * @return: void
     * @date: 2020/12/16 上午11:19
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 自定义权限控制
        http.authorizeRequests()
                //1.放入修改后的accessDecisionManager
                .accessDecisionManager(customizeAccessDecisionManager())
                //2.扩展 FilterSecurityInterceptor，放入自定义的FilterInvocationSecurityMetadataSource
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(
                            O fsi) {
                        fsi.setSecurityMetadataSource(roleSecurityMetadataSource);
                        return fsi;
                    }
                })
                .anyRequest().authenticated()
                .and()
                    .formLogin(form -> form
                            .loginPage("/login")
                            .defaultSuccessUrl("/admin",true)
                            .failureUrl("/login?error=true")
                    )
                    .csrf().disable()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(myLogoutSuccessHandler)
                .and()
                    .exceptionHandling()
                    .accessDeniedHandler(myAccessDeniedHandler)
                .and()
                    .headers().frameOptions().sameOrigin();

        // 记住我
        http.rememberMe()
                .tokenRepository(persistentTokenRepository())
                // 超时时间
                .tokenValiditySeconds(60)
                // 自定义登陆逻辑
                .userDetailsService(userDetailsService);
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
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        /*// 第一次启动开启
        jdbcTokenRepository.setCreateTableOnStartup(true);*/
        return  jdbcTokenRepository;
    }

    //使用自定义角色器，放入 AccessDecisionManager的一个实现 AffirmativeBased 中
    private AccessDecisionManager customizeAccessDecisionManager() {

        List<AccessDecisionVoter<? extends Object>> decisionVoterList
                = Arrays.asList(
                new RoleBasedVoter()
        );
        return new AffirmativeBased(decisionVoterList);
    }

}
