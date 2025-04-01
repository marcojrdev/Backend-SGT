package com.marco.backend.controller.rest;

import org.springframework.web.bind.annotation.RestController;

import com.marco.backend.dto.exceptions.ErrorDTO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class TratamentoErros {
    
    @GetMapping("/error")
    public ResponseEntity<ErrorDTO> getError(HttpServletRequest request) {
        String ex = (String)request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        return new ResponseEntity<>(new ErrorDTO(ex), HttpStatus.UNAUTHORIZED);
    }
    

}
