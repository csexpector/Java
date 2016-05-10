
public class Hwbio2 {
    public static void main(String[] args)
    {
        // Test question 2
        String dna = concat("ATGC", "CGTA");
        System.out.println("Concat 2 DNAs: " + dna);
        dna = concat("AAAA", "TTTT", "GGGG", "CCCC");
        System.out.println("Concat 4 DNAs: " + dna);
    }
    // question 2
    public static String concat(String... dnas)
    {
        String res = "";
        for (String dna : dnas)
        {
            res += dna;
        }
        return res;
    }
}
