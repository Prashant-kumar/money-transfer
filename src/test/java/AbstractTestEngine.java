import com.revolut.moneytransfer.Application;
import com.revolut.moneytransfer.config.WebConfig;
import com.revolut.moneytransfer.repository.AccountRepository;
import com.revolut.moneytransfer.repository.TransactionRepository;
import com.revolut.moneytransfer.service.impl.AccountServiceImpl;
import com.revolut.moneytransfer.service.impl.TransactionServiceImpl;
import io.restassured.RestAssured;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcConnectionPool;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import javax.sql.DataSource;

import static spark.Spark.*;

public abstract class AbstractTestEngine {

    public static final String PROTOCOL = "http";
    public static final int PORT = 8081;
    public static final String HOST = "localhost";
    public static final String PATH = "";

    @BeforeAll
    static void startApplication() {
        try {
            stop();
        }catch (Exception e) {}
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
