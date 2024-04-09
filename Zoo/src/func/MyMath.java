package func;

public class MyMath {

    public static double square(double x){
        return x * x;
    }

    public static void main(String[] args) {
        int x=10; //todo: NON ASSEGNAMENTO MA BINDING.
        x=6; //todo: QUESTO è ASSEGNAMENTO. ASSEGNAMENTO = MODIFICA
    }
}



//todo: scrivendo questo in Java non sto comunque utilizzando Object-Orientation. Java non è Paradigma OO. il Paradigma
// è IMPERATIVO. Il paradigma IMPERATIVO richiede ASSEGNAMENTO. Assegnamento = imperativo, No Assegnato = Non Imperativo
// Non Imperativo = Funzionale

/*class Person{
    public String name;
    public int age;
}
Person x;*/

//todo: in c++ creando classe person non è object-oriented. Non definisco OO se ho classe e creo costruttori,
// dico programmazione OO quando ho POLIMORFISMO (subtype) quindi virtual-table e Dynamic Dispatching.

/*class person{
    String name;
    int age;
}*/
