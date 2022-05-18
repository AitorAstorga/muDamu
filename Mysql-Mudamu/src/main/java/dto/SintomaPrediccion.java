package dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "sintomaID", "nombre", "prediccionID" })
public class SintomaPrediccion implements Serializable {
	private static final long serialVersionUID = 1671417246199538663L;

	private Integer sintomaID;
	private String nombre;
	private Integer prediccionID;

	public SintomaPrediccion() {
		super();
	}

	public SintomaPrediccion(String nombre) {
		super();
		this.nombre = nombre;
	}

	public SintomaPrediccion(Integer sintomaID, String nombre, Integer prediccionID) {
		super();
		this.sintomaID = sintomaID;
		this.nombre = nombre;
		this.prediccionID = prediccionID;
	}

	public Integer getSintomaID() {
		return sintomaID;
	}

	public void setSintomaID(Integer sintomaID) {
		this.sintomaID = sintomaID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getPrediccionID() {
		return prediccionID;
	}

	public void setPrediccionID(Integer prediccionID) {
		this.prediccionID = prediccionID;
	}

	@Override
	public String toString() {
		return "enfermedadID: " + sintomaID + " nombre: " + nombre + " prediccionID: " + prediccionID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sintomaID == null) ? 0 : sintomaID.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((prediccionID == null) ? 0 : prediccionID.hashCode());

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

		SintomaPrediccion other = (SintomaPrediccion) obj;
		if (sintomaID == null) {
			if (other.sintomaID != null)
				return false;
		} else if (!sintomaID.equals(other.sintomaID))
			return false;

		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		
		if (prediccionID == null) {
			if (other.prediccionID != null)
				return false;
		} else if (!prediccionID.equals(other.prediccionID))
			return false;

		return true;
	}

}
