
package Modelo;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.bson.Document;

public class Pedido {
    private int id;
    private Cliente cliente;
    private long timestamp;
    private Carrinho carrinho;

    public Pedido(int id, Cliente cliente, Carrinho carrinho) {
        this.id = id;
        this.cliente = cliente;
        LocalDateTime agora = LocalDateTime.now();
        Timestamp stamp = Timestamp.valueOf(agora);
        this.timestamp = stamp.getTime();
        this.carrinho = carrinho;
    }
    public Pedido(){
        LocalDateTime agora = LocalDateTime.now();
        Timestamp stamp = Timestamp.valueOf(agora);
        this.timestamp = stamp.getTime();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", cliente=" + cliente + ", timestamp=" + timestamp + ", carrinho=" + carrinho + '}';
    }
    
    public Document toDocument(){
        Document doc = new Document().append("_id", id).append("cliente", cliente.toDocument()).append("timestamp", timestamp)
                .append("carrinho", carrinho.toDocument());
        return doc;
    }
    public Pedido fromDocument(Document doc){
        id = doc.getInteger("id");
        cliente = doc.get("cliente", Cliente.class);
        timestamp = doc.getLong("timestamp");
        carrinho = doc.get("carrinho", Carrinho.class);
        return this;
    }
    
}
