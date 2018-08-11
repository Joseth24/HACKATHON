package DAO;

import Modelo.UsuarioM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioD extends DAO {

    public void registrar(UsuarioM usuario) throws Exception {
        try {
            this.Conexion();
            String sql = "SP_ADD_USUARIO ?,?,?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, usuario.getNombre());
            st.setString(2, usuario.getApellido());
            st.setString(3, usuario.getCelular());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void Eliminar(UsuarioM user) throws Exception {
        try {
            this.Conexion();
            String sql = "delete from Usuario Where IdUsuario=?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, user.getIdUsuario());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void Modificar(UsuarioM user) throws Exception {
        try {
            this.Conexion();
            String sql = "SP_UPDATE_USUARIO ?,?,?,?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, user.getIdUsuario());
            st.setString(2, user.getNombre());
            st.setString(3, user.getApellido());
            st.setString(4, user.getCelular());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<UsuarioM> Listar() throws Exception {
        List<UsuarioM> Lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM Usuario ";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            Lista = new ArrayList();
            while (rs.next()) {
                UsuarioM user = new UsuarioM();
                user.setIdUsuario(rs.getString("IdUsuario"));
                user.setNombre(rs.getString("NombreUsuario"));
                user.setApellido(rs.getString("ApellidoUsuario"));
                user.setCelular(rs.getString("CelularUsuario"));
                Lista.add(user);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return Lista;
    }

    public UsuarioM LeerId(String Codigo) throws Exception {
        UsuarioM user = null;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT IdUsuario,NombreUsuario,ApellidoUsuario,CelularUsuario FROM Usuario WHERE IdUsuario=?";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, Codigo);
            rs = st.executeQuery();
            while (rs.next()) {
                user = new UsuarioM();
                user.setIdUsuario(rs.getString("IdUsuario"));
                user.setNombre(rs.getString("NombreUsuario"));
                user.setApellido(rs.getString("ApellidoUsuario"));
                user.setCelular(rs.getString("CelularUsuario"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return user;
    }

    public String obtenerCodigoUsuario(String usuario) throws SQLException {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "select IdUsuario from Usuario where concat(NombreUsuario,' ',ApellidoUsuario) like ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("IdUsuario");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }
}
