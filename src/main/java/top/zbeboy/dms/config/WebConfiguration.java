package top.zbeboy.dms.config;


import io.undertow.servlet.api.SecurityConstraint;
import io.undertow.servlet.api.SecurityInfo;
import io.undertow.servlet.api.TransportGuaranteeType;
import io.undertow.servlet.api.WebResourceCollection;
import org.apache.commons.codec.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.spring5.view.ThymeleafView;

import java.io.File;

/**
 * Spring boot web init.
 *
 * @author zbeboy
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private DMSProperties dmsProperties;

    /**
     * 切换语言
     *
     * @return 语言环境
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }

    /**
     * ajax 返回页面
     *
     * @return 页面节点
     */
    @Bean
    @Scope("prototype")
    public ThymeleafView thymeleafView() {
        ThymeleafView thymeleafView = new ThymeleafView();
        thymeleafView.setMarkupSelector("#page-wrapper");
        return thymeleafView;
    }

    /**
     * undertow http 重定向 https
     *
     * @return factory
     */
    @Bean
    public UndertowServletWebServerFactory undertow() {
        UndertowServletWebServerFactory undertow = new UndertowServletWebServerFactory();
        File documentRoot = new File(System.getProperty("user.dir") + "/" + this.dmsProperties.getConstants().getDocumentRoot());
        if (!documentRoot.exists()) {
            documentRoot.mkdirs();
        }
        undertow.setDocumentRoot(documentRoot);
        return undertow;
    }
}
