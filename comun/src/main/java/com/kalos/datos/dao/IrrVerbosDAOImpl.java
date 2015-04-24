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

import com.kalos.beans.IrrVerbo;
import com.kalos.datos.dao.comunes.Borrado;
import com.kalos.datos.dao.comunes.SeleccionIds;
import com.kalos.enumeraciones.Aumento;
import com.kalos.enumeraciones.Contraccion;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Modo;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Propagacion;
import com.kalos.enumeraciones.Tiempo;
import com.kalos.enumeraciones.Voz;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

/**
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.1 $
 */
@SuppressWarnings("unchecked")
public class IrrVerbosDAOImpl extends JdbcDaoSupport implements IrrVerbosDAO {

	private static String SELECCION_POR_VERBO_PARTIC;
	private static String SELECCION_IDS_POR_VERBO_PARTIC;
	private static String SELECCION_POR_TEMA;
	private static String SELECCION_POR_VERBO_PARA_PARTCIPIOS;
	private static String SELECCION_POR_VERBO_PARTIC_PARA_INFINITIVOS_SQL;
	private static String SELECCION_INDIVIDUAL;
	private static String SELECCION_POR_IDS_SQL;
	private static String SELECCION_PARTICS_SQL;
	private static String INSERCION_SQL;
	private static String MODIFICACION_SQL;
	private static String BORRADO_SQL;


