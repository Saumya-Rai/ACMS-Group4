package linkedlist;

import java.util.*;

public class IntersectLL {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		LinkedList<Character> LL1=new LinkedList<Character>();
		LinkedList<Character> LL2=new LinkedList<Character>();
		
		System.out.print("Enter elements of first linked list: ");
		String s=sc.next();
		for(int i=0;i<s.length();i++)
		{
			LL1.add(s.charAt(i));
		}
		System.out.print("Enter elements of second linked list: ");
		s=sc.next();
		for(int i=0;i<s.length();i++)
		{
			LL2.add(s.charAt(i));
		}
		
		if(LL1.size()<LL2.size())
		{
			LinkedList<Character> temp=new LinkedList<Character>();
			temp=LL1;
			LL1=LL2;
			LL2=temp;
		}
    
    //uses difference between sizes of lists
		
		int d=LL1.size()-LL2.size();
		int i=0; boolean found=false;
		while(i<LL2.size())
		{
			if(LL1.get(i+d)==LL2.get(i))
			{
				found=true;
				break;
			}
			i++;
		}
		
		if(found)
			System.out.println(LL2.get(i));
		else
			System.out.println("No intersection point");
	}

}
