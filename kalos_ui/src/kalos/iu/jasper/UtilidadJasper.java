// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.iu.jasper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import kalos.enumeraciones.Reportes;
import kalos.flexion.CeldaReporte;
import kalos.flexion.UtilidadesTM;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRewindableDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;

// Referenced classes of package kalos.iu.B:
//            C, A

public class UtilidadJasper {

	public void setUtilidadesTM(UtilidadesTM c) {
		C = c;
	}

	public UtilidadJasper() {
		A = Logger.getLogger(getClass().getName());
		D = new HashMap();
		B = new HashMap();
		A();
	}

	public static JRRewindableDataSource creaFuenteJR(
			DefaultTableModel defaulttablemodel) {
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

	public JRViewerKalos visorJasper(Reportes m, Map map, DefaultTableModel defaulttablemodel) {
		String s = (new StringBuilder()).append("armado_reportes/")
				.append(A(m, C.nombresColumna(defaulttablemodel))).toString();
		try {
			JasperReport jasperreport = (JasperReport) D.get(s);
			if (jasperreport == null) {
				jasperreport = (JasperReport) JRLoader.loadObject(s);
				D.put(s, jasperreport);
			}
			net.sf.jasperreports.engine.JasperPrint jasperprint = JasperFillManager
					.fillReport(jasperreport, map, new JRTableModelDataSource(
							defaulttablemodel));
			JRViewerKalos a = new JRViewerKalos(jasperprint);
			return a;
		} catch (JRException jrexception) {
			A.error("error al crear el visor de jasper", jrexception);
		}
		return null;
	}

	private void A() {
		B.put(Reportes.VERBOS_POR_MODO, "verbos_completo.jasper");
		B.put(Reportes.VERBOS_POR_MODO_SIN_DUAL, "verbos_completo.jasper");
		B.put(Reportes.VERBOS_POR_MODO_ABREVIADO, "verbos_compacto.jasper");
		B.put(Reportes.VERBOS_POR_VOZ, "verbos_voz_primero.jasper");
		B.put(Reportes.VERBOS_POR_VOZ_SIN_DUAL, "verbos_voz_primero.jasper");
		B.put(Reportes.INFINITIVOS_POR_VOZ, "infinitivos.jasper");
		B.put(Reportes.PARTICIPIOS_POR_CASO_ABREVIADO, "participios_nom_gen.jasper");
		B.put(Reportes.PARTICIPIOS_POR_VOZ_ABREVIADO, "participios_nom_gen_voz_primero.jasper");
		B.put(Reportes.PARTICIPIOS_POR_NUMERO, "participios_completo.jasper");
		B.put(Reportes.PARTICIPIOS_POR_VOZ, "participios_voz_primero.jasper");
		B.put(Reportes.PARTICIPIOS_POR_NUMERO_SIN_DUAL, "participios_sin_dual.jasper");
		B.put(Reportes.PARTICIPIOS_POR_VOZ_SIN_DUAL, "participios_voz_primero.jasper");
		B.put(Reportes.PARTICIPIOS_POR_NUMERO_SIN_VOCATIVO, "participios_completo.jasper");
		B.put(Reportes.PARTICIPIOS_POR_VOZ_SIN_VOCATIVO, "participios_voz_primero.jasper");
		B.put(Reportes.SUSTANTIVOS_POR_NUMERO, "sustantivos_completo.jasper");
		B.put(Reportes.SUSTANTIVOS_POR_NUMERO_SIN_DUAL, "sustantivos_sin_dual.jasper");
		B.put(Reportes.ADJETIVOS_POR_NUMERO, "adjetivos_por_numero.jasper");
		B.put(Reportes.ADJETIVOS_POR_NUMERO_SIN_DUAL, "adjetivos_por_numero_sin_dual.jasper");
		B.put(Reportes.ARTICULOS_POR_GENERO, "articulos_masc_fem_neu.jasper");
		B.put(Reportes.PRONOMBRES_PERSONALES_POR_CASO, "pronombres_personales_por_caso.jasper");
		B.put(Reportes.PRONOMBRES_PERSONALES_POR_CASO_SIN_DUAL, "pronombres_personales_por_caso.jasper");
		B.put(Reportes.PRONOMBRES_RELATIVOS_POR_GENERO_1, "pronombres_relativos_por_genero.jasper");
		B.put(Reportes.PRONOMBRES_RELATIVOS_POR_GENERO_2, "pronombres_relativos_por_genero.jasper");
		B.put(Reportes.PRONOMBRES_RELATIVOS_POR_GENERO_3, "pronombres_relativos_por_genero.jasper");
		B.put(Reportes.PRONOMBRES_REFLEXIVOS_POR_GENERO, "pronombres_reflexivos_por_genero.jasper");
		B.put(Reportes.PRONOMBRES_INTERROGATIVOS_POR_GENERO, "pronombres_interrogativos_por_genero.jasper");
		B.put(Reportes.CONJUNCIONES_POR_TIPO, "conjunciones_por_tipo.jasper");
		B.put(Reportes.CONJUNCIONES_ALFABETICO, "conjunciones_alfabeticamente.jasper");
		B.put(Reportes.PREPOSICIONES_ALFABETICO, "preposiciones_alfabeticamente.jasper");
		B.put(Reportes.PREPOSICIONES_POR_CASO, "preposiciones_por_caso.jasper");
		B.put(Reportes.INTERJECCIONES_ALFABETICO, "interjecciones_alfabeticamente.jasper");
		B.put(Reportes.PRONOMBRES_INDEFINIDOS_POR_GENERO, "pronombres_indefinidos_por_genero.jasper");
	}

	private String A(Reportes m, List list) {
		String s = (String) B.get(m);
		if (s != null)
			return s;
		if (m.equals(m.ADJETIVOS_POR_GENERO)) {
			boolean flag = list.contains("MASCULINO");
			boolean flag1 = list.contains("FEMENINO");
			boolean flag2 = list.contains("NEUTRO");
			boolean flag3 = list.contains("MASC_FEM");
			if (flag && flag1 && flag2 && !flag3)
				return "adjetivos_masc_fem_neu.jasper";
			if (!flag && !flag1 && flag2 && flag3)
				return "adjetivos_mascfem_neu.jasper";
			if (flag && flag1 && flag2 && flag3) {
				return "adjetivos_mascfem_masc_fem_neu.jasper";
			} else {
				StringBuffer stringbuffer = new StringBuffer(200);
				stringbuffer
						.append("esa combinaci\363n de columnas no est\341 contemplada para los reportes de adjetivos \n");
				stringbuffer.append((new StringBuilder())
						.append(" masc existe=").append(flag).append("\n")
						.toString());
				stringbuffer.append((new StringBuilder())
						.append(" fem existe=").append(flag1).append("\n")
						.toString());
				stringbuffer.append((new StringBuilder())
						.append(" neu existe=").append(flag2).append("\n")
						.toString());
				stringbuffer.append((new StringBuilder())
						.append(" mascfem existe=").append(flag3).append("\n")
						.toString());
				A.error(stringbuffer.toString());
				throw new RuntimeException(stringbuffer.toString());
			}
		} else {
			throw new RuntimeException(
					"ese tipo de reporte no est\341 contemplado");
		}
	}

	private Logger A;
	private UtilidadesTM C;
	private Map D;
	private Map B;
}
