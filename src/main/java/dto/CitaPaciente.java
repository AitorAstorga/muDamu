package dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "citaID", "fecha_hora"})
public class CitaPaciente implements Serializable {
	private static final long serialVersionUID = 1671417246199538663L;

	private Integer citaID;
	private String fecha_hora;
	

	public CitaPaciente() {
		super();
	}

	public CitaPaciente(Integer citaID) {
		super();
		this.citaID = citaID;
	}

	public CitaPaciente(Integer citaID, String fecha_hora) {
		super();
		this.citaID = citaID;
		this.fecha_hora = fecha_hora;
	}
	
	public Integer getCitaID() {
		return citaID;
	}

	public void setCitaID(Integer citaID) {
		this.citaID = citaID;
	}

	public String getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(String fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "citaID: " + citaID + " fecha_hora: " + fecha_hora;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((citaID == null) ? 0 : citaID.hashCode());
		result = prime * result + ((fecha_hora == null) ? 0 : fecha_hora.hashCode());
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
		
		CitaPaciente other = (CitaPaciente) obj;
		if (citaID == null) {
			if (other.citaID != null)
				return false;
		} else if (!citaID.equals(other.citaID))
			return false;
		
		if (fecha_hora == null) {
			if (other.fecha_hora != null)
				return false;
		} else if (!fecha_hora.equals(other.fecha_hora))
			return false;
		
		return true;
	}

}