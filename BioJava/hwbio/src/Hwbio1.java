public class Hwbio1 {
    public static void main(String[] args)
    {
        // Test question 1
        String dna = "ACGGGAGGACGGGAAAATTTACTAGC";
        String rev_com = reverseComplement(dna);
        System.out.println("Reverse Complement: " + rev_com);
    }
    public static String reverseComplement(String dna)
    {
        String res = "";
        for (int i = dna.length() - 1; i >= 0; i--)
            res += complement(dna.charAt(i));
        return res;
    }
    public static Character complement(Character c)
    {
        switch (c)
        {
            case 'A': return 'T';
            case 'T': return 'A';
            case 'G': return 'C';
            case 'C': return 'G'; 
            default: return 'X';
        }
    }
}
