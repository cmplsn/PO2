package tinyjdk;

public interface Collection<T> extends Iterable<T>{ //todo: su Collection<T> T è parametro mentre su Iterable<T>
                                                    // T è argomento
    void add(T e);

    default void addAll(Collection<T> c){ //todo: polimorfo non faccio nessuna assunzione sul tipo
        Iterator<T> t =c.iterator();
        while(t.hasNext()){
            add(t.next());
        }
    }

    //todo: potrei creare un defaul void addAll slegato da generico di Collection ma al momento di utilizzare add
    // non funziona perchè add richiede stesso generic di Collection
    default void addAllE(Collection<T> c){
        Iterator<T> t =c.iterator();
        while(t.hasNext()){
            add(t.next());
        }
    }

    void clear();
    default boolean contains(T e){
        Iterator<T> it= iterator();
        while(it.hasNext()){
            T x=it.next();
            if(x.equals(e)){
                return true;
            }
        }
        return false;
    }
    boolean isEmpty();
    void remove(T x);

    int size();


}
