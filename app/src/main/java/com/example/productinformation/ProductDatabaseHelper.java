package com.example.productinformation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for interacting with the product_information_database
 */
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

    /**
     * Creates the PRODUCTS_TABLE_NAME table
     * @param database
     */
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

    /**
     * No-op method
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * Add a Product to the database
     * @param product
     */
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

    /**
     * Returns all products in the database
     * @return List<Product>
     */
    public List<Product> getAllProducts(){
        SQLiteDatabase database = getReadableDatabase();

        Cursor productCursor = database.rawQuery("SELECT * FROM " + PRODUCTS_TABLE_NAME, null);
        ArrayList<Product> productList = new ArrayList<>();
        if(productCursor.moveToFirst()) {
            do {
                Product product = new Product(productCursor.getInt(0),productCursor.getString(1),
                    productCursor.getString(2), productCursor.getString(3), productCursor.getDouble(4),
                    productCursor.getString(5));
                productList.add(product);
            } while(productCursor.moveToNext());
        }
        productCursor.close();
        return productList;
    }
}
