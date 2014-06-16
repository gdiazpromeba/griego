package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.IrrParticipioEntero;
import kalos.datos.dao.IrrParticipiosEnterosDAO;

import org.springframework.beans.factory.InitializingBean;

public class GerenteIrrParticipiosEnterosImpl implements GerenteIrrParticipiosEnteros {

    private IrrParticipiosEnterosDAO irrParticipiosEnterosDAO;
    
	public List<IrrParticipioEntero> seleccionaPorVerbo(String verboId) {
        return irrParticipiosEnterosDAO.seleccionaPorVerbo(verboId);
	}
	
	public List<IrrParticipioEntero> seleccionaPorNominativo(String nominativo) {
        return irrParticipiosEnterosDAO.seleccionaPorNominativo(nominativo);
	}
	
	public List<IrrParticipioEntero> seleccionaPorGenitivo(String genitivo) {
        return irrParticipiosEnterosDAO.seleccionaPorGenitivo(genitivo);
	}
	


	public List<?> reseleccionar() {
		throw new RuntimeException("reselección aún no implementada");
	}

	public void setUltimosParametros(Object[] parametros) {
		// TODO Auto-generated method stub

	}

	public Object[] getUltimosParametros() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUltimaSeleccion() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setUltimaSeleccion(String query) {
		// TODO Auto-generated method stub

	}

	public InitializingBean getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDao(InitializingBean dao) {
		// TODO Auto-generated method stub

	}

    public IrrParticipiosEnterosDAO getIrrParticipiosEnterosDAO() {
        return irrParticipiosEnterosDAO;
    }

    public void setIrrParticipiosEnterosDAO(IrrParticipiosEnterosDAO irrParticipiosEnterosDAO) {
        this.irrParticipiosEnterosDAO = irrParticipiosEnterosDAO;
    }

}
