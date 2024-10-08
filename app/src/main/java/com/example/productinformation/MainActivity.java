package com.example.productinformation;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProductDatabaseHelper productDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        productDatabaseHelper = new ProductDatabaseHelper(this);
        this.deleteDatabase(productDatabaseHelper.getDatabaseName());
        List<Product> products = productDatabaseHelper.getAllProducts();

        if(products.isEmpty()){
            populateTable();
            products = productDatabaseHelper.getAllProducts();
        }

        ProductAdapter productAdapter = new ProductAdapter(products);
        RecyclerView recyclerView = findViewById(R.id.product_list);
        recyclerView.setAdapter(productAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void populateTable(){
        Product volleyballProduct = new Product("Volleyball","A regulation size volleyball",
            "Rapid Transit Sports",15.99,"volleyball.png");
        productDatabaseHelper.addProductToProductsTable(volleyballProduct);
        Product basketballProduct = new Product("Basketball","A regulation size basketball",
                "Rapid Transit Sports",19.99,"basketball.png");
        productDatabaseHelper.addProductToProductsTable(basketballProduct);
        Product footballProduct = new Product("Football","A regulation size football",
                "Rapid Transit Sports",18.98,"football.png");
        productDatabaseHelper.addProductToProductsTable(footballProduct);
        Product baseballProduct = new Product("Baseball","A regulation size baseball",
                "Rapid Transit Sports",9.99,"baseball.png");
        productDatabaseHelper.addProductToProductsTable(baseballProduct);
        Product hockeyStickProduct = new Product("Hockey Stick","A regulation size hockery stick",
                "Rapid Transit Sports",37.99,"hockeystick.png");
        productDatabaseHelper.addProductToProductsTable(hockeyStickProduct);
    }
}