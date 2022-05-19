package facade;

import dao.SintomaDao;
import dto.Sintomas;
import dto.SintomasPredicciones;

public class SintomaFacade {
	SintomaDao daoItem;
	
	public SintomaFacade() {
		daoItem = new SintomaDao();
	}
	
	public Sintomas load() {
		return daoItem.getObject();
	}
	
	public SintomasPredicciones loadSintomaPaciente(Integer predID) {
		return daoItem.getObjectPacienteSintomas(predID);
	}	
}
