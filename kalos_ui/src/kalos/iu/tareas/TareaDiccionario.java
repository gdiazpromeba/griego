package kalos.iu.tareas;

import java.util.List;

import kalos.beans.EntradaDiccionario;
import kalos.datos.adaptadores.AdaptadorGerenteDiccionario;
import kalos.enumeraciones.Ignorancia;
import kalos.enumeraciones.LugarSubcadena;
import kalos.enumeraciones.TipoPalabra;
import kalos.visual.modelos.DictionaryPM;
import kalos.visual.modelos.PagingModel;
import foxtrot.Task;

public class TareaDiccionario extends Task {

	private AdaptadorGerenteDiccionario adaptadorGerenteDiccionario;
	private String cadena;
	private Ignorancia ignorancia;
	private PagingModel <EntradaDiccionario> pagingModel; 
	private LugarSubcadena lugarSubcadena;
	private List<TipoPalabra> tipos;
	
//	Logger logger=Logger.getLogger(this.getClass().getName());
	


	@Override
	/**
	 * returns a PagingModel or null if there is any problem
	 */
	public Object run() throws Exception {
		switch(ignorancia){
			case Nada:
				adaptadorGerenteDiccionario.seleccionaPorNormal(cadena, lugarSubcadena, tipos);
				break;
			case SignosLargaCorta:
				adaptadorGerenteDiccionario.seleccionaPorSinLargas(cadena, lugarSubcadena, tipos);
				break;
			default:
				adaptadorGerenteDiccionario.seleccionaPorNeutralizada(cadena, lugarSubcadena, tipos);
			}
		pagingModel=new DictionaryPM(adaptadorGerenteDiccionario, 100);
		return pagingModel;
	}



	public TareaDiccionario(AdaptadorGerenteDiccionario adaptadorGerenteDiccionario, String cadena, 
			Ignorancia ignorancia, LugarSubcadena lugarSubcadena, 
			List<TipoPalabra> tipos) {
		super();
		this.adaptadorGerenteDiccionario = adaptadorGerenteDiccionario;
		this.cadena = cadena;
		this.ignorancia = ignorancia;
		this.lugarSubcadena=lugarSubcadena;
		this.tipos=tipos;
	}


}
