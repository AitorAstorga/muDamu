package facade;

import dao.EnfermedadDao;
import dto.Enfermedad;

public class EnfermedadFacade {
	EnfermedadDao daoItem;
	
	public EnfermedadFacade() {
		daoItem = new EnfermedadDao();
	}
	
	public Enfermedad load(Integer predID) {
		return daoItem.getObject(predID);
	}
}