import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
public class Node {

    public char val;
    public Boolean visited; //A normal linked list doesnt have visited parameter.
    public Node next = null;
    public Node random = null;

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
    public static void insertRandom(Node head, char n1, char n2){                  //for question 2
        Node temp = head;
        while(temp.val != n1){
            temp = temp.next;
        }
        Node temp2 = head;
        while(temp2.val != n2){
            temp2 = temp2.next;
        }
        temp.random = temp2;
        temp = head;
    }
    public static void traverse(Node head) {
        while (head != null)
        {if( head.random == null) {
            System.out.print( head.val + " -> " );
            head = head.next;
        }
        else{
            System.out.print(head.val + " random(" + head.random.val + ")   ->");  //used in question 2 only
            head = head.next;
        }
        }
        System.out.println();
    }
    public static Node clone(Node head){                                           //for question 2
        Node head1 = new Node(head.val);
        Node temp = head1;
        Node temp2 = head;
        while (temp2 != null){
            temp.next = temp2.next;
            temp = temp.next;
            temp2 = temp2.next;
        }
        temp = head1;
        temp2 = head;
        while (temp2 != null){
            temp.random = temp2.random;
            temp = temp.next;
            temp2 = temp2.next;
        }
        return (head1);

    }
    public static void findIntersection(Node head1, Node head2){                   //for question 3
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
