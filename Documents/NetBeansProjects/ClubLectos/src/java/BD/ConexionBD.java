package BD;

/**
 *
 * @author jeff
 */
import java.io.InputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;

public final class ConexionBD {
    
    static InputStream inputStream;
    static Connection conn;
    
    public static void connect() throws Exception{
        
        inputStream = Class.forName("BD.ConexionBD").getClassLoader().getResourceAsStream("config.properties");      
        Properties prop = new Properties();     
        prop.load(inputStream);
         
        Class.forName(prop.getProperty("db.driver"));
       conn = DriverManager.getConnection(prop.getProperty("db.url"),
                                          prop.getProperty("db.user"),
                                          prop.getProperty("db.password"));
    }
}

