package ru.naumow.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.header.writers.frameoptions.AllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.web.filter.DelegatingFilterProxy;
import ru.naumow.aspects.TestObjectPostProcessor;
import ru.naumow.model.UserSessionData;
import ru.naumow.security.handlers.BlogAliasHandler;

import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
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
    @Qualifier("processor")
    private ObjectPostProcessor<ProviderManager> testObjectPostProcessor;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*System.out.println("context " + servletContext);
        OpenEntityManagerInViewFilter filter = new OpenEntityManagerInViewFilter();
        filter.setServletContext(servletContext);*/



        http.csrf().disable();



        http.authorizeRequests()
                .antMatchers("/sign-up")
                .permitAll()
                .anyRequest()
                .authenticated()

                .and()
                .formLogin()
                .loginPage("/sign-in")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(successHandler)
                .failureUrl("/sign-in?error")
                .defaultSuccessUrl("/profile")
                .withObjectPostProcessor(testObjectPostProcessor)
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
