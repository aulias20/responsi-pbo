import javax.swing.*;
import java.sql.*;
import java.util.*;

public class Model {
    //koneksi ke db
    static final String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    static final String db_url = "jdbc:mysql://localhost/responsi?serverTimezone=" + TimeZone.getDefault().getID();
    // ---> somehow harus pake serverTimeZone
    static final String user = "root";
    static final String pass = "";
    String sta=null;

    Connection koneksi;
    Statement stmt;

    public Model(){
        try{
            Class.forName(jdbc_driver);
            koneksi = (Connection)DriverManager.getConnection(db_url,user,pass);
            System.out.println("Koneksi Berhasil!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Koneksi Gagal!");
        }
    }
    public String[][] readKereta(String sta) {
        try {
            int jmlData1 = 0; //utk nampung jumlah data
            String data1[][] = new String[getBanyakData1()][4]; // baris blm diketahui, kolom 4
            String query = "SELECT * FROM kereta WHERE staTujuan='" + sta + "'";
            ResultSet res = stmt.executeQuery(query); // menampilkan hasil execute query dengan ResultSet (karena >1)
            while (res.next()) {
                data1[jmlData1][0] = res.getString("idKrt");
                data1[jmlData1][1] = res.getString("namaKrt");
                data1[jmlData1][2] = res.getString("staTujuan");
                data1[jmlData1][3] = res.getString("kelasKrt");
                jmlData1++;
            }
            return data1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error!");
            return null;
        }
    }

    public String[][] readTiket(){
        try{
            int jmlData2 = 0; //utk nampung jumlah data
            String data2[][] = new String[getBanyakData2()][4]; // baris blm diketahui, kolom 4
            String query = "SELECT * FROM tiket";
            ResultSet res = stmt.executeQuery(query); // menampilkan hasil execute query dengan ResultSet (karena >1)
            while(res.next()){
                data2[jmlData2][0] = res.getString("idCust");
                data2[jmlData2][1] = res.getString("namaCust");
                data2[jmlData2][2] = res.getString("jenisKel");
                data2[jmlData2][3] = res.getString("idKrt");
                jmlData2++;
            }
            return data2;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error!");
            return null;
        }
    }

    public int getBanyakData1() {
        int jmlData1 = 0;
        try{
            stmt = koneksi.createStatement();
            String query = "SELECT * FROM kereta";
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                jmlData1++;
            }
            return jmlData1;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error!");
            return 0;
        }
    }
    public int getBanyakData2() {
        int jmlData2 = 0;
        try{
            stmt = koneksi.createStatement();
            String query = "SELECT * FROM tiket";
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                jmlData2++;
            }
            return jmlData2;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error!");
            return 0;
        }
    }

    public void insertTiket(String nama, String jk, String id){
        try{
            String query = "INSERT INTO tiket(namaCust,jenisKel,idKrt) VALUES ('"+nama+"', '"+jk+"', '"+id+"');";
            //String '"+String+"' kalau Int "+int+"
            stmt = (Statement)koneksi.createStatement(); // prepare statementnya
            stmt.executeUpdate(query); // execute querynya
            System.out.println("Berhasil ditambahkan!");
            JOptionPane.showMessageDialog(null,"Berhasil ditambahkan!");
        }catch (Exception sql){
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }

    public void updateTiket(String nama, String jk, String id) {
        try{
            stmt = (Statement) koneksi.createStatement(); // prepare statementnya
            String query1 = "UPDATE tiket SET namaCust='"+nama+"',jenisKel='"+jk+"',idKrt='"+id+"' WHERE namaCust='" +nama+ "';";
            stmt.executeUpdate(query1); // execute querynya
            System.out.println("Berhasil diupdate!");
            JOptionPane.showMessageDialog(null,"Berhasil diupdate!");
        }catch (Exception sql){
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }
    public void deleteTiket(String nama){
        try{
            stmt = (Statement) koneksi.createStatement(); // prepare statementnya
            String query1 = "DELETE FROM tiket WHERE namaCust='"+nama+"';";
            stmt.executeUpdate(query1); // execute querynya
            System.out.println("Berhasil dihapus!");
            JOptionPane.showMessageDialog(null,"Berhasil dihapus!");
        }catch (Exception sql){
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }

}
