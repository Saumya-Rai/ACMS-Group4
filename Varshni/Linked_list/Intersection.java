import java.util.*;

public class Intersection
{
	public static void main(String[] args)
  {
		Scanner sc=new Scanner(System.in);
		LinkedList<String> list1=new LinkedList<String>();
		LinkedList<String> list2=new LinkedList<String>();
		list1.add("1");
		list1.add("3");
		list1.add("5");
		list1.add("6");
		list1.add("7");
		list2.add("2");
		list2.add("3");
		list2.add("4");
		String intersection="";
		int flag=0;
		for(int i=0;i<list1.size();i++)
		{
			for(int j=0;j<list2.size();j++)
			{
				if(list1.get(i)==list2.get(j))
				{
					intersection =list1.get(i);
					flag=1;
					break;
				}
			}
		}

		if(flag==0)
		{
			System.out.println("No intersection");
		}
		else
			System.out.println("Point of intersection is " + intersection);
  }
}
