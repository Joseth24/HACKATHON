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
            String sql = "INSERT INTO Chofer (NombreChofer,ApellidoChofer,TelefonoChofer,Asistente_IdAsistente) VALUES(?,?,?,?)";
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
            String sql = "SP_ACTUALIZAR_ASISTENTE ?,?,?,?";
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
        List<ChoferM> Lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM VW_CHOFER ";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            Lista = new ArrayList();
            while (rs.next()) {
                ChoferM chof = new ChoferM();
                chof.setCodigoChofer(rs.getString("IdChofer"));
                chof.setNombreChofer(rs.getString("NombreChofer"));
                chof.setApellidoChofer(rs.getString("ApellidoChofer"));
                chof.setTelefonoChofer(rs.getString("TelefonoChofer"));
                chof.setCodigoAsistente(rs.getString("Asistente"));
                Lista.add(chof);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return Lista;
    }

    public List<String> autocompleteAsistente(String Consulta) throws SQLException {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "select concat(NombreAsistente,' ',ApellidoAsistente) AS Asistente from Asistente where UPPER(NombreAsistente) like UPPER(?) or UPPER(ApellidoAsistente) like UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            ps.setString(2, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("Asistente"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
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

    public List<String> autocompleteChofer(String Consulta) throws SQLException {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "select concat(NombreChofer,' ',ApellidoChofer) AS Chofer from Chofer where UPPER(NombreChofer) like UPPER(?) or UPPER(ApellidoChofer) like UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            ps.setString(2, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("Chofer"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

}
