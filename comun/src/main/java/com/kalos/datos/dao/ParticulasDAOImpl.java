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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.kalos.beans.ParticulaBean;
import com.kalos.beans.Significado;
import com.kalos.datos.dao.comunes.Borrado;
import com.kalos.datos.dao.comunes.SeleccionIds;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Idioma;
import com.kalos.enumeraciones.Numero;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Persona;
import com.kalos.enumeraciones.TipoPalabra;
import com.kalos.recursos.Configuracion;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

/**
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.1 $
 */
@SuppressWarnings("unchecked")
public class ParticulasDAOImpl extends JdbcDaoSupport implements ParticulasDAO  {


	private static String SELECCION_POR_TIPO_SQL;
	private static String SELECCION_POR_ENCABEZADO_SQL;
	private static String SELECCION_INDIVIDUAL_SQL;
	private static String SELECCION_POR_FORMA_SQL;
	private static String SELECCION_NO_ACENTUABLES_SQL;
	private static String SELECCION_TODOS_IDS_SQL;
	private static String SELECCION_IDS_POR_TIPO_SQL;
	private static String SELECCION_IDS_POR_ENCABEZADO_SQL;
	private static String SELECCION_POR_IDS_SQL;
	private static String INSERCION_SQL;
	private static String MODIFICACION_SQL;
	private static String BORRADO_SQL;
	
	

