package com.example.productinformation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Activity that both displays the information for the selected products and starts the email
 * application when the user clicks the Email Items button.
 */
public class EmailerActivity extends AppCompatActivity {

    Context context = this;

    //This is what listens for the email activity to close. Once the email activity closes it displays
    //the toast and returns to the main activity
    ActivityResultLauncher<Intent> sendEmailActivityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    (result) -> {
                        Toast toast = Toast.makeText(context, "Email Sent.",Toast.LENGTH_SHORT);
                        toast.show();
                        Intent sendToMainActivityIntent = new Intent(EmailerActivity.this, MainActivity.class);
                        startActivity(sendToMainActivityIntent);
                    }
            );

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
        //Gets the selected products as a serializable extra from the intent, then displays them
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

    /**
     * Formats the email and then launches the email intent, which prompts the user to select their
     * email application of choice.
     * @param selectedProducts
     */
    private void sendEmail(SelectedProducts selectedProducts){
        String toEmail = "sweng888mobileapps@gmail.com";
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
        sendEmailActivityResultLauncher.launch(sendEmailIntent);
    }
}
