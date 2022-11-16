package ru.finance.market.postgres;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

public class PostgresRunner {
    public static void main(String[] args) throws IOException, SQLException, InterruptedException {
        var closeLatch = new CountDownLatch(1);
        var postgres = new ProjectPostgres(true, true, 5432, null);
        Runtime.getRuntime().addShutdownHook(new Thread(closeLatch::countDown));
        closeLatch.await();
        postgres.close();
    }
}