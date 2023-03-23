package dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "sintomas")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sintomas {
	@XmlElement(name = "sintoma")
	private List<Sintoma> listaSintomas;

	public Sintomas() {
		listaSintomas = new ArrayList<Sintoma>();
	}

	public List<Sintoma> mostrar() {
		return listaSintomas;
	}
	
	public void añadir(Sintoma enfermedad) {
		listaSintomas.add(enfermedad);		
	}

	public void borrar(int id) {
		listaSintomas.remove(id);
	}

	public int contar() {
		return listaSintomas.size();
	}

	public Sintoma buscar(int id) {
		Sintoma cdtemp = new Sintoma();
		Sintoma cdfinal = new Sintoma();
		for (int i = 0; i < listaSintomas.size(); i++) {
			cdtemp = (Sintoma) listaSintomas.get(i);
			if (cdtemp.getSintomaID() == id)
				cdfinal = cdtemp;
		}
		return cdfinal;
	}

	public int buscarPos(int id) {
		Sintoma cdtemp = new Sintoma();
		int pos = -1;
		for (int i = 0; i < listaSintomas.size(); i++) {
			cdtemp = listaSintomas.get(i);
			if (cdtemp.getSintomaID() == id)
				pos = i;
		}
		return pos;
	}

	public List<Sintoma> getListaCD() {
		return listaSintomas;
	}

	public void setListaCD(List<Sintoma> listaCD) {
		this.listaSintomas = listaCD;
	}
}
