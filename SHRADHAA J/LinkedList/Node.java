package linkedlist;
//used for question 2
public class Node {
	
	Node next=null, random=null;
	int element=0;
	Node(int e, Node n, Node r)
	{
		element=e;
		next=n;
		random=r;
	}
	
	Node find(Node head, int r)
	{
		while(head!=null)
		{
			//System.out.print(head.element+" ");
			if(head.element==r)
				return head;
			head=head.next;
		}
		return null;
	}
	
	void print(Node head)
	{
		while(head!=null)
		{
			System.out.print(head.element+" ");
			if(head.next!=null)
				System.out.print(head.next.element+" ");
			else
				System.out.print("null ");
			if(head.random!=null)
				System.out.print(head.random.element);
			else
				System.out.print("null");
			head=head.next;
			
			System.out.println();
		}
		
	}
	//cloning
	Node clone(Node head)
	{
		Node temp=head;
		Node t=head;
		while(head!=null)
		{
			t.next=head.next;
			
			t=t.next;
			head=head.next;
		}
		
		head=temp;
		t=temp;
		while(head!=null)
		{
			int r=head.random.element;
			if(t==null)
				System.out.println("yes");
			Node f=t.find(t, r);
			if(f!=null)
			{
				t.random=f;
			}
			t=t.next;
			
			head=head.next;
		}
		return temp;
	}
	
}
