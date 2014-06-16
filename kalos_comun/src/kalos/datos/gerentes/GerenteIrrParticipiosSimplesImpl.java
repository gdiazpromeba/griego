package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.IrrParticipioSimpleBean;
import kalos.datos.dao.IrrParticipiosSimplesDAO;

import org.springframework.beans.factory.InitializingBean;

public class GerenteIrrParticipiosSimplesImpl implements GerenteIrrParticipiosSimples {
    
    private IrrParticipiosSimplesDAO irrParticipiosSimplesDAO;

	public List<IrrParticipioSimpleBean> seleccionaPorVerbo(String verboId) {
		return irrParticipiosSimplesDAO.seleccionaPorVerbo(verboId);
	}
	
	public List<IrrParticipioSimpleBean> seleccionaPorForma(String forma) {
		return irrParticipiosSimplesDAO.seleccionaPorForma(forma);
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

    public void setIrrParticipiosSimplesDAO(IrrParticipiosSimplesDAO irrParticipiosSimplesDAO) {
        this.irrParticipiosSimplesDAO = irrParticipiosSimplesDAO;
    }

}