	private void puebla() {
		StringBuffer sb = new StringBuffer(200);

		sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  PARTICULA_ID,   \n");
		sb.append("  PARTICULA_ENCABEZADO_ID,   \n");
		sb.append("  TIPO_PALABRA,   \n");
		sb.append("  PARTICULARIDAD,   \n");
		sb.append("  CASO,   \n");
		sb.append("  PERSONA,   \n");
		sb.append("  SUBINDICE,   \n");
		sb.append("  GENERO,   \n");
		sb.append("  NUMERO,   \n");
		sb.append("  FORMA   \n");
		sb.append("FROM   \n");
		sb.append("  PARTICULAS   \n");
		sb.append("WHERE   \n");
		sb.append("  TIPO_PALABRA=?   \n");
		

		SELECCION_POR_TIPO_SQL = sb.toString();
		
		sb = new StringBuffer(200);

		sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  PARTICULA_ID,   \n");
		sb.append("  PARTICULA_ENCABEZADO_ID,   \n");
		sb.append("  TIPO_PALABRA,   \n");
		sb.append("  PARTICULARIDAD,   \n");
		sb.append("  CASO,   \n");
		sb.append("  PERSONA,   \n");
		sb.append("  SUBINDICE,   \n");
		sb.append("  GENERO,   \n");
		sb.append("  NUMERO,   \n");
		sb.append("  FORMA   \n");
		sb.append("FROM   \n");
		sb.append("  PARTICULAS   \n");
		sb.append("WHERE   \n");
		sb.append("  PARTICULA_ENCABEZADO_ID=?   \n");
		

		SELECCION_POR_ENCABEZADO_SQL = sb.toString();		
		
        sb = new StringBuffer(200);
        sb.append("SELECT   \n");
        sb.append("  PART.PARTICULA_ID,   \n"); 
        sb.append("  PART.PARTICULA_ENCABEZADO_ID,   \n");
		sb.append("  PART.TIPO_PALABRA,   \n");
		sb.append("  PART.PARTICULARIDAD,   \n");
		sb.append("  PART.CASO,   \n");
		sb.append("  PART.PERSONA,   \n");
		sb.append("  PART.SUBINDICE,   \n");
		sb.append("  PART.GENERO,   \n");
		sb.append("  PART.NUMERO,   \n");
		sb.append("  PART.FORMA,   \n");
        sb.append("  SIG.SIGNIFICADO_ID,   \n");
        sb.append("  SIG.VALOR   \n");
        sb.append("FROM        \n");
        sb.append("  PARTICULAS PART       \n");
        sb.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
        sb.append("      ON PART.PARTICULA_ID=SIG.REFERENTE_ID       \n");
        sb.append("WHERE  \n");
        sb.append("  PART.PARTICULA_ID IN (?)    \n");
        sb.append("  AND (SIG.IDIOMA IS NULL OR SIG.IDIOMA='" + Configuracion.getIdiomaSignificados() + "')    \n");
		sb.append("ORDER BY    \n");
		sb.append("  PART.PARTICULA_ENCABEZADO_ID,    \n");		
		sb.append("  PART.TIPO_PALABRA,    \n");
		sb.append("  PART.PARTICULARIDAD,    \n");
		sb.append("  PART.GENERO,    \n");
		sb.append("  PART.NUMERO,    \n");
		sb.append("  PART.CASO,    \n");
		sb.append("  PART.PERSONA    \n");

        SELECCION_POR_IDS_SQL = sb.toString();
		
		
		sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  PARTICULA_ID   \n");
		sb.append("FROM   \n");
		sb.append("  PARTICULAS   \n");
		sb.append("ORDER BY    \n");
		sb.append("  PARTICULA_ENCABEZADO_ID,   \n");
		sb.append("  TIPO_PALABRA,    \n");
		sb.append("  PARTICULARIDAD,    \n");
		sb.append("  GENERO,    \n");
		sb.append("  NUMERO,    \n");
		sb.append("  CASO,    \n");
		sb.append("  PERSONA    \n");

		SELECCION_TODOS_IDS_SQL= sb.toString();
		
		sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  PARTICULA_ID   \n");
		sb.append("FROM   \n");
		sb.append("  PARTICULAS   \n");
		sb.append("WHERE    \n");
		sb.append("  TIPO_PALABRA=?   \n");
		sb.append("ORDER BY    \n");
		sb.append("  PARTICULA_ENCABEZADO_ID,   \n");
		sb.append("  TIPO_PALABRA,    \n");
		sb.append("  PARTICULARIDAD,    \n");
		sb.append("  GENERO,    \n");
		sb.append("  NUMERO,    \n");
		sb.append("  CASO,    \n");
		sb.append("  PERSONA    \n");

		SELECCION_IDS_POR_TIPO_SQL= sb.toString();	
		
		
		sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  PARTICULA_ID   \n");
		sb.append("FROM   \n");
		sb.append("  PARTICULAS   \n");
		sb.append("WHERE    \n");
		sb.append("  PARTICULA_ENCABEZADO_ID=?   \n");
		sb.append("ORDER BY    \n");
		sb.append("  PARTICULA_ENCABEZADO_ID,   \n");
		sb.append("  TIPO_PALABRA,    \n");
		sb.append("  PARTICULARIDAD,    \n");
		sb.append("  GENERO,    \n");
		sb.append("  NUMERO,    \n");
		sb.append("  CASO,    \n");
		sb.append("  PERSONA    \n");

		SELECCION_IDS_POR_ENCABEZADO_SQL= sb.toString();			
		
		sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  PARTICULA_ID,   \n");
		sb.append("  PARTICULA_ENCABEZADO_ID,   \n");
		sb.append("  TIPO_PALABRA,   \n");
		sb.append("  PARTICULARIDAD,   \n");
		sb.append("  CASO,   \n");
		sb.append("  PERSONA,   \n");
		sb.append("  SUBINDICE,   \n");
		sb.append("  GENERO,   \n");
		sb.append("  NUMERO,   \n");
		sb.append("  FORMA   \n");
		sb.append("FROM   \n");
		sb.append("  PARTICULAS   \n");
		sb.append("WHERE   \n");
		sb.append("  PARTICULA_ID=?   \n");

		SELECCION_INDIVIDUAL_SQL = sb.toString();
		
		sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  PART.PARTICULA_ID,   \n");
		sb.append("  PART.PARTICULA_ENCABEZADO_ID,   \n");
		sb.append("  PART.TIPO_PALABRA,   \n");
		sb.append("  PART.PARTICULARIDAD,   \n");
		sb.append("  PART.CASO,   \n");
		sb.append("  PART.PERSONA,   \n");
		sb.append("  PART.SUBINDICE,   \n");
		sb.append("  PART.GENERO,   \n");
		sb.append("  PART.NUMERO,   \n");
		sb.append("  PART.FORMA   \n");
        sb.append("FROM        \n");
        sb.append("  PARTICULAS PART       \n");
		sb.append("WHERE   \n");
		sb.append("  FORMA=?   \n");

		SELECCION_POR_FORMA_SQL = sb.toString();
		

		sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  PART.PARTICULA_ID,   \n");
		sb.append("  PART.PARTICULA_ENCABEZADO_ID,   \n");
		sb.append("  PART.TIPO_PALABRA,   \n");
		sb.append("  PART.PARTICULARIDAD,   \n");
		sb.append("  PART.CASO,   \n");
		sb.append("  PART.PERSONA,   \n");
		sb.append("  PART.SUBINDICE,   \n");
		sb.append("  PART.GENERO,   \n");
		sb.append("  PART.NUMERO,   \n");
		sb.append("  PART.FORMA   \n");
        sb.append("FROM        \n");
        sb.append("  PARTICULAS PART       \n");
		sb.append("WHERE   \n");
		sb.append("  FORMA not like '%/%'  \n"); 
	    sb.append("  AND FORMA NOT LIKE  '%\\\\%'  \n"); 
		sb.append("  AND FORMA NOT LIKE '%=%'  \n");


		SELECCION_NO_ACENTUABLES_SQL = sb.toString();
		
        sb = new StringBuffer(200);
        sb.append("INSERT INTO PARTICULAS (  \n");
        sb.append("  PARTICULA_ID,   \n");
        sb.append("  PARTICULA_ENCABEZADO_ID,   \n");
        sb.append("  TIPO_PALABRA,   \n");
        sb.append("  PARTICULARIDAD,   \n");
        sb.append("  CASO,   \n");
        sb.append("  PERSONA,   \n");
        sb.append("  SUBINDICE,   \n");
        sb.append("  GENERO,   \n");
        sb.append("  NUMERO,   \n");
        sb.append("  FORMA   \n");
        sb.append(") VALUES (     \n");
        sb.append("  ?,?,?,?,?,?,?,?,?,?   \n");
        sb.append(" )   \n");

        INSERCION_SQL = sb.toString();

        sb = new StringBuffer(200);
        sb.append("UPDATE PARTICULAS SET  \n");
        sb.append("  PARTICULA_ENCABEZADO_ID=?,   \n");
        sb.append("  TIPO_PALABRA=?,   \n");
        sb.append("  PARTICULARIDAD=?,   \n");
        sb.append("  CASO=?,   \n");
        sb.append("  PERSONA=?,   \n");
        sb.append("  SUBINDICE=?,   \n");
        sb.append("  GENERO=?,   \n");
        sb.append("  NUMERO=?,   \n");
        sb.append("  FORMA=?   \n");
        sb.append("WHERE     \n");
        sb.append("  PARTICULA_ID=?   \n");

        MODIFICACION_SQL = sb.toString();
        
        sb = new StringBuffer(200);
        sb.append("DELETE FROM PARTICULAS  \n");
        sb.append("WHERE     \n");
        sb.append("  PARTICULA_ID=?   \n");

        BORRADO_SQL = sb.toString();
		

		

		
	}

	
	//general select
	abstract class SeleccionAbstractaSinSignificado extends MappingSqlQuery {
		public SeleccionAbstractaSinSignificado(DataSource dataSource, String sql) {
			super(dataSource, sql);
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			ParticulaBean bean = new ParticulaBean();
			bean.setParticulaId(rs.getString("PARTICULA_ID"));
			bean.setParticulaEncabezadoId(rs.getString("PARTICULA_ENCABEZADO_ID"));
			bean.setParticulaTipo(TipoPalabra.getEnum(rs.getString("TIPO_PALABRA")));
			bean.setParticularidad((Particularidad)Particularidad.getEnum(rs.getString("PARTICULARIDAD")));
			bean.setCaso((Caso)Caso.getEnum(rs.getInt("CASO")));
			int persona=rs.getInt("PERSONA");
			if (!rs.wasNull()){
				bean.setPersona(Persona.getEnum(persona));
			}
			bean.setSubindice(rs.getInt("SUBINDICE"));
			String genero=rs.getString("GENERO");
			if (!rs.wasNull()){
			  bean.setGenero((Genero)Genero.getEnum(genero));
			}
			int numero=rs.getInt("NUMERO");
			if (!rs.wasNull()){
			  bean.setNumero((Numero)Numero.getEnum(numero));
			}
			bean.setForma(rs.getString("FORMA"));
			return bean;
		}
	}	
	
	
	//general select
	abstract class SeleccionAbstracta extends MappingSqlQuery {
		public SeleccionAbstracta(DataSource dataSource, String sql) {
			super(dataSource, sql);
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			ParticulaBean bean = new ParticulaBean();
			bean.setParticulaId(rs.getString("PARTICULA_ID"));
			bean.setParticulaEncabezadoId(rs.getString("PARTICULA_ENCABEZADO_ID"));
			bean.setParticulaTipo(TipoPalabra.getEnum(rs.getString("TIPO_PALABRA")));
			bean.setParticularidad((Particularidad)Particularidad.getEnum(rs.getString("PARTICULARIDAD")));
			bean.setCaso((Caso)Caso.getEnum(rs.getInt("CASO")));
			int persona=rs.getInt("PERSONA");
			if (!rs.wasNull()){
				bean.setPersona(Persona.getEnum(persona));
			}
			bean.setSubindice(rs.getInt("SUBINDICE"));
			String genero=rs.getString("GENERO");
			if (!rs.wasNull()){
			  bean.setGenero((Genero)Genero.getEnum(genero));
			}
			int numero=rs.getInt("NUMERO");
			if (!rs.wasNull()){
			  bean.setNumero((Numero)Numero.getEnum(numero));
			}
			bean.setForma(rs.getString("FORMA"));
            // un significado
            Significado sig = new Significado();
            sig.setIdioma(Configuracion.getIdiomaSignificados());
            sig.setReferenteId(bean.getId());
            sig.setId(rs.getString("SIGNIFICADO_ID"));
            sig.setValor(rs.getString("VALOR"));
            Map<Idioma, Significado> mapSignificados = new HashMap<Idioma, Significado>();
            mapSignificados.put(Idioma.getEnum(Configuracion.getIdiomaSignificados()), sig);
            bean.setSignificados(mapSignificados);
			return bean;
		}
	}

