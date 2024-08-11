package com.shivasai.ecommerce.admin.dashboard;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.shivasai.ecommerce.R;
import com.shivasai.ecommerce.adaptors.ImageAdapter;

public class AdminDashboard extends AppCompatActivity {

    TextView name;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        getSupportActionBar().hide();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        Bundle bundle = getIntent().getExtras();
        String userName = bundle.getString("userName");
        String userEmail = bundle.getString("userEmail");
        name =findViewById(R.id.UserName);
        email =findViewById(R.id.UserEmail);

        name.setText(userName);
        email.setText(userEmail);

        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this));
    }
}