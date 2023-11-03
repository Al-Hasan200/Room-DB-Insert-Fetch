package com.example.roomdbinsertfetch;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDetailsDao {

    @Query("select * from product_details")
    List<ProductDetails> getAllProductDetails();

    @Insert
    void saveProductDetails(ProductDetails productDetails);

    @Update
    void updateProductDetails(ProductDetails productDetails);

    @Delete
    void deleteProductDetails(ProductDetails productDetails);
}
