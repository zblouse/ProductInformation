package com.example.productinformation;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * POJO for Products
 */
public class Product implements Parcelable, Serializable {

    private Integer id;
    private String name;
    private String description;
    private String seller;
    private Double price;
    private int imageId;

    public Product(int id, String name, String description, String seller, double price, int imageId){
        this.id = id;
        this.name = name;
        this.description = description;
        this.seller = seller;
        this.price = price;
        this.imageId = imageId;
    }

    public Product(String name, String description, String seller, double price, int imageId){
        this.name = name;
        this.description = description;
        this.seller = seller;
        this.price = price;
        this.imageId = imageId;
    }

    public Product(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.seller = in.readString();
        this.price = in.readDouble();
        this.imageId = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.description);
        parcel.writeString(this.seller);
        parcel.writeDouble(this.price);
        parcel.writeInt(this.imageId);
    }

    public Integer getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public String getSeller(){
        return this.seller;
    }

    public Double getPrice(){
        return this.price;
    }

    public int getImageId(){
        return this.imageId;
    }
}
