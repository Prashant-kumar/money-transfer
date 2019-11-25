import com.revolut.moneytransfer.config.WebConfig;
import com.revolut.moneytransfer.repository.AccountRepository;
import com.revolut.moneytransfer.service.AccountService;
import com.revolut.moneytransfer.service.impl.AccountServiceImpl;
import io.restassured.RestAssured;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcConnectionPool;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static spark.Spark.port;
import static spark.Spark.stop;

public abstract class AbstractTestEngine {
    public static final AccountService accountService;
    public static final String PROTOCOL = "http";
    public static final int PORT = 8081;
    public static final String HOST = "localhost";
    public static final String PATH = "";

    static {
        DataSource dataSource = JdbcConnectionPool.create("jdbc:h2:mem:moneytransfer", "sa", "");
        Flyway.configure().dataSource(dataSource).load().migrate();
        DSLContext jooq = DSL.using(dataSource, SQLDialect.H2);
        accountService = new AccountServiceImpl(new AccountRepository(jooq));



    }


    @BeforeAll
    static void startApplication() {
        port(PORT);
        WebConfig.setupRoutes();
        WebConfig.setupExceptionHandler();

        RestAssured.baseURI = PROTOCOL + "://" + HOST;
        RestAssured.port = PORT;
        RestAssured.basePath = PATH;
    }

    @AfterAll
    static void stopApplication() {
        stop();
    }

}
