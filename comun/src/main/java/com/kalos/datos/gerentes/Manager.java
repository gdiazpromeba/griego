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
package com.kalos.datos.gerentes;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;


/**
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.2 $
 */
public class Manager implements BeanFactoryAware {

    private InitializingBean dao;

    private BeanFactory beanFactory;

    /**
     * @see com.turner.ide.trivia.cma.business.IManager#getDao()
     * @return the DAO object
     */
    public InitializingBean getDao() {
        return this.dao;
    }

    /**
     * @see com.turner.ide.trivia.cma.business.IManager#setDao(org.springframework.beans.factory.InitializingBean)
     * @param dao the DAO object
     */
    public void setDao(InitializingBean dao) {
        this.dao = dao;
    }

    /**
     * @see org.springframework.beans.factory.BeanFactoryAware#setBeanFactory(BeanFactory)
     * @param arg0               the bean factory
     * @throws BeansException    exception
     */
    public void setBeanFactory(BeanFactory arg0) throws BeansException {
        this.beanFactory = arg0;

    }

    /**
     * @return Returns the beanFactory.
     */
    public BeanFactory getBeanFactory() {
        return this.beanFactory;
    }

}
