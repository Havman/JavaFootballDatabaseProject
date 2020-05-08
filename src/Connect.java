import java.sql.Connection;
import java.sql.DriverManager;

/** Klasa realizująca połączenie z bazą danych postgres */
public class Connect{
    /** statyczna metoda realizująca połączenie z bazą danych postgres */
    public static Connection getConnect(){
        Connection c = null;
        try{
            String dbaseURL = "jdbc:postgresql://127.0.0.1:5432/postgres";
            String username  = "postgres";
            String password  = "postgres"; 
            c = DriverManager.getConnection (dbaseURL, username, password);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return c;
    }
}