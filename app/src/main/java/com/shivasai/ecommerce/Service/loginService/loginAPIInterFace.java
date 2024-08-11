package com.shivasai.ecommerce.Service.loginService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface loginAPIInterFace {

    @GET("/api/login/{email}")
    Call<loginModel> getUser(@Path("email") String email);
}
