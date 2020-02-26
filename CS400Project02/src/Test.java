
public class Test {
    
    public static void main(String[] args) throws KeyNotFoundException {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
//        RBT<Integer, Integer> bst = new RBT<Integer, Integer>();
        try {
            
            // USE AND DO NOT EDIT THESE CONSTANTS
            final int RED = 0;
            final int BLACK = 1;
            
            Integer one = 12;
            Integer two = 2;
            Integer three = 1;
            Integer four = 300;
            
            bst.insert(one, one);
            bst.insert(two, two);
            bst.insert(three, three);
            bst.insert(four, four);
            
//            System.out.println(bst.getKeyAtRoot());
//            System.out.println("color: " + bst.colorOf(2));
//            System.out.println("left : " + bst.getKeyOfLeftChildOf(two));
//            System.out.println("right: " + bst.getKeyOfRightChildOf(two) + "\n");
//
//
//            
//            System.out.println(one);
//            System.out.println("color: " + bst.colorOf(one) + "\n");
//            
//            System.out.println(three);
//            System.out.println("color: " + bst.colorOf(three) + "\n");
//            
//            System.out.println(four);
//            System.out.println("right: " + bst.colorOf(four));
            
            System.out.println(bst.getHeight());
            System.out.println(bst.getInOrderTraversal().toString());
            System.out.println(bst.getPreOrderTraversal().toString());
            System.out.println(bst.getPostOrderTraversal());
            System.out.println(bst.getLevelOrderTraversal());

            
//            System.out.println("root: " + bst.getKeyAtRoot());
//            System.out.println(bst.getKeyOfLeftChildOf(2));
//            System.out.println(bst.getKeyOfRightChildOf(12));

        } catch (IllegalNullKeyException | DuplicateKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
