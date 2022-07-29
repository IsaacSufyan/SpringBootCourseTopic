package com.isaacsufyan.webservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello")
    public String helloWorld(
//            @RequestHeader(name = "Accept-Language", required = false) Locale locale
    ) {
        return messageSource.getMessage("welcome.message", null,"Default Message"
//                , locale
                , LocaleContextHolder.getLocale()
        );
    }
}
