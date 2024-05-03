package Beans;

import BD.Datab;
import Utils.Crypt;
import Utils.SessionUtils;
import Utils.Users;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;
import javax.inject.Named;
import Utils.randomGen;

/**
 *
 * @author jeff
 */
@Named("readBean")
@SessionScoped
public class ReadersBean implements Serializable{
    
    private final Datab bd = new Datab();
    private List<Users> usuarios;
    private randomGen rn = new randomGen();
    private Crypt cryptor = new Crypt();
    
    private String idselecc;
    private String nombre;
    private int idread;
    private PrimeFaces primeFaces;
    private String pssw;

    public String getIdselecc() {
        return idselecc;
    }

    public void setIdselecc(String idselecc) {
        this.idselecc = idselecc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdread() {
        return idread;
    }

    public void setIdread(int idread) {
        this.idread = idread;
    }
    

    public List<Users> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Users> usuarios) {
        this.usuarios = usuarios;
    }

    public ReadersBean(List<Users> usuarios) {
        this.usuarios = usuarios;
    }

    public ReadersBean() throws SQLException {
        setUsuarios(bd.consultarUsuarios());
        System.out.println("consulta usuarios");
    }

    public String getPssw() {
        return pssw;
    }

    public void setPssw(String pssw) {
        this.pssw = pssw;
    }
    
    
    public void agregarUsu() {
        System.out.println("agregar usuario");
        try {
            if (seleccionados()!=1){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Escoja 1 solo usuario");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                System.out.println("muestradialogo");
                 primeFaces.current().executeScript("PF('wdialogoagregar').show();");
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void eliminarUsu() {
         try {
            if (seleccionados()!=1){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Escoja 1 solo usuario");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                System.out.println("muestradialogo");
                System.out.println(nombre);
                 primeFaces.current().executeScript("PF('wdialogoconf').show();");
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void editarUsu() {
         try {
            if (seleccionados()!=1){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Escoja 1 solo usuario");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                System.out.println("muestradialogo");
                 primeFaces.current().executeScript("PF('wdialogoeditar').show();");
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    
    
    public void agregar() {
        try {
            
            String pss = cryptor.encriptar(pssw);
            String rta = ".";
            while (rta != "ok"){
                rta = bd.agregarUsuario(idselecc,pss,nombre,rn.generateReaderID());
            }
            setUsuarios(bd.consultarUsuarios());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Usuario agregado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "No se pudo agregar el usuario");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void editar() {
        try {
            String pss;
            if (pssw==null || pssw.length()<2){
                pss = "";
            } else {
                pss = cryptor.encriptar(pssw);
            }
            bd.editarUsuario(idselecc,pss,nombre);
            setUsuarios(bd.consultarUsuarios());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Usuario editado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch(Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "No se pudo editar el usuario");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void eliminar() {
        try {
            bd.eliminarUsuario(idselecc);
            setUsuarios(bd.consultarUsuarios());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Usuario editado");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch(Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "No se pudo editar el usuario");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    private int seleccionados(){
        int s = 0;
        for (Users us : usuarios){
            if (us.isSeleccionado()){
                s++;
                nombre = us.getName();
                pssw = null;
                idselecc = us.getId();
            }
        }
        return s;
    }
    
    
}