	private void puebla() {
		StringBuffer sb = new StringBuffer(200);
		sb.append("SELECT \n");
		sb.append("  TEMA, \n");
		sb.append("  PARTIC, \n");
		sb.append("  SUBPART, \n");
		sb.append("  MODO, \n");
		sb.append("  NTIE, \n");
		sb.append("  NVOZ, \n");
		sb.append("  FUERTE, \n");
		sb.append("  CONT, \n");
		sb.append("  AUM, \n");
		sb.append("  REDUP, \n");
		sb.append("  JUEGO, \n");
		sb.append("  JTIE, \n");
		sb.append("  JVOZ, \n");
		sb.append("  PROPAGA, \n");
        sb.append("  PATS, \n");		
		sb.append("  VERBO_ID, \n");
	    sb.append("  IRR_VERBO_ID \n");
		sb.append("FROM  \n");
		sb.append("  IRR_VERBOS \n");
		sb.append("WHERE \n");
		sb.append("  VERBO_ID=? \n");
		sb.append("  AND PARTIC=? \n");
		sb.append("ORDER BY \n");
		sb.append("  SUBPART \n");

		SELECCION_POR_VERBO_PARTIC = sb.toString();
		
		sb = new StringBuffer(200);
		sb.append("SELECT \n");
		sb.append("  IRR_VERBO_ID \n");
		sb.append("FROM  \n");
		sb.append("  IRR_VERBOS \n");
		sb.append("WHERE \n");
		sb.append("  VERBO_ID=? \n");
		sb.append("  AND PARTIC=? \n");
		sb.append("ORDER BY \n");
		sb.append("  SUBPART \n");

		SELECCION_IDS_POR_VERBO_PARTIC = sb.toString();
				
		
		sb = new StringBuffer(200);
		sb.append("SELECT \n");
		sb.append("  TEMA, \n");
		sb.append("  PARTIC, \n");
		sb.append("  SUBPART, \n");
		sb.append("  MODO, \n");
		sb.append("  NTIE, \n");
		sb.append("  NVOZ, \n");
		sb.append("  FUERTE, \n");
		sb.append("  CONT, \n");
		sb.append("  AUM, \n");
		sb.append("  REDUP, \n");
		sb.append("  JUEGO, \n");
		sb.append("  JTIE, \n");
		sb.append("  JVOZ, \n");
		sb.append("  PROPAGA, \n");
        sb.append("  PATS, \n");		
		sb.append("  VERBO_ID, \n");
		sb.append("  IRR_VERBO_ID, \n");
		sb.append("  VERBO_ID \n");
		sb.append("FROM  \n");
		sb.append("  IRR_VERBOS \n");
		sb.append("WHERE \n");
		sb.append("  TEMA=? \n");
		sb.append("ORDER BY \n");
		sb.append("  VERBO_ID \n");

		SELECCION_POR_TEMA = sb.toString();		

		sb = new StringBuffer(200);
		sb.append("SELECT \n");
		sb.append("  TEMA, \n");
		sb.append("  PARTIC, \n");
		sb.append("  SUBPART, \n");
		sb.append("  MODO, \n");
		sb.append("  NTIE, \n");
		sb.append("  NVOZ, \n");
		sb.append("  FUERTE, \n");
		sb.append("  CONT, \n");
		sb.append("  AUM, \n");
		sb.append("  REDUP, \n");
		sb.append("  JUEGO, \n");
		sb.append("  JTIE, \n");
		sb.append("  JVOZ, \n");
		sb.append("  PROPAGA, \n");
        sb.append("  PATS, \n");		
		sb.append("  VERBO_ID, \n");
		sb.append("  IRR_VERBO_ID \n");
	    sb.append("FROM  \n");
		sb.append("  IRR_VERBOS \n");
		sb.append("WHERE \n");
		sb.append("  IRR_VERBO_ID=? \n");

		SELECCION_INDIVIDUAL = sb.toString();
		
	
        sb=new StringBuffer(200);
        sb.append("SELECT \n");
        sb.append("  irr.VERBO_ID,   \n");
        sb.append("  irr.TEMA,   \n");
        sb.append("  irr.CONT,   \n");
        sb.append("  irr.PARTIC,   \n");
        sb.append("  PAR.ORDEN as ORDEN_PART,   \n");
        sb.append("  irr.SUBPART,   \n");
        sb.append("  irr.AUM,   \n");
        sb.append("  irr.JUEGO,   \n");
        sb.append("  irr.NTIE,   \n");
        sb.append("  irr.FUERTE,   \n");
        sb.append("  irr.MODO,   \n");
        sb.append("  irr.NVOZ,   \n");
        sb.append("  irr.JVOZ,   \n");
        sb.append("  irr.JTIE,   \n");
        sb.append("  irr.PROPAGA,   \n");
        sb.append("  irr.PATS,   \n");
        sb.append("  irr.REDUP,   \n");
        sb.append("  PATS, \n");
        sb.append("  irr.IRR_VERBO_ID   \n");
        sb.append("FROM   \n");
        sb.append("   IRR_VERBOS irr   \n");
        sb.append("   INNER JOIN PARTICULARIDADES PAR ON irr.PARTIC=PAR.PARTIC \n");
        sb.append("WHERE             \n");
        sb.append("   irr.VERBO_ID=?        \n");
        sb.append("   AND irr.MODO=1   \n");
        sb.append("   AND irr.PROPAGA=-1   \n");
        sb.append("   AND irr.NTIE IN (1, 3, 4, 5)   \n");
        sb.append("ORDER BY          \n");
        sb.append("   ORDEN_PART,        \n");
        sb.append("   SUBPART        \n");

        SELECCION_POR_VERBO_PARA_PARTCIPIOS=sb.toString();
        
        sb=new StringBuffer();
        sb.append("SELECT \n");
        sb.append("  irr.VERBO_ID,   \n");
        sb.append("  irr.TEMA,   \n");
        sb.append("  irr.CONT,   \n");
        sb.append("  irr.PARTIC,   \n");
        sb.append("  irr.SUBPART,   \n");
        sb.append("  irr.AUM,   \n");
        sb.append("  irr.JUEGO,   \n");
        sb.append("  irr.NTIE,   \n");
        sb.append("  irr.FUERTE,   \n");
        sb.append("  irr.MODO,   \n");
        sb.append("  irr.NVOZ,   \n");
        sb.append("  irr.JVOZ,   \n");
        sb.append("  irr.JTIE,   \n");
        sb.append("  irr.PROPAGA,   \n");
        sb.append("  irr.PATS,   \n");
        sb.append("  irr.REDUP,   \n");
        sb.append("  irr.IRR_VERBO_ID   \n");
        sb.append("FROM   \n");
        sb.append("   IRR_VERBOS irr   \n");
        sb.append("WHERE             \n");
        sb.append("   irr.VERBO_ID=?        \n");
        sb.append("   AND irr.PARTIC=?   \n");
        sb.append("   AND irr.MODO=1   \n");
        sb.append("   AND irr.PROPAGA=-1   \n");
        sb.append("ORDER BY          \n");
        sb.append("   irr.PARTIC,        \n");
        sb.append("   irr.SUBPART       \n");
        
        SELECCION_POR_VERBO_PARTIC_PARA_INFINITIVOS_SQL=sb.toString();
        
        sb=new StringBuffer();
        sb.append("select DISTINCT  \n");
        sb.append("  IRR.PARTIC \n");
        sb.append("FROM  \n");
        sb.append("  IRR_VERBOS IRR \n");
        sb.append("    INNER JOIN PARTICULARIDADES PAR \n");
        sb.append("      ON PAR.PARTIC=IRR.PARTIC \n");
        sb.append("WHERE \n");
        sb.append("  VERBO_ID=? \n");
        sb.append("ORDER BY \n");
        sb.append("  PAR.ORDEN \n");
  			
  		SELECCION_PARTICS_SQL=sb.toString();

		sb = new StringBuffer(200);
		sb.append("INSERT INTO IRR_VERBOS (   \n");
		sb.append("  TEMA, \n");
		sb.append("  PARTIC, \n");
		sb.append("  SUBPART, \n");
		sb.append("  MODO, \n");
		sb.append("  NTIE, \n");
		sb.append("  NVOZ, \n");
		sb.append("  FUERTE, \n");
		sb.append("  CONT, \n");
		sb.append("  AUM, \n");
		sb.append("  REDUP, \n");
		sb.append("  JUEGO, \n");
		sb.append("  JTIE, \n");
		sb.append("  JVOZ, \n");
		sb.append("  PROPAGA, \n");
		sb.append("  PATS, \n");
		sb.append("  VERBO_ID, \n");
		sb.append("  IRR_VERBO_ID \n");
		sb.append(" ) VALUES (    \n");
		sb.append("  ?,?,?,?,?,?,?,?,?,?,  \n");
		sb.append("  ?,?,?,?,?,?,? \n");
		sb.append(")     \n");

		INSERCION_SQL = sb.toString();

		sb = new StringBuffer(200);
		sb.append("UPDATE IRR_VERBOS SET   \n");
		sb.append("  TEMA=?, \n");
		sb.append("  PARTIC=?, \n");
		sb.append("  SUBPART=?, \n");
		sb.append("  MODO=?, \n");
		sb.append("  NTIE=?, \n");
		sb.append("  NVOZ=?, \n");
		sb.append("  FUERTE=?, \n");
		sb.append("  CONT=?, \n");
		sb.append("  AUM=?, \n");
		sb.append("  REDUP=?, \n");
		sb.append("  JUEGO=?, \n");
		sb.append("  JTIE=?, \n");
		sb.append("  JVOZ=?, \n");
		sb.append("  PROPAGA=?, \n");
		sb.append("  PATS=?,   \n");
		sb.append("  VERBO_ID=? \n");
		sb.append("WHERE    \n");
		sb.append("  IRR_VERBO_ID=?   \n");

		MODIFICACION_SQL = sb.toString();

		sb = new StringBuffer(200);
		sb.append("DELETE FROM IRR_VERBOS WHERE   \n");
		sb.append("  IRR_VERBO_ID=?  \n");

		BORRADO_SQL = sb.toString();
		
		sb = new StringBuffer(200);
		sb.append("SELECT   \n");
        sb.append("  irr.VERBO_ID,   \n");
        sb.append("  irr.TEMA,   \n");
        sb.append("  irr.CONT,   \n");
        sb.append("  irr.PARTIC,   \n");
        sb.append("  irr.SUBPART,   \n");
        sb.append("  irr.AUM,   \n");
        sb.append("  irr.JUEGO,   \n");
        sb.append("  irr.NTIE,   \n");
        sb.append("  irr.FUERTE,   \n");
        sb.append("  irr.MODO,   \n");
        sb.append("  irr.NVOZ,   \n");
        sb.append("  irr.JVOZ,   \n");
        sb.append("  irr.JTIE,   \n");
        sb.append("  irr.PROPAGA,   \n");
        sb.append("  irr.PATS,   \n");
        sb.append("  irr.REDUP,   \n");
        sb.append("  irr.IRR_VERBO_ID   \n");
        sb.append("FROM   \n");
        sb.append("   IRR_VERBOS irr   \n");
		sb.append("WHERE  \n");
		sb.append("  irr.IRR_VERBO_ID IN (?)   \n");

		SELECCION_POR_IDS_SQL = sb.toString();

	}

