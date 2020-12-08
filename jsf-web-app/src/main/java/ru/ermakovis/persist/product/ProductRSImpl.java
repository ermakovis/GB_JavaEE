package ru.ermakovis.persist.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ermakovis.service.ProductService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("product")
public class ProductRSImpl implements ProductRS {
    private static final Logger logger = LoggerFactory.getLogger(ProductRSImpl.class);

    @EJB
    ProductService service;

    @Override
    @POST
    public void add(ProductRepr repr) {
        logger.info("add call " + repr);
        service.insert(repr);
    }

    @Override
    @DELETE
    @Path("delete/{id}")
    public void delete(@PathParam("id") Integer id) {
        service.delete(id);
    }

    @Override
    @PUT
    @Path("category/{category_id}")
    public void setCategory(ProductRepr repr,
                            @PathParam("category_id") Integer category_id) {
        service.setCategory(repr, category_id);
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/by_id/{id}")
    public ProductRepr getById(@PathParam("id") Integer id) {
        logger.info("get by id call");
        return service.find(id);
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/by_name/{name}")
    public List<ProductRepr> getByName(@PathParam("name") String name) {
        logger.info("find by name");
        return service.findByName(name);
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/by_category/{name}")
    public List<ProductRepr> getByCategoryName(@PathParam("name") String name) {
        logger.info("find by category name");
        return service.findByCategoryName(name);
    }

    @Override
    @GET
    @Produces("application/json")
    @Path("/all")
    public List<ProductRepr> getAll() {
        return service.findAllRepr();
    }
}
