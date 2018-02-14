public class Question2 {

    public static void main(String[] args) {
        Node head1 = new Node('1');
        Node.insert( head1,'2' );
        Node.insert( head1,'3' );
        Node.insert( head1,'4' );
        Node.insert( head1,'5' );
        Node.insert( head1,'6' );
        Node.insertRandom( head1, '1', '5' );
        Node.insertRandom( head1, '2', '6' );
        Node.insertRandom( head1, '3', '1' );
        Node.insertRandom( head1, '4', '2' );
        Node.insertRandom( head1, '5', '2' );
        Node.insertRandom( head1, '6', '3' );
        Node.traverse( head1 );
        Node newHead = Node.clone( head1 );
        System.out.println("Printing Clone");
        Node.traverse( newHead );
    }
}