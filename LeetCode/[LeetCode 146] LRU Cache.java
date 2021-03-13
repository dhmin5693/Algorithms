import java.util.HashMap;
import java.util.Map;

class LRUCache {

    private final Map<Integer, Node> map = new HashMap<>();
    private final LinkedList list;

    public LRUCache(int capacity) {
        list = new LinkedList(capacity);
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            var node = map.get(key);
            if (node.value != -1) {
                list.moveToFirst(node);
            }
            return node.value;
        }

        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            var node = map.get(key);
            node.value = value;
            list.moveToFirst(node);
            return;
        }

        if (list.isFull()) {
            var target = list.deleteLast();
            map.remove(target.key);
        }

        var node = new Node(key, value);
        list.addFirst(node);
        map.put(key, node);
    }

    static class LinkedList {

        int size = 0;
        int capacity;

        Node head = new Node(-1, -1);
        Node tail = new Node(-1, -1);

        public LinkedList(int capacity) {
            this.capacity = capacity;
            head.prev = null;
            head.next = tail;
            tail.prev = head;
            tail.next = null;
        }

        public void addFirst(Node node) {
            node.prev = head;
            node.next = head.next;
            node.next.prev = node;
            head.next = node;
            size++;
        }

        public Node deleteLast() {

            var target = tail.prev;

            if (target == head) {
                return null;
            }

            target.value = -1;
            delete(target);
            return target;
        }

        public void delete(Node node) {
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
            size--;
        }

        public void moveToFirst(Node node) {
            delete(node);
            addFirst(node);
        }

        public boolean isFull() {
            return size >= capacity;
        }
    }

    static class Node {

        int key, value;
        Node prev, next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int hashCode() {
            return key;
        }

        @Override
        public boolean equals(Object obj) {

            if (!(obj instanceof Node)) {
                return false;
            }

            Node node = (Node) obj;
            return node.key == this.key && node.value == this.value;
        }
    }
}
