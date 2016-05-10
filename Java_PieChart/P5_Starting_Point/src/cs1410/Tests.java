package cs1410;

import static org.junit.Assert.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;
import cs1410.GraphingMethods;

public class Tests
{
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> categories = new ArrayList<>();
    ArrayList<Double> values = new ArrayList<>();
    int operation = 0;
    ArrayList<Double> summaries = new ArrayList<Double>();
    ArrayList<Color> colors = new ArrayList<Color>();
    BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = image.createGraphics();
    
    private void init()
    {
        names = new ArrayList<>();
        categories = new ArrayList<>();
        values = new ArrayList<>();
        operation = 0;
        
        summaries = new ArrayList<Double>();
        colors = new ArrayList<Color>();
        image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        g = image.createGraphics();
    }
    
    public Tests()
    {
        
    }
    @Test
    public void test ()
    {
        // This is a sample test that shows two ways to make assertions about ArrayLists.
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Double> values = new ArrayList<>();
        GraphingMethods.readTable(new Scanner("A\t45\nB\t50\n"), names, values);
        
        ArrayList<String> namesGoal = new ArrayList<>();
        namesGoal.add("A");
        namesGoal.add("B");
        assertEquals(namesGoal, names);
        
        assertEquals(2, values.size());
        assertEquals(45.0, values.get(0), 1e-6);
        assertEquals(50.0, values.get(1), 1e-6);
    }
    @Test(expected=IllegalArgumentException.class)
    public void testSummerizeDataEqualSize()
    {
        init();
        // Test name and values has different size
        names.add("Utah"); names.add("California");
        values.add(new Double(40));
        categories.add("Utah");
        GraphingMethods.summarizeData(names, values, categories, operation);  
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testSummerizeDataNonPositive()
    {
        init();
        
        names.add("Utah"); names.add("California");
        values.add(new Double(40));
        // Test values contain non positive number
        values.add(new Double(-20));
        categories.add("Utah"); 
        
        GraphingMethods.summarizeData(names, values, categories, operation); 
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testSummerizeCategoriesSizeIsZero()
    {
        init();
        
        names.add("Utah"); 
        values.add(new Double(40));
        // Test categories size is 0                
        GraphingMethods.summarizeData(names, values, categories, operation); 
    }
    @Test(expected=IllegalArgumentException.class)
    public void testSummerizeCategoriesDuplicate()
    {
        init();
        
        names.add("Utah"); names.add("Utah"); 
        values.add(new Double(40)); values.add(new Double(40));
        
        // Test categories dublicate
        categories.add("Utah");categories.add("Utah");
               
        GraphingMethods.summarizeData(names, values, categories, operation); 
    }
    @Test(expected=IllegalArgumentException.class)
    public void testSummerizeUnknownOperation()
    {
        init();
        
        names.add("Utah"); names.add("Utah"); 
        values.add(new Double(40)); values.add(new Double(40));
        categories.add("Utah");
        // Test unknown operation
        operation = 100;   
        GraphingMethods.summarizeData(names, values, categories, operation); 
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testDrawGraphSummariesSizeIsZero()
    {
        init();
        categories.add("Utah");
        colors.add(new Color(200));
        GraphingMethods.drawGraph(g, categories, summaries, colors, true); 
    }
    @Test(expected=IllegalArgumentException.class)
    public void testDrawGraphCategoriesSizeIsZero()
    {
        init();
        summaries.add(new Double(8));
        colors.add(new Color(100));
        GraphingMethods.drawGraph(g, categories, summaries, colors, true); 
    }
    @Test(expected=IllegalArgumentException.class)
    public void testDrawGraphColorSizeIsZero()
    {
        init();
        summaries.add(new Double(8));
        categories.add("Utah");
        GraphingMethods.drawGraph(g, categories, summaries, colors, true); 
    }
    @Test(expected=IllegalArgumentException.class)
    public void testDrawGraphSizeNotMatch1()
    {
        init();
        summaries.add(new Double(8));
        categories.add("Utah");categories.add("Cali");
        colors.add(new Color(100));
        GraphingMethods.drawGraph(g, categories, summaries, colors, true); 
    }
    @Test(expected=IllegalArgumentException.class)
    public void testDrawGraphSizeNotMatch2()
    {
        init();
        summaries.add(new Double(8));
        categories.add("Utah");
        colors.add(new Color(100));colors.add(new Color(200));
        GraphingMethods.drawGraph(g, categories, summaries, colors, true); 
    }
    @Test(expected=IllegalArgumentException.class)
    public void testDrawGraphSummariesNotZero()
    {
        init();
        summaries.add(new Double(-8));
        categories.add("Utah");
        colors.add(new Color(100));
        GraphingMethods.drawGraph(g, categories, summaries, colors, true); 
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testReadTableInvalidNumber()
    {
        init();
        GraphingMethods.readTable(new Scanner("A\t45x\nB\t50\n"), names, values);
    }
    @Test(expected=IllegalArgumentException.class)
    public void testReadTableNotCorrectFormat()
    {
        init();
        GraphingMethods.readTable(new Scanner("   A\t45x\nB\t50\n"), names, values);
    }
}