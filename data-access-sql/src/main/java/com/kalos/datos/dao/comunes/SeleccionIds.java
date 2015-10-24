package com.kalos.datos.dao.comunes;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;

//selecciones que devuelven ID's solamente
public abstract class SeleccionIds extends MappingSqlQuery {
	
	private String campoId;
	public SeleccionIds(DataSource dataSource, String sql, String campoId) {
		super(dataSource, sql);
		this.campoId=campoId;
	}

	protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		String id=rs.getString(campoId);
		return id;
	}
}