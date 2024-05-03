
package BD;

import Utils.Blogs;
import Utils.BlogsReaders;
import Utils.Users;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author jeff
 */
public class Datab {
    
   
     public int validarIngreso(String usuario, String clave) throws SQLException {

        int reslt = 404;
        try {
            ConexionBD.connect();
            CallableStatement cst = ConexionBD.conn.prepareCall("{ CALL VALIDAR_LOGIN(?,?,?) }");
            cst.setString("usu_id", usuario);
            cst.setString("pssw", clave);
            cst.registerOutParameter("Resultado", java.sql.Types.INTEGER);
            cst.execute();
            reslt = cst.getInt("Resultado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionBD.conn.close();
        }
         System.out.println(reslt);
        return reslt;
    }
    
     public List consultarUsuarios() throws SQLException {

        List usuarios = new ArrayList<>();
        try {
            ConexionBD.connect();
            System.out.println("Conecta");
            Statement st = ConexionBD.conn.createStatement();
            ResultSet rs = st.executeQuery("select id,name,creacion,readerid from usuarios");
            
            while (rs.next()){
                Users usu = new Users(rs.getString("id"),rs.getString("name"),rs.getString("creacion"),rs.getInt("readerid"));
                usuarios.add(usu);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionBD.conn.close();
        }
        return usuarios;
    }
     
     public void eliminarUsuario(String id) throws SQLException {

        try {
            ConexionBD.connect();
            System.out.println("Conecta");
            Statement st = ConexionBD.conn.createStatement();
            st.executeUpdate("delete from usuarios where id ='"+id+"'");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionBD.conn.close();
        }
    }
     
     public String agregarUsuario(String id,String pss, String nombre,int reader) throws SQLException {
        String rta = "ok";
        try {
            ConexionBD.connect();
            System.out.println("Conecta");
            Statement st = ConexionBD.conn.createStatement();
            
            st.executeUpdate("insert into readers select "+reader+",'"+id+"'");
            
            st.executeUpdate("insert into usuarios(id,pass,name,readerid) \n"
                    + "select '"+id+"','"+pss+"','"+nombre+"',"+reader);
            
        } catch (Exception e) {
            rta = e.getMessage();
        } finally {
            ConexionBD.conn.close();
        }
        return rta;
    }
    
    public void editarUsuario(String id,String pss, String nombre) throws SQLException {

        try {
            ConexionBD.connect();
            System.out.println("Conecta");
            Statement st = ConexionBD.conn.createStatement();
            
            if (pss!=null && pss.length()>1){
                st.executeUpdate("update usuarios \n"
                    + "set pass='"+pss+"',name='"+nombre+"' where id = '"+id+"'");
            } else {
                st.executeUpdate("update usuarios \n"
                    + "set name='"+nombre+"' where id = '"+id+"'");
            }
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionBD.conn.close();
        }
    }
    
    public List consultarblreaders() throws SQLException {

        List blogsReaders = new ArrayList<>();
        try {
            ConexionBD.connect();
            System.out.println("Conecta");
            Statement st = ConexionBD.conn.createStatement();
            ResultSet rs = st.executeQuery("select r_id,b_id,convert(varchar,fecha) fecha from blogs_readers");
            
            while (rs.next()){
                BlogsReaders blgrd = new BlogsReaders(rs.getInt("b_id"),rs.getInt("r_id"),rs.getString("fecha"));
                blogsReaders.add(blgrd);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionBD.conn.close();
        }
        return blogsReaders;
    }
    
    public List consultarblogs() throws SQLException {

        List blogsReaders = new ArrayList<>();
        try {
            ConexionBD.connect();
            System.out.println("Conecta");
            Statement st = ConexionBD.conn.createStatement();
            ResultSet rs = st.executeQuery("select id,title,description from blogs");
            
            while (rs.next()){
                Blogs blg = new Blogs(rs.getInt("id"),rs.getString("title"),rs.getString("description"));
                blogsReaders.add(blg);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionBD.conn.close();
        }
        return blogsReaders;
    }
    
    
            
    public List agregareditarblrd(int idr,int idb) throws SQLException {

        List blogsReaders = new ArrayList<>();
        try {
            ConexionBD.connect();
            Statement st = ConexionBD.conn.createStatement();
            st.executeUpdate(" if ((select count(*) from blogs_readers where r_id ="+idr+" and b_id="+idb+")>0)\n" +
                                            " begin\n" +
                                            "	update blogs_readers\n" +
                                            "	set R_ID = "+idr+",b_id="+idb+"\n" +
                                            "	where r_id ="+idr+"  and b_id="+idb+"\n" +
                                            " end\n" +
                                            " else \n" +
                                            " begin\n" +
                                            "	insert into blogs_readers(r_id,b_id)\n" +
                                            "	select "+idr+","+idb+"\n" +
                                            " end");
            
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionBD.conn.close();
        }
        return blogsReaders;
    }
    
    public List eliminarblrd(int idr,int idb) throws SQLException {

        List blogsReaders = new ArrayList<>();
        try {
            ConexionBD.connect();
            Statement st = ConexionBD.conn.createStatement();
            st.executeUpdate(" delete from blogs_readers\n" +
                                            "	where r_id ="+idr+" and b_id="+idb);
            
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionBD.conn.close();
        }
        return blogsReaders;
    }
    
    public void insertarLectorBlog(int idr,int idb) throws SQLException{
        try {
            ConexionBD.connect();
            Statement st = ConexionBD.conn.createStatement();
            st.executeUpdate("delete from blogs_readers where r_id ="+idr+" and b_id="+idb);
            st.executeUpdate(" if ((select count(*) from blogs_readers where r_id ="+idr+" and b_id="+idb+")=0)\n" +
                                            " begin\n" +
                                            "	insert into blogs_readers(r_id,b_id)\n" +
                                            "   select "+idr+", "+idb+" \n" +
                                            " end");
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionBD.conn.close();
        }

    }
    
    public int consultaridusu(String name) throws SQLException {
        int rta=0 ;
        try {
            ConexionBD.connect();
            System.out.println("Conecta");
            Statement st = ConexionBD.conn.createStatement();
            ResultSet rs = st.executeQuery("Select id from readers where name ='"+name+"'");
            while (rs.next()){
                rta = rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionBD.conn.close();
        }
        return rta;
    }
    
    public String agregarBlog(int id,String title, String desc) throws SQLException {
        String rta = "ok";
        System.out.println("entra agregar blog");
        try {
            ConexionBD.connect();
            Statement st = ConexionBD.conn.createStatement();
            st.executeUpdate("insert into blogs(id,title,description) \n"
                    + "select "+id+",'"+title+"','"+desc+"'");
            
        } catch (Exception e) {
            rta = e.getMessage();
            System.out.println(rta);
        } finally {
            ConexionBD.conn.close();
        }
        return rta;
    }
    
    public void eliminarblog(int id) throws SQLException {

        try {
            ConexionBD.connect();
            System.out.println("Conecta");
            Statement st = ConexionBD.conn.createStatement();
            st.executeUpdate("delete from blogs where id ="+id+"");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionBD.conn.close();
        }
    }
    
     public void editarblog(int id,String tit, String desc) throws SQLException {

        try {
            ConexionBD.connect();
            System.out.println("Conecta");
            Statement st = ConexionBD.conn.createStatement();
            
                st.executeUpdate("update blogs \n"
                    + "set title='"+tit+"',description='"+desc+"' where id = "+id);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionBD.conn.close();
        }
    }
}
