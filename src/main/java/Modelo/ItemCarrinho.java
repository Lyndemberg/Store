
package Modelo;

import java.util.ArrayList;
import org.bson.Document;

public class ItemCarrinho {
    private Produto produto;
    private int quantidade;

    public ItemCarrinho(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float calcularSubTotal(){
        return produto.getPreco()*quantidade;
    }
    @Override
    public String toString() {
        return "ItemCarrinho{" + "produto=" + produto + ", quantidade=" + quantidade + '}';
    }
     public Document toDocument(){
        Document doc = new Document().append("produto", produto.toDocument()).append("quantidade", quantidade);
        return doc;
    }
    public ItemCarrinho fromDocument(Document doc){
        produto = doc.get("produto", Produto.class);
        quantidade = doc.getInteger("quantidade");
        return this;
    }
    
}
