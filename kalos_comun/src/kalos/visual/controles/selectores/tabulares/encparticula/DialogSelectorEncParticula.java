package kalos.visual.controles.selectores.tabulares.encparticula;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import kalos.datos.adaptadores.AdaptadorGerenteEncParticulas;
import kalos.datos.adaptadores.AdaptadorGerenteSustantivos;
import kalos.datos.gerentes.GerenteEncParticulas;
import kalos.datos.gerentes.GerenteSustantivos;
import kalos.enumeraciones.LugarSubcadena;
import kalos.operaciones.ExcepcionCaracterNoEncontradoEnPalabra;
import kalos.operaciones.OpPalabras;
import kalos.recursos.Recursos;
import kalos.visual.controles.botones.BotonBuscar;
import kalos.visual.controles.combos.ComboLetras;
import kalos.visual.controles.selectores.tabulares.DialogSelectorTabular;
import kalos.visual.controles.textos.alternable.TextoAlternable;
import kalos.visual.modelos.EncParticulasPM;
import kalos.visual.modelos.PagingModel;
import kalos.visual.modelos.SustantivosPM;
import kalos.visual.renderers.FRGenericos;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * <p>
 * Title: Kalos
 * </p>
 * <p>
 * Description: Greek verb conjugation and research tool
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class DialogSelectorEncParticula extends DialogSelectorTabular {

    BotonBuscar butBuscarTodos = new BotonBuscar();
    private GerenteEncParticulas gerenteEncParticulas;

    /**
     * @param conPreposicionales
     *            se refiere a si el resultado s'olo devuelve verbos que tengan
     *            formas preposicionales, o todos lo verbos
     */
    public DialogSelectorEncParticula(GerenteEncParticulas gerenteEncParticulas) {
	super(new String[] { "forma", "tipoPalabra" }, new int[] { 30, 90 }, new String[] { FRGenericos.TEXTO_LATINO,
		FRGenericos.ENUMERACION }, new String[] { "forma", "tipo_de_palabra" });

	// this.tiposSustantivo = fac.getSelectorTiposSustantivo(true, false,
	// false);
	this.gerenteEncParticulas = gerenteEncParticulas;

	setTitle(Recursos.getCadena("encabezado"));
	// panel de búsqueda
	panBusqueda.setLayout(new GridBagLayout());
	panBusqueda.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

	FormLayout layout = new FormLayout("right:pref, 3dlu, right:pref", // cols
		"15dlu" // rows
	);

	panBusqueda.setLayout(new BorderLayout());
	PanelBuilder builder = new PanelBuilder(layout);
	builder.setDefaultDialogBorder();
	CellConstraints cc = new CellConstraints();

	builder.addLabel(Recursos.getCadena("todo"), cc.xy(1, 1));
	builder.add(butBuscarTodos, cc.xy(3, 1));

	panBusqueda.add(builder.getPanel());

	// eventos de búsqueda
	butBuscarTodos.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		cargaBeansEnTablaTodos();
	    }
	});

    }

    protected void cargaBeansEnTablaTodos() {
	preparaColumnas();
	AdaptadorGerenteEncParticulas apv = new AdaptadorGerenteEncParticulas(gerenteEncParticulas);
	apv.seleccionaTodos();
	pagingModel = new EncParticulasPM(apv, 100);
	tablemodelATabla();
    }

    public void fonts() {
    }

    public void limpieza() {
    }

    public void llenaCampos() {
    }

    public void mensajeVacio() {
	JOptionPane.showMessageDialog(this, Recursos.getCadena("no_puede_ser_vacio"), Recursos.getCadena("busqueda"),
		JOptionPane.OK_OPTION);
    }

}