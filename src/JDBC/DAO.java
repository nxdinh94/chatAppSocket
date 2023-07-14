
package JDBC;
import Encrypt_Decrypt.AES;
import Model.Model_user;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {
    private static String DB_URL = "jdbc:mysql://localhost:3306/cuoiki2java";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";
    static Connection conn;
    public DAO(){
        conn = getConnection(DB_URL, USER_NAME, PASSWORD);
        if(conn != null){
//            System.out.println("true");
        }else System.out.println("False");
    }
    
    public static Connection getConnection(String dbURL, String userName, 
            String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
//            System.out.println("connect successfully!");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
    
    public ArrayList<Model_user> getALL(){
        ArrayList<Model_user> list = new ArrayList<>();
        String sql = "SELECT user_account.UserName, user_account.Gender, user_account.Yob FROM user \n" +
                    ", user_account WHERE user.UserName = user_account.UserName;";
        try{
            String name;
            String gender;
            String yob;
            
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                name = rs.getString("UserName"); 
                gender = rs.getString("Gender");
                yob = rs.getString("Yob");
                
                list.add(new Model_user(name, gender, yob));
            }
            }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public void register(String userName, String password, String yob, String gender){
        String sql  = "insert into user value(?,?)";
        String sql2  ="insert into user_account values (?,?,?)";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps.setString(1, userName);
            ps.setString(2, password);
            ps2.setString(1, userName);
            ps2.setString(2, yob);
            ps2.setString(3, gender);
            
            ps.executeUpdate();
            ps2.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public String getUserName(String userName){
        String  sql = "select username from user where username = ?";
        String  username;
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, AES.encrypt(userName, "123"));
            
            ResultSet         rs = ps.executeQuery();
            while(rs.next()){
                username         = rs.getString("username");
                if(username != null){
                    return AES.decrypt(username, "123");
                }
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public String getPassword(String userName){
        String sql = "select password from user where username = ?";
        String password;
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, AES.encrypt(userName, "123"));
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                password = rs.getString("password");
                if(password != null){
                    return AES.decrypt(password, "123");
                }
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public String getYob(String userName){
        String sql = "select yob from user where username = ?";
        String yob;
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, AES.encrypt(userName, "123"));
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                yob = rs.getString("yob");
                if(yob != null){
                    return AES.decrypt(yob, "123");
                }
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public String getGender(String userName){
        String sql = "select yob from user where gender = ?";
        String gender;
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, AES.encrypt(userName, "123"));
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                gender = rs.getString("gender");
                if(gender != null){
                    return AES.decrypt(gender, "123");
                }
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public void clearALL(){
        String sql = "delete from user";
        try{
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public ArrayList<String> listWord (){
        ArrayList<String> list = new ArrayList<>();
        String sql = "Select * from word";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String w = rs.getString("w");
                list.add(w);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public void insertWord(String w){
        String sql = "insert into word(w) values(?)";
        try {
            PreparedStatement ps  = conn.prepareStatement(sql);
            ps.setString(1, w);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void deleteWordG(){
        String sql = "delete from word";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
   
    
}
