package guma.simulator;
import guma.simulator.AddingSimulator;
public class Benchmark
{
	public static void main(String[] args)
	{
		AddingSimulator add=new AddingSimulator(100,200);
		boolean next=true;

		do
		{
			next=add.next();
			System.out.println(add.toString());
			System.out.println("\t"+add.getTelestis1()+"\n\t"+add.getTelestis2()+"\n"+add.getResult());
		}while (next);
	}
}
