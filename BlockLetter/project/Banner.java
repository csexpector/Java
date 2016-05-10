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
import java.util.*;
import javax.swing.*;
public class Banner
{
   //Instance Variable - A collection of BlockLetter elements
   private ArrayList<BlockLetter> letters;
   
   //Constructor 
   // @param words : The content of this Banner
   // @param height: Height of the letters in this Banner
   // default is to construct each letter using the letter
   //    itself as the print-symbol in each BlockLetter
   public Banner(String words, int height)
   {
   }
   
   //Constructor 
   // @param words : The content of this Banner
   // @param height: Height of the letters in this Banner
   // @param symbol: Print-symbol used in all BlockLetter   s
   public Banner(String words, int height, char symbol)
   {
   }
   
   //Override - return a "horizontal" image of this Banner. Example:
   // FFFFFFF  IIIIIII  U     U           MMMMMMM  AAAAAAA  RRRRRRR  SSSSSSS
   // F           I     U     U           M  M  M  A     A  RRRRRRR  S      
   // F           I     U     U           M  M  M  A     A  RRRRRRR  S      
   // FFFFFFF     I     U     U           M  M  M  AAAAAAA  RRRRRRR  SSSSSSS
   // F           I     U     U           M     M  A     A  RRRRRRR        S
   // F           I     U     U           M     M  A     A  RRRRRRR        S
   // F        IIIIIII  UUUUUUU           M     M  A     A  RRRRRRR  SSSSSSS
   public String toString()
   {
      String image = "";
      return image;
   }
}
