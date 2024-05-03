/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author jeff
 */
public class BlogsReaders {
    
    private boolean selecc;
    private int idblog;
    private int idreader;
    private String fecha;

    public BlogsReaders(int idblog, int idreader,String Fecha) {
        this.idblog = idblog;
        this.idreader = idreader;
        this.fecha = Fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    public int getIdblog() {
        return idblog;
    }

    public void setIdblog(int idblog) {
        this.idblog = idblog;
    }

    public int getIdreader() {
        return idreader;
    }

    public void setIdreader(int idreader) {
        this.idreader = idreader;
    }

    public boolean isSelecc() {
        return selecc;
    }

    public void setSelecc(boolean selecc) {
        this.selecc = selecc;
    }
    
    
    
}
