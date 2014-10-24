package kalos.datos.gerentes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kalos.beans.EncParticulaBean;
import kalos.datos.dao.EncParticulasDAO;

import org.springframework.beans.factory.InitializingBean;

public class GerenteEncParticulas implements InitializingBean, SelectorTodos {
    private Map<String, EncParticulaBean> mapEncPart = new HashMap<String, EncParticulaBean>();
    private EncParticulasDAO encParticulasDAO;

    public void afterPropertiesSet() throws Exception {
	List<EncParticulaBean> localList = this.encParticulasDAO.seleccionaEncParticulasTodos();
	Iterator<EncParticulaBean> localIterator = localList.iterator();
	while (localIterator.hasNext()) {
	    EncParticulaBean localf = localIterator.next();
	    this.mapEncPart.put(localf.getId(), localf);
	}
    }

    public List<EncParticulaBean> getTodos() {
	return new ArrayList<EncParticulaBean>(this.mapEncPart.values());
    }

    public EncParticulaBean seleccionaUno(String paramString) {
	return (EncParticulaBean) this.mapEncPart.get(paramString);
    }

    public void setEncParticulasDAO(EncParticulasDAO paramQA) {
	this.encParticulasDAO = paramQA;
    }
}
