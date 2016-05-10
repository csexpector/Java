
/**
* Description:  a class implementing maps from Strings to
*   integers.
*
* The map is from "keys" to "values".
*
* The set of keys is distinct and so form a set; values may be
*   duplicated.  So, the map can be viewed as a set of (key,value)
*   pairs.  For example, a map from name to age might contain
*   the following:
*
*      ("Larry", 29), ("Mary", 62), ("Alice", 29), ("Phil", 91) 
*
*   (note that Larry and Alice are the same age.)
*/
class MyMap {

  /* YOUR DATA MEMBERS HERE! */
  private  final int INITIALSIZE  = 100;    // initial size for map
  private  final int INCREASESIZE = 50;     // when map is full, increase map size
  private  final int ARBITRARY    = -13579; // when a key is not found, return this value
  private  String [] keys;
  private  Integer[] values;
  private  int       length;                // current length of map
  private  int       capacity;              // current capacity of map

  /**
  * Constructor:  Initializes an empty map
  */
  public MyMap(){
    keys = new String[INITIALSIZE];
    values = new Integer[INITIALSIZE];
    capacity = INITIALSIZE;
    length = 0; // still empty

  }

  /**
  * If the given key is a member of the map, its associated
  *    value is updated to the given value (parameter val) and
  *    the size of the map is unchanged.
  * If the given key is NOT a member of the map, a new entry is
  *    created for the given key and value.
  */
  public void set(String key, int val){
    int index = getIndex(key);
    if (index != -1) values[index] = val;
    else
    {
      // check if full?
      // map is full when the current length equal to current capacity
      if () // TODO1: write command to check if the map is full
      {
        reAllocate();
      }
      else
      {
        // add new entry
        keys[length] = key;
        values[length] = val;
        length++;
      }
    }

  }

  /**
  * returns the value associated with the given key
  *   if there is indeed an entry for the key.
  *  If there is no such entry, an arbitrary value is returned
  *   (up to the class implementor).  The caller is responsible
  *   for making sure an entry for the given key exists before
  *   calling get().
  */
  public Integer get(String key){
    
    int index = getIndex(key);
    
    if () // TODO2: write command to check if the key not found
      return values[index];
    else  
      return ARBITRARY;
  }

  /**
  * returns true if the map has an entry for the
  *   given key.
  * false otherwise.
  */
  public boolean contains(String key){
    // TODO3: Use getIndex to implement this method
    
  }

  /**
  * returns the number of keys currently in the map
  *   (equivalently, the number of key-value pairs in
  *   the map).
  */
  public int size(){
    return length;
  }

  /**
  * removes map entry associated with the given key and 
  *   returns true if such an entry exists.
  * If the given key is not in the map, the map is unchanged
  *   and false is returned.
  */
  public boolean remove(String key){
    int index = getIndex(key);
    if (index == -1)  return false;
    else
    {
      // remove key at index
      for (int i = index; i < length - 1; i ++)
      {
        keys[i] = keys[i+1];
      }
      // TODO4: remove value at index (just like remove key from the code above)
      

      // decrease the size of map
      length--;
      return true;
    }
  }

  /**
  * makes the map empty (but the map still exists)
  */
  public void clear(){
    keys = new String[INITIALSIZE];
    values = new Integer[INITIALSIZE];
    capacity = INITIALSIZE;
    length = 0; // still empty
  }

  /*
  * Get array-index of a given key. Return -1 if not exist
  */
  private int getIndex(String key)
  {
    for (int i = 0; i < length; i++)
    {
      if (keys[i].equals(key)) return i;
    }
    return -1;
  }
  /*
  * In case the map is full, reAllocate it
  */
  private void reAllocate()
  {
    // create a new arrays to hold temporary keys
    String [] keystemp = new String[keys.length];
    // copy keys to keystemp
    System.arraycopy(keys, 0, keystemp, 0, keys.length);
    // now reallocate keys
    keys = new String[keys.length + INCREASESIZE];
    // copy keystemp back to keys
    System.arraycopy(keystemp, 0, keys, 0, keystemp.length);

    // TODO5: now do the same for values
    

    
    // update capacity
    capacity += INCREASESIZE;

  }

  public int getCapacity()
  {
    return capacity;
  }
}












// ANSWERS

// TODO1: length == capacity
// TODO2: index != -1
// TODO3:
/*
    int index = getIndex(key);
    if (index != -1)    return true;
    else return false;
    */

// TODO4:
    /*
      for (int i = index; i < length - 1; i++)
      {
        values[i] = values[i+1];
      }
      /*

// TODO5:
/*
    Integer[] valuestemp = new Integer[values.length];
    System.arraycopy(values, 0, valuestemp, 0, values.length);
    values = new Integer[values.length + INCREASESIZE];
    System.arraycopy(valuestemp, 0, values, 0, valuestemp.length);
    */
