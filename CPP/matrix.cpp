//
// Created by Alessandro on 02/05/24.
//
#include <iostream>
#include <ostream>
#include <vector>
using namespace std;
//todo: il template system di C++ è più "potente" dei generics di Java. è possibile templetizzare  qualsiasi tipo di
// entità. possono templetizzare anche typedef ma per esempio NON sono templetizzabili namespace e campi di una classe.
//todo: in C++ prima scriviamo template <class T> per definire il generic T e poi creo

//todo:operatore può essere overloadato dentro a classe con un parametro solo perchè il primo parametro sarà this
// sottointeso. se invece voglio fare overloading globale, lo dichiaro fuori da qualsiasi classe con due parametri,
// quello che andrà a sinistrà del + e quello che andrà a destra.

template <class T>
class matrix{
private:
    //todo: utilizzare vector al posto di un array C-like anche se non utilizzo le sue proprietà di dimensione dinamica
    // ecc ecc pushback ecc, ho comunque il vantaggio di costruzione e distruzione automatica senza dovermene preoccupare.
    size_t cols;
    vector<T> v;

public:

    //todo: in C++ i costruttori UNARI sono OPERATORI DI CONVERSIONE. Se io ho un size_t od un qualsiasi tipo
    // convertibile a size_t. Mettiamo che chiamo una funzione che ha come parametro una matrix. se io chiamo la
    // funzione f(7) converte automaticamente ???. Risolvo la situazione aggiungendo EXPLICIT prima della firma della
    // funzione permettendo l'utilizzo di quello specifico costruttore solo ESPLICITAMENTE quindi da parte del
    // programmatore
    matrix() : v(){}
    matrix(const matrix<T>& m) : v(m.v) {}

    //todo: CONVERSION CONSTRUCTOR. non riconosciuto dal compilatore come default/copy constructor
    template <class S>
    matrix(const matrix<S>& m): cols(m.cols), v(m.get_rows()*m.get_cols()) {
        for (int i = 0; i < v.size(); ++i) {
            v[i]=T(m.v[i]);
            //todo:CONVERSIONE->chiamo il costruttore di tipo T e d in questo caso visto che m.v[i] è di tipo S,
            // quindi chiedo che venga costruito un oggetto di tipo T a partire da uno di tipo S. diverso
            // dal CAST perchè cast forza un tipo su variabile di altro tipo. Conversione invece prendere
            // il binario di un oggetto S e lo interpreta come T.

            //todo: vero e proprio cast
            v[i]=*((T*)&m.v[i]);
        }
    }
    //todo: commentando il copy constructor il conversion constructor potrebbe anche fungere da copy constructor perchè
    // quando lo utilizzo S viene impostato = T.


    //matrix(size_t rows, size_t _cols): cols(_cols), v(rows*cols) {}
    matrix(size_t rows, size_t _cols, const T& x = T()): cols(_cols), v(rows * cols,x){}
    explicit matrix(size_t dim): matrix(dim, dim) {}
    //todo: non serve distruttore perchè ho solo vector e distruttore di v viene chiamato automaticamente.

    typedef T value_type; //creo aliasing di T e gli do come nome secondario valuetype
    //todo: creo il typedef value_type proprio perchè dall'esterno non posso richiamare matrix::T ma posso fare
    // matrix::value_type. Utilizzo value_type per tutti container lineari (in STL), invece STL per cose tipo Dizionari,
    // mappe ecc dove posso avere fino a due template avra come member-types value_type e key_type.

    //qualsiasi tipo di container deve avere sia begin() che end(), che hanno come tipo di ritorno iterator.

    typedef typename vector<T>::iterator iterator;

    //todo: definisco anche const_iterator perchè voglio poter avere un iterator solo per leggere (const_iterator) e
    // uno per leggere e scrivere.
    typedef typename vector<T>::const_iterator const_iterator;

    //todo:usando vettore con tipo T*
    //typedef T* iterator;

