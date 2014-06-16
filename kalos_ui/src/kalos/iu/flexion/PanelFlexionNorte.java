package kalos.iu.flexion;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kalos.beans.EntradaDiccionario;
import kalos.enumeraciones.Reportes;
import kalos.recursos.Configuracion;
import kalos.recursos.Recursos;
import kalos.visual.controles.combos.ComboEnumeracion;
import kalos.visual.controles.deslizador.Deslizador;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class PanelFlexionNorte extends JPanel{

	
	private ComboEnumeracion tiposReporte = new ComboEnumeracion("kalos.enumeraciones.Reportes");
	private ComboEnumeracion masTipos=new ComboEnumeracion(
	        new Reportes[]{
	                Reportes.ARTICULOS_POR_GENERO, 
	                Reportes.PRONOMBRES_PERSONALES_POR_CASO, Reportes.PRONOMBRES_PERSONALES_POR_CASO_SIN_DUAL,
	                Reportes.PRONOMBRES_RELATIVOS_POR_GENERO_1,
	                Reportes.PRONOMBRES_RELATIVOS_POR_GENERO_2,
	                Reportes.PRONOMBRES_RELATIVOS_POR_GENERO_3,
	                Reportes.PRONOMBRES_REFLEXIVOS_POR_GENERO,
	                Reportes.PRONOMBRES_INTERROGATIVOS_POR_GENERO,
	                Reportes.CONJUNCIONES_ALFABETICO, Reportes.CONJUNCIONES_POR_TIPO, 
	                Reportes.PREPOSICIONES_ALFABETICO, Reportes.PREPOSICIONES_POR_CASO,
	                Reportes.INTERJECCIONES_ALFABETICO,
	                Reportes.PRONOMBRES_INDEFINIDOS_POR_GENERO,
	                });
	private JButton crear=new JButton(Recursos.getCadena("cargando"));
	private JButton crearMas=new JButton(Recursos.getCadena("cargando"));
	Deslizador deslizador=new Deslizador();
	private JLabel etiquetaCanonica=new JLabel();
	private boolean todaviaCargandoPanel=true;
	private boolean tieneValores=false;
	
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	/**
	 * @return Returns the crear.
	 */
	public JButton getCrear() {
		return crear;
	}


	public void habilitarBotones(){
	    todaviaCargandoPanel=false;
		crear.setEnabled(!todaviaCargandoPanel && tieneValores);
	    crear.setText(Recursos.getCadena("crear"));
	    crearMas.setText(Recursos.getCadena("crear"));
	    crearMas.setEnabled(true);
	}
	
	/**
	 * @return Returns the tiposReporte.
	 */
	public ComboEnumeracion getTiposReporte() {
		return tiposReporte;
	}


	public PanelFlexionNorte() {
		etiquetaCanonica.setFont(Configuracion.getFont());
		disposicion();
		crear.setEnabled(false);
		crearMas.setEnabled(false);
	}
	
	/**
	 * recibe la forma canónica completa
	 * @param formaCanonica   la forma canónica en unicode
	 */
	public void setFormaCanonica(String formaCanonica){
		etiquetaCanonica.setText(formaCanonica);
	}

	
	public void disposicion() {
		FormLayout layout = new FormLayout("pref,3dlu,pref,3dlu,pref, fill:1dlu:grow, right:pref, 3dlu, pref", // cols
				"fill:pref:grow(.1), 3dlu, fill:pref:grow(.9),  3dlu,pref,3dlu " // rows
		);

		PanelBuilder builder = new PanelBuilder(layout);

		//		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
		//				new FormDebugPanel());

		builder.setDefaultDialogBorder();

		CellConstraints cc = new CellConstraints();

//		layout.setColumnGroups(new int[][] { { 3, 7 } });

		builder.addLabel(Recursos.getCadena("forma_canonica"), cc.xy(1, 1));
		builder.add(etiquetaCanonica, cc.xy(3, 1));
		builder.addLabel(Recursos.getCadena("elija_tipo_de_reporte"), cc.xy(1, 3));
		builder.add(tiposReporte, cc.xy(3, 3));
		builder.add(crear, cc.xy(5, 3));
        builder.addLabel(Recursos.getCadena("mas_tipos_de_cuadro"), cc.xy(1, 5));
        builder.add(masTipos, cc.xy(3, 5));
		builder.add(crearMas, cc.xy(5, 5));
		builder.addLabel(Recursos.getCadena("tamaño_tipografia"), cc.xy(7, 3));
		builder.add(deslizador, cc.xy(9, 3));
		setLayout(new BorderLayout());
		add(builder.getPanel());
	}
	
	/**
	 * puebla el combo de tipos de reporte según el tipo de palabra de 
	 * la entrada de diccionario seleccionada 
	 */
	public void pueblaSegunEntrada(EntradaDiccionario edi) {
		tiposReporte.removeAllItems();
		if (edi == null) {
			tiposReporte.addItem(Reportes.NINGUN_TIPO_DISPONIBLE);
			tieneValores=false;
		} else {
			tieneValores=true;
			switch (edi.getTipoPalabra()) {
			case Adjetivo:
				tiposReporte.addItem(Reportes.ADJETIVOS_POR_GENERO);
				tiposReporte.addItem(Reportes.ADJETIVOS_POR_NUMERO);
				tiposReporte.addItem(Reportes.ADJETIVOS_POR_NUMERO_SIN_DUAL);
				break;
			case Adverbio:
				tiposReporte.addItem(Reportes.NINGUN_TIPO_DISPONIBLE);
				break;
			case Sustantivo:
				tiposReporte.addItem(Reportes.SUSTANTIVOS_POR_NUMERO);
				tiposReporte.addItem(Reportes.SUSTANTIVOS_POR_NUMERO_SIN_DUAL);
				break;
			case Verbo:
				//reportes verbales
				tiposReporte.addItem(Reportes.VERBOS_POR_MODO_ABREVIADO);
				tiposReporte.addItem(Reportes.VERBOS_POR_MODO);
				tiposReporte.addItem(Reportes.VERBOS_POR_VOZ);
				tiposReporte.addItem(Reportes.VERBOS_POR_MODO_SIN_DUAL);
				tiposReporte.addItem(Reportes.VERBOS_POR_VOZ_SIN_DUAL);
				//reportes de participio
				tiposReporte.addItem(Reportes.PARTICIPIOS_POR_CASO_ABREVIADO);
				tiposReporte.addItem(Reportes.PARTICIPIOS_POR_VOZ_ABREVIADO);
				tiposReporte.addItem(Reportes.PARTICIPIOS_POR_NUMERO);
				tiposReporte.addItem(Reportes.PARTICIPIOS_POR_VOZ);
				tiposReporte.addItem(Reportes.PARTICIPIOS_POR_NUMERO_SIN_DUAL);
				tiposReporte.addItem(Reportes.PARTICIPIOS_POR_VOZ_SIN_DUAL);
				tiposReporte.addItem(Reportes.PARTICIPIOS_POR_NUMERO_SIN_VOCATIVO);
				tiposReporte.addItem(Reportes.PARTICIPIOS_POR_VOZ_SIN_VOCATIVO);
				//reportes de infinitivo
				tiposReporte.addItem(Reportes.INFINITIVOS_POR_VOZ);
				break;
			case Articulo:
				tiposReporte.addItem(Reportes.ARTICULOS_POR_GENERO);
				break;
			case PronombrePersonal:
				tiposReporte.addItem(Reportes.PRONOMBRES_PERSONALES_POR_CASO);
				tiposReporte.addItem(Reportes.PRONOMBRES_PERSONALES_POR_CASO_SIN_DUAL);
				break;
			case PronombreRelativo:
				tiposReporte.addItem(Reportes.PRONOMBRES_RELATIVOS_POR_GENERO_1);
				tiposReporte.addItem(Reportes.PRONOMBRES_RELATIVOS_POR_GENERO_2);
				tiposReporte.addItem(Reportes.PRONOMBRES_RELATIVOS_POR_GENERO_3);
				break;
			case PronombreReflexivo:
				tiposReporte.addItem(Reportes.PRONOMBRES_REFLEXIVOS_POR_GENERO);
				break;
			case Conjuncion:
			    tiposReporte.addItem(Reportes.CONJUNCIONES_ALFABETICO);
			    tiposReporte.addItem(Reportes.CONJUNCIONES_POR_TIPO);
				break;
			case Preposicion:
			    tiposReporte.addItem(Reportes.PREPOSICIONES_ALFABETICO);
			    tiposReporte.addItem(Reportes.PREPOSICIONES_POR_CASO);
			    break;
			case Interjeccion:
			    tiposReporte.addItem(Reportes.INTERJECCIONES_ALFABETICO);
            case PronombreIndefinido:
                tiposReporte.addItem(Reportes.PRONOMBRES_INDEFINIDOS_POR_GENERO);
			    break;
			}
		}
		crear.setEnabled(!todaviaCargandoPanel && tieneValores);
	}


    public JButton getCrearMas() {
        return this.crearMas;
    }


    public ComboEnumeracion getMasTipos() {
        return this.masTipos;
    }
	
	

}
