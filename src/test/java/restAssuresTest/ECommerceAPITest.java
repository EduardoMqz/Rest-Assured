package restAssuresTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import files.ReusableMethods;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojoClasses.CommerceLogin;
import pojoClasses.CommerceLoginResponse;
import pojoClasses.CommerceOrder;
import pojoClasses.CommerceOrderDetail;

public class ECommerceAPITest {

    CommerceLogin commerceLogin = new CommerceLogin();
    CommerceLoginResponse commerceLoginResponse = new CommerceLoginResponse();
    CommerceOrder commerceOrder = new CommerceOrder();
    CommerceOrderDetail commerceOrderDetail = new CommerceOrderDetail();
    ReusableMethods configReader = new ReusableMethods();
    String productId;
    String response;

    @Test(priority = 1)
    public void login(){

        RequestSpecification requestSpecification =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
            .setContentType(ContentType.JSON).build();

        commerceLogin.setUserEmail("awalker@housereal.com");
        commerceLogin.setUserPassword("All3n.walk3r");

        commerceLoginResponse = given().log().all().spec(requestSpecification).body(commerceLogin)
        .when().post("api/ecom/auth/login")
        .then().log().all().extract().response().as(CommerceLoginResponse.class);

    }

    @Test(priority = 2)
    public void addProduct(){

        RequestSpecification addProductSpec =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
            .addHeader("Authorization", commerceLoginResponse.getToken()).build();

        response = given().log().all().spec(addProductSpec).param("productName", "Dr martens")
            .param("productAddedBy", commerceLoginResponse.getUserId()).param("productCategory", "fashion")
            .param("productSubCategory", "shoes").param("productPrice", "15100").param("productDescription", "stylish boots")
            .param("productFor", "men").multiPart("productImage",new File("src\\main\\resources\\attachment.jpg"))
        .when().post("api/ecom/product/add-product")
        .then().log().all().assertThat().statusCode(201).extract().response().asString();

        productId = configReader.rawToString(response, "productId");
    }

    @Test(priority = 3)
    public void createOrder(){

        RequestSpecification createOrderSpec =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
        .addHeader("Authorization", commerceLoginResponse.getToken()).setContentType(ContentType.JSON).build();

        commerceOrderDetail.setCountry("United Kingdom");
        commerceOrderDetail.setProductOrderedId(productId);

        List<CommerceOrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(commerceOrderDetail);

        commerceOrder.setOrders(orderDetailList);

        response = given().log().all().spec(createOrderSpec).body(commerceOrder)
        .when().post("api/ecom/order/create-order")
        .then().log().all().extract().response().asString();
    }

    @Test(priority = 4)
    public void deleteProduct(){
        RequestSpecification deleteProductSpec =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
        .addHeader("Authorization", commerceLoginResponse.getToken()).build();

        response = given().log().all().spec(deleteProductSpec).pathParam("productId", productId)
        .when().delete("api/ecom/product/delete-product/{productId}")
        .then().log().all().extract().response().asString();

        String message = configReader.rawToString(response, "message");

        Assert.assertEquals("Product Deleted Successfully", message);
    }

}
