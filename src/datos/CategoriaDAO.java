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
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }
         return registros;
    }

    @Override
    public boolean insertar(Categorias obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizar(Categorias obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean desactivar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean activar(int obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int total() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean exite(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
