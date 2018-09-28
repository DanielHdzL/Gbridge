package com.gbridge.gbridge.WebService;


import com.gbridge.gbridge.Domain.Bill;
import com.gbridge.gbridge.Domain.Product;
import com.google.gson.Gson;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


public class WebService {

    private ArrayList<Product> products = new ArrayList<>();
    private Product product = new Product();
    private ArrayList<Bill> bills = new ArrayList<>();

    public List<Product> getAllProducts(){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://40.124.44.225/api/products");
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        String answer = response.readEntity(String.class);
        Gson gson = new Gson();
        Product[] responseProducts = gson.fromJson(answer, Product[].class);
        for (Product currentProduct: responseProducts) {
            Product product = new Product(currentProduct.getName(), currentProduct.getQuantity(), currentProduct.getPrice(), currentProduct.getDescription());
            products.add(product);
        }
        return products;
    }

    public Product getProductById(String id){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://40.124.44.225/api/products/"+id);
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        String answer = response.readEntity(String.class);
        Gson gson = new Gson();
        Product[] responseProducts = gson.fromJson(answer, Product[].class);
        for (Product currentProduct: responseProducts) {
            Product productAUX = new Product(currentProduct.getName(), currentProduct.getQuantity(), currentProduct.getPrice(), currentProduct.getDescription());
            product = productAUX;
        }
        return product;
    }

    public List<Product> getProductsByName(String name){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(""+name); // falta la direccion del webService a consumir.
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        String answer = response.readEntity(String.class);
        Gson gson = new Gson();
        Product[] responseProducts = gson.fromJson(answer, Product[].class);
        for (Product currentProduct: responseProducts) {
            Product product = new Product(currentProduct.getName(), currentProduct.getQuantity(), currentProduct.getPrice(), currentProduct.getDescription());
            products.add(product);
        }
        return products;
    }

    public String transactionKey(String url, String registryKey){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://40.124.44.225/api/register/request"+url+"/"+registryKey);
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        String answer = response.readEntity(String.class);
        return answer;
    }
    /*Falta ver con Juan eso de la IP*/
    public List<Bill> buyProducts(String userIP, String transactionKey, int productQuantity){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://40.124.44.225/api/products/buy"+userIP+"/"+transactionKey+"/"+productQuantity); // falta la direccion del webService a consumir.
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        String answer = response.readEntity(String.class);
        Gson gson = new Gson();
        Bill[] responseBills = gson.fromJson(answer, Bill[].class);
        for (Bill currentBill: responseBills
             ) {
            Bill bill = new Bill(currentBill.getProduct(), currentBill.getCost());
            bills.add(bill);
        }
        return bills;
    }

}
