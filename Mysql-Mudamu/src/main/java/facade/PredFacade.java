package facade;

import dao.PredDao;
import dto.Prediccion;
import dto.User;

public class PredFacade {
	
	PredDao daoItem;
	
	public PredFacade() {
		daoItem = new PredDao();
	}
	
	public Prediccion load(Integer medicoID) {
		return daoItem.getObject(medicoID);
	}
}