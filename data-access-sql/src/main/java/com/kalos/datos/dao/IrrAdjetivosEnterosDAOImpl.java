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
import java.util.List;

import javax.sql.DataSource;

import com.kalos.beans.IrrAdjetivoEntero;
import com.kalos.datos.dao.comunes.Borrado;
import com.kalos.datos.dao.comunes.SeleccionIds;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.GradoComparacion;
import com.kalos.enumeraciones.Particularidad;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

/**
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.1 $
 */
@SuppressWarnings("unchecked")
public class IrrAdjetivosEnterosDAOImpl extends JdbcDaoSupport implements IrrAdjetivosEnterosDAO {

	private static String SELECCION_POR_ADJETIVO_SQL;
	private static String SELECCION_INDIVIDUAL_SQL;
	private static String SELECCION_POR_IDS_SQL;
	private static String SELECCION_PARTICS_SQL;
	private static String INSERCION_SQL;
	private static String MODIFICACION_SQL;
	private static String BORRADO_SQL;
	private static String BORRADO_ADJETIVO_SQL;
	
	
	
		

	private void puebla() {
		StringBuffer sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  IAE.IRR_ADJETIVOS_ENTEROS_ID   \n");
		sb.append("FROM        \n");
		sb.append("   IRR_ADJETIVOS_ENTEROS IAE       \n");
		sb.append("WHERE  \n");
		sb.append("  IAE.ADJETIVO_ID=?    \n");
		sb.append("ORDER BY  \n");
		sb.append("  IAE.GRADO,   \n");
		sb.append("  IAE.GENERO,   \n");
		sb.append("  IAE.SUBINDICE   \n");

		SELECCION_POR_ADJETIVO_SQL = sb.toString();

		sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  IAE.IRR_ADJETIVOS_ENTEROS_ID,   \n");
		sb.append("  IAE.ADJETIVO_ID,   \n");
		sb.append("  IAE.GENERO,   \n");
		sb.append("  IAE.GRADO,   \n");
		sb.append("  IAE.PARTICULARIDAD,   \n");
		sb.append("  IAE.SUBINDICE,   \n");
		sb.append("  IAE.NOMINATIVO,   \n");
		sb.append("  IAE.GENITIVO,   \n");
		sb.append("  IAE.SOLO_SINGULAR,   \n");
		sb.append("  IAE.TIPO_SUSTANTIVO_ID,   \n");
		sb.append("  IAE.TIPO_SUSTANTIVO   \n");
		sb.append("FROM        \n");
		sb.append("   IRR_ADJETIVOS_ENTEROS IAE       \n");
		sb.append("WHERE  \n");
		sb.append("  IAE.IRR_ADJETIVOS_ENTEROS_ID=?   \n");

		SELECCION_INDIVIDUAL_SQL = sb.toString();
		
		
		sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  IAE.IRR_ADJETIVOS_ENTEROS_ID,   \n");
		sb.append("  IAE.ADJETIVO_ID,   \n");
		sb.append("  IAE.GENERO,   \n");
		sb.append("  IAE.GRADO,   \n");
		sb.append("  IAE.PARTICULARIDAD,   \n");
		sb.append("  IAE.SUBINDICE,   \n");
		sb.append("  IAE.NOMINATIVO,   \n");
		sb.append("  IAE.GENITIVO,   \n");
		sb.append("  IAE.SOLO_SINGULAR,   \n");
		sb.append("  IAE.TIPO_SUSTANTIVO_ID,   \n");
		sb.append("  IAE.TIPO_SUSTANTIVO   \n");
		sb.append("FROM        \n");
		sb.append("   IRR_ADJETIVOS_ENTEROS IAE       \n");
		sb.append("WHERE  \n");
		sb.append("  IAE.IRR_ADJETIVOS_ENTEROS_ID IN (?)   \n");

		SELECCION_POR_IDS_SQL = sb.toString();
		
		
        sb=new StringBuffer();
        sb.append("select DISTINCT  \n");
        sb.append("  CV.CLAVE_VALOR \n");
        sb.append("FROM  \n");
        sb.append("  IRR_ADJETIVOS_ENTEROS IRR \n");
        sb.append("    INNER JOIN PARTICULARIDADES PAR \n");
        sb.append("      ON PAR.PARTIC=IRR.PARTIC \n");
        sb.append("WHERE \n");
        sb.append("  ADJETIVO_ID=? \n");
        sb.append("ORDER BY \n");
        sb.append("  PAR.ORDEN \n");
  			
  		SELECCION_PARTICS_SQL=sb.toString();



		sb = new StringBuffer(200);
		sb.append("INSERT IRR_ADJETIVOS_ENTEROS(   \n");
		sb.append("  IRR_ADJETIVOS_ENTEROS_ID,   \n");
		sb.append("  ADJETIVO_ID,   \n");
		sb.append("  GENERO,   \n");
		sb.append("  GRADO,   \n");
		sb.append("  PARTICULARIDAD,   \n");
		sb.append("  SUBINDICE,   \n");
		sb.append("  NOMINATIVO,   \n");
		sb.append("  GENITIVO,   \n");
		sb.append("  SOLO_SINGULAR,   \n");
		sb.append("  TIPO_SUSTANTIVO_ID,   \n");
		sb.append("  TIPO_SUSTANTIVO   \n");
		sb.append(" ) VALUES (    \n");
		sb.append("  ?,?,?,?,?,?,?,?,?,?,  \n");
		sb.append("  ?  \n");
		sb.append(")     \n");

		INSERCION_SQL = sb.toString();

		sb = new StringBuffer(200);
		sb.append("UPDATE IRR_ADJETIVOS_ENTEROS SET   \n");
		sb.append("  ADJETIVO_ID=?,   \n");
		sb.append("  GENERO=?,   \n");
		sb.append("  GRADO=?,   \n");
		sb.append("  PARTICULARIDAD=?,   \n");
		sb.append("  SUBINDICE=?,   \n");
		sb.append("  NOMINATIVO=?,   \n");
		sb.append("  GENITIVO=?,   \n");
		sb.append("  SOLO_SINGULAR=?,   \n");
		sb.append("  TIPO_SUSTANTIVO_ID=?,   \n");
		sb.append("  TIPO_SUSTANTIVO=?   \n");
		sb.append("WHERE    \n");
		sb.append("  IRR_ADJETIVOS_ENTEROS_ID=?   \n");

		MODIFICACION_SQL = sb.toString();

		sb = new StringBuffer(200);
		sb.append("DELETE FROM IRR_ADJETIVOS_ENTEROS WHERE   \n");
		sb.append("  IRR_ADJETIVOS_ENTEROS_ID=?  \n");

		BORRADO_SQL = sb.toString();
		
        sb = new StringBuffer(200);
        sb.append("DELETE FROM IRR_ADJETIVOS_ENTEROS    \n");
        sb.append("WHERE ADJETIVO_ID=?    \n");

        BORRADO_ADJETIVO_SQL = sb.toString();		

	}

	//general select
	abstract class SeleccionAbstracta extends MappingSqlQuery {
		public SeleccionAbstracta(DataSource dataSource, String sql) {
			super(dataSource, sql);
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			IrrAdjetivoEntero iae = new IrrAdjetivoEntero();
			iae.setId(rs.getString("IRR_ADJETIVOS_ENTEROS_ID"));
			iae.setGenero(Genero.getEnum(rs.getString("GENERO")));
			iae.setGrado(GradoComparacion.getEnum(rs.getString("GRADO")));
			iae.setParticularidad(Particularidad.getEnum(rs.getString("PARTICULARIDAD")));
			iae.setSubindice(rs.getInt("SUBINDICE"));
			iae.setNominativo(rs.getString("NOMINATIVO"));
			iae.setGenitivo(rs.getString("GENITIVO"));
			iae.setSoloSingular(rs.getInt("SOLO_SINGULAR")==1);
			iae.setTipoSustantivoId(rs.getString("TIPO_SUSTANTIVO_ID"));
			iae.setAdjetivoId(rs.getString("ADJETIVO_ID"));
			iae.setTipoSustantivo(rs.getInt("TIPO_SUSTANTIVO"));
			return iae;
		}
	}
	
	
    
    class SeleccionPorIds extends SeleccionAbstracta{
        public SeleccionPorIds(DataSource dataSource, String sql) {
            super(dataSource, sql);
        }
    }

