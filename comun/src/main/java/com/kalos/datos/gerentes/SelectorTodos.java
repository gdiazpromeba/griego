package com.kalos.datos.gerentes;

import java.util.List;

/**
 * esta interfaz indica que el Gerente correspondiente tiene
 * un método para seleccionar todos los beans, sin parámetros.
 * Es útil en los selectores, que guardan todos los datos en su interior
 * @author gdiaz
 *
 */
public interface SelectorTodos {
	
	/**
	 * devuelve todos los beans disponibles de un tipo
	 * @return
	 */
	List<?> getTodos();

}
