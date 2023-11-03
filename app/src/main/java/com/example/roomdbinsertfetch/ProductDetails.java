package com.example.roomdbinsertfetch;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_details")
public class ProductDetails {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "productName")
    private String productName;
    @ColumnInfo(name = "productPrice")
    private String productPrice;

    public ProductDetails(int id, String productName, String productPrice) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    @Ignore
    public ProductDetails(String productName, String productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
