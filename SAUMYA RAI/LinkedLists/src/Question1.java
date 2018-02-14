public class Question1 {
    public static void main(String[] args) {
        Node head1 = new Node('9');
        Node.insert( head1,'3' );
        Node head2 = new Node('-');
        Node.insert( head2, '3' );
        Node result = findSum(head1, head2);
        Node.traverse( result );
    }
    public static Node findSum(Node head1, Node head2) {
        Node temp = head1;
        int num1 = 0,num2 = 0;
        boolean isNeg = false;
        while(temp != null) {
            if (temp.val == '-'){
                isNeg = true;
                temp = temp.next;
                continue;
            }
            num1 = num1*10 + Character.getNumericValue( temp.val );
            temp = temp.next;
        }
        if(isNeg) {
          num1 = -num1;
        }
        temp = head2;
        isNeg = false;
        while(temp != null) {
            if (temp.val == '-'){
                isNeg = true;
                temp = temp.next;
                continue;
            }
            num2 = num2*10 + Character.getNumericValue( temp.val );
            temp = temp.next;
        }
        if(isNeg) {
            num2 = -num2;
        }
        num1 += num2;
        String num = String.valueOf( num1 );
        char[] arr = num.toCharArray();

        Node result = new Node( arr[0] );
        for(int i = 1 ; i < arr.length ; i++) {
            Node.insert( result, arr[i] );
        }
        return result;
    }
}