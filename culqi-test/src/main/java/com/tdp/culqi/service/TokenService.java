package com.tdp.culqi.service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdp.culqi.binlist.Resource;

public class TokenService 
{
	public Map<String, Object> create(Map<String, Object> request)
	{
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		
		Resource resourceApi = new Resource();
		Map<String, Object> responseApi = resourceApi.findResource(request);

		if (responseApi.get("error").equals(false)) {
			String data = responseApi.get("data").toString();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root;
			String scheme = "";
			try {
				root = mapper.readTree(data);
				JsonNode nodeScheme = root.path("scheme");
				scheme = nodeScheme.asText();
			} catch (JsonProcessingException e) {
				response.put("error", true);
				response.put("detail", e.getMessage());
			} catch (IOException e) {
				response.put("error", true);
				response.put("detail", e.getMessage());
			}
			
			response.put("token", "tkn_live_" + request.get("bin") + "-2020-10");
			response.put("brand", scheme);
			response.put("creation_dt", "2018-06-20 18:00:00");
			
		} else {
			response = responseApi;
		}
		
		return response;
	}
}
