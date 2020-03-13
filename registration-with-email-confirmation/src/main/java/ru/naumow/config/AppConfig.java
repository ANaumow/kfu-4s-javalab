package ru.naumow.config;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import ru.naumow.components.mail.MailComponent;
import ru.naumow.components.mail.PreparedMailComponent;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@org.springframework.context.annotation.Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "ru.naumow")
@EnableAspectJAutoProxy
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(driverManagerDataSource());
    }

    @Bean
    public DataSource driverManagerDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("db.driver"));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.user"));
        dataSource.setPassword(environment.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public Properties fileService() {
        Properties properties = new Properties();
        properties.put("files.storage", environment.getProperty("files.storage"));
        return properties;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ServletContextFactory servletContextFactory() {
        return new ServletContextFactory();
    }

    @Bean
    public Properties mailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        return properties;
    }

    @Bean
    public MailComponent mailComponent() {
        PreparedMailComponent mailComponent = new PreparedMailComponent();
        mailComponent.setSenderMailName("andrewnaumow@gmail.com");
        mailComponent.setSenderMailPassword("AndrewN008");
        mailComponent.setMailProperties(mailProperties());
        return mailComponent;
    }

    @Bean
    public Properties confirmationMailProperties() {
        Properties properties = new Properties();
        properties.put("subject", "Подтверждение почты");
        return properties;
    }

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setSuffix(".ftl");
        resolver.setContentType("text/html; charset=UTF-8");
        resolver.setOrder(0);
        return resolver;
    }

    @Bean
    public Configuration freemarkerConfiguration() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_29);
        try {
            ServletContext servletContext = servletContextFactory().getObject();
            configuration.setServletContextForTemplateLoading(servletContext, "");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        configuration.setDefaultEncoding("UTF-8");
        return configuration;
    }

    @Bean
    public Template mailForConfirmationTemplate() {
        try {
            return freemarkerConfiguration().getTemplate("WEB-INF/confirm_mail.ftl");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Bean
    public Template mailForNotificationTemplate() {
        try {
            return freemarkerConfiguration().getTemplate("WEB-INF/notification_mail.ftl");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/");
        return freeMarkerConfigurer;
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".html");
        viewResolver.setOrder(1);
        return viewResolver;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        return new CommonsMultipartResolver();
    }

}
