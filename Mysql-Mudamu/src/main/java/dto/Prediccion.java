package dto;

import java.io.Serializable;
import java.sql.Timestamp;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlType(propOrder = {"prediccionID","pacienteID", "categoriaID", "fecha_hora", "medicoID"})
public class Prediccion implements Serializable{	
	private static final long serialVersionUID = 1671417246199538663L;

	private Integer prediccionID;
	private Integer pacienteID;
	private Integer categoriaID;
	private Timestamp fecha_hora;
	private Integer medicoID;

	public Prediccion() {
		super();
	}

	public Prediccion(Integer prediccionID) {
		super();
		this.prediccionID = prediccionID;
	}

	public Prediccion(Integer prediccionID,Integer pacienteID, Integer categoriaID, Timestamp fecha_hora,
			Integer medicoID)
    {
        this.prediccionID = prediccionID;
        this.pacienteID = pacienteID;
		this.categoriaID = categoriaID;
        this.fecha_hora = fecha_hora;
        this.medicoID = medicoID;
    }

	public Integer getprediccionID() {
		return prediccionID;
	}

	public void setprediccionID(Integer prediccionID) {
		this.prediccionID = prediccionID;
	}

	public Integer getPacienteID(){
		return pacienteID;
	}

	public void setPacienteID(Integer pacienteID) {
		this.pacienteID = pacienteID;
	}
	
	public Integer getCategoriaID() {
		return categoriaID;
	}

	public void setCategoriaID(Integer categoriaID) {
		this.categoriaID = categoriaID;
	}
	
	public Timestamp getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(Timestamp fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	public int getMedicoID() {
		return medicoID;
	}

	public void setMedicoID(int medicoID) {
		this.medicoID = medicoID;
	}
	
	@Override
	public String toString() {
		return "prediccionID: "+prediccionID+"pacienteID: "+pacienteID+"categoriaID: "+categoriaID+"fecha_hora: "+fecha_hora+
				"medicoID: " + medicoID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prediccionID == null) ? 0 : prediccionID.hashCode());
		result = prime * result + ((pacienteID == null) ? 0 : pacienteID.hashCode());
		result = prime * result + ((categoriaID == null) ? 0 : categoriaID.hashCode());
		result = prime * result + ((fecha_hora == null) ? 0 : fecha_hora.hashCode());	
		result = prime * result + ((medicoID == null) ? 0 : medicoID.hashCode());	
		
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
		Prediccion other = (Prediccion) obj;
		if (prediccionID == null) {
			if (other.prediccionID != null)
				return false;
		} else if (!prediccionID.equals(other.prediccionID))
			return false;
		if (pacienteID == null) {
			if (other.pacienteID != null)
				return false;
		} else if (!pacienteID.equals(other.pacienteID))
			return false;
		if (categoriaID == null) {
			if (other.categoriaID != null)
				return false;
		} else if (!categoriaID.equals(other.categoriaID))
			return false;
		if (fecha_hora == null) {
			if (other.fecha_hora != null)
				return false;
		} else if (!fecha_hora.equals(other.fecha_hora))
			return false;
		if (medicoID == null) {
			if (other.medicoID != null)
				return false;
		} else if (!medicoID.equals(other.medicoID))
			return false;
		return true;
	}

}
