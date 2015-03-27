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

import com.kalos.beans.TermRegInfinitivo;
import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.Contraccion;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Silaba;
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
public class TermRegInfinitivoDAOImpl extends JdbcDaoSupport implements TermRegInfinitivoDAO  {

	private static String SELECCION_POR_TERMINACION_SQL;
	private static String INSERCION_SQL;
	private static String BORRA_TODO;

	private void puebla() {
		StringBuffer sb = new StringBuffer(200);

		sb.append("SELECT \n");
		sb.append("   VOZ,  \n");
		sb.append("   ASPECTO,  \n");
		sb.append("   FUERTE,  \n");
		sb.append("   TIPO_DESINENCIA,  \n");
		sb.append("   TERMINACION,     \n");
		sb.append("   REGEX_TERM,    \n");
		sb.append("   SILABA,      \n");
		sb.append("   ACENTO,    \n");
		sb.append("   CONTRACCION_GENERADORA \n");    
		sb.append("FROM \n");
		sb.append("   TERM_REG_INFIN \n");
		sb.append("WHERE \n");
		sb.append(" SUBSTRING(?, LENGTH(?)-LENGTH(TERMINACION)+1, LENGTH(?))=TERMINACION \n");
		SELECCION_POR_TERMINACION_SQL = sb.toString();
		
		sb = new StringBuffer(200);
		sb.append("INSERT INTO TERM_REG_INFIN ( \n");
		sb.append("   VOZ,  \n");
		sb.append("   ASPECTO,  \n");
		sb.append("   FUERTE,  \n");
		sb.append("   TIPO_DESINENCIA,  \n");
		sb.append("   TERMINACION,     \n");
		sb.append("   REGEX_TERM,    \n");
		sb.append("   SILABA,      \n");
		sb.append("   ACENTO,    \n");
		sb.append("   CONTRACCION_GENERADORA) \n");    
		sb.append("VALUES ( \n");
		sb.append("   ?,?,?,?,?,?,?,?,?) \n");
		INSERCION_SQL=sb.toString();
		
		
		sb = new StringBuffer(200);
		sb.append("DELETE FROM TERM_REG_INFIN ");
		BORRA_TODO=sb.toString();
		
	}

	//general select
	abstract class SeleccionAbstracta extends MappingSqlQuery {
		public SeleccionAbstracta(DataSource dataSource, String sql) {
			super(dataSource, sql);
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			TermRegInfinitivo bean = new TermRegInfinitivo();
			bean.setVoz(Voz.getEnum(rs.getInt("VOZ")));
			bean.setAspecto(Aspecto.getEnum(rs.getInt("ASPECTO")));
			bean.setFuerte(FuerteDebil.getEnum(rs.getInt("FUERTE")));
			bean.setTipoDesinencia(rs.getInt("TIPO_DESINENCIA"));
			bean.setTerminacion(rs.getString("TERMINACION"));
			bean.setRegEx(rs.getString("REGEX_TERM"));
			bean.setSilaba(Silaba.getEnum(rs.getInt("SILABA")));
			bean.setAcento(Acento.getEnum(rs.getInt("ACENTO")));
			bean.setContraccionGeneradora(Contraccion.getEnum(rs.getInt("CONTRACCION_GENERADORA")));
			return bean;
		}
	}

	//selección por terminación
	class SeleccionPorTerminacion extends SeleccionAbstracta {
		public SeleccionPorTerminacion(DataSource dataSource) {
			super(dataSource, SELECCION_POR_TERMINACION_SQL);
			declareParameter(new SqlParameter(Types.VARCHAR)); //la terminación
			declareParameter(new SqlParameter(Types.VARCHAR));// la misma terminación
			declareParameter(new SqlParameter(Types.VARCHAR));//la misma terminación (3 veces)
		}
	}


	private SeleccionPorTerminacion seleccionPorTerminacion;

	/* (non-Javadoc)
	 * @see kalos.dao.TermRegInfinitivoDAO#seleccionaPorTerminacion(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see kalos.dao.TermRegInfinitivoDAO#seleccionaPorTerminacion(java.lang.String)
	 */
	public List<TermRegInfinitivo> seleccionaPorTerminacion(String terminacion) {
		List<TermRegInfinitivo> lstAux = seleccionPorTerminacion.execute(new Object[] {
				terminacion, terminacion, terminacion});
		return lstAux;
	}

	
	private class Insercion extends SqlUpdate {
		public Insercion(DataSource dataSource){
			super(dataSource, INSERCION_SQL);
			declareParameter(new SqlParameter(Types.INTEGER)); //voz
			declareParameter(new SqlParameter(Types.INTEGER)); //aspecto
			declareParameter(new SqlParameter(Types.INTEGER)); //fuerte
			declareParameter(new SqlParameter(Types.INTEGER)); //tipo desinencia
			declareParameter(new SqlParameter(Types.VARCHAR)); //terminacion
			declareParameter(new SqlParameter(Types.VARCHAR)); //regex
			declareParameter(new SqlParameter(Types.INTEGER)); //silaba
			declareParameter(new SqlParameter(Types.INTEGER)); //acento
			declareParameter(new SqlParameter(Types.INTEGER)); //contracción generadora
		}
	}
	
	private Insercion insercion;
	
	public void inserta(TermRegInfinitivo bean){
		insercion.update(new Object[]{
				Voz.getInt(bean.getVoz()),
				Aspecto.getInt((Aspecto)bean.getAspecto()),
				FuerteDebil.getInt(bean.getFuerte()),
				bean.getTipoDesinencia(),
				bean.getTerminacion(),
				bean.getRegEx(),
				bean.getSilaba(),
				Acento.getInt(bean.getAcento()),
				Contraccion.getInt(bean.getContraccionGeneradora())
		});
	}	
	
	private class BorraTodo extends SqlUpdate{
		public BorraTodo(DataSource dataSource){
			super(dataSource, BORRA_TODO);
		}
	}
	
	public void borraTodo(){
		borraTodo.update();
	}
	
	private BorraTodo borraTodo; 

	public void initDao() throws Exception {
		super.initDao();
		puebla();
		seleccionPorTerminacion = new SeleccionPorTerminacion(getDataSource());
		insercion=new Insercion(getDataSource());
		borraTodo=new BorraTodo(getDataSource());
	}

}
