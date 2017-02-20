package com.payroll.service;
import javax.servlet.http.HttpSession;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.ptg.payroll.model.SignonResponse;


@Service
public interface SignonService {
	public SignonResponse process(HttpSession session);
	public void setConversionService(ConversionService conversionService);

}