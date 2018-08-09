package com.tdp.culqi.binlist;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;

public class Resource extends Binlist 
{
	public Resource() {
		super();
		this.tipo = "rest";
	}

	public Map<String, Object> findResource(Map<String, Object> request)
	{
		Map<String, Object> elementos = new HashMap<String, Object>();
		elementos.put("action", "find_resource"); // campo definido para el trace
		elementos.put("url", "https://lookup.binlist.net/{bin}");
		elementos.put("httpMethod", HttpMethod.GET);
		elementos.put("request", request);
		
		return this.doAction(elementos);
	}
}
