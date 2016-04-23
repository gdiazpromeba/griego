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
import java.util.List;

import javax.sql.DataSource;

import com.kalos.beans.DesinInfinitivo;
import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Voz;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;

/**
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.1 $
 */
@SuppressWarnings("unchecked")
public class DesinInfintivosDAOImpl extends JdbcDaoSupport  implements DesinInfinitivosDAO {

	private static String SELECCION_RESTRINGIDAS_SQL;
	private static String SELECCION_TODAS_SQL;
	

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

	public void inserta(DesinInfinitivo desinInfinitivo){
		throw new RuntimeException("not implemented");
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

	

	public void initDao() throws Exception {
		super.initDao();
		puebla();
		seleccionRestringidas=new SeleccionRestringidas(getDataSource());
		seleccionTodas=new SeleccionTodas(getDataSource());
	}

}
