package DAO;

import Modelo.PasajeroM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasajeroD extends DAO {

    public void registrar(PasajeroM pasajero) throws Exception {
        try {
            this.Conexion();
            String sql = "SP_ADD_PASAJERO ?,?,?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, pasajero.getNombre());
            st.setString(2, pasajero.getApellido());
            st.setString(3, pasajero.getDni());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void Eliminar(PasajeroM pasa) throws Exception {
        try {
            this.Conexion();
            String sql = "delete from Pasajero Where IdPasajero=?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, pasa.getIdPasajero());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void Modificar(PasajeroM asis) throws Exception {
        try {
            this.Conexion();
            String sql = "SP_UPDATE_PASAJERO ?,?,?,?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, asis.getIdPasajero());
            st.setString(2, asis.getNombre());
            st.setString(3, asis.getApellido());
            st.setString(4, asis.getDni());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<PasajeroM> Listar() throws Exception {
        List<PasajeroM> Lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM Pasajero ";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            Lista = new ArrayList();
            while (rs.next()) {
                PasajeroM pasa = new PasajeroM();
                pasa.setIdPasajero(rs.getString("IdPasajero"));
                pasa.setNombre(rs.getString("NombrePasajero"));
                pasa.setApellido(rs.getString("ApellidoPasajero"));
                pasa.setDni(rs.getString("DniPasajero"));
                Lista.add(pasa);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return Lista;
    }

    public PasajeroM LeerId(String Codigo) throws Exception {
        PasajeroM pasa = null;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT IdPasajero,NombrePasajero,ApellidoPasajero,DniPasajero FROM Pasajero WHERE IdPasajero=?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, Codigo);
            rs = st.executeQuery();
            while (rs.next()) {
                pasa = new PasajeroM();
                pasa.setIdPasajero(rs.getString("IdPasajero"));
                pasa.setNombre(rs.getString("NombrePasajero"));
                pasa.setApellido(rs.getString("ApellidoPasajero"));
                pasa.setDni(rs.getString("DniPasajero"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return pasa;
    }

    public String obtenerCodigoPasajero(String pasajero) throws SQLException {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "select IdPasajero from Pasajero where concat(NombrePasajero,' ',ApellidoPasajero) like ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, pasajero);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("IdPasajero");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }
}
