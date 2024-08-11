package com.shivasai.ecommerce.models.productModel;

public class ProductPostModel {

    private String productId;
    private String ProductName;
    private double ProductPrice;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    private String ProductImage;


        public ProductPostModel(String productId, String ProductName, double ProductPrice, String ProductImage) {
            this.productId = productId;
            this.ProductName = ProductName;
            this.ProductPrice = ProductPrice;
            this.ProductImage = ProductImage;
        }

        // Getters and setters
    }
