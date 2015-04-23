
package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.kalos.beans.IrrSustantivoBean;
import com.kalos.datos.dao.comunes.Borrado;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Numero;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.utils.Listas;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

public class IrrSustantivosDAOImpl extends JdbcDaoSupport implements IrrSustantivosDAO {

    private static String SEL_POR_SUSTANTIVO_SQL;
    private static String SEL_POR_SUSTANTIVOS_SQL;
    private static String SEL_POR_SUST_Y_PARTIC_SQL;
    private static String SEL_POR_FORMA_SQL;
    private static String SEL_PARTICS_POR_SUST_SQL;
    private static String INSERT_SQL;
    private static String UPDATE_SQL;
    private static String DELETE_SQL;
    private SeleccionPorId seleccionPorId;
    private SeleccionPorIdYPartic seleccionPorIdYPartic;
    private SeleccionPorForma seleccionPorForma;
    private SeleccionPartics seleccionPartics;
    private Insercion insercion;
    private Modificacion modificacion;
    private Borrado borrado;

    private void puebla() {
        StringBuffer localStringBuffer = new StringBuffer(200);
        localStringBuffer.append("SELECT   \n");
        localStringBuffer.append("  IRR.IRR_SUSTANTIVO_ID,   \n");
        localStringBuffer.append("  IRR.SUSTANTIVO_ID,   \n");
        localStringBuffer.append("  IRR.PARTICULARIDAD,   \n");
        localStringBuffer.append("  IRR.CASO,   \n");
        localStringBuffer.append("  IRR.NUMERO,   \n");
        localStringBuffer.append("  IRR.SUBINDICE,   \n");
        localStringBuffer.append("  IRR.FORMA   \n");
        localStringBuffer.append("FROM        \n");
        localStringBuffer.append("  IRR_SUSTANTIVOS IRR       \n");
        localStringBuffer.append("WHERE  \n");
        localStringBuffer.append("  IRR.SUSTANTIVO_ID=?    \n");
        SEL_POR_SUSTANTIVO_SQL = localStringBuffer.toString();
        localStringBuffer = new StringBuffer(200);
        localStringBuffer.append("SELECT   \n");
        localStringBuffer.append("  IRR.IRR_SUSTANTIVO_ID,   \n");
        localStringBuffer.append("  IRR.SUSTANTIVO_ID,   \n");
        localStringBuffer.append("  IRR.PARTICULARIDAD,   \n");
        localStringBuffer.append("  IRR.CASO,   \n");
        localStringBuffer.append("  IRR.NUMERO,   \n");
        localStringBuffer.append("  IRR.SUBINDICE,   \n");
        localStringBuffer.append("  IRR.FORMA   \n");
        localStringBuffer.append("FROM        \n");
        localStringBuffer.append("  IRR_SUSTANTIVOS IRR       \n");
        localStringBuffer.append("WHERE  \n");
        localStringBuffer.append("  IRR.IRR_SUSTANTIVO_ID IN (?)    \n");
        SEL_POR_SUSTANTIVOS_SQL = localStringBuffer.toString();
        localStringBuffer = new StringBuffer(200);
        localStringBuffer.append("SELECT   \n");
        localStringBuffer.append("  IRR.IRR_SUSTANTIVO_ID,   \n");
        localStringBuffer.append("  IRR.SUSTANTIVO_ID,   \n");
        localStringBuffer.append("  IRR.PARTICULARIDAD,   \n");
        localStringBuffer.append("  IRR.CASO,   \n");
        localStringBuffer.append("  IRR.NUMERO,   \n");
        localStringBuffer.append("  IRR.SUBINDICE,   \n");
        localStringBuffer.append("  IRR.FORMA   \n");
        localStringBuffer.append("FROM        \n");
        localStringBuffer.append("  IRR_SUSTANTIVOS IRR       \n");
        localStringBuffer.append("WHERE  \n");
        localStringBuffer.append("  IRR.SUSTANTIVO_ID=?    \n");
        localStringBuffer.append("  AND IRR.PARTICULARIDAD=?    \n");
        SEL_POR_SUST_Y_PARTIC_SQL = localStringBuffer.toString();
        localStringBuffer = new StringBuffer(200);
        localStringBuffer.append("SELECT   \n");
        localStringBuffer.append("  IRR.IRR_SUSTANTIVO_ID,   \n");
        localStringBuffer.append("  IRR.SUSTANTIVO_ID,   \n");
        localStringBuffer.append("  IRR.PARTICULARIDAD,   \n");
        localStringBuffer.append("  IRR.CASO,   \n");
        localStringBuffer.append("  IRR.NUMERO,   \n");
        localStringBuffer.append("  IRR.SUBINDICE,   \n");
        localStringBuffer.append("  IRR.FORMA   \n");
        localStringBuffer.append("FROM        \n");
        localStringBuffer.append("  IRR_SUSTANTIVOS IRR       \n");
        localStringBuffer.append("WHERE  \n");
        localStringBuffer.append("  IRR.FORMA=?    \n");
        SEL_POR_FORMA_SQL = localStringBuffer.toString();
        localStringBuffer = new StringBuffer(200);
        localStringBuffer.append("SELECT DISTINCT  \n");
        localStringBuffer.append("  IRR.PARTICULARIDAD  \n");
        localStringBuffer.append("FROM  \n");
        localStringBuffer.append("  IRR_SUSTANTIVOS IRR  \n");
        localStringBuffer.append("  INNER JOIN PARTICULARIDADES PAR ON IRR.PARTICULARIDAD=PAR.PARTIC  \n");
        localStringBuffer.append("WHERE   \n");
        localStringBuffer.append("  SUSTANTIVO_ID=? \n");
        localStringBuffer.append("ORDER BY \n");
        localStringBuffer.append("  PAR.ORDEN \n");
        SEL_PARTICS_POR_SUST_SQL = localStringBuffer.toString();
        localStringBuffer = new StringBuffer(200);
        localStringBuffer.append("INSERT INTO IRR_SUSTANTIVOS (   \n");
        localStringBuffer.append("  IRR_SUSTANTIVO_ID,   \n");
        localStringBuffer.append("  SUSTANTIVO_ID,   \n");
        localStringBuffer.append("  PARTICULARIDAD,   \n");
        localStringBuffer.append("  CASO,   \n");
        localStringBuffer.append("  NUMERO,   \n");
        localStringBuffer.append("  SUBINDICE,   \n");
        localStringBuffer.append("  FORMA   \n");
        localStringBuffer.append(") VALUES (        \n");
        localStringBuffer.append("  ?,?,?,?,?,?,?      \n");
        localStringBuffer.append(")  \n");
        INSERT_SQL = localStringBuffer.toString();
        localStringBuffer = new StringBuffer(200);
        localStringBuffer.append("UPDATE IRR_SUSTANTIVOS SET   \n");
        localStringBuffer.append("  SUSTANTIVO_ID=?,   \n");
        localStringBuffer.append("  PARTICULARIDAD=?,   \n");
        localStringBuffer.append("  CASO=?,   \n");
        localStringBuffer.append("  NUMERO=?,   \n");
        localStringBuffer.append("  SUBINDICE=?,   \n");
        localStringBuffer.append("  FORMA=?   \n");
        localStringBuffer.append("WHERE    \n");
        localStringBuffer.append("  IRR_SUSTANTIVO_ID=?   \n");
        UPDATE_SQL = localStringBuffer.toString();
        localStringBuffer = new StringBuffer(200);
        localStringBuffer.append("DELETE FROM IRR_SUSTANTIVOS \n");
        localStringBuffer.append("WHERE    \n");
        localStringBuffer.append("  IRR_SUSTANTIVO_ID=?   \n");
        DELETE_SQL = localStringBuffer.toString();
    }

