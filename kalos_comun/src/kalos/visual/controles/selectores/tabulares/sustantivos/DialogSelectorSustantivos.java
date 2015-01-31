package kalos.visual.controles.selectores.tabulares.sustantivos;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import kalos.datos.adaptadores.AdaptadorGerenteSustantivos;
import kalos.datos.gerentes.GerenteSustantivos;
import kalos.enumeraciones.LugarSubcadena;
import kalos.operaciones.ExcepcionCaracterNoEncontradoEnPalabra;
import kalos.operaciones.OpPalabras;
import kalos.recursos.Recursos;
import kalos.visual.controles.botones.BotonBuscar;
import kalos.visual.controles.combos.ComboLetras;
import kalos.visual.controles.selectores.tabulares.DialogSelectorTabular;
import kalos.visual.controles.textos.alternable.TextoAlternable;
import kalos.visual.modelos.PagingModel;
import kalos.visual.modelos.SustantivosPM;
import kalos.visual.renderers.FRGenericos;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * <p>Title: Kalos</p>
 * <p>Description: Greek verb conjugation and research tool</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class DialogSelectorSustantivos extends DialogSelectorTabular {
	
//	selección de la forma de diccionario
	TextoAlternable talBuscarDiccionarioSubcadena = new TextoAlternable(false);
	BotonBuscar butBuscarDiccionarioSubcadena = new BotonBuscar();
	ComboLetras cmbLetras = new ComboLetras();
	BotonBuscar butBuscarDiccionarioLetra = new BotonBuscar();
	JTextField txtNominativo = new JTextField(15);
//    SelectorJerarquicoBeans tiposSustantivo;
    private GerenteSustantivos gerenteSustantivos ;
	
	
	/**
	 * @param conPreposicionales se refiere a si el resultado s'olo devuelve verbos que tengan formas preposicionales, o todos lo verbos
	 */
	public DialogSelectorSustantivos(GerenteSustantivos gerenteSustantivos) {
		super(
                new String[] { "letra", "codigo", "nominativo", "genitivo", "genero", "significado" },
                new int[] { 30, 50, 90, 40, 40, 200 },
                new String[] { FRGenericos.TEXTO_LATINO, FRGenericos.NUMERO_ENTERO, 
                        FRGenericos.TEXTO_LATINO, FRGenericos.TEXTO_LATINO, FRGenericos.ENUMERACION, FRGenericos.TEXTO_LATINO },
                new String[] { "letra", "codigo", "nominativo", "genitivo", "genero", "significado" }
                );
        
//        this.tiposSustantivo = fac.getSelectorTiposSustantivo(true, false, false);
        this.gerenteSustantivos= gerenteSustantivos;
                        
		setTitle(Recursos.getCadena("sustantivo"));
		//panel de búsqueda
		panBusqueda.setLayout(new GridBagLayout());
		panBusqueda.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		
		
		FormLayout layout=new FormLayout(
				"right:pref, 3dlu, 30dlu, 120dlu:grow(0.7), 50dlu:grow(0.3), 3dlu, pref ", // cols
			    "3dlu, 15dlu,   3dlu, 15dlu,   3dlu" // rows
		);
		
		panBusqueda.setLayout(new BorderLayout());
	    PanelBuilder builder = new PanelBuilder(layout);
	    builder.setDefaultDialogBorder();
	    CellConstraints cc = new CellConstraints();

	     
	    builder.addLabel(Recursos.getCadena("por_subcadena"),        cc.xy(1,  2));
	    builder.add(talBuscarDiccionarioSubcadena,                   cc.xyw(3, 2, 3));
	    builder.add(butBuscarDiccionarioSubcadena ,                  cc.xy(7, 2));
	 
	    builder.addLabel(Recursos.getCadena("por_letra_inicial"),      cc.xy(1,  4));
	    builder.add(cmbLetras ,                                        cc.xy(3,  4));
	    builder.add(butBuscarDiccionarioLetra,                         cc.xy(7,  4));

	    
	    panBusqueda.add(builder.getPanel());
		
		//eventos de búsqueda
		butBuscarDiccionarioLetra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                cargaBeansEnTablaDadaLetra(cmbLetras.getLetraSeleccionadaBeta());
			}
		});
		
		butBuscarDiccionarioSubcadena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String verbo=talBuscarDiccionarioSubcadena.getCadenaCompleta();
				cargaBeansEnTablaDadoNominativo(verbo, LugarSubcadena.Principio);
			}
		});



	}
	
	
	
	
	
    protected void cargaBeansEnTablaDadaLetra(String letra) {
        preparaColumnas();
    	AdaptadorGerenteSustantivos apv=new AdaptadorGerenteSustantivos(gerenteSustantivos);
        apv.seleccionPorLetraInicial(letra);
        pagingModel=new SustantivosPM(apv, 100);
        tablemodelATabla();
	}
    
    protected void cargaBeansEnTablaDadoNominativo(String subcadenaBeta, LugarSubcadena lus) {
    	preparaColumnas();
        AdaptadorGerenteSustantivos apv=new AdaptadorGerenteSustantivos(gerenteSustantivos);
        apv.seleccionaPorNominativo(subcadenaBeta, lus);
        pagingModel=new SustantivosPM(apv, 100);
        tablemodelATabla();
    }
	
	
	
	public void fonts(){}
	public void limpieza(){}
	
	
	public void llenaCampos(){}
	
	
	

	
	public void mensajeVacio(){
		JOptionPane.showMessageDialog(this, Recursos.getCadena("no_puede_ser_vacio"), Recursos.getCadena("busqueda"), JOptionPane.OK_OPTION);
	}
	
	
}