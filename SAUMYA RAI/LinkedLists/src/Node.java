import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class Node {

    public char val;
    public Boolean visited;
    public Node next = null;

    public Node(char val) {
        this.val = val;
       // this.next=null;
        this.visited = false;
    }

    public static void insert(Node head, char ch) {
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = new Node(ch);
    }


    public static void traverse(Node head) {
        while (head != null)
        {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println();
    }
    public static void findIntersection(Node head1, Node head2){
        while (head1 != null)
        {
            if(head1.visited == false){
                head1.visited = true;
            }
            head1 = head1.next;
        }
        while (head2 != null)
        {
            if(head2.visited == false){
                head2.visited = true;
            }
            else {
                System.out.println( head2.val+" is intersection node " );
                break;
            }
            head2 = head2.next;
        }
    }

}

