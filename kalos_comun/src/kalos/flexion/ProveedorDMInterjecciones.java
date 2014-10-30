package kalos.flexion;

import java.util.Iterator;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import kalos.beans.InterjeccionBean;
import kalos.beans.Significado;
import kalos.datos.gerentes.GerenteInterjecciones;
import kalos.enumeraciones.Idioma;
import kalos.operaciones.OpPalabras;
import kalos.recursos.Configuracion;

public class ProveedorDMInterjecciones {
    private GerenteInterjecciones gerenteInterjecciones;

    public DefaultTableModel getTMModeloInterjecciones() {
	DefaultTableModel localDefaultTableModel = new DefaultTableModel(new String[] { "FORMA", "PARTICULARIDAD",
		"SIGNIFICADO" }, 0);
	List<InterjeccionBean> localList = this.gerenteInterjecciones.seleccionaTodos();
	Iterator<InterjeccionBean> localIterator = localList.iterator();
	while (localIterator.hasNext()) {
	    InterjeccionBean localY = localIterator.next();
	    Significado localQ = localY.getSignificados().get(Idioma.getEnum(Configuracion.getIdiomaSignificados()));
	    String str = localQ.getValor();
	    localDefaultTableModel.addRow(new Object[] { OpPalabras.strBetaACompleto(localY.getInterjeccion()),
		    localY.getPartic(), str });
	}
	return localDefaultTableModel;
    }

    public void setGerenteInterjecciones(GerenteInterjecciones paramjA) {
	this.gerenteInterjecciones = paramjA;
    }
}