	//general select
	abstract class SeleccionAbstracta extends MappingSqlQuery {
		public SeleccionAbstracta(DataSource dataSource, String sql) {
			super(dataSource, sql);
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			IrrVerbo bean = new IrrVerbo();
			bean.setId(rs.getString("IRR_VERBO_ID"));
			bean.setTema(rs.getString("TEMA"));
			bean.setPartic(Particularidad.getEnum(rs.getString("PARTIC")));
			bean.setSubPart(rs.getInt("SUBPART"));
			bean.setModo(Modo.getEnum(rs.getInt("MODO")));
			bean.setTiempo(Tiempo.getEnum(rs.getInt("NTIE")));
			bean.setVoz(Voz.getEnum(rs.getInt("NVOZ")));
			bean.setFuerte(FuerteDebil.getEnum(rs.getInt("FUERTE")));
			bean.setContraccion(Contraccion.getEnum(rs.getInt("CONT")));
			bean.setAumento(Aumento.getEnum(rs.getInt("AUM")));
			bean.setReduplicacion(rs.getInt("REDUP") != 0);
			bean.setJuego(rs.getInt("JUEGO"));
			bean.setTiempoJuego(Tiempo.getEnum(rs.getInt("JTIE")));
			bean.setVozJuego(Voz.getEnum(rs.getInt("JVOZ")));
			bean.setPropagacion(Propagacion.getEnum(rs.getInt("PROPAGA")));
			bean.setPats(rs.getInt("PATS")==1);
			bean.setVerboId(rs.getString("VERBO_ID"));
			return bean;
		}
	}
	
