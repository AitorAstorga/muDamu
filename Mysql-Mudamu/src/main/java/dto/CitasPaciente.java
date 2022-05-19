package dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "citasPaciente")
@XmlAccessorType(XmlAccessType.FIELD)
public class CitasPaciente {

	@XmlElement(name = "citaPaciente")
	private List<CitaPaciente> listaCitas;

	public CitasPaciente() {
		listaCitas = new ArrayList<CitaPaciente>();
	}

	public List<CitaPaciente> mostrar() {
		return listaCitas;
	}

	public void añadir(CitaPaciente cita) {
		listaCitas.add(cita);
	}

	public void borrar(int id) {
		listaCitas.remove(id);
	}

	public int contar() {
		return listaCitas.size();
	}

	public CitaPaciente buscar(int id) {
		CitaPaciente cdtemp = new CitaPaciente();
		CitaPaciente cdfinal = new CitaPaciente();
		for (int i = 0; i < listaCitas.size(); i++) {
			cdtemp = (CitaPaciente) listaCitas.get(i);
			if (cdtemp.getCitaID() == id)
				cdfinal = cdtemp;
		}
		return cdfinal;
	}

	public int buscarPos(int id) {
		CitaPaciente cdtemp = new CitaPaciente();
		int pos = -1;
		for (int i = 0; i < listaCitas.size(); i++) {
			cdtemp = listaCitas.get(i);
			if (cdtemp.getCitaID() == id)
				pos = i;
		}
		return pos;
	}

	public List<CitaPaciente> getListaCD() {
		return listaCitas;
	}

	public void setListaCD(List<CitaPaciente> listaCD) {
		this.listaCitas = listaCD;
	}
}
