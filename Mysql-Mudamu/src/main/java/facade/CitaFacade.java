package facade;

import dao.CitaDao;
import dto.CitaMedico;
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
	public CitasMedico loadAdministrador() {
		return daoItem.getObjectAdministrador();
	}

	public CitasMedico loadNewCitas(){
		return daoItem.getObjectNewCitas();
	}

	public void insertCita(int prediccionID, String fecha_hora, int pacienteID) {
		daoItem.insertNewCita(prediccionID, fecha_hora, pacienteID);
	}
}
