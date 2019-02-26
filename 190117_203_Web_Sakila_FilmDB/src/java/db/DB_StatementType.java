package db;

/**
 *
 * @author Manuel
 */
public enum DB_StatementType {
    
    
    GET_ALL_FILMS("SELECT * FROM nicer_but_slower_film_list"),
    GET_FILM_BY_LENGTH("SELECT * FROM film f WHERE f.length < ?");

    private DB_StatementType(String sqlString) {
        this.sqlString = sqlString;
    }
    
    private String sqlString;

    public String getSqlString() {
        return sqlString;
    }
}
