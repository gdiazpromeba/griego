package kalos.flexion.conjugacion;



import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CacheFlexionParticipios
{

 public CacheFlexionParticipios()
 {
     B = new HashMap();
     C = new LinkedList();
 }

 public Map getMapaOcurrencias(String s)
 {
     Map map = (Map)B.get(s);
     return map;
 }

 public void setMapaOcurrencias(String s, Map map)
 {
     C.offer(s);
     B.put(s, map);
     if(C.size() > 10)
     {
         String s1 = (String)C.poll();
         B.remove(s1);
     }
 }

 private final int A = 10;
 private Map B;
 private Queue C;
}

