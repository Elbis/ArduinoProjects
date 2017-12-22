/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arduinoradar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author mkuta
 */


public class mesafeTablo {
    private int mesafe;
    private Date mesafeTarih;
    
    
    
    
    public mesafeTablo(int mesafe, Date mesafeTarih){
    this.mesafe=mesafe;
    this.mesafeTarih=mesafeTarih;

}

    /**
     * @return the mesafe
     */
    public int getMesafe() {
        return mesafe;
    }

    /**
     * @param mesafe the mesafe to set
     */
    public void setMesafe(int mesafe) {
        this.mesafe = mesafe;
    }

    /**
     * @return the mesafeTarih
     */
    public Date getMesafeTarih() {
        return mesafeTarih;
    }

    /**
     * @param mesafeTarih the mesafeTarih to set
     */
    public void setMesafeTarih(Date mesafeTarih) {
        this.mesafeTarih = mesafeTarih;
    }

  
}