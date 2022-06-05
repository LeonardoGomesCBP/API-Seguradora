package com.apiseguradora.exception;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//classe para retornar mensagens de erro ou sucesso no response entity
public class ApiMessage  {
	
	private String Message;

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public ApiMessage(String message) {
		super();
		Message = message;
	}

}
