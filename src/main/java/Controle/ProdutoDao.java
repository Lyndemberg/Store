
package Controle;

import Conexao.ConFactory;
import Modelo.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDao {
    
    public ProdutoDao(){
        
    }
    public boolean create(Produto p) throws ClassNotFoundException, SQLException{
        Connection con = ConFactory.getConnection();
        PreparedStatement st = con.prepareStatement("INSERT INTO produto (codigo, descricao, preco) VALUES (?,?,?)");
        st.setInt(1, p.getCodigo());
        st.setString(2, p.getDescricao());
        st.setFloat(3, p.getPreco());
        boolean retorno = st.executeUpdate()>0;
        con.close();
        return retorno;
    }
    public Produto read(int codigo) throws ClassNotFoundException, SQLException{
        Connection con = ConFactory.getConnection();
        PreparedStatement st = con.prepareStatement("SELECT * FROM produto WHERE codigo=?");
        st.setInt(1, codigo);
        
        ResultSet rs = st.executeQuery();
        if(rs.next()){
            Produto r = new Produto(rs.getInt("codigo"),rs.getString("descricao"),rs.getFloat("preco"));
            con.close();
            return r;
        }else{
            con.close();
            return null;
        }
    }
    public boolean update(Produto p) throws ClassNotFoundException, SQLException{
        Connection con = ConFactory.getConnection();
        PreparedStatement st = con.prepareStatement("UPDATE produto SET descricao = ? , preco = ? WHERE codigo=?");
        st.setString(1, p.getDescricao());
        st.setFloat(2, p.getPreco());
        st.setInt(3, p.getCodigo());
        boolean retorno = st.executeUpdate()>0;
        con.close();
        return retorno;
    }
    
    public boolean delete(int codigo) throws ClassNotFoundException, SQLException{
        Connection con = ConFactory.getConnection();
        PreparedStatement st = con.prepareStatement("DELETE FROM produto WHERE codigo = ?");
        st.setInt(1, codigo);
        boolean retorno =  st.executeUpdate()>0;
        con.close();
        return retorno;
    }
    
}
