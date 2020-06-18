/*
|--------------------------------------------------------------------------
| PurchaseOrder java class
|--------------------------------------------------------------------------
|
|PurchaseOrder class is used for create a purchase order from  shopping cart
|given all information need to create an order
|
*/
package com.ecom.ecar.request;

import java.util.List;


public class PurchaseOrder {


    private String accountId;

    private String shipping_info;

    private int total_price;

    private String status;

    private List<String> bookIds;

    public List<String> getBookIds() {
        return bookIds;
    }


    public void setBookIds(List<String> bookIds) {
        this.bookIds = bookIds;
    }


    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getShipping_info() {
        return shipping_info;
    }

    public void setShipping_info(String shipping_info) {
        this.shipping_info = shipping_info;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int i) {
        this.total_price = i;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
