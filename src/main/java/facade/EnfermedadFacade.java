package facade;

import dao.EnfermedadDao;
import dto.Enfermedad;
import dto.Enfermedades;

public class EnfermedadFacade {
	EnfermedadDao daoItem;
	
	public EnfermedadFacade() {
		daoItem = new EnfermedadDao();
	}
	
	public Enfermedades load(Integer predID) {
		return daoItem.getObject(predID);
	}
}