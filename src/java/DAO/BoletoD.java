package DAO;

import Modelo.AsistenteM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AsistenteD extends DAO {

    public void registrar(AsistenteM asistente) throws Exception {
        try {
            this.Conexion();
            String sql = "INSERT INTO Asistente (NombreAsistente, ApellidoAsistente, CelularAsistente) VALUES(?,?,?)";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, asistente.getNombre());
            st.setString(2, asistente.getApellido());
            st.setString(3, asistente.getCelular());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void Eliminar(AsistenteM asis) throws Exception {
        try {
            this.Conexion();
            String sql = "delete from Asistente Where IdAsistente=?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, asis.getCodigo());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void Modificar(AsistenteM asis) throws Exception {
        try {
            this.Conexion();
            String sql = "SP_ACTUALIZAR_ASISTENTE ?,?,?,?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, asis.getCodigo());
            st.setString(2, asis.getNombre());
            st.setString(3, asis.getApellido());
            st.setString(4, asis.getCelular());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<AsistenteM> Listar() throws Exception {
        List<AsistenteM> Lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM Asistente ";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            Lista = new ArrayList();
            while (rs.next()) {
                AsistenteM asis = new AsistenteM();
                asis.setCodigo(rs.getString("IdAsistente"));
                asis.setNombre(rs.getString("NombreAsistente"));
                asis.setApellido(rs.getString("ApellidoAsistente"));
                asis.setCelular(rs.getString("CelularAsistente"));
                Lista.add(asis);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return Lista;
    }

    public AsistenteM LeerId(String Codigo) throws Exception {
        AsistenteM asis = null;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT IdAsistente,NombreAsistente,ApellidoAsistente,CelularAsistente FROM Asistente WHERE IdAsistente=?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, Codigo);
            rs = st.executeQuery();
            while (rs.next()) {
                asis = new AsistenteM();
                asis.setCodigo(rs.getString("IdAsistente"));
                asis.setNombre(rs.getString("NombreAsistente"));
                asis.setApellido(rs.getString("ApellidoAsistente"));
                asis.setCelular(rs.getString("CelularAsistente"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return asis;
    }

}
