

class TestMap
{
	public static void main(String[] args)
	{
		MyMap map = new MyMap();
		map.set("Larry", 29);
		map.set("Mary", 62);
		map.set("Alice", 29);
		map.set("Phil", 91);

		// Test get
		System.out.println("Age of Alice is: " + map.get("Alice"));

		// Test contains
		if (map.contains("John"))
			System.out.println("Found John");
		else
			System.out.println("John not found");
		// Test contains
		if (map.contains("Phil"))
			System.out.println("Found Phil");
		else
			System.out.println("Phil not found");

		// Test size
		System.out.println("Map size: " + map.size());

		// Test remove
		if (map.remove("Larry"))
		{
			System.out.println("Larry is removed!");
			System.out.println("Now the size is: " + map.size());
		}
		else
		{
			System.out.println("Cannot remove, Larry not found");
		}


		// Test reallocate
		System.out.println("Current Capacity: " + map.getCapacity());
		for (int i = 0; i < map.getCapacity(); i++)
		{
			map.set(String.valueOf(i), i);
		}
		System.out.println("Current Capacity: " + map.getCapacity()); // should be increased

	}
}