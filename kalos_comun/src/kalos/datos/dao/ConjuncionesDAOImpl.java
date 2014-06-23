// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import kalos.B.L;
import kalos.B.X;
import kalos.B.h;
import kalos.B.q;
import kalos.E.C.A.A;
import kalos.E.C.A.B;
import kalos.E.C.A.D;
import kalos.K.Q;
import kalos.beans.Significado;
import kalos.datos.dao.comunes.ModificaCodigoIndividual;
import kalos.enumeraciones.Idioma;
import kalos.enumeraciones.Particularidad;
import kalos.enumeraciones.SubtipoConjuncion;
import kalos.enumeraciones.TipoConjuncion;
import kalos.recursos.Configuracion;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

// Referenced classes of package kalos.E.C:
//            d

public class ConjuncionesDAOImpl extends JdbcDaoSupport
    implements ConjuncionesDAO
{
    class Update extends SqlUpdate
    {

        final E A;

        public Update(DataSource datasource)
        {
            super(datasource, UPDATE_SQL);
            declareParameter(new SqlParameter(4));
            declareParameter(new SqlParameter(12));
            declareParameter(new SqlParameter(12));
            declareParameter(new SqlParameter(12));
            declareParameter(new SqlParameter(4));
            declareParameter(new SqlParameter(1));
            declareParameter(new SqlParameter(1));
        }
    }

    class Insert extends SqlUpdate
    {

        final E A;

        public Insert(DataSource datasource)
        {
            super(datasource, INSERT_SQL);
            declareParameter(new SqlParameter(1));
            declareParameter(new SqlParameter(4));
            declareParameter(new SqlParameter(12));
            declareParameter(new SqlParameter(12));
            declareParameter(new SqlParameter(12));
            declareParameter(new SqlParameter(4));
            declareParameter(new SqlParameter(1));
        }
    }

    class SelectPorId extends Seleccion
    {

        final E A;

        public SelectPorId(DataSource datasource)
        {
            super(datasource, SELECT_POR_ID_SQL);
            declareParameter(new SqlParameter(1));
        }
    }

    class SelectPorIdConSignificado extends SeleccionConSiginificado
    {

        final E A;

        public SelectPorIdConSignificado(DataSource datasource)
        {
            super(datasource, SELECT_POR_ID_CON_SIG_SQL);
            declareParameter(new SqlParameter(1));
        }
    }

    class SelectPorForma extends Seleccion
    {

        final E A;

        public SelectPorForma(DataSource datasource)
        {
            super(datasource, SELECT_POR_FORMA_SQL);
            declareParameter(new SqlParameter(12));
        }
    }

    class SelectIdsPorSubtipo extends B
    {

        final E G;

        public SelectIdsPorSubtipo(DataSource datasource)
        {
            super(datasource, SELECT_IDS_POR_SUBTIPO_SQL, "CONJUNCION_ID");
            declareParameter(new SqlParameter(1));
        }
    }

    class _M extends B
    {

        final E H;

        public _M(DataSource datasource, String s)
        {
            H = E.this;
            super(datasource, s, "CONJUNCION_ID");
        }
    }

    class SelectSinAcento extends Seleccion
    {

        final E A;

        public SelectSinAcento(DataSource datasource)
        {
            super(datasource, SELECT_SIN_ACENTO_SQL);
        }
    }

    class _H extends Seleccion
    {

        final E A;

        public _H(DataSource datasource, String s)
        {
            A = E.this;
            super(datasource, s);
        }
    }

    class SelectTodasConSignificado extends SeleccionConSiginificado
    {

        final E A;

        public SelectTodasConSignificado(DataSource datasource)
        {
            super(datasource, SELECT_TODAS_CON_SIGNIFICADO_SQL);
        }
    }

    class _L extends SeleccionConSiginificado
    {

        final E A;

        public _L(DataSource datasource, String s)
        {
            A = E.this;
            super(datasource, s);
        }
    }

    abstract class Seleccion extends MappingSqlQuery
    {

        protected Object mapRow(ResultSet resultset, int i)
            throws SQLException
        {
            kalos.beans.ConjuncionBean  e = new kalos.beans.ConjuncionBean();
            e.setId(resultset.getString("CONJUNCION_ID"));
            e.setCodigo(resultset.getInt("CODIGO"));
            e.setForma(resultset.getString("FORMA"));
            e.setPartic(X.getEnum(resultset.getString("PARTIC")));
            e.setTipo(TipoConjuncion.getEnum(resultset.getString("TIPO")));
            e.setSubtipo(SubtipoConjuncion.getEnum(resultset.getString("SUBTIPO")));
            e.setParte(resultset.getInt("PARTE") == 1);
            return e;
        }

        final E A;

        public Seleccion(DataSource datasource, String s)
        {
            A = E.this;
            super(datasource, s);
        }
    }

    abstract class SeleccionConSiginificado extends MappingSqlQuery
    {

        protected Object mapRow(ResultSet resultset, int i)
            throws SQLException
        {
            kalos.beans.ConjuncionBean  e = new kalos.beans.ConjuncionBean();
            e.setId(resultset.getString("CONJUNCION_ID"));
            e.setCodigo(resultset.getInt("CODIGO"));
            e.setForma(resultset.getString("FORMA"));
            e.setPartic(Particularidad.getEnum(resultset.getString("PARTIC")));
            e.setTipo(TipoConjuncion.getEnum(resultset.getString("TIPO")));
            e.setSubtipo(SubtipoConjuncion.getEnum(resultset.getString("SUBTIPO")));
            e.setParte(resultset.getInt("PARTE") == 1);
            Significado sig = new Significado();
            sig.setIdioma(Configuracion.getIdiomaSignificados());
            sig.setReferenteId(e.getId());
            sig.setId(resultset.getString("SIGNIFICADO_ID"));
            sig.setValor(resultset.getString("VALOR"));
            HashMap hashmap = new HashMap();
            hashmap.put(Idioma.getEnum(Configuracion.getIdiomaSignificados()), sig);
            e.setSignificados(hashmap);
            return e;
        }

        final E A;

        public SeleccionConSiginificado(DataSource datasource, String s)
        {
            super(datasource, s);
        }
    }


    public E()
    {
    }

    private void C()
    {
        StringBuffer stringbuffer = new StringBuffer(200);
        stringbuffer = new StringBuffer(200);
        stringbuffer.append("SELECT   \n");
        stringbuffer.append("  CONJ.CONJUNCION_ID   \n");
        stringbuffer.append("FROM        \n");
        stringbuffer.append("  CONJUNCIONES CONJ       \n");
        stringbuffer.append("    INNER JOIN SIGNIFICADOS SIG                 \n");
        stringbuffer.append("      ON CONJ.CONJUNCION_ID=SIG.REFERENTE_ID       \n");
        stringbuffer.append("WHERE  \n");
        stringbuffer.append("  CONJ.FORMA LIKE ?    \n");
        stringbuffer.append((new StringBuilder()).append("  AND SIG.IDIOMA='").append(kalos.recursos.Configuracion.getIdiomaSignificados()).append("'    \n").toString());
        stringbuffer.append("ORDER BY  \n");
        stringbuffer.append("  CONJ.CODIGO   \n");
        SELECT_POR_FORMA_CON_SIG_SQL = stringbuffer.toString();
        stringbuffer = new StringBuffer(200);
        stringbuffer.append("SELECT   \n");
        stringbuffer.append("  CONJ.CONJUNCION_ID,   \n");
        stringbuffer.append("  CONJ.CODIGO,   \n");
        stringbuffer.append("  CONJ.FORMA,   \n");
        stringbuffer.append("  CONJ.PARTIC,   \n");
        stringbuffer.append("  CONJ.TIPO,   \n");
        stringbuffer.append("  CONJ.SUBTIPO,   \n");
        stringbuffer.append("  CONJ.PARTE,   \n");
        stringbuffer.append("  SIG.SIGNIFICADO_ID,   \n");
        stringbuffer.append("  SIG.VALOR   \n");
        stringbuffer.append("FROM        \n");
        stringbuffer.append("  CONJUNCIONES CONJ       \n");
        stringbuffer.append("    INNER JOIN SIGNIFICADOS SIG                 \n");
        stringbuffer.append("      ON CONJ.CONJUNCION_ID=SIG.REFERENTE_ID       \n");
        stringbuffer.append("WHERE  \n");
        stringbuffer.append((new StringBuilder()).append("  SIG.IDIOMA='").append(kalos.recursos.Configuracion.getIdiomaSignificados()).append("'    \n").toString());
        stringbuffer.append("ORDER BY  \n");
        stringbuffer.append("  CONJ.CODIGO   \n");
        SELECT_TODAS_CON_SIGNIFICADO_SQL = stringbuffer.toString();
        stringbuffer = new StringBuffer(200);
        stringbuffer.append("SELECT   \n");
        stringbuffer.append("  CONJ.CONJUNCION_ID   \n");
        stringbuffer.append("FROM        \n");
        stringbuffer.append("  CONJUNCIONES CONJ       \n");
        stringbuffer.append("WHERE  \n");
        stringbuffer.append("  CONJ.SUBTIPO=?    \n");
        stringbuffer.append("ORDER BY  \n");
        stringbuffer.append("  CONJ.CODIGO   \n");
        SELECT_IDS_POR_SUBTIPO_SQL = stringbuffer.toString();
        stringbuffer = new StringBuffer(200);
        stringbuffer.append("SELECT   \n");
        stringbuffer.append("  CONJ.CONJUNCION_ID,   \n");
        stringbuffer.append("  CONJ.CODIGO,   \n");
        stringbuffer.append("  CONJ.FORMA,   \n");
        stringbuffer.append("  CONJ.TIPO,   \n");
        stringbuffer.append("  CONJ.SUBTIPO,   \n");
        stringbuffer.append("  CONJ.PARTE,   \n");
        stringbuffer.append("  CONJ.PARTIC   \n");
        stringbuffer.append("FROM        \n");
        stringbuffer.append("  CONJUNCIONES CONJ \n");
        stringbuffer.append("WHERE  \n");
        stringbuffer.append("  CONJ.FORMA=?    \n");
        SELECT_POR_FORMA_SQL = stringbuffer.toString();
        stringbuffer = new StringBuffer(200);
        stringbuffer.append("SELECT   \n");
        stringbuffer.append("  CONJ.CONJUNCION_ID,   \n");
        stringbuffer.append("  CONJ.CODIGO,   \n");
        stringbuffer.append("  CONJ.FORMA,   \n");
        stringbuffer.append("  CONJ.TIPO,   \n");
        stringbuffer.append("  CONJ.SUBTIPO,   \n");
        stringbuffer.append("  CONJ.PARTE,   \n");
        stringbuffer.append("  CONJ.PARTIC,   \n");
        stringbuffer.append("  SIG.SIGNIFICADO_ID,   \n");
        stringbuffer.append("  SIG.VALOR   \n");
        stringbuffer.append("FROM        \n");
        stringbuffer.append("  CONJUNCIONES CONJ       \n");
        stringbuffer.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
        stringbuffer.append("      ON CONJ.CONJUNCION_ID=SIG.REFERENTE_ID       \n");
        stringbuffer.append("WHERE  \n");
        stringbuffer.append("  CONJ.CONJUNCION_ID IN (?)    \n");
        stringbuffer.append((new StringBuilder()).append("  AND (SIG.IDIOMA IS NULL OR SIG.IDIOMA='").append(kalos.recursos.Configuracion.getIdiomaSignificados()).append("')    \n").toString());
        stringbuffer.append("ORDER BY  \n");
        stringbuffer.append("  CONJ.CODIGO   \n");
        SELECT_POR_VARIOS_IDS_CON_SIG_SQL = stringbuffer.toString();
        stringbuffer = new StringBuffer(200);
        stringbuffer.append("SELECT   \n");
        stringbuffer.append("  CONJ.CONJUNCION_ID,   \n");
        stringbuffer.append("  CONJ.CODIGO,   \n");
        stringbuffer.append("  CONJ.FORMA,   \n");
        stringbuffer.append("  CONJ.TIPO,   \n");
        stringbuffer.append("  CONJ.SUBTIPO,   \n");
        stringbuffer.append("  CONJ.PARTE,   \n");
        stringbuffer.append("  CONJ.PARTIC   \n");
        stringbuffer.append("FROM        \n");
        stringbuffer.append("  CONJUNCIONES CONJ       \n");
        stringbuffer.append("WHERE  \n");
        stringbuffer.append("  CONJ.CONJUNCION_ID IN (?)    \n");
        SELECT_POR_VARIOS_IDS_SQL = stringbuffer.toString();
        stringbuffer = new StringBuffer(200);
        stringbuffer.append("SELECT   \n");
        stringbuffer.append("  CONJ.CONJUNCION_ID,   \n");
        stringbuffer.append("  CONJ.CODIGO,   \n");
        stringbuffer.append("  CONJ.FORMA,   \n");
        stringbuffer.append("  CONJ.TIPO,   \n");
        stringbuffer.append("  CONJ.SUBTIPO,   \n");
        stringbuffer.append("  CONJ.PARTE,   \n");
        stringbuffer.append("  CONJ.PARTIC,   \n");
        stringbuffer.append("FROM        \n");
        stringbuffer.append("  CONJUNCIONES CONJ       \n");
        stringbuffer.append("WHERE  \n");
        stringbuffer.append("  CONJ.CONJUNCION_ID=?    \n");
        SELECT_POR_ID_SQL = stringbuffer.toString();
        stringbuffer = new StringBuffer(200);
        stringbuffer.append("SELECT   \n");
        stringbuffer.append("  CONJ.CONJUNCION_ID,   \n");
        stringbuffer.append("  CONJ.CODIGO,   \n");
        stringbuffer.append("  CONJ.FORMA,   \n");
        stringbuffer.append("  CONJ.TIPO,   \n");
        stringbuffer.append("  CONJ.SUBTIPO,   \n");
        stringbuffer.append("  CONJ.PARTE,   \n");
        stringbuffer.append("  CONJ.PARTIC,   \n");
        stringbuffer.append("  SIG.SIGNIFICADO_ID,   \n");
        stringbuffer.append("  SIG.VALOR   \n");
        stringbuffer.append("FROM        \n");
        stringbuffer.append("  CONJUNCIONES CONJ       \n");
        stringbuffer.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
        stringbuffer.append("      ON CONJ.CONJUNCION_ID=SIG.REFERENTE_ID       \n");
        stringbuffer.append("WHERE  \n");
        stringbuffer.append("  CONJ.CONJUNCION_ID=?    \n");
        stringbuffer.append((new StringBuilder()).append("  AND (SIG.IDIOMA IS NULL OR SIG.IDIOMA='").append(kalos.recursos.Configuracion.getIdiomaSignificados()).append("')    \n").toString());
        SELECT_POR_ID_CON_SIG_SQL = stringbuffer.toString();
        stringbuffer = new StringBuffer(200);
        stringbuffer.append("SELECT   \n");
        stringbuffer.append("  CONJUNCION_ID,   \n");
        stringbuffer.append("  CODIGO,   \n");
        stringbuffer.append("  FORMA,   \n");
        stringbuffer.append("  TIPO,   \n");
        stringbuffer.append("  SUBTIPO,   \n");
        stringbuffer.append("  PARTE,   \n");
        stringbuffer.append("  PARTIC   \n");
        stringbuffer.append("FROM   \n");
        stringbuffer.append("  CONJUNCIONES   \n");
        stringbuffer.append("WHERE   \n");
        stringbuffer.append("  FORMA not like '%/%'  \n");
        stringbuffer.append("  AND FORMA NOT LIKE  '%\\\\%'  \n");
        stringbuffer.append("  AND FORMA NOT LIKE '%=%'  \n");
        SELECT_SIN_ACENTO_SQL = stringbuffer.toString();
        stringbuffer = new StringBuffer(200);
        stringbuffer.append("INSERT INTO CONJUNCIONES (  \n");
        stringbuffer.append("  CONJUNCION_ID,   \n");
        stringbuffer.append("  CODIGO,   \n");
        stringbuffer.append("  FORMA,   \n");
        stringbuffer.append("  TIPO,   \n");
        stringbuffer.append("  SUBTIPO,   \n");
        stringbuffer.append("  PARTE,   \n");
        stringbuffer.append("  PARTIC   \n");
        stringbuffer.append(") VALUES (     \n");
        stringbuffer.append("  ?,?,?,?,?,?,?   \n");
        stringbuffer.append(" )   \n");
        INSERT_SQL = stringbuffer.toString();
        stringbuffer = new StringBuffer(200);
        stringbuffer.append("UPDATE CONJUNCIONES SET  \n");
        stringbuffer.append("  CODIGO=?,   \n");
        stringbuffer.append("  FORMA=?,   \n");
        stringbuffer.append("  TIPO=?,   \n");
        stringbuffer.append("  SUBTIPO=?,   \n");
        stringbuffer.append("  PARTE=?,   \n");
        stringbuffer.append("  PARTIC=?   \n");
        stringbuffer.append("WHERE     \n");
        stringbuffer.append("  CONJUNCION_ID=?   \n");
        UPDATE_SQL = stringbuffer.toString();
        stringbuffer = new StringBuffer(200);
        stringbuffer.append("UPDATE CONJUNCIONES SET  \n");
        stringbuffer.append("  CODIGO=?   \n");
        stringbuffer.append("WHERE     \n");
        stringbuffer.append("  CONJUNCION_ID=?   \n");
        UPDATE_CODIGO_SQL = stringbuffer.toString();
        stringbuffer = new StringBuffer(200);
        stringbuffer.append("DELETE FROM CONJUNCIONES  \n");
        stringbuffer.append("WHERE     \n");
        stringbuffer.append("  CONJUNCION_ID=?   \n");
        DELETE_SQL = stringbuffer.toString();
    }

    public List seleccionaTodos()
    {
        return selTodasConSignificado.execute();
    }

    public List seleccionaConjuncionesNoAcentuables()
    {
        return selSinAcento.execute();
    }

    public List getRegistros(List list)
    {
        ArrayList arraylist = new ArrayList();
        int i = list.size();
        int j = 0;
        char c = '\u03E8';
        for(; i > 0; i -= c)
        {
            List list1 = list.subList(j, Math.min(j + c, list.size()));
            StringBuffer stringbuffer = new StringBuffer(500);
            String s1;
            for(Iterator iterator = list1.iterator(); iterator.hasNext(); stringbuffer.append((new StringBuilder()).append("'").append(s1).append("',").toString()))
                s1 = (String)iterator.next();

            stringbuffer.deleteCharAt(stringbuffer.length() - 1);
            String s = SELECT_POR_VARIOS_IDS_CON_SIG_SQL.replaceFirst("\\?", stringbuffer.toString());
            _L _ll = new _L(getDataSource(), s);
            List list2 = _ll.execute();
            arraylist.addAll(list2);
            j += c;
        }

        return arraylist;
    }

    public List getRegistrosSinSignificado(List list)
    {
        ArrayList arraylist = new ArrayList();
        int i = list.size();
        int j = 0;
        char c = '\u03E8';
        for(; i > 0; i -= c)
        {
            List list1 = list.subList(j, Math.min(j + c, list.size()));
            StringBuffer stringbuffer = new StringBuffer(500);
            String s1;
            for(Iterator iterator = list1.iterator(); iterator.hasNext(); stringbuffer.append((new StringBuilder()).append("'").append(s1).append("',").toString()))
                s1 = (String)iterator.next();

            stringbuffer.deleteCharAt(stringbuffer.length() - 1);
            String s = SELECT_POR_VARIOS_IDS_SQL.replaceFirst("\\?", stringbuffer.toString());
            _H _lh = new _H(getDataSource(), s);
            List list2 = _lh.execute();
            arraylist.addAll(list2);
            j += c;
        }

        return arraylist;
    }

    public List seleccionaPorForma(String s, h h1)
    {
        String s1 = null;
        static class _cls1
        {

            static final int A[];

            static 
            {
                A = new int[h.values().length];
                try
                {
                    A[h.E.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    A[h.A.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    A[h.C.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    A[h.D.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
            }
        }

        switch(kalos.E.C._cls1.A[h1.ordinal()])
        {
        case 1: // '\001'
            s1 = (new StringBuilder()).append("'").append(s).append("%'").toString();
            break;

        case 2: // '\002'
            s1 = (new StringBuilder()).append("'%").append(s).append("%'").toString();
            break;

        case 3: // '\003'
            s1 = (new StringBuilder()).append("'%").append(s).append("'").toString();
            break;

        case 4: // '\004'
            s1 = (new StringBuilder()).append("'").append(s).append("'").toString();
            break;
        }
        String s2 = SELECT_POR_FORMA_CON_SIG_SQL.replaceFirst("\\?", s1);
        _M _lm = new _M(getDataSource(), s2);
        List list = _lm.execute();
        return list;
    }

    public List seleccionaPorSubtipo(SubtipoConjuncion stc)
    {
        List list = selIdsPorSubtipo.execute(new Object[] {
            SubtipoConjuncion.getCadena(stc)
        });
        return list;
    }

    public List seleccionaPorFormaParaAM(String s)
    {
        return selPorForma.execute(new Object[] {
            s
        });
    }

    public kalos.beans.ConjuncionBean  seleccionaInidvidual(String s)
    {
        List list = selPorIdConSig.execute(new Object[] {
            s
        });
        if(list.size() == 0)
            return null;
        else
            return (kalos.beans.ConjuncionBean)list.get(0);
    }

    public kalos.beans.ConjuncionBean  seleccionaInidvidualParaAM(String s)
    {
        List list = selPorId.execute(new Object[] {
            s
        });
        if(list.size() == 0)
            return null;
        else
            return (kalos.beans.ConjuncionBean)list.get(0);
    }

    public void inserta(kalos.beans.ConjuncionBean  e)
    {
        String s = kalos.E.A.A.getHashableId();
        insert.update(new Object[] {
            s, Integer.valueOf(e.getCodigo()), e.getForma(), TipoConjuncion.getCadena(e.getTipo()), SubtipoConjuncion.getCadena(e.getSubtipo()), Integer.valueOf(e.isParte() ? 1 : 0), X.getString(e.getPartic())
        });
        e.setId(s);
    }

    public void modifica(kalos.beans.ConjuncionBean e)
    {
        update.update(new Object[] {
            Integer.valueOf(e.getCodigo()), e.getForma(), TipoConjuncion.getCadena(e.getTipo()), SubtipoConjuncion.getCadena(e.getSubtipo()), Integer.valueOf(e.isParte() ? 1 : 0), X.getString(e.getPartic()), e.getId()
        });
    }

    public void modificaCodigoIndividual(String s, int i)
    {
        modificaCodigoIndividual.update(new Object[] {
            Integer.valueOf(i), s
        });
    }

    public void borra(String s)
    {
        K.update(new Object[] {
            s
        });
    }

    public void initDao()
        throws Exception
    {
        super.initDao();
        C();
        selPorForma = new SelectPorForma(getDataSource());
        selPorIdConSig = new SelectPorIdConSignificado(getDataSource());
        selPorId = new SelectPorId(getDataSource());
        selSinAcento = new SelectSinAcento(getDataSource());
        selTodasConSignificado = new SelectTodasConSignificado(getDataSource());
        selIdsPorSubtipo = new SelectIdsPorSubtipo(getDataSource());
        insert = new Insert(getDataSource());
        K = new D(getDataSource(), DELETE_SQL);
        update = new Update(getDataSource());
        modificaCodigoIndividual = new ModificaCodigoIndividual(getDataSource(), UPDATE_CODIGO_SQL);
    }

    static String B()
    {
        return SELECT_TODAS_CON_SIGNIFICADO_SQL;
    }

    static String H()
    {
        return SELECT_SIN_ACENTO_SQL;
    }

    static String E()
    {
        return SELECT_IDS_POR_SUBTIPO_SQL;
    }

    static String A()
    {
        return SELECT_POR_FORMA_SQL;
    }

    static String G()
    {
        return SELECT_POR_ID_CON_SIG_SQL;
    }

    static String D()
    {
        return SELECT_POR_ID_SQL;
    }

    static String I()
    {
        return INSERT_SQL;
    }

    static String F()
    {
        return UPDATE_SQL;
    }

    private static String SELECT_POR_FORMA_CON_SIG_SQL;
    private static String SELECT_IDS_POR_SUBTIPO_SQL;
    private static String SELECT_POR_FORMA_SQL;
    private static String SELECT_POR_ID_CON_SIG_SQL;
    private static String SELECT_POR_ID_SQL;
    private static String SELECT_POR_VARIOS_IDS_CON_SIG_SQL;
    private static String SELECT_POR_VARIOS_IDS_SQL;
    private static String SELECT_SIN_ACENTO_SQL;
    private static String SELECT_TODAS_CON_SIGNIFICADO_SQL;
    private static String INSERT_SQL;
    private static String UPDATE_CODIGO_SQL;
    private static String UPDATE_SQL;
    private static String DELETE_SQL;
    private SelectTodasConSignificado selTodasConSignificado;
    private SelectSinAcento selSinAcento;
    SelectIdsPorSubtipo selIdsPorSubtipo;
    private SelectPorForma selPorForma;
    private SelectPorIdConSignificado selPorIdConSig;
    private SelectPorId selPorId;
    private Insert insert;
    private Update update;
    private ModificaCodigoIndividual modificaCodigoIndividual;
    private A R;
    private D K;
}
