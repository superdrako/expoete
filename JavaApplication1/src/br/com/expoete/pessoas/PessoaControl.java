package br.com.expoete.pessoas;

import br.com.expoete.acessodb.bancodedados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PessoaControl {
    
    PreparedStatement pstm;
    ResultSet rs;
    String consultaCliente = "SELECT * FROM pessoa WHERE nome LIKE ?";
    String criarCliente = "INSERT INTO pessoa (nome, endereco, telefone, sexo, peso, altura) VALUES " + 
            "(?,?,?,?,?,?)";
    String buscaUltimoCodigo = "SELECT MAX(id) AS id FROM pessoa";
    bancodedados bd = new bancodedados();
    
    public void PessoaControl(){
        
    }
    
    public int returnLastId(){
        
        try{
        pstm = bd.conectarSQL().prepareStatement(buscaUltimoCodigo);
        rs = pstm.executeQuery();
            
            if(rs.last() ){
                return rs.getInt("id");
            }
                bd.desconectarSQL();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }
    
    public void cadastrarPessoa(PessoaBean pessoa) throws SQLException{
        
        int id = returnLastId() + 1;
            
            try{
        pstm = bd.conectarSQL().prepareStatement(criarCliente);
                
           // pstm.setInt(1, pessoa.getId());
            pstm.setString(1, pessoa.getNome());
            pstm.setString(2, pessoa.getEndereco());
            pstm.setString(3, pessoa.getTelefone());
            pstm.setString(4, pessoa.getSexo());
            pstm.setString(5, pessoa.getPeso());
            pstm.setString(6, pessoa.getAltura());
            pstm.executeUpdate();
            bd.desconectarSQL();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
    }
    
    /*
    public List<PessoaBean> listarPessoas(String nome){
     List<PessoaBean> pessoas = null;
        try {
            bancodedados mysql = new bancodedados();
            pstm = mysql.conectarSQL().prepareStatement(criarCliente);
            pstm.setString(1, nome);
            rs = pstm.executeQuery();
            PessoaBean pess;
            while(rs.next()){
                pess = new PessoaBean();
                pess.setId(rs.getInt("id"));
                pess.setNome(rs.getString("nome"));
                pess.setEndereco(rs.getString("endereco"));
                pess.setTelefone(rs.getString("telefone"));
                pess.setSexo(rs.getString("sexo"));
                pess.setAltura(rs.getFloat("altura"));
                pess.setPeso(rs.getFloat("peso"));
                pessoas.add(pess);
            }
        } catch (Exception e) {
        }
        
     return pessoas;
    }
*/
}
