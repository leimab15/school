package db;

import beans.Actor;
import beans.Film;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        stmtPool.releaseStatement(pStat);
        return filmNames;
    }
    
    public ArrayList<Film> getFilms() throws SQLException
    {
        PreparedStatement pStat = stmtPool.getPreparedStatement(DB_StatementType.GET_ALL_FILMS);
        ResultSet rs = pStat.executeQuery();
        ArrayList<Film> filmList = new ArrayList();
        while (rs.next())
        {   
            //Array actorArray = rs.getArray("actors");
            //String[] actorsStrings = (String[])actorArray.getArray();
            String[] actorArray = rs.getString("actors").split(",");
            ArrayList<Actor> actors = new ArrayList();
            for (String actorString : actorArray) {
                actorString=actorString.replaceAll("([^_])([A-Z])", "$1 $2").trim();
                String fistname = actorString.split(" ")[0];
                String lastname = actorString.split(" ")[1];
                actors.add(new Actor(fistname, lastname));
            }
            Film f = new Film(rs.getInt("fid"),rs.getString("title"),rs.getString("description"),rs.getString("category"),rs.getDouble("price"),rs.getInt("length"),rs.getString("rating"),actors);
            filmList.add(f);
        }
        return filmList;
    }
}
