package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.VerbalizadorBean;

public interface VerbalizadorParticipiosDAO {

	List<VerbalizadorBean> seleccionaPorTerminacionNominativo(String terminacionNominativo);

	List<VerbalizadorBean> seleccionaPorTerminacionGenitivo(String terminacionGenitivo);

}