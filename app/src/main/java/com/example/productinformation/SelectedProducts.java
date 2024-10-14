package com.example.productinformation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to pass the selected products to the EmailerActivity
 */
public class SelectedProducts implements Serializable {

    private List<Product> products;

    public SelectedProducts(){
        this.products = new ArrayList<>();
    }

    /**
     * Returns a list of all selected products
     * @return List<Product>
     */
    public List<Product> getProducts(){
        return products;
    }

    /**
     * Adds a product to the list of selected products
     * @param product
     */
    public void addProduct(Product product){
        products.add(product);
    }

    /**
     * Removes a product from the list of selected products
     * @param product
     */
    public void removeProduct(Product product){
        if(products.contains(product)){
            products.remove(product);
        }
    }
}