	class SeleccionPorTipo extends SeleccionAbstractaSinSignificado {
		public SeleccionPorTipo(DataSource dataSource) {
			super(dataSource, SELECCION_POR_TIPO_SQL);
			declareParameter(new SqlParameter(Types.CHAR)); //tipo
		}
	}
	
	class SeleccionPorEncabezado extends SeleccionAbstractaSinSignificado {
		public SeleccionPorEncabezado(DataSource dataSource) {
			super(dataSource, SELECCION_POR_ENCABEZADO_SQL);
			declareParameter(new SqlParameter(Types.CHAR)); //id encabezado
		}
	}
	
	
	class SeleccionIndividual extends SeleccionAbstractaSinSignificado {
		public SeleccionIndividual(DataSource dataSource) {
			super(dataSource, SELECCION_INDIVIDUAL_SQL);
			declareParameter(new SqlParameter(Types.CHAR)); //id
		}
	}
	
	class SeleccionPorForma extends SeleccionAbstractaSinSignificado {
		public SeleccionPorForma(DataSource dataSource) {
			super(dataSource, SELECCION_POR_FORMA_SQL);
			declareParameter(new SqlParameter(Types.VARCHAR)); //forma
		}
	}
	
	class SeleccionNoAcentuables extends SeleccionAbstractaSinSignificado {
		public SeleccionNoAcentuables(DataSource dataSource) {
			super(dataSource, SELECCION_NO_ACENTUABLES_SQL);
		}
	}


