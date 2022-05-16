package dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlType(propOrder = {"pacienteID", "salt", "tarjetaSanitaria", "password"})
public class User implements Serializable{
	private static final long serialVersionUID = 1671417246199538663L;

	
	private Integer pacienteID;
	private String salt;
	private String tarjetaSanitaria;
	private String password;

	public User() {
		super();
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public User(Integer pacienteID) {
		super();
		this.pacienteID = pacienteID;
	}

	public User(int pacienteID,String salt,String tarjetaSanitaria,String password)
    {
        this.pacienteID = pacienteID;
        this.salt = salt;
        this.tarjetaSanitaria = tarjetaSanitaria;
        this.password = password;
    }

	public Integer getpacienteID() {
		return pacienteID;
	}

	public void setpacienteID(Integer pacienteID) {
		this.pacienteID = pacienteID;
	}

	public String getTarjetaSanitaria() {
		return tarjetaSanitaria;
	}

	public void setTarjetaSanitaria(String tarjetaSanitaria) {
		this.tarjetaSanitaria = tarjetaSanitaria;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	@Override
	public String toString() {
		return "Id: "+pacienteID+" salt: "+salt+" tarjetaSanitaria: "+tarjetaSanitaria+" password: "+password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pacienteID == null) ? 0 : pacienteID.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((tarjetaSanitaria == null) ? 0 : tarjetaSanitaria.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());	

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
		User other = (User) obj;
		if (pacienteID == null) {
			if (other.pacienteID != null)
				return false;
		} else if (!pacienteID.equals(other.pacienteID))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (tarjetaSanitaria == null) {
			if (other.tarjetaSanitaria != null)
				return false;
		} else if (!tarjetaSanitaria.equals(other.tarjetaSanitaria))
			return false;
		return true;
	}
}
