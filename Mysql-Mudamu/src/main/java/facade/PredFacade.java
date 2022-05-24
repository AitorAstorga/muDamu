package facade;

import dao.PredDao;
import dto.Predicciones;

public class PredFacade {
	
	PredDao daoItem;
	
	public PredFacade() {
		daoItem = new PredDao();
	}
	
	public Predicciones load(Integer medicoID) {
		return daoItem.getObject(medicoID);
	}
	
	public void update(Integer prediccionID, Integer categoriaID) {
		daoItem.setObject(prediccionID, categoriaID);
	}
}