    public List<String> seleccionaPartics(String paramString) {
        List localList = this.seleccionPartics.execute(new Object[] { paramString });
        return localList;
    }

    public List<IrrSustantivoBean> seleccionaPorId(String paramString) {
        List localList = this.seleccionPorId.execute(new Object[] { paramString });
        return localList;
    }

    public List<IrrSustantivoBean> seleccionaPorIdyPartic(String paramString, Particularidad paramX) {
        List localList = this.seleccionPorIdYPartic.execute(new Object[] { paramString, Particularidad.getString(paramX) });
        return localList;
    }

    public List<IrrSustantivoBean> getRegistros(List<String> ids) {
        List<List<String>> listaDeListas = Listas.segmentos(ids, 500);
        List<IrrSustantivoBean> beans = new ArrayList<IrrSustantivoBean>();
        for (List<String> listaDeIds : listaDeListas) {
            StringBuffer sb = new StringBuffer(500);
            for (String id : listaDeIds) {
                sb.append("'").append(id).append("',");
            }
            String sql = SEL_POR_SUSTANTIVOS_SQL.replaceFirst("\\?", sb.toString());
            SeleccionPorSustantivos sel = new SeleccionPorSustantivos(getDataSource(), sql);
            List<IrrSustantivoBean> segmBeans = sel.execute();
            beans.addAll(segmBeans);
        }
        return beans;
    }

    public List<IrrSustantivoBean> seleccionaPorForma(String paramString) {
        List localList = this.seleccionPorForma.execute(new Object[] { paramString });
        return localList;
    }

