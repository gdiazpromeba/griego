package kalos.visual.controles.selectores.jerarquicos;

import java.util.List;

import kalos.beans.TipoJerarquico;
import kalos.bibliotecadatos.JerarquiaBeans;
import kalos.bibliotecadatos.ListaSeleccionable;
import kalos.operaciones.OpBeans;
import kalos.recursos.Recursos;
import kalos.visual.controles.selectores.DialogSelectorBeans;

/**
 * Se utiliza para vincular datos de tipo jerárquico
 * (árbol) con un resultset principal.
 * Los clientes que lo usan normalmente van a querer no sólo un
 * registro seleccionado, sino sus ancestros o descendientes
 */
@SuppressWarnings("unchecked")
public class SelectorJerarquicoBeans<T extends TipoJerarquico> extends SelectorActivoBeans<T> {
	private List<T> seleccionadoEHijos;

	private List<T> seleccionadoYAncestros;

	String cadenaEtiquetaDescripcion;

	private boolean soloHojas;

	protected String tituloDialog;

	protected JerarquiaBeans<T> jerBeans;

	public SelectorJerarquicoBeans(boolean editable, boolean puedeSerNulo,
			boolean soloHojas, JerarquiaBeans<T> dmJerarquico, boolean griego,
			String tituloDialog) {
		super(null, editable, puedeSerNulo, griego, false, "desClave");
		this.jerBeans = dmJerarquico;
		super.beans = new ListaSeleccionable(jerBeans.getBeans());
		this.soloHojas = soloHojas;
		if (!puedeSerNulo)
			seleccionInicial();
	}

	protected DialogSelectorBeans<T> creaDialog() {
		return new DialogSelectorJerarquicoBeans<T>(tituloDialog, jerBeans,
				cadenaEtiquetaDescripcion, soloHojas);
	}
	
	
	/**
	 * Indica lo que se muestra al principio, incluso antes de que la ventana de selección siquiera se 
	 * haya utilizado.
	 * si la selección no puede ser nula, hay que encargarse de mostrar algo.
	 * Esto será la raíz (si se puede seleccionar nodos no-hoja) o el primer nodo hoja disponible
	 *
	 */
	private void seleccionInicial(){
		String idASeleccionar;
		if (!soloHojas){
			idASeleccionar=null;//significa que seleccionamos la raíz
		}else{ //
			List<T> hojas=jerBeans.getHojas((String)null);
			T primeraHoja=hojas.get(0);
			idASeleccionar=OpBeans.getId(primeraHoja);
		}
		fuerzaSeleccion(idASeleccionar); 
		seleccionadoEHijos=jerBeans.getHojas((String)idASeleccionar);
		seleccionadoYAncestros=jerBeans.getRegistroYAncestros((String)idASeleccionar);
	}
	

	/**
	 * Muestra la dialog con el tree. 
	 * Al volver (de Aceptar) fuerza la selección en el 
	 * datamodule interno y puebla los dos arrays de Registros "ancestros" y "descendientes"
	 */
	public void muestraDialog() {
		dialog = creaDialog();
		dialog.setSize(540, 380); //áureo
		dialog.setLocationRelativeTo(null);
		dialog.setModal(true);
		String pk = jerBeans.getPK();
		((DialogSelectorJerarquicoBeans) dialog).setSeleccionado(pk);
		dialog.setVisible(true);
		if (dialog.isAcepto()) {
			fuerzaSeleccion(dialog.getPK());
			seleccionadoEHijos = ((DialogSelectorJerarquicoBeans) dialog)
					.getHojasDeSeleccion();
			seleccionadoYAncestros = ((DialogSelectorJerarquicoBeans) dialog)
					.getSeleccionadoYAncestros();
			txtDescripcion.setText(getDescripcion());
		}
		dialog.dispose();
	}

	/**
	 * crea una String que consiste en todos los valores de descripción concatenados por barras "/", 
	 * desde la raíz hasta el nodo seleccionado
	 */
	protected String getDescripcion() {
		StringBuffer sb = new StringBuffer();
		seleccionadoYAncestros = jerBeans.getRegistroYAncestros(jerBeans.getPK());
		for (int i = 0; i < seleccionadoYAncestros.size(); i++) {
			T bean = seleccionadoYAncestros.get(i);
			String contenidoDescripcion = OpBeans.getPropiedad(bean,
					columnaDescripcion);
			sb.append(Recursos.getCadena(contenidoDescripcion));
			if (i < seleccionadoYAncestros.size() - 1)
				sb.append("/");
		}
		return sb.toString();
	}

	@Override
	public void fuerzaSeleccion(String pk) {
		jerBeans.setPK(pk);
		txtDescripcion.setText(getDescripcion());
		super.beans.setPK(pk);
	}

	@Override
	public String getIdSeleccionado() {
		return jerBeans.getPK();
	}
	
	
	/**
	 * Los tipos jerárquicos tienen un "valor entero", que es una clave legible, paralela a la real.
	 * Eso es lo que devuelvo aqué.
	 * @return  el valore entero seleccionado en ese momento 
	 */
	public int getEnteroSeleccionado(){
		jerBeans.getPK();
		TipoJerarquico bean=(TipoJerarquico)getBeanSeleccionado();
		return bean.getValorEntero();
	}

	/**
	 * Devuelve el registro seleccionado más sus descendientes
	 * @return
	 */
	public List<T> getSeleccionadoEHijos() {
		return seleccionadoEHijos;
	}

	/**
	 * Devuelve el registro seleccionado más sus ancestros
	 * @return
	 */
	public List<T> getSeleccionadoYAncestros() {
		return seleccionadoYAncestros;
	}

}