	private SeleccionPorTipo seleccionPorTipo;
	
	public List<ParticulaBean> seleccionaParticulasDadoTipoSinSignificado(TipoPalabra tipo) {
		return seleccionPorTipo.execute(new Object[] {tipo.getStringCorta()});
	}
	
	private SeleccionIndividual seleccionIndividual;
	
	public ParticulaBean seleccionaIndividual(String id) {
		List<ParticulaBean> particulas=seleccionIndividual.execute(new Object[] {id});
		if (particulas.size()>0){
			return particulas.get(0);
		}else{
			return null;
		}
	}
	
	private SeleccionPorForma seleccionPorForma;
	

	public List<ParticulaBean> seleccionaParticulasDadaFormaSinSignificado(String forma) {
		return seleccionPorForma.execute(new Object[] {forma});
	}
	
	private SeleccionNoAcentuables seleccionNoAcentuables;
	

	@SuppressWarnings("unchecked")
	public List<ParticulaBean> seleccionaParticulasNoAcentuablesSinSignificado() {
		return seleccionNoAcentuables.execute();
	}
	

	private SeleccionPorEncabezado seleccionPorEncabezado; 

	public List<ParticulaBean> seleccionaParticulasDadoEncabezadoSinSignificado(String idEncabezado) {
		List<ParticulaBean> resultado=seleccionPorEncabezado.execute(new Object[]{idEncabezado});
		return resultado;
	}
	
	
    // selección de ids por encabezado
    class SeleccionIdsPorEncabezado extends SeleccionIds {
        public SeleccionIdsPorEncabezado(DataSource dataSource) {
            super(dataSource, SELECCION_IDS_POR_ENCABEZADO_SQL, "PARTICULA_ID");
            declareParameter(new SqlParameter(Types.CHAR));//tipoPalabra            
        }
    }
    
