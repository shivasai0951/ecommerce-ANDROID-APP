package com.shivasai.ecommerce.Service.productAPIService;

import com.shivasai.ecommerce.models.productModel.ProductGetModel;
import com.shivasai.ecommerce.models.productModel.ProductPostModel;
import com.shivasai.ecommerce.models.productModel.productPutModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

public interface product_ApiService {
    @GET("/api/products")
    Call<List<ProductGetModel>> getProducts();

    @DELETE("/api/products/{id}")
    Call<List<ProductGetModel>> deleteProducts(@Path("id") String id);

    @POST("/api/products")
    Call<Void> createProduct(@Body ProductPostModel product);

    @PUT("/api/products/{id}")
    Call<Void> UpdateProduct(@Path("id") String id,@Body productPutModel product);

}
