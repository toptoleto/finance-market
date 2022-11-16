package ru.finance.market.postgres;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class ProjectPostgres {

    private EmbeddedPostgres postgres;

    private boolean cleanData;
    private boolean initData;
    private final Integer port;
    private final String dataDir;

    public ProjectPostgres(boolean cleanData, boolean initData, Integer port, String dataDir) throws IOException, SQLException {
        this.cleanData = cleanData;
        this.initData = initData;
        this.port = port;
        this.dataDir = dataDir;

        postgres = initPostgres();
    }

    private EmbeddedPostgres initPostgres() throws IOException, SQLException {
        var builder = EmbeddedPostgres.builder()
                .setCleanDataDirectory(cleanData);
        if (port != null)
            builder.setPort(port);
        if (dataDir != null)
            builder.setDataDirectory(dataDir);

        EmbeddedPostgres pg = builder.start();

        if (initData) {
            try (var connection = pg.getPostgresDatabase().getConnection()) {
                executeFile(connection, "db/01_create_roles.sql");
                executeFile(connection, "db/02_create_db.sql");
            }
            try (var connection = pg.getDatabase("ru/finance/market/postgres", "finance_market").getConnection()) {
                executeFile(connection, "db/03_create_schemas.sql");
                executeFile(connection, "db/04_create_extensions.sql");
            }
        }

        return pg;
    }

    public void close() throws IOException {
        if(postgres != null)
            postgres.close();
    }

    public int getPort(){
        return postgres.getPort();
    }

    private void executeFile(Connection connection, String file) throws SQLException, IOException {
        try (var statement = connection.createStatement()) {
            for (String string : getInitSql(file))
                statement.execute(string.replaceFirst("\n", ""));
        }
    }

    private String[] getInitSql(String file) throws IOException {
        URL resource = Thread.currentThread().getContextClassLoader()
                .getResource(file);
        Objects.requireNonNull(resource, "Can't load resource: "+file);
        return IOUtils.toString(resource, StandardCharsets.UTF_8.name()).split(";");
    }

}