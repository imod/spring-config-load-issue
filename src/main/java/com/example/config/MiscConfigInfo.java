package com.example.config;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@ConfigurationProperties(prefix = "misc")
public class MiscConfigInfo implements InitializingBean {

	@Valid
	private final BaseUrlConfig baseUrl = new BaseUrlConfig();

	@NotEmpty
	private final Map<Integer, Boolean> kmsToWarning = new HashMap<Integer, Boolean>();

	@NotNull
	private Boolean listener;

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("-->" + listener);
		System.out.println("-->" + kmsToWarning);
	}

	public MiscConfigInfo() {
	}

	public Map<Integer, Boolean> getKmsToWarning() {
		return kmsToWarning;
	}

	@Bean
	public BaseUrlConfig getBaseUrl() {
		return baseUrl;
	}

	public void setListener(Boolean listener) {
		this.listener = listener;
	}

	@Validated
	public static class BaseUrlConfig {

		@NotBlank
		@URL
		private String url;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}
}
