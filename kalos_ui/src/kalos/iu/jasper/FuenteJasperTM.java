package kalos.iu.jasper;

import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

/**
 *  Clase que implementa las interfaces necesarias de JasperReports para ser una fuente
 *  de datos aceptable para él.  
 */
public class FuenteJasperTM extends JRTableModelDataSource {
	
	private int cursor=-1;
	private int tamaño;
	private DefaultTableModel tm;
	
	public FuenteJasperTM(DefaultTableModel tm){
		super(tm);
		cursor=-1;
		tamaño=tm.getRowCount();
		this.tm=tm;
	}
	
	public boolean next(){
		cursor++;
		if (cursor>=tamaño)
			return false;
		else
			return true;
	}
	
	public void moveFirst(){
		cursor=0;
	}
	
	public Object getFieldValue(JRField campo){
		String columna=campo.getName();
		int col=tm.findColumn(columna);
		return tm.getValueAt(cursor, col);
	}
	

}
