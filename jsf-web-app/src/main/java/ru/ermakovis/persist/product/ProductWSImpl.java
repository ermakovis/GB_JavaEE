package ru.ermakovis.persist.product;

import ru.ermakovis.service.ProductService;

import javax.ejb.EJB;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.ermakovis.persist.product.ProductWS")
public class ProductWSImpl implements ProductWS {
    @EJB
    ProductService service;

    @Override
    public List<ProductRepr> getProducts() {
        return service.findAllRepr();
    }
}
