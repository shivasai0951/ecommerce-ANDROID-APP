package com.shivasai.ecommerce.models.productModel;

public class productPutModel {

    private String ProductName;
    private double ProductPrice;

    public productPutModel(String productName, double productPrice) {
        ProductName = productName;
        ProductPrice = productPrice;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public double getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(double productPrice) {
        ProductPrice = productPrice;
    }




}