    private SeleccionIdsPorEncabezado seleccionIdsPorEncabezado;  
    
	public List<String> seleccionaIdsPorEncabezado(String idEncabezado) {
		return seleccionIdsPorEncabezado.execute(new Object[]{idEncabezado});
	}

	
    // selección de todos
    class SeleccionTodosIds extends SeleccionIds {
        public SeleccionTodosIds(DataSource dataSource) {
            super(dataSource, SELECCION_TODOS_IDS_SQL, "PARTICULA_ID");

        }
    }
    
    private SeleccionTodosIds seleccionTodosIds;
    
    @SuppressWarnings("unchecked")
    public List<String> seleccionaTodosLosIds(){
    	return seleccionTodosIds.execute();
    }
    
    // selección por tipo de palabra
    class SeleccionIdsPorTipo extends SeleccionIds {
        public SeleccionIdsPorTipo(DataSource dataSource) {
            super(dataSource, SELECCION_IDS_POR_TIPO_SQL, "PARTICULA_ID");
            declareParameter(new SqlParameter(Types.CHAR));//tipoPalabra
        }
    }
    
    private SeleccionIdsPorTipo seleccionIdsPorTipo;
    
    @SuppressWarnings("unchecked")
    public List<String> seleccionaIdsPorTipo(TipoPalabra tipoPalabra){
    	return seleccionIdsPorTipo.execute(new Object[]{
    			tipoPalabra.getStringCorta()
    			});
    }
	
	
    // inserción
    class Insercion extends SqlUpdate {
        public Insercion(DataSource dataSource) {
            super(dataSource, INSERCION_SQL);
            declareParameter(new SqlParameter(Types.CHAR)); // id
            declareParameter(new SqlParameter(Types.CHAR)); // id encabezado
            declareParameter(new SqlParameter(Types.VARCHAR)); // tipoPalabra
            declareParameter(new SqlParameter(Types.CHAR)); // particularidad
            declareParameter(new SqlParameter(Types.INTEGER)); // caso
            declareParameter(new SqlParameter(Types.INTEGER)); // persona
            declareParameter(new SqlParameter(Types.INTEGER)); // subindice
            declareParameter(new SqlParameter(Types.CHAR)); // genero
            declareParameter(new SqlParameter(Types.INTEGER)); // numero
            declareParameter(new SqlParameter(Types.VARCHAR)); // forma
        }
    }

    private Insercion insercion;

    public void inserta(ParticulaBean ea) {
        String pk = com.kalos.datos.util.DBUtil.getHashableId();
        insercion.update(new Object[]{
        		pk, 
        		ea.getParticulaEncabezadoId(),
        		TipoPalabra.getString(ea.getParticulaTipo()),
        		Particularidad.getString(ea.getParticularidad()),
        		ea.getCaso()!=null?Caso.getInt(ea.getCaso()): null,
        		ea.getPersona()!=null?Persona.getInt(ea.getPersona()):null,
        		ea.getSubindice(),
        		ea.getGenero()!=null?Genero.getLetra(ea.getGenero()):null,
        		ea.getNumero()!=null?Numero.getInt(ea.getNumero()):null,
        	    ea.getForma()
        });
        ea.setParticulaId(pk);
    }	
    
