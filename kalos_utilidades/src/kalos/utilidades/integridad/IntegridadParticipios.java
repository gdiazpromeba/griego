/*
 * Created on Apr 26, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package kalos.utilidades.integridad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;

import kalos.KalosApp;
import kalos.datos.modulos.resultados.DMCubosTiposPart;
import kalos.datos.modulos.resultados.DMIrrParticipiosEnteros;
import kalos.datos.modulos.resultados.DMVerbos;
import kalos.datos.objetos.Registro;
import kalos.enumeraciones.Genero;
import kalos.flexion.declinacion.Declina;
import kalos.flexion.declinacion.Participios;
import kalos.operaciones.OpPalabras;


/** 
 * @author GDiaz
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class IntegridadParticipios {
	public static void main(String[] args){
		try {
			KalosApp.inicializa();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (false){
		  verificaTiposIrrParticipiosEnteros(true);
		  verificaTiposCubos();
		  flexionaTodo();
		}
	}	
	
	/**
	 * En la tabla IRR_PARTICIPIOS_ENTEROS,
	 * verifica que el tipo de sustantivo corresponda con
	 * el nominativo y el genitivo entrados. Opcionalmente lo cambia 
	 *
	 */
	private static void verificaTiposIrrParticipiosEnteros(boolean corrige){
		System.out.println("IRR_PARTICIPIOS_ENTEROS ...");
		DMIrrParticipiosEnteros dm=new DMIrrParticipiosEnteros("TODOS", DMIrrParticipiosEnteros.getTodasNoClave(), null);
		for (int i=0; i<dm.getRowCount(); i++){
		  Registro reg=dm.getFila(i);
		  String nominativo=reg.getString("NOMINATIVO");
		  String genitivo=reg.getString("GENITIVO");
		  int conta=reg.getInt("CODIGO");
		  String partic=reg.getString("PARTIC");
		  Genero genero=Genero.getEnum(reg.getString("GENERO"));
		  int tipoSugerido=0;
		  int tipoTabla=reg.getInt("TIPO_SUSTANTIVO");
		  try{
		    tipoSugerido=Declina.sugiereDeclinacion(nominativo, genitivo, genero);
		  }catch(RuntimeException ex){
			System.out.println("problemas sugiriendo para participios del verbo=" + conta + " particularidad " + partic  );
		  	ex.printStackTrace();
		  	return;
		  }
		  if (tipoSugerido!=tipoTabla){
			System.out.println("tipos distintos para participios del verbo=" + conta + " particularidad " + partic  + 
			"\n    sugerido=" + tipoSugerido + " existente " + tipoTabla + " nominativo=" + OpPalabras.strCompletoABeta(nominativo));
			if (corrige){
		      reg.setInt("TIPO_SUSTANTIVO", tipoSugerido);
		      reg.setString("NOMINATIVO", OpPalabras.strCompletoABeta(nominativo));
			  reg.setString("GENITIVO", OpPalabras.strCompletoABeta(genitivo));
		      dm.actualizaSQL(reg);
		    }
		  }
		}			  
	  
	}

	
	private static void verificaTiposCubos(){
		System.out.println("CUBOS...");
		DMCubosTiposPart dm=new DMCubosTiposPart("TODOS", DMCubosTiposPart.getTodasNoClave(), null);
		for (int i=0; i<dm.getRowCount(); i++){
		  Registro reg=dm.getFila(i);
		  String nominativo=reg.getString("NOMINATIVO");
		  String genitivo=reg.getString("GENITIVO");
		  Genero genero=Genero.getEnum(reg.getString("GENERO"));
		  int tipoSugerido=0;
		  int tipoTabla=reg.getInt("TIPO_SUSTANTIVO");
		  try{
		    tipoSugerido=Declina.sugiereDeclinacion(OpPalabras.strBetaACompleto(nominativo), OpPalabras.strBetaACompleto(genitivo), genero);
		  }catch(RuntimeException ex){
			System.out.println("problemas sugiriendo para participios con nominativo verbo=" + nominativo + " genitivo " + genitivo );
		  	ex.printStackTrace();
		  	return;
		  }
		  if (tipoSugerido!=tipoTabla){
			System.out.println("tipos distintos para participios con nominativo =" + nominativo + " genitivo " + genitivo  + 
			"\n    sugerido=" + tipoSugerido + " existente " + tipoTabla );
		  }
		}			  
	  
	}
	
	
	private static void flexionaTodo(){
			try {
				DMVerbos dm=new DMVerbos("TODOS", DMVerbos.getTodasNoClave(), null);
				OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(new File("conjugatodo.log")));
				long comienzo=System.currentTimeMillis();
				osw.write("comenzando en " + new Date());
				for (int i=0; i<dm.getRowCount(); i++){
					Registro reg=dm.getFila(i);
					String letra=reg.getString("LETRA");
					Integer codigo=reg.getInteger("CODIGO");
					try {
						Participios.flexiona(letra, codigo.intValue(), new HashMap(), new HashMap());	
						if (i%100==0){
							osw.write("procesando " + OpPalabras.strCompletoABeta(reg.getString("VERBO")) + "  código " + codigo + " transcurrido= " + tiempo(System.currentTimeMillis()-comienzo) + "\n");
							System.out.println("procesando " + OpPalabras.strCompletoABeta(reg.getString("VERBO")) + "  código " + codigo + " transcurrido= " + tiempo(System.currentTimeMillis()-comienzo) + "\n");
						}
						
					} catch (Exception e) {
						e.printStackTrace();
						osw.write("**** error en " + OpPalabras.strCompletoABeta(reg.getString("VERBO")) + "  código " + codigo + " transcurrido= " + tiempo(System.currentTimeMillis()-comienzo) + "\n");
					}
					
				}
				osw.write("terminado en  " + new Date());
				osw.write("tiempo total =  " + tiempo(System.currentTimeMillis()-comienzo) + "\n");
				osw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			
		}
	

	private static  String tiempo(long milisegundos){
			long segundos=milisegundos /1000;
			long minutos=segundos/60;
			long horas=minutos/60;
			
			minutos=minutos%60;
			segundos=segundos%60;
			

			StringBuffer sb=new StringBuffer();
			if (horas>0)
				sb.append(horas + " horas ");
			if (minutos>0)
				sb.append(minutos + " minutos ");
			if (segundos>0)
				sb.append(segundos + " segundos ");
			return sb.toString();
		}
		
}
