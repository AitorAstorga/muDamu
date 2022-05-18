package dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"sintomaID", "nombre"})
public class Sintoma implements Serializable {
	private static final long serialVersionUID = 1671417246199538663L;
	
	private Integer	sintomaID;
	private String	nombre;
	
	public Sintoma() {
		super();
	}

	public Sintoma(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public Sintoma(Integer sintomaID, String nombre) {
		super();
		this.sintomaID = sintomaID;
		this.nombre = nombre;
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
	

	@Override
	public String toString() {
		return "sintomaID: " + sintomaID + " nombre: " + nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sintomaID == null) ? 0 : sintomaID.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		
		Sintoma other = (Sintoma) obj;
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
		
		return true;
	}
	
}
