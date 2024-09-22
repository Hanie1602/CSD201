// =========== DO NOT EDIT THE GIVEN CONTENT OF THIS FILE ============

class Node {

    Worker info;
    Node next;

    Node() {
    }

    Node(Worker x, Node p) {
        info = x;
        next = p;
    }

    Node(Worker x) {
        this(x, null);
    }
}