	public List<IrrAdjetivoEntero> getRegistros(List<String> ids) {
		StringBuffer idsSeparadosComa = new StringBuffer(500);
		for (String id : ids) {
			idsSeparadosComa.append("'" + id + "',");
		}
		if (idsSeparadosComa.length()>0)
			idsSeparadosComa.deleteCharAt(idsSeparadosComa.length() - 1);
		else
			return new ArrayList<IrrAdjetivoEntero>();
		String sql = SELECCION_POR_IDS_SQL.replaceFirst("\\?", idsSeparadosComa.toString());

		SeleccionPorIds seleccionPorIds = new SeleccionPorIds(getDataSource(), sql);
		List<IrrAdjetivoEntero> entradasDic = seleccionPorIds.execute();
		return entradasDic;
	}
	
	
	//selección por adjetivo
	class SeleccionPorAdjetivo extends SeleccionIds {
		public SeleccionPorAdjetivo(DataSource dataSource) {
			super(dataSource, SELECCION_POR_ADJETIVO_SQL, "IRR_ADJETIVOS_ENTEROS_ID");
			declareParameter(new SqlParameter(Types.CHAR));
			
		}
	}

	SeleccionPorAdjetivo seleccionPorAdjetivo;

	/* (non-Javadoc)
	 * @see kalos.dao.IrrAdjetivosEnterosDAO#seleccionaPorAdjetivo(java.lang.String)
	 */
	public List<String> seleccionaPorAdjetivo(String adjetivoId) {
		List<String> ids=seleccionPorAdjetivo.execute(new Object[] {
				adjetivoId
		});
		return ids;
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
	 * @see kalos.dao.IrrAdjetivosEnterosDAO#seleccionIndividual(java.lang.String)
	 */
	public IrrAdjetivoEntero seleccionIndividual(String irrAdjetivoEnteroId) {
		List<IrrAdjetivoEntero> lstAux = seleccionIndividual
				.execute(new Object[] { irrAdjetivoEnteroId });
		if (lstAux.size() == 0)
			return null;
		else
			return lstAux.get(0);
	}
	
	
	//selección de partics
	class SeleccionPartics extends MappingSqlQuery {
		public SeleccionPartics(DataSource dataSource) {
			super(dataSource, SELECCION_PARTICS_SQL);
			declareParameter(new SqlParameter(Types.CHAR));
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			String partic=rs.getString("CLAVE_VALOR");
			return partic;
		}
	}
	
	private SeleccionPartics seleccionPartics;
	
	public List<String> seleccionaPartics(String idAdjetivo){
		List<String> resultado=seleccionPartics.execute(new Object[]{idAdjetivo});
		return resultado;
	}	
	


	//inserción
	class Insercion extends SqlUpdate {
		public Insercion(DataSource dataSource) {
			super(dataSource, INSERCION_SQL);
			declareParameter(new SqlParameter(Types.CHAR)); //id
			declareParameter(new SqlParameter(Types.CHAR)); //id adjetivo
			declareParameter(new SqlParameter(Types.CHAR)); //genero
			declareParameter(new SqlParameter(Types.CHAR)); //grado
			declareParameter(new SqlParameter(Types.CHAR)); //partic
			declareParameter(new SqlParameter(Types.INTEGER));//subíndice
			declareParameter(new SqlParameter(Types.VARCHAR)); //nominativo
			declareParameter(new SqlParameter(Types.VARCHAR)); //genitivo
			declareParameter(new SqlParameter(Types.INTEGER)); //solo singular
			declareParameter(new SqlParameter(Types.CHAR));  //id tipo nominal
			declareParameter(new SqlParameter(Types.INTEGER)); //tipo nominal
		}
	}

	private Insercion insercion;

	/* (non-Javadoc)
	 * @see kalos.dao.AdjetivoDAO#inserta(kalos.beans.AdjetivoBean)
	 */
	/* (non-Javadoc)
	 * @see kalos.dao.IrrAdjetivosEnterosDAO#inserta(kalos.beans.IrrAdjetivoEntero)
	 */
	public void inserta(IrrAdjetivoEntero iae) {
		String pk = com.kalos.datos.util.DBUtil.getHashableId();
		insercion.update(new Object[] { 
				pk, 						
				iae.getAdjetivoId(),        
				iae.getGenero().valorEntero(),
				iae.getGrado().abreviatura(),
				iae.getParticularidad().abreviatura(),
				iae.getSubindice(),
				iae.getNominativo(), 
				iae.getGenitivo(),
				iae.isSoloSingular()?1:0,
				iae.getTipoSustantivoId(),
				iae.getTipoSustantivo()
		});
		iae.setId(pk);
	}

	// update
	class Modificacion extends SqlUpdate {
		public Modificacion(DataSource dataSource) {
			super(dataSource, MODIFICACION_SQL);
			declareParameter(new SqlParameter(Types.CHAR)); //id adjetivo
			declareParameter(new SqlParameter(Types.CHAR));  //género
			declareParameter(new SqlParameter(Types.CHAR));  //grado
			declareParameter(new SqlParameter(Types.CHAR)); //partic
			declareParameter(new SqlParameter(Types.INTEGER));  //subíndice
			declareParameter(new SqlParameter(Types.VARCHAR));  //nominativo
			declareParameter(new SqlParameter(Types.VARCHAR));  //genitivo
			declareParameter(new SqlParameter(Types.INTEGER));  //sólo singular
			declareParameter(new SqlParameter(Types.CHAR));     //id tipo nominal
			declareParameter(new SqlParameter(Types.INTEGER));  //tipo nominal
			//where
			declareParameter(new SqlParameter(Types.CHAR));    //id
		}
	}

	private Modificacion modificacion;

	/* (non-Javadoc)
	 * @see kalos.dao.IrrAdjetivosEnterosDAO#modifica(kalos.beans.IrrAdjetivoEntero)
	 */
	public void modifica(IrrAdjetivoEntero bean) {
		modificacion.update(new Object[] { 
				bean.getAdjetivoId(),
				bean.getGenero().valorEntero(),
				bean.getGrado().abreviatura(),
				bean.getParticularidad().abreviatura(),
				bean.getSubindice(),
				bean.getNominativo(), 
				bean.getGenitivo(),
				bean.isSoloSingular()?1:0,
				bean.getTipoSustantivoId(),
				bean.getTipoSustantivo(),
				//where
				bean.getId()
			});
	}

	
	
	
	
	
	private Borrado borrado;


	public void borra(String cmsGroupId) {
		borrado.update(new Object[] { cmsGroupId, });
	}

	
	   private class BorradoAdjetivos extends SqlUpdate {
	        public BorradoAdjetivos(DataSource dataSource){
	            super(dataSource, BORRADO_ADJETIVO_SQL);
	            declareParameter(new SqlParameter(Types.CHAR)); //id adjetivo
	        }
	    }
	    
	    private BorradoAdjetivos borradoAdjetivos;
	    
	    public void borraAdjetivo(String id){
	        borradoAdjetivos.update(new Object[]{id});
	    }
	
	public void initDao() throws Exception {
		super.initDao();
		puebla();
		seleccionPorAdjetivo= new SeleccionPorAdjetivo(getDataSource());
		seleccionIndividual = new SeleccionIndividual(getDataSource());
		seleccionPartics=new SeleccionPartics(getDataSource());
		insercion = new Insercion(getDataSource());
		borrado = new Borrado(getDataSource(), BORRADO_SQL);
		borradoAdjetivos=new BorradoAdjetivos(getDataSource());
		modificacion = new Modificacion(getDataSource());
	}

}
