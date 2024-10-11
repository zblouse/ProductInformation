package com.example.productinformation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SelectedProducts implements Serializable {

    private List<Product> products;

    public SelectedProducts(){
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts(){
        return products;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(Product product){
        if(products.contains(product)){
            products.remove(product);
        }
    }
}
