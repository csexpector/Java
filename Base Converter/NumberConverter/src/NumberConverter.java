


public class NumberConverter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // 19.2_10 = 23.{1463}_8
        short[] Int = {1,9};
        short[] NoRep = {2};
        short[] Rep = {};
        Number N = new Number(Int,NoRep,Rep,(short)10);
        Number M = N.convert((short)8);
        System.out.println(N + " = " + M);
        
        // 25.1875_10 = (11001.0011)_2
        short[] Int2 = {2,5};
        short[] NoRep2 = {1,8,7,5};
        short[] Rep2 = {};
        Number N2 = new Number(Int2,NoRep2,Rep2,(short)10);
        Number M2 = N2.convert((short)2);
        System.out.println(N2 + " = " + M2);
    }
    
}
