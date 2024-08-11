package com.shivasai.ecommerce.admin.productsActivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shivasai.ecommerce.R;
import com.shivasai.ecommerce.Service.ApiClient;
import com.shivasai.ecommerce.Service.productAPIService.product_ApiService;
import com.shivasai.ecommerce.models.productModel.ProductPostModel;
import com.shivasai.ecommerce.models.productModel.productPutModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addOrUpdateProductActivity extends AppCompatActivity {

    EditText EditTxt1;
    EditText EditTxt2;
    Button Submit1;
    private static final String TAG = "addProductActivity";
    String _id;
    String productId;
    String ProductName;
    String ProductPrice;
    String ProductImage;
    String __v;
    String validation;
    String validationValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        EditTxt1=findViewById(R.id.EditTxt1);
        EditTxt2=findViewById(R.id.EditTxt2);
        Submit1=findViewById(R.id.Submit1);

        // create the get Intent object
        Intent intent = getIntent();

         validation =intent.getStringExtra("IsFromUpdate");


      if(validation.trim().toString().equals("yes")){
           _id = intent.getStringExtra("_id");
          productId = intent.getStringExtra("productId");
           ProductName = intent.getStringExtra("ProductName");
           ProductPrice = intent.getStringExtra("ProductPrice");
           ProductImage = intent.getStringExtra("ProductImage");
           __v = intent.getStringExtra("__v");

          EditTxt1.setText(ProductName.toString());
          EditTxt2.setText(ProductPrice.toString());
          Log.e(TAG, "if true  : "+ProductName+ ProductPrice);
      }
      else{
          validationValue="No";
          Log.e(TAG, "if false : "+validation);
          Log.e(TAG, "if false : "+validationValue);
          Log.e(TAG, "if false : "+intent.getStringExtra("IsFromUpdate"));
          Log.e(TAG, "_id : "+intent.getStringExtra("_id"));
          Log.e(TAG, "productId : "+intent.getStringExtra("productId"));
          Log.e(TAG, "ProductName : "+intent.getStringExtra("ProductName"));
          Log.e(TAG, "ProductPrice : "+intent.getStringExtra("ProductPrice"));
          Log.e(TAG, "ProductImage : "+intent.getStringExtra("ProductImage"));
          Log.e(TAG, "__v : "+intent.getStringExtra("__v"));
      }

        Submit1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = 33)
            @Override
            public void onClick(View view) {

                ///if this is coming from update botton then
                if(validation.trim().toString().equals("yes")==false){
                    Log.e(TAG, "Post");
                    if(EditTxt2.getText().toString().isBlank()==false && EditTxt1.getText().toString().isBlank()==false){
                        product_ApiService apiService = ApiClient.getClient().create(product_ApiService.class);

                        ProductPostModel product = new ProductPostModel("001", EditTxt1.getText().toString(), Integer.parseInt(EditTxt2.getText().toString()), "image");


                        Call<Void> call = apiService.createProduct(product);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    finish();
                                    Log.d(TAG, "Product added successfully.");
                                } else {
                                    try {
                                        Log.e(TAG, "Response unsuccessful: " + response.errorBody().string());
                                    } catch (IOException e) {
                                        Log.e(TAG, "Error reading error body", e);
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                // Handle error
                                Log.e("api fail",t.getMessage().toString());
                            }
                        });
                    }else{
                        Log.e(TAG, "onClick : "+ProductName+ ProductPrice);
                    }
                }

                else{
                    Log.e(TAG, "Put");
                    if(EditTxt2.getText().toString().isBlank()==false && EditTxt1.getText().toString().isBlank()==false){
                        product_ApiService apiService = ApiClient.getClient().create(product_ApiService.class);

                        productPutModel product = new productPutModel(EditTxt1.getText().toString(), Integer.parseInt(EditTxt2.getText().toString()));


                        Call<Void> call = apiService.UpdateProduct(_id,product);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    finish();
                                    Log.d(TAG, "Product added successfully.");
                                } else {
                                    try {
                                        Log.e(TAG, "Response unsuccessful: " + response.errorBody().string());
                                    } catch (IOException e) {
                                        Log.e(TAG, "Error reading error body", e);
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                // Handle error
                                Log.e("api fail",t.getMessage().toString());
                            }
                        });
                    }else{

                    }

                }

            }
        });


    }
}