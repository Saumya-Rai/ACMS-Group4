import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import java.util.*;
public class Node {

    public char val;

    public Node next = null;
    public Node random = null;

    public Node(char val) {
        this.val = val;

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
    public static void findIntersection(Node head1, Node head2){
        ArrayList<Character> visit = new ArrayList<Character>(10);

        while (head1 != null)
        {
           visit.add(head1.val);
            head1 = head1.next;
        }

        while (head2 != null)
        {
            if(visit.contains( head2.val ) == false){
                head2 = head2.next;
            }
            else{
                break;
            }

        }

        boolean intersect = true;
        Node intersection = head2;
        while (head2 != null){
            if(visit.contains( head2.val ) == false ){
                intersect = false;
            }
            head2 = head2.next;
        }
        if (intersect == false){
            System.out.println("no intrsection");
        }
        else System.out.println( intersection.val + " is intersection pooint" );
    }
}
