
package Modelo;

import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class Carrinho {
    private String sessionId;
    private List<ItemCarrinho> itens;

    public Carrinho(String sessionId) {
        this.sessionId = sessionId;
        itens = new ArrayList<>();
    }

    public boolean addItem(ItemCarrinho novo){
        return itens.add(novo);
    }
    
    public float calcularTotal(){
        float total=0;
        for (int i=0; i<itens.size(); i++){
            total = total + itens.get(i).calcularSubTotal();
        }
        return total;
    }
    
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinho> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Carrinho{" + "sessionId=" + sessionId + ", itens=" + itens + '}';
    }
    
    public Document toDocument(){
        ArrayList<Document> lista = new ArrayList<>();
        for(int i=0; i<itens.size(); i++){
            lista.add(itens.get(i).toDocument());
        }
       
        Document doc = new Document().append("sessionId", sessionId).append("itens", lista); 
        return doc;
    }
    public Carrinho fromDocument(Document doc){
        sessionId = doc.getString("id");
        itens = doc.get("itens", ArrayList.class);
        return this;
    }
    
}
