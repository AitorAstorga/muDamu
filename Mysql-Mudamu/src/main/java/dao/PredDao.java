package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.jersey.api.NotFoundException;

import config.MySQLConfig;
import dto.Prediccion;
import dto.Predicciones;

public class PredDao {
	
	private MySQLConfig mysqlConfig;

	public PredDao() {
		mysqlConfig = MySQLConfig.getInstance();
	}
	
	public Predicciones createValueObject() {
		return new Predicciones();
	}
	
	public Predicciones getObject(int medicoID) {
		Connection conn = mysqlConfig.connect();
		Predicciones valueObject = createValueObject();
		loadPred(valueObject, medicoID);
		return valueObject;
	}
	
	public void setObject(int prediccionID, int categoriaID) {
		Connection conn = mysqlConfig.connect();
		Predicciones valueObject = createValueObject();
		setPred(valueObject, prediccionID, categoriaID);
	}
	
	public void loadPred(Predicciones valueObject, int medicoID) {
		Connection conn = mysqlConfig.connect();

		String sql = "SELECT predicciones.prediccionID, tarjeta_sanitaria.nombre, tarjeta_sanitaria.apellido1, tarjeta_sanitaria.apellido2, predicciones.fecha_hora, categorias.categoriaID, categorias.nombre as nombreCategoria, predicciones.medicoID as medicoID , predicciones.citaSolicitada as citaSolicitada FROM mudamu.predicciones\n"
				+ "	JOIN\n"
				+ "pacientes ON predicciones.pacienteID = pacientes.pacienteID\n"
				+ "	JOIN tarjeta_sanitaria ON pacientes.tarjetaSanitaria = tarjeta_sanitaria.tarjetaSanitaria\n"
				+ "	JOIN categorias ON predicciones.categoriaID = categorias.categoriaID WHERE (predicciones.medicoID = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, medicoID);

			multipleQueryPred(conn, stmt, valueObject);
		} catch (SQLException e) {
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
	
	public void setPred(Predicciones valueObject, int prediccionID, int categoriaID) {
		Connection conn = mysqlConfig.connect();

		String sql = "update predicciones set citaSolicitada=1, categoriaID=? where prediccionID = ?";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, categoriaID);
			stmt.setInt(2, prediccionID);

			int result = stmt.executeUpdate();		
			
		} catch (SQLException e) {
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
	
	protected void multipleQueryPred(Connection conn, PreparedStatement stmt, Predicciones valueObject)
			throws NotFoundException, SQLException {

		ResultSet result = null;

		try {
			result = stmt.executeQuery();

			while (result.next()) {
				Prediccion prediccion = new Prediccion();
				
				prediccion.setPrediccionID(Integer.parseInt(result.getString("prediccionID")));
				prediccion.setNombre(result.getString("nombre"));
				prediccion.setApellido1(result.getString("apellido1"));
				prediccion.setApellido2(result.getString("apellido2"));
				prediccion.setCategoriaID(Integer.parseInt(result.getString("categoriaID")));
				prediccion.setNombreCategoria(result.getString("nombreCategoria"));
				prediccion.setFecha_hora(result.getString("fecha_hora"));
				
				prediccion.setMedicoID(result.getInt("medicoID"));
				prediccion.setCitaSolicitada(result.getInt("citaSolicitada"));
				
				valueObject.añadir(prediccion);
			} if (result==null) {
				// System.out.println("User Object Not Found!");
				throw new NotFoundException("Prediccion Object Not Found!");
			}
		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
		}
	}
}
