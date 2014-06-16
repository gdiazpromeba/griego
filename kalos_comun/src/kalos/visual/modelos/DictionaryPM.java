package kalos.visual.modelos;

import kalos.beans.EntradaDiccionario;
import kalos.beans.Significado;
import kalos.bibliotecadatos.FuenteDatosCacheable;
import kalos.operaciones.OpSignificados;
import kalos.recursos.CadenasEnum;

public class DictionaryPM extends PagingModel<EntradaDiccionario> {

	public DictionaryPM(FuenteDatosCacheable fuenteDatosCacheable, int tamañoPagina) {
		super(fuenteDatosCacheable, tamañoPagina);
	}
	
	// Work only on the visible part of the table.
	public Object getValueAt(int row, int col) {
		EntradaDiccionario ed=(EntradaDiccionario)getFila(row);
		switch (col){
		 case 0:
			 return ed.getNormal();
		 case 1: 
			 return CadenasEnum.getCadena(ed.getTipoPalabra());
		 case 2:
			 Significado significado=OpSignificados.getSignificadoIndividual(ed);
			 return significado.getValor();
		}
		return null;
//		return data[realRow].getValueAt(col);
	}
	
	public int getColumnCount(){
		return 3;
	}


}
