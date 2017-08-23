
package Modelo;

import org.bson.Document;

public class Cliente {
    private int id;
    private String nome;
    private String email;

    public Cliente(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }
    public Cliente(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nome=" + nome + ", email=" + email + '}';
    }
    public Document toDocument(){
        Document doc = new Document().append("id", id).append("nome", nome).append("email", email);
        return doc;
    }
    public Cliente fromDocument(Document doc){
        id = doc.getInteger("id");
        nome = doc.getString("nome");
        email = doc.getString("email");
        return this;
    }
}
