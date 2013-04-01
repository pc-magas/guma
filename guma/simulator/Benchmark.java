package guma.simulator;
import guma.simulator.*;
public class Benchmark
{
	public static void main(String[] args)
	{
		DivisionSimulator add=new DivisionSimulator(300002,2);
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
