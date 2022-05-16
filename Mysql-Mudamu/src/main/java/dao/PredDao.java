package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.jersey.api.NotFoundException;

import config.MySQLConfig;
import dto.Prediccion;
import dto.User;

public class PredDao {
	
	private MySQLConfig mysqlConfig;

	public PredDao() {
		mysqlConfig = MySQLConfig.getInstance();
	}
	
	public Prediccion createValueObject() {
		return new Prediccion();
	}
	
	public Prediccion getObject(int medicoID) {
		Connection conn = mysqlConfig.connect();
		Prediccion valueObject = createValueObject();
		valueObject.setMedicoID(medicoID);
		loadPred(valueObject);
		return valueObject;
	}
	
	public void loadPred(Prediccion valueObject) {
		Connection conn = mysqlConfig.connect();

		String sql = "SELECT * FROM predicciones WHERE (medicoID = ? ) ";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, valueObject.getMedicoID());

			singleQueryPred(conn, stmt, valueObject);
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
	
	
	protected void singleQueryPred(Connection conn, PreparedStatement stmt, Prediccion valueObject)
			throws NotFoundException, SQLException {

		ResultSet result = null;

		try {
			result = stmt.executeQuery();

			if (result.next()) {

				valueObject.setprediccionID(Integer.parseInt(result.getString("prediccionID")));
				valueObject.setPacienteID(Integer.parseInt(result.getString("pacienteID")));
				valueObject.setCategoriaID(Integer.parseInt(result.getString("categoriaID")));

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
				Date parsedDate;
				try {
					parsedDate = dateFormat.parse(result.getString("fecha_hora"));
					Timestamp timestamp = new Timestamp(parsedDate.getTime());
					valueObject.setFecha_hora(timestamp);

				} catch (ParseException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				valueObject.setMedicoID(Integer.parseInt(result.getString("medicoID")));
			} else {
				// System.out.println("User Object Not Found!");
				throw new NotFoundException("Medico Object Not Found!");
			}
		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
		}
	}


}
