package com.example.idvlabsTask.authentication.controller;


import com.example.idvlabsTask.authentication.controller.request.LoginRequest;
import com.example.idvlabsTask.authentication.service.LoginService;
import com.example.idvlabsTask.common.response.MessageResponseID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    @PostMapping("/login")
    public MessageResponseID login(@Valid @RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }
}