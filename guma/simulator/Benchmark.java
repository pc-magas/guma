package guma.simulator;
import guma.simulator.*;
public class Benchmark
{
	public static void main(String[] args)
	{
		AbstractSimulator add=new MultiplicationSimulator(11,22);
		boolean next=true;

		do
		{
			try
			{
			next=add.next();
			System.out.println(add.toString());
			System.out.println("\t"+add.getTelestis1()+"\n\t"+add.getTelestis2()+"\n"+add.getResult());
			}
			catch(Exception e)
			{
				e.printStackTrace();
				break;
			}
		}while (next);
	}
}
