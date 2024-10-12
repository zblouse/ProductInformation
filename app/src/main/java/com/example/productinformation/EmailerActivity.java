package com.example.productinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EmailerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.emailer_activity);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SelectedProducts selectedProducts = getIntent().getSerializableExtra("selectedProducts", SelectedProducts.class);
        ProductAdapter productAdapter = new ProductAdapter(selectedProducts.getProducts());
        RecyclerView recyclerView = findViewById(R.id.selected_product_list);
        recyclerView.setAdapter(productAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        Button sendEmailButton = findViewById(R.id.send_email_button);
        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail(selectedProducts);
            }
        });
    }

    private void sendEmail(SelectedProducts selectedProducts){
        String toEmail = "zrb5155@psu.edu";
        String subject = "Requested Product Information";
        String body = "";
        for(Product product: selectedProducts.getProducts()){
            body+="Product: " + product.getName() + "\n";
            body+="Description: " + product.getDescription() + "\n";
            body+="Price: " + product.getPrice() + "\n";
            body+="Seller: " + product.getSeller() + "\n";
        }

        Intent sendEmailIntent = new Intent(Intent.ACTION_SEND);
        sendEmailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{toEmail});
        sendEmailIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
        sendEmailIntent.putExtra(Intent.EXTRA_TEXT, body);
        sendEmailIntent.setType("message/rfc822");
        startActivity(sendEmailIntent);
    }
}
