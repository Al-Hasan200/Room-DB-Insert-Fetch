package com.example.roomdbinsertfetch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //variable declaring
    EditText productName, productPrice;
    Button saveProduct;

    RecyclerView recyclerView;
    Adapter adapter;
    List<ProductDetails> arrayList1;
    LinearLayoutManager linearLayoutManager;
    ProductDetails p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find id
        productName = findViewById(R.id.productName);
        productPrice = findViewById(R.id.productPrice);
        saveProduct = findViewById(R.id.saveProduct);
        recyclerView = findViewById(R.id.recyclerView);

        //object create database helper class
        DatabaseHelper databaseHelper = DatabaseHelper.getDatabase(this);

//show data

        List<ProductDetails> arrayList = databaseHelper.productDetailsDao().getAllProductDetails();
        adapter = new Adapter(MainActivity.this, arrayList, this);


        //set data recyclerview
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


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

                List<ProductDetails> arrayList = databaseHelper.productDetailsDao().getAllProductDetails();
                adapter = new Adapter(MainActivity.this, arrayList, MainActivity.this);


                //set data recyclerview
                linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                linearLayoutManager.setReverseLayout(true);
                linearLayoutManager.setStackFromEnd(true);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);


                /*for(int i = 0; i < arrayList.size(); i++){



                    Log.d("Data", "Product Name: "+arrayList.get(i).getProductName() + "Product Price: "+arrayList.get(i).getProductPrice());
                }*/
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}