/*
|--------------------------------------------------------------------------
| PurchaseResponse java class
|--------------------------------------------------------------------------
|
|PurchaseResponse class is used for create a purchase order from  shopping cart
|given all information need to create an order
|
*/
package com.ecom.ecar.response;

public class PurchaseResponse {
    private String purchase_id;

    public String getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(String purchase_id) {
        this.purchase_id = purchase_id;
    }
}
