package linkedlist;

import java.util.*;

public class AddLL {
	
	static LinkedList<Integer> LL1 = new LinkedList<Integer>(); //number 1
	static LinkedList<Integer> LL2 = new LinkedList<Integer>(); //number 2
	static LinkedList<Integer> LLR = new LinkedList<Integer>(); //result
	
	static int A;
	static int B;

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter first number: ");
		A=sc.nextInt();
		System.out.print("Enter second number: ");
		B=sc.nextInt();
		
		if(Math.abs(B)>Math.abs(A))
		{
			int temp=A;
			A=B;
			B=temp;
		}
		
		int a=(int)Math.abs(A);
		int b=(int)Math.abs(B);
		
    //conversion of numbers to linked lists
		while(a!=0)
		{
			LL1.addFirst(a%10);
			a/=10;
		}
		while(b!=0)
		{
			LL2.addFirst(b%10);
			b/=10;
		}
		
		if((A>=0 && B>=0) || (A<0 && B<0))
			add();
		else
			sub();
		
	}
	
  //function for two positive number or two negative numbers
	public static void add()
	{
		if(LL1.get(0)==0)
		{
			print(LL2);
		}
		else if(LL2.get(0)==0)
		{
			print(LL1);
		}
		else
		{
			int i=LL1.size()-1;
			int j=LL2.size()-1;
			int carry=0;
			
			while(i>=0 && j>=0)
			{
				int s=LL1.get(i)+LL2.get(j);
				s=s+carry;
				
				LLR.addFirst(s%10);
				
				carry=s/10;
				
				i--; j--;
			}
			//System.out.println(i+" "+j);
			while(i>=0)
			{
				int s=LL1.get(i)+carry;
				
				LLR.addFirst(s%10);
				
				carry=s/10;
				
				i--;
			}
			while(j>=0)
			{
				int s=LL2.get(i)+carry;
				
				LLR.addFirst(s%10);
				
				carry=s/10;
				
				j--;
			}
			
			if(carry!=0)
				LLR.addFirst(carry);
			
			if(A<0)
				System.out.print("-");
			print(LLR);
		}
	}
	
  //function for one positive and one negative number
	public static void sub()
	{
		if(LL1.get(0)==0)
		{
			if(A<0)
				System.out.print("-");
			print(LL2);
		}
		else if(LL2.get(0)==0)
		{
			if(B<0)
				System.out.print("-");
			print(LL1);
		}
		else
		{
			int i=LL1.size()-1;
			int j=LL2.size()-1;
			
			
			while(i>=0 && j>=0)
			{
				int s=LL1.get(i)-LL2.get(j);
				
				if(s<0)
				{
					s=s+10;
					LL1.add(i-1, LL1.get(i-1)-1);
				}
				
				LLR.addFirst(s%10);
				
				i--; j--;
			}
			//System.out.println(i+" "+j);
			while(i>=0)
			{
				int s=LL1.get(i);
				
				if(s<0)
				{
					s=s+10;
					LL1.add(i-1, LL1.get(i-1)-1);
				}
				
				LLR.addFirst(s%10);
				
				i--;
			}
			while(j>=0)
			{
				int s=LL2.get(i);
				
				if(s<0)
				{
					s=s+10;
					LL1.add(i-1, LL1.get(i-1)-1);
				}
				
				LLR.addFirst(s%10);
				
				
				j--;
			}
			
			if(A<0)
				System.out.print("-");
			print(LLR);
		}
	}
	
	//printing function
	public static void print(LinkedList<Integer> LL)
	{
		for(int i=0;i<LL.size();i++)
			System.out.print(LL.get(i));
	}

}
