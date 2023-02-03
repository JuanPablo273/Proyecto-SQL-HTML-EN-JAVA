package negocio.bo;

import datos.DoctorDao;
import java.util.List;
import negocio.clases.Doctor;

public class DoctorBo {

    private DoctorDao doctorDao;

    public DoctorBo() {
        this.doctorDao = new DoctorDao();

    }

    public int insertar(Doctor doctor) {
        return doctorDao.insertar(doctor);

    }

    public int modificar(Doctor doctor) {
        return doctorDao.modificar(doctor);

    }

    public int eliminar(Doctor doctor) {
        return doctorDao.eliminar(doctor);
    }

    public List<Doctor> consultarXNombre(String nombre) {
        return doctorDao.consultarXNombre(nombre);
    }

    public List<Doctor> consultarTodos() {
        return doctorDao.consultarTodos();
    }

    public Doctor consultarXCedula(Integer cedula) {
        return doctorDao.consultarXCedula(cedula);

    }

    public DoctorDao getDoctorDao() {
        return doctorDao;
    }

    public void setDoctorDao(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

}
