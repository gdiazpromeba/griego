
package com.kalos.analisismorfologico;

import junit.framework.TestCase;

import org.junit.Ignore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

        
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.kalos.comun.config");
        context.refresh();
        return context;
    }

}