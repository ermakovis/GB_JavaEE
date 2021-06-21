package ru.ermakovis.persist.product;

import javax.ws.rs.*;
import java.util.List;

public interface ProductRS {
    @POST
    void add(ProductRepr repr);

    @DELETE
    @Path("delete/{id}")
    void delete(@PathParam("id") Integer id);

    @PUT
    @Path("category/{category_id}")
    void setCategory(ProductRepr repr, @PathParam("category_id") Integer category_id);

    @GET
    @Path("/by_id/{id}")
    ProductRepr getById(Integer id);

    @GET
    @Path("/by_name/{name}")
    List<ProductRepr> getByName(@PathParam("name") String name);

    @GET
    @Path("/by_category/{name}")
    List<ProductRepr> getByCategoryName(@PathParam("name") String name);

    @GET
    @Path("/all")
    List<ProductRepr> getAll();
}
