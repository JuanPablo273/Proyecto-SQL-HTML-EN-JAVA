package datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.clases.Doctor;
import negocio.clases.Salon;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;

public class SalonDao {

    private Conexion laConexion;
    private PreparedStatement sentencia;

    public SalonDao() {
        this.laConexion = new Conexion();
        this.sentencia = null;

    }

    public int insertar(Salon salon) {
        try {
            if (this.laConexion.conectarse()) {
                this.sentencia = laConexion.getConn().prepareStatement("insert into simame.salon values(?, ?,?,?);");
                sentencia.setInt(1, salon.getNum_salon());
                sentencia.setInt(2, salon.getCant_camas());
                sentencia.setString(3, salon.getArea());
                sentencia.setInt(4, salon.getCedula_doctor());
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

    public int modificar(Salon salon) {
        try {
            if (this.laConexion.conectarse()) {
                this.sentencia = laConexion.getConn().prepareStatement("update simame.salon set cant_camas = ?, area = ?, cedula_doctor= ? where num_salon = ?");
                sentencia.setInt(1, salon.getCant_camas());
                sentencia.setString(2, salon.getArea());
                sentencia.setInt(3, salon.getCedula_doctor());
                sentencia.setInt(4, salon.getNum_salon());
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

    public int eliminar(Salon salon) {
        try {
            if (this.laConexion.conectarse()) {
                this.sentencia = laConexion.getConn().prepareStatement("delete from simame.salon where num_salon=?");
                sentencia.setInt(1, salon.getNum_salon());
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

    public List<Salon> consultarTodos() {
        try {
            if (this.laConexion.conectarse()) {
                this.sentencia = laConexion.getConn().prepareStatement("select * from simame.salon");
                List resultados = new ArrayList();
                ResultSet datos = sentencia.executeQuery();
                while (datos.next()) {
                    Salon salon = new Salon();
                    salon.setNum_salon(datos.getInt(1));
                    salon.setCant_camas(datos.getInt(2));
                    salon.setArea(datos.getString(3));
                    salon.setCedula_doctor(datos.getInt(4));
                    resultados.add(salon);
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

    public Salon consultarXNum_salon(Integer num_salon) {
        try {
            if (this.laConexion.conectarse()) {
                this.sentencia = laConexion.getConn().prepareStatement("select * from simame.salon where num_salon= ?");
                sentencia.setInt(1, num_salon);  // puede haber error aqui

                Salon elSalon = new Salon();
                ResultSet datos = sentencia.executeQuery();
                while (datos.next()) {
                    elSalon.setNum_salon(datos.getInt(1));
                    elSalon.setCant_camas(datos.getInt(2));
                    elSalon.setArea(datos.getString(3));
                    elSalon.setCedula_doctor(datos.getInt(4));
                }
                this.laConexion.desconectarse();
                if (elSalon.getNum_salon() == null) {
                    return null;
                } else {
                    return elSalon;
                }

            } else {
                return null;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public List<Salon> consultarXArea(String area) {
        System.out.println(area);
        try {
            if (this.laConexion.conectarse()) {
                this.sentencia = laConexion.getConn().prepareStatement("select * from simame.salon where area = ? ");
                sentencia.setString(1, area);
                List resultados = new ArrayList();
                ResultSet datos = sentencia.executeQuery();
                while (datos.next()) {
                    Salon consultarArea = new Salon();
                    consultarArea.setNum_salon(datos.getInt(1));
                    consultarArea.setCant_camas(datos.getInt(2));
                    consultarArea.setArea(datos.getString(3));
                    consultarArea.setCedula_doctor(datos.getInt(4));    // PUEDE HABER error AQUI 
                    resultados.add(consultarArea);

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
