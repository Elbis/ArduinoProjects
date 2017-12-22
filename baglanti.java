package arduinoradar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author mkuta
 */
import java.sql.Connection;
import java.sql.DriverManager;
 
/**
*
* @author ecir
*/
public class baglanti {
    protected static final String SERVER="jdbc:mysql://localhost:3306/arduino";
    protected static final String KULLANICIADI="root";
    protected static final String SIFRE="1234";
    protected Connection baglanti=null;
    
    protected void baglan(){
        try{
            Class.forName("com.mysql.jdbc.Driver");//İlgili dosyanın belleğe yüklenmesini sağlar
            baglanti=DriverManager.getConnection(SERVER,KULLANICIADI,SIFRE);//veri tabanına bağlanmayı sağlar
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    protected void baglantiKes(){
        try{
            baglanti.close();//açık olan bağlantıyı kapatır
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
