package cn.cenjp.platform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	@Autowired
	UserArgumentResolver userArgumentResolver;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(userArgumentResolver);
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}

	@Bean
	public HttpMessageConverter<String> responseBodyStringConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
		return converter;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 前面是url路径，后面是视图路径，添加thymeleaf后自动配置prefix为/templates,suffix为.html
		registry.addViewController("/Login").setViewName("/Login");
		registry.addViewController("/toIndex").setViewName("/toIndex");
	}

}
