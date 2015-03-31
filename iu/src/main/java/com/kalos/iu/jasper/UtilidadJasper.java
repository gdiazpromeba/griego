// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.iu.jasper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import com.kalos.enumeraciones.Reportes;
import com.kalos.flexion.CeldaReporte;
import com.kalos.flexion.UtilidadesTM;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRewindableDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;

// Referenced classes of package kalos.iu.B:
//            C, A

public class UtilidadJasper {

	public void setUtilidadesTM(UtilidadesTM c) {
		utildadesTM = c;
	}

	public UtilidadJasper() {
		logger = Logger.getLogger(getClass().getName());
		mapJaspers = new HashMap();
		mapTiposReporte = new HashMap();
		pueblaTiposReporte();
	}

	public static JRRewindableDataSource creaFuenteJR(DefaultTableModel defaulttablemodel) {
		int i = defaulttablemodel.getRowCount();
		int j = defaulttablemodel.getColumnCount();
		for (int k = 0; k < i; k++) {
			for (int l = 0; l < j; l++) {
				Object obj = defaulttablemodel.getValueAt(k, l);
				if (obj instanceof CeldaReporte) {
					CeldaReporte n = (CeldaReporte) obj;
					defaulttablemodel.setValueAt(n.getContenido(), k, l);
				}
			}

		}

		FuenteJasperTM c = new FuenteJasperTM(defaulttablemodel);
		return c;
	}

	public JRViewerKalos visorJasper(Reportes tipoReporte, Map map, DefaultTableModel defaulttablemodel) {
		String nombreArchivoReporte = getArchivoReporte(tipoReporte, utildadesTM.nombresColumna(defaulttablemodel));
	    String pathArchivoReporte = "armado_reportes/" + nombreArchivoReporte;
		try {
			JasperReport jasperreport = (JasperReport) mapJaspers.get(pathArchivoReporte);
			if (jasperreport == null) {
				jasperreport = (JasperReport) JRLoader.loadObject(pathArchivoReporte);
				mapJaspers.put(pathArchivoReporte, jasperreport);
			}
			JasperPrint jasperprint = JasperFillManager.fillReport(jasperreport, map, new JRTableModelDataSource(defaulttablemodel));
			JRViewerKalos a = new JRViewerKalos(jasperprint);
			return a;
		} catch (JRException jrexception) {
			logger.error("error al crear el visor de jasper", jrexception);
		}
		return null;
	}

