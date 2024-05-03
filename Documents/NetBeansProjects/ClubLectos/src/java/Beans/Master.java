/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import BD.Datab;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author jeff
 */
@Named("master")
@SessionScoped
public class Master implements Serializable {
    
    private final Datab bd = new Datab();
    
    
    public void redireccionarReaders() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect("Readers.xhtml");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public void redireccionarHome() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect("Home.xhtml");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public void redireccionarBlogs() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect("Blogs.xhtml");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public void redireccionarReadersbyBlog() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect("BlogsReaders.xhtml");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
        
}
