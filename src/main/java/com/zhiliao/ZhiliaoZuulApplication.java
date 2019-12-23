package com.zhiliao;

import com.zhiliao.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableZuulProxy
public class ZhiliaoZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhiliaoZuulApplication.class, args);
	}
	@Bean
	public AccessFilter tokenFilter() {
		return new AccessFilter();
	}

}


