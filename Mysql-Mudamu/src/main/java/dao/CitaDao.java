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
import dto.CitaMedico;
import dto.CitaPaciente;
import dto.CitasMedico;
import dto.CitasPaciente;
import dto.Prediccion;
import dto.User;

public class CitaDao {

	private MySQLConfig mysqlConfig;

	public CitaDao() {
		mysqlConfig = MySQLConfig.getInstance();
	}

	public CitasMedico createValueObjectMedico() {
		return new CitasMedico();
	}

	public CitasMedico getObjectMedico(int citaID) {
		Connection conn = mysqlConfig.connect();
		CitasMedico valueObject = createValueObjectMedico();
		loadCitaMedicos(valueObject, citaID);
		return valueObject;
	}

	public void loadCitaMedicos(CitasMedico valueObject, int citaID) {
		Connection conn = mysqlConfig.connect();

		String sql = "select  citas.citaID ,tarjeta_sanitaria.nombre,tarjeta_sanitaria.apellido1 \n"
				+ ",tarjeta_sanitaria.apellido2  , citas.fecha_hora , categorias.categoriaID, categorias.nombre\n"
				+ "from citas join trabajadores t1 on t1.trabajadorID  = citas.medicoID\n"
				+ "            join tarjeta_sanitaria on t1.trabajadorID =tarjeta_sanitaria.medicoID\n"
				+ "            join trabajadores t2 on t2.trabajadorID = tarjeta_sanitaria.medicoID\n"
				+ "            join predicciones on predicciones.medicoID = t2.trabajadorID\n"
				+ "            join categorias on predicciones.categoriaID = categorias.categoriaID WHERE citas.medicoID = ?";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, citaID);

			multipleQueryCitasMedico(conn, stmt, valueObject);
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

	protected void multipleQueryCitasMedico(Connection conn, PreparedStatement stmt, CitasMedico valueObject)
			throws NotFoundException, SQLException {

		ResultSet result = null;

		try {
			result = stmt.executeQuery();

			while (result.next()) {
				CitaMedico citaMed = new CitaMedico();
				citaMed.setCitaID(result.getInt("citaID"));
				citaMed.setNombre(result.getString("nombre"));
				citaMed.setApellido1(result.getString("apellido1"));
				citaMed.setApellido2(result.getString("apellido2"));
				citaMed.setFecha_hora(result.getString("fecha_hora"));
				citaMed.setCategoriaID(result.getInt("categoriaID"));
				citaMed.setNombreCategoria(result.getString("nombre"));

				valueObject.añadir(citaMed);

			}
			if (result == null) {
				throw new NotFoundException("Enfermedad Object Not Found!");
			}
		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
		}
	}

	public CitasPaciente CitasPaciente() {
		return new CitasPaciente();
	}
	public CitasPaciente createValueObjectPaciente() {
		return new CitasPaciente();
	}

	public CitasPaciente getObjectPaciente(int citaID) {
		Connection conn = mysqlConfig.connect();
		CitasPaciente valueObject = createValueObjectPaciente();
		loadCitaPaciente(valueObject, citaID);
		return valueObject;
	}

	public void loadCitaPaciente(CitasPaciente valueObject, int citaID) {
		Connection conn = mysqlConfig.connect();

		String sql = "select citas.citaID , citas.fecha_hora \n"
				+ "from citas join pacientes on pacientes.pacienteID  = citas.pacienteID WHERE citas.pacienteID = ?";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, citaID);

			multipleQueryCitasPaciente(conn, stmt, valueObject);
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

	protected void multipleQueryCitasPaciente(Connection conn, PreparedStatement stmt, CitasPaciente valueObject)
			throws NotFoundException, SQLException {

		ResultSet result = null;

		try {
			result = stmt.executeQuery();

			while (result.next()) {
				CitaPaciente citaPac = new CitaPaciente();
				citaPac.setCitaID(result.getInt("citaID"));
				citaPac.setFecha_hora(result.getString("fecha_hora"));

				valueObject.añadir(citaPac);

			}
			if (result == null) {
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
