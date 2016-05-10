package map1;

import static org.junit.Assert.*;
import org.junit.Test;

public class MyMapTests
{
    /**
     * Tests on empty map
     */
    @Test
    public void test1 ()
    {
        MyMap m = new MyMap();
        assertEquals(0, m.size());
        assertNull(m.get("a"));
    }
    
    /**
     * Simple tests for put, size, get
     */
    @Test
    public void test2 ()
    {
        MyMap m = new MyMap();
        m.put("a", "1");
        m.put("b", "2");
        m.put("a", "3");
        assertEquals(2, m.size());
        assertEquals("3", m.get("a"));
        assertEquals("2", m.get("b"));
        assertNull(m.get("c"));
    }
    
    /**
     * Simple tests for put, remove, size, get
     */
    @Test
    public void test3 ()
    {
        MyMap m = new MyMap();
        m.put("a", "7");
        m.put("b", "14");
        m.put("a", "21");
        m.put("c", "28");
        assertEquals(3, m.size());
        assertEquals("21", m.get("a"));
        assertEquals("14", m.get("b"));
        assertEquals("28", m.get("c"));
        
        m.remove("a");
        assertEquals(2, m.size());
        assertNull(m.get("a"));
        assertEquals("14", m.get("b"));
        assertEquals("28", m.get("c"));
    }
    
    /**
     * More comprehensive tests for add, remove, size, get
     */
    @Test
    public void test4 ()
    {
        MyMap m = new MyMap();
        int SIZE = 10000;
        
        // Add SIZE mappings
        for (int i = 0; i < SIZE; i++)
        {
            m.put(i+"", (i*17)+"");
        }
        
        // Add some of the keys again with new values.
        for (int i = 0; i < SIZE; i+=2)
        {
            m.put(i+"", (i*18)+"");
        }
        
        // Remove every third element.
        for (int i = 0; i < SIZE; i += 3)
        {
            m.remove(i+"");
        }
        
        // Remove some of them again.  This should make no difference.
        for (int i = 0; i < SIZE; i += 6)
        {
            m.remove(i+"");
        }
        
        // Assert that the size is as expected
        assertEquals(SIZE - (int) Math.ceil(SIZE / 3.0), m.size());
        
        // Assert that membership is as expected
        for (int i = 0; i < SIZE; i++)
        {
            if (i%3 == 0)
            {
                assertNull(m.get(i+""));
            }
            else if (i%2 == 0)
            {
                assertEquals((i*18)+"", m.get(i+""));
            }
            else
            {
            	assertEquals((i*17)+"", m.get(i+""));
            }
        }
    }

}
