package tinyjdk;

public class StructuralSet<T> extends AbstractResizableArray<T> implements Set<T>{


    @Override
    public void add(T e) {
        if(!contains(e)){
          super.add(e);
        }
    }

    @Override
    public void remove(T x) {
        //TODO
    }

    @Override
    public Iterator<T> iterator() {
        //TODO
        return null;
    }
}

//todo: differenza interfaccia vs. Abstract Class: nelle Abstract Class posso avere campi e si possono definire metodi,
// implementare datatype e lasciare implementazione ai figli ???