	private void pueblaTiposReporte() {
		mapTiposReporte.put(Reportes.VERBOS_POR_MODO, "verbos_completo.jasper");
		mapTiposReporte.put(Reportes.VERBOS_POR_MODO_SIN_DUAL, "verbos_completo.jasper");
		mapTiposReporte.put(Reportes.VERBOS_POR_MODO_ABREVIADO, "verbos_compacto.jasper");
		mapTiposReporte.put(Reportes.VERBOS_POR_VOZ, "verbos_voz_primero.jasper");
		mapTiposReporte.put(Reportes.VERBOS_POR_VOZ_SIN_DUAL, "verbos_voz_primero.jasper");
		mapTiposReporte.put(Reportes.INFINITIVOS_POR_VOZ, "infinitivos.jasper");
		mapTiposReporte.put(Reportes.PARTICIPIOS_POR_CASO_ABREVIADO, "participios_nom_gen.jasper");
		mapTiposReporte.put(Reportes.PARTICIPIOS_POR_VOZ_ABREVIADO, "participios_nom_gen_voz_primero.jasper");
		mapTiposReporte.put(Reportes.PARTICIPIOS_POR_NUMERO, "participios_completo.jasper");
		mapTiposReporte.put(Reportes.PARTICIPIOS_POR_VOZ, "participios_voz_primero.jasper");
		mapTiposReporte.put(Reportes.PARTICIPIOS_POR_NUMERO_SIN_DUAL, "participios_sin_dual.jasper");
		mapTiposReporte.put(Reportes.PARTICIPIOS_POR_VOZ_SIN_DUAL, "participios_voz_primero.jasper");
		mapTiposReporte.put(Reportes.PARTICIPIOS_POR_NUMERO_SIN_VOCATIVO, "participios_completo.jasper");
		mapTiposReporte.put(Reportes.PARTICIPIOS_POR_VOZ_SIN_VOCATIVO, "participios_voz_primero.jasper");
		mapTiposReporte.put(Reportes.SUSTANTIVOS_POR_NUMERO, "sustantivos_completo.jasper");
		mapTiposReporte.put(Reportes.SUSTANTIVOS_POR_NUMERO_SIN_DUAL, "sustantivos_sin_dual.jasper");
		mapTiposReporte.put(Reportes.ADJETIVOS_POR_NUMERO, "adjetivos_por_numero.jasper");
		mapTiposReporte.put(Reportes.ADJETIVOS_POR_NUMERO_SIN_DUAL, "adjetivos_por_numero_sin_dual.jasper");
		mapTiposReporte.put(Reportes.ARTICULOS_POR_GENERO, "articulos_masc_fem_neu.jasper");
		mapTiposReporte.put(Reportes.PRONOMBRES_PERSONALES_POR_CASO, "pronombres_personales_por_caso.jasper");
		mapTiposReporte.put(Reportes.PRONOMBRES_PERSONALES_POR_CASO_SIN_DUAL, "pronombres_personales_por_caso.jasper");
		mapTiposReporte.put(Reportes.PRONOMBRES_RELATIVOS_POR_GENERO_1, "pronombres_relativos_por_genero.jasper");
		mapTiposReporte.put(Reportes.PRONOMBRES_RELATIVOS_POR_GENERO_2, "pronombres_relativos_por_genero.jasper");
		mapTiposReporte.put(Reportes.PRONOMBRES_RELATIVOS_POR_GENERO_3, "pronombres_relativos_por_genero.jasper");
		mapTiposReporte.put(Reportes.PRONOMBRES_REFLEXIVOS_POR_GENERO, "pronombres_reflexivos_por_genero.jasper");
		mapTiposReporte.put(Reportes.PRONOMBRES_INTERROGATIVOS_POR_GENERO, "pronombres_interrogativos_por_genero.jasper");
		mapTiposReporte.put(Reportes.CONJUNCIONES_POR_TIPO, "conjunciones_por_tipo.jasper");
		mapTiposReporte.put(Reportes.CONJUNCIONES_ALFABETICO, "conjunciones_alfabeticamente.jasper");
		mapTiposReporte.put(Reportes.PREPOSICIONES_ALFABETICO, "preposiciones_alfabeticamente.jasper");
		mapTiposReporte.put(Reportes.PREPOSICIONES_POR_CASO, "preposiciones_por_caso.jasper");
		mapTiposReporte.put(Reportes.INTERJECCIONES_ALFABETICO, "interjecciones_alfabeticamente.jasper");
		mapTiposReporte.put(Reportes.PRONOMBRES_INDEFINIDOS_POR_GENERO, "pronombres_indefinidos_por_genero.jasper");
	}

	private String getArchivoReporte(Reportes tipoReporte, List<String> nombresColumna) {
		String archivoReporte = mapTiposReporte.get(tipoReporte);
		if (archivoReporte != null)
			return archivoReporte;
		if (tipoReporte.equals(Reportes.ADJETIVOS_POR_GENERO)) {
			boolean tieneMasc = nombresColumna.contains("MASCULINO");
			boolean tieneFem = nombresColumna.contains("FEMENINO");
			boolean tieneNeu = nombresColumna.contains("NEUTRO");
			boolean tieneMascFem = nombresColumna.contains("MASC_FEM");
			if (tieneMasc && tieneFem && tieneNeu && !tieneMascFem)
				return "adjetivos_masc_fem_neu.jasper";
			if (!tieneMasc && !tieneFem && tieneNeu && tieneMascFem)
				return "adjetivos_mascfem_neu.jasper";
			if (tieneMasc && tieneFem && tieneNeu && tieneMascFem) {
				return "adjetivos_mascfem_masc_fem_neu.jasper";
			} else {
				StringBuffer sb = new StringBuffer(200);
				sb.append("esa combinación de columnas no está contemplada para los reportes de adjetivos \n");
				sb.append(" masc existe=").append(tieneMasc).append("\n");
				sb.append(" fem existe=").append(tieneFem).append("\n");
				sb.append(" neu existe=").append(tieneNeu).append("\n");
				sb.append(" mascfem existe=").append(tieneMascFem).append("\n");
				logger.error(sb.toString());
				throw new RuntimeException(sb.toString());
			}
		} else {
			throw new RuntimeException("ese tipo de reporte no está contemplado");
		}
	}

	private Logger logger;
	private UtilidadesTM utildadesTM;
	private Map<String, JasperReport> mapJaspers;
	private Map<Reportes, String> mapTiposReporte;
}
