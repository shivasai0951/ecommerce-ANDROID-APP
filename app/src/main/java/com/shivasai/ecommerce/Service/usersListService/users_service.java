package com.shivasai.ecommerce.Service.usersListService;

import com.shivasai.ecommerce.models.productModel.ProductGetModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface users_service {
    @GET("/api/login")
    Call<List<usersGetModel>> getUsers();
}
