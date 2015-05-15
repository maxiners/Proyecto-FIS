package modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import modelo.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SpringLayout;

/**
 *
 * @author Oscar
 */
public class Tipo_EquipoDAO implements CRUD<Tipo_EquipoDTO> {

    private static final String SQL_INSERT="insert into PERSONA (cedula,nombre,correo)values (?,?,?)";
    private static final String SQL_DELETE="delete from PERSONA where cedula=?";
    private static final String SQL_UPDATE="update PERSONA set correo=?,nombre=?  where cedula=?";
    private static final String SQL_READ="select * from PERSONA where cedula=?";
    private static final String SQL_READALL="select * from PERSONA";   
       
   private static final Conexion con= Conexion.entregarConexion();
    
   PreparedStatement ps; 
    @Override
    public boolean crear(Tipo_EquipoDTO tipo_EquipoDTO) {
        try {
            ps=con.getCnn().prepareStatement(SQL_INSERT);
            ps.setString(1,tipo_EquipoDTO.getDescripcion());
            ps.setString(2,tipo_EquipoDTO.getDescripcion());
            ps.setString(3, tipo_EquipoDTO.getId_rol());
            
            if(ps.executeUpdate()>0){return true;}
            
        } catch (SQLException ex) {
            Logger.getLogger(Tipo_EquipoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex.getSuppressed());
        }
        finally{con.cerraConexion();}
        return false;
    }

    @Override
    public boolean delete(Tipo_EquipoDTO tipo_EquipoDTO) {
        
        try {
            ps=con.getCnn().prepareStatement(SQL_DELETE);
            ps.setString(1,tipo_EquipoDTO.getDescripcion());
            if(ps.executeUpdate()>0){return true;}
        } catch (SQLException ex) {
            Logger.getLogger(Tipo_EquipoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{con.cerraConexion();}
        return false;
    }

    @Override
    public boolean update(Tipo_EquipoDTO tipo_EquipoDTO) {
     try {
            ps=con.getCnn().prepareStatement(SQL_UPDATE);
            ps.setString(1,tipo_EquipoDTO.getDescripcion());
            ps.setString(2,tipo_EquipoDTO.getId_rol());
            ps.setString(3,tipo_EquipoDTO.getDescripcion());
            if(ps.executeUpdate()>0){return true;}
        } catch (SQLException ex) {
            Logger.getLogger(Tipo_EquipoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{con.cerraConexion();}
        return false;  
        
    }

    @Override
    public Tipo_EquipoDTO read(Tipo_EquipoDTO tipo_EquipoDTO) {
         Tipo_EquipoDTO l=null;
        try {
            ps=con.getCnn().prepareStatement(SQL_READ);
            ResultSet rs;
            ps.setString(1,tipo_EquipoDTO.getDescripcion());
            rs=ps.executeQuery();
            while(rs.next()){
                l=new Tipo_EquipoDTO(new Tipo_Equipo("",""));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Tipo_EquipoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{con.cerraConexion();}
        return l;  

        
        
    }

    @Override
    public List<Tipo_EquipoDTO> readAll() {
       ArrayList <Tipo_EquipoDTO> personas=new ArrayList();
        try {
            ps=con.getCnn().prepareStatement(SQL_READALL);
            ResultSet rs;
                  
            rs=ps.executeQuery();
            while(rs.next())
                 personas.add(new Tipo_EquipoDTO(new Tipo_Equipo("","")));
           
        } catch (SQLException ex) {
            Logger.getLogger(Tipo_EquipoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{con.cerraConexion();}
        return personas;  
        
        
    }

      
}
