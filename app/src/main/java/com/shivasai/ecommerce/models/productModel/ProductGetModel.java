package com.shivasai.ecommerce.models.productModel;

public class ProductGetModel {
    private String _id;
    private String productId;
    private String ProductName;
    private int ProductPrice;
    private String ProductImage;
    private int __v;

    public ProductGetModel(String _id, String productId, String productName, int productPrice, String productImage, int __v) {
        this._id = _id;
        this.productId = productId;
        ProductName = productName;
        ProductPrice = productPrice;
        ProductImage = productImage;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

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

    public int getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(int productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }



    // Getters and Setters

}
