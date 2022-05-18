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

import config.MySQLConfig;
import dto.Prediccion;
import dto.Sintoma;
import dto.SintomaPrediccion;


public class SintomaDao {
	private MySQLConfig mysqlConfig;

	public SintomaDao() {
		mysqlConfig = MySQLConfig.getInstance();
	}
	
	public Sintoma createValueObject() {
		return new Sintoma();
	}
	
	public SintomaPrediccion createValueSintomaPredObject() {
		return new SintomaPrediccion();
	}
	
	public Sintoma getObject() {
		Connection conn = mysqlConfig.connect();
		Sintoma valueObject = createValueObject();
		loadAllSintoms(valueObject);
		return valueObject;
	}
	
	public SintomaPrediccion getObjectPacienteSintomas(int prediccionID) {
		Connection conn = mysqlConfig.connect();
		SintomaPrediccion valueObject = createValueSintomaPredObject();
		valueObject.setPrediccionID(prediccionID);
		loadSintomasPac(valueObject);
		return valueObject;
	}
	
	public void loadSintomasPac(SintomaPrediccion valueObject) {
		Connection conn = mysqlConfig.connect();

		String sql = "SELECT sintomas.sintomaID, sintomas.nombre FROM sintomas \r\n"
				+ "    JOIN sintomas_prediccion ON sintomas.sintomaID = sintomas_prediccion.sintomaID WHERE prediccionID = ?";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, valueObject.getPrediccionID());

			singleQuerySinPred(conn, stmt, valueObject);
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
	
	public void loadAllSintoms(Sintoma valueObject) {
		Connection conn = mysqlConfig.connect();

		String sql = "SELECT * FROM mudamu.sintomas";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);

			singleQuerySin(conn, stmt, valueObject);
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
	
	
	protected void singleQuerySin(Connection conn, PreparedStatement stmt, Sintoma valueObject)
			throws Exception {

		ResultSet result = null;

		try {
			result = stmt.executeQuery();

			if (result.next()) {
				valueObject.setSintomaID(result.getInt("sintomaID"));
				valueObject.setNombre(result.getString("nombre"));
			} else {
				throw new Exception("Sintoma Object Not Found!");
			}
		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
		}
	}
	
	protected void singleQuerySinPred(Connection conn, PreparedStatement stmt, SintomaPrediccion valueObject)
			throws Exception {

		ResultSet result = null;

		try {
			result = stmt.executeQuery();

			if (result.next()) {
				
				valueObject.setSintomaID(result.getInt("sintomaID"));
				valueObject.setNombre(result.getString("nombre"));
				valueObject.setPrediccionID(result.getInt("prediccionID"));
				
			} else {
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
