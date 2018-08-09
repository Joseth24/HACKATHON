package DAO;

import Modelo.BoletoM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoletoD extends DAO {

    public void registrar(BoletoM boleto) throws Exception {
        try {
            this.Conexion();
            String sql = "INSERT INTO Boleto (OrigenBoleto, DestinoBoleto, FechaViajeBoleto,CostoBoleto,HoraPartida,Asistente_IdAsistente,Pasajero_IdPasajero,Usuario_IdUsuario,Bus_IdBus,Chofer_IdChofer) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, boleto.getOrigen());
            st.setString(2, boleto.getDestino());
            st.setString(3, boleto.getFechaViaje());
            st.setString(4, boleto.getCosto());
            st.setString(5, boleto.getPartida());
            st.setString(6, boleto.getCodigoAsistente());
            st.setString(7, boleto.getCodigoPasajero());
            st.setString(8, boleto.getCodigoUsuario());
            st.setString(9, boleto.getCodigoBus());
            st.setString(10, boleto.getCodigoChofer());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void Eliminar(BoletoM bole) throws Exception {
        try {
            this.Conexion();
            String sql = "delete from Boleto Where IdBoleto=?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, bole.getIdBoleto());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void Modificar(BoletoM bole) throws Exception {
        try {
            this.Conexion();
            String sql = "SP_ACTUALIZAR_ASISTENTE ?,?,?,?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, bole.getIdBoleto());
            st.setString(2, bole.getOrigen());
            st.setString(3, bole.getDestino());
            st.setString(4, bole.getFechaViaje());
            st.setString(5, bole.getCosto());
            st.setString(6, bole.getPartida());
            st.setString(7, bole.getCodigoAsistente());
            st.setString(8, bole.getCodigoPasajero());
            st.setString(9, bole.getCodigoUsuario());
            st.setString(10, bole.getCodigoBus());
            st.setString(11, bole.getCodigoChofer());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<BoletoM> Listar() throws Exception {
        List<BoletoM> Lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM Boleto ";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            Lista = new ArrayList();
            while (rs.next()) {
                BoletoM bole = new BoletoM();
                bole.setIdBoleto(rs.getString("IdBoleto"));
                bole.setOrigen(rs.getString("OrigenBoleto"));
                bole.setDestino(rs.getString("DestinoBoleto"));
                bole.setFechaViaje(rs.getString("FechaViajeBoleto"));
                bole.setCosto(rs.getString("CostoBoleto"));
                bole.setPartida(rs.getString("HoraPartida"));
                bole.setCodigoAsistente(rs.getString("Asistente"));
                bole.setCodigoPasajero(rs.getString("Pasajero"));
                bole.setCodigoUsuario(rs.getString("Usuario"));
                bole.setCodigoBus(rs.getString("Bus"));
                bole.setCodigoChofer(rs.getString("Chofer"));
                Lista.add(bole);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return Lista;
    }

    public BoletoM LeerId(String Codigo) throws Exception {
        BoletoM bole = null;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT IdBoleto,OrigenBoleto,DestinoBoleto,FechaViajeBoleto,CostoBoleto,HoraPartida,CONCAT(Asistente.NombreAsistente,' ', Asistente.ApellidoAsistente) AS Asistente,CONCAT(Pasajero.NombrePasajero,' ',Pasajero.ApellidoPasajero) AS Pasajero, CONCAT(Usuario.NombreUsuario,' ',Usuario.ApellidoUsuario) AS Usuario,Bus.PlacaBus AS Bus, CONCAT(Chofer.NombreChofer,' ',Chofer.ApellidoChofer) AS Chofer FROM Boleto LEFT OUTER JOIN Asistente on Boleto.Asistente_IdAsistente = Asistente.IdAsistente LEFT OUTER JOIN Pasajero ON Boleto.Pasajero_IdPasajero = Pasajero.IdPasajero LEFT OUTER JOIN Usuario ON Boleto.Usuario_IdUsuario = Usuario.IdUsuario LEFT OUTER JOIN Bus ON Boleto.Bus_IdBus = Bus.IdBus LEFT OUTER JOIN Chofer ON Boleto.Chofer_IdChofer = Chofer.IdChofer WHERE IdBoleto=?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, Codigo);
            rs = st.executeQuery();
            while (rs.next()) {
                bole = new BoletoM();
                bole.setIdBoleto(rs.getString("IdBoleto"));
                bole.setOrigen(rs.getString("OrigenBoleto"));
                bole.setDestino(rs.getString("DestinoBoleto"));
                bole.setFechaViaje(rs.getString("FechaViajeBoleto"));
                bole.setCosto(rs.getString("CostoBoleto"));
                bole.setPartida(rs.getString("HoraPartida"));
                bole.setCodigoAsistente(rs.getString("Asistente"));
                bole.setCodigoPasajero(rs.getString("Pasajero"));
                bole.setCodigoUsuario(rs.getString("Usuario"));
                bole.setCodigoBus(rs.getString("Bus"));
                bole.setCodigoChofer(rs.getString("Chofer"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return bole;
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

    public List<String> autocompletePasajero(String Consulta) throws SQLException {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "select concat(NombrePasajero,' ',ApellidoPasajero) AS Pasajero from Pasajero where UPPER(NombrePasajero) like UPPER(?) or UPPER(ApellidoPasajero) like UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            ps.setString(2, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("Pasajero"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<String> autocompleteUsuario(String Consulta) throws SQLException {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "select concat(NombreUsuario,' ',ApellidoUsuario) AS Usuario from Usuario where UPPER(NombreUsuario) like UPPER(?) or UPPER(ApellidoUsuario) like UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            ps.setString(2, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("Usuario"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<String> autocompleteBus(String Consulta) throws SQLException {
        this.Conexion();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "select concat(PlacaBus,' / ',TelefonoBus) AS Bus from Bus where UPPER(PlacaBus) like UPPER(?) or UPPER(TelefonoBus) like UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + Consulta + "%");
            ps.setString(2, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {

                Lista.add(rs.getString("Bus"));
            }
            return Lista;
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
