package ru.ermakovis.persist.product;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ProductWS {

    @WebMethod
    List<ProductRepr> getProducts();
}
