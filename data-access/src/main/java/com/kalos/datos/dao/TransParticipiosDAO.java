package com.kalos.datos.dao;

import java.util.List;

import com.kalos.beans.TransParticipiosBean;

public interface TransParticipiosDAO {


	List<TransParticipiosBean> seleccionaTodo();
    List<TransParticipiosBean> seleccionaPorSubstraer(String forma);
    public void inserta(TransParticipiosBean bean);

}