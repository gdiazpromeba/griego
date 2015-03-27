
package com.kalos.analisismorfologico;

import junit.framework.TestCase;

import org.junit.Ignore;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.kalos.enumeraciones.CompLetras;

;

/**
 * <p>Title: Kalos</p>
 * <p>Description: Greek verb conjugation and research tool</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

@Ignore
public class BaseAMTest extends TestCase implements CompLetras {

    public static ApplicationContext creaContexto() {
        ApplicationContext contexto;
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        ClassPathResource res = new ClassPathResource("daos.xml");
        reader.loadBeanDefinitions(res);
        res = new ClassPathResource("gerentes-datos.xml");
        reader.loadBeanDefinitions(res);
        res = new ClassPathResource("flexion.xml");
        reader.loadBeanDefinitions(res);
        res = new ClassPathResource("analisisMorfologico.xml");
        reader.loadBeanDefinitions(res);

        contexto = new GenericApplicationContext(factory);
        return contexto;
    }

}