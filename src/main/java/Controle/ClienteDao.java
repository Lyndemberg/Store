package Controle;

import Conexao.ConFactory;
import Modelo.Cliente;
import Modelo.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDao {
    public ClienteDao(){
        
        
    }
    public boolean create(Cliente novo) throws ClassNotFoundException, SQLException{
        Connection con = ConFactory.getConnection();
        PreparedStatement st = con.prepareStatement("INSERT INTO cliente (id, nome, email) VALUES (?,?,?)");
        st.setInt(1, novo.getId());
        st.setString(2, novo.getNome());
        st.setString(3, novo.getEmail());
        boolean retorno = st.executeUpdate()>0;
        con.close();
        
        return retorno;
    }
    public Cliente read(String email) throws ClassNotFoundException, SQLException{
        Connection con = ConFactory.getConnection();
        PreparedStatement st = con.prepareStatement("SELECT * FROM cliente WHERE email=?");
        st.setString(1, email);
        
        ResultSet rs = st.executeQuery();
        if(rs.next()){
            Cliente r = new Cliente(rs.getInt("id"),rs.getString("nome"),rs.getString("email"));
            con.close();
            return r;
        }else{
            con.close();
            return null;
        }
        
    }
    
}
