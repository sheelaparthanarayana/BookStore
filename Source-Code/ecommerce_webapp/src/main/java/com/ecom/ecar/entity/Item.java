/*
|--------------------------------------------------------------------------
| Item java class
|--------------------------------------------------------------------------
|
|Item is user for store items that the current user selected to shopping cart
|An item represents an single book and the quantity in shopping cart
|
*/
package com.ecom.ecar.entity;

public class Item {
    private int quantity;
    private String bookid;
    private String title;
    private int price;


    public String getId() {
        return bookid;
    }

    public void setId(String id) {
        this.bookid = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
