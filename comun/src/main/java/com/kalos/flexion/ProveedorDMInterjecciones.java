package com.kalos.flexion;

import java.util.Iterator;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.kalos.beans.InterjeccionBean;
import com.kalos.beans.Significado;
import com.kalos.datos.gerentes.GerenteInterjecciones;
import com.kalos.enumeraciones.Idioma;
import com.kalos.operaciones.OpPalabras;
import com.kalos.recursos.Configuracion;

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
