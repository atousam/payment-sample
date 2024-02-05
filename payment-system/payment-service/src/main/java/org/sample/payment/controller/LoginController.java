package org.sample.payment.controller;

import lombok.RequiredArgsConstructor;
import org.sample.payment.dto.auth.LoginRequestDto;
import org.sample.payment.dto.auth.LoginResponseDto;
import org.sample.payment.service.auth.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Atousa Mirhosseini
 * @since 05 Feb, 2024
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public LoginResponseDto billInquiry(@RequestBody LoginRequestDto requestDto) {
        return loginService.login(requestDto);
    }
}
