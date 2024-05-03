package Utils;

/**
 *
 * @author jeff
 */
public class Users {
    
    private boolean seleccionado;
    private String id;
    private String name;
    private String creacion;
    private int readerid;

    public Users(String id, String name, String creacion, int readerid) {
        this.id = id;
        this.name = name;
        this.creacion = creacion;
        this.readerid = readerid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreacion() {
        return creacion;
    }

    public void setCreacion(String creacion) {
        this.creacion = creacion;
    }

    public int getReaderid() {
        return readerid;
    }

    public void setReaderid(int readerid) {
        this.readerid = readerid;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
    
    
    
}
