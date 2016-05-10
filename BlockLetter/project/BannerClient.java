/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author laure_000
 */
public class BannerClient
{
   public static void main(String[] args)
   {
      System.out.println( new Banner("MIAMI RULES", 7) );
      System.out.println();
      System.out.println( new Banner("FIU PANTHERS", 11, 'x') );
   }
}