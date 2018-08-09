package com.tdp.culqi.binlist;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public abstract class Binlist 
{
	protected String wsdl; // uso para SOAP
	protected String tipo;
	
	protected Map<String, Object> doAction(Map<String, Object> elementos)
	{
		if (this.getTipo().equals("rest")) {
			return this.rest(elementos);
		} else {
			return this.soap(elementos);
		}
	}
	
	private Map<String, Object> soap(Map<String, Object> elementos) 
	{
		return null;
	}
	
	private Map<String, Object> rest(Map<String, Object> elementos) 
	{
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("error", false);
		response.put("detail", "");
		
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<Map<String, Object>>(
				(Map<String, Object>) elementos.get("request"), 
				httpHeaders
			);
			
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> responseEntity = restTemplate.exchange(
				(String) elementos.get("url"), 
				(HttpMethod) elementos.get("httpMethod"),
				httpEntity, 
				new ParameterizedTypeReference<String>() {}, 
				(Map<String, Object>) elementos.get("request")
			);
			
			response.put("data", responseEntity.getBody());
		} catch (Exception e) {
			response.put("error", true);
			response.put("detail", e.getMessage());
		}
		
		return response;
	}
	
	protected void tracer(Map<String, Object> elementos, Map<String, Object> response)
	{
		// Guardar request y response cuando se consume el API (DB)
	}

	public String getWsdl() {
		return wsdl;
	}

	public void setWsdl(String wsdl) {
		this.wsdl = wsdl;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
