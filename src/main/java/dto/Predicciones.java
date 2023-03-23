package dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "enfermedades")
@XmlAccessorType(XmlAccessType.FIELD)
public class Predicciones {
	@XmlElement(name = "prediccion")
	private List<Prediccion> listaPredicciones;

	public Predicciones() {
		listaPredicciones = new ArrayList<Prediccion>();
	}

	public List<Prediccion> mostrar() {
		return listaPredicciones;
	}
	
	public void añadir(Prediccion enfermedad) {
		listaPredicciones.add(enfermedad);		
	}

	public void borrar(int id) {
		listaPredicciones.remove(id);
	}

	public int contar() {
		return listaPredicciones.size();
	}

	public Prediccion buscar(int id) {
		Prediccion cdtemp = new Prediccion();
		Prediccion cdfinal = new Prediccion();
		for (int i = 0; i < listaPredicciones.size(); i++) {
			cdtemp = (Prediccion) listaPredicciones.get(i);
			if (cdtemp.getPrediccionID() == id)
				cdfinal = cdtemp;
		}
		return cdfinal;
	}

	public int buscarPos(int id) {
		Prediccion cdtemp = new Prediccion();
		int pos = -1;
		for (int i = 0; i < listaPredicciones.size(); i++) {
			cdtemp = listaPredicciones.get(i);
			if (cdtemp.getPrediccionID() == id)
				pos = i;
		}
		return pos;
	}

	public List<Prediccion> getListaCD() {
		return listaPredicciones;
	}

	public void setListaCD(List<Prediccion> listaCD) {
		this.listaPredicciones = listaCD;
	}
}
