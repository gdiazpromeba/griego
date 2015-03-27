package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.TransParticipiosBean;

public interface TransParticipiosDAO {


	List<TransParticipiosBean> getTodos();
    List<TransParticipiosBean> seleccionaPorSubstraer(String forma);

}