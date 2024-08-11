package com.shivasai.ecommerce.login;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.shivasai.ecommerce.R;
import com.shivasai.ecommerce.Service.ApiClient;
import com.shivasai.ecommerce.Service.loginService.loginAPIInterFace;
import com.shivasai.ecommerce.Service.loginService.loginModel;
import com.shivasai.ecommerce.admin.dashboard.AdminDashboard;
import com.shivasai.ecommerce.user.dasbdoard.userDashboard;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity {

   public EditText emailField;
   public EditText password;
   public Button loginBtn;
   LinearLayout fieldLayout;
   ProgressBar loader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // calling the action bar
       getSupportActionBar().hide();


        ///fields
        emailField=findViewById(R.id.email);
        password=findViewById(R.id.password);
        ///buttons
        loginBtn=findViewById(R.id.loginBtn);
        ///layout
        fieldLayout=findViewById(R.id.fieldLayout);
        ///loader
        loader=findViewById(R.id.loader);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(emailField.getText().toString()!=""){
                  loader.setVisibility(View.VISIBLE);
                  fieldLayout.setVisibility(View.GONE);
                  ApiValidation(emailField.getText().toString());
                  loader.setVisibility(View.GONE);
                  fieldLayout.setVisibility(View.VISIBLE);
              }else{
                  Toast.makeText(loginActivity.this, "Email should not empty", Toast.LENGTH_SHORT).show();
              }
            }
        });

    }


    public void ApiValidation(String email){

        loginAPIInterFace loginAPIInterface = ApiClient.getClient().create(loginAPIInterFace.class);
        Call<loginModel> call =  loginAPIInterface.getUser(email);
        call.enqueue(new Callback<loginModel>() {
            @Override
            public void onResponse(Call<loginModel> call, Response<loginModel> response) {
                Intent intent;
                if(response.code()==200){
                    if(response.body().getType().equals("ADMIN")){
                        intent = new Intent(loginActivity.this, AdminDashboard.class);
                        intent.putExtra("userName", response.body().getName());
                        intent.putExtra("userEmail", response.body().getEmail());
                        startActivity(intent);

                    }
                    else  if(response.body().getType().equals("USER")){
                        intent = new Intent(loginActivity.this, userDashboard.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(loginActivity.this, "No Login Prmission", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(loginActivity.this, "Wrong Login Details", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<loginModel> call, Throwable t) {
                Toast.makeText(loginActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}