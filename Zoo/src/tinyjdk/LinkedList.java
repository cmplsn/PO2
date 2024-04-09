package tinyjdk;

public class LinkedList<T> implements List<T> {

    protected class Node{
        public T data;
        public Node next; //i reference type sono già tutti sotto forma di pointer quindi non deve essere di tipo Node*
        public Node(T data, Node next){
            this.data=data;
            this.next=next;
        }
    }

    protected Node head;
    protected int sz;

    public LinkedList(){
        this.head=null;
        sz=0;
    }

    @Override
    public void add(T e) {
        if(head == null){
            //todo: preferibile costruttore di node che prende sia T elem, che Node next per non
            // "dimenticarsi" di popolarli correttamente.
            head=new Node(e,null);
        }
        else{
            Node n=head;
            while(n.next!=null){
                n=n.next;
            }
            n.next=new Node(e,null);
        }
        sz++;

    }

    @Override
    public void clear() {
        this.head=null; //GARBAGE COLLECTION MULTISHOT-> elimina prima solo la testa poi il next non sarà più
                        // puntato da nulla quindi garbage collector al prossimo giro libererà puntatore dopo ecc ecc.
        sz=0;
    }

    @Override
    public boolean isEmpty() {
        return head==null;
    }

    @Override
    public void remove(T x) {
       for (Node prev=null, n=head; n!=null; prev=n,n=n.next){
           if(n.data.equals(x)){
               if(prev!=null){
                   prev.next=n.next;
               }else{
                   head=n.next;
               }
               return;
           }
       }
    }

    @Override
    public int size() {
        return sz;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node n = head;
            @Override
            public boolean hasNext() {
                return n != null;
            }
            @Override
            public T next() {
                T r = n.data;
                n = n.next;
                return r;
            }
        };
    }
    protected Node getNode(int i){
        if(i<0||i>=size()){
            throw new RuntimeException(String.format("index %d is out of bound (size = %d)", i, size()));
        }
        Node n =head;
        for(;i>0;--i)
            n=n.next;
        return n;
    }

    @Override
    public T get(int i) {
        return getNode(i).data;
    }

    @Override
    public T set(int i, T x) {
        Node n = getNode(i);
        T old = n.data;
        n.data = x;
        return old;
    }

    @Override
    public void add(int i, T x) {
        //todo: da fare
    }

    @Override
    public T remove(int i) {
        //todo:da fare
        return null;
    }
}
