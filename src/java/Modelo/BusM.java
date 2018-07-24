package Modelo;

import lombok.Data;

@Data
public class BusM {

    private String Codigo;
    private String Placa;
    private String ChoferNombre;
    private String ChoferApellido;
    private String Telefono;

    //Asistente
    private String CodigoAsistente;
    private String NombreAsistente;

}
