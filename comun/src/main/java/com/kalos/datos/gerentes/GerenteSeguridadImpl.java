package com.kalos.datos.gerentes;

import com.kalos.datos.dao.SeguridadDAO;
import com.kalos.recursos.Configuracion;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


public class GerenteSeguridadImpl implements GerenteSeguridad {

	private SeguridadDAO seguridadDAO;

	boolean primeraConsulta=true;
	private static Logger logger=Logger.getLogger(GerenteSeguridadImpl.class.getName());
	

	//
	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteSeguridad#getNombre()
	 */
	@Override
	public String getNombre(){
		if (primeraConsulta){
			primeraConsulta=false;
			Configuracion.setNombre(seguridadDAO.getNombre());
		}
		logger.debug("el nombre es" + Configuracion.getNombre());
		return Configuracion.getNombre();
	}

	
	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteSeguridad#registra(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean registra(String nombre, String clave){
		logger.debug("registrando nombre=" + nombre + " clave=" + clave);
		String claveCalculada=generaClave(nombre);
		if (clave.equals(claveCalculada)){
			logger.debug("escribiendo en seguridadDAO");
			seguridadDAO.inserta(nombre, clave);
			logger.debug("después de insertar");
			Configuracion.setNombre(nombre);
			logger.debug("se setea el nombre" + nombre);
			return true;
		}else{
			logger.debug("la clave no es igual a la calculada");
			return false;
		}
	}
	
	
	
	
	
	
	/**
	 * método que se puede ejecutar independientemente cuando alguien pide la clave de 
	 * activacion. No necesita conexión con la base de datos.
	 * @param args
	 */
	public static void main(String[] args){
		System.out.println(new GerenteSeguridadImpl().generaClave("Stefano Belfiore"));
	}
	
	/**
	 * dado un nombre, genera una clave numérica que es función de él
	 * @param nombre
	 * @return
	 */
	private String generaClave(String nombre){
		//sumo los caracteres ascii de todo
		long suma=0;
		for (int i=0; i<nombre.length(); i++){
			suma+=nombre.charAt(i);
		}
		//le sumo PI
		double masPi=suma+Math.PI;
		//saco la raíz cuadrada
		double raiz=Math.pow(masPi, 0.5);
		//lo convierto a String
		String cadena=Double.toString(raiz);
		//quito el punto si lo hay
		cadena=cadena.replaceAll("\\.", "");
		//corto o relleno a 16 lugares, según el tamaño
		cadena=StringUtils.rightPad(cadena, 16, '0');
		cadena=cadena.substring(0, 16);
		//corto en 4 pedazos
		String pedazo1=cadena.substring(0, 4);
		String pedazo2=cadena.substring(4, 8);
		String pedazo3=cadena.substring(8, 12);
		String pedazo4=cadena.substring(12, 16);
		return pedazo1 + "-" + pedazo2 + "-" + pedazo3 + "-" + pedazo4;
	}


	/* (non-Javadoc)
	 * @see com.kalos.datos.gerentes.GerenteSeguridad#setSeguridadDAO(kalos.datos.dao.SeguridadDAO)
	 */
	@Override
	public void setSeguridadDAO(SeguridadDAO seguridadDAO) {
		this.seguridadDAO = seguridadDAO;
	}
        

}
