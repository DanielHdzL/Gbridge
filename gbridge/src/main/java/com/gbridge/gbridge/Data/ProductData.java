package com.gbridge.gbridge.Data;

import com.gbridge.gbridge.Domain.Bill;
import com.gbridge.gbridge.Domain.Product;
import com.gbridge.gbridge.WebService.WebService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/products")
public class ProductData {

    WebService webService = new WebService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProducts(){
        return webService.getAllProducts();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProductById(@PathParam("id") String id){
        return webService.getProductById(id);
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProductsByName(@PathParam("name") String name){
        return webService.getProductsByName(name);
    }

    @GET
    @Path("/{url}/{registryKey}")
    public String transactionKey(@PathParam("url") String url, @PathParam("registryKey") String registryKey){
        return webService.transactionKey(url,registryKey);
    }

    @GET
    @Path("/{userIP}/{transactionKey}/{productQuantity}")
    public List<Bill> buyProducts(@PathParam("userIP") String userIP, @PathParam("transactionKey") String transactionKey, @PathParam("productQuantity") int productQuantity){
        return webService.buyProducts(userIP,transactionKey,productQuantity);
    }


    /*
    @POST
    @Path("/{registryKey}/{url}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String transactionKey(String registryKey, String url){
        return webService.transactionKey(registryKey, url);
    }

    @POST
    @Path("/{transactionKey}/{idProduct}/{productQuantity}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bill> buyProducts(String transactionKey, String idProduct, int productQuantity){
        return webService.buyProducts(transactionKey,idProduct,productQuantity);
    }
    */


}
