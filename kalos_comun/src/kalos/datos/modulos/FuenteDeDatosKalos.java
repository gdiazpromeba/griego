package kalos.datos.modulos;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * Fuente de datos que acepta una clave de usuario encriptada
 * @author gdiaz
 *
 */
public class FuenteDeDatosKalos extends BasicDataSource {


//	Logger logger=Logger.getLogger(this.getClass().getName());



	/**
	 * Encripta o desencripta una palabra. 
	 * Primero la usé externamente para encriptar una clave.
	 * Luego la uso internamente para desencriptarla.
	 * 
	 * @param s				la palabra a encriptar/desencriptar
	 * @param codificar     true=encriptar, false=desencriptar
	 * @return
	 */
	public static  String messup(String s, boolean codificar) {
		StringBuffer sb=new StringBuffer(s.length());
		String semilla="mkopnjibhuvgycftxdrzseawq";
		for (int i = 0; i < s.length(); i++){
			char c=s.charAt(i);
			if (codificar)
				c=semilla.charAt((int)c-97);
			else{
				c=(char)(semilla.indexOf(c)+97);
			}sb.append(c);
		}
		return sb.toString();
	}

	/* descarta al método homóninmo de BasicDataSource, con la diferencia de que 
	 * acept una clave encriptada
	 * @see org.apache.commons.dbcp.BasicDataSource#setPassword(java.lang.String)
	 */
	@Override
	public synchronized void setPassword(String clave) {
		clave=messup(clave, false);
		super.setPassword(clave);
	}

}
