
public class Test {
    
    public static void main(String ... a){
        String uno = "número";
        String dos = new String("número");
        String tres = "número";
        
        System.out.println(uno.equals(dos));
        System.out.println(uno == dos);
        System.out.println(uno == tres);
        
    }

}
