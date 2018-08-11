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

    //Usuario
    private String CodigoUsuario;

    //Bus
    private String CodigoBus;
    
    //Asistente
    private String CodigoAsistente;
    
    //Chofer
    private String CodigoChofer;

}
