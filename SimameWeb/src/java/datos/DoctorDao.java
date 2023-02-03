
package datos;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.clases.Doctor;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

public class DoctorDao {
    
    private Conexion laConexion;
    private PreparedStatement sentencia;

    public DoctorDao() {
        this.laConexion = new Conexion();
        this.sentencia = null;
    }
            
            
        public int insertar(Doctor doctor){
                try {
                    if(this.laConexion.conectarse()){
                    this.sentencia = laConexion.getConn().prepareStatement("insert into simame.doctor values(?,?,?,?,?,?,?);");
                    sentencia.setInt(1, doctor.getCedula());
                    sentencia.setString(2, doctor.getNombre());
                    sentencia.setString(3, doctor.getApellido());
                    sentencia.setString(4, doctor.getEspecialidad());
                    sentencia.setDouble(5, doctor.getSalario());
                    sentencia.setString(6, doctor.getDireccion());
                    sentencia.setInt(7, doctor.getTelefono());
                    sentencia.executeUpdate();
                    this.laConexion.desconectarse();
                    return 0; // Todo bien
                    
            }else{
            return 1; // no se conecto a la BD.
            }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        if(ex.getSQLState().startsWith("23")){
                            return 3; // LLAVE PK DUPLI
                        }
                        return 2; // Error ejecutando el insert
        }  
                
        }   
        
        public int modificar(Doctor doctor){
                try {
                    if(this.laConexion.conectarse()){
                    this.sentencia = laConexion.getConn().prepareStatement("update simame.doctor set nombre = ?,apellido= ?, especialidad = ?,salario =?,direccion= ?,telefono= ? where cedula = ?");
                    sentencia.setString(1, doctor.getNombre());
                    sentencia.setString(2, doctor.getApellido());
                    sentencia.setString(3, doctor.getEspecialidad());
                    sentencia.setDouble(4, doctor.getSalario());
                    sentencia.setString(5, doctor.getDireccion());
                    sentencia.setInt(6, doctor.getTelefono());
                    sentencia.setInt(7, doctor.getCedula());
                    sentencia.executeUpdate ();
                    this.laConexion.desconectarse();
                    return 0; // Todo bien
                    
            }else{
            return 1; // no se conecto a la BD.
            }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        return 2; // Error ejecutando el insert
                    }
        }
        
        public int eliminar (Doctor doctor){
                try {
                    if(this.laConexion.conectarse()){
                    this.sentencia = laConexion.getConn().prepareStatement
                    ("delete from simame.doctor where cedula =?");
                    sentencia.setInt(1, doctor.getCedula());
                    int res = sentencia.executeUpdate (); //int res = es para saber cuantos se eliminaron
                    this.laConexion.desconectarse();
                    if(res== 0 ){
                        
                    return 0; // no se elimino nada
                    }else{
                        return 1; // se elimino bien
                    }
                    }else{
                        return 2; // no se conecto a la BD
                   
                    }  
            } catch(SQLIntegrityConstraintViolationException ex){
                    ex.printStackTrace();
                    return 4; // tiene registros asociados
                    
                }catch (SQLException ex) {
                        ex.printStackTrace();
                        return 2; // Error ejecutando el insert
                    }
        }
        
        
        
        
       public List<Doctor> consultarTodos(){
            try{
               if(this.laConexion.conectarse()){
                   this.sentencia = laConexion.getConn().prepareStatement
                    ("select * from simame.doctor");
                   List resultados = new ArrayList();
                   ResultSet datos = sentencia.executeQuery();
                   while(datos.next()){
                       Doctor doctor = new Doctor();
                       doctor.setCedula(datos.getInt(1));
                       doctor.setNombre(datos.getString(2));
                       doctor.setApellido(datos.getString(3));
                       doctor.setEspecialidad(datos.getString(4));
                       doctor.setSalario(datos.getDouble(5));
                       doctor.setDireccion(datos.getString(6));
                       doctor.setTelefono(datos.getInt(7));

                       resultados.add(doctor);
                    }
                   this.laConexion.desconectarse();
                   return resultados;
               }else{
                return null;
               }
            }catch(SQLException ex){
                ex.printStackTrace();
                return null;
        }
    }
        
        
         public Doctor consultarXCedula(Integer cedula){
            try{
               if(this.laConexion.conectarse()){
                   this.sentencia = laConexion.getConn().prepareStatement
                    ("select * from simame.doctor where cedula= ?");
                   sentencia.setInt(1, cedula);


                   Doctor elDoctor = new Doctor();
                   ResultSet datos = sentencia.executeQuery();
                   while(datos.next()){
                     
                       elDoctor.setCedula(datos.getInt(1));
                       elDoctor.setNombre(datos.getString(2));
                       elDoctor.setApellido(datos.getString(3));
                       elDoctor.setEspecialidad(datos.getString(4));
                       elDoctor.setSalario(datos.getDouble(5));
                       elDoctor.setDireccion(datos.getString(6));
                       elDoctor.setTelefono(datos.getInt(7));
                       

                   }
                   this.laConexion.desconectarse();
                   if(elDoctor.getCedula()== null){
                       return null;
                               }else{
                       return elDoctor;
                   }
                   
                   
                   

               }else{
                return null;
               }

            }catch(SQLException ex){
                ex.printStackTrace();
                return null;
            }
        

        }
         
         
         public List<Doctor> consultarXNombre(String nombre){
            try{
               if(this.laConexion.conectarse()){
                   this.sentencia = laConexion.getConn().prepareStatement
                    ("select * from simame.doctor where nombre like ? ");
                   sentencia.setString(1, "%"+nombre+"%");
                   List resultados = new ArrayList();
                   ResultSet datos = sentencia.executeQuery();
                   while(datos.next()){
                       Doctor doctor = new Doctor();
                       doctor.setCedula(datos.getInt(1));
                       doctor.setNombre(datos.getString(2));
                       doctor.setApellido(datos.getString(3));
                       doctor.setEspecialidad(datos.getString(4));
                       doctor.setSalario(datos.getDouble(5));
                       doctor.setDireccion(datos.getString(6));
                       doctor.setTelefono(datos.getInt(7));
                       resultados.add(doctor);
                   }
                   this.laConexion.desconectarse();
                   return resultados;
               }else{
                return null;
               }
               
            }catch(SQLException ex){
                ex.printStackTrace();
                return null;
            }
            
         
        }
        
        
    public Conexion getLaConexion() {
        return laConexion;
    }

    public void setLaConexion(Conexion laConexion) {
        this.laConexion = laConexion;
    }

    public PreparedStatement getSentencia() {
        return sentencia;
    }

    public void setSentencia(PreparedStatement sentencia) {
        this.sentencia = sentencia;
    }
    
    
}
