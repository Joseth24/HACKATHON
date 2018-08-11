package Controlador;

import DAO.BusD;
import Modelo.BusM;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;

@Named(value = "busC")
@SessionScoped
@Data
public class BusC implements Serializable {

    private BusM bus = new BusM();
    private List<BusM> lstBus;
    private String accion = "Defecto";

    @PostConstruct
    public void Iniciar() {
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
        bus = new BusM();
    }

    private void guardar() throws Exception {
        BusD DAO;
        try {
            DAO = new BusD();
            DAO.registrar(bus);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("AGREGADO"));
            Listar();
            Limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
            throw e;
        }
    }

    public void modificar() throws Exception {
        BusD DAO;
        try {
            DAO = new BusD();
            DAO.Modificar(bus);
            Listar();
            Limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ACTUALIZADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
            throw e;
        }
    }

    public void eliminar(BusM bus) throws Exception {
        BusD DAO;
        try {
            DAO = new BusD();
            DAO.Eliminar(bus);
            Listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ELIMINADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
            throw e;
        }
    }

    public void Listar() throws Exception {
        BusD DAO;
        try {
            DAO = new BusD();
            lstBus = DAO.Listar();
        } catch (Exception e) {
            throw e;
        }

    }

    public void leerID(String Codigo) throws Exception {
        BusD DAO;
        try {
            DAO = new BusD();
            this.bus = DAO.LeerId(Codigo);
            this.accion = "Modificar";
        } catch (Exception e) {
            throw e;
        }
    }

}
