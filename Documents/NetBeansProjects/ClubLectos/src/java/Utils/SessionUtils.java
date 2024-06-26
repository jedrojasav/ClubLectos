package Utils;

/**
 *
 * @author jeff
 */
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils{
    
    public static HttpSession getSession(){
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }
    
    public static HttpServletRequest getRequest(){
        return (HttpServletRequest)
        FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
    
    public static String getUserName(){
        HttpSession sesion = (HttpSession)
        FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return sesion.getAttribute("usuario").toString();
    }
}


