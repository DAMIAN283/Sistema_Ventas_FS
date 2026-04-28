
package negocio;

import datos.CategoriaDAO;
import entidades.Categorias;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dilan
 */
public class CategoriaControl {
    
    private CategoriaDAO DATOS;
    private Categorias obj;
    private DefaultTableModel modeloTabla;
    
    
    public CategoriaControl(){
        this.DATOS= new CategoriaDAO();
        this.obj= new Categorias();
    }
    public DefaultTableModel listar(String texto){
        List<Categorias> lista = new ArrayList<>();
        lista.addAll(DATOS.listar(texto));
        
        String estado;
        String[] registro = new String[4];
        
        for(Categorias item:lista){
            if(item.isActivo()){
                estado = "Activo";
            }else{
                estado = "Inactivo";
            }
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getDescripcion();
            registro [2] = item.getDescripcion();
            registro [3] = estado;
            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }
    public String insertar(String nombre, String descripcion){
      if(DATOS.exite(nombre)){
          return "El registo ya existe";
      }else{
          obj.setNombre(nombre);
          obj.setDescripcion(descripcion);
          if (DATOS.insertar(obj)){
              return "OK";
          }else{
              return "Error en el registro";
          }
      }
    }
    public String actualizar(int id, String nombreAnt, String descripcion){
        
    }
    public String desactivar(int id){
        
    }
    public String activar(int id){
        
    }
    public int total(){
        
    }