    // update
    class Modificacion extends SqlUpdate {
        public Modificacion(DataSource dataSource) {
            super(dataSource, MODIFICACION_SQL);
            declareParameter(new SqlParameter(Types.CHAR)); // id encabezado
            declareParameter(new SqlParameter(Types.VARCHAR)); // tipoPalabra
            declareParameter(new SqlParameter(Types.CHAR)); // particularidad
            declareParameter(new SqlParameter(Types.INTEGER)); // caso
            declareParameter(new SqlParameter(Types.INTEGER)); // persona
            declareParameter(new SqlParameter(Types.INTEGER)); // subindice
            declareParameter(new SqlParameter(Types.CHAR)); // genero
            declareParameter(new SqlParameter(Types.INTEGER)); // numero
            declareParameter(new SqlParameter(Types.VARCHAR)); // forma
            // where
            declareParameter(new SqlParameter(Types.CHAR)); // id
        }
    }

    private Modificacion modificacion;

    
    
    public void modifica(ParticulaBean ea) {
        modificacion.update(new Object[]{
        		ea.getParticulaEncabezadoId(),
        		TipoPalabra.getString(ea.getParticulaTipo()), 
        		Particularidad.getString(ea.getParticularidad()),
        		ea.getCaso()!=null?Caso.getInt(ea.getCaso()): null,
        		ea.getPersona()!=null?Persona.getInt(ea.getPersona()):null,
        		ea.getSubindice(),
        		ea.getGenero()!=null?Genero.getLetra(ea.getGenero()):null,
        		ea.getNumero()!=null?Numero.getInt(ea.getNumero()):null,
        	    ea.getForma(),
        	    //where
        	    ea.getId()
        });
    }    
    
    //delete
    private Borrado borrado;

    public void borra(String id) {
        borrado.update(new Object[]{id  });
    }    
    
    
    // selección de registros a mostrar de la tabla "PARTICULAS", dados los
    // IDs
    class SeleccionPorIds extends SeleccionAbstracta {
        public SeleccionPorIds(DataSource dataSource, String sql) {
            super(dataSource, sql);
        }
    }    
	
    @SuppressWarnings("unchecked")
    public List<ParticulaBean> getRegistros(List<String> ids) {
        List<ParticulaBean> resultado = new ArrayList<ParticulaBean>();
        int restantes = ids.size();
        int comienzo = 0;
        int segmento = 1000;
        while (restantes > 0) {
            List<String> subIds = ids.subList(comienzo, Math.min(comienzo + segmento, ids.size()));
            StringBuffer idsSeparadosComa = new StringBuffer(500);
            for (String id : subIds) {
                idsSeparadosComa.append("'" + id + "',");
            }
            idsSeparadosComa.deleteCharAt(idsSeparadosComa.length() - 1);
            String sql = SELECCION_POR_IDS_SQL.replaceFirst("\\?", idsSeparadosComa.toString());

            SeleccionPorIds seleccionPorIds = new SeleccionPorIds(getDataSource(), sql);
            List<ParticulaBean> entradasDic = seleccionPorIds.execute();
            resultado.addAll(entradasDic);
            comienzo += segmento;
            restantes -= segmento;
        }
        return resultado;
    }
    

	public void initDao() throws Exception {
		super.initDao();
		puebla();
		seleccionPorTipo= new SeleccionPorTipo(getDataSource());
		seleccionPorEncabezado= new SeleccionPorEncabezado(getDataSource());
		seleccionIndividual= new SeleccionIndividual(getDataSource());
		seleccionPorForma= new SeleccionPorForma(getDataSource());
		seleccionNoAcentuables= new SeleccionNoAcentuables(getDataSource());
		seleccionTodosIds=new SeleccionTodosIds(getDataSource());
		seleccionIdsPorTipo=new SeleccionIdsPorTipo(getDataSource());
		seleccionIdsPorEncabezado=new SeleccionIdsPorEncabezado(getDataSource());
		insercion=new Insercion(getDataSource());
		modificacion=new Modificacion(getDataSource());
		borrado = new Borrado(getDataSource(), BORRADO_SQL);
	}

}
