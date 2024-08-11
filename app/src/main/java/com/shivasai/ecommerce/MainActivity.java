package com.shivasai.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.shivasai.ecommerce.adaptors.ImageAdapter;
import com.shivasai.ecommerce.login.loginActivity;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
        finish();
    }
}
