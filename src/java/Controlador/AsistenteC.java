package Controlador;

import DAO.AsistenteD;
import Modelo.AsistenteM;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "asistenteC")
@Dependent
public class AsistenteC {

    private AsistenteM asistente = new AsistenteM();
    private List<AsistenteM> lstAsistente;

    @PostConstruct
    public void Iniciar() {
        try {
            Listar();
        } catch (Exception e) {
        }
    }

    public void Limpiar(){
    this.asistente.getNombre();
    }
   
    public void Guardar(AsistenteD Asistente) throws Exception {
        AsistenteD DAO;
        try {
            DAO = new AsistenteD();
            DAO.registrar(asistente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("AGREGADO"));
            Listar();
            Limpiar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        AsistenteD DAO;
        try {
            DAO = new AsistenteD();
            DAO.Modificar(asistente);
            Listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ACTUALIZADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
            throw e;
        }
    }

    public void eliminar() throws Exception {
        AsistenteD DAO;
        try {
            DAO = new AsistenteD();
            DAO.Eliminar(asistente);
            Listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ELIMINADO"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void Listar() throws Exception {
        AsistenteD DAO;
        try {
            DAO = new AsistenteD();
            lstAsistente = DAO.Listar();
        } catch (Exception e) {
            throw e;
        }

    }

    public AsistenteM getAsistente() {
        return asistente;
    }

    public void setAsistente(AsistenteM asistente) {
        this.asistente = asistente;
    }

    public List<AsistenteM> getLstAsistente() {
        return lstAsistente;
    }

    public void setLstAsistente(List<AsistenteM> lstAsistente) {
        this.lstAsistente = lstAsistente;
    }
    
    
}
