package com.tdp.culqi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdp.culqi.bean.Token;
import com.tdp.culqi.service.TokenService;

@RestController
@RequestMapping(value = "/tokens")
public class TokenController 
{
	TokenService tokenService = new TokenService();
	
	@PostMapping
	public ResponseEntity createToken(@RequestBody Token token) 
	{
		String bin = token.getPan().substring(0, 8);

		Map<String, Object> request = new HashMap<String, Object>();
		request.put("bin", bin);
	
		Map<String, Object> response = tokenService.create(request);

		if (response.get("error") != null && response.get("error").equals(true)) {
			return new ResponseEntity(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(response, HttpStatus.OK);
	}
}
