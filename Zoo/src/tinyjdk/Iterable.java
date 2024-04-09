package tinyjdk;

public interface Iterable<E> { //todo: generic T qui è parametro

    Iterator<E> iterator(); //todo: qui invece lo passo come se fosse ARGOMENTO. ho già definito T
                            // come parametro quando "creo" Iterable.

}


//todo: per fare più classi interfacce dentro allo stesso file solo NESTED non separate.

//todo: classe Nested non ha nessuna relazione di subtyping con la classe enclosing.

//todo: TYPE PARAMETER e TYPE ARGUMENT. Parameter -> dentro a FIRMA funziona, void f(int n) -> int n è parametro
// Argument -> passato al momento di chiamata di funzione f(7) -> 7 è argomento