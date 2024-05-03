/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import BD.Datab;
import Utils.Blogs;
import Utils.BlogsReaders;
import Utils.SessionUtils;
import Utils.randomGen;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;

/**
 *
 * @author jeff
 */

@Named("blogsBean")
@SessionScoped
public class BlogsBean implements Serializable {
    
     private final Datab bd = new Datab();
    private List<Blogs> blogs;
    
    private randomGen rn = new randomGen();
    
    private int idblog=0;
    private String title;
    private String description;
    
    private PrimeFaces primeFaces;

    public List<Blogs> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blogs> blogs) {
        this.blogs = blogs;
    }

    public int getIdblog() {
        return idblog;
    }

    public void setIdblog(int idblog) {
        this.idblog = idblog;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public BlogsBean() throws SQLException {
        setBlogs(bd.consultarblogs());
    }
           
    public void redireccionarBlogs() {
        try {
            
            if (seleccionados()==1){
                HttpSession session = SessionUtils.getSession();
                int idreader = bd.consultaridusu(session.getAttribute("usuario").toString());
                
                
                System.out.println(idreader + "  "+ idblog);
                if (idreader==0){
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "No se pudo visualizar el blog");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } else {
                    bd.insertarLectorBlog(idreader,idblog);
                
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se visualizó el Blog "+idblog);
                FacesContext.getCurrentInstance().addMessage(null, message);
                }
                
//                FacesContext context = FacesContext.getCurrentInstance();
//                context.getExternalContext().redirect("verBlog.xhtml");
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Escoja 1 solo Blog");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
            
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    
    public void editarblog() {
        try {
            if (seleccionados()!=1){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Escoja 1 solo Blog");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                System.out.println("muestradialogo");
                primeFaces.current().executeScript("PF('wdialogoeditar').show();");
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void eliminarblog() {
        try {
            if (seleccionados()!=1){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Escoja 1 solo Blog");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                System.out.println("muestradialogo");
                primeFaces.current().executeScript("PF('wdialogoconf').show();");
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void agregar() {
         try {
            
            String rta = ".";
                rta = bd.agregarBlog(rn.generateBlogID(),title,description);
                        
            setBlogs(bd.consultarblogs());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Blog agregado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch(Exception e) {
             System.out.println(e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "No se pudo agregar el blog");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
     public void editar() {
        try {
            bd.editarblog(idblog,title,description);
            setBlogs(bd.consultarblogs());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Blog editado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch(Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "No se pudo editar el Blog");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void eliminar() {
        try {
            bd.eliminarblog(idblog);
            setBlogs(bd.consultarblogs());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Blog eliminado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch(Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "No se pudo editar el blog");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    
    private int seleccionados(){
        int s = 0;
        for (Blogs br : blogs){
            if (br.isSeleccionado()){
                s++;
                idblog = br.getId();
                description = br.getDescription();
                title = br.getTitle();
            }
        }
        return s;
    }
}
