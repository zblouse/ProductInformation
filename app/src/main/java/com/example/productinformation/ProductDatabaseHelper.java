package com.example.productinformation;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class ProductDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "product_information_database";
    private static final int DATABASE_VERSION = 1;
    private static final String PRODUCTS_TABLE_NAME = "products";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_SELLER = "seller";
    private static final String KEY_PRICE = "price";
    private static final String KEY_IMAGE = "image";

    public ProductDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String createProductsTableQuery = "CREATE TABLE " + PRODUCTS_TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_NAME + " TEXT," +
                KEY_DESCRIPTION + " TEXT," +
                KEY_SELLER + " TEXT," +
                KEY_PRICE + " REAL," +
                KEY_IMAGE + " TEXT" +
                ")";
        database.execSQL(createProductsTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addProductToProductsTable(Product product){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, product.getName());
        contentValues.put(KEY_DESCRIPTION, product.getDescription());
        contentValues.put(KEY_SELLER, product.getSeller());
        contentValues.put(KEY_PRICE, product.getPrice());
        contentValues.put(KEY_IMAGE, product.getImage());
        database.insert(PRODUCTS_TABLE_NAME, null, contentValues);
        database.close();
    }

    public List<Product> getAllProducts(){
        
    }
}
