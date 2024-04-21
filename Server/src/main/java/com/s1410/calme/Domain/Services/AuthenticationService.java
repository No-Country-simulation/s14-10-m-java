package com.s1410.calme.Domain.Services;
import com.s1410.calme.Domain.Dtos.request.RequestLogin;
import com.s1410.calme.Domain.Dtos.response.ResponseLogin;

public interface AuthenticationService {
    ResponseLogin login(RequestLogin data);
    String forgotPassword(String email);
    String setPassword(String email, String newPassword);
}
