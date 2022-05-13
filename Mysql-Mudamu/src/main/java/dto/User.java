package dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlType(propOrder = {"pacienteID", "tarjetaSanitaria", "salt", "username", "password"})
public class User implements Serializable{
	private static final long serialVersionUID = 1671417246199538663L;

	
	private Integer pacienteID;
	private Integer tarjetaSanitaria;
	private String salt;
	private String username;
	private String password;
	private String confirmPassword;

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

	public User(int pacienteID,Integer tarjetaSanitaria,String salt,String username,String password)
    {
        this.pacienteID = pacienteID;
        this.salt = salt;
        this.username = username;
        this.password = password;
    }

	public Integer getIDPaciente() {
		return pacienteID;
	}

	public void setIDPaciente(Integer pacienteID) {
		this.pacienteID = pacienteID;
	}

	public Integer getTarjetaSanitaria(){
		return tarjetaSanitaria;
	}

	public void setTarjetaSanitaria(Integer tarjetaSanitaria) {
		this.tarjetaSanitaria = tarjetaSanitaria;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	

	@Override
	public String toString() {
		return "Id: "+pacienteID+" tarjeta: "+tarjetaSanitaria+" salt: "+salt+" username: "+username+" password: "+password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pacienteID == null) ? 0 : pacienteID.hashCode());
		result = prime * result + ((tarjetaSanitaria == null) ? 0 : tarjetaSanitaria.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		if (tarjetaSanitaria == null) {
			if (other.tarjetaSanitaria != null)
				return false;
		} else if (!tarjetaSanitaria.equals(other.tarjetaSanitaria))
			return false;
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
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
