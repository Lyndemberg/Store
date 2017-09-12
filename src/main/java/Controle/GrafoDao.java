
package Controle;

import Conexao.ConFactory;
import Modelo.Cliente;
import Modelo.ItemCarrinho;
import Modelo.Pedido;
import Modelo.Produto;
import java.util.ArrayList;
import java.util.List;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Values;

public class GrafoDao {
    
    public GrafoDao(){
        
    }
    public void registraPedido(Pedido p){
        Driver driver = ConFactory.getConnectionNeo4j();
        Session sessao = driver.session();
        Cliente c = p.getCliente();
        
        StatementResult res = sessao.run("MATCH (c:Cliente{email:$email}) RETURN c", Values.parameters("email",c.getEmail()));
        if(res.hasNext()){
            List<ItemCarrinho> itens = p.getCarrinho().getItens();
            for(int i=0; i<itens.size(); i++){
                Produto prod = itens.get(i).getProduto();
                StatementResult consulta = sessao.run
                ("MATCH (p:Produto{codigo:$codigo, descricao:$descricao, preco:$preco})"
                        + " RETURN p", Values.parameters("codigo",prod.getCodigo(),"descricao", prod.getDescricao(), "preco",prod.getPreco()));
                if(consulta.hasNext()){
                    sessao.run("MATCH (c:Cliente{email:$email}), (p:Produto{codigo:$codigo})"
                    + "CREATE (c)-[:COMPROU]->(p)", Values.parameters("email",c.getEmail(), "codigo", prod.getCodigo()));
                }else{
                    sessao.run("CREATE (:Produto{codigo:$codigo, descricao:$descricao, preco:$preco})",
                            Values.parameters("codigo",prod.getCodigo(),"descricao",prod.getDescricao(),"preco", prod.getPreco()));
                    sessao.run("MATCH (c:Cliente{email:$email}), (p:Produto{codigo:$codigo})"
                    + "CREATE (c)-[:COMPROU]->(p)", Values.parameters("email",c.getEmail(), "codigo", prod.getCodigo()));
                }
            }
        }else{
            sessao.run("CREATE (:Cliente{id:$id, nome:$nome, email:$email})",
            Values.parameters("id",c.getId(),"nome",c.getNome(),"email",c.getEmail()));
            
            List<ItemCarrinho> itens = p.getCarrinho().getItens();
            for(int i=0; i<itens.size(); i++){
                Produto prod = itens.get(i).getProduto();
                StatementResult consulta = sessao.run
                ("MATCH (p:Produto{codigo:$codigo, descricao:$descricao, preco:$preco})"
                        + " RETURN p", Values.parameters("codigo",prod.getCodigo(),"descricao", prod.getDescricao(), "preco",prod.getPreco()));
                if(consulta.hasNext()){
                    sessao.run("MATCH (c:Cliente{email:$email}), (p:Produto{codigo:$codigo})"
                    + "CREATE (c)-[:COMPROU]->(p)", Values.parameters("email",c.getEmail(), "codigo", prod.getCodigo()));
                }else{
                    sessao.run("CREATE (:Produto{codigo:$codigo, descricao:$descricao, preco:$preco})",
                            Values.parameters("codigo",prod.getCodigo(),"descricao",prod.getDescricao(),"preco", prod.getPreco()));
                    sessao.run("MATCH (c:Cliente{email:$email}), (p:Produto{codigo:$codigo})"
                    + "CREATE (c)-[:COMPROU]->(p)", Values.parameters("email",c.getEmail(), "codigo", prod.getCodigo()));
                }
            }
        }

        sessao.close();
    }
    
    public ArrayList<String> recomendacoes(Produto x){
        Driver driver = ConFactory.getConnectionNeo4j();
        Session sessao = driver.session();
        ArrayList<String> recomendacoes = new ArrayList<>();
        StatementResult busca = sessao.run("MATCH (c:Cliente)-[:COMPROU]->(pro:Produto{descricao: $descricao}), "
                + "(c)-[:COMPROU]->(p:Produto) WHERE p.descricao <> pro.descricao RETURN DISTINCT p.descricao as nome",
                Values.parameters("descricao",x.getDescricao()));
        
        while(busca.hasNext()){
            recomendacoes.add(busca.next().get("nome").toString());
        }
        
        sessao.close();
        
        return recomendacoes;
    }
    
}
