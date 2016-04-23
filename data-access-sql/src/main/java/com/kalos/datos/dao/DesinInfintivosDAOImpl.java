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
import java.util.List;

import javax.sql.DataSource;

import com.kalos.beans.DesinInfinitivo;
import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.FuerteDebil;
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
public class DesinInfintivosDAOImpl extends JdbcDaoSupport  implements DesinInfinitivosDAO {

	private static String SELECCION_RESTRINGIDAS_SQL;
	private static String SELECCION_TODAS_SQL;
	private static String INSERCION_SQL;
	

	private void puebla() {
		StringBuffer sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  DEI.JUEGO,   \n");
		sb.append("  DEI.VOZ,   \n");
		sb.append("  DEI.ASPECTO,   \n");
		sb.append("  DEI.FUERTE,   \n");
		sb.append("  DEI.DESINENCIA,   \n");
		sb.append("  DEI.SILABA,   \n");
		sb.append("  DEI.SUBINDICE,   \n");
		sb.append("  DEI.ACENTO,   \n");		
		sb.append("  DEI.ORDEN,   \n");
		sb.append("  DEI.ORDEN_POR_DEFECTO   \n");
		sb.append("FROM        \n");
		sb.append("  DESIN_INFINITIVOS DEI       \n");
		sb.append("WHERE  \n");
		sb.append("  DEI.ORDEN IS NOT NULL  ");

		SELECCION_RESTRINGIDAS_SQL = sb.toString();

		sb = new StringBuffer(200);
		sb.append("INSERT INTO DESIN_INFINITIVOS (  \n");
		sb.append("  JUEGO,   \n");
		sb.append("  VOZ,   \n");
		sb.append("  MODO,   \n");
		sb.append("  ASPECTO,   \n");
		sb.append("  FUERTE,   \n");
		sb.append("  DESINENCIA,   \n");
		sb.append("  SILABA,   \n");
		sb.append("  SUBINDICE,   \n");
		sb.append("  ACENTO,   \n");
		sb.append("  ORDEN,   \n");
		sb.append("  ORDEN_POR_DEFECTO   \n");
		sb.append(" ) VALUES ( ?,?,?,?,?,?,?,?,?,?,?)        \n");

		INSERCION_SQL = sb.toString();
        
        sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  DEI.JUEGO,   \n");
		sb.append("  DEI.VOZ,   \n");
		sb.append("  DEI.ASPECTO,   \n");
		sb.append("  DEI.FUERTE,   \n");
		sb.append("  DEI.DESINENCIA,   \n");
		sb.append("  DEI.SILABA,   \n");
		sb.append("  DEI.SUBINDICE,   \n");
		sb.append("  DEI.ACENTO,   \n");		
		sb.append("  DEI.ORDEN,   \n");
		sb.append("  DEI.ORDEN_POR_DEFECTO   \n");
		sb.append("FROM        \n");
		sb.append("  DESIN_INFINITIVOS DEI       \n");

		SELECCION_TODAS_SQL = sb.toString();
		
	}

	//general select
	abstract class SeleccionAbstracta extends MappingSqlQuery {
		public SeleccionAbstracta(DataSource dataSource, String sql) {
			super(dataSource, sql);
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DesinInfinitivo bean=new DesinInfinitivo();
			
			bean.setJuego(rs.getInt("JUEGO"));
			bean.setVoz(Voz.getEnum(rs.getInt("VOZ")));
			bean.setAspecto(Aspecto.getEnum(rs.getInt("ASPECTO")));
			bean.setFuerte(FuerteDebil.getEnum(rs.getInt("FUERTE")));
			bean.setDesinencia(rs.getString("DESINENCIA"));
			bean.setSilaba(rs.getInt("SILABA"));
			bean.setSubindice(rs.getInt("SUBINDICE"));
			bean.setAcento(Acento.getEnum(rs.getInt("ACENTO")));
			bean.setOrden(rs.getInt("ORDEN"));
			bean.setOrdenPorDefecto(rs.getInt("ORDEN_POR_DEFECTO"));

			return bean;
		}
	}
	
	class SeleccionRestringidas extends SeleccionAbstracta{
		public SeleccionRestringidas(DataSource dataSource){
			super(dataSource, SELECCION_RESTRINGIDAS_SQL);
		}
	}

	private SeleccionRestringidas seleccionRestringidas;
	
	/* (non-Javadoc)
	 * @see com.kalos.datos.dao.DesinVerbosDAO#seleccionaRestringidas()
	 */
	public List<DesinInfinitivo> seleccionaRestringidas() {
		List<DesinInfinitivo> resultado = seleccionRestringidas.execute(new Object[] {}); 
	    return resultado;
	}


	
	class SeleccionTodas extends SeleccionAbstracta{
		public SeleccionTodas(DataSource dataSource){
			super(dataSource, SELECCION_TODAS_SQL);
		}
	}

	private SeleccionTodas seleccionTodas;
	
	/* (non-Javadoc)
	 * @see com.kalos.datos.dao.DesinVerbosDAO#seleccionaTodas()
	 */
	public List<DesinInfinitivo> seleccionaTodas() {
		List<DesinInfinitivo> resultado = seleccionTodas.execute(new Object[] {}); 
	    return resultado;
	}

	private Insercion insercion;

	class Insercion extends SqlUpdate {

		public Insercion(DataSource datasource) {
			super(datasource, INSERCION_SQL);
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.INTEGER));
		}
	}

	public void inserta(DesinInfinitivo m1) {
		String s = com.kalos.datos.util.DBUtil.getHashableId();
		insercion.update(new Object[]{
				m1.getJuego(),
				m1.getVoz().valorEntero(),
				5,
				m1.getAspecto().valorEntero(),
				m1.getFuerte().valorEntero(),
				m1.getDesinencia(),
				m1.getSilaba(),
				m1.getSubindice(),
				m1.getAcento().valorEntero(),
				m1.getOrden(),
				m1.getOrdenPorDefecto()
		});
	}


	public void initDao() throws Exception {
		super.initDao();
		puebla();
		seleccionRestringidas=new SeleccionRestringidas(getDataSource());
		seleccionTodas=new SeleccionTodas(getDataSource());
        insercion = new Insercion(getDataSource());
	}

}
