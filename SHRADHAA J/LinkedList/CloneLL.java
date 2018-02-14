package linkedlist;

import java.util.*;
//uses Node structure
public class CloneLL {

	public static void main(String[] args) {
		Node head=new Node(1, null, null);
		
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter size of linked list: ");
		int n=sc.nextInt();
		
		Node temp=head;
		for(int i=2;i<=n;i++)
		{
			Node nxt=new Node(i, null, null);
			temp.next=nxt;
			temp=temp.next;
		}
		
		System.out.println("Enter random links: ");
		temp=head;
		for(int i=1;i<=n;i++)
		{
			System.out.print("Random link for "+i+": ");
			int r=sc.nextInt();
			
			Node f=head.find(head, r);
			//System.out.println();
			if(f!=null)
			{
				temp.random=f;
			}
			temp=temp.next;
		}
		
		head.print(head);
		
		//cloning
		Node cl=head.clone(head);
		cl.print(cl);

	}

}
