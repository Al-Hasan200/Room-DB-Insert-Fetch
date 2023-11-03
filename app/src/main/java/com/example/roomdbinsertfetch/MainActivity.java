package com.example.roomdbinsertfetch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //variable declaring
    EditText productName, productPrice;
    Button saveProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find id
        productName = findViewById(R.id.productName);
        productPrice = findViewById(R.id.productPrice);
        saveProduct = findViewById(R.id.saveProduct);

        //object create database helper class
        DatabaseHelper databaseHelper = DatabaseHelper.getDatabase(this);

        //save product details
        saveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pName = productName.getText().toString();
                String pPrice = productPrice.getText().toString();

                //add data
                databaseHelper.productDetailsDao().saveProductDetails(
                        new ProductDetails(pName, pPrice)
                );

                //show data
                ArrayList<ProductDetails> arrayList = (ArrayList<ProductDetails>) databaseHelper.productDetailsDao().getAllProductDetails();

                for(int i = 0; i < arrayList.size(); i++){
                    Log.d("Data", "Product Name: "+arrayList.get(i).getProductName() + "Product Price: "+arrayList.get(i).getProductPrice());
                }
            }
        });
    }
}