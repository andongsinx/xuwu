package com.focus.ttnet.config;


import org.modelmapper.ModelMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {


    @Value("${spring.thymeleaf.cache}")
    private boolean thymeleafCacheEnable = true;

    /**
     * 静态资源加载器
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    /**
     * thymeleaf 模板解析器
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.thymeleaf")
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setCharacterEncoding("utf-8");
        templateResolver.setCacheable(thymeleafCacheEnable);
        return templateResolver;
    }


    /**
     * thymeleaf 标准方言解释器
     *
     * @return
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        //支持 spring EL 表达式
        templateEngine.setEnableSpringELCompiler(true);
        //支持SpringSecurity 方言
        SpringSecurityDialect securityDialect = new SpringSecurityDialect();
        templateEngine.addDialect(securityDialect);
        return templateEngine;
    }


    /**
     * 试图解析器
     *
     * @return
     */
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }


    /**
     * Bean Util
     *
     * @return
     */
    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();

    }


}
