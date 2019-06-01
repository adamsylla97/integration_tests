package edu.iis.mto.blog.rest.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class AdditionalRestApiTests extends FunctionalTests {

    private static final String USER_API = "/blog/user";

    @Test
    public void emailHasToBeUnique(){
        JSONObject jsonObj = new JSONObject().put("email", "john@domain.com");
        RestAssured.given()
                   .accept(ContentType.JSON)
                   .header("Content-Type", "application/json;charset=UTF-8")
                   .body(jsonObj.toString())
                   .expect()
                   .log()
                   .all()
                   .statusCode(HttpStatus.SC_CONFLICT)
                   .when()
                   .post(USER_API);
    }

    private static final String POST_API_CONFIRMED = "blog/user/1/post";
    private static final String POST_API_NEW = "blog/user/3/post";

    @Ignore
    @Test
    public void userWithStatusNewShoudntBeAbleToAddPost(){
        JSONObject jsonObj = new JSONObject().put("entry", "Test");

        RestAssured.given()
                   .accept(ContentType.JSON)
                   .header("Content-Type", "application/json;charset=UTF-8")
                   .body(jsonObj.toString())
                   .expect()
                   .log()
                   .all()
                   .statusCode(HttpStatus.SC_CONFLICT)
                   .when()
                   .post(POST_API_NEW);
    }

    @Test
    public void userWithStatusConfirmedShouldBeAbleToAddPost(){
        JSONObject jsonObj = new JSONObject().put("entry", "test2");
        RestAssured.given()
                   .accept(ContentType.JSON)
                   .header("Content-Type", "application/json;charset=UTF-8")
                   .body(jsonObj.toString())
                   .expect()
                   .log()
                   .all()
                   .statusCode(HttpStatus.SC_CREATED)
                   .when()
                   .post(POST_API_CONFIRMED);
    }

    private static final String POST_LIKE_NEW = "/blog/user/2/like/1";
    private static final String POST_LIKE_CONFIRMED = "/blog/user/1/like/2";
    private static final String POST_LIKE_AUTHOR = "/blog/user/1/like/1";

    @Test
    public void userWithoutConfirmedStatusCannotLikePost(){

        JSONObject jsonObj = new JSONObject();

        RestAssured.given()
                   .accept(ContentType.JSON)
                   .header("Content-Type", "application/json;charset=UTF-8")
                   .body(jsonObj.toString())
                   .expect()
                   .log()
                   .all()
                   .statusCode(HttpStatus.SC_BAD_REQUEST)
                   .when()
                   .post(POST_LIKE_NEW);

    }

    @Test
    public void userWithConfirmedStatusCanLikePost(){

        JSONObject jsonObj = new JSONObject();

        RestAssured.given()
                   .accept(ContentType.JSON)
                   .header("Content-Type", "application/json;charset=UTF-8")
                   .body(jsonObj.toString())
                   .expect()
                   .log()
                   .all()
                   .statusCode(HttpStatus.SC_BAD_REQUEST)
                   .when()
                   .post(POST_LIKE_CONFIRMED);

    }

    @Test
    public void postCannotBeLikedByAuthorOfThatPost(){

        JSONObject jsonObj = new JSONObject();

        RestAssured.given()
                   .accept(ContentType.JSON)
                   .header("Content-Type", "application/json;charset=UTF-8")
                   .body(jsonObj.toString())
                   .expect()
                   .log()
                   .all()
                   .statusCode(HttpStatus.SC_BAD_REQUEST)
                   .when()
                   .post(POST_LIKE_AUTHOR);

    }


    @Test
    public void relikeOfPostShouldntChangeItsStatus(){

        JSONObject jsonObj = new JSONObject();

        RestAssured.given()
                   .accept(ContentType.JSON)
                   .header("Content-Type", "application/json;charset=UTF-8")
                   .body(jsonObj.toString())
                   .expect()
                   .log()
                   .all()
                   .statusCode(HttpStatus.SC_BAD_REQUEST)
                   .when()
                   .post(POST_LIKE_AUTHOR);
    }



    @Test
    public void searchingForDeletedUserPostsShouldntBeSuccessful(){

    }

    @Ignore
    @Test
    public void searchingForPostsShouldReturnProperAmountOfLikes(){

    }

    @Ignore
    @Test
    public void lookingForUsersShouldntReturnDeletedUsers(){

    }

}
