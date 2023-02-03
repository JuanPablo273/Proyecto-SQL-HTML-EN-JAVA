package negocio.bo;

import datos.SalonDao;
import datos.UsuarioDao;
import java.util.List;
import negocio.clases.Doctor;
import negocio.clases.Salon;
import negocio.clases.Usuario;

public class UsuarioBo {

    
   

    private UsuarioDao usuarioDao;

    public UsuarioBo() {
        this.usuarioDao = new UsuarioDao();

    }

    public int insertar(Usuario usuario) {
        return usuarioDao.insertar(usuario);

    }

    public int modificar(Usuario usuario) {
        return usuarioDao.modificar(usuario);

    }

    public int eliminar(Usuario usuario) {
        return usuarioDao.eliminar(usuario);
    }


    public List<Usuario> consultarTodos() {
        return usuarioDao.consultarTodos();

    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }
 
    public  boolean consultarXUsuario(Usuario usuario ) {
        return usuarioDao.consultarXUsuario(usuario);

    }

}
