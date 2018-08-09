package Modelo;

import lombok.Data;

@Data
public class BoletoM {

    private String IdBoleto;
    private String Origen;
    private String Destino;
    private String FechaViaje;
    private String Costo;
    private String Partida;

    //Pasajero
    private String CodigoPasajero;
    private String NombrePasajero;

    //Usuario
    private String CodigoUsuario;
    private String NombreUsuario;

    //Bus
    private String CodigoBus;
    private String PlacaBus;
    
    //Asistente
    private String CodigoAsistente;
    private String NombreAsistente;
    
    //Chofer
    private String CodigoChofer;
    private String NombreChofer;

}
