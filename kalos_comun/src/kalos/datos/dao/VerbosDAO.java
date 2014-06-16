package kalos.datos.dao;

import java.util.List;

import kalos.beans.VerboBean;
import kalos.enumeraciones.LugarSubcadena;

public interface VerbosDAO  {
	
	List<String> seleccionaPorLetra(String letra);
	
	List<String> seleccionaPorTipos(Integer[] tipos);

	VerboBean seleccionaInidvidual(String verboId);
    
    VerboBean seleccionaInidvidualParaAM(String verboId);
    
    List<VerboBean> getRegistros(List<String> ids);
	
	void inserta(VerboBean ea);

	void modifica(VerboBean ea);
    
    /**
     * multiplica todos los códigos de una letra por 100
     * (paso previo a ordenar)
     */
    public void modificaCodigosTodos(String letra);
    
    /**
     * cambia el código entero de un verbo (como resultado de un
     * ordenamiento)
     * @param letra
     */
    public void modificaCodigoIndividual(int nuevoCodigo, String idSustantivo);    

	void borra(String verboId);
	
	
	/**
	 * selecciona por el verbo o una subcadena de éste, en la forma fácil
	 * @param neutralizada
	 * @param lugar
	 * @return
	 */
	public List<String> seleccionaPorVerbo(String neutralizada, LugarSubcadena lugar);
	
	/**
	 * selección rápida especial para el análisis morfológico.
	 * no vincula con Significados, y busca sólo entre los verbos que
	 * no son dibujados
	 * @param verbo
	 * @return
	 */
	List<VerboBean> seleccionaPorVerboParaAM(String verbo);
	
	/**
	 * selección "rápida" (sin join con significados) de entradas de verbo por ids, 
	 * para el análisis morfológico
	 * @param ids
	 * @return
	 */
	List<VerboBean> getRegistrosSinSignificado(List<String> ids);

}