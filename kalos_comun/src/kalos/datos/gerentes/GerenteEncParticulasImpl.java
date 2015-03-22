package kalos.datos.gerentes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kalos.beans.EncParticulaBean;
import kalos.datos.dao.EncParticulasDAO;

import org.springframework.beans.factory.InitializingBean;

public class GerenteEncParticulasImpl implements InitializingBean, SeleccionadorUnoTodos, GerenteEncParticulas {
    private Map<String, EncParticulaBean> mapEncPart = new HashMap<String, EncParticulaBean>();
    private EncParticulasDAO encParticulasDAO;

    /* (non-Javadoc)
     * @see kalos.datos.gerentes.GerenteEncParticulasImpl#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
	List<EncParticulaBean> localList = this.encParticulasDAO.seleccionaEncParticulasTodos();
	Iterator<EncParticulaBean> localIterator = localList.iterator();
	while (localIterator.hasNext()) {
	    EncParticulaBean localf = localIterator.next();
	    this.mapEncPart.put(localf.getId(), localf);
	}
    }

    /* (non-Javadoc)
     * @see kalos.datos.gerentes.GerenteEncParticulasImpl#getTodos()
     */
    @Override
    public List<EncParticulaBean> getTodos() {
	return new ArrayList<EncParticulaBean>(this.mapEncPart.values());
    }

    /* (non-Javadoc)
     * @see kalos.datos.gerentes.GerenteEncParticulasImpl#seleccionaUno(java.lang.String)
     */
    @Override
    public EncParticulaBean seleccionaUno(String paramString) {
	return (EncParticulaBean) this.mapEncPart.get(paramString);
    }

    /* (non-Javadoc)
     * @see kalos.datos.gerentes.GerenteEncParticulasImpl#setEncParticulasDAO(kalos.datos.dao.EncParticulasDAO)
     */
    @Override
    public void setEncParticulasDAO(EncParticulasDAO paramQA) {
	this.encParticulasDAO = paramQA;
    }
}
