/*
 * Created on Apr 26, 2004
 */
package kalos.utilidades.integridad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kalos.datos.ActSustantivos;
import kalos.datos.modulos.resultados.DMIrrParticipiosEnteros;
import kalos.datos.modulos.resultados.DMSustantivos;
import kalos.datos.objetos.Registro;
import kalos.enumeraciones.Beta;
import kalos.enumeraciones.Genero;
import kalos.flexion.declinacion.Declina;
import kalos.flexion.declinacion.ProblemaSugiriendoDeclinacion;
import kalos.operaciones.Comparador;
import kalos.operaciones.OpPalabras;
import kalos.recursos.Configuracion;


/**
 * @author GDiaz
 *
 */
public class IntegridadNominal {
	public static void main(String[] args){
		try {
			KalosApp.inicializa();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		verificaTipos(true);
		tiposCuboParticipios();
		tiposIrrParticipiosEnteros(true);
	}	
	
	/**
	 * Averigua los tipos de sustantivos, excepto para aquellos que
	 * están marcados como 4 (inclasificables)
	 * Opcionalmente fuerza la clasificación automáticamente
	 *
	 */
	private static void verificaTipos(boolean corrige){	
		DMSustantivos dm=new DMSustantivos("TODOS", new String[]{"NOMINATIVO", "GENITIVO", "TIPO_SUSTANTIVO", "SIGNIFICADO_" + Configuracion.getIdioma()}, null);
		for (int i=0; i<dm.getRowCount(); i++){
			Registro reg=dm.getFila(i);
			String nominativo=reg.getString("NOMINATIVO");
			String genitivo=reg.getString("GENITIVO");
			String letra=reg.getString("LETRA");
			Integer codigo=reg.getInteger("CODIGO");
			int tipoSugerido=0;
			int tipoTabla=reg.getInt("TIPO_SUSTANTIVO");
			Genero genero=Genero.getEnum(reg.getString("GENERO"));
			if (tipoTabla!=4){
				try{
					tipoSugerido=Declina.sugiereDeclinacion(nominativo, genitivo, genero);
					
					
					if (tipoSugerido!=tipoTabla){
						StringBuffer sb=new StringBuffer(100);
						sb.append("\ntipos distintos para la letra " + letra + " codigo " + codigo.intValue()  );
						sb.append("\n    sugerido=" + tipoSugerido + " existente " + tipoTabla );
						sb.append("\n    nominativo=" + OpPalabras.strCompletoABeta(nominativo) + " genitivo="  + OpPalabras.strCompletoABeta(genitivo));
						sb.append("\n    " + reg.getString("SIGNIFICADO_" + Configuracion.getIdioma()));
						System.out.println(sb.toString());
						if (corrige){
							ActSustantivos.actTipo(letra, codigo, new Integer(tipoSugerido));
						}
					}
					
				}catch(ProblemaSugiriendoDeclinacion ex){
			    	StringBuffer sb=new StringBuffer("\n *** problemas al intentar sugerir declinación para las siguientes formas me impiden continuar: *** \n");
			    	sb.append("nominativo=" + OpPalabras.strCompletoABeta(nominativo) + "\n");
			    	sb.append("genitivo=" + OpPalabras.strCompletoABeta(genitivo) + "\n");
			    	sb.append("el mensaje es " +  ex.getMessage() + "\n");
			    	System.out.println(sb.toString());
			    	System.out.println("letra " + letra + " codigo " + codigo + "\n");
			    	System.out.println(reg.getString("SIGNIFICADO_" + Configuracion.getIdioma()));
					return;
				}catch(Exception ex){
			    	System.out.println("problema general en el siguiente sustantivo");
			    	System.out.println("nominativo=" + OpPalabras.strCompletoABeta(nominativo) + "\n");
			    	System.out.println("genitivo=" + OpPalabras.strCompletoABeta(genitivo) + "\n");
			    	System.out.println("letra " + letra + " codigo " + codigo + "\n");
			    	System.out.println(reg.getString("SIGNIFICADO_" + Configuracion.getIdioma()));
					ex.printStackTrace();
				}
				
			}
	      }
			
		}	
	
	
	
	/**
	 * Esta clase puebla el campo TIPO_SUSTANTIVO de la tabla TIPOS_CUBO_PART
	 * (como esos tipos están sujetos a cambio, es más fácil regenerarlos con esto).
	 * Dicha tabla apunta a contener los tipos de sustantivo permitidos para cada 
	 * voz, aspecto, fuerte, género. 
	 * 
	 */

	static void tiposCuboParticipios(){
		
		try{
			KalosApp.con.setAutoCommit(false);
			StringBuffer sbSel=new StringBuffer("SELECT * FROM CUBOS_TIPO_PART");
			Statement stmSel=KalosApp.con.createStatement();
			ResultSet rs=stmSel.executeQuery(sbSel.toString());
			//el que actualiza
			Statement stmAct=KalosApp.con.createStatement();
			while (rs.next()){
			    String nominativo=rs.getString("NOMINATIVO");
			    String genitivo=rs.getString("GENITIVO");
			    Genero genero=Genero.getEnum(rs.getString("GENERO"));
			    String nominativoCompleto=OpPalabras.strBetaACompleto(nominativo);
			    String genitivoCompleto=OpPalabras.strBetaACompleto(genitivo);
			    System.out.println(nominativo + "  -  " + genitivo);
			    int tipoSust=Declina.sugiereDeclinacion(nominativoCompleto, genitivoCompleto, genero);
			    stmAct.executeUpdate("UPDATE CUBOS_TIPO_PART SET TIPO_SUSTANTIVO=" + tipoSust +  " WHERE NOMINATIVO='" + nominativo + "' AND GENITIVO='" + genitivo +"'");
			}
			KalosApp.con.commit();
			
		}catch(SQLException ex){
			System.out.println("error en  generaFormasRegularesParaBasileuw() al averiguar o setear el autocommit de la conexión ");
		}
	}

	
	/**
	 * Averigua los tipos nominales de IRR_PARTICIPIOS_ENTEROS,
	 * y los compara con la tabla.
	 * Opcionalmente fuerza la clasificación 
	 *
	 */
	private static void tiposIrrParticipiosEnteros(boolean corrige){	
		DMIrrParticipiosEnteros dm=new DMIrrParticipiosEnteros("TODOS", DMIrrParticipiosEnteros.getTodasNoClave() , null);
		for (int i=0; i<dm.getRowCount(); i++){
			Registro reg=dm.getFila(i);
			String nominativo=reg.getString("NOMINATIVO");
			String genitivo=reg.getString("GENITIVO");
			int secuencia=reg.getInt("SECUENCIA");
			int tipoSugerido=0;
			int tipoTabla=reg.getInt("TIPO_SUSTANTIVO");
			Genero genero=Genero.getEnum(reg.getString("GENERO"));
			if (tipoTabla!=4){
				try{
					tipoSugerido=Declina.sugiereDeclinacion(nominativo, genitivo, genero);
					
					
					if (tipoSugerido!=tipoTabla){
						StringBuffer sb=new StringBuffer(100);
						sb.append("\ntipos distintos para la secuencia " + secuencia   );
						sb.append("\n    sugerido=" + tipoSugerido + " existente " + tipoTabla );
						sb.append("\n    nominativo=" + OpPalabras.strCompletoABeta(nominativo) + " genitivo="  + OpPalabras.strCompletoABeta(genitivo));
						System.out.println(sb.toString());
						if (corrige){
						    Statement stm=KalosApp.con.createStatement();
						    stm.executeUpdate("UPDATE IRR_PARTICIPIOS_ENTEROS SET TIPO_SUSTANTIVO=" + tipoSugerido + " WHERE SECUENCIA=" + secuencia);
						    KalosApp.con.commit();
						}
					}
					
				}catch(ProblemaSugiriendoDeclinacion ex){
			    	StringBuffer sb=new StringBuffer("\n *** problemas al intentar sugerir declinación para las siguientes formas me impiden continuar: *** \n");
			    	sb.append("nominativo=" + OpPalabras.strCompletoABeta(nominativo) + "\n");
			    	sb.append("genitivo=" + OpPalabras.strCompletoABeta(genitivo) + "\n");
			    	sb.append("el mensaje es " +  ex.getMessage() + "\n");
			    	System.out.println(sb.toString());
			    	System.out.println("secuencia " + secuencia + " \n");
					return;
				}catch(Exception ex){
			    	System.out.println("problema general en el siguiente sustantivo");
			    	System.out.println("nominativo=" + OpPalabras.strCompletoABeta(nominativo) + "\n");
			    	System.out.println("genitivo=" + OpPalabras.strCompletoABeta(genitivo) + "\n");
			    	System.out.println("secuencia  " + secuencia + " \n");
					ex.printStackTrace();
				}
				
			}
	      }
			
		}	

	
	
	
	

	public static void orden(){
		for (int i=0; i<Beta.arrBeta.length; i++){
			System.out.println("***reporte para la  letra  " + Beta.arrBeta[i] + " **** ");
			orden(new String(new char[]{ Beta.arrBeta[i]}));
		}
		
	}
	
	private static void orden(String letra){
		DMSustantivos dm=new DMSustantivos("LETRA_INICIAL", new String[]{"CODIGO", "LETRA", "NOMINATIVO"}, new String[]{letra});
		if (dm.getRowCount()==0){
			System.out.println("la letra " + letra  + " no contiene formas todavía");
			return;
		}
		Registro regAnterior=dm.getFila(0);
		for (int i=1; i<dm.getRowCount(); i++){
			String cadenaAnterior=regAnterior.getString("NOMINATIVO");
			int codigoAnterior=regAnterior.getInt("CODIGO");
			Registro regActual=dm.getFila(i);
			String cadenaActual=regActual.getString("NOMINATIVO");
			int codigoActual=regActual.getInt("CODIGO");
			int resultado=Comparador.compara(cadenaAnterior, cadenaActual);
			if (resultado==1){
				StringBuffer sb=new StringBuffer();
				sb.append(OpPalabras.strCompletoABeta(cadenaAnterior) + "-" + codigoAnterior + "\n");
				sb.append(OpPalabras.strCompletoABeta(cadenaActual) + "-" + codigoActual + "\n");
				System.out.println(sb.toString());
			}
			regAnterior=regActual;
		}
	}


}
