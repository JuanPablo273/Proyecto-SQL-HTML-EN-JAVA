package datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.clases.Paciente;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.text.SimpleDateFormat;

public class PacienteDao {

    private Conexion laConexion;
    private PreparedStatement sentencia;

    public PacienteDao() {
        this.laConexion = new Conexion();
        this.sentencia = null;

    }

    public int insertar(Paciente paciente) {
        try {
            System.out.println("entre a insertar en PacienteDao");
            System.out.println("entre a insertar en PacienteDao" + paciente.getFecha_nacimiento());

            if (this.laConexion.conectarse()) {
                this.sentencia = laConexion.getConn().prepareStatement("insert into simame.paciente values(?, ?,?,?,?,?,?,?);");
                sentencia.setInt(1, paciente.getNum_asegurado());
                sentencia.setString(2, paciente.getNombre());
                sentencia.setString(3, paciente.getDireccion());
                sentencia.setInt(4, paciente.getEdad());
                sentencia.setTimestamp(5, new Timestamp(paciente.getFecha_nacimiento().getTime()));
               // sentencia.setDate(5,  paciente.getFecha_nacimiento());
                sentencia.setString(6, paciente.getEmail());
                sentencia.setInt(7, paciente.getTelefono());
                sentencia.setString(8, paciente.getProfesion());
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

    public int modificar(Paciente paciente) {
        try {
            if (this.laConexion.conectarse()) {
                this.sentencia = laConexion.getConn().prepareStatement("update simame.paciente set nombre = ?, direccion =?, edad = ?, fecha_nacimiento = ?, e_mail = ?, telefono = ?"
                        + ", profesion= ? where num_asegurado= ?");
                sentencia.setString(1, paciente.getNombre());
                sentencia.setString(2, paciente.getDireccion());
                sentencia.setInt(3, paciente.getEdad());
                sentencia.setTimestamp(4, new Timestamp(paciente.getFecha_nacimiento().getTime()));
                sentencia.setString(5, paciente.getEmail());
                sentencia.setInt(6, paciente.getTelefono());
                sentencia.setString(7, paciente.getProfesion());
                sentencia.setInt(8, paciente.getNum_asegurado());
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

    public int eliminar(Paciente paciente) {
        try {
            if (this.laConexion.conectarse()) {
                this.sentencia = laConexion.getConn().prepareStatement("delete from simame.paciente where num_asegurado =?");
                sentencia.setInt(1, paciente.getNum_asegurado());
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

    public List<Paciente> consultarTodos() {
        try {
            if (this.laConexion.conectarse()) {
                this.sentencia = laConexion.getConn().prepareStatement("select * from simame.paciente");
                List resultados = new ArrayList();
                ResultSet datos = sentencia.executeQuery();
                while (datos.next()) {
                    Paciente paciente = new Paciente();
                    paciente.setNum_asegurado(datos.getInt(1));
                    paciente.setNombre(datos.getString(2));
                    paciente.setDireccion(datos.getString(3));
                    paciente.setEdad(datos.getInt(4));
                    paciente.setFecha_nacimiento(datos.getDate(5));
                    paciente.setEmail(datos.getString(6));
                    paciente.setTelefono(datos.getInt(7));
                    paciente.setProfesion(datos.getString(8));

                    resultados.add(paciente);
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

    public Paciente consultarXNum_asegurado(Integer num_asegurado) {
        try {
            if (this.laConexion.conectarse()) {
                this.sentencia = laConexion.getConn().prepareStatement("select * from simame.paciente where num_asegurado= ?");
                sentencia.setInt(1, num_asegurado);

                Paciente elpaciente = new Paciente();
                ResultSet datos = sentencia.executeQuery();
                while (datos.next()) {
                    elpaciente.setNum_asegurado(datos.getInt(1));
                    elpaciente.setNombre(datos.getString(2));
                    elpaciente.setDireccion(datos.getString(3));
                    elpaciente.setEdad(datos.getInt(4));
                    elpaciente.setFecha_nacimiento(datos.getDate(5));
                    elpaciente.setEmail(datos.getString(6));
                    elpaciente.setTelefono(datos.getInt(7));
                    elpaciente.setProfesion(datos.getString(8));
                }
                this.laConexion.desconectarse();
                if (elpaciente.getNum_asegurado() == null) {
                    return null;
                } else {
                    return elpaciente;
                }

            } else {
                return null;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public List<Paciente> consultarXNombre(String nombre) {
        try {
            if (this.laConexion.conectarse()) {
                this.sentencia = laConexion.getConn().prepareStatement("select * from simame.paciente where nombre like ? ");
                sentencia.setString(1, "%" + nombre + "%");  // puede haber error
                List resultados = new ArrayList();
                ResultSet datos = sentencia.executeQuery();
                while (datos.next()) {
                    Paciente paciente = new Paciente();
                    paciente.setNum_asegurado(datos.getInt(1));
                    paciente.setNombre(datos.getString(2));
                    paciente.setDireccion(datos.getString(3));
                    paciente.setEdad(datos.getInt(4));
                    paciente.setFecha_nacimiento(datos.getDate(5));
                    paciente.setEmail(datos.getString(6));
                    paciente.setTelefono(datos.getInt(7));
                    paciente.setProfesion(datos.getString(8));
                    resultados.add(paciente);
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
