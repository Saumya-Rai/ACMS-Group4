public class Question3 {
    public static void main(String[] args) {
        Node head1 = new Node( '1' );
        head1.next = new Node( '2' );
        head1.next.next = new Node( '3' );
        head1.next.next.next = new Node( '4' );
        head1.next.next.next.next = new Node( '5' );
        head1.next.next.next.next.next = new Node( '6' );
        Node head2 = new Node( 'A' );
        head2.next = new Node( 'B' );
        head2.next.next = head1.next.next;
        Node.findIntersection( head1, head2 );
    }
    }
