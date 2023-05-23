package ru.hackathon.sovcombankchallenge.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api("Test")
public class ApiController {
    @GetMapping
    @ApiOperation("Test")
    @RequestMapping(method = RequestMethod.GET)
    public String printHello() {
        return "hello";
    }
}
