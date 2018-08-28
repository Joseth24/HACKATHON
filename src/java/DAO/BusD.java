package DAO;

import Modelo.BusM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusD extends DAO {

    public void registrar(BusM bus) throws Exception {
        try {
            this.Conexion();
            String sql = "INSERT INTO Bus (PlacaBus, TelefonoBus) VALUES (?,?)";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, bus.getPlaca());
            st.setString(2, bus.getTelefono());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void Eliminar(BusM bus) throws Exception {
        try {
            this.Conexion();
            String sql = "delete from Bus Where IdBus=?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, bus.getIdBus());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void Modificar(BusM bus) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE Bus SET  PlacaBus=?, TelefonoBus=? WHERE IdBus = ?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, bus.getPlaca());
            st.setString(2, bus.getTelefono());
            st.setString(3, bus.getIdBus());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<BusM> Listar() throws Exception {
        List<BusM> Lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM Bus ";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            Lista = new ArrayList();
            while (rs.next()) {
                BusM bus = new BusM();
                bus.setIdBus(rs.getString("IdBus"));
                bus.setPlaca(rs.getString("PlacaBus"));
                bus.setTelefono(rs.getString("TelefonoBus"));
                Lista.add(bus);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return Lista;
    }

    public BusM LeerId(String Codigo) throws Exception {
        BusM bus = null;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT IdBus,PlacaBus,TelefonoBus FROM Bus WHERE IdBus=?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, Codigo);
            rs = st.executeQuery();
            while (rs.next()) {
                bus = new BusM();
                bus.setIdBus(rs.getString("IdBus"));
                bus.setPlaca(rs.getString("PlacaBus"));
                bus.setTelefono(rs.getString("TelefonoBus"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return bus;
    }

    public String obtenerCodigoBus(String bus) throws SQLException {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "select IdBus from Bus where PlacaBus like ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, bus);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("IdBus");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }
}
