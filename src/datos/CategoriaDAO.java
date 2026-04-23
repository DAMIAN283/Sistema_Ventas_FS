/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import database.Conexion;
import datos.interfaces.CrudSimpleInterface;
import entidades.Categorias;
import java.util.List;
import java.sql.PreparedStatement;
import java. sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.SQLException;

/**
 *
 * @author dilan
 */
public class CategoriaDAO implements CrudSimpleInterface<Categorias>{

    
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    public boolean resp;
    
    public CategoriaDAO()
    {
        CON= Conexion.getInstatncia();
    }
    
     @Override
    public List<Categorias> listar(String texto) {
        List<Categorias> registros = new ArrayList ();
         try {
             ps=CON.conectar().prepareStatement("SELECT * FROM categorias WHERE nombre LIKE ?");
              ps.setString(1, "%" + texto + "%");
              rs=ps.executeQuery();
              while(rs.next()){
                  registros.add(new Categorias(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getBoolean(4)));
              }
              ps.close();
              rs.close();
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }
         finally{
        ps=null;
        rs=null;
        CON.desconectar();
    }
         return registros;
    }

    @Override
    public boolean insertar(Categorias obj) {
        resp=false;
        try {
            ps=CON.conectar().prepareStatement("ISERT INTO categorias (nombre,descripcion, activo) VALUES (?,?,1");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            if(ps.executeUpdate()> 0){
                resp= true;
            }
            ps.close();;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
     }

    @Override
    public boolean actualizar(Categorias obj) {
        resp=false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE categoria SET activo=0, WHERE id=?");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            ps.setInt(3, obj.getId());
            if(ps.executeUpdate()> 0){
                resp= true;
            }
            ps.close();;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
     }

    @Override
    public boolean desactivar(int id) {
        resp=false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE categoria SET activo=0, WHERE id=?");
            ps.setInt(1, id);
            if(ps.executeUpdate()> 0){
                resp= true;
            }
            ps.close();;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
        }

    @Override
    public boolean activar(int id) {
        resp=false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE categoria SET nombre=?, descripcion=? WHERE id=?");
            ps.setInt(1,id);
            if(ps.executeUpdate()> 0){
                resp= true;
            }
            ps.close();;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
        }

    @Override
    public int total() {
        int totalRegistros=0;
        try {
            ps=CON.conectar().prepareStatement("SELECT count(id) categorias");
            rs=ps.executeQuery();
            while(rs.next()){
                totalRegistros=rs.getInt("COUNT(id)");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        finally{
            ps=null;
            CON.desconectar();
        }
        return totalRegistros;
          }

    @Override
    public boolean exite(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
