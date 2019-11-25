package com.revolut.test.integration;

import com.revolut.moneytransfer.Application;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static spark.Spark.awaitStop;
import static spark.Spark.stop;

public abstract class AbstractTestEngine {

    public static final String PROTOCOL = "http";
    public static final int PORT = 8081;
    public static final String HOST = "localhost";
    public static final String PATH = "";

    @BeforeAll
    static void startApplication() {
        Application.startApplication(PORT);
        RestAssured.baseURI = PROTOCOL + "://" + HOST;
        RestAssured.port = PORT;
        RestAssured.basePath = PATH;
    }

    @AfterAll
    static void stopApplication() {
        stop();
        awaitStop();
    }

}
