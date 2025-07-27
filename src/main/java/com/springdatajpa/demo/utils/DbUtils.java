package com.springdatajpa.demo.utils;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DbUtils {
	public static final String MARIADB = "mariadb";
    public static final String POSTGRESQL = "postgresql";
    public static final String DB_TYPE = "DB_TYPE";
    public static final String MARIADB_DRIVER = "org.mariadb.jdbc.Driver";
    public static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";
    public static final String DEFAULT_DATABASE_CONNECT_TIMEOUT_MS = "10000";
    public static final String DEFAULT_DATABASE_SOCKET_READ_TIMEOUT_MS = "10000";
    public static final String DATABASE_CONNECT_TIMEOUT_MS = "DATABASE_CONNECT_TIMEOUT_MS";
    public static final String DATABASE_SOCKET_READ_TIMEOUT_MS = "DATABASE_SOCKET_READ_TIMEOUT_MS";
    public static final String DOUBLE_DASH = "--";
    public static final String END_COMMENT = "*/;";
    public static final String START_COMMENT = "/*!";
    public static final char SPACE = ' ';
    public static final String SEMI_COLON = ";";

    // Visible For Test
    protected static final HashMap<String, String> envOverrideForTest = new HashMap<>();

    public static void main(String[] args) {
        String dbType = getFromEnv(DB_TYPE, MARIADB);
        DbUtils dbUtils = null;
        if (MARIADB.equals(dbType)) {
//            dbUtils = new MariaDBUtils();
//        } else if (POSTGRESQL.equals(dbType)) {
//            dbUtils = new PostgresqlDBUtils();
        } else {
            System.out.print("FAILURE"); // NOSONAR
            return;
        }

        handle(args, dbUtils);
    }

    protected static void handle(String[] args, DbUtils dbUtils) {
        if (args.length > 0) {
            switch (args[0]) {
            case "ping":
                if (args.length > 10) {
                    dbUtils.checkDbAvailable(args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
                            args[9], args[10]);
                } else {
                    dbUtils.checkDbAvailable(args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
                            args[9], null);
                }
                break;
            case "create":
                if (args.length > 10) {
                    dbUtils.createDatabase(args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
                            args[9], args[10]);
                } else {
                    dbUtils.createDatabase(args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
                            args[9], null);
                }
                break;
            case "executeScript":
                dbUtils.executeScript(args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9],
                        args[10]);
                break;
            case "pingUser":
                dbUtils.checkUserAvailable(args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
                        args[9], args[10]);
                break;
            case "createUser":
                dbUtils.createUser(args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9],
                        args[10]);
                break;
            case "getDatabaseName":
                String dbName = dbUtils.getDatabaseName(args[1]);
                System.out.print(dbName); // NOSONAR
                break;
            case "getDatabaseType":
                String databaseType = dbUtils.getDatabaseType(args[1]);
                System.out.print(databaseType.toLowerCase()); // NOSONAR
                break;
            case "getTablesCount":
                dbUtils.getTablesCount(args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9],
                        args[10]);
                break;
            default:
                System.out.print("FAILURE"); // NOSONAR
            }
        } else {
            System.out.print("FAILURE"); // NOSONAR
        }
    }

    protected abstract void checkDbAvailable(String dbUrl, String dbUsername, String dbPassword, String isSecure,
            String trustStore, String trustStorePassword, String keyStore, String keyStorePassword,
            String isTrustServerCertificate, String schemaName);

    protected abstract void createDatabase(String dbUrl, String dbUsername, String dbPassword, String isSecure,
            String trustStore, String trustStorePassword, String keyStore, String keyStorePassword,
            String isTrustServerCertificate, String schemaName);

    protected abstract void checkUserAvailable(String dbUrl, String dbUsername, String dbPassword, String isSecure,
            String trustStore, String trustStorePassword, String keyStore, String keyStorePassword,
            String isTrustServerCertificate, String username);

    protected abstract void createUser(String dbUrl, String dbUsername, String dbPassword, String isSecure,
            String trustStore, String trustStorePassword, String keyStore, String keyStorePassword,
            String isTrustServerCertificate, String username);

    protected abstract void executeScript(String dbUrl, String dbUsername, String dbPassword, String isSecure,
            String trustStore, String trustStorePassword, String keyStore, String keyStorePassword,
            String isTrustServerCertificate, String schemaCreationScript);

    protected abstract void getTablesCount(String dbUrl, String dbUsername, String dbPassword, String isSecure,
            String trustStore, String trustStorePassword, String keyStore, String keyStorePassword,
            String isTrustServerCertificate, String databaseName);

    protected String getDatabaseName(String dbUrl) {
        Matcher matcher = getJdbcUrlMatcher(dbUrl);
        return matcher.group(4).replace("/", "");
    }

    protected String getDatabaseType(String dbUrl) {
        Matcher matcher = getJdbcUrlMatcher(dbUrl);
        return matcher.group(2).replace("://", "");
    }

    protected String getDbUrlWithoutDatabaseName(String dbUrl) {
        Matcher matcher = getJdbcUrlMatcher(dbUrl);
        String protocol = matcher.group(1);
        String dbType = matcher.group(2);
        String hostAndPort = matcher.group(3);
        String extraProperties = "";
        try {
            extraProperties = matcher.group(5);
        } catch (Exception e) {
            // catch and forget
        }
        return protocol + dbType + hostAndPort + "/" + extraProperties;
    }

    protected Matcher getJdbcUrlMatcher(String dbUrl) {
        Pattern pattern = Pattern.compile("(.*:)(.*://)([^/]*)(/.*)(\\?.*)");
        Matcher matcher = pattern.matcher(dbUrl);
        if (!matcher.find()) {
            pattern = Pattern.compile("(.*:)(.*://)([^/]*)(/.*)");
            matcher = pattern.matcher(dbUrl);
            matcher.find();
        }
        return matcher;
    }

    protected static String getFromEnv(String name, String defaultValue) {
        if (envOverrideForTest.containsKey(name)) {
            return envOverrideForTest.get(name);
        }
        String value = System.getenv(name);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }
}
