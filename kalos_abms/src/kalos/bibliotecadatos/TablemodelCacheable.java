package kalos.bibliotecadatos;

import javax.swing.table.AbstractTableModel;

import kalos.beans.ISignificados;
import kalos.operaciones.OpBeans;
import kalos.operaciones.OpSignificados;

import org.apache.log4j.Logger;

public class TablemodelCacheable extends AbstractTableModel {
	
	private Logger logger=Logger.getLogger(this.getClass().getName());
	private CacheMovil cacheMovil;
	private String[] propiedades;
	
	public TablemodelCacheable(CacheMovil cacheMovil, String[] propiedades){
		this.cacheMovil=cacheMovil;
		this.propiedades=propiedades;
	}
	
	

	public int getColumnCount() {
		return 3;
	}



	public int getRowCount() {
		return (int)cacheMovil.getLongitudTotal();
	}



	public Object getValueAt(int rowIndex, int columnIndex) {
		Object bean=cacheMovil.get(rowIndex);
		if (bean==null){
		    logger.warn("la fila " + rowIndex + " no tiene bean cargado!");
		    return null;
		}
		String propiedad=propiedades[columnIndex];
		if (propiedad.equals("significado")){
			  return OpSignificados.getValorSignificado((ISignificados)bean);
		}
		Object valor=OpBeans.getPropiedadObject(bean, propiedades[columnIndex]);
		return valor;
	}




}
