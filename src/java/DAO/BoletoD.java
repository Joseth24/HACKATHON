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
            String sql = "INSERT INTO Boleto (OrigenBoleto,DestinoBoleto,FechaViajeBoleto,CostoBoleto,HoraPartida,Asistente_IdAsistente,Pasajero_IdPasajero,Usuario_IdUsuario,Bus_IdBus,Chofer_IdChofer) VALUES (?,?,?,?,?,?,?,?,?,?)";
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
            String sql = "UPDATE Boleto SET OrigenBoleto=?,DestinoBoleto=?,FechaViajeBoleto=?,CostoBoleto=?,HoraPartida=?,Asistente_IdAsistente=?,Pasajero_IdPasajero=?,Usuario_IdUsuario,Bus_IdBus=?,Chofer_IdChofer=? WHERE IdBoleto =?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, bole.getOrigen());
            st.setString(2, bole.getDestino());
            st.setString(3, bole.getFechaViaje());
            st.setString(4, bole.getCosto());
            st.setString(5, bole.getPartida());
            st.setString(6, bole.getCodigoAsistente());
            st.setString(7, bole.getCodigoPasajero());
            st.setString(8, bole.getCodigoUsuario());
            st.setString(9, bole.getCodigoBus());
            st.setString(10, bole.getCodigoChofer());
            st.setString(11, bole.getIdBoleto());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<BoletoM> Listar() throws Exception {
        List<BoletoM> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT IdBoleto, OrigenBoleto, DestinoBoleto,FORMAT(CONVERT(date, FechaViajeBoleto,103),'dd/MM/yyyy','en-gb') AS FechaViajeBoleto, CostoBoleto, HoraPartida,CONCAT(Asistente.NombreAsistente,' ',Asistente.ApellidoAsistente) AS Asistente, CONCAT(Pasajero.NombrePasajero,' ',Pasajero.ApellidoPasajero) AS Pasajero, CONCAT(Usuario.NombreUsuario,' ',Usuario.ApellidoUsuario) AS Usuario, CONCAT(Bus.PlacaBus,' ',Bus.TelefonoBus) AS Bus, CONCAT(Chofer.NombreChofer,' ', Chofer.ApellidoChofer) AS Chofer FROM Boleto INNER JOIN Asistente ON Boleto.Asistente_IdAsistente = Asistente.IdAsistente INNER JOIN Pasajero ON Boleto.Pasajero_IdPasajero = Pasajero.IdPasajero INNER JOIN Usuario ON Boleto.Usuario_IdUsuario = Usuario.IdUsuario INNER JOIN Bus ON Boleto.Bus_IdBus = Bus.IdBus INNER JOIN Chofer ON Boleto.Chofer_IdChofer = Chofer.IdChofer ";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            lista = new ArrayList();
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
                lista.add(bole);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
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
}
