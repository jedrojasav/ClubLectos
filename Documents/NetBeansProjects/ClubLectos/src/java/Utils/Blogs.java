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
public class Blogs {
    
    private boolean Seleccionado;
    private int id;
    private String title;
    private String description;

    public boolean isSeleccionado() {
        return Seleccionado;
    }

    public void setSeleccionado(boolean Seleccionado) {
        this.Seleccionado = Seleccionado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Blogs(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
    
    
    
}
