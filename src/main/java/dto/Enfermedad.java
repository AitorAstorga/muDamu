package dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "enfermedad_preID", "prediccionID", "nombre", "categoriaID", "categoria", "probabilidad" })
public class Enfermedad implements Serializable {
	private static final long serialVersionUID = 1671417246199538663L;

	private Integer enfermedad_preID;
	private Integer prediccionID;
	private String nombre;
	private Integer categoriaID;
	String categoria;
	private Float probabilidad;

	public Enfermedad() {
		super();
	}

	public Enfermedad(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Enfermedad(Integer enfermedad_preID, Integer prediccionID, String nombre, Integer categoriaID,
			String categoria, Float probabilidad) {
		super();
		this.enfermedad_preID = enfermedad_preID;
		this.prediccionID = prediccionID;
		this.nombre = nombre;
		this.categoriaID = categoriaID;
		this.categoria = categoria;
		this.probabilidad = probabilidad;

	}

	public Integer getEnfermedad_preID() {
		return enfermedad_preID;
	}

	public void setEnfermedad_preID(Integer enfermedad_preID) {
		this.enfermedad_preID = enfermedad_preID;
	}

	public Integer getPrediccionID() {
		return prediccionID;
	}

	public void setPrediccionID(Integer prediccionID) {
		this.prediccionID = prediccionID;
	}

	public Float getProbabilidad() {
		return probabilidad;
	}

	public void setProbabilidad(Float probabilidad) {
		this.probabilidad = probabilidad;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCategoriaID() {
		return categoriaID;
	}

	public void setCategoriaID(Integer categoriaID) {
		this.categoriaID = categoriaID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "enfermedadID: " + enfermedad_preID + 
				"prediccionID: " + prediccionID +
				" nombre: " + nombre + " categoriaID: " + categoriaID + 
				"categoria: " + categoria + "probabilidad: " + probabilidad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((enfermedad_preID == null) ? 0 : enfermedad_preID.hashCode());
		result = prime * result + ((prediccionID == null) ? 0 : prediccionID.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((categoriaID == null) ? 0 : categoriaID.hashCode());
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((probabilidad == null) ? 0 : probabilidad.hashCode());

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

		Enfermedad other = (Enfermedad) obj;
		if (enfermedad_preID == null) {
			if (other.enfermedad_preID != null)
				return false;
		} else if (!enfermedad_preID.equals(other.enfermedad_preID))
			return false;
		
		if (prediccionID == null) {
			if (other.prediccionID != null)
				return false;
		} else if (!prediccionID.equals(other.prediccionID))
			return false;

		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;

		if (categoriaID == null) {
			if (other.categoriaID != null)
				return false;
		} else if (!categoriaID.equals(other.categoriaID))
			return false;
		
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		
		if (probabilidad == null) {
			if (other.probabilidad != null)
				return false;
		} else if (!probabilidad.equals(other.probabilidad))
			return false;

		return true;
	}
}