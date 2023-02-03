package datos;

import static java.lang.System.out;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import negocio.clases.Doctor;
import negocio.clases.Salon;
import negocio.clases.Usuario;
import negocio.bo.UsuarioBo;
import sun.swing.PrintColorUIResource;

public class UsuarioDao {

    private Conexion laConexion;
    private PreparedStatement sentencia;

    public UsuarioDao() {
        this.laConexion = new Conexion();
        this.sentencia = null;

    }

    public int insertar(Usuario usuario) {
        try {
            if (this.laConexion.conectarse()) {
                this.sentencia = laConexion.getConn().prepareStatement("insert into simame.usuario values(?, ?,);");
                sentencia.setString(1, usuario.getUsuario());
                sentencia.setString(2, usuario.getPassword());

                sentencia.executeUpdate();
                this.laConexion.desconectarse();
                return 0; // Todo bien

            } else {
                return 1; // no se conecto a la BD.
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (ex.getSQLState().startsWith("23")) {
                return 3; // LLAVE PK DUPLI
            }
            return 2; // Error ejecutando el insert
        }

    }

    public int modificar(Usuario usuario) {
        try {
            if (this.laConexion.conectarse()) {
                this.sentencia = laConexion.getConn().prepareStatement("update simame.usuario set usuario= ?, password =?");
                sentencia.setString(1, usuario.getUsuario());
                sentencia.setString(2, usuario.getPassword());
                sentencia.executeUpdate();;
                sentencia.executeUpdate();
                this.laConexion.desconectarse();
                return 0; // Todo bien

            } else {
                return 1; // no se conecto a la BD.
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 2; // Error ejecutando el insert
        }
    }

    public int eliminar(Usuario usuario) {
        try {
            if (this.laConexion.conectarse()) {
                this.sentencia = laConexion.getConn().prepareStatement("delete from simame.usuario where Usuario=?");
                sentencia.setString(1, usuario.getUsuario());
                int res = sentencia.executeUpdate(); //int res = es para saber cuantos se eliminaron
                this.laConexion.desconectarse();
                if (res == 0) {

                    return 0; // no se elimino nada
                } else {
                    return 1; // se elimino bien
                }
            } else {
                return 2; // no se conecto a la BD

            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            ex.printStackTrace();
            return 4; // tiene registros asociados

        } catch (SQLException ex) {
            ex.printStackTrace();
            return 2; // Error ejecutando el insert
        }
    }

    public List<Usuario> consultarTodos() {
        try {
            if (this.laConexion.conectarse()) {
                this.sentencia = laConexion.getConn().prepareStatement("select * from simame.usuario");
                List resultados = new ArrayList();
                ResultSet datos = sentencia.executeQuery();
                while (datos.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setUsuario(datos.getString(1));
                    usuario.setPassword(datos.getString(2));

                    resultados.add(usuario);
                }
                this.laConexion.desconectarse();
                return resultados;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean consultarXUsuario(Usuario usuario) {

        Boolean resultado = false;

        try {
            if (this.laConexion.conectarse()) {
                System.out.println("entre al catch");
                this.sentencia = laConexion.getConn().prepareStatement("select * from simame.usuario where Usuario= ?");
                sentencia.setString(1, usuario.getUsuario());

                Usuario elUsuario = new Usuario();
                ResultSet datos = sentencia.executeQuery();
                while (datos.next()) {

                    elUsuario.setUsuario(datos.getString(1));
                    elUsuario.setPassword(datos.getString(2));
                    if (elUsuario.getUsuario() == null) {
                        resultado = false;
                    } else {

                        if (elUsuario.getPassword() == usuario.getPassword()) {
                            resultado = true;
                        } else {
                            resultado = false;

                        }

                    }

                }
                this.laConexion.desconectarse();

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return resultado;

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
