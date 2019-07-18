package com.example.demo.algamoney.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("algamoney")
public class AlgamoneyApiProperty {
	
	private final Seguranca seguranca = new Seguranca();
	
	private String originPermitida = "http://localhost8000";
	
	
	
	public String getOriginPermitida() {
		return originPermitida;
	}



	public void setOriginPermitida(String originPermitida) {
		this.originPermitida = originPermitida;
	}



	public Seguranca getSeguranca() {
		return seguranca;
	}



	public static class Seguranca {
		
		private boolean enableHttps = false;
	
		public boolean isEnableHttps() {
			return enableHttps;
		}
	
		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
		}
	
}
