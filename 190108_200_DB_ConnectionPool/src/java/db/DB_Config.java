package db;

public interface DB_Config {
    String DB_DATABASE_NAME = "sakila";
    String DB_URL = "jdbc:postgresql://localhost:5432/";
    String DB_USERNAME= "postgres";
    String DB_PASSWD = "postgres";
    String DB_DRIVER = "org.postgresql.Driver";
}
