package DAO;

import Modelo.ChoferM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChoferD extends DAO {

    public void registrar(ChoferM chofer) throws Exception {
        try {
            this.Conexion();
            String sql = "SP_ADD_CHOFER ?,?,?,?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, chofer.getNombreChofer());
            st.setString(2, chofer.getApellidoChofer());
            st.setString(3, chofer.getTelefonoChofer());
            st.setString(4, chofer.getCodigoAsistente());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void Eliminar(ChoferM chof) throws Exception {
        try {
            this.Conexion();
            String sql = "delete from Chofer Where IdChofer=?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, chof.getCodigoChofer());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void Modificar(ChoferM chofer) throws Exception {
        try {
            this.Conexion();
            String sql = "SP_UPDATE_CHOFER ?,?,?,?,?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, chofer.getCodigoChofer());
            st.setString(2, chofer.getNombreChofer());
            st.setString(3, chofer.getApellidoChofer());
            st.setString(4, chofer.getTelefonoChofer());
            st.setString(5, chofer.getCodigoAsistente());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<ChoferM> Listar() throws Exception {
        List<ChoferM> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM VW_CHOFER ";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                ChoferM chof = new ChoferM();
                chof.setCodigoChofer(rs.getString("IdChofer"));
                chof.setNombreChofer(rs.getString("NombreChofer"));
                chof.setApellidoChofer(rs.getString("ApellidoChofer"));
                chof.setTelefonoChofer(rs.getString("TelefonoChofer"));
                chof.setCodigoAsistente(rs.getString("Asistente"));
                lista.add(chof);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

    public ChoferM LeerId(String Codigo) throws Exception {
        ChoferM chofer = null;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT IdChofer,NombreChofer,ApellidoChofer,TelefonoChofer,CONCAT(Asistente.NombreAsistente,' ',Asistente.ApellidoAsistente) AS Asistente FROM Chofer LEFT OUTER JOIN Asistente ON Chofer.Asistente_IdAsistente = Asistente.IdAsistente WHERE IdChofer=?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, Codigo);
            rs = st.executeQuery();
            while (rs.next()) {
                chofer = new ChoferM();
                chofer.setCodigoChofer(rs.getString("IdChofer"));
                chofer.setNombreChofer(rs.getString("NombreChofer"));
                chofer.setApellidoChofer(rs.getString("ApellidoChofer"));
                chofer.setTelefonoChofer(rs.getString("TelefonoChofer"));
                chofer.setCodigoAsistente(rs.getString("Asistente"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return chofer;
    }

    public String obtenerCodigoChofer(String chofer) throws SQLException {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "select IdChofer from Chofer where concat(NombreChofer,',',ApellidoChofer) like ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, chofer);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("IdChofer");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

}
