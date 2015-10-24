// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.operaciones;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.log4j.Logger;

import com.kalos.beans.TermRegVerbal;

// Referenced classes of package kalos.G:
//            I

public class OpBeans {
    
    private static Logger logger=Logger.getLogger(OpBeans.class.getName());

	public static String getId(Object obj) {
		try {
			return BeanUtils.getProperty(obj, "id");

		} catch (NoSuchMethodException nosuchmethodexception) {
			nosuchmethodexception.printStackTrace();
		} catch (IllegalAccessException illegalaccessexception) {
			illegalaccessexception.printStackTrace();
		} catch (InvocationTargetException invocationtargetexception) {
			invocationtargetexception.printStackTrace();
		}
		throw new RuntimeException((new StringBuilder())
				.append("no se pudo extraer id de este bean ").append(obj)
				.toString());
	}

	public static String getPropiedad(Object obj, String s) {
		try {
			return BeanUtils.getProperty(obj, s);
		} catch (Exception exception) {
			throw new RuntimeException((new StringBuilder())
					.append("la propiedad ").append(s)
					.append(" no existe en el bean ").append(obj)
					.append(", o no es String").toString());
		}
	}



	public static Object getPropiedadObject(Object obj, String s) {
		try {
			String s1 = A(obj, s);
			Object obj1 = MethodUtils.invokeExactMethod(obj, s1, new Object[0]);
			return obj1;
		} catch (Exception exception) {
			StringBuffer stringbuffer = new StringBuffer((new StringBuilder())
					.append("la propiedad ").append(s)
					.append(" no existe en el bean ").append(obj).append("\n")
					.toString());
			stringbuffer.append("las propiedades del bean son:\n");
			stringbuffer.append(debugBean(obj, new String[0]));
			A.error(stringbuffer.toString(), exception);
			return null;
		}
	}

	private static String A(Object obj, String s) {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append(s.substring(0, 1).toUpperCase());
		stringbuffer.append(s.substring(1));
		String s1 = "is".concat(stringbuffer.toString());
		String s2 = "get".concat(stringbuffer.toString());
		Method amethod[] = obj.getClass().getMethods();
		Method amethod1[] = amethod;
		int i = amethod1.length;
		for (int j = 0; j < i; j++) {
			Method method = amethod1[j];
			if (method.getName().equals(s1))
				return s1;
			if (method.getName().equals(s2))
				return s2;
		}

		return null;
	}

	public static String debugBean(Object obj) {
		return debugBean(obj, new String[0]);
	}

	public static String debugBean(Object obj, String as[]) {
		Arrays.sort(as);
		String s = null;
		try {
			Map map = BeanUtils.describe(obj);
			StringBuffer stringbuffer = new StringBuffer();
			String s1;
			String s2;
			for (Iterator iterator = map.keySet().iterator(); iterator
					.hasNext(); stringbuffer.append((new StringBuilder())
					.append("[").append(s1).append("]=").append(s2)
					.append("; ").toString())) {
				Object obj1 = iterator.next();
				s1 = (String) obj1;
				s2 = (String) map.get(obj1);
				s = s1;
				if (Arrays.binarySearch(as, s1) >= 0)
					s2 = OpPalabras.strCompletoABeta(s2);
			}

			return stringbuffer.toString();
		} catch (Exception exception) {
			return (new StringBuilder())
					.append("error al debuguear el bean, en la propiedad=")
					.append(s).append("\n el error es ")
					.append(exception.getMessage()).toString();
		}
	}

	public static Object getPropiedadObject(Object obj, String as[]) {
		String as1[] = as;
		int i = as1.length;
		for (int j = 0; j < i; j++) {
			String s = as1[j];
			if (tienePropiedad(obj, s))
				return getPropiedadObject(obj, s);
		}

		throw new RuntimeException(
				"ninguna de las propiedades dadas existe en el bean");
	}

	public static boolean tienePropiedad(Object obj, String s) {
		try {
			Map map = BeanUtils.describe(obj);
			Set set = map.keySet();
			return set.contains(s);
		} catch (Exception exception) {
			throw new RuntimeException(
					"error averiguando si el bean tenía una propiedad");
		}
	}

	public static int getPropiedadInt(Object obj, String s) {
		try {
			String s1 = BeanUtils.getProperty(obj, s);
			return Integer.parseInt(s1);
		} catch (Exception exception) {
			throw new RuntimeException((new StringBuilder())
					.append("la propiedad ").append(s)
					.append(" no existe en el bean ").append(obj)
					.append(", o no es del tipo int").toString());
		}
	}

	public static void setPropiedad(Object bean, String name, Object value) {
		try {
			BeanUtils.setProperty(bean, name, value);
		} catch (Exception exception) {
			throw new RuntimeException((new StringBuilder())
					.append("la propiedad ").append(name)
					.append(" no existe en el bean ").append(bean)
					.append(", o no es String").toString());
		}
	}

	public static Object getBeanConEsteID(List list, String s) {
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object obj = iterator.next();
			String s1 = getId(obj);
			if (s == null && s1 == null)
				return obj;
			if (s1.equals(s))
				return obj;
		}

		return null;
	}

	public static String primerCampoNoVacio(Object obj, String as[]) {
		String as1[] = as;
		int i = as1.length;
		for (int j = 0; j < i; j++) {
			String s = as1[j];
			String s1 = getPropiedad(obj, s);
			if (s1 != null)
				return s1;
		}

		throw new RuntimeException(
				"al menos uno de los campos debería ser no nulo");
	}

	public static void copiaPropiedades(Object obj, Object obj1) {
		try {
			BeanUtils.copyProperties(obj, obj1);
			BeanUtils.setProperty(obj, "hashCode", null);
			obj.hashCode();
		} catch (Exception exception) {
			throw new RuntimeException("error al copiar proopiedades");
		}
	}

	public static void pasaDeBetaACompleto(Collection collection, String as[]) {
		try {
			Object obj;
			for (Iterator iterator = collection.iterator(); iterator.hasNext(); pasaDeBetaACompleto(
					obj, as))
				obj = iterator.next();

		} catch (Exception exception) {
			A.error("pasaDeBetaACompleto", exception);
		}
	}

	public static void pasaDeBetaACompleto(Object obj, String as[]) {
		try {
			String as1[] = as;
			int i = as1.length;
			for (int j = 0; j < i; j++) {
				String s = as1[j];
				String s1 = BeanUtils.getProperty(obj, s);
				if (s1 != null) {
					s1 = OpPalabras.strBetaACompleto(s1);
					BeanUtils.setProperty(obj, s, s1);
				}
			}

		} catch (Exception exception) {
			A.error("error en pasaDeBetaACompleto", exception);
		}
	}

	public static void pasaDeCompletoABeta(List list, String as[]) {
		try {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object obj = iterator.next();
				String as1[] = as;
				int i = as1.length;
				int j = 0;
				while (j < i) {
					String s = as1[j];
					String s1 = BeanUtils.getProperty(obj, s);
					s1 = OpPalabras.strCompletoABeta(s1);
					BeanUtils.setProperty(obj, s, s1);
					j++;
				}
			}

		} catch (Exception exception) {
			A.error("pasaDeCompletoABeta ", exception);
		}
	}

	private static Logger A = Logger.getLogger(OpBeans.class.getName());

}
