
package negocio.bo;


import datos.SalonDao;
import java.util.List;
import negocio.clases.Doctor;
import negocio.clases.Salon;

public class SalonBo {
    
    
     private SalonDao salonDao;

    public SalonBo() {
        this.salonDao = new SalonDao();
        
    }
     
     public int insertar(Salon salon){
         return salonDao.insertar(salon);
         
         
     }
         public int modificar(Salon salon){
         return salonDao.modificar(salon);

     }
         
        public int eliminar(Salon salon){
         return salonDao.eliminar(salon); 
     }
        
        public List<Salon> consultarXArea (String area){
            return salonDao.consultarXArea(area); 
        }
        
        
        public List<Salon> consultarTodos(){
            return salonDao.consultarTodos();
        }
        
        public Salon consultarXNum_salon(Integer num_salon){
        return salonDao.consultarXNum_salon(num_salon);
        
            
        }
        
        
        
    public SalonDao getSalonDao() {
        return salonDao;
    }

    public void setSalonDao(SalonDao salonDao) {
        this.salonDao = salonDao;
    }
     
     
    
}  
    


    

