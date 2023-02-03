package negocio.clases;

import java.util.Date;

public class Paciente {

    private Integer num_asegurado;
    private String nombre;
    private String direccion;
    private Integer edad;
    private Date fecha_nacimiento;
    //private String fecha_nacimiento;
    private String email;
    private Integer telefono;
    private String profesion;

    public Integer getNum_asegurado() {
        return num_asegurado;
    }

    public void setNum_asegurado(Integer num_asegurado) {
        this.num_asegurado = num_asegurado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
}
