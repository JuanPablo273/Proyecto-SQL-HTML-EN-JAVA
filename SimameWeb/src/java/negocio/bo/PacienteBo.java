
package negocio.bo;

import datos.PacienteDao;
import java.util.List;
import negocio.clases.Paciente;


public class PacienteBo {
    
    private PacienteDao pacienteDao;

    public PacienteBo () {
        this.pacienteDao = new PacienteDao();
        
    }
     
     public int insertar(Paciente paciente){
         return pacienteDao.insertar(paciente);
         
         
     }
         public int modificar(Paciente paciente){
         return pacienteDao.modificar(paciente);

     }
         
        public int eliminar(Paciente paciente){
         return pacienteDao.eliminar(paciente); 
     }
        
        public List<Paciente> consultarXNombre(String nombre){
            return pacienteDao.consultarXNombre(nombre); 
        }
        
        
        public List<Paciente> consultarTodos(){
            return pacienteDao.consultarTodos();
        }
        
        public Paciente consultarXNum_asegurado(Integer num_asegurado){
        return pacienteDao.consultarXNum_asegurado(num_asegurado);
        
            
        }
        
        
        
    public PacienteDao getPacienteDao() {
        return pacienteDao;
    }

    public void setPacienteDao(PacienteDao pacienteDao) {
        this.pacienteDao = pacienteDao;
    }
     
     
    
}

    

