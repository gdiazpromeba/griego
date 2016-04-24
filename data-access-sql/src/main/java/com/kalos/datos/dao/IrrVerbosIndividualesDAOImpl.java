// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.// Jad home page: http://www.kpdus.com/jad.html// Decompiler options: packimports(3) package com.kalos.datos.dao;import java.sql.ResultSet;import java.sql.SQLException;import java.util.ArrayList;import java.util.List;import javax.sql.DataSource;import org.springframework.jdbc.core.SqlParameter;import org.springframework.jdbc.core.support.JdbcDaoSupport;import org.springframework.jdbc.object.MappingSqlQuery;import org.springframework.jdbc.object.SqlUpdate;// Referenced classes of package kalos.E.C://            BAimport com.kalos.beans.IrrVerboIndividual;import com.kalos.datos.dao.comunes.Borrado;import com.kalos.datos.dao.comunes.SeleccionIds;import com.kalos.datos.util.DBUtil;import com.kalos.datos.util.Listas;import com.kalos.enumeraciones.FuerteDebil;import com.kalos.enumeraciones.Modo;import com.kalos.enumeraciones.Particularidad;import com.kalos.enumeraciones.Persona;import com.kalos.enumeraciones.Tiempo;import com.kalos.enumeraciones.Voz;public class IrrVerbosIndividualesDAOImpl extends JdbcDaoSupport implements IrrVerbosIndividualesDAO {    class Insercion extends SqlUpdate {        public Insercion(DataSource datasource) {            super(datasource, INSERT_SQL);            declareParameter(new SqlParameter(1));            declareParameter(new SqlParameter(1));            declareParameter(new SqlParameter(12));            declareParameter(new SqlParameter(4));            declareParameter(new SqlParameter(1));            declareParameter(new SqlParameter(4));            declareParameter(new SqlParameter(4));            declareParameter(new SqlParameter(4));            declareParameter(new SqlParameter(4));            declareParameter(new SqlParameter(4));            declareParameter(new SqlParameter(4));        }    }    class Modificacion extends SqlUpdate {        public Modificacion(DataSource datasource) {            super(datasource, UPDATE_SQL);            declareParameter(new SqlParameter(1));            declareParameter(new SqlParameter(12));            declareParameter(new SqlParameter(4));            declareParameter(new SqlParameter(1));            declareParameter(new SqlParameter(4));            declareParameter(new SqlParameter(4));            declareParameter(new SqlParameter(4));            declareParameter(new SqlParameter(4));            declareParameter(new SqlParameter(4));            declareParameter(new SqlParameter(4));            declareParameter(new SqlParameter(1));        }    }    class SeleccionPorIds extends SeleccioAbstracta {        public SeleccionPorIds(DataSource datasource, String s) {            super(datasource, s);        }    }    class SeleccionIdsPorVerboYPartic extends SeleccionIds {        public SeleccionIdsPorVerboYPartic(DataSource datasource) {            super(datasource, SEL_IDS_POR_VERBO_Y_PARTIC_SQL, "IRR_VERBO_INDIVIDUAL_ID");            declareParameter(new SqlParameter(1));            declareParameter(new SqlParameter(1));        }    }    class SeleccionPorForma extends SeleccioAbstracta {        public SeleccionPorForma(DataSource datasource) {            super(datasource, SEL_POR_FORMA_SQL);            declareParameter(new SqlParameter(12));        }    }    class SelPorVerboYPartic extends SeleccioAbstracta {        public SelPorVerboYPartic(DataSource datasource) {            super(datasource, SEL_POR_VERBO_Y_PARTIC_SQL);            declareParameter(new SqlParameter(1));            declareParameter(new SqlParameter(1));        }    }    class SeleccionTodo extends SeleccioAbstracta {        public SeleccionTodo(DataSource datasource) {            super(datasource, SEL_TODO_SQL);        }    }    private class SelPartics extends MappingSqlQuery {        protected Object mapRow(ResultSet resultset, int i) throws SQLException {            Particularidad x = Particularidad.getEnum(resultset.getString("PARTIC"));            return x;        }        public SelPartics(DataSource datasource) {            super(datasource, SEL_PARTICS);            declareParameter(new SqlParameter(1));        }    }    abstract class SeleccioAbstracta extends MappingSqlQuery {        protected Object mapRow(ResultSet resultset, int i) throws SQLException {            IrrVerboIndividual q1 = new IrrVerboIndividual();            q1.setId(resultset.getString("IRR_VERBO_INDIVIDUAL_ID"));            q1.setVerboId(resultset.getString("VERBO_ID"));            q1.setForma(resultset.getString("FORMA"));            q1.setPersona(Persona.getEnum(resultset.getInt("PERS")));            q1.setPartic(Particularidad.getEnum(resultset.getString("PARTIC")));            q1.setSubPart(resultset.getInt("SUBPART"));            q1.setModo(Modo.getEnum(resultset.getInt("MODO")));            q1.setTiempo(Tiempo.getEnum(resultset.getInt("TIEMPO")));            q1.setVoz(Voz.getEnum(resultset.getInt("VOZ")));            q1.setFuerte(FuerteDebil.getEnum(resultset.getInt("FUERTE")));            q1.setRespetaAcento(resultset.getInt("RESPETAR_POSICION_ACENTO") == 1);            q1.setOrdenProceso(resultset.getInt("ORDEN_PROCESO"));            return q1;        }        public SeleccioAbstracta(DataSource datasource, String s) {            super(datasource, s);        }    }    private void puebla() {        StringBuffer sql = new StringBuffer(200);        sql.append("SELECT \n");        sql.append("  irr.IRR_VERBO_INDIVIDUAL_ID,   \n");        sql.append("  irr.VERBO_ID,   \n");        sql.append("  irr.FORMA,   \n");        sql.append("  irr.PERS,   \n");        sql.append("  irr.PARTIC,   \n");        sql.append("  irr.SUBPART,   \n");        sql.append("  irr.TIEMPO,   \n");        sql.append("  irr.FUERTE,   \n");        sql.append("  irr.MODO,   \n");        sql.append("  irr.VOZ,   \n");        sql.append("  irr.RESPETAR_POSICION_ACENTO,   \n");        sql.append("  irr.ORDEN_PROCESO   \n");        sql.append("FROM   \n");        sql.append("   IRR_VERBOS_INDIVIDUALES irr \n");        sql.append("       \n");        sql.append("WHERE             \n");        sql.append("   irr.VERBO_ID=?        \n");        sql.append("   AND irr.PARTIC=?        \n");        sql.append("ORDER BY          \n");        sql.append("   irr.SUBPART,       \n");        sql.append("   irr.ORDEN_PROCESO  \n");        SEL_POR_VERBO_Y_PARTIC_SQL = sql.toString();        sql = new StringBuffer(200);        sql.append("SELECT \n");        sql.append("  irr.IRR_VERBO_INDIVIDUAL_ID,   \n");        sql.append("  irr.VERBO_ID,   \n");        sql.append("  irr.FORMA,   \n");        sql.append("  irr.PERS,   \n");        sql.append("  irr.PARTIC,   \n");        sql.append("  irr.SUBPART,   \n");        sql.append("  irr.TIEMPO,   \n");        sql.append("  irr.FUERTE,   \n");        sql.append("  irr.MODO,   \n");        sql.append("  irr.VOZ,   \n");        sql.append("  irr.RESPETAR_POSICION_ACENTO,   \n");        sql.append("  irr.ORDEN_PROCESO   \n");        sql.append("FROM   \n");        sql.append("   IRR_VERBOS_INDIVIDUALES irr \n");        sql.append("       \n");        sql.append("WHERE             \n");        sql.append("   irr.IRR_VERBO_INDIVIDUAL_ID IN(?)     \n");        sql.append("ORDER BY          \n");        sql.append("   irr.SUBPART,       \n");        sql.append("   irr.ORDEN_PROCESO  \n");        SEL_POR_IDS_SQL = sql.toString();        sql = new StringBuffer(200);        sql.append("SELECT \n");        sql.append("  irr.IRR_VERBO_INDIVIDUAL_ID,   \n");        sql.append("  irr.VERBO_ID,   \n");        sql.append("  irr.FORMA,   \n");        sql.append("  irr.PERS,   \n");        sql.append("  irr.PARTIC,   \n");        sql.append("  irr.SUBPART,   \n");        sql.append("  irr.TIEMPO,   \n");        sql.append("  irr.FUERTE,   \n");        sql.append("  irr.MODO,   \n");        sql.append("  irr.VOZ,   \n");        sql.append("  irr.RESPETAR_POSICION_ACENTO,   \n");        sql.append("  irr.ORDEN_PROCESO   \n");        sql.append("FROM   \n");        sql.append("   IRR_VERBOS_INDIVIDUALES irr \n");        sql.append("       \n");        sql.append("ORDER BY          \n");        sql.append("   irr.SUBPART,       \n");        sql.append("   irr.ORDEN_PROCESO  \n");        SEL_TODO_SQL = sql.toString();        sql = new StringBuffer(200);        sql.append("SELECT \n");        sql.append("  irr.IRR_VERBO_INDIVIDUAL_ID   \n");        sql.append("FROM   \n");        sql.append("   IRR_VERBOS_INDIVIDUALES irr \n");        sql.append("WHERE             \n");        sql.append("   irr.VERBO_ID=?        \n");        sql.append("   AND irr.PARTIC=?        \n");        sql.append("ORDER BY          \n");        sql.append("   irr.SUBPART,       \n");        sql.append("   irr.ORDEN_PROCESO  \n");        SEL_IDS_POR_VERBO_Y_PARTIC_SQL = sql.toString();        sql = new StringBuffer(200);        sql.append("UPDATE  IRR_VERBOS_INDIVIDUALES SET \n");        sql.append("  VERBO_ID=?,   \n");        sql.append("  FORMA=?,   \n");        sql.append("  PERS=?,   \n");        sql.append("  PARTIC=?,   \n");        sql.append("  SUBPART=?,   \n");        sql.append("  TIEMPO=?,   \n");        sql.append("  FUERTE=?,   \n");        sql.append("  MODO=?,   \n");        sql.append("  VOZ=?,   \n");        sql.append("  RESPETAR_POSICION_ACENTO=?   \n");        sql.append("WHERE             \n");        sql.append("  IRR_VERBO_INDIVIDUAL_ID=?   \n");        UPDATE_SQL = sql.toString();        sql = new StringBuffer(200);        sql.append("INSERT INTO IRR_VERBOS_INDIVIDUALES ( \n");        sql.append("  IRR_VERBO_INDIVIDUAL_ID,    \n");        sql.append("  VERBO_ID,    \n");        sql.append("  FORMA,   \n");        sql.append("  PERS,   \n");        sql.append("  PARTIC,   \n");        sql.append("  SUBPART,   \n");        sql.append("  TIEMPO,   \n");        sql.append("  FUERTE,   \n");        sql.append("  MODO,   \n");        sql.append("  VOZ,   \n");        sql.append("  RESPETAR_POSICION_ACENTO   \n");        sql.append(") VALUES (             \n");        sql.append("  ?,?,?,?,?,?,?,?,?,?,             \n");        sql.append("  ?             \n");        sql.append(")   \n");        INSERT_SQL = sql.toString();        sql = new StringBuffer(200);        sql.append("SELECT \n");        sql.append("  irr.IRR_VERBO_INDIVIDUAL_ID,   \n");        sql.append("  irr.VERBO_ID,   \n");        sql.append("  irr.FORMA,   \n");        sql.append("  irr.PERS,   \n");        sql.append("  irr.PARTIC,   \n");        sql.append("  irr.SUBPART,   \n");        sql.append("  irr.TIEMPO,   \n");        sql.append("  irr.FUERTE,   \n");        sql.append("  irr.MODO,   \n");        sql.append("  irr.VOZ,   \n");        sql.append("  irr.RESPETAR_POSICION_ACENTO,   \n");        sql.append("  irr.ORDEN_PROCESO   \n");        sql.append("FROM   \n");        sql.append("   IRR_VERBOS_INDIVIDUALES irr \n");        sql.append("       \n");        sql.append("WHERE             \n");        sql.append("   irr.FORMA=?        \n");        sql.append("ORDER BY          \n");        sql.append("   irr.VERBO_ID       \n");        SEL_POR_FORMA_SQL = sql.toString();        sql = new StringBuffer(200);        sql.append("SELECT DISTINCT  \n");        sql.append("  IRR.PARTIC \n");        sql.append("FROM  \n");        sql.append("  IRR_VERBOS_INDIVIDUALES  IRR \n");        sql.append("  INNER JOIN PARTICULARIDADES PAR ON IRR.PARTIC=PAR.PARTIC  \n");        sql.append("WHERE   \n");        sql.append("  IRR.VERBO_ID=? \n");        sql.append("ORDER BY \n");        sql.append("  PAR.ORDEN \n");        SEL_PARTICS = sql.toString();        sql = new StringBuffer(200);        sql.append("DELETE FROM IRR_VERBOS_INDIVIDUALES WHERE   \n");        sql.append("  IRR_VERBO_INDIVIDUAL_ID=?  \n");        DELETE_SQL = sql.toString();    }    public List seleccionaPartics(String s) {        List list = selPartics.execute(new Object[] { s });        return list;    }    public List seleccionaPorVerboPartic(String s, Particularidad x) {        List list = selPorVerboYPartic.execute(new Object[] { s, x.abreviatura() });        return list;    }    public List seleccionaTodo() {        List list = seleccionTodo.execute(new Object[] { });        return list;    }    public List seleccionaPorForma(String s) {        List list = selPorForma.execute(new Object[] { s });        return list;    }    public List seleccionaIdsPorVerboPartic(String s, Particularidad x) {        List list = selIdsPorVerboYPartic.execute(new Object[] { s, x.abreviatura() });        return list;    }    public List<IrrVerboIndividual> getRegistros(List<String> ids) {        List<List<String>> listaDeListas = Listas.segmentos(ids, 500);        List<IrrVerboIndividual> beans = new ArrayList<IrrVerboIndividual>();        for (List<String> listaDeIds : listaDeListas) {            StringBuffer sb = new StringBuffer(500);            for (String id : listaDeIds) {                sb.append("'").append(id).append("',");            }            String sql = SEL_POR_IDS_SQL.replaceFirst("\\?", sb.toString());            SeleccionPorIds sel = new SeleccionPorIds(getDataSource(), sql);            List<IrrVerboIndividual> segmBeans = sel.execute();            beans.addAll(segmBeans);        }        return beans;    }    public void modifica(IrrVerboIndividual irrVerbInd) {        modificacion.update(new Object[] { irrVerbInd.getVerboId(), irrVerbInd.getForma(), irrVerbInd.getPersona().valorEntero(),                irrVerbInd.getPartic().abreviatura(), Integer.valueOf(irrVerbInd.getSubPart()),                irrVerbInd.getTiempo().valorEntero(), irrVerbInd.getFuerte().valorEntero(),                irrVerbInd.getModo().valorEntero(), irrVerbInd.getVoz().valorEntero(),                Integer.valueOf(irrVerbInd.isRespetaAcento() ? 1 : 0), irrVerbInd.getId() });    }    public void inserta(IrrVerboIndividual q1) {        String s = DBUtil.getHashableId();        insercion.update(new Object[] { s, q1.getVerboId(), q1.getForma(), q1.getPersona().valorEntero(),                q1.getPartic().abreviatura(), q1.getSubPart(),                q1.getTiempo().valorEntero(), q1.getFuerte().valorEntero(),                q1.getModo().valorEntero(), q1.getVoz().valorEntero(),                Integer.valueOf(q1.isRespetaAcento() ? 1 : 0) });        q1.setId(s);    }    public void borra(String s) {        borrado.update(new Object[] { s });    }    public void initDao() throws Exception {        super.initDao();        puebla();        borrado = new Borrado(getDataSource(), DELETE_SQL);        insercion = new Insercion(getDataSource());        modificacion = new Modificacion(getDataSource());        selPorVerboYPartic = new SelPorVerboYPartic(getDataSource());        selPorForma = new SeleccionPorForma(getDataSource());        selPartics = new SelPartics(getDataSource());        seleccionTodo = new SeleccionTodo(getDataSource());        selIdsPorVerboYPartic = new SeleccionIdsPorVerboYPartic(getDataSource());    }    private static String SEL_POR_VERBO_Y_PARTIC_SQL;    private static String SEL_IDS_POR_VERBO_Y_PARTIC_SQL;    private static String SEL_POR_IDS_SQL;    private static String SEL_POR_FORMA_SQL;    private static String SEL_TODO_SQL;    private static String SEL_PARTICS;    private static String UPDATE_SQL;    private static String INSERT_SQL;    private static String DELETE_SQL;    private SeleccionTodo seleccionTodo;    private SelPartics selPartics;    private SelPorVerboYPartic selPorVerboYPartic;    private SeleccionPorForma selPorForma;    SeleccionIdsPorVerboYPartic selIdsPorVerboYPartic;    private Modificacion modificacion;    private Insercion insercion;    private Borrado borrado;}