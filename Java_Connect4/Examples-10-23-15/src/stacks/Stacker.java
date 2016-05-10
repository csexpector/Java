package stacks;

import rational.Rat;

public class Stacker
{
    public static void main (String[] args)
    {
        int blocks = 42;
        System.out.println(extension1(blocks));
        System.out.println(extension2(blocks));
        System.out.println(extension3(blocks));
        System.out.println(extension3(blocks).toDouble());
    }
    
    public static double extension1 (int blocks)
    {
        double extension = 0;
        for (int block = 1; block <= blocks; block++)
        {
            extension += 1.0 / (block*2);
        }
        return extension;
    }
    
    public static double extension2 (int blocks)
    {
        double extension = 0;
        for (int block = blocks; block >= 1; block--)
        {
            extension += 1.0 / (block*2);
        }
        return extension;
    }
    
    public static Rat extension3 (int blocks)
    {
        Rat extension = new Rat();
        for (int block = 1; block <= blocks; block++)
        {
            extension = extension.add(new Rat(1, block*2));
        }
        return extension;
    }
}
