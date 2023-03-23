package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.jersey.api.NotFoundException;

import config.MySQLConfig;
import dto.Enfermedad;
import dto.Enfermedades;


public class EnfermedadDao {
	private MySQLConfig mysqlConfig;

	public EnfermedadDao() {
		mysqlConfig = MySQLConfig.getInstance();
	}
	
	public Enfermedades createValueObject() {
		return new Enfermedades();
	}
	
	public Enfermedades getObject(int predID) {
		Connection conn = mysqlConfig.connect();
		Enfermedades valueObject = createValueObject();
		loadEnf(valueObject, predID);
		return valueObject;
	}
	
	public void loadEnf(Enfermedades valueObject, int predId) {
		Connection conn = mysqlConfig.connect();

		String sql = "SELECT enfermedades_predicciones.enfermedad_preID, enfermedades_predicciones.prediccionID, enfermedades.nombre, categorias.categoriaID, categorias.nombre as categoria, enfermedades_predicciones.probabilidad FROM \r\n"
				+ "enfermedades JOIN enfermedades_predicciones ON enfermedades.enfermedadID  = enfermedades_predicciones.enfermedadID\r\n"
				+ "JOIN categorias ON categorias.categoriaID = enfermedades.urgenciaID WHERE enfermedades_predicciones.prediccionID = ?";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, predId);

			multipleQueryEnf(conn, stmt, valueObject);
		} catch (NotFoundException | SQLException e) {
			Logger l = Logger.getLogger(e.getMessage());
			l.log(Level.SEVERE, "context", e);

		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					Logger l = Logger.getLogger(e.getMessage());
					l.log(Level.SEVERE, "context", e);
				}
		}
	}
	
	
	protected void multipleQueryEnf(Connection conn, PreparedStatement stmt, Enfermedades valueObject)
			throws NotFoundException, SQLException{

		ResultSet result = null;

		try {
			result = stmt.executeQuery();

			while (result.next()) {
				Enfermedad enfermedad = new Enfermedad();
				enfermedad.setEnfermedad_preID(result.getInt("enfermedad_preID"));
				enfermedad.setPrediccionID(result.getInt("prediccionID"));
				enfermedad.setNombre(result.getString("nombre"));
				enfermedad.setCategoriaID(result.getInt("categoriaID"));
				enfermedad.setCategoria(result.getString("categoria"));
				enfermedad.setProbabilidad(result.getFloat("probabilidad"));
				
				valueObject.añadir(enfermedad);

			} if (result == null) {
				throw new NotFoundException("Enfermedad Object Not Found!");
			}
		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
		}
	}
	
}
