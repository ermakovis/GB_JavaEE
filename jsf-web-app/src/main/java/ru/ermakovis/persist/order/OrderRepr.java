package ru.ermakovis.persist.order;

import java.io.Serializable;

public class OrderRepr implements Serializable {
    private Integer id;
    private String productName;
    private Integer quantity;

    public OrderRepr() {
    }

    public OrderRepr(Integer id, String productName, Integer quantity) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
