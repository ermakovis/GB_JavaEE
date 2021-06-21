package ru.ermakovis;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class RestClient {
    private static final String REST_URI = "http://127.0.0.1:8080/jsf-web-app/api/product";

    private final Client client = ClientBuilder.newClient();

    public static void main(String[] args) {
        RestClient client = new RestClient();
        ProductRepr productRepr =
                new ProductRepr(null, "product_new", "new product desc", 1, null);

        client.getByName("product_new").forEach(p -> client.delete(p.getId()));
        client.add(productRepr);
        client.getByName("product_new").forEach(p -> client.setCategory(p, 2));
        client.getByName("product_new").forEach(System.out::println);
        System.out.println(client.getById(1));
        client.getByCategoryName("category1").forEach(System.out::println);
        client.getAll().forEach(System.out::println);
    }

    public void add(ProductRepr productRepr) {
        client.target(REST_URI)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(productRepr, MediaType.APPLICATION_JSON));
    }

    public void setCategory(ProductRepr productRepr, Integer category_id) {
        client.target(REST_URI)
                .path("category")
                .path(String.valueOf(category_id))
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(productRepr, MediaType.APPLICATION_JSON));
    }

    public void delete(Integer id) {
        client.target(REST_URI)
                .path("delete")
                .path(String.valueOf(id))
                .request(MediaType.APPLICATION_JSON)
                .delete();
    }

    public ProductRepr getById(Integer id) {
        return client
                .target(REST_URI)
                .path("by_id")
                .path(String.valueOf(id))
                .request(MediaType.APPLICATION_JSON)
                .get(ProductRepr.class);
    }

    public List<ProductRepr> getByName(String name) {
        return client
                .target(REST_URI)
                .path("by_name")
                .path(name)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {});
    }

    public List<ProductRepr> getByCategoryName(String name) {
        return client
                .target(REST_URI)
                .path("by_category")
                .path(name)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {});
    }

    public List<ProductRepr> getAll() {
        return client
                .target(REST_URI)
                .path("all")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {});
    }
}
