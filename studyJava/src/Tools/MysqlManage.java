package Tools;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//import com.m
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author root
 */
public class MysqlManage {

    private String dbName = "db_study";
    private String dbMotor = "mysql";
    private String dbHost = "localhost";
    
    private String user = "nonickn";
    private String pass = "0000";
    
    private boolean isConnected = false;
    
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultset = null;
    private ResultSetMetaData ressetmetadata = null;
    
    public ResultSet getResultSet(){
    	return resultset;
    }
    
    public String[][] resultSetToStrDoubleArray(){
        String[][] res = null;
        try {
            ressetmetadata = resultset.getMetaData();
            int columncount = ressetmetadata.getColumnCount();
            int rowcount = getResultSetSize();
            System.out.println("colcount: "+columncount);
            System.out.println("rowcount: "+rowcount);
            int i = 0;
            res = new String[rowcount][columncount];
            
            resultset.first();
            while(resultset.next() | resultset.isAfterLast() ){
                for( int j = 1; j <= columncount; j++){
                    res[i][j - 1] = resultset.getObject(j).toString();
//                    System.out.println(resultset.getObject(j).toString());
                }
                i++;
            }
        } catch (SQLException ex) {
//            Logger.getLogger(MysqlManage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public int getResultSetSize(){
        int size = 0;
        try{
            if (resultset != null) {
                resultset.beforeFirst();
                resultset.last();
                size = resultset.getRow();
            }
        }catch(SQLException sqlexception){
            
        }
        return size;
    }
    
    //<editor-fold defaultstate="collapsed" desc="tsy mety, jereo ny comment">
    //        // tsy mety ampiasaina am result_query ny ArrayList
//        //    tsy afaka accedena ny ligne iray ao ArrayList rah oh ka String[]
//        //    ny elt iray an_ArrayList
    
    
//    public ArrayList toStrFromResultSet_notready(){
//        ArrayList<String[]> res = null;
//        int i = 1;
//        try {
//            res = new ArrayList();
//            ressetmetadata = resultset.getMetaData();
//            int columncount = ressetmetadata.getColumnCount();
//            
//            while(resultset.next()){
//                
//                String[] strarray = new String[columncount];
//                
//                for (int j = 1; j <= columncount; j++){
//                    strarray[ j - 1 ] = resultset.getObject(j).toString();
//                }
//                res.add(strarray);
//                i++;
//            }
//        } catch (SQLException ex) {
//            //            Logger.getLogger(MysqlManage.class.getName()).log(Level.SEVERE, null, ex);
//            
//        }
//        
//        return res;
//    }
    //</editor-fold>
    
    public void cleanEnv(){
        System.out.println();
        try {
            if(resultset != null){
                resultset.close();
                System.out.println("resultset closed");
            }
            statement.close();
            connection.close();
            System.out.println("statement closed");
            System.out.println("connection to:"+dbName+" closed");
        }catch(SQLException sqlexception){
            System.out.println("could not close the connection to db(" +
            		dbName +
            		") properly");
        }
    }
    
    public void selectQueryToResultSet(
            String query)
            throws SQLException
    {
        try {            
            System.out.println("query_select: " + query);
            resultset = this.statement.executeQuery(query);
        }catch(SQLException sqlexception){
            
        }
    }
    
    public void ins_upd_del_QueryToStatement(
            String query)
    throws SQLException
    {
        try{
            System.out.println("query: "+query);
            statement.executeUpdate(query);
        }catch(SQLException sqlexception){
            
        }
    }
    
    public MysqlManage(String _dbName, 
            String _dbMotor, 
            String _dbHost,
            String _user, 
            String _pass)
            throws ClassNotFoundException, SQLException
    {
        
        dbName = _dbName;
        dbMotor = _dbMotor;
        dbHost = _dbHost;
        user = _user;
        pass = _pass;
        
        String dbUrl = "jdbc:"+dbMotor+"://"+dbHost+"/"+dbName;
//        System.out.println("dbUrl: "+dbUrl);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, user, pass);
//            System.out.println("user: "+user);
//            System.out.println("pass: "+pass);
            // Windows>Preferences>General>Appearance>Colors and Fonts 
            isConnected = true;
            
            statement = connection.createStatement();
            
            
            // eto zaraina

//            resultset = statement.executeQuery("select id_n_t, tiitle from notes_tech where id_n_t=63");
//            
//            while(resultset.next()){
//                System.out.println(resultset.getObject(2).toString());
//            }
            
            
            
//            System.out.println("here");
        }catch (ClassNotFoundException classnotfound){
            System.out.println("class not found exception");
        }catch (SQLException sqlexception){
            
        }
        
        if(isConnected){
            System.out.println("connection to:"+dbName+" : ok");
            System.out.println();
        }else{
            System.out.println("connection to:"+dbName+" : not established");
            System.out.println();
        }
    }
    
    public MysqlManage(String _dbName, String _user, String _pass)
    throws SQLException, ClassNotFoundException
    {
        this(_dbName, "mysql", "localhost", _user, _pass);
        
    }
    
    public MysqlManage () throws SQLException, ClassNotFoundException
    {
        this("db_study", "nonickn", "0000");
    }
    
}
