// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.kalos.beans.VerbalizadorBean;
import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Voz;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;

// Referenced classes of package kalos.E.C:
//            x

public class VerbalizadorParticipiosDAOImpl extends JdbcDaoSupport implements VerbalizadorParticipiosDAO {
    
    class SeleccionPorGen extends SeleccionAbstracta {


	public SeleccionPorGen(DataSource datasource) {
	    super(datasource, SELECT_BY_GEN_SQL);
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(12));
	}
    }

    class SeleccionPorNom extends SeleccionAbstracta {
	public SeleccionPorNom(DataSource datasource) {
	    super(datasource, SELECT_BY_NOM_SQL);
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(12));
	}
    }

    abstract class SeleccionAbstracta extends MappingSqlQuery {

	protected Object mapRow(ResultSet resultset, int i) throws SQLException {
	    VerbalizadorBean bean = new VerbalizadorBean();
	    bean.setVoz(Voz.getEnum(resultset.getInt("VOZ")));
	    bean.setAspecto(Aspecto.getEnum(resultset.getInt("ASPECTO")));
	    bean.setFuerte(FuerteDebil.getEnum(resultset.getInt("FUERTE")));
	    bean.setGenero(Genero.getEnum(resultset.getString("GENERO")));
	    bean.setTerminacionGenitivo(resultset.getString("TERM_GEN"));
	    bean.setTerminacionNominativo(resultset.getString("TERM_NOM"));
	    bean.setTipoVerbo(resultset.getInt("TIPO_VERBO"));
	    return bean;
	}


	public SeleccionAbstracta(DataSource datasource, String sql) {
	    super(datasource, sql);
	}
    }

    public VerbalizadorParticipiosDAOImpl() {
    }

    private void B() {
	StringBuffer stringbuffer = new StringBuffer(200);
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT \n");
	stringbuffer.append("   VOZ, \n");
	stringbuffer.append("   ASPECTO, \n");
	stringbuffer.append("   TIPO_VERBO, \n");
	stringbuffer.append("   GENERO, \n");
	stringbuffer.append("   TERM_NOM, \n");
	stringbuffer.append("   TERM_GEN, \n");
	stringbuffer.append("   FUERTE \n");
	stringbuffer.append("FROM \n");
	stringbuffer.append("   VERBALIZADOR_PARTICIPIOS \n");
	stringbuffer.append("WHERE \n");
	stringbuffer.append(" SUBSTRING(?, LENGTH(?)-LENGTH(TERM_NOM)+1, LENGTH(?))=TERM_NOM \n");
	stringbuffer.append("ORDER BY \n");
	stringbuffer.append("   TIPO_VERBO, VOZ, ASPECTO, GENERO \n");
	SELECT_BY_NOM_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT \n");
	stringbuffer.append("   VOZ, \n");
	stringbuffer.append("   ASPECTO, \n");
	stringbuffer.append("   TIPO_VERBO, \n");
	stringbuffer.append("   GENERO, \n");
	stringbuffer.append("   TERM_NOM, \n");
	stringbuffer.append("   TERM_GEN, \n");
	stringbuffer.append("   FUERTE \n");
	stringbuffer.append("FROM \n");
	stringbuffer.append("   VERBALIZADOR_PARTICIPIOS \n");
	stringbuffer.append("WHERE \n");
	stringbuffer.append(" SUBSTRING(?, LENGTH(?)-LENGTH(TERM_GEN)+1, LENGTH(?))=TERM_GEN \n");
	stringbuffer.append("ORDER BY \n");
	stringbuffer.append("   TIPO_VERBO, VOZ, ASPECTO, GENERO \n");
	SELECT_BY_GEN_SQL = stringbuffer.toString();
    }

    @SuppressWarnings("unchecked")
    public List<VerbalizadorBean> seleccionaPorTerminacionNominativo(String s) {
	List<VerbalizadorBean> list = seleccionPorGen.execute(new Object[] { s, s, s });
	return list;
    }

    @SuppressWarnings("unchecked")
    public List<VerbalizadorBean> seleccionaPorTerminacionGenitivo(String s) {
	List<VerbalizadorBean> list = seleccionPorNom.execute(new Object[] { s, s, s });
	return list;
    }

    public void initDao() throws Exception {
	super.initDao();
	B();
	seleccionPorGen = new SeleccionPorNom(getDataSource());
	seleccionPorNom = new SeleccionPorGen(getDataSource());
    }



    private static String SELECT_BY_GEN_SQL;
    private static String SELECT_BY_NOM_SQL;
    private SeleccionPorNom seleccionPorGen;
    private SeleccionPorGen seleccionPorNom;
}
