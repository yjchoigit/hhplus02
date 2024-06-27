package com.hhplus.hhplus02.myapplication;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import java.io.File;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Setting {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUpByBeforeEach() {
        RestAssured.port = port;
    }

    public static ExtractableResponse<Response> get(String path) {
        return RestAssured
                .given().log().all()
                .when().get(path)
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> get(String path, Map<String, ?> parametersMap) {
        return RestAssured
                .given().log().all()
                .queryParams(parametersMap)
                .when().get(path)
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> get(String path, String session) {
        return RestAssured
                .given().log().all()
                .with().cookie("SESSION", session)
                .when().get(path)
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> get(String path, Map<String, ?> parametersMap, String session) {
        return RestAssured
                .given().log().all()
                .with().cookie("SESSION", session)
                .queryParams(parametersMap)
                .when().get(path)
                .then().log().all().extract();
    }

    public static <T> ExtractableResponse<Response> post(String path) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post(path)
                .then().log().all().extract();
    }

    public static <T> ExtractableResponse<Response> post(String path, T requestBody) {
        return RestAssured
                .given().log().all()
                .body(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post(path)
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> post(String path, File file, Map<String, ?> requestParam) {
        return RestAssured
                .given().log().all()
                .multiPart("filesList", file)
                .formParams(requestParam)
                .when().post(path)
                .then().log().all().extract();
    }

    public static <T> ExtractableResponse<Response> post(String path, T requestBody, String session) {
        return RestAssured
                .given().log().all()
                .with().cookie("SESSION", session)
                .body(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post(path)
                .then().log().all().extract();
    }

    public static <T> ExtractableResponse<Response> put(String path) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().put(path)
                .then().log().all().extract();
    }

    public static <T> ExtractableResponse<Response> put(String path, String session) {
        return RestAssured
                .given().log().all()
                .with().cookie("SESSION", session)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().put(path)
                .then().log().all().extract();
    }

    public static <T> ExtractableResponse<Response> put(String path, T requestBody) {
        return RestAssured
                .given().log().all()
                .body(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().put(path)
                .then().log().all().extract();
    }

    public static <T> ExtractableResponse<Response> put(String path, T requestBody, String session) {
        return RestAssured
                .given().log().all()
                .with().cookie("SESSION", session)
                .body(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().put(path)
                .then().log().all().extract();
    }

    public static <T> ExtractableResponse<Response> patch(String path) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().patch(path)
                .then().log().all().extract();
    }

    public static <T> ExtractableResponse<Response> patch(String path, T requestBody) {
        return RestAssured
                .given().log().all()
                .body(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().patch(path)
                .then().log().all().extract();
    }

    public static <T> ExtractableResponse<Response> patch(String path, T requestBody, String session) {
        return RestAssured
                .given().log().all()
                .with().cookie("SESSION", session)
                .body(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().patch(path)
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> delete(String path) {
        return RestAssured
                .given().log().all()
                .when().delete(path)
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> delete(String path, String session) {
        return RestAssured
                .given().log().all()
                .with().cookie("SESSION", session)
                .when().delete(path)
                .then().log().all().extract();
    }

    public static <T> ExtractableResponse<Response> delete(String path, T requestBody) {
        return RestAssured
                .given().log().all()
                .body(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().delete(path)
                .then().log().all().extract();
    }

    public static <T> ExtractableResponse<Response> delete(String path, T requestBody, String session) {
        return RestAssured
                .given().log().all()
                .with().cookie("SESSION", session)
                .body(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().delete(path)
                .then().log().all().extract();
    }
}
