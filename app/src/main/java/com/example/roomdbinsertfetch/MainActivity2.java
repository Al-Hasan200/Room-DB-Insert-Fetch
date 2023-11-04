package com.example.roomdbinsertfetch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    EditText uProductName, uProductPrice;
    Button updateProduct;

    DatabaseHelper databaseHelper;

    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //find id
        uProductName = findViewById(R.id.uProductName);
        uProductPrice = findViewById(R.id.uProductPrice);
        updateProduct = findViewById(R.id.updateProduct);

        databaseHelper = DatabaseHelper.getDatabase(this);


        //set user details in edit text
        int u_id = Integer.parseInt(getIntent().getStringExtra("u_id"));
        uProductName.setText(getIntent().getStringExtra("u_name"));
        uProductPrice.setText(getIntent().getStringExtra("u_email"));

        updateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        databaseHelper.productDetailsDao().updateProductDetails(
                            new ProductDetails(u_id, uProductName.getText().toString(), uProductPrice.getText().toString())
                        );

                Toast.makeText(MainActivity2.this, "Data Updated...", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}