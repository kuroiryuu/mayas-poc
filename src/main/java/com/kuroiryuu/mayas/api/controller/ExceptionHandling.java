package com.kuroiryuu.mayas.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.ThrowableProblem;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;

import static org.zalando.problem.Status.UNAUTHORIZED;

@ControllerAdvice
public class ExceptionHandling implements ProblemHandling, SecurityAdviceTrait {
    @ExceptionHandler
    public ResponseEntity<Problem> handleBadCredentials(final BadCredentialsException e, final NativeWebRequest request) {
        ThrowableProblem problem = Problem.builder()
                .withTitle("Credenciales incorrectas")
                .withDetail("El usuario y contraseña son incorrectos")
                .withStatus(UNAUTHORIZED)
                .build();
        return create(problem, request);
    }

}
