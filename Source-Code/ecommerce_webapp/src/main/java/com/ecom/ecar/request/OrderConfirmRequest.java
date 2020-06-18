/*
|--------------------------------------------------------------------------
| OrderConfirmRequest java class
|--------------------------------------------------------------------------
|
|Order Confirm class is a representation of an order status and send it to service
|
*/
package com.ecom.ecar.request;

/**
 * @author Varun Hanabe Muralidhara UOttawa ID : 300055628
 */
public class OrderConfirmRequest {

    private String purchase_id;
    private String status;

    public String getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(String purchase_id) {
        this.purchase_id = purchase_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
