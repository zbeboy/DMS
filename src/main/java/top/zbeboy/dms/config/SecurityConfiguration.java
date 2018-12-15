package top.zbeboy.dms.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import top.zbeboy.dms.filter.SecurityLoginFilter;
import top.zbeboy.dms.security.AjaxAuthenticationFailureHandler;
import top.zbeboy.dms.security.AjaxAuthenticationSuccessHandler;
import top.zbeboy.dms.security.MyUserDetailsServiceImpl;

import javax.inject.Inject;
import javax.sql.DataSource;

/**
 * spring security 配置.
 *
 * @author zbeboy
 * @version 1.0
 * @since 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsService;

    @Inject
    private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

    @Inject
    private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JdbcTokenRepositoryImpl jdbcTokenRepository() {
        JdbcTokenRepositoryImpl j = new JdbcTokenRepositoryImpl();
        j.setDataSource(this.dataSource);
        return j;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                // allow same origin to frame our site to support iframe SockJS
                .frameOptions().sameOrigin()
                .and()
                .authorizeRequests().antMatchers("/css/**", "/js/**", "/fonts/**", "/img/**", "/plugin/**", "/files/**", "/webjars/**", "/webjarsjs/**").permitAll()
                .and().formLogin().loginPage("/login")
                .successHandler(this.ajaxAuthenticationSuccessHandler)
                .failureHandler(this.ajaxAuthenticationFailureHandler)
                .and().sessionManagement().invalidSessionUrl("/login")
                .and().logout().logoutSuccessUrl("/")
                .permitAll().invalidateHttpSession(true)
                .and().rememberMe().alwaysRemember(true).tokenValiditySeconds(2419200).tokenRepository(jdbcTokenRepository())
                .and().authorizeRequests().antMatchers("/web/**").authenticated()
                .and().authorizeRequests().antMatchers("/anyone/**").permitAll()
                .and().addFilterBefore(new SecurityLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.myUserDetailsService).passwordEncoder(passwordEncoder()).and().eraseCredentials(false);
    }
}
