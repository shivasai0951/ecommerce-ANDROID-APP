package com.shivasai.ecommerce.admin.UsersListActivirty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.shivasai.ecommerce.R;
import com.shivasai.ecommerce.Service.ApiClient;
import com.shivasai.ecommerce.Service.productAPIService.product_ApiService;
import com.shivasai.ecommerce.Service.usersListService.usersGetModel;
import com.shivasai.ecommerce.Service.usersListService.users_service;
import com.shivasai.ecommerce.models.productModel.ProductGetModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class usersListActivity extends AppCompatActivity {

    RecyclerView userListId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        userListId = findViewById(R.id.userListId);
        LinearLayoutManager llm =new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        userListId.setLayoutManager(llm);
        apiCallMethod();
    }

    private void apiCallMethod() {
        users_service apiInterface = ApiClient.getClient().create(users_service.class);
        Call<List<usersGetModel>> call = apiInterface.getUsers();

      call.enqueue(new Callback<List<usersGetModel>>() {
          @Override
          public void onResponse(Call<List<usersGetModel>> call, Response<List<usersGetModel>> response) {

              if(response.isSuccessful()){
                      userListAdaptor adaptor =new userListAdaptor(response.body());
                      userListId.setAdapter(adaptor);
              }

          }

          @Override
          public void onFailure(Call<List<usersGetModel>> call, Throwable t) {

          }
      });
    }
}