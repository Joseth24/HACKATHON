package DAO;

import Modelo.AsistenteM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AsistenteD extends DAO {

    public void registrar(AsistenteM asis) throws Exception {
        try {
            this.Conexion();
            String sql = "SP_ASISTENTE_ADD ?,?,?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, asis.getNombre());
            st.setString(2, asis.getApellido());
            st.setString(3, asis.getCelular());
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
            String sql = "SP_ASISTENTE_DELETE";
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
            String sql = "SP_ASISTENTE_UPDATE ?,?,?,?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, asis.getCodigo());
            st.setString(2, asis.getNombre());
            st.setString(3, asis.getApellido());
            st.setString(4, asis.getCelular());
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
            String sql = "SELECT * FROM Asistente";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            Lista = new ArrayList();
            while(rs.next()){
                AsistenteM Asis = new AsistenteM();
                Asis.setCodigo(rs.getString("IdAsistente"));
                Asis.setNombre(rs.getString("NombreAsistente"));
                Asis.setApellido(rs.getString("ApellidoAsistente"));
                Asis.setCelular(rs.getString("CelularAsistente"));
                Lista.add(Asis);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return Lista;
    }

}
