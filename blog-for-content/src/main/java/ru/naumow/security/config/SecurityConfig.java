package ru.naumow.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import ru.naumow.security.handlers.BlogAliasHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@ComponentScan("ru.naumow.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService service;

    @Autowired
    private BlogAliasHandler successHandler;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*System.out.println("context " + servletContext);
        OpenEntityManagerInViewFilter filter = new OpenEntityManagerInViewFilter();
        filter.setServletContext(servletContext);*/
        //super.configure(http);



        //http.csrf().disable();

        http.csrf().ignoringAntMatchers("/api/**");

        http.authorizeRequests()
                .antMatchers("/sign-up")
                .permitAll()
                .antMatchers("/api/**")
                .permitAll()
                /*.anyRequest()
                .authenticated()*/

                .and()
                .formLogin()
                .loginPage("/sign-in")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(successHandler)
                .failureUrl("/sign-in?error")
                .defaultSuccessUrl("/profile")
                .permitAll()


                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("remember-me")

                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .rememberMeParameter("remember-me")

                .and()
                .headers()
                .frameOptions().disable();
                //.addHeaderWriter(new StaticHeadersWriter("X-FRAME-OPTIONS", "ALLOW-FROM http://localhost:8080"));
                //.addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.ALLOW_FROM))
                //.addHeaderWriter(n);
        //.headers()
        //.frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
