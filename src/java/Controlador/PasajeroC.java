package Controlador;

import DAO.PasajeroD;
import Modelo.PasajeroM;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;

@Named(value = "pasajeroC")
@SessionScoped
@Data
public class PasajeroC implements Serializable{

    private PasajeroM pasajero = new PasajeroM();
    private List<PasajeroM> lstPasajero;
    private String accion = "Defecto";

    @PostConstruct
    public void Iniciar(){
        try {
            Listar();
        } catch (Exception e) {
        }
    }
    
    public void operar() throws Exception {
        switch (accion) {
            case "Registrar":
                this.guardar();
                break;
            case "Modificar":
                this.modificar();
                break;
        }
    }

    public void Limpiar() {
        pasajero = new PasajeroM();
    }

    private void guardar() throws Exception {
        PasajeroD DAO;
        try {
            DAO = new PasajeroD();
            DAO.registrar(pasajero);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("AGREGADO"));
            Listar();
            Limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
            throw e;
        }
    }

    public void modificar() throws Exception {
        PasajeroD DAO;
        try {
            DAO = new PasajeroD();
            DAO.Modificar(pasajero);
            Listar();
            Limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ACTUALIZADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
            throw e;
        }
    }

    public void eliminar(PasajeroM pasa) throws Exception {
        PasajeroD DAO;
        try {
            DAO = new PasajeroD();
            DAO.Eliminar(pasa);
            Listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ELIMINADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
            throw e;
        }
    }

    public void Listar() throws Exception {
        PasajeroD DAO;
        try {
            DAO = new PasajeroD();
            lstPasajero = DAO.Listar();
        } catch (Exception e) {
            throw e;
        }

    }

    public void leerID(String Codigo) throws Exception {
        PasajeroD DAO;
        try {
            DAO = new PasajeroD();
            this.pasajero = DAO.LeerId(Codigo);
            this.accion = "Modificar";
        } catch (Exception e) {
            throw e;
        }
    }
}
