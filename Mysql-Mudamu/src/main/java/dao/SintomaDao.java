package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import config.MySQLConfig;
import dto.Sintoma;
import dto.Sintomas;
import dto.SintomasPrediccion;
import dto.SintomasPredicciones;

// "sintomaID", "nombre"
public class SintomaDao {
	final static int NUMERO_SINTOMAS = 218; // Provisional: getNumSintomas
	
	private MySQLConfig mysqlConfig;

	public SintomaDao() {
		mysqlConfig = MySQLConfig.getInstance();
	}
	
	public Sintomas createValueObject() {
		return new Sintomas();
	}
	
	public SintomasPredicciones createValueSintomaPredObject() {
		return new SintomasPredicciones();
	}
	
	public Sintomas getObject() {
		Connection conn = mysqlConfig.connect();
		Sintomas valueObject = createValueObject();
		loadAllSintoms(valueObject);

		return valueObject;
	}
	
	public SintomasPredicciones getObjectPacienteSintomas(int prediccionID) {
		Connection conn = mysqlConfig.connect();
		SintomasPredicciones valueObject = createValueSintomaPredObject();
		loadSintomasPac(valueObject, prediccionID);
		return valueObject;
	}
	
	public void loadSintomasPac(SintomasPredicciones valueObject, int prediccionID) {
		Connection conn = mysqlConfig.connect();

		String sql = "SELECT sintomas.sintomaID, sintomas.nombre, sintomas_prediccion.prediccionID FROM sintomas \r\n"
				+ "    JOIN sintomas_prediccion ON sintomas.sintomaID = sintomas_prediccion.sintomaID WHERE sintomas_prediccion.prediccionID = ?";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prediccionID);

			multipleQuerySinPred(conn, stmt, valueObject);
		} catch (Exception e) {
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
	
	public void loadAllSintoms(Sintomas valueObject) {
		Connection conn = mysqlConfig.connect();

		String sql = "SELECT * FROM mudamu.sintomas";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);

			multipleQuerySin(conn, stmt, valueObject);
		} catch (Exception e) {
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
	
	
	protected void multipleQuerySin(Connection conn, PreparedStatement stmt, Sintomas valueObject)
			throws Exception {

		ResultSet result = null;

		try {
			result = stmt.executeQuery();

			while (result.next()) {
				Sintoma sintoma = new Sintoma();
				
				sintoma.setSintomaID(result.getInt("sintomaID"));
				sintoma.setNombre(result.getString("nombre"));
				
				valueObject.añadir(sintoma);
			} if(result == null) {
				throw new Exception("Sintomas Object Not Found!");
			}
		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
		}
	}
	
	protected void multipleQuerySinPred(Connection conn, PreparedStatement stmt, SintomasPredicciones valueObject)
			throws Exception {

		ResultSet result = null;

		try {
			result = stmt.executeQuery();

			while (result.next()) {
				SintomasPrediccion sintomas = new SintomasPrediccion();
				sintomas.setSintomaID(result.getInt("sintomaID"));
				sintomas.setNombre(result.getString("nombre"));
				sintomas.setPrediccionID(result.getInt("prediccionID"));
				
				valueObject.añadir(sintomas);
			} if(result==null) {
				throw new Exception("Sintoma paciente Object Not Found!");
			}
		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
		}
	}
	
}
