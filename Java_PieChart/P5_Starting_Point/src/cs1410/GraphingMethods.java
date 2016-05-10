package cs1410;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class GraphingMethods
{
    /**
     * Constant used to request a max operation
     */
    public final static int MAX = 0;

    /**
     * Constant used to request a min operation
     */
    public final static int MIN = 1;

    /**
     * Constant used to request a sum operation
     */
    public final static int SUM = 2;

    /**
     * Constant used to request an average operation
     */
    public final static int AVG = 3;

    /**
     * If names and values have different lengths, throws an IllegalArgumentException.
     * 
     * If values contains any non-positive numbers, throws an IllegalAgumentException.
     * 
     * If categories is of length zero, throws an IllegalArgumentException.
     * 
     * If categories contains any duplicates, throws an IllegalArgumentException.
     * 
     * If operation is anything other than SUM, AVG, MAX, or MIN, throws an IllegalArgumentException.
     * 
     * Put side-by-side, the lists "names" and "values" represent a table of values, where each row contains a name and
     * a value. For example, here is the table of values that might give the number of people named Zebediah in
     * different parts of four states.
     * 
     * <pre>
     *  Utah          5
     *  Nevada        6
     *  California   12
     *  Oregon        8
     *  Utah          9
     *  California   10
     *  Nevada        4
     *  Nevada        4
     *  Oregon       17
     *  California    6
     * </pre>
     * 
     * We can make a table of summaries by pairing each string c from the list "categories" with either the sum,
     * average, maximum, or minimum of the numbers paired with c in the table above. For example, if "categories" is
     * 
     * <pre>
     *  Utah
     *  Nevada
     *  California
     * </pre>
     * 
     * then, assuming we are summing, the table of summaries would be
     *
     * <pre>
     *  Utah       14 
     *  Nevada     14 
     *  California 28
     * </pre>
     * 
     * The method should calculate and return, as an ArrayList<Double>, the second column of the table of summaries. It
     * should use the value of the "operation" parameter to determine whether the values are combined by summing,
     * averaging, max-ing, or min-ing. (If, for any category, there are no values to sum, average, min, or max, the
     * method should throw an IllegalArgumentException.)
     * 
     * The order of the numbers in the returned ArrayList<Double> should correspond to the order of the categories in
     * the "categories" list.
     */
    public static ArrayList<Double> summarizeData (ArrayList<String> names, ArrayList<Double> values,
            ArrayList<String> categories, int operation)
    {
        // If names and values have different lengths, throws an IllegalArgumentException.
        if (names.size() != values.size()) throw new IllegalArgumentException();
        // If values contains any non-positive numbers, throws an IllegalArgumentException.
        for (int i = 0; i < values.size(); i++)
        {
            if (values.get(i) <= 0) throw new IllegalArgumentException();
        }
        // If categories is of length zero, throws an IllegalArgumentException.
        if (categories.size() == 0) throw new IllegalArgumentException();
        // If categories contains any duplicates, throws an IllegalArgumentException.
        HashSet<String> set = new HashSet<String>();
        
        for (String category : categories)
        {
            if(!set.add(category)) throw new IllegalArgumentException();
        }
        // If operation is anything other than SUM, AVG, MAX, or MIN, throws an IllegalArgumentException.
        if (operation != SUM && operation != AVG && operation != MAX && operation != MIN) throw new IllegalArgumentException();
        // This is sample code that always returns the same summaries.
        ArrayList<Double> summaries = new ArrayList<Double>();
        Operator op = new Operator();
        switch (operation)
        {
            case SUM:
                op = new SumOperator();
                break;
            case MAX:
                op = new MaxOperator();
                break;
            case MIN:
                op = new MinOperator();
                break;
            case AVG:
                op = new SumOperator();
                break;
        }
        for (int i = 0; i < categories.size(); i++)
        {
            // for each category, loop through names to find matching name with category
            // then do the operator
            boolean f = true;
            Double firstValue = Double.valueOf(0), secondValue = Double.valueOf(0);
            int count = 0; // <- for calculating average
            for (int j = 0; j < names.size(); j++)
            {
                if (categories.get(i).equals(names.get(j)))
                {
                    count++;
                    if (f)
                    {
                        f = false;
                        firstValue = values.get(j);                        
                    }
                    else
                    {
                        secondValue = values.get(j);
                        firstValue = op.calculate(firstValue, secondValue);
                    }
                }
            }
            if (operation == AVG) firstValue = firstValue / count;
            summaries.add(firstValue);
        }
        return summaries;
    }

    /**
     * If categories, summaries, or colors is empty, throws an IllegalArgumentException.
     * 
     * If categories, summaries, and colors don't have the same number of elements, throws an IllegalArgumentException.
     * 
     * If any of the numbers in summaries is non-positive, throws an IllegalArgumentException.
     * 
     * Otherwise, views the three lists as a table with a String, a double, and a Color in each column and displays the
     * data with either a pie chart (if usePieChart is true) or a bar graph (otherwise). Let SUM be the sum of all the
     * entries in summaries. The area of slice i (of a pie chart) and the length of bar i (in a bar graph) should be
     * proportional to categories[i]/SUM. The slice/bar should be labeled with categories[i] and summaries[i], and it
     * should be colored with colors[i].
     * 
     * For example, suppose we are given this data:
     * 
     * <pre>
     *  Utah       14    Color.RED
     *  Nevada     14    Color.BLUE
     *  California 28    Color.GREEN
     * </pre>
     * 
     * In a pie chart Utah and Nevada should each have a quarter of the pie and California should have half. In a bar
     * graph, California's line should be twice as long as Utah's and Nevada's.
     * 
     * The method should display the pie chart or bar graph by drawing on the g parameter. The example code below draws
     * both a pie chart and a bar graph for the situation described above.
     */
    public static void drawGraph (Graphics g, ArrayList<String> categories, ArrayList<Double> summaries,
            ArrayList<Color> colors, boolean usePieChart)
    {
        // If categories, summaries, or colors is empty, throws an IllegalArgumentException.
        if (categories.size() == 0 || summaries.size() == 0 || colors.size() == 0) throw new IllegalArgumentException();
        // If categories, summaries, and colors don't have the same number of elements, throws an IllegalArgumentException.
        if (categories.size() != summaries.size() || categories.size() != colors.size())
            throw new IllegalArgumentException();
        // If any of the numbers in summaries is non-positive, throws an IllegalArgumentException.
        for (int i = 0; i < summaries.size(); i++)
        {
            if (summaries.get(i) <= 0) throw new IllegalArgumentException();
        }
        Double sum = Double.valueOf(0);
        for (int i = 0; i < summaries.size(); i++)
        {
            sum += summaries.get(i);
        }
        
        if (usePieChart)
        {    
            int x = 10, y = 10, width = 300, startAngle = 0, rectX = 330, rectY = 10, rectW = 10, countPie = 0;
            int legendX = 350, legendY = 20;
            for (int i = 0; i < categories.size(); i++)
            {
                
                g.setColor(colors.get(i));
                Double arcAngle = 360 * summaries.get(i) / sum;
                // round up the last angle to avoid tiny empty pie
                if (i == categories.size() - 1) arcAngle = Double.valueOf(360 - startAngle);
                g.fillArc(x, y, width, width, startAngle, arcAngle.intValue());
                g.fillRect(rectX, rectY + countPie * 15, rectW, rectW);
                g.setColor(Color.black);
                g.drawString(categories.get(i) + " " + String.format("%.2f",summaries.get(i)), legendX, legendY + countPie * 15);
                
                countPie++;
                startAngle = startAngle + arcAngle.intValue();
            }
        }
        else
        {
            int endX = 310, startY = 10, countPie = 0, height = 20;
            int legendX = 350, legendY = 25;
            for (int i = 0; i < categories.size(); i++)
            {
                g.setColor(colors.get(i));
                Double width = 300 * summaries.get(i) / sum;
                g.fillRect(endX - width.intValue() , startY + countPie * 30, width.intValue(), height);
                g.setColor(Color.black);
                g.drawString(categories.get(i) + " " + String.format("%.2f",summaries.get(i)), legendX, legendY + countPie * 30);
                
                countPie++;
            }
        }
    }

    /**
     * The dataSource should consist of zero or more lines. Each line should consist of some text, followed by a tab
     * character, followed by a double literal, followed by a newline.
     * 
     * If any lines are encountered that don't meet this criteria, the method throws an IllegalArgumentException whose
     * message explains what is wrong.
     * 
     * Otherwise, for each line, the text before the tab is added to names, and the parsed double literal is added to
     * values.
     */
    public static void readTable (Scanner dataSource, ArrayList<String> names, ArrayList<Double> values)
    {
        while (dataSource.hasNextLine())
        {
            String s = dataSource.nextLine().trim();
            String name = "", num = "";
            Double value = Double.valueOf(0);
            // cannot start with a space
            if (s.charAt(0) == ' ') throw new IllegalArgumentException("Reading error: Cannot start with a space!");
            boolean isString = true;
            for (int i = 0; i < s.length(); i++)
            {
                char c = s.charAt(i);
                if (c == '\t')          isString = false;
                else if (isString)      name += c;
                else                    num  += c;
            }
            try {
                value = Double.valueOf(num);
            } catch (Exception e)
            {
                throw new IllegalArgumentException("Readding error: value is not a double!");
            }
            names.add(name);
            values.add(value);
        }
    }
}
