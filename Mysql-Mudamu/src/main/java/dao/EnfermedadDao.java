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


public class EnfermedadDao {
	private MySQLConfig mysqlConfig;

	public EnfermedadDao() {
		mysqlConfig = MySQLConfig.getInstance();
	}
	
	public Enfermedad createValueObject() {
		return new Enfermedad();
	}
	
	public Enfermedad getObject(int predID) {
		Connection conn = mysqlConfig.connect();
		Enfermedad valueObject = createValueObject();
		valueObject.setPrediccionID(predID);
		loadEnf(valueObject);
		return valueObject;
	}
	
	public void loadEnf(Enfermedad valueObject) {
		Connection conn = mysqlConfig.connect();

		String sql = "SELECT enfermedades_predicciones.enfermedad_preID, enfermedades_predicciones.prediccionID, enfermedades.nombre, categorias.categoriaID, categorias.nombre as categoria, enfermedades_predicciones.probabilidad FROM \r\n"
				+ "enfermedades JOIN enfermedades_predicciones ON enfermedades.enfermedadID  = enfermedades_predicciones.enfermedadID\r\n"
				+ "JOIN categorias ON categorias.categoriaID = enfermedades.urgenciaID WHERE enfermedades_predicciones.prediccionID = ?";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, valueObject.getPrediccionID());

			singleQueryEnf(conn, stmt, valueObject);
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
	
	
	protected void singleQueryEnf(Connection conn, PreparedStatement stmt, Enfermedad valueObject)
			throws NotFoundException, SQLException{

		ResultSet result = null;

		try {
			result = stmt.executeQuery();

			if (result.next()) {
				valueObject.setEnfermedad_preID(result.getInt("enfermedad_preID"));
				valueObject.setPrediccionID(result.getInt("prediccionID"));
				valueObject.setNombre(result.getString("nombre"));
				valueObject.setCategoriaID(result.getInt("categoriaID"));
				valueObject.setCategoria(result.getString("categoria"));
				valueObject.setProbabilidad(result.getFloat("probabilidad"));

			} else {
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
