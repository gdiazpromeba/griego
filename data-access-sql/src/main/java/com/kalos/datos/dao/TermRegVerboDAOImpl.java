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

import com.kalos.beans.TermRegVerbo;
import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Modo;
import com.kalos.enumeraciones.Persona;
import com.kalos.enumeraciones.Silaba;
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
public class TermRegVerboDAOImpl extends JdbcDaoSupport implements TermRegVerboDAO  {

	private static String SELECCION_POR_TERMINACION_SQL;
	private static String SELECCION_TODO_SQL;
    private static String INSERCION_SQL;

	private void puebla() {
		StringBuffer sb = new StringBuffer(200);

		sb.append("SELECT \n");
		sb.append("   VOZ, \n");
		sb.append("   MODO, \n");
		sb.append("   TIEMPO, \n");
		sb.append("   FUERTE, \n");
		sb.append("   PERSONA, \n");
		sb.append("   TIPO_DESINENCIA, \n");
		sb.append("   TERMINACION, \n");
		sb.append("   REGEX_TERM, \n");
		sb.append("   SILABA, \n");
		sb.append("   ACENTO \n");
		sb.append("FROM \n");
		sb.append("   TERM_REG_VERBOS \n");
		sb.append("WHERE \n");
		sb.append(" SUBSTRING(?, LENGTH(?)-LENGTH(TERMINACION)+1, LENGTH(?))=TERMINACION \n");
		SELECCION_POR_TERMINACION_SQL = sb.toString();


		sb = new StringBuffer(200);
		sb.append("SELECT \n");
		sb.append("   VOZ, \n");
		sb.append("   MODO, \n");
		sb.append("   TIEMPO, \n");
		sb.append("   FUERTE, \n");
		sb.append("   PERSONA, \n");
		sb.append("   TIPO_DESINENCIA, \n");
		sb.append("   TERMINACION, \n");
		sb.append("   REGEX_TERM, \n");
		sb.append("   SILABA, \n");
		sb.append("   ACENTO \n");
		sb.append("FROM \n");
		sb.append("   TERM_REG_VERBOS \n");
		SELECCION_TODO_SQL = sb.toString();

        sb = new StringBuffer(200);
        sb.append("INSERT INTO TERM_REG_VERBOS ( \n");
        sb.append("   VOZ, \n");
        sb.append("   MODO, \n");
        sb.append("   TIEMPO, \n");
        sb.append("   FUERTE, \n");
        sb.append("   PERSONA, \n");
        sb.append("   TIPO_DESINENCIA, \n");
        sb.append("   TERMINACION, \n");
        sb.append("   REGEX_TERM, \n");
        sb.append("   SILABA, \n");
        sb.append("   ACENTO \n");
        sb.append(") VALUES ( \n");
        sb.append(" ?,?,?,?,?,?,?,?,?,? \n");
        sb.append(") \n");
        INSERCION_SQL = sb.toString();
	}

	//general select
	abstract class SeleccionAbstracta extends MappingSqlQuery {
		public SeleccionAbstracta(DataSource dataSource, String sql) {
			super(dataSource, sql);
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			TermRegVerbo bean = new TermRegVerbo();
			bean.setVoz(Voz.getEnum(rs.getInt("VOZ")));
			bean.setModo(Modo.getEnum(rs.getInt("MODO")));
			bean.setTiempo(Tiempo.getEnum(rs.getInt("TIEMPO")));
			bean.setFuerte(FuerteDebil.getEnum(rs.getInt("FUERTE")));
			bean.setPersona(Persona.getEnum(rs.getInt("PERSONA")));
			bean.setTipoDesinencia(rs.getInt("TIPO_DESINENCIA"));
			bean.setTerminacion(rs.getString("TERMINACION"));
			bean.setRegEx(rs.getString("REGEX_TERM"));
			bean.setSilaba(Silaba.getEnum(rs.getInt("SILABA")));
			bean.setAcento(Acento.getEnum(rs.getInt("ACENTO")));
			return bean;
		}
	}

	//selección por terminación
	class SeleccionPorTerminacion extends SeleccionAbstracta {
		public SeleccionPorTerminacion(DataSource dataSource) {
			super(dataSource, SELECCION_POR_TERMINACION_SQL);
			declareParameter(new SqlParameter(Types.VARCHAR)); //la termnación
			declareParameter(new SqlParameter(Types.VARCHAR));// la misma terminación
			declareParameter(new SqlParameter(Types.VARCHAR));//la misma terminación (3 veces)
		}
	}

    //selección de todo
    class SeleccionTodo extends SeleccionAbstracta {
        public SeleccionTodo(DataSource dataSource) {
            super(dataSource, SELECCION_TODO_SQL);
        }
    }


    private class Insercion extends SqlUpdate {
        public Insercion(DataSource dataSource){
            super(dataSource, INSERCION_SQL);
            declareParameter(new SqlParameter(Types.INTEGER)); //voz
            declareParameter(new SqlParameter(Types.INTEGER)); //modo
            declareParameter(new SqlParameter(Types.INTEGER)); //tiempo
            declareParameter(new SqlParameter(Types.INTEGER)); //fuerte
            declareParameter(new SqlParameter(Types.INTEGER)); //persona
            declareParameter(new SqlParameter(Types.INTEGER)); //tipo desinencia
            declareParameter(new SqlParameter(Types.VARCHAR)); //terminacion
            declareParameter(new SqlParameter(Types.VARCHAR)); //regex
            declareParameter(new SqlParameter(Types.INTEGER)); //silaba
            declareParameter(new SqlParameter(Types.INTEGER)); //acento
        }
    }

    private Insercion insercion;

    public void inserta(TermRegVerbo bean){
        insercion.update(new Object[]{
                bean.getVoz().valorEntero(),
                bean.getModo().valorEntero(),
                bean.getTiempo().valorEntero(),
                bean.getFuerte().valorEntero(),
                bean.getPersona().valorEntero(),
                bean.getTipoDesinencia(),
                bean.getTerminacion(),
                bean.getRegEx(),
                bean.getSilaba().valorEntero(),
                bean.getAcento().valorEntero()
        });
    }

    private SeleccionPorTerminacion seleccionPorTerminacion;
    private SeleccionTodo seleccionTodo;


	/* (non-Javadoc)
	 * @see kalos.dao.TermRegVerboDAO#seleccionaPorTerminacion(java.lang.String)
	 */
	public List<TermRegVerbo> seleccionaPorTerminacion(String terminacion) {
		List<TermRegVerbo> lstAux = seleccionPorTerminacion.execute(new Object[] {
				terminacion, terminacion, terminacion});
		return lstAux;
	}

    public List<TermRegVerbo> seleccionaTodo() {
        List<TermRegVerbo> lstAux = seleccionTodo.execute();
        return lstAux;
    }


	public void initDao() throws Exception {
		super.initDao();
		puebla();
		seleccionPorTerminacion = new SeleccionPorTerminacion(getDataSource());
        seleccionTodo = new SeleccionTodo(getDataSource());
        insercion = new Insercion(getDataSource());

	}

}
