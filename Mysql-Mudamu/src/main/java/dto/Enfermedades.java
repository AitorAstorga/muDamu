package dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "enfermedades")
@XmlAccessorType(XmlAccessType.FIELD)
public class Enfermedades {
	@XmlElement(name = "enfermedad")
	private List<Enfermedad> listaEnfermedades;

	public Enfermedades() {
		listaEnfermedades = new ArrayList<Enfermedad>();
	}

	public List<Enfermedad> mostrar() {
		return listaEnfermedades;
	}
	
	public void añadir(Enfermedad enfermedad) {
		listaEnfermedades.add(enfermedad);		
	}

	public void borrar(int id) {
		listaEnfermedades.remove(id);
	}

	public int contar() {
		return listaEnfermedades.size();
	}

	public Enfermedad buscar(int id) {
		Enfermedad cdtemp = new Enfermedad();
		Enfermedad cdfinal = new Enfermedad();
		for (int i = 0; i < listaEnfermedades.size(); i++) {
			cdtemp = (Enfermedad) listaEnfermedades.get(i);
			if (cdtemp.getEnfermedad_preID() == id)
				cdfinal = cdtemp;
		}
		return cdfinal;
	}

	public int buscarPos(int id) {
		Enfermedad cdtemp = new Enfermedad();
		int pos = -1;
		for (int i = 0; i < listaEnfermedades.size(); i++) {
			cdtemp = listaEnfermedades.get(i);
			if (cdtemp.getEnfermedad_preID() == id)
				pos = i;
		}
		return pos;
	}

	public List<Enfermedad> getListaCD() {
		return listaEnfermedades;
	}

	public void setListaCD(List<Enfermedad> listaCD) {
		this.listaEnfermedades = listaCD;
	}
}
