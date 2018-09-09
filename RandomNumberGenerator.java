import java.util.*;
public class 1411099_RandomNumberGenerator{
	public static void main(String args[])
	{
        int p, q, r;
        int count=1;count1 =0;
        int myarray[]=new int[101];
		System.out.println("Please enter First element");
		Scanner sc = new Scanner(System.in);
		myarray[0]=sc.nextInt();
		
		System.out.println("Enter p");
		p=sc.nextInt();
		System.out.println("Enter q");
		q=sc.nextInt();
		System.out.println("Enter r");
		r=sc.nextInt();
		
		System.out.println(myarray[0]);
		for(int i=0;i<100;i++)
			{
			myarray[i+1]=((p*myarray[i])+q)%r;
			System.out.println(myarray[i+1]);
            if((myarray[0]!=myarray[i+1])&&(count1!=1))
				count++;
            else count1=1;
		}
        System.out.println("Period until which RANDOM NUMBER GENERATED is :"+count);
	}
}
