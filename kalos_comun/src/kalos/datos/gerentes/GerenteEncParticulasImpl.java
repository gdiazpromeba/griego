package kalos.datos.gerentes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kalos.beans.EncParticulaBean;
import kalos.datos.dao.EncParticulasDAO;

import org.springframework.beans.factory.InitializingBean;

public class GerenteEncParticulasImpl implements GerenteEncParticulas, InitializingBean, GerenteDatos {

    private Map<String, EncParticulaBean> map=new HashMap<String, EncParticulaBean>();
    private EncParticulasDAO encParticulasDAO;

    
    public void afterPropertiesSet() throws Exception {
        List<EncParticulaBean> encs=encParticulasDAO.seleccionaEncParticulasTodos();
        for (EncParticulaBean bean: encs){
            map.put(bean.getId(), bean);
        }
	}

	
	public List<EncParticulaBean> getTodos() {
		return new ArrayList<EncParticulaBean>(map.values());
	}

	@SuppressWarnings("unchecked")
	public EncParticulaBean seleccionaUno(String id) {
		return map.get(id);
	}

    public void setEncParticulasDAO(EncParticulasDAO encParticulasDAO) {
        this.encParticulasDAO = encParticulasDAO;
    }


}
