package Conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;

public class ConFactory {

    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("org.postgresql.Driver");
        
        String url = "jdbc:postgresql://localhost:5432/Supermercado";
        String usuario = "postgres";
        String senha = "pgadmin";
        
        return DriverManager.getConnection(url,usuario,senha);
    }
    public static Driver getConnectionNeo4j(){
        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "123"));
        return driver;
    }
}
