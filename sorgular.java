package arduinoradar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mkuta
 */
public class sorgular extends baglanti {
    private static ObservableList<mesafeTablo> liste;
    private static ObservableList<isikTablo> isikListe;
    public boolean kullaniciCek(String kadi, String sifre){
        boolean sonuc=FALSE;
        try{
            baglan();
            PreparedStatement ps = baglanti.prepareStatement("SELECT * FROM USER WHERE username=? AND password=?");
            ps.setString(1,kadi);
            ps.setString(2,sifre);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()){
                sonuc=FALSE;
            }
            else{
                ps.close();
                rs.close();
                sonuc= TRUE;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            baglantiKes();
        }
        return sonuc;
    }
    
    public void mesafeAl(int mesafe){
        
        try{
            baglan();
            PreparedStatement ps = baglanti.prepareStatement("INSERT INTO hcsr04 (mesafe) VALUES(?)");
            ps.setInt(1,mesafe);
            ps.execute();
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
          
        }
        finally{
            baglantiKes();
        }
       
    }
    public void isikAl(int isik){
        try{
            baglan();
            PreparedStatement ps = baglanti.prepareStatement("INSERT INTO ldr (isik) VALUES(?)");
            ps.setInt(1, isik);
            ps.execute();
        }
        catch(Exception e){
            
        }
        finally{
            baglantiKes();
        }
    }
    public ObservableList<mesafeTablo> mesafeGetir(){
        try{
            baglan();
            
            PreparedStatement ps = baglanti.prepareStatement("SELECT mesafe,zaman FROM arduino.hcsr04");
            //
            liste=FXCollections.observableArrayList();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                liste.add(new mesafeTablo(rs.getInt(1), rs.getDate(2)));
                
                
            }
             ps.close();
             rs.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            baglantiKes();
        }
        System.out.println("LİSTE YAZDIRILDI");
        System.out.println(liste);
        return liste;
    }
    public ObservableList<isikTablo> isikGetir(){
        try{
            baglan();
            PreparedStatement ps = baglanti.prepareStatement("SELECT isik, zaman FROM arduino.ldr");
            isikListe=FXCollections.observableArrayList();
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                isikListe.add(new isikTablo(rs.getInt(1), rs.getDate(2)));
            }
            ps.close();
            rs.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            baglantiKes();
        }
        return isikListe;
    }
    
    
    
}
