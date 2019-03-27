package com.appdirect.sdk.vendorFields.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Locale;
import java.util.concurrent.Callable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.sdk.vendorFields.converter.FlowTypeConverter;
import com.appdirect.sdk.vendorFields.converter.LocaleConverter;
import com.appdirect.sdk.vendorFields.converter.OperationTypeConverter;
import com.appdirect.sdk.vendorFields.handler.VendorRequiredFieldHandler;
import com.appdirect.sdk.vendorFields.model.FlowType;
import com.appdirect.sdk.vendorFields.model.OperationType;
import com.appdirect.sdk.vendorFields.model.VendorRequiredFieldsResponse;

/**
 * Defines the endpoint for enforcing vendor required requiredFields on their products
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class VendorRequiredFieldController {
	@Autowired
	private final VendorRequiredFieldHandler vendorRequiredFieldHandler;

	@RequestMapping(
			method = GET,
			value = "/api/v1/admin/requiredFields",
			produces = APPLICATION_JSON_VALUE)
	public Callable<VendorRequiredFieldsResponse> getRequiredFields(
			@RequestParam(value = "sku") String sku,
			@RequestParam(value = "flowType") FlowType flowType,
			@RequestParam(value = "operationType") OperationType operationType,
			@RequestParam(value = "locale") Locale locale) {

		log.info(
				"Calling required fields API with sku={}, flowType={}, operationType={}, partner={}, locale={}",
				sku,
				flowType,
				operationType,
				locale.toLanguageTag()
		);
		return () -> vendorRequiredFieldHandler.getRequiredFields(sku, flowType, operationType, locale);
	}

	@InitBinder
	public void initBinder(final WebDataBinder webdataBinder) {
		webdataBinder.registerCustomEditor(FlowType.class, new FlowTypeConverter());
		webdataBinder.registerCustomEditor(OperationType.class, new OperationTypeConverter());
		webdataBinder.registerCustomEditor(Locale.class, new LocaleConverter());
	}
}