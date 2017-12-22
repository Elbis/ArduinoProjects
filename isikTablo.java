/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arduinoradar;

import java.util.Date;

/**
 *
 * @author mkuta
 */
public class isikTablo {
    private int isik;
    private Date isikTarih;

    public isikTablo(int isik, Date isikTarih) {
        this.isik = isik;
        this.isikTarih = isikTarih;
    }

    /**
     * @return the isik
     */
    public int getIsik() {
        return isik;
    }

    /**
     * @param isik the isik to set
     */
    public void setIsik(int isik) {
        this.isik = isik;
    }

    /**
     * @return the isikTarih
     */
    public Date getIsikTarih() {
        return isikTarih;
    }

    /**
     * @param isikTarih the isikTarih to set
     */
    public void setIsikTarih(Date isikTarih) {
        this.isikTarih = isikTarih;
    }
}
