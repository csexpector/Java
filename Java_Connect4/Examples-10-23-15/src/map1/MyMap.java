package map1;

import java.util.ArrayList;

/**
 * A MyMap object maps strings to strings.
 * 
 * @author Joe Zachary
 */
public class MyMap
{
    /**
     * Illustrates how MyMap objects work.
     */
    public static void main (String[] args)
    {
        MyMap m = new MyMap();
        m.put("Utah", "4");
        System.out.println(m.size());
        m.put("Ohio State", "1");
        m.put("Utah", "3");
        System.out.println(m.size());
        System.out.println(m.get("Ohio State"));
        System.out.println(m.get("Utah"));
        System.out.println(m.get("BYU"));
    }
    
    /**
     * keys and values are parallel arrays.
     * The keys list contains the keys of this MyMap,
     * without duplicates.
     */
    private ArrayList<String> keys;
    
    /**
     * The values list is the same length as keys.
     * If a key is stored at keys[i], the corresponding
     * value is stored at values[i].
     */
    private ArrayList<String> values;

    /**
     * Creates an empty MyMap.
     */
    public MyMap ()
    {
        keys = new ArrayList<>();
        values = new ArrayList<>();
    }

    /**
     * If key already maps to something, replaces its mapping with key. 
     * Otherwise, adds a mapping from key to val.
     */
    public void put (String key, String val)
    {
        int index = keys.indexOf(key);
        if (index >= 0)
        {
            values.set(index, val);
        }
        else
        {
            keys.add(key);
            values.add(val);
        }
    }

    /**
     * Removes the mapping involving key (if one exists) or does nothing (otherwise).
     */
    public void remove (String key)
    {
        int index = keys.indexOf(key);
        if (index >= 0)
        {
            keys.remove(index);
            values.remove(index);
        }
    }

    /**
     * Returns the value to which key maps (if one exists) or null (otherwise).
     */
    public String get (String key)
    {
        int index = keys.indexOf(key);
        if (index < 0)
        {
            return null;
        }
        else
        {
            return values.get(index);
        }
    }

    /**
     * Returns the number of elements in this map.
     */
    public int size ()
    {
    	return keys.size();
    }
}
