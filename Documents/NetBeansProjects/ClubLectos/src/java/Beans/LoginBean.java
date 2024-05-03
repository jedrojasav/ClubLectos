package Beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import BD.Datab;
import Utils.Crypt;
import Utils.SessionUtils;
import java.sql.SQLException;
import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jeff
 */
@Named("loginBean")
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private final Datab bd = new Datab();
    private Crypt cryptor = new Crypt();

    private String usuario;
    private String password;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void validarCredenciales() throws SQLException {
        try {
            String pss = cryptor.encriptar(getPassword());
            int r = bd.validarIngreso(getUsuario(), pss);
            if (r == 0) {
                System.out.println("inval");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "¡Contraseña incorrecta!.");
                FacesContext.getCurrentInstance().addMessage(null, message);

            } else if (r == 99) {
                System.out.println("no exis");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Usuario no existe en BD.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else if (r == 1) {
                
                HttpSession session = SessionUtils.getSession();
                session.setAttribute("usuario", getUsuario());


                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().redirect("faces/Home.xhtml");
                System.out.println("OK");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    
    public void salir() {
        try {
            HttpSession session = SessionUtils.getSession();
            session.invalidate();
            
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
