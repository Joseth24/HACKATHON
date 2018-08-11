package Controlador;

import DAO.AsistenteD;
import DAO.ChoferD;
import Modelo.ChoferM;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;

@Named(value = "choferC")
@SessionScoped
@Data
public class ChoferC implements Serializable {

    private ChoferM chofer = new ChoferM();
    private List<ChoferM> lstChofer;
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
        chofer = new ChoferM();
    }

    public List<String> completeText(String query) throws SQLException {
        ChoferD DAO = new ChoferD();
        return DAO.autocompleteAsistente(query);
    }

    private void guardar() throws Exception {
        ChoferD DAO;
        AsistenteD dao2;
        try {
            DAO = new ChoferD();
            dao2 = new AsistenteD();
            chofer.setCodigoAsistente(dao2.obtenerCodigoAsistente(chofer.getNombreAsistente()));
            DAO.registrar(chofer);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("AGREGADO"));
            Listar();
            Limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
            throw e;
        }
    }

    public void modificar() throws Exception {
        ChoferD DAO;
        AsistenteD dao2;
        try {
            DAO = new ChoferD();
            dao2 = new AsistenteD();
            chofer.setCodigoAsistente(dao2.obtenerCodigoAsistente(chofer.getNombreAsistente()));
            DAO.Modificar(chofer);
            Listar();
            Limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ACTUALIZADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
            throw e;
        }
    }

    public void eliminar(ChoferM chofer) throws Exception {
        ChoferD DAO;
        try {
            DAO = new ChoferD();
            DAO.Eliminar(chofer);
            Listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ELIMINADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
            throw e;
        }
    }

    public void Listar() throws Exception {
        ChoferD DAO;
        try {
            DAO = new ChoferD();
            lstChofer = DAO.Listar();
        } catch (Exception e) {
            throw e;
        }

    }

    public void leerID(String Codigo) throws Exception {
        ChoferD DAO;
        try {
            DAO = new ChoferD();
            this.chofer = DAO.LeerId(Codigo);
            this.accion = "Modificar";
        } catch (Exception e) {
            throw e;
        }
    }
}
