
package datos;


public class Principal {
    

    public static void main(String[] args) {
        
        Conexion laConexion = new Conexion();
        
        if(laConexion.conectarse() == true){
            System.out.println("Si se conecto");
        }else{
            System.out.println("no se conecto");
        }
        
    }
    
}
