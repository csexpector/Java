import java.lang.Exception;

public class AirportCheckin
{
	public static final int MAXQUEUESIZE = 25;
	public static final int PROCESSTIME  = 10;
	public static int nCurrent = 0;
	public static void main(String[] args) throws Exception
	{
		PeopleQueue waitingLine = new PeopleQueue(MAXQUEUESIZE);
		String passenger;
		// TODO3: Use method enterQueue to initiate 20 first people
		// write your code here
		
		// start processing
		for (int time = 1; time <= PROCESSTIME; time++)
		{
			System.out.println("Monitoring ----- Starting minute #" + time);
			// print content of queue
			System.out.println(waitingLine);
			// every minute, 1 passenger goes to gate#1			
			process(waitingLine, "Gate #1");

			// 2 passengers go to gate#2, 2 passengers go to gate#3			
			process(waitingLine, "Gate #2");
			process(waitingLine, "Gate #3");
			process(waitingLine, "Gate #2");
			process(waitingLine, "Gate #3");
			
			// TOOD4: Write commands to send 3 passengers go to gate#4, 2 passengers go to gate#5
			// Hint: look at examples above
			// write your code here
			

			

			System.out.println("Monitoring ----- Ending minute #" + time);
			// print content of queue
			System.out.println(waitingLine);

			// 10 more people go to the waiting line
			enterQueue(waitingLine, 10);
		}
	}
	/**
	 * Process passenger from queue to gate
	 */
	public static void process(PeopleQueue waitingLine, String gate)
	{
		String passenger = waitingLine.dequeue();
		if (passenger != null)
			System.out.println(gate + " processes " + passenger + ". Done! Next passenger!");
		else
			System.out.println("Queue empty!");
	}
	/**
	 * Enter passenger to queue
	 */
	public static void enterQueue(PeopleQueue waitingLine, int nPassenger) throws Exception
	{
		for (int i = 1; i <= nPassenger; i++)
		{
			String passenger = "Passenger" + (i+nCurrent);
			waitingLine.enqueue(passenger);
		}
		nCurrent += nPassenger;
	}



	
}


// ANSWERS

// TODO3: enterQueue(waitingLine, 20);
/* TODO4:
			process(waitingLine, "Gate #4");
			process(waitingLine, "Gate #5");
			process(waitingLine, "Gate #4");
			process(waitingLine, "Gate #5");
			process(waitingLine, "Gate #4");
			process(waitingLine, "Gate #5");
*/