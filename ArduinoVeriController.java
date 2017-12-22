
package arduinoradar;

import com.fazecast.jSerialComm.SerialPort;
import static com.fazecast.jSerialComm.SerialPort.NO_PARITY;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.io.BufferedReader;
import java.util.Date;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import static java.util.Calendar.DATE;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static javax.xml.datatype.DatatypeConstants.DATETIME;



public class ArduinoVeriController implements Initializable {
    private BufferedReader input;
    private static OutputStream output;
    String inputline;
    private static int TIME_OUT = 2000;
    @FXML
    private ComboBox<?> cb_port;
    @FXML
    private Label lb_connect;
    @FXML
    private Font x1;
    private static SerialPort port;
    @FXML
    private Label label_mesafeAl;
    @FXML
    private Font x2;
    @FXML
    private Label label_isikAl;
    @FXML
    private TableColumn<?,?> tc_mesafe;
    @FXML
    private TableColumn<?, ?> tc_isik;
    @FXML
    private TableColumn<?, ?> tc_isiktarih;
    @FXML
    private TableView<mesafeTablo> tablo_mesafe;
    private ObservableList<mesafeTablo> mesafeVeri;
    private ObservableList<isikTablo> isikVeri;
    @FXML
    private TableColumn<?, ?> mesafeZaman;
    @FXML
    private TableView<isikTablo> tablo_isik;
    @FXML
    private ToggleButton tb_baglanti;
    @FXML
    private ToggleButton tb_baglanmadi;
    @FXML
    private Font x3;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList data = FXCollections.observableArrayList();
        mesafeVeri=FXCollections.observableArrayList();
        toggleClose();
        // TODO
        SerialPort ports[]=SerialPort.getCommPorts();
        for (SerialPort port: ports){
             data.add(port);
        }
        cb_port.setItems(data);
        
        
        
    }    

    @FXML
    private void connectPort(ActionEvent event) {
        
        port = (SerialPort) cb_port.getValue();
       
        if (port.openPort()){
            toggleOpen();
            tb_baglanti.setSelected(true);
            port.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 150, 0);
            
            port.setComPortParameters(9600, 8, 1, NO_PARITY);
            
            
            
        }
        else{
            lb_connect.setText("Başarısız Bağlantı");
        }
        try{
            input = new BufferedReader(new InputStreamReader(port.getInputStream()));
            output = port.getOutputStream();
            
            
            port.addDataListener(new SerialPortDataListener() {
                @Override
                public int getListeningEvents() {
                    return port.LISTENING_EVENT_DATA_AVAILABLE;
                }

                @Override
                public void serialEvent(SerialPortEvent spe) {
                    if (spe.getEventType() == SerialPort.LISTENING_EVENT_DATA_AVAILABLE){
                       try{
                           if(input.ready()){
                               output.write(1);
                               inputline=input.readLine();
                               /*Date tarih = new Date();
                               System.out.println("Veri: "+inputline);
                               System.out.println(tarih);
                               System.out.println(Integer.parseInt(inputline));
                               */Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (Integer.parseInt(inputline)>100&&Integer.parseInt(inputline)<700){
                                            label_isikAl.setText(inputline);
                                        }
                                           
                                        if (Integer.parseInt(inputline)<=100){
                                            label_mesafeAl.setText(inputline);
                                        }
                                            }
});
                              
                           }
                           
                           
                       }
                       catch(Exception e){
                           System.out.println(e.getMessage());
                       }
                        
                    }
                    
                }
            });
            
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
         
    }
    public synchronized void close() {
        if (port != null) {
            port.removeDataListener();
            port.closePort();
        }
    }

    private void toggleOpen(){
        tb_baglanmadi.setVisible(false);
        
        tb_baglanti.setVisible(true);
        
        
    }
    private void toggleClose(){
        tb_baglanti.setVisible(false);
        
        tb_baglanmadi.setVisible(true);
    }
    
    @FXML
    private void btn_mesafe(ActionEvent event) {
        sorgular sordu=new sorgular();
        sordu.mesafeAl(Integer.parseInt(label_mesafeAl.getText()));
        
    }

    @FXML
    private void btn_isik(ActionEvent event) {
        sorgular sordu=new sorgular();
        sordu.isikAl(Integer.parseInt(label_isikAl.getText()));
    }

    @FXML
    private void btn_yenile(ActionEvent event) {
        tc_mesafe.setCellValueFactory(new PropertyValueFactory<>("mesafe"));
        mesafeZaman.setCellValueFactory(new PropertyValueFactory<>("mesafeTarih"));
        sorgular sor = new sorgular();
        mesafeVeri=sor.mesafeGetir();
        
        
        tablo_mesafe.setItems(mesafeVeri);
        
        
    }

    @FXML
    private void btn_isikYenile(ActionEvent event) {
        
        tc_isik.setCellValueFactory(new PropertyValueFactory<>("isik"));
        tc_isiktarih.setCellValueFactory(new PropertyValueFactory<>("isikTarih"));
        sorgular sor = new sorgular();
        isikVeri=sor.isikGetir();
        tablo_isik.setItems(isikVeri);
    }

    

    
    
    }
    
    
