/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hwbio;

public class Hwbio123 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        
        // Test question 3
        String blast = ">ref|NM_001081660.1| Rattus norvegicus crystallin, gamma B (mapped) (Crygb), mRNA";
        String Acc_num = extractAccessionNumber(blast);
        System.out.println("Accession number: " + Acc_num);
        
        
    }
    // question 1
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
    
    // question 3
    public static String extractAccessionNumber(String blastSeq)
    {
        String[] list = blastSeq.split("|");
        return list[1];
    }
}
