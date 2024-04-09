package tinyjdk;
//todo: non esiste modo solo utilizzando Type-System per impedire che due valori non siano duplicati
public interface Map<K,V> extends Iterable<Pair<K,V>> {

    void put( K k, V v);

    V get(K k) throws KeyNotFoundException;

    class KeyNotFoundException extends Exception{
        public KeyNotFoundException(Object k){
            super(String.format("key %s not found in map", k));
        }
    }

}
//todo: FINAL può essere usato su Campi, Parametri  e Metodi:
// su Parametri e Campi impedisce modifica
// su Metodi -> chi eredita non può fare Override