import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReqresTest {

  public static final String URL = "https://reqres.in/";


  @Test
  public void checkAvatarAndIdTest() {
    RestSpecifications.installSpecifictations(RestSpecifications.responseSpecification200(),
        RestSpecifications.requestSpecification(URL));
    List<User> userList = given()
        .when()
        .get("api/users?page=2")
        .then().log().all()
        .extract().body().jsonPath().getList("data", User.class);
    userList.forEach(x -> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));

    userList.forEach(x -> Assertions.assertTrue(x.getEmail().endsWith("reqres.in")));

  }


  @Test
  public void successRegTest() {
    RestSpecifications.installSpecifictations(RestSpecifications.responseSpecification200(),
        RestSpecifications.requestSpecification(URL));
    Integer id = 4;
    String token = "QpwL5tke4Pnpja7X4";
    Register register = new Register("eve.holt@reqres.in", "pistol");
    SuccessReg successReg = given()
        .body(register)
        .when()
        .post("api/register")
        .then().log().all()
        .extract().as(SuccessReg.class);
    Assertions.assertNotNull(successReg.getId());
    Assertions.assertNotNull(successReg.getToken());
    Assertions.assertEquals(id, successReg.getId());
    Assertions.assertEquals(token, successReg.getToken());
  }


  @Test
  public void failedRegTest() {
    RestSpecifications.installSpecifictations(RestSpecifications.responseSpecification400(),
        RestSpecifications.requestSpecification(URL));
    String error = "Missing password";
    Register register = new Register("sydney@fife");
    FailedReg failedReg = given()
        .body(register)
        .when()
        .post("api/register")
        .then().log().all()
        .extract().as(FailedReg.class);

    Assertions.assertNotNull(failedReg.getError());
    Assertions.assertEquals(error, failedReg.getError());
  }

}
