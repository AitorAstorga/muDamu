package facade;

import dao.SintomaDao;
import dto.Sintoma;
import dto.SintomaPrediccion;

public class SintomaFacade {
	SintomaDao daoItem;
	
	public SintomaFacade() {
		daoItem = new SintomaDao();
	}
	
	public Sintoma load() {
		return daoItem.getObject();
	}
	
	public SintomaPrediccion loadSintomaPaciente(Integer predID) {
		return daoItem.getObjectPacienteSintomas(predID);
	}	
}
