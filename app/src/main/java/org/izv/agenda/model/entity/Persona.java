package org.izv.agenda.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "persona")
public class Persona implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;

    @NonNull
    @ColumnInfo(name="apellidos")
    private String apellidos;

    @NonNull
    @ColumnInfo(name="telefono")
    private String telefono;

    @NonNull
    @ColumnInfo(name="fnacimiento")
    private String fNacimiento;

    @NonNull
    @ColumnInfo(name="localidad")
    private String localidad;

    @NonNull
    @ColumnInfo(name="calle")
    private String calle;

    @NonNull
    @ColumnInfo(name="numero")
    private String numero;

    public Persona(@NonNull String nombre, @NonNull String apellidos, String telefono, @NonNull String fNacimiento, @NonNull String localidad, @NonNull String calle, String numero) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fNacimiento = fNacimiento;
        this.localidad = localidad;
        this.calle = calle;
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(@NonNull String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @NonNull
    public String getFNacimiento() {
        return fNacimiento;
    }

    public void setFNacimiento(@NonNull String fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    @NonNull
    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(@NonNull String localidad) {
        this.localidad = localidad;
    }

    @NonNull
    public String getCalle() {
        return calle;
    }

    public void setCalle(@NonNull String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return id == persona.id &&
                telefono == persona.telefono &&
                numero == persona.numero &&
                nombre.equals(persona.nombre) &&
                apellidos.equals(persona.apellidos) &&
                fNacimiento.equals(persona.fNacimiento) &&
                localidad.equals(persona.localidad) &&
                calle.equals(persona.calle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellidos, telefono, fNacimiento, localidad, calle, numero);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", telefono=" + telefono +
                ", fNacimiento='" + fNacimiento + '\'' +
                ", localidad='" + localidad + '\'' +
                ", calle='" + calle + '\'' +
                ", numero=" + numero +
                '}';
    }
}
