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
	
	public CitaMedico createValueObject() {
		return new CitaMedico();
	}

	public CitasMedico getObjectMedico(int citaID) {
		Connection conn = mysqlConfig.connect();
		CitasMedico valueObject = createValueObjectMedico();
		loadCitaMedicos(valueObject, citaID);
		return valueObject;
	}

	public void loadCitaMedicos(CitasMedico valueObject, int citaID) {
		Connection conn = mysqlConfig.connect();

		String sql = "SELECT citas.citaID, pacientes.tarjetaSanitaria,  tarjeta_sanitaria.nombre AS nombrePaciente, \r\n"
				+ "			tarjeta_sanitaria.apellido1, tarjeta_sanitaria.apellido2, citas.fecha_hora AS fechaCita, categorias.categoriaID AS categoriaId,\r\n"
				+ "			 categorias.nombre AS categoriaNombre\r\n"
				+ "FROM predicciones \r\n"
				+ "JOIN pacientes ON predicciones.pacienteID = pacientes.pacienteID\r\n"
				+ "JOIN tarjeta_sanitaria ON pacientes.tarjetaSanitaria = tarjeta_sanitaria.tarjetaSanitaria\r\n"
				+ "JOIN citas ON predicciones.prediccionID = citas.prediccionID\r\n"
				+ "JOIN categorias ON predicciones.categoriaID = categorias.categoriaID\r\n"
				+ "WHERE tarjeta_sanitaria.medicoID = ?";
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
				citaMed.setTarjetaSanitaria(result.getString("pacientes.tarjetaSanitaria"));
				citaMed.setNombre(result.getString("nombrePaciente"));
				citaMed.setApellido1(result.getString("apellido1"));
				citaMed.setApellido2(result.getString("apellido2"));
				citaMed.setFecha_hora(result.getString("fechaCita"));
				citaMed.setCategoriaID(result.getInt("categoriaId"));
				citaMed.setNombreCategoria(result.getString("categoriaNombre"));
				citaMed.setPrediccionID(result.getInt("prediccionID"));

				valueObject.añadir(citaMed);

			}
			if (result == null) {
				throw new NotFoundException("Cita Object Not Found!");
			}
		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
		}
	}

	/******************************************************************************************/
	/******************************************************************************************/
	/******************************************************************************************/
	/******************************************************************************************/

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

	/******************************************************************************************/
	/******************************************************************************************/
	/******************************************************************************************/
	/******************************************************************************************/
	public CitasMedico getObjectAdministrador() {
		Connection conn = mysqlConfig.connect();
		CitasMedico valueObject = createValueObjectMedico();
		loadCitaAdministrador(valueObject);
		return valueObject;
	}

	public CitasMedico getObjectNewCitas() {
		Connection conn = mysqlConfig.connect();
		CitasMedico valueObject = createValueObjectMedico();
		loadNewCitas(valueObject);
		return valueObject;
	}
	
	public void insertNewCita(int prediccionID, String fecha_hora, int pacienteID) {
		Connection conn = mysqlConfig.connect();
		CitaMedico valueObject = createValueObject();
		createNewCita(valueObject, prediccionID, fecha_hora, pacienteID);
	}

	private void createNewCita(CitaMedico valueObject, int prediccionID, 
			String fecha_hora, int pacienteID) {
		Connection conn = mysqlConfig.connect();

		String sql = "INSERT INTO citas (prediccionID,fecha_hora,medicoID,pacienteID) VALUES (?,?,\r\n"
				+ "    (SELECT medicoID FROM tarjeta_sanitaria JOIN pacientes ON tarjeta_sanitaria.tarjetaSanitaria = pacientes.tarjetaSanitaria\r\n"
				+ "    WHERE pacientes.pacienteID = ?), ?);";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prediccionID);
			stmt.setString(2, fecha_hora);
			stmt.setInt(3, pacienteID);
			stmt.setInt(4, pacienteID);

			int result = stmt.executeUpdate();
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

	public void loadNewCitas(CitasMedico valueObject) {
		Connection conn = mysqlConfig.connect();

		String sql = "SELECT citas.citaID, pacientes.tarjetaSanitaria,  tarjeta_sanitaria.nombre AS nombrePaciente, tarjeta_sanitaria.apellido1, tarjeta_sanitaria.apellido2, citas.fecha_hora AS fechaCita, categorias.categoriaID AS categoriaId, categorias.nombre AS categoriaNombre, predicciones.prediccionID FROM predicciones JOIN pacientes ON predicciones.pacienteID = pacientes.pacienteID JOIN tarjeta_sanitaria ON pacientes.tarjetaSanitaria = tarjeta_sanitaria.tarjetaSanitaria JOIN citas ON predicciones.prediccionID = citas.prediccionID JOIN categorias ON predicciones.categoriaID = categorias.categoriaID where predicciones.citaSolicitada = 1;";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);

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

	public void loadCitaAdministrador(CitasMedico valueObject) {
		Connection conn = mysqlConfig.connect();

		String sql = "SELECT citas.citaID, pacientes.tarjetaSanitaria,  tarjeta_sanitaria.nombre AS nombrePaciente, \r\n"
				+ "			tarjeta_sanitaria.apellido1, tarjeta_sanitaria.apellido2, citas.fecha_hora AS fechaCita, categorias.categoriaID AS categoriaId,\r\n"
				+ "			 categorias.nombre AS categoriaNombre\r\n"
				+ "FROM predicciones \r\n"
				+ "JOIN pacientes ON predicciones.pacienteID = pacientes.pacienteID\r\n"
				+ "JOIN tarjeta_sanitaria ON pacientes.tarjetaSanitaria = tarjeta_sanitaria.tarjetaSanitaria\r\n"
				+ "JOIN citas ON predicciones.prediccionID = citas.prediccionID\r\n"
				+ "JOIN categorias ON predicciones.categoriaID = categorias.categoriaID;";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);

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

	protected void multipleQueryCitasAdministrador(Connection conn, PreparedStatement stmt, CitasMedico valueObject)
			throws NotFoundException, SQLException {

		ResultSet result = null;

		try {
			result = stmt.executeQuery();

			while (result.next()) {
				CitaMedico citaMed = new CitaMedico();
				citaMed.setCitaID(result.getInt("citaID"));
				citaMed.setTarjetaSanitaria(result.getString("pacientes.tarjetaSanitaria"));
				citaMed.setNombre(result.getString("nombrePaciente"));
				citaMed.setApellido1(result.getString("apellido1"));
				citaMed.setApellido2(result.getString("apellido2"));
				citaMed.setFecha_hora(result.getString("fechaCita"));
				citaMed.setCategoriaID(result.getInt("categoriaId"));
				citaMed.setNombreCategoria(result.getString("categoriaNombre"));

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
	
}
