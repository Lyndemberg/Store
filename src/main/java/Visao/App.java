
package Visao;

import Controle.ClienteDao;
import Controle.ProdutoDao;
import Modelo.Carrinho;
import Modelo.Cliente;
import Modelo.ItemCarrinho;
import Modelo.Pedido;
import Modelo.Produto;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import redis.clients.jedis.Jedis;

public class App {
    public static void main(String[] args) {
        
        try {
            ProdutoDao daoProduto = new ProdutoDao();    
            ClienteDao daoCliente = new ClienteDao();
            
            Gson gson = new Gson();
            Jedis jedis = new Jedis("127.0.0.1", 6379);
            
            //BUSCANDO PRODUTOS NO BANCO
            Produto p1 = daoProduto.read(1);
            Produto p2 = daoProduto.read(2);
            Produto p3 = daoProduto.read(3);
            
            Carrinho c = new Carrinho("c2");
            ItemCarrinho item1 = new ItemCarrinho(p1,4);
            ItemCarrinho item2 = new ItemCarrinho(p2,2);
            ItemCarrinho item3 = new ItemCarrinho(p3,7);
            
            c.addItem(item1);
            c.addItem(item2);
            c.addItem(item3);
            
            String jsonCarrinho = gson.toJson(c);
            jedis.setex(c.getSessionId(),1000,jsonCarrinho);
            
            
            MongoClient cliente = new MongoClient("localhost", 27017);
        
            MongoDatabase database = cliente.getDatabase("supermercado");
        
            MongoCollection<Document> colecao = database.getCollection("Pedido");
            
            Cliente cli = daoCliente.read("lyndemberg@gmail.com");
            Pedido ped = new Pedido(2,cli,c);
            colecao.insertOne(ped.toDocument());
            
            
            
            cliente.close();
            
            
            //BUSCANDO NO REDIS E EXIBINDO NA APLICAÇÃO
            //Carrinho cc = gson.fromJson(jedis.get("c2"), Carrinho.class);
            //System.out.println(cc);
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
}
