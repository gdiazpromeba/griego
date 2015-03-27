/*
 * Created on May 4, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.kalos.flexion;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.kalos.beans.ConjuncionBean;
import com.kalos.beans.Significado;
import com.kalos.datos.gerentes.GerenteConjunciones;
import com.kalos.enumeraciones.Idioma;
import com.kalos.operaciones.OpPalabras;
import com.kalos.recursos.Configuracion;

/**
 * 
 * Representa el contacto del paquete de conjugación con el exterior. Su
 * objetivo es proveer representaciones de la conjugación de un verbo o
 * infinitivo, via dataModules Estos datamodules presentan el dato bruto, sin
 * rotar ni seleccionar ni transformar en renderers de celda Su interfaz con el
 * mundo exterior (fuera de este paquete) debe ser la clase
 * CreadorTableModelsGrilla
 * 
 * @author GDiaz
 * 
 */
public class ProveedorDMConjunciones {

    private GerenteConjunciones gerenteConjunciones;

    /**
     * Produce un tablemodel con todas las filas de ese tipo de partícula
     * 
     * @param tipo
     * @return
     */
    public DefaultTableModel getTMModeloConjunciones() {
        DefaultTableModel tm = new DefaultTableModel(new String[]{"PARTICULARIDAD", "TIPO", "SUBTIPO",  "CODIGO", "FORMA", "SIGNIFICADO" }, 0);
        List<ConjuncionBean> beans = gerenteConjunciones.seleccionaTodos();
        for (ConjuncionBean bean : beans) {
            Significado sig=bean.getSignificados().get(Idioma.getEnum(Configuracion.getIdiomaSignificados()));
            String valorSignificado=sig.getValor();
            tm.addRow(new Object[]{
                    bean.getPartic(), 
                    bean.getTipo(),
                    bean.getSubtipo(), bean.getCodigo(), OpPalabras.strBetaACompleto(bean.getForma()),
                    valorSignificado });
        }
        return tm;
    }
    
  

    /**
     * @param gerenteParticulas
     *            The gerenteParticulas to set.
     */
    public void setGerenteConjunciones(GerenteConjunciones gerenteConjunciones) {
        this.gerenteConjunciones = gerenteConjunciones;
    }

}
