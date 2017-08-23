
package Modelo;

import java.util.ArrayList;
import org.bson.Document;

public class Produto {
    private int codigo;
    private String descricao;
    private float preco;

    public Produto(int codigo, String descricao, float preco) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" + "codigo=" + codigo + ", descricao=" + descricao + ", preco=" + preco + '}';
    }
     public Document toDocument(){
        Document doc = new Document().append("codigo", codigo).append("descricao", descricao).append("preco", preco);
        return doc;
    }
    public Produto fromDocument(Document doc){
        codigo = doc.getInteger("codigo");
        descricao = doc.getString("descricao");
        preco = doc.getDouble("preco").floatValue();
        return this;
    }
    
}