    iterator begin(){
        return v.begin();

        //todo:usando vettore con tipo T*
        //return v;
    }
    iterator end(){
        return v.end();

       // todo:facendo con vettore fatto T*
       // return v +cols+rows;
    }
    //per utilizzare dalla class matrix iteatore "nativo" da vector visto che la strutt. dati che uso in matrix è vector.

    const_iterator begin() const{
        return v.begin();
    }

    const_iterator end() const{
        return v.end();
    }

    //todo: da C++14 ->
    using valuetype2 =T;

    //todo: CASTING OPERATOR
    operator vector<T>&()const{
        return v;
    }

    operator int() const{ //todo: non ha senso ma è possibile.
        return 11;
    }
    matrix<T>& operator*(){
        return this;
    }
    matrix<T>& operator=(const matrix<T>& m) {
        cols=m.cols;
        v=m.v;
        return *this;
    }

    const T& operator() (size_t i, size_t j) const{
        return v.at(i*cols+j);
    }

    //todo: overloading di () si chiama APPLICATION OPERATOR
    T& operator() (size_t i, size_t j){
        return v.at(i*cols+j);
    }

    const T& at(size_t i, size_t j) const{
        return v.at(i*cols+j);
    }
    size_t get_cols() const{return cols;}
    size_t get_rows() const{return v.size()/cols;}



};

//todo: ostream viene fatto globale non interno alla classe, perchè il primo parametro deve essere ostream, se io l'avessi
// messo dentro ad una classe avrei potuto al massimo metterlo dentro la classe ostream (non posso) quindi lo metto
// esterno.
template<class C>
ostream& operator<<(ostream& os,const C& m){

    //todo: typename si prefigge al nome di un tipo quando utilizza :: per problemi di parsing. per aiutare a far capire
    // al compilatore che quello è un nome e non altre cose.
    for (typename C::iterator it=m.v.begin(); it != m.v.end(); ++it) {
        typename C::value_type x=*it;
        os << *it <<" ";
    }
    return os;
}

//todo:template system è un sistema di macroexpansion. Quando compila controlla solo la sintassi e sospende "giudizio"
// fino al primo utilizzo.
template <class C>
void f(C& v){
    v.push_back(7);
}

//todo: il template system al momento dell'utilizzo del template ricompila utilizzando il tipo utilizzato e solo in quel
// momento fa un typecheck ecc ecc


/*int main3(){
    matrix<int> m(20,30);
    for(matrix<int>::iterator it=m.begin();it!=m.end();++it){
        matrix<int>::value_type x=*it;
        cout<<x<<endl;

    }
return 0;
}*/

//todo: INLINING->invece della chiamata a funzione, sposti il suo corpo dove c'è la chiamata cosi non c'è bisogno della
// jump alla funzione

//todo: C++ introduce la Value-Oriented programming. gli oggetti che io creo devono comportarsi come dei valori non come
// degli oggetti ovvero si comportano come i data type nativi quindi c'è la convenzione di inserire sempre
// Default constructor, Copy constructor e operatore di Assegnamento


//todo: l'operatore [] tecnicamente si chiama SUBSCRIPT

//todo: senza subtyping ovviamente in C++ non riesco a programmare polimorficamente, Dynamic Dispatching ecc. però
// posso usare i TEMPLATE


//todo: std (standard library) ed stl (standard template library) sono due cose diverse e separate.

//todo: Cast in JAVA: quanto tipo è built-in fa conversione mentre quando deve fare cast di reference type posso solo
// down-castare ovvero l'oggetto castato può essere solo un sottotipo di quello di "partenza"


//todo: in C++ posso fare veri e propri cast solamente attraverso i puntatori T*, altrimenti passando T prende sempre il
// costruttore di T.

//todo: template system guarda inizialmente solo la sintassi poi sospende il giudizio poi riprende a "giudicare" la
// correttezza di quello che ho scritto solo al momento dell'utilizzo.

//todo: end() di container ritorna past-the-end.

//todo: SMART POINTER: