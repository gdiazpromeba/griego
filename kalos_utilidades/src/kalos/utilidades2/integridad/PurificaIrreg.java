/*
 * Created on 07/03/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package kalos.utilidades.integridad;

import java.sql.ResultSet;
import java.sql.Statement;

import kalos.KalosApp;
import kalos.datos.modulos.resultados.DMVerbos;
import kalos.datos.objetos.Registro;
import kalos.flexion.conjugacion.ProveedorDMVerbal;
;

/**
 * Contiene varios tests que producen varias conjugaciones autom'aticamente
 * @author gonzy
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class PurificaIrreg {

//TODO: hacer tests de participios contemplando las 3 capas: IRR_VERBOS respecto de lo normal,
// irr_participios enteros, e irr_participios_simples
	
	
	public static void main(String[] args){
	  if(false){
		verificaIrregIndividuales();
		conjugatodo();
		verificaParticipiosEnteros();
	  }
	}
	
	
	/**
	 * Se utiliza para saber si hay formas en la tabla IRR_VERBOS_INDIVIDUALES que sean innecesarias.
	 *
	 */
	public static void verificaIrregIndividuales(){
		
//		StringBuffer sb=new StringBuffer(200);
//		sb.append("select distinct LETRA, CONTA from IRR_VERBOS_INDIVIDUALES");
//		ResultSet rs;
//		int conta=0;
//		try {
//			KalosApp.inicializa();
//			Statement stmt=KalosApp.con.createStatement();
//			rs=stmt.executeQuery(sb.toString());
//			while(rs.next()){
//				String letra=rs.getString("LETRA");
//			    conta=rs.getInt("CONTA");
//				DMVerbos dm=new DMVerbos(DMVerbos.LETRA_CODIGO, DMVerbos.getTodasNoClave(), new Object[]{letra, Enteros.get(conta)});
//				Registro regAux=dm.getFila(0);
//				DefaultTableModel tm=ProveedorDMVerbal.modeloVerbosBasico(regAux.getString("LETRA"), regAux.getInt("CONTA"),  regAux.getString("VERBO"), regAux.getInt("DIBUJADO")!=0, regAux.getInt("TIPO"), true);
//				Statement stmVerbo=KalosApp.con.createStatement();
//				ResultSet rsVerbo=stmVerbo.executeQuery("SELECT * FROM IRR_VERBOS_INDIVIDUALES WHERE CONTA=" + regAux.getInt("CONTA"));
//				while (rsVerbo.next()){
//					for (Iterator it=tm.getDataVector().iterator(); it.hasNext();){
//						Vector veg=(java.util.Vector)it.next();
//						int voz=((Integer)veg.get(2)).intValue();
//						int tiempo=((Integer)veg.get(3)).intValue();
//						int fuerte=((Integer)veg.get(4)).intValue();
//						int persona=((Integer)veg.get(5)).intValue();
//						int modo=((Integer)veg.get(6)).intValue();
//						String forma=(String)veg.get(7);
//						if (rsVerbo.getInt("NVOZ")==voz && rsVerbo.getInt("NTIE")==tiempo && rsVerbo.getInt("PERS") ==persona && rsVerbo.getInt("MODO")==modo &&  OpPalabras.strBetaACompleto(rsVerbo.getString("FORMA")).equals(forma)){
//							System.out.println(" DELETE FROM IRR_VERBOS_INDIVIDUALES WHERE CONTA=" + rs.getInt("CONTA") + " AND NVOZ=" + voz + "  AND NTIE= " + tiempo +  " AND MODO=" + modo + " AND PERS =" + persona + " AND FORMA='" +  OpPalabras.strCompletoABeta(forma) + "'");
//						}
//					}
//				}
//        stmVerbo.close();				
//			}
//			stmt.close();
//		} catch (Exception e) {
//			System.out.println("falló en conta=" + conta);
//			e.printStackTrace();
//		} 
//		
		
		
	}
	

	
	/**
	 * Se utiliza para comprobar si la tabla IRR_PARTICIPIOS_ENTEROS contiene informaci'on redundante
	 *
	 */
	public static void verificaParticipiosEnteros(){
		
		StringBuffer sb=new StringBuffer(200);
		sb.append("select distinct CONTA from IRR_PARTICIPIOS_ENTEROS WHERE CONTA=27350");
		ResultSet rs;
		int conta=0;
		try {
			KalosApp.inicializa();
			Statement stmt=KalosApp.con.createStatement();
			rs=stmt.executeQuery(sb.toString());
			while(rs.next()){
				conta=rs.getInt("CONTA");
				System.out.print(".");
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("falló en conta=" + conta);
			e.printStackTrace();
		} 
		
		
		
	}

	
	
	
	/**
	 * Conjuga todos los verbos, para asegurarse de que no se produzcan errores en el proceso
	 *
	 */
	public static void conjugatodo(){
		
		StringBuffer sb=new StringBuffer(200);
		sb.append("select CODIGO from VERBOS WHERE CONTA>58000 ORDER BY CONTA");
		ResultSet rs;
		int conta=0;
		try {
			KalosApp.inicializa();
			Statement stmt=KalosApp.con.createStatement();
			rs=stmt.executeQuery(sb.toString());
			while(rs.next()){
				conta=rs.getInt("CODIGO");
				String letra=rs.getString("LETRA");
				System.out.print(".");
				if (conta % 1000==0 ) System.out.print(conta +   "\n"); 
				DMVerbos dm=new DMVerbos("CONTA_2", DMVerbos.getTodasNoClave(),  new Object[]{letra, new Integer(conta)});
				Registro regAux=dm.getFila(0);
				ProveedorDMVerbal.modeloVerbosBasico(regAux.getString("LETRA"), regAux.getInt("CONTA"),  true);
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("falló en conta=" + conta);
			e.printStackTrace();
		} 
		
		
		
	}



}
