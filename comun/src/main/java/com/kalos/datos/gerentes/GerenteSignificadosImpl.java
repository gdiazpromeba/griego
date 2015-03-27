package com.kalos.datos.gerentes;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.kalos.beans.ISignificados;
import com.kalos.beans.ResultadoUniversal;
import com.kalos.beans.Significado;
import com.kalos.datos.dao.SignificadoDAO;
import com.kalos.enumeraciones.Idioma;
import com.kalos.operaciones.OpSignificados;
import com.kalos.recursos.Configuracion;

public class GerenteSignificadosImpl implements GerenteSignificados {
	
	SignificadoDAO significadoDAO;
	
	/**
	 * @return Returns the significadoDAO.
	 */
	public SignificadoDAO getSignificadoDAO() {
		return significadoDAO;
	}

	/**
	 * @param significadoDAO The significadoDAO to set.
	 */
	public void setSignificadoDAO(SignificadoDAO significadoDAO) {
		this.significadoDAO = significadoDAO;
	}

	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteSignificados#pueblaTodosLosSignificados(kalos.beans.ISignificados)
	 */
	public void pueblaTodosLosSignificados(ISignificados bean){
		List<Significado> significados=significadoDAO.getPorReferente(bean.getId());
		for (Significado sig: significados){
			OpSignificados.setSignificadoIndividual(bean, sig);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteSignificados#inserta(kalos.beans.Significado)
	 */
	public void inserta(Significado sig){
		significadoDAO.inserta(sig);
	}
	
	/**
	 * borra de la base de datos todos los significados adjuntos a ese referente.
	 * @param referenteId
	 */
	public void borraSignificadosDelReferente(String referenteId){
		List<Significado> listaSignificados=significadoDAO.getPorReferente(referenteId);
		for(Significado significado: listaSignificados){
			significadoDAO.borra(significado.getId());
		}
	}
	
	public void refresca(ISignificados bean){
		Map<Idioma, Significado> mapaSignificados=bean.getSignificados();
		borraSignificadosDelReferente(bean.getId());
		//inserto todlos los significados que vineron en el bean como parámetros
		for (Map.Entry<Idioma, Significado> entrada: mapaSignificados.entrySet()){
			Significado sig=entrada.getValue();
			sig.setReferenteId(bean.getId());
			significadoDAO.inserta(entrada.getValue());
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteSignificados#modifica(kalos.beans.Significado)
	 */
	public void modifica(Significado sig){
		significadoDAO.modifica(sig);
	}
	
	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteSignificados#borra(java.lang.String)
	 */
	public void borra(String significadoId){
		significadoDAO.borra(significadoId);
	}
	
	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteSignificados#pueblaTodosLosSignificados(java.util.List)
	 */
	public void pueblaTodosLosSignificados(Collection<? extends ISignificados> beans){
		for (ISignificados bean: beans){
			pueblaTodosLosSignificados((ISignificados)bean);
		}
	}
	
	public void pueblaSignificadosResultados(Collection<ResultadoUniversal> beans){
		for (ResultadoUniversal res: beans){
			List<Significado> sigs=significadoDAO.getPorReferente(res.getIdSimpleOCompuesto());
			for (Significado sig: sigs){
				if (sig.getIdioma().equals(Configuracion.getIdiomaSignificados())){
				  res.setSignificado(sig.getValor());
				}
			}
		}
	}

	

}
