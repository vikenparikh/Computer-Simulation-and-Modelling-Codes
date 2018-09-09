import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class DumpTruck
	{ 
	static int l=2,lq=3,s=1,sq=0;
	static int clock=0,prev=0,bl=0,bs=0;
	static int lcount=2,scount=1,tcount=0;

	static ArrayList<Integer> loadingQueue = new ArrayList<Integer>();
	static ArrayList<Integer> sQueue = new ArrayList<Integer>();
	static ArrayList<FE> event = new ArrayList<FE>();

	static int load[]={10,5,5,10,15,10,10};
	static int scale[]={12,12,12,16,12,16};
	static int travel[]={60,100,40,40,80};
	
	public static void main(String args[])
		{
		loadingQueue.add(4);
		loadingQueue.add(5);
		loadingQueue.add(6);
		
		event.add(new FE("EL",5,3));
		event.add(new FE("EL",10,2));
		event.add(new FE("EW",12,1));
		
		System.out.println("Enter the clock limit");
		Scanner sc= new Scanner(System.in);
		int climit=sc.nextInt();
		for(int i=0;i<climit;)
			{
			for(int k=0;k<=event.size();k++)
				{
				for(int j=0;j<event.size()-1;j++)
					{
					if(event.get(j).time>event.get(j+1).time)
						{
						Collections.swap(event,j,j+1);
						}
					}
				}
			System.out.println(clock+"    "+event.toString()+" "+bl+" "+bs);
			i=event.get(0).time;
			clock=i;
			bl=bl+l*(clock-prev);
			bs=bs+s*(clock-prev);
			prev=clock;
			event.get(0).getNextEvent();
			event.remove(0);
			}
		}
}

class FE
	{
	String act;
	int time,tnumber;
	FE(String a,int t,int tn)
		{
		act=a;
		time=t;
		tnumber=tn;
		}
	public String toString()
		{
		String p=new String;
		p=act+","+time+","+tnumber
		return p;
		}
	void getNextEvent()
		{
		if(act.equals("EL"))
			{
			if(DumpTruck.s!=0)
				{
				DumpTruck.sQueue.add(tnumber);
				DumpTruck.sq++;
				if(DumpTruck.lq!=0)
					{
					DumpTruck.lq--;
					DumpTruck.event.add(new FE("EL",DumpTruck.clock+DumpTruck.load[DumpTruck.lcount++%7],DumpTruck.loadingQueue.get(0)));
					DumpTruck.loadingQueue.remove(0);
					}
				else DumpTruck.l--;
				}
			else
				{
				DumpTruck.s++;	
				DumpTruck.lq--;
				DumpTruck.event.add(new FE("EW",DumpTruck.clock+DumpTruck.scale[DumpTruck.scount++%6],DumpTruck.loadingQueue.get(0)));
				DumpTruck.loadingQueue.remove(0);
				}		
			}
		if(act.equals("EW"))
			{
			if(DumpTruck.sq!=0)
				{
				DumpTruck.sq--;
				DumpTruck.event.add(new FE("ALQ",DumpTruck.clock+DumpTruck.travel[DumpTruck.tcount++%5],DumpTruck.event.get(0).tnumber));
				DumpTruck.event.add(new FE("EW",DumpTruck.clock+DumpTruck.scale[DumpTruck.scount++%6],DumpTruck.sQueue.get(0)));
				DumpTruck.sQueue.remove(0);
				}
			else DumpTruck.event.add(new FE("ALQ",DumpTruck.clock+DumpTruck.travel[DumpTruck.tcount++%5],DumpTruck.event.get(0).tnumber));
			}
		if(act.equals("ALQ"))
			{
			DumpTruck.loadingQueue.add(DumpTruck.event.get(0).tnumber);
			DumpTruck.lq++;
			if(DumpTruck.l<=1)
				{
				DumpTruck.event.add(new FE("EL",DumpTruck.clock+DumpTruck.load[DumpTruck.lcount++%7],DumpTruck.loadingQueue.get(0)));
				DumpTruck.l++;
				DumpTruck.lq--;
				DumpTruck.loadingQueue.remove(0);
				}
			}
		}
	}

