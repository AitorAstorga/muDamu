package facade;

import dao.CitaDao;
import dto.CitasMedico;
import dto.CitasPaciente;

public class CitaFacade {

	CitaDao daoItem;

	public CitaFacade() {
		daoItem = new CitaDao();
	}

	public CitasMedico loadMedico(Integer medicoID) {
		return daoItem.getObjectMedico(medicoID);
	}
	public CitasPaciente loadPaciente(Integer pacienteID) {
		return daoItem.getObjectPaciente(pacienteID);
	}
}
