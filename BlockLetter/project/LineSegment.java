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
public class LineSegment {
     public final static int MIN_WIDTH = 5;
   
   //Instance Variable
   private final String segment;
   
   //Constructor - create a LineSegment of some given SegmentType
   // @param type:   the type of this LineSegment
   // @param width:  the width of this LineSegment, an odd integer
   // @param dot:    the character used in constructing this LineSegment
   // RuntimeExcption thrown if the requested width is even or < MIN_WIDTH
   public LineSegment(SegmentType type, int length, char dot)
   {
      if (length < MIN_WIDTH || length % 2 == 0)
         throw new RuntimeException("Width must be odd, >= " + MIN_WIDTH);
         
      switch ( type )
      {
         case SOLID     :  segment = solid(length, dot); break;
         case TRIPLE    :  segment = triple(length, dot); break;
         case DOUBLE    :  segment = doubles(length, dot); break;
         case LEFT_DOT  :  segment = leftDot(length, dot); break;
         case CENTER_DOT:  segment = centerDot(length, dot); break;
         case RIGHT_DOT :  segment = rightDot(length, dot); break;
         default        :  segment = "";
      }
   }
   
   //Return the length (width) of this LineSegment
   public int length()
   {
      return this.segment.length();
   }
   
   //Override - returns a printable image of this LineSegment
   public String toString()
   {
      return this.segment;
   }
   
   //*************************CONSTRUCTOR HELPERS***********************
   
   //Return a SegmentType.SOLID LineSegment
   // @param width: The width/length of this SOLID LineSegment
   // @param dot: The symbol used in constructing this LineSegment
   private String solid(int width, char dot)
   {
      String line = "";
      for (int k = 1; k <= width; k++)
         line = line + dot;
      return line;
   }
   
   //Return a SegmentType.TRIPLE LineSegment
   // @param width: The width/length of this LineSegment
   // @param dot: The symbol used in constructing this LineSegment
   private String triple(int width, char dot)
   {
      return dot + solid(width/2 - 1,' ' ) + dot +
                   solid(width/2 - 1,' ' ) + dot ;
   }

   //Return a SegmentType.DOUBLE LineSegment
   // @param width: The width/length of this LineSegment
   // @param dot: The symbol used in constructing this LineSegment
   private String doubles(int width, char dot)
   {
      
      return dot + solid(width - 2,' ' ) + dot;
   }

   //Return a SegmentType.LEFT_DOT LineSegment
   // @param width: The width/length of this LineSegment
   // @param dot: The symbol used in constructing this LineSegment
   private String leftDot(int width, char dot)
   {
      return dot+ solid(width-1,' ');
   }

   //Return a SegmentType.CENTER_DOT LineSegment
   // @param width: The width/length of this LineSegment
   // @param dot: The symbol used in constructing this LineSegment
   private String centerDot(int width, char dot)
   {
      return solid(width/2,' ' ) + dot +
             solid(width/2,' ');
   }
   
   //Return a SegmentType.RIGHT_DOT LineSegment
   // @param width: The width/length of this LineSegment
   // @param dot: The symbol used in constructing this LineSegment
   private String rightDot(int width, char dot)
   {
      return solid(width-1,' ')+ dot;
   }
    
}
