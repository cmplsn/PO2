package tinyjdk;

public interface SortedSet<T extends Comparable<T> > extends Set<T> {
    //todo: mettendo <T extends > impongo un interfaccia minima
    // per cui il generic possa essere confrontabile

    T first();

    T last();

    //boolean lessThan(T x);
    //todo: non posso farlo perchè starai dando un metodo lessThan a SortedSet e lo andrei
    // a confrontare con un oggetto di tipo T

}

//todo: Come ordinare senza sapere che tipo stiamo ordinando? NON e chiedi a chi ti sta chiamando di darti un valido
// operatore di ordinamento


//todo: La keyword EXTENDS ha 3 modi di utilizzo:
// 1) Suberinterface extends Interface
// 2) Subclass extends Class -> specifico classe Padre
// 3) Generic extends Type -> limito utilizzo Generic almeno a Type