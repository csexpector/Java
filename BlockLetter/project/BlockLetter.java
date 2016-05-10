//An instance of this class represents a block letter
//Full BlockLetter representations are provided for
//    A, C, E, F, H, I, L, M, O, P, S, T, U, W
//All other characters are represented as "blurbs",
//    solid lines composed of the character

package project;


public class BlockLetter
{
   public static final int MIN_SIZE = LineSegment.MIN_WIDTH;
   
   //Instance Variable: a collection of LineSegment elements
   private LineSegment[] block ;
   
   //Constructor
   // @param letter: the character represented by this BlockLetter
   // default size : MIN_SIZE
   // default print_symbol : the letter
   public BlockLetter(char letter)
   {
      this(letter, MIN_SIZE);
   }
   
   //Constructor
   // @param letter: the character represented by this BlockLetter
   // @param  size : size of this BlockLetter, odd number >= MIN_SIZE
   // default print_symbol : the letter
   public BlockLetter(char letter, int size)
   {
      this(letter, size, letter);
   }
   
   //Constructor
   // @param letter: the character represented by this BlockLetter
   // @param  size : size of this BlockLetter, odd number >= MIN_SIZE
   // @param symbol: the "dot"/character used in the LineSegments
   public BlockLetter(char letter, int size, char symbol)
   {  
       
      // this.block[]= new LineSegment(SegmentType.CENTER_DOT, size ,symbol);
      
       if (size < MIN_SIZE)
         throw new RuntimeException("Minimum Size is " + MIN_SIZE);
         
      if (size % 2 == 0){
         size = size + 1;}
     
      
      
      switch ( letter )
      {   
          case 'A' : build_A(letter);
              break;
          case'C':build_C(letter);
              break;
          case'E':build_E(letter);
              break;
          case'F':build_F(letter);
              break;
          case'H':build_H(letter);
              break;
          case'I':build_I(letter);
              break;
          case'L':build_L(letter);
              break;
          case'M':build_M(letter);
              break;
          case'O':build_O(letter);
              break;
          case'P':build_P(letter);
              break;
          case'S':build_S(letter);
              break;
          case'T': build_T(letter);
              break;
          case'U':build_U(letter);
              break;
          case'W':build_W(letter);
              break;       
         default  : blurb(letter);
      }
   }
      //Accessor - returns a selected LineSegment of this BlockLetter

   // @param index: 0 .. this.size()-1, selects a LineSegment
   public LineSegment getSegment(int index)
  { // block[0]=  new LineSegment(SegmentType.CENTER_DOT, size(), 'R');
//   block[1]=  new LineSegment(SegmentType.DOUBLE, size(), 'R');
//   block[2]=  new LineSegment(SegmentType.CENTER_DOT, size(), 'R');
//   block[3]=  new LineSegment(SegmentType.LEFT_DOT, size(), 'R');
//   block[4]=  new LineSegment(SegmentType.RIGHT_DOT, size(), 'R');
//   block[5]=  new LineSegment(SegmentType.TRIPLE, size(), 'R');
      return this.block[index];
   }
   
   //Returns the number of LineSegments in this BlockLetter
   public int size()
   {
       //return this.block.length;
       return this.MIN_SIZE;
   }
   
   //Override - returns a printable image of this BlockLetter
   public String toString()
   {
       return "";
   }
   
   public LineSegment getBlockSegment(int index,char Symbol){
   this.block[0]=  new LineSegment(SegmentType.SOLID, size(), Symbol);
  this. block[1]=  new LineSegment(SegmentType.DOUBLE, size(), Symbol);
   this.block[2]=  new LineSegment(SegmentType.CENTER_DOT, size(), Symbol);
   this.block[3]=  new LineSegment(SegmentType.LEFT_DOT, size(), Symbol);
   this.block[4]=  new LineSegment(SegmentType.RIGHT_DOT, size(), Symbol);
   this.block[5]=  new LineSegment(SegmentType.TRIPLE, size(), Symbol);
       
       return block[index];

   }
      
   private void blurb(char symbol)
   {   for(int index = 0 ; index <= size(); index++)
        //new LineSegment(SegmentType.SOLID, size(), symbol);
       System.out.println(getBlockSegment(0,symbol));
   
   }
   
   private void build_A(char symbol)
   {  
       for( int row = 0 ; row < size(); row++){
           if ( row  == 0 || row == ((size()-1) / 2))
        System.out.println(new LineSegment(SegmentType.SOLID,size(), symbol));
           else{
               System.out.println(new LineSegment(SegmentType.DOUBLE, size(), 
                       symbol) );
            }
       }
   }
   private void build_C(char symbol){
       for( int row = 0 ; row < size(); row++){
           if ( row  == 0 || row == size()-1)
        System.out.println(new LineSegment(SegmentType.SOLID,size(), symbol));
           else{
               System.out.println(new LineSegment(SegmentType.LEFT_DOT, size(),
                       symbol) );
            }
       }
   }
   private void build_E(char symbol){
       for( int row = 0 ; row < size(); row++){
           if ( row  == 0 || row == ((size()-1) / 2) || row == (size()-1) )
        System.out.println(new LineSegment(SegmentType.SOLID,size(), symbol));
           else{
               System.out.println(new LineSegment(SegmentType.LEFT_DOT, size(), 
                       symbol) );
            }
       }
       
   }
   private void build_F(char symbol){
       for( int row = 0 ; row < size(); row++){
           if ( row  == 0 || row == ((size()-1) / 2))
        System.out.println(new LineSegment(SegmentType.SOLID,size(), symbol));
           else{
               System.out.println(new LineSegment(SegmentType.LEFT_DOT, size(),
                       symbol) );
            }
       }
       
   }
   private void build_H(char symbol){
       for( int row = 0 ; row < size(); row++){
           if ( row == ((size()-1) / 2))
        System.out.println(new LineSegment(SegmentType.SOLID,size(), symbol));
           else{
               System.out.println(new LineSegment(SegmentType.DOUBLE, size(), 
                       symbol) );
            }
       }
       
   }
   private void build_I(char symbol){
       for( int row = 0 ; row < size(); row++){
           if ( row  == 0 || row == ((size()-1) ))
        System.out.println(new LineSegment(SegmentType.SOLID,size(), symbol));
           else{
               System.out.println(new LineSegment(SegmentType.CENTER_DOT, size()
                       , symbol) );
            }
       }
       
   }
   private void build_L(char symbol){
       for( int row = 0 ; row < size(); row++){
           if ( row  == size()-1 )
        System.out.println(new LineSegment(SegmentType.SOLID,size(), symbol));
           else{
               System.out.println(new LineSegment(SegmentType.LEFT_DOT, size(),
                       symbol) );
            }
       }
       
   }
   private void build_M(char symbol){
       for( int row = 0 ; row < size(); row++){
          
          if(row == 0) 
            System.out.println(new LineSegment(SegmentType.SOLID,size(), symbol));

          else if (  row <= ((size()) / 2))
        System.out.println(new LineSegment(SegmentType.TRIPLE,size(), symbol));
           
           else{
               System.out.println(new LineSegment(SegmentType.DOUBLE, size(), symbol) );
            }
       }
   }
  
   private void build_O(char symbol){
       
       for( int row = 0 ; row < size(); row++){
           if ( row  == 0 || row == ((size()-1)))
        System.out.println(new LineSegment(SegmentType.SOLID,size(), symbol));
           else{
               System.out.println(new LineSegment(SegmentType.DOUBLE, size(), 
                       symbol) );}
       }
   }
       
   
   
private void build_P(char symbol){
    
       for( int row = 0 ; row < size(); row++){
          if ( row  == 0 || row ==(size()-1) / 2){
        System.out.println(new LineSegment(SegmentType.SOLID,size(), symbol));}
        
          else if(row <((size()-1) / 2)){
               System.out.println(new LineSegment(SegmentType.DOUBLE, size(), 
                       symbol) );}
          else{
              System.out.println(new LineSegment(SegmentType.LEFT_DOT, size(),
                       symbol) );}
          }
       
       }
       
   
   private void build_S(char symbol){
        for( int row = 0 ; row < size(); row++){
           if ( row  == 0 || row == ((size()-1)/2) || row == (size()-1))
        System.out.println(new LineSegment(SegmentType.SOLID,size(), symbol));
           
           else if(row > 0 && row <((size()-1)/2) ) 
            System.out.println(new LineSegment(SegmentType.LEFT_DOT, size(), 
                       symbol) );
               
            if (row > ((size()-1)/2)&& row < (size()-1)) System.out.println(new LineSegment(SegmentType.RIGHT_DOT, size(), 
                       symbol) );
            }
      }
   
   
   private void build_T(char symbol){
        for( int row = 0 ; row < size(); row++){
           if ( row  == 0 )
        System.out.println(new LineSegment(SegmentType.SOLID,size(), symbol));
           else{
               System.out.println(new LineSegment(SegmentType.CENTER_DOT, size(), 
                       symbol) );}
            
    }
   }
       
   
   
   private void build_U(char symbol)
   { for( int row = 0 ; row < size(); row++){
           if ( row <(size()-1))
        System.out.println(new LineSegment(SegmentType.DOUBLE,size(), symbol));
           else{
               System.out.println(new LineSegment(SegmentType.SOLID, size(), 
                       symbol) );
            } 
   }
   }
   
   private void build_W(char symbol){
       for( int row = 0 ; row < size(); row++){
          
          if(row == (size()-1)) 
            System.out.println(new LineSegment(SegmentType.SOLID,size(), symbol));

          else if (  row >= ((size()) / 2))
        System.out.println(new LineSegment(SegmentType.TRIPLE,size(), symbol));
           
           else{
               System.out.println(new LineSegment(SegmentType.DOUBLE, size(), symbol) );
            }
       }
       
   }
   
   //*************************************************************
   // Throw-away tester
   public static void main(String[] args)
   {
      String chars = "ACEFHILMOPSTUWX";
      for (int i = 0; i < chars.length(); i++)
         System.out.println( new BlockLetter(chars.charAt(i)) +"\n"
         );
   }
}
   
