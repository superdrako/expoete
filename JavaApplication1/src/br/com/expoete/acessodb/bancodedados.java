package br.com.expoete.acessodb;

//Pacote em que a classe se encontra

//Imports necessários
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class bancodedados {
    //Variáveis necessárias
    Connection con;
    
    public static void main(String args[]){
        bancodedados bdd = new bancodedados();
        bdd.conectarSQL();
    }
    
    public bancodedados(){  
        conectarSQL();
    }
    
    //Conectar-se com o banco de dados
    public Connection conectarSQL(){
        try {
            //Importa Driver do JDBC
            Class.forName("com.mysql.jdbc.Driver");
            //127.0.0.1 = domínio em que será feito o acesso ao banco de dados
            //expoete = Database que será acessada
            //root = Usuário que será usado
            //O parâmetro password está vázio porque o usuário root está sem password
            //Relizar a conexão
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/expoete?user=root&password=");            
        } catch (ClassNotFoundException ex) {
            //Caso a classe não seja encontrada
            System.out.println("Não foi possível encontrar o Driver do banco de dados.");
        }catch(SQLException ex){
            //Caso não seja´possível realizar a conexão
            System.out.println("Não foi possível conectar com o banco de dados.");
        }
        return con;
    }
        
    //Desconectar-se com o banco de dados
    public void desconectarSQL(){
        try {
            //Finalizar conexão com o banco de dados
            con.close();
        } catch (SQLException ex) {
            //Caso não seja possível finalizar a conexão
            System.out.println("Não foi possível finalizar a conexão com o banco de dados.");
        }
    }
    

}
