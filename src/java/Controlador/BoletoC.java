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
        UsuarioD dao2;
        AsistenteD dao3;
        PasajeroD dao4;
        BusD dao5;
        ChoferD dao6;
        try {
            DAO = new BoletoD();
            dao2 = new UsuarioD();
            dao3 = new AsistenteD();
            dao4 = new PasajeroD();
            dao5 = new BusD();
            dao6 = new ChoferD();
            boleto.setCodigoUsuario(dao2.obtenerCodigoUsuario(boleto.getNombreUsuario()));
            boleto.setCodigoAsistente(dao3.obtenerCodigoAsistente(boleto.getNombreAsistente()));
            boleto.setCodigoPasajero(dao4.obtenerCodigoPasajero(boleto.getNombrePasajero()));
            boleto.setCodigoBus(dao5.obtenerCodigoBus(boleto.getPlacaBus()));
            boleto.setCodigoChofer(dao6.obtenerCodigoChofer(boleto.getNombreChofer()));
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
        UsuarioD dao2;
        AsistenteD dao3;
        PasajeroD dao4;
        BusD dao5;
        ChoferD dao6;
        try {
            DAO = new BoletoD();
            dao2 = new UsuarioD();
            dao3 = new AsistenteD();
            dao4 = new PasajeroD();
            dao5 = new BusD();
            dao6 = new ChoferD();
            boleto.setCodigoUsuario(dao2.obtenerCodigoUsuario(boleto.getNombreUsuario()));
            boleto.setCodigoAsistente(dao3.obtenerCodigoAsistente(boleto.getNombreAsistente()));
            boleto.setCodigoPasajero(dao4.obtenerCodigoPasajero(boleto.getNombrePasajero()));
            boleto.setCodigoBus(dao5.obtenerCodigoBus(boleto.getPlacaBus()));
            boleto.setCodigoChofer(dao6.obtenerCodigoChofer(boleto.getNombreChofer()));
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

    public List<String> completeTextAsistente(String query) throws SQLException {
        BoletoD DAO = new BoletoD();
        return DAO.autocompleteAsistente(query);
    }

    public List<String> completeTextPasajero(String query) throws SQLException {
        BoletoD DAO = new BoletoD();
        return DAO.autocompletePasajero(query);
    }

    public List<String> completeTextUsuario(String query) throws SQLException {
        BoletoD DAO = new BoletoD();
        return DAO.autocompleteUsuario(query);
    }

    public List<String> completeTextBus(String query) throws SQLException {
        BoletoD DAO = new BoletoD();
        return DAO.autocompleteBus(query);
    }

    public List<String> completeTextChofer(String query) throws SQLException {
        BoletoD DAO = new BoletoD();
        return DAO.autocompleteChofer(query);
    }
}
