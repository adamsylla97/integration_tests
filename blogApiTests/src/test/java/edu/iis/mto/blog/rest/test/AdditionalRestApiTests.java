package edu.iis.mto.blog.rest.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class AdditionalRestApiTests {

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

    @Ignore
    @Test
    public void onlyUserWithConfirmedStatusCanAddPost(){

    }

    @Ignore
    @Test
    public void onlyUserWithConfirmedStatusCanLikePost(){

    }

    @Ignore
    @Test
    public void postCannotBeLikedByAuthorOfThatPost(){

    }

    @Ignore
    @Test
    public void relikeOfPostShouldntChangeItsStatus(){

    }

    @Ignore
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
