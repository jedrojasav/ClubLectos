/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Utils.BlogsReaders;
import BD.Datab;
import Utils.Users;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author jeff
 */

@Named("rdblBean")
@SessionScoped
public class BlogReadersBean implements Serializable {
    private final Datab bd = new Datab();
    private List<BlogsReaders> blogsreaders;
    
    private int idblog;
    private int idread;
    private PrimeFaces primeFaces;

    public List<BlogsReaders> getUsuarios() {
        return blogsreaders;
    }

    public void setblogsreaders(List<BlogsReaders> blogsreaders) {
        this.blogsreaders = blogsreaders;
    }

    public int getIdblog() {
        return idblog;
    }

    public void setIdblog(int idblog) {
        this.idblog = idblog;
    }

    public int getIdread() {
        return idread;
    }

    public void setIdread(int idread) {
        this.idread = idread;
    }

    public BlogReadersBean() throws SQLException {
        setblogsreaders(bd.consultarblreaders());
    }

    public List<BlogsReaders> getBlogsreaders() {
        return blogsreaders;
    }

    public void setBlogsreaders(List<BlogsReaders> blogsreaders) {
        this.blogsreaders = blogsreaders;
    }
    
    
    public void agregareditarrdbl() {
        try {
            if (seleccionados()<1){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Escoja 1 solo Registro");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                System.out.println("muestradialogo");
                 primeFaces.current().executeScript("PF('wdialogoagregar').show();");
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void eliminarrdbl() {
         try {
            if (seleccionados()!=1){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Escoja 1 solo Registro");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                 primeFaces.current().executeScript("PF('wdialogoconf').show();");
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    
    public void agregareditar() {
        try {
            bd.agregareditarblrd(idread,idblog);
            
            setblogsreaders(bd.consultarblreaders());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Registro editado o agregado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch(Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "No se pudo agregar el registro");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void eliminar() {
        try {
            bd.eliminarblrd(idread,idblog);
            setblogsreaders(bd.consultarblreaders());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Registro Eliminado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch(Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "No se pudo eliminar el registros");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    private int seleccionados(){
        int s = 0;
        for (BlogsReaders br : blogsreaders){
            if (br.isSelecc()){
                s++;
                idblog = br.getIdblog();
                idread = br.getIdreader();
            }
        }
        return s;
    }
    
}
