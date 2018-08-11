package Controlador;

import DAO.AsistenteD;
import DAO.BoletoD;
import DAO.BusD;
import DAO.ChoferD;
import DAO.PasajeroD;
import DAO.UsuarioD;
import Modelo.BoletoM;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;

@Named(value = "boletoC")
@SessionScoped
@Data
public class BoletoC implements Serializable {

    private BoletoM boleto = new BoletoM();
    private List<BoletoM> lstBoleto;
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
        boleto = new BoletoM();
    }

    private void guardar() throws Exception {
        BoletoD DAO;
        try {
            DAO = new BoletoD();
            DAO.registrar(boleto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("AGREGADO"));
            Listar();
            Limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
            throw e;
        }
    }

    public void modificar() throws Exception {
        BoletoD DAO;
        try {
            DAO = new BoletoD();
            DAO.Modificar(boleto);
            Listar();
            Limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ACTUALIZADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
            throw e;
        }
    }

    public void eliminar(BoletoM boleto) throws Exception {
        BoletoD DAO;
        try {
            DAO = new BoletoD();
            DAO.Eliminar(boleto);
            Listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ELIMINADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
            throw e;
        }
    }

    public void Listar() throws Exception {
        BoletoD DAO;
        try {
            DAO = new BoletoD();
            lstBoleto = DAO.Listar();
        } catch (Exception e) {
            throw e;
        }

    }

    public void leerID(String Codigo) throws Exception {
        BoletoD DAO;
        try {
            DAO = new BoletoD();
            this.boleto = DAO.LeerId(Codigo);
            this.accion = "Modificar";
        } catch (Exception e) {
            throw e;
        }
    }
}
