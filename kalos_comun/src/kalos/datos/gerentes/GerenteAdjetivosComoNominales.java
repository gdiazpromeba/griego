package kalos.datos.gerentes;

import java.util.List;

import kalos.beans.AdjetivoComoNominalBean;

public interface GerenteAdjetivosComoNominales {

	void borraTodo();

	void inserta(AdjetivoComoNominalBean bean);

	List<AdjetivoComoNominalBean>  seleccionaPorGenitivoParaAM(String genitivo);

	List<AdjetivoComoNominalBean> seleccionaPorNominativoAM(String nominativo);
	
	List<AdjetivoComoNominalBean> seleccionaPorTipos(Integer[] tiposEnteros);
	
	void borraAdjetivo(String id);
	
	void insertaVarios(List<AdjetivoComoNominalBean> lote);
}