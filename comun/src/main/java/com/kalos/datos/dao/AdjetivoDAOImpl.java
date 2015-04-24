/*
 *  This program is an unpublished work fully protected by the United
 *  States copyright laws and is considered a trade secret belonging
 *  to Turner Entertainment Group. To the extent that this work may be
 *  considered "published,"
 *  the following notice applies:
 *
 *      "Copyright 2005, Turner Entertainment Group."
 *
 *  Any unauthorized use, reproduction, distribution, display,
 *  modification, or disclosure of this program is strictly prohibited.
 *
 */
/**
 * 
 */
package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.kalos.beans.AdjetivoBean;
import com.kalos.beans.Significado;
import com.kalos.beans.SustantivoBean;
import com.kalos.datos.dao.comunes.Borrado;
import com.kalos.datos.dao.comunes.ModificaCodigoIndividual;
import com.kalos.datos.dao.comunes.SeleccionIds;
import com.kalos.enumeraciones.GradoComparacion;
import com.kalos.enumeraciones.Idioma;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.recursos.Configuracion;
import com.kalos.utils.Listas;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

/**
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.1 $
 */
@SuppressWarnings("unchecked")
public class AdjetivoDAOImpl extends JdbcDaoSupport implements AdjetivoDAO {

	private static String SELECCION_POR_LETRA_SQL;
	private static String SELECCION_POR_CANONICA_SQL;
	private static String SELECCION_TODOS_SQL;
	private static String SELECCION_POR_TIPOS_SQL;
	private static String SELECCION_INDIVIDUAL_SQL;
	private static String SELECCION_INVARIABLES_SIN_SIGNIFICADO_SQL;
	private static String SELECCION_POR_IDS_SQL; 
	private static String INSERCION_SQL;
	private static String MODIFICACION_SQL;
	private static String BORRADO_SQL;
    private static String MODIFICA_CODIGO_INDIVIDUAL_SQL; 	
	
	
    private void puebla(){
		StringBuffer sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  ADJ.ADJETIVO_ID   \n");
		sb.append("FROM        \n");
		sb.append("  ADJETIVOS ADJ       \n");
		sb.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
		sb.append("      ON ADJ.ADJETIVO_ID=SIG.REFERENTE_ID       \n");
		sb.append("WHERE  \n");
		sb.append("  ADJ.LETRA=?    \n");
		sb.append("  AND (SIG.IDIOMA IS NULL OR SIG.IDIOMA='" + Configuracion.getIdiomaSignificados() + "')    \n");
		sb.append("ORDER BY  \n");
		sb.append("  ADJ.CODIGO   \n");
		

		SELECCION_POR_LETRA_SQL = sb.toString();
		
		sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  ADJ.ADJETIVO_ID   \n");
		sb.append("FROM        \n");
		sb.append("  ADJETIVOS ADJ       \n");
		sb.append("WHERE  \n");
		sb.append("  ADJ.MASCULINO=?     \n");
		sb.append("  OR ADJ.FEMENINO=?     \n");
		sb.append("  OR ADJ.NEUTRO=?     \n");
		sb.append("  OR ADJ.MASC_FEM=?     \n");

		SELECCION_POR_CANONICA_SQL = sb.toString();		

		sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  ADJ.ADJETIVO_ID,   \n");
		sb.append("  ADJ.LETRA,   \n");
		sb.append("  ADJ.CODIGO,   \n");
		sb.append("  ADJ.MASCULINO,   \n");
		sb.append("  ADJ.FEMENINO,   \n");
		sb.append("  ADJ.NEUTRO,   \n");
		sb.append("  ADJ.MASC_FEM,   \n");
		sb.append("  ADJ.GENITIVO,   \n");		
		sb.append("  ADJ.PARTIC,   \n");
		sb.append("  ADJ.TIPO_ADJETIVO,   \n");
		sb.append("  ADJ.TIPO_ADJETIVO_ID,   \n");
		sb.append("  SIG.SIGNIFICADO_ID,   \n");
		sb.append("  SIG.VALOR,   \n");
		sb.append("  ADJ.VERBAL,   \n");
		sb.append("  ADJ.GRADO_COMPARACION   \n");
		sb.append("FROM        \n");
		sb.append("  ADJETIVOS ADJ       \n");
		sb.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
		sb.append("      ON ADJ.ADJETIVO_ID=SIG.REFERENTE_ID       \n");
		sb.append("WHERE  \n");
		sb.append("  (SIG.IDIOMA IS NULL OR SIG.IDIOMA='" + Configuracion.getIdiomaSignificados() + "')    \n");
		sb.append("ORDER BY  \n");
		sb.append("  GRP.NAME   \n");

		SELECCION_TODOS_SQL = sb.toString();
		
		
		sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  ADJ.ADJETIVO_ID   \n");
		sb.append("FROM        \n");
		sb.append("  ADJETIVOS ADJ       \n");
		sb.append("    INNER JOIN TIPOS_ADJETIVO TIA                 \n");
		sb.append("      ON ADJ.TIPO_ADJETIVO_ID=TIA.TIPO_ADJETIVO_ID       \n");
		sb.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
		sb.append("      ON ADJ.ADJETIVO_ID=SIG.REFERENTE_ID       \n");
		sb.append("WHERE  \n");
		sb.append("  TIA.TIPO_ADJETIVO IN (?) \n");
		sb.append("  AND (SIG.IDIOMA IS NULL OR SIG.IDIOMA='" + Configuracion.getIdiomaSignificados() + "')    \n");		
		sb.append("ORDER BY  \n");
		sb.append("  ADJ.LETRA,   \n");
		sb.append("  ADJ.CODIGO   \n");

		SELECCION_POR_TIPOS_SQL = sb.toString();

		sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  ADJ.ADJETIVO_ID,   \n");
		sb.append("  ADJ.LETRA,   \n");
		sb.append("  ADJ.CODIGO,   \n");
		sb.append("  ADJ.MASCULINO,   \n");
		sb.append("  ADJ.FEMENINO,   \n");
		sb.append("  ADJ.NEUTRO,   \n");
		sb.append("  ADJ.MASC_FEM,   \n");
		sb.append("  ADJ.GENITIVO,   \n");		
		sb.append("  ADJ.PARTIC,   \n");
		sb.append("  ADJ.TIPO_ADJETIVO,   \n");
		sb.append("  ADJ.TIPO_ADJETIVO_ID,   \n");
		sb.append("  SIG.SIGNIFICADO_ID,   \n");		
		sb.append("  SIG.VALOR,   \n");
		sb.append("  ADJ.VERBAL,   \n");
		sb.append("  ADJ.GRADO_COMPARACION   \n");
		sb.append("FROM        \n");
		sb.append("  ADJETIVOS ADJ       \n");
		sb.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
		sb.append("      ON ADJ.ADJETIVO_ID=SIG.REFERENTE_ID       \n");		
		sb.append("WHERE  \n");
		sb.append("  ADJ.ADJETIVO_ID=?    \n");
		sb.append("  AND (SIG.IDIOMA IS NULL OR SIG.IDIOMA='" + Configuracion.getIdiomaSignificados() + "')    \n");

		SELECCION_INDIVIDUAL_SQL = sb.toString();
		
		sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  ADJ.ADJETIVO_ID,   \n");
		sb.append("  ADJ.LETRA,   \n");
		sb.append("  ADJ.CODIGO,   \n");
		sb.append("  ADJ.MASCULINO,   \n");
		sb.append("  ADJ.FEMENINO,   \n");
		sb.append("  ADJ.NEUTRO,   \n");
		sb.append("  ADJ.MASC_FEM,   \n");
		sb.append("  ADJ.GENITIVO,   \n");		
		sb.append("  ADJ.PARTIC,   \n");
		sb.append("  ADJ.TIPO_ADJETIVO,   \n");
		sb.append("  ADJ.TIPO_ADJETIVO_ID,   \n");
		sb.append("  SIG.SIGNIFICADO_ID,   \n");		
		sb.append("  SIG.VALOR,   \n");
		sb.append("  ADJ.VERBAL,   \n");
		sb.append("  ADJ.GRADO_COMPARACION   \n");
		sb.append("FROM        \n");
		sb.append("  ADJETIVOS ADJ       \n");
		sb.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
		sb.append("      ON ADJ.ADJETIVO_ID=SIG.REFERENTE_ID       \n");		
		sb.append("WHERE  \n");
		sb.append("  ADJ.ADJETIVO_ID IN (?)    \n");
		sb.append("  AND (SIG.IDIOMA IS NULL OR SIG.IDIOMA='" + Configuracion.getIdiomaSignificados() + "')    \n");
	    sb.append("ORDER BY  \n");
        sb.append("  ADJ.LETRA,   \n");
        sb.append("  ADJ.CODIGO   \n");

		SELECCION_POR_IDS_SQL = sb.toString();
		
		sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  ADJ.ADJETIVO_ID,   \n");
		sb.append("  ADJ.LETRA,   \n");
		sb.append("  ADJ.CODIGO,   \n");
		sb.append("  ADJ.MASCULINO,   \n");
		sb.append("  ADJ.FEMENINO,   \n");
		sb.append("  ADJ.NEUTRO,   \n");
		sb.append("  ADJ.MASC_FEM,   \n");
		sb.append("  ADJ.GENITIVO,   \n");		
		sb.append("  ADJ.PARTIC,   \n");
		sb.append("  ADJ.TIPO_ADJETIVO,   \n");
		sb.append("  ADJ.TIPO_ADJETIVO_ID,   \n");
		sb.append("  ADJ.VERBAL,   \n");
		sb.append("  ADJ.GRADO_COMPARACION   \n");
		sb.append("FROM        \n");
		sb.append("  ADJETIVOS ADJ       \n");
		sb.append("WHERE  \n");
		sb.append("  ADJ.TIPO_ADJETIVO=12    \n");
		sb.append("  AND ADJ.MASCULINO=?    \n");
	    sb.append("ORDER BY  \n");
        sb.append("  ADJ.LETRA,   \n");
        sb.append("  ADJ.CODIGO   \n");

        SELECCION_INVARIABLES_SIN_SIGNIFICADO_SQL = sb.toString(); 		

		sb = new StringBuffer(200);
		sb.append("INSERT ADJETIVOS(   \n");
		sb.append("  ADJETIVO_ID,   \n");
		sb.append("  LETRA,   \n");
		sb.append("  CODIGO,   \n");
		sb.append("  MASCULINO,   \n");
		sb.append("  FEMENINO,   \n");
		sb.append("  NEUTRO,   \n");
		sb.append("  MASC_FEM,   \n");
		sb.append("  GENITIVO,   \n");		
		sb.append("  PARTIC,   \n");
		sb.append("  TIPO_ADJETIVO,   \n");
		sb.append("  TIPO_ADJETIVO_ID,   \n");
		sb.append("  VERBAL,   \n");
		sb.append("  GRADO_COMPARACION   \n");
		sb.append(" ) VALUES (    \n");
		sb.append("  ?,?,?,?,?,?,?,?,?,?,?,?,?  \n");
		sb.append(")     \n");

		INSERCION_SQL = sb.toString();

		sb = new StringBuffer(200);
		sb.append("UPDATE ADJETIVOS SET   \n");
		sb.append("  LETRA=?,   \n");
		sb.append("  CODIGO=?,   \n");
		sb.append("  MASCULINO=?,   \n");
		sb.append("  FEMENINO=?,   \n");
		sb.append("  NEUTRO=?,   \n");
		sb.append("  MASC_FEM=?,   \n");
		sb.append("  GENITIVO=?,   \n");		
		sb.append("  PARTIC=?,   \n");
		sb.append("  TIPO_ADJETIVO=?,   \n");
		sb.append("  TIPO_ADJETIVO_ID=?,   \n");
		sb.append("  VERBAL=?,   \n");
		sb.append("  GRADO_COMPARACION=?   \n");
		sb.append("WHERE    \n");
		sb.append("  ADJETIVO_ID=?   \n");

		MODIFICACION_SQL = sb.toString();
		

        sb = new StringBuffer(200);
        sb.append("UPDATE ADJETIVOS SET   \n");
        sb.append("  CODIGO=?   \n");
        sb.append("WHERE    \n");
        sb.append("  ADJETIVO_ID=?   \n");

        MODIFICA_CODIGO_INDIVIDUAL_SQL = sb.toString();  		

		sb = new StringBuffer(200);
		sb.append("DELETE FROM ADJETIVOS WHERE   \n");
		sb.append("  ADJETIVO_ID=?  \n");

		BORRADO_SQL = sb.toString();

	}

	//general select
	abstract class SeleccionAbstracta extends MappingSqlQuery {
		public SeleccionAbstracta(DataSource dataSource, String sql) {
			super(dataSource, sql);
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			AdjetivoBean ea = new AdjetivoBean();
			ea.setId(rs.getString("ADJETIVO_ID"));
			ea.setLetra(rs.getString("LETRA"));
			ea.setCodigo(rs.getInt("CODIGO"));
			ea.setFemenino(rs.getString("FEMENINO"));
			ea.setMasculino(rs.getString("MASCULINO"));
			ea.setNeutro(rs.getString("NEUTRO"));
			ea.setMascFem(rs.getString("MASC_FEM"));
			ea.setGenitivo(rs.getString("GENITIVO"));
			ea.setTipoAdjetivo(rs.getInt("TIPO_ADJETIVO"));
			ea.setIdTipo(rs.getString("TIPO_ADJETIVO_ID"));
			ea.setParticularidad(Particularidad.getEnum(rs.getString("PARTIC")));
			ea.setVerbal(rs.getInt("VERBAL")!=0);
			ea.setGrado(GradoComparacion.getEnum(rs.getString("GRADO_COMPARACION")));
			//un significado
			Significado sig=new Significado();
			sig.setIdioma(Configuracion.getIdiomaSignificados());
			sig.setReferenteId(ea.getId());
			sig.setId(rs.getString("SIGNIFICADO_ID"));
			sig.setValor(rs.getString("VALOR"));
			Map<Idioma, Significado> mapSignificados=new HashMap<Idioma, Significado>();
			mapSignificados.put(Idioma.getEnum(Configuracion.getIdiomaSignificados()), sig);
			ea.setSignificados(mapSignificados);
			return ea;
		}
	}
	
	//selección que no hace el join con significado (rápida para AM)
	abstract class SeleccionAbstractaSinSignificado extends MappingSqlQuery {
		public SeleccionAbstractaSinSignificado(DataSource dataSource, String sql) {
			super(dataSource, sql);
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			AdjetivoBean ea = new AdjetivoBean();
			ea.setId(rs.getString("ADJETIVO_ID"));
			ea.setLetra(rs.getString("LETRA"));
			ea.setCodigo(rs.getInt("CODIGO"));
			ea.setFemenino(rs.getString("FEMENINO"));
			ea.setMasculino(rs.getString("MASCULINO"));
			ea.setNeutro(rs.getString("NEUTRO"));
			ea.setMascFem(rs.getString("MASC_FEM"));
			ea.setGenitivo(rs.getString("GENITIVO"));
			ea.setTipoAdjetivo(rs.getInt("TIPO_ADJETIVO"));
			ea.setIdTipo(rs.getString("TIPO_ADJETIVO_ID"));
			ea.setParticularidad(Particularidad.getEnum(rs.getString("PARTIC")));
			ea.setVerbal(rs.getInt("VERBAL")!=0);
			ea.setGrado(GradoComparacion.getEnum(rs.getString("GRADO_COMPARACION")));
			return ea;
		}
	}
	
	


	//selección de todos
	class SeleccionTodos extends SeleccionAbstracta {
		public SeleccionTodos(DataSource dataSource) {
			super(dataSource, SELECCION_TODOS_SQL);
		}
	}
	
    // selección de registros a mostrar de la tabla "adjetivos", dados los IDs
    class SeleccionPorIds extends SeleccionAbstracta {
        public SeleccionPorIds(DataSource dataSource, String sql) {
            super(dataSource, sql);
        }
    }
	
    	
	/* (non-Javadoc)
	 * @see com.kalos.datos.dao.AdjetivoDAO#getRegistros(java.util.List)
	 */
	@Override
	public List<AdjetivoBean> getRegistros(List<String> ids) {
		List<List<String>> segmentos=Listas.segmentos(ids, 500);
		List<AdjetivoBean> resultado = new ArrayList<AdjetivoBean>();
		for (List<String> segmento: segmentos){
		    StringBuffer idsSeparadosComa = new StringBuffer(500);
    		for (String id : segmento) {
    			idsSeparadosComa.append("'" + id + "',");
    		}
            if (idsSeparadosComa.length()>0){
              idsSeparadosComa.deleteCharAt(idsSeparadosComa.length() - 1);    
            }
    		String sql = SELECCION_POR_IDS_SQL.replaceFirst("\\?", idsSeparadosComa.toString());
    		SeleccionPorIds seleccionPorIds = new SeleccionPorIds(getDataSource(), sql);
    		List<AdjetivoBean> adjs = seleccionPorIds.execute();
    		resultado.addAll(adjs);
		}
    	return resultado;
	}	

	//select todos
	private SeleccionTodos selectTodos;

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#getTodos()
	 */
	/* (non-Javadoc)
	 * @see com.kalos.datos.dao.AdjetivoDAO#getTodos()
	 */
	@Override
	public List<AdjetivoBean> getTodos() {
		return selectTodos.execute(new Object[] {});
	}
	
	
	//selección por tipos
	class SeleccionPorTipos extends SeleccionIds {
		public SeleccionPorTipos(DataSource dataSource, String sql) {//reemplazo los parámetros manualmente
			super(dataSource, sql, "ADJETIVO_ID");
		}
	}



	/* (non-Javadoc)
	 * @see com.kalos.datos.dao.AdjetivoDAO#getPorTipos(java.lang.Integer[])
	 */
	@Override
	public List<String> getPorTipos(Integer[] tipos) {
		StringBuffer sbParams=new StringBuffer();
		for (int tipo: tipos){
			sbParams.append(tipo);
			sbParams.append(",");
		}
		sbParams.deleteCharAt(sbParams.length()-1);
		
		String sql=SELECCION_POR_TIPOS_SQL.replaceFirst("\\?", sbParams.toString());
		
		SeleccionPorTipos seleccionPorTipos=new SeleccionPorTipos(getDataSource(), sql);
		
		List<String> beans= seleccionPorTipos.execute(new Object[] {});
		return beans;
	}

	//selección por letra
	class SelectPorLetra extends SeleccionIds {
		public SelectPorLetra(DataSource dataSource) {
			super(dataSource, SELECCION_POR_LETRA_SQL, "ADJETIVO_ID");
			declareParameter(new SqlParameter(Types.CHAR));
		}
	}

	private SelectPorLetra selectPorLetra;

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#getPorLetra(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.kalos.datos.dao.AdjetivoDAO#seleccionaPorLetra(java.lang.String)
	 */
	@Override
	public List<String> seleccionaPorLetra(String letra) {
		return selectPorLetra.execute(new Object[] { letra });
	}
	
	//selección de invariables
    private class SeleccionInvariables extends SeleccionAbstractaSinSignificado{
    	public SeleccionInvariables(DataSource dataSource){
    		super(dataSource, SELECCION_INVARIABLES_SIN_SIGNIFICADO_SQL);
    		declareParameter(new SqlParameter(Types.VARCHAR));
    	}
    }
    
    private SeleccionInvariables seleccionInvariables;
    
    /* (non-Javadoc)
	 * @see com.kalos.datos.dao.AdjetivoDAO#seleccionaInvariables(java.lang.String)
	 */
    @Override
	public List<AdjetivoBean> seleccionaInvariables(String forma){
    	List<AdjetivoBean> resultado=seleccionInvariables.execute(new Object[]{forma});
    	return resultado;
    }	
	
	//selección por canónica
	class SeleccionPorCanonica extends SeleccionIds {
		public SeleccionPorCanonica(DataSource dataSource) {
			super(dataSource, SELECCION_POR_CANONICA_SQL, "ADJETIVO_ID");
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
		}
	}

	private SeleccionPorCanonica seleccionPorCanonica;

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#getPorLetra(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.kalos.datos.dao.AdjetivoDAO#seleccionaPorCanonica(java.lang.String)
	 */
	@Override
	public List<String> seleccionaPorCanonica(String canonica) {
		return seleccionPorCanonica.execute(new Object[] { 
				canonica,
				canonica,
				canonica,
				canonica
				});
	}	

	//selección individual
	class SeleccionIndividual extends SeleccionAbstracta {
		public SeleccionIndividual(DataSource dataSource) {
			super(dataSource, SELECCION_INDIVIDUAL_SQL);
			declareParameter(new SqlParameter(Types.CHAR));
		}
	}

	private SeleccionIndividual seleccionIndividual;

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#getInidvidual(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.kalos.datos.dao.AdjetivoDAO#getInidvidual(java.lang.String)
	 */
	@Override
	public AdjetivoBean getInidvidual(String adjetivoId) {
		List<AdjetivoBean> lstAux = seleccionIndividual
				.execute(new Object[] { adjetivoId });
		if (lstAux.size() == 0)
			return null;
		else
			return lstAux.get(0);
	}

	


    private ModificaCodigoIndividual modificaCodigoIndividual;

    /* (non-Javadoc)
	 * @see com.kalos.datos.dao.AdjetivoDAO#modificaCodigoIndividual(int, java.lang.String)
	 */
    @Override
	public void modificaCodigoIndividual(int nuevoCodigo, String idAdjetivo) {
        modificaCodigoIndividual.update(new Object[] { nuevoCodigo, idAdjetivo});
    }

	
	
	//inserción
	class Insercion extends SqlUpdate {
		public Insercion(DataSource dataSource) {
			super(dataSource, INSERCION_SQL);
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.CHAR));
		}
	}

	private Insercion insercion;

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#inserta(kalos.beans.AdjetivoBean)
	 */
	/* (non-Javadoc)
	 * @see com.kalos.datos.dao.AdjetivoDAO#inserta(kalos.beans.AdjetivoBean)
	 */
	@Override
	public void inserta(AdjetivoBean ea) {
		String pk = com.kalos.datos.util.DBUtil.getHashableId();
		insercion.update(new Object[] { 
				pk, 
				ea.getLetra(), 
				ea.getCodigo(),
				ea.getMasculino(), 
				ea.getFemenino(), 
				ea.getNeutro(),
				ea.getMascFem(),
				ea.getGenitivo(),
				ea.getParticularidad().abreviatura(),
				ea.getTipoAdjetivo(),
				ea.getIdTipo(),
				ea.isVerbal()?1:0,
				ea.getGrado().abreviatura(),
		});
		ea.setId(pk);
	}

	// update
	class Modificacion extends SqlUpdate {
		public Modificacion(DataSource dataSource) {
			super(dataSource, MODIFICACION_SQL);
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.CHAR));
			//where
			declareParameter(new SqlParameter(Types.CHAR));
		}
	}

	private Modificacion modificacion;

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#modifica(kalos.beans.AdjetivoBean)
	 */
	/* (non-Javadoc)
	 * @see com.kalos.datos.dao.AdjetivoDAO#modifica(kalos.beans.AdjetivoBean)
	 */
	@Override
	public void modifica(AdjetivoBean ea) {
		modificacion.update(new Object[] { 
				ea.getLetra(), 
				ea.getCodigo(),
				ea.getMasculino(), 
				ea.getFemenino(), 
				ea.getNeutro(),
				ea.getMascFem(),
				ea.getGenitivo(),
				ea.getParticularidad().abreviatura(),
				ea.getTipoAdjetivo(), 
				ea.getIdTipo(),
				ea.isVerbal()?1:0,
				ea.getGrado().abreviatura(),
				//where
				ea.getId() });
	}


	private Borrado borrado;

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#borra(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.kalos.datos.dao.AdjetivoDAO#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		borrado.update(new Object[] { id });
	}

	/* (non-Javadoc)
	 * @see com.kalos.datos.dao.AdjetivoDAO#initDao()
	 */
	@Override
	public void initDao() throws Exception {
		super.initDao();
		puebla();
		selectTodos = new SeleccionTodos(getDataSource());
		selectPorLetra = new SelectPorLetra(getDataSource());
		seleccionPorCanonica= new SeleccionPorCanonica(getDataSource());
		seleccionIndividual = new SeleccionIndividual(getDataSource());
		seleccionInvariables=new SeleccionInvariables(getDataSource());
		insercion = new Insercion(getDataSource());
		borrado = new Borrado(getDataSource(), BORRADO_SQL);
		modificacion = new Modificacion(getDataSource());
		modificaCodigoIndividual=new ModificaCodigoIndividual(getDataSource(), MODIFICA_CODIGO_INDIVIDUAL_SQL);
	}

}
