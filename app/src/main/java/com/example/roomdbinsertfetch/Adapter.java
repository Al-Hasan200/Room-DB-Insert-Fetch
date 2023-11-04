package com.example.roomdbinsertfetch;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;
    private List<ProductDetails> productDetails;
    Activity activity;

    DatabaseHelper databaseHelper;

    public Adapter(Context context, List<ProductDetails> productDetails, Activity activity) {
        this.context = context;
        this.productDetails = productDetails;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        ProductDetails productDetails1 = productDetails.get(position);
        holder.nameText.setText(productDetails1.getProductName());
        holder.emailText.setText(productDetails1.getProductPrice());

        holder.updateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity2.class);
                intent.putExtra("u_id", String.valueOf(productDetails1.getId()));
                intent.putExtra("u_name", productDetails1.getProductName());
                intent.putExtra("u_email", productDetails1.getProductPrice());
                activity.startActivityForResult(intent, 1);
            }
        });

        holder.deleteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
databaseHelper = DatabaseHelper.getDatabase(context);
databaseHelper.productDetailsDao().deleteProductDetails(productDetails1);
productDetails.remove(position);
notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return productDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameText, emailText;
        ImageView updateText, deleteText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.nameText);
            emailText = itemView.findViewById(R.id.emailText);
            updateText = itemView.findViewById(R.id.updateText);
            deleteText = itemView.findViewById(R.id.deleteText);
        }
    }
}
