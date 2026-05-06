package com.college.resourceserver.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.NativeWebRequest;

public class AnnouncementsApiController {

    private final NativeWebRequest request;

    @Autowired
    public AnnouncementsApiController(NativeWebRequest request) {
        this.request = request;
    }

   
}
