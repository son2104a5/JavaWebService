package com.data.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo-log")
public class DemoLogController {

    private static final Logger logger = LoggerFactory.getLogger(DemoLogController.class);

    @GetMapping("/trace")
    public String traceLog() {
        logger.trace("Đã ghi log trace");
        return "Logged TRACE";
    }

    @GetMapping("/debug")
    public String debugLog() {
        logger.debug("Đã ghi log debug");
        return "Logged DEBUG";
    }

    @GetMapping("/info")
    public String infoLog() {
        logger.info("Đã ghi log info");
        return "Logged INFO";
    }

    @GetMapping("/warning")
    public String warnLog() {
        logger.warn("Đã ghi log warning");
        return "Logged WARN";
    }

    @GetMapping("/error")
    public String errorLog() {
        logger.error("Đã ghi log error");
        return "Logged ERROR";
    }
}
