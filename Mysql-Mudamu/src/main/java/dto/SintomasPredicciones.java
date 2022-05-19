package dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "enfermedades")
@XmlAccessorType(XmlAccessType.FIELD)
public class SintomasPredicciones {
	@XmlElement(name = "sintomasPrediccion")
	private List<SintomasPrediccion> listaEnfermedades;

	public SintomasPredicciones() {
		listaEnfermedades = new ArrayList<SintomasPrediccion>();
	}

	public List<SintomasPrediccion> mostrar() {
		return listaEnfermedades;
	}
	
	public void añadir(SintomasPrediccion enfermedad) {
		listaEnfermedades.add(enfermedad);		
	}

	public void borrar(int id) {
		listaEnfermedades.remove(id);
	}

	public int contar() {
		return listaEnfermedades.size();
	}

	public SintomasPrediccion buscar(int id) {
		SintomasPrediccion cdtemp = new SintomasPrediccion();
		SintomasPrediccion cdfinal = new SintomasPrediccion();
		for (int i = 0; i < listaEnfermedades.size(); i++) {
			cdtemp = (SintomasPrediccion) listaEnfermedades.get(i);
			if (cdtemp.getSintomaID() == id)
				cdfinal = cdtemp;
		}
		return cdfinal;
	}

	public int buscarPos(int id) {
		SintomasPrediccion cdtemp = new SintomasPrediccion();
		int pos = -1;
		for (int i = 0; i < listaEnfermedades.size(); i++) {
			cdtemp = listaEnfermedades.get(i);
			if (cdtemp.getSintomaID() == id)
				pos = i;
		}
		return pos;
	}

	public List<SintomasPrediccion> getListaCD() {
		return listaEnfermedades;
	}

	public void setListaCD(List<SintomasPrediccion> listaCD) {
		this.listaEnfermedades = listaCD;
	}
}
