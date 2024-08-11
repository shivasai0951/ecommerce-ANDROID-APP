package com.shivasai.ecommerce.admin.productsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shivasai.ecommerce.R;
import com.shivasai.ecommerce.Service.ApiClient;
import com.shivasai.ecommerce.Service.productAPIService.product_ApiService;
import com.shivasai.ecommerce.adaptors.ProductAdapter;
import com.shivasai.ecommerce.models.productModel.ProductGetModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class products extends AppCompatActivity {

    private RecyclerView recyclerView;
    ProgressBar progressBar_cyclic;
    private FloatingActionButton fab;
    private ProductAdapter productAdapter;
    List<ProductGetModel> productsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Mobile Products List");
        actionBar.setDisplayHomeAsUpEnabled(true);

        //recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);
        progressBar_cyclic = findViewById(R.id.progressBar_cyclic);
        recyclerView.setLayoutManager(new LinearLayoutManager(products.this));
        getProductsList();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(products.this, addOrUpdateProductActivity.class);
                intent.putExtra("IsFromUpdate","no");
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        getProductsList();
        super.onResume();
    }

    @Override
    protected void onPause() {
        getProductsList();
        super.onPause();
    }

    public void  getProductsList(){
        progressBar_cyclic.setVisibility(View.VISIBLE);
        product_ApiService apiInterface = ApiClient.getClient().create(product_ApiService.class);
        Call<List<ProductGetModel>> call = apiInterface.getProducts();

        call.enqueue(new Callback<List<ProductGetModel>>() {
            @Override
            public void onResponse(Call<List<ProductGetModel>> call, Response<List<ProductGetModel>> response) {
                progressBar_cyclic.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    productsArray = response.body();
                    productAdapter = new ProductAdapter(productsArray);
                    productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
                        @Override
                        public void onUpdateClick(int position) {
                            Intent intent = new Intent(products.this, addOrUpdateProductActivity.class);
                            intent.putExtra("IsFromUpdate","yes");

                            intent.putExtra("_id",productsArray.get(position).get_id().toString());
                            intent.putExtra("productId",productsArray.get(position).getProductId().toString());
                            intent.putExtra("ProductName",productsArray.get(position).getProductName().toString());
                            intent.putExtra("ProductPrice",String.valueOf(productsArray.get(position).getProductPrice()));//String.valueOf(product.getProductPrice())
                            intent.putExtra("ProductImage",productsArray.get(position).getProductImage().toString());
                            intent.putExtra("__v",String.valueOf(productsArray.get(position).get__v()));


                            startActivity(intent);
                        }

                        @Override
                        public void onDeleteClick(int position) {



                            Call<List<ProductGetModel>> call = apiInterface.deleteProducts(productsArray.get(position).get_id());

                            call.enqueue(new Callback<List<ProductGetModel>>() {
                                @Override
                                public void onResponse(Call<List<ProductGetModel>> call, Response<List<ProductGetModel>> response) {
                                    Toast.makeText(products.this, "Deleted " + productsArray.get(position).getProductName(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<List<ProductGetModel>> call, Throwable t) {
                                    Toast.makeText(products.this, "", Toast.LENGTH_LONG).show();
                                    //  Toast.makeText(products.this, "error while deleting " + products.get(position).getProductName(), Toast.LENGTH_SHORT).show();
                                }
                            });


                            // Handle delete action
                            productsArray.remove(position);
                            productAdapter.notifyItemRemoved(position);
                            // Optionally, call your API to delete the item on the server
                        }
                    });
                    recyclerView.setAdapter(productAdapter);
                } else {
                    Toast.makeText(products.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductGetModel>> call, Throwable t) {
                progressBar_cyclic.setVisibility(View.VISIBLE);
                Toast.makeText(products.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}