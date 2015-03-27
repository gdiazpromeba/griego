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
package com.kalos.datos.util;

import java.rmi.server.UID;

import org.apache.commons.lang.StringUtils;



/**
 * Temporary utility class for db methods
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.1 $
 */
public class DBUtil {
    
    /**
     * returns a unique 32-char ID
     * @return the ID
     */
    public static String getHashableId(){
      String key=new UID().toString();
      key=StringUtils.repeat(key, 3);
      key=key.substring(0, 32);
      key=key.replace(':', '1');
      key=key.replace('-', '2');
      return key;
    }
    
    
    
    public static void main(String[] args){
    	String key=getHashableId();
    	System.out.println(key);
    }

}
