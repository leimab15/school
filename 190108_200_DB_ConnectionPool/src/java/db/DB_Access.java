package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel
 */
public class DB_Access {
    
    private DB_StatementPool stmtPool = DB_StatementPool.getInstance();
    
    public List<String> getGenres() throws SQLException
    {
        Statement stat = stmtPool.getStatement();
        String sqlString = "Select * FROM category";
        ResultSet rs = stat.executeQuery(sqlString);
        List<String> genreNames = new LinkedList<>();
        while (rs.next())
        {
            genreNames.add(rs.getString("name"));
        }
        return genreNames;
    }
    
    public List<String> getFilmsByLength(int length) throws SQLException
    {
        PreparedStatement pStat = stmtPool.getPreparedStatement(DB_StatementType.GET_FILM_BY_LENGTH);
        pStat.setInt(1, length);
        ResultSet rs = pStat.executeQuery();
        List<String> filmNames = new LinkedList<>();
        while (rs.next())
        {
            filmNames.add(rs.getString("title"));
        }
        stmtPool.releaseStatement(pStat); //TODO - Does not work ??!
        return filmNames;
    }
    
    public static void main(String[] args) {
        try {
            DB_Access dba = new DB_Access();
            for (String name : dba.getFilmsByLength(100)) {
                System.out.println(name);
            }
            /*for (String name : dba.getGenres())
            {
                System.out.println(name);
            }*/
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.toString());
        }
    }
}