	//selección de partics
	class SeleccionPartics extends MappingSqlQuery {
		public SeleccionPartics(DataSource dataSource) {
			super(dataSource, SELECCION_PARTICS_SQL);
			declareParameter(new SqlParameter(Types.CHAR));
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Particularidad partic=Particularidad.getEnum(rs.getString("PARTIC"));
			return partic;
		}
	}


	//selección por verbo y partic
	class SeleccionPorVerboPartic extends SeleccionAbstracta {
		public SeleccionPorVerboPartic(DataSource dataSource) {
			super(dataSource, SELECCION_POR_VERBO_PARTIC);
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.CHAR));
		}
	}

	private SeleccionPorVerboPartic seleccionPorVerboPartic;
	
	private SeleccionPartics seleccionPartics;
	
	public List<Particularidad> seleccionaPartics(String idVerbo){
		List<Particularidad> resultado=seleccionPartics.execute(new Object[]{idVerbo});
		return resultado;
	}

	//seleccion por temas
	class SeleccionPorTema extends SeleccionAbstracta {
		public SeleccionPorTema(DataSource dataSource) {
			super(dataSource, SELECCION_POR_TEMA);
			declareParameter(new SqlParameter(Types.VARCHAR));
		}
	}

	private SeleccionPorTema seleccionPorTema;
	
	public List<IrrVerbo> seleccionaPorTema(String tema) {
		List<IrrVerbo> resultado = seleccionPorTema.execute(new Object[] { tema });
		return resultado;
	}
	
	

	/* (non-Javadoc)
	 * @see kalos.dao.IrrVerbosDAO#seleccionaPorVerboPartic(java.lang.String, java.lang.String)
	 */
	public List<IrrVerbo> seleccionaPorVerboPartic(String verboId, Particularidad partic) {
		List<IrrVerbo> resultado = seleccionPorVerboPartic.execute(new Object[] { 
				verboId, 
				partic.abreviatura()
				});
		return resultado;
	}
	
	//selección por verbo para participios
	class SeleccionPorVerboParaParticipios extends SeleccionAbstracta {
		public SeleccionPorVerboParaParticipios(DataSource dataSource) {
			super(dataSource, SELECCION_POR_VERBO_PARA_PARTCIPIOS);
			declareParameter(new SqlParameter(Types.CHAR));  //verbo_id
		} 
	}
	
	//selección por verbo y partic para infinitivos
	class SeleccionPorVerboParticParaInfinitivos extends SeleccionAbstracta {
		public SeleccionPorVerboParticParaInfinitivos(DataSource dataSource) {
			super(dataSource, SELECCION_POR_VERBO_PARTIC_PARA_INFINITIVOS_SQL);
			declareParameter(new SqlParameter(Types.CHAR));  //verbo_id
			declareParameter(new SqlParameter(Types.CHAR));  //partic
		} 
	}
	
	
    //selección de ids por id de verbo y partic
	class SeleccionIdsPorVerboPartric extends SeleccionIds {
		public SeleccionIdsPorVerboPartric (DataSource dataSource) {
			super(dataSource, SELECCION_IDS_POR_VERBO_PARTIC, "IRR_VERBO_ID");
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.CHAR));
		}
	}

	SeleccionIdsPorVerboPartric seleccionIdsPorVerboPartric;

	/**
	 * devuelve los ids de la irregularidades de verbo
	 * @param verboId	id del verbo
	 * @param partic    particularidad
	 * @return
	 */
	public List<String> seleccionaIdsPorVerboPartic(String verboId, Particularidad partic) {
		List<String> ids=seleccionIdsPorVerboPartric.execute(new Object[] {
				verboId, partic.abreviatura()
		});
		return ids;
	}

    
	private SeleccionPorVerboParaParticipios seleccionPorVerboParaParticipios;
	
	
	/* (non-Javadoc)
	 * @see kalos.dao.IrrVerbosDAO#seleccionaPorVerboParaParticipios(java.lang.String)
	 */
	public List<IrrVerbo> seleccionaPorVerboParaParticipios(String verboId) {
		List<IrrVerbo> resultado = seleccionPorVerboParaParticipios.execute(new Object[] {
				verboId});
		return resultado;
	}
	
	private SeleccionPorVerboParticParaInfinitivos seleccionPorVerboParticParaInfinitivos;
	
	public List<IrrVerbo> seleccionaPorVerboParticParaInfinitivos(String verboId, Particularidad partic) {
		List<IrrVerbo> resultado = seleccionPorVerboParticParaInfinitivos.execute(
				new Object[] {
						verboId, 
						partic.abreviatura()
				});
		return resultado;
	}


	//selección individual
	class SeleccionIndividual extends SeleccionAbstracta {
		public SeleccionIndividual(DataSource dataSource) {
			super(dataSource, SELECCION_INDIVIDUAL);
			declareParameter(new SqlParameter(Types.CHAR));
		}
	}

	private SeleccionIndividual seleccionIndividual;

	/* (non-Javadoc)
	 * @see kalos.dao.IrrVerbosDAO#getInidvidual(java.lang.String)
	 */
	public IrrVerbo getInidvidual(String irrVerboId) {
		List<IrrVerbo> lstAux = seleccionIndividual.execute(new Object[] { irrVerboId });
		if (lstAux.size() == 0)
			return null;
		else
			return lstAux.get(0);
	}

	//inserción
	class Insercion extends SqlUpdate {
		public Insercion(DataSource dataSource) {
			super(dataSource, INSERCION_SQL);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.CHAR));
		}
	}

	private Insercion insercion;

	/* (non-Javadoc)
	 * @see kalos.dao.IrrVerbosDAO#inserta(kalos.beans.IrrVerbo)
	 */
	public void inserta(IrrVerbo bean) {
		String pk = com.kalos.datos.util.DBUtil.getHashableId();

		insercion.update(new Object[] { bean.getTema(),  bean.getPartic().abreviatura(),
				bean.getSubPart(), bean.getModo().valorEntero(), bean.getTiempo().valorEntero(),
				bean.getVoz().valorEntero(), FuerteDebil.getInt(bean.getFuerte()), 
				bean.getContraccion().valorEntero(), Aumento.getInt(bean.getAumento()), bean.isReduplicacion(),
				bean.getJuego(), bean.getTiempoJuego().valorEntero(), bean.getVozJuego().valorEntero(),
				bean.getPropagacion().valorEntero(), bean.isPats()?1:0, 
				bean.getVerboId(), pk });
		bean.setId(pk);
	}

	// update
	class Modificacion extends SqlUpdate {
		public Modificacion(DataSource dataSource) {
			super(dataSource, MODIFICACION_SQL);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.CHAR));
			//where
			declareParameter(new SqlParameter(Types.CHAR));
		}
	}

	private Modificacion modificacion;

	/* (non-Javadoc)
	 * @see kalos.dao.IrrVerbosDAO#modifica(kalos.beans.IrrVerbo)
	 */
	public void modifica(IrrVerbo bean) {
		modificacion.update(new Object[] { bean.getTema(),  bean.getPartic().abreviatura(),
				bean.getSubPart(), bean.getModo().valorEntero(), bean.getTiempo().valorEntero(),
				bean.getVoz().valorEntero(), FuerteDebil.getInt(bean.getFuerte()), 
				bean.getContraccion().valorEntero(), Aumento.getInt(bean.getAumento()), bean.isReduplicacion(),
				bean.getJuego(), bean.getTiempoJuego().valorEntero(), bean.getVozJuego().valorEntero(),
				bean.getPropagacion().valorEntero(), bean.isPats()?1:0, 
				bean.getVerboId(),
				//where
				bean.getId() });
	}



	private Borrado borrado;


	/* (non-Javadoc)
	 * @see kalos.dao.IrrVerbosDAO#borra(java.lang.String)
	 */
	public void borra(String irrVerboId) {
		borrado.update(new Object[] { irrVerboId });
	}
	
	
	
	//selección por verbo y partic para infinitivos
	class SeleccionPorIds extends SeleccionAbstracta {
		public SeleccionPorIds(DataSource dataSource, String sql) {
			super(dataSource, sql);
		} 
	}
	
	
	public List<IrrVerbo> getRegistros(List<String> ids) {
		StringBuffer idsSeparadosComa = new StringBuffer(500);
		for (String id : ids) {
			idsSeparadosComa.append("'" + id + "',");
		}
		if (idsSeparadosComa.length()>0)
			idsSeparadosComa.deleteCharAt(idsSeparadosComa.length() - 1);
		else
			return new ArrayList<IrrVerbo>();
		String sql = SELECCION_POR_IDS_SQL.replaceFirst("\\?", idsSeparadosComa.toString());

		SeleccionPorIds seleccionPorIds = new SeleccionPorIds(getDataSource(), sql);
		List<IrrVerbo> entradasDic = seleccionPorIds.execute();
		return entradasDic;
	}

	public void initDao() throws Exception {
		super.initDao();
		puebla();
		seleccionPorVerboPartic = new SeleccionPorVerboPartic(getDataSource());
		seleccionIdsPorVerboPartric = new SeleccionIdsPorVerboPartric(getDataSource());
		seleccionPorVerboParticParaInfinitivos=new SeleccionPorVerboParticParaInfinitivos(getDataSource());
		seleccionPorVerboParaParticipios=new SeleccionPorVerboParaParticipios(getDataSource());
		seleccionPorTema=new SeleccionPorTema(getDataSource());
		seleccionIndividual = new SeleccionIndividual(getDataSource());
		seleccionPartics = new SeleccionPartics(getDataSource());
		insercion = new Insercion(getDataSource());
		borrado = new Borrado(getDataSource(), BORRADO_SQL);
		modificacion = new Modificacion(getDataSource());
	}

}
