package arduinoradar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author mkuta
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField tf_user;
    @FXML
    private PasswordField tf_pw;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void Login(ActionEvent event) {
        sorgular sor = new sorgular();
        boolean sordu=sor.kullaniciCek(tf_user.getText(),tf_pw.getText());
        if(sordu){
            try{
                Stage ne = (Stage) tf_user.getScene().getWindow();
                ne.close();
                Stage stage=new Stage();
                Parent root;
                root = FXMLLoader.load(getClass().getClassLoader().getResource("arduinoradar/ArduinoVeri.fxml"));
                stage.setScene(new Scene(root));
                stage.show();
            }
            catch(Exception e){
                System.out.println(e.toString());
                
            }
            
        }
        else{
            tf_user.setText("");
            tf_pw.setText("");
            JOptionPane.showMessageDialog(null, "Kullanıcı adınız ya da şifreniz hatalı!", "BAŞARISIZ GİRİŞ", JOptionPane.ERROR_MESSAGE);
        }
}
}