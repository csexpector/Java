public class Hwbio3 {
    public static void main(String[] args)
    {
        // Test question 3
        String blast = ">ref|NM_001081660.1| Rattus norvegicus crystallin, gamma B (mapped) (Crygb), mRNA";
        // extract accession number then store to Acc_num
        String Acc_num = extractAccessionNumber(blast);
        System.out.println("Accession number: " + Acc_num);
    }
    // question 3
    public static String extractAccessionNumber(String blastSeq)
    {
        String[] list = blastSeq.split("\\|");
        return list[1];
    }
}
