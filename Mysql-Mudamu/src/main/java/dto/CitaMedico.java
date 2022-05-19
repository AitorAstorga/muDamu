package dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "citaID", "nombre", "apellido1", "apellido2", "fecha_hora", "categoriaID",
		"nombreCategoria"})
public class CitaMedico implements Serializable {
	private static final long serialVersionUID = 1671417246199538663L;

	private Integer citaID;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String fecha_hora;
	private Integer categoriaID;
	private String nombreCategoria;

	public CitaMedico() {
		super();
	}

	public CitaMedico(Integer citaID) {
		super();
		this.citaID = citaID;
	}

	public CitaMedico(Integer citaID, String nombre, String apellido1, String apellido2, String fecha_hora,
			Integer categoriaID, String nombreCategoria) {
		super();
		this.citaID = citaID;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fecha_hora = fecha_hora;
		this.categoriaID = categoriaID;
		this.nombreCategoria = nombreCategoria;
	}
	
	public Integer getCitaID() {
		return citaID;
	}

	public void setCitaID(Integer citaID) {
		this.citaID = citaID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(String fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	public Integer getCategoriaID() {
		return categoriaID;
	}

	public void setCategoriaID(Integer categoriaID) {
		this.categoriaID = categoriaID;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "citaID: " + citaID + " nombre: " + nombre + " apellido1: " + apellido1 + " apellido2: "
				+ apellido2 + " fecha_hora: " + fecha_hora + " categoriaID: " + categoriaID + " nombreCategoria: "
				+ nombreCategoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((citaID == null) ? 0 : citaID.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((apellido1 == null) ? 0 : apellido1.hashCode());
		result = prime * result + ((apellido2 == null) ? 0 : apellido2.hashCode());
		result = prime * result + ((fecha_hora == null) ? 0 : fecha_hora.hashCode());
		result = prime * result + ((categoriaID == null) ? 0 : categoriaID.hashCode());
		result = prime * result + ((nombreCategoria == null) ? 0 : nombreCategoria.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		CitaMedico other = (CitaMedico) obj;
		if (citaID == null) {
			if (other.citaID != null)
				return false;
		} else if (!citaID.equals(other.citaID))
			return false;
		
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		
		if (apellido1 == null) {
			if (other.apellido1 != null)
				return false;
		} else if (!apellido1.equals(other.apellido1))
			return false;
		
		if (apellido2 == null) {
			if (other.apellido2 != null)
				return false;
		} else if (!apellido2.equals(other.apellido2))
			return false;
		
		if (fecha_hora == null) {
			if (other.fecha_hora != null)
				return false;
		} else if (!fecha_hora.equals(other.fecha_hora))
			return false;
		
		if (categoriaID == null) {
			if (other.categoriaID != null)
				return false;
		} else if (!categoriaID.equals(other.categoriaID))
			return false;
		
		if (nombreCategoria == null) {
			if (other.nombreCategoria != null)
				return false;
		} else if (!nombreCategoria.equals(other.nombreCategoria))
			return false;
		
		return true;
	}

}
