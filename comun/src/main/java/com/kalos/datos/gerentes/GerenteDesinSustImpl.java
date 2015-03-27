package com.kalos.datos.gerentes;

import java.util.List;

import com.kalos.beans.DesinSust;
import com.kalos.beans.TipoJerarquico;
import com.kalos.datos.dao.DesinSustDAO;

public class GerenteDesinSustImpl  extends Manager implements GerenteDesinSust {

	
	private Object[] ultimosParametros;
	private String ultimaSeleccion;
	private DesinSustDAO desinSustDAO;
	
	public List<String> seleccionaPorTipos(String[] tipos, boolean todas) {
		setUltimosParametros(new Object[]{tipos, todas});
		setUltimaSeleccion("getPorTipos");
		List<String> desinencias=desinSustDAO.getPorTipos(tipos, todas);
		return desinencias;
	}
    
    
    public List<String> seleccionaPorTipos(List <TipoJerarquico> tipos, boolean todas) {
        String[] ids=new String[tipos.size()];
        for(int i=0; i<ids.length; i++){
            TipoJerarquico tij=tipos.get(i);
            ids[i]=tij.getId();
        }
        List<String> resultado= desinSustDAO.getPorTipos(ids, todas);
        return resultado;
    }

	public DesinSust getInidvidual(String desinSustId) {
		return desinSustDAO.getInidvidual(desinSustId);
	}

	public void inserta(DesinSust ds) {
		desinSustDAO.inserta(ds);

	}

	public void modifica(DesinSust ds) {
		desinSustDAO.modifica(ds);

	}

	public void borra(String desinSustId) {
		desinSustDAO.borra(desinSustId);
	}
	
	
	public List<DesinSust> seleccionaNominativosSingulares(){
		return desinSustDAO.seleccionaNominativosSingulares();
	}
	
	public List<String> seleccionaContracciones(){
		return desinSustDAO.seleccionaContracciones();
	}
	
	public List<?> reseleccionar(){
		if (ultimaSeleccion.equals("getPorTipos")){
			return seleccionaPorTipos((String[])ultimosParametros[0], (Boolean)ultimosParametros[1]);
		}else{
			return null;
		}
	}
    
    public List<DesinSust> getBeans(List<String> ids){
        return desinSustDAO.getRegistros(ids);
    }

	/**
	 * @return Returns the ultimaSeleccion.
	 */
	public String getUltimaSeleccion() {
		return ultimaSeleccion;
	}

	/**
	 * @param ultimaSeleccion The ultimaSeleccion to set.
	 */
	public void setUltimaSeleccion(String ultimaSeleccion) {
		this.ultimaSeleccion = ultimaSeleccion;
	}

	/**
	 * @return Returns the ultimosParametros.
	 */
	public Object[] getUltimosParametros() {
		return ultimosParametros;
	}

	/**
	 * @param ultimosParametros The ultimosParametros to set.
	 */
	public void setUltimosParametros(Object[] ultimosParametros) {
		this.ultimosParametros = ultimosParametros;
	}

	/**
	 * @param desinSustDAO The desinSustDAO to set.
	 */
	public void setDesinSustDAO(DesinSustDAO desinSustDAO) {
		this.desinSustDAO = desinSustDAO;
	}

}
