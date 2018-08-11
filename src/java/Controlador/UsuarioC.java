package Controlador;

import DAO.UsuarioD;
import Modelo.UsuarioM;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;

@Named(value = "usuarioC")
@SessionScoped
@Data
public class UsuarioC implements Serializable {

    private UsuarioM usuario = new UsuarioM();
    private List<UsuarioM> lstUsuario;
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
        usuario = new UsuarioM();
    }

    private void guardar() throws Exception {
        UsuarioD DAO;
        try {
            DAO = new UsuarioD();
            DAO.registrar(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("AGREGADO"));
            Listar();
            Limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
            throw e;
        }
    }

    public void modificar() throws Exception {
        UsuarioD DAO;
        try {
            DAO = new UsuarioD();
            DAO.Modificar(usuario);
            Listar();
            Limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ACTUALIZADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
            throw e;
        }
    }

    public void eliminar(UsuarioM user) throws Exception {
        UsuarioD DAO;
        try {
            DAO = new UsuarioD();
            DAO.Eliminar(user);
            Listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ELIMINADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR"));
            throw e;
        }
    }

    public void Listar() throws Exception {
        UsuarioD DAO;
        try {
            DAO = new UsuarioD();
            lstUsuario = DAO.Listar();
        } catch (Exception e) {
            throw e;
        }

    }

    public void leerID(String Codigo) throws Exception {
        UsuarioD DAO;
        try {
            DAO = new UsuarioD();
            this.usuario = DAO.LeerId(Codigo);
            this.accion = "Modificar";
        } catch (Exception e) {
            throw e;
        }
    }

}