    public void inserta(IrrSustantivoBean paramU) {
        String str = com.kalos.datos.util.DBUtil.getHashableId();
        this.insercion.update(new Object[] { str, paramU.getSustantivoId(),
                Particularidad.getString(paramU.getParticularidad()), paramU.getCaso().valorEntero(),
                paramU.getNumero().valorEntero(), Integer.valueOf(paramU.getSubindice()),
                paramU.getForma() });
        paramU.setId(str);
    }

    public void actualiza(IrrSustantivoBean irrSust) {
        this.modificacion.update(new Object[] { irrSust.getSustantivoId(), Particularidad.getString(irrSust.getParticularidad()),
                irrSust.getCaso().valorEntero(), irrSust.getNumero().valorEntero(),
                Integer.valueOf(irrSust.getSubindice()), irrSust.getForma(), irrSust.getId() });
    }

    public void borra(String paramString) {
        this.borrado.update(new Object[] { paramString });
    }

    public void initDao() throws Exception {
        super.initDao();
        puebla();
        this.insercion = new Insercion(getDataSource());
        this.modificacion = new Modificacion(getDataSource());
        this.borrado = new Borrado(getDataSource(), DELETE_SQL);
        this.seleccionPorId = new SeleccionPorId(getDataSource());
        this.seleccionPorForma = new SeleccionPorForma(getDataSource());
        this.seleccionPartics = new SeleccionPartics(getDataSource());
        this.seleccionPorIdYPartic = new SeleccionPorIdYPartic(getDataSource());
    }

    private class Modificacion extends SqlUpdate {

        public Modificacion(DataSource paramDataSource) {
            super(paramDataSource, UPDATE_SQL);
            declareParameter(new SqlParameter(1));
            declareParameter(new SqlParameter(1));
            declareParameter(new SqlParameter(4));
            declareParameter(new SqlParameter(4));
            declareParameter(new SqlParameter(4));
            declareParameter(new SqlParameter(12));
            declareParameter(new SqlParameter(1));
        }
    }

    private class Insercion extends SqlUpdate {

        public Insercion(DataSource paramDataSource) {
            super(paramDataSource, INSERT_SQL);
            declareParameter(new SqlParameter(1));
            declareParameter(new SqlParameter(1));
            declareParameter(new SqlParameter(1));
            declareParameter(new SqlParameter(4));
            declareParameter(new SqlParameter(4));
            declareParameter(new SqlParameter(4));
            declareParameter(new SqlParameter(12));
        }
    }

    private class SeleccionPartics extends MappingSqlQuery {

        public SeleccionPartics(DataSource paramDataSource) {
            super(paramDataSource, SEL_PARTICS_POR_SUST_SQL);
            declareParameter(new SqlParameter(1));
        }

        protected Object mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
            String str = paramResultSet.getString("PARTICULARIDAD");
            return str;
        }
    }

    private class SeleccionPorForma extends SeleccionAbstracta {

        public SeleccionPorForma(DataSource paramDataSource) {
            super(paramDataSource, SEL_POR_FORMA_SQL);
            declareParameter(new SqlParameter(12));
        }
    }

    private class SeleccionPorIdYPartic extends SeleccionAbstracta {

        public SeleccionPorIdYPartic(DataSource paramDataSource) {
            super(paramDataSource, SEL_POR_SUST_Y_PARTIC_SQL);
            declareParameter(new SqlParameter(1));
            declareParameter(new SqlParameter(1));
        }
    }

    private class SeleccionPorSustantivos extends SeleccionAbstracta {

        public SeleccionPorSustantivos(DataSource paramDataSource, String paramString) {
            super(paramDataSource, paramString);
        }
    }

    private class SeleccionPorId extends SeleccionAbstracta {

        public SeleccionPorId(DataSource paramDataSource) {
            super(paramDataSource, SEL_POR_SUSTANTIVO_SQL);
            declareParameter(new SqlParameter(1));
        }
    }

    private abstract class SeleccionAbstracta extends MappingSqlQuery {

        public SeleccionAbstracta(DataSource paramDataSource, String paramString) {
            super(paramDataSource, paramString);
        }

        protected Object mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
            IrrSustantivoBean localU = new IrrSustantivoBean();
            localU.setId(paramResultSet.getString("IRR_SUSTANTIVO_ID"));
            localU.setSustantivoId(paramResultSet.getString("SUSTANTIVO_ID"));
            localU.setParticularidad(Particularidad.getEnum(paramResultSet.getString("PARTICULARIDAD")));
            localU.setCaso(Caso.getEnum(paramResultSet.getInt("CASO")));
            localU.setNumero(Numero.getEnum(paramResultSet.getInt("NUMERO")));
            localU.setSubindice(paramResultSet.getInt("SUBINDICE"));
            localU.setForma(paramResultSet.getString("FORMA"));
            return localU;
        }
    }
}
