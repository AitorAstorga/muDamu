package dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "citasMedico")
@XmlAccessorType(XmlAccessType.FIELD)
public class CitasMedico {

	@XmlElement(name = "citaMedico")
	private List<CitaMedico> listaCitas;

	public CitasMedico() {
			listaCitas = new ArrayList<CitaMedico>();
		}

	public List<CitaMedico> mostrar() {
		return listaCitas;
	}

	public void añadir(CitaMedico cita) {
		listaCitas.add(cita);		
		}

	public void borrar(int id) {
		listaCitas.remove(id);
	}

	public int contar() {
		return listaCitas.size();
	}

	public CitaMedico buscar(int id) {
		CitaMedico cdtemp = new CitaMedico();
		CitaMedico cdfinal = new CitaMedico();
		for (int i = 0; i < listaCitas.size(); i++) {
			cdtemp = (CitaMedico) listaCitas.get(i);
			if (cdtemp.getCitaID() == id)
				cdfinal = cdtemp;
		}
		return cdfinal;
	}

	public int buscarPos(int id) {
		CitaMedico cdtemp = new CitaMedico();
		int pos = -1;
		for (int i = 0; i < listaCitas.size(); i++) {
			cdtemp = listaCitas.get(i);
			if (cdtemp.getCitaID() == id)
				pos = i;
		}
		return pos;
	}

	public List<CitaMedico> getListaCD() {
		return listaCitas;
	}

	public void setListaCD(List<CitaMedico> listaCD) {
		this.listaCitas = listaCD;
	}
}
