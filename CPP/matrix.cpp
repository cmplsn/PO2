//
// Created by Alessandro on 02/05/24.
//

#include <vector>
using namespace std;

//todo: in C++ prima scriviamo template <class T> per definire il generic T e poi creo
template <class T>
class matrix{
private:
    //todo: utilizzare vector al posto di un array C-like anche se non utilizzo le sue proprietà di dimensione diniamica
    // ecc ecc pushback ecc, ho comunque il vantaggio di costruzione e distruzione automatica senza dovermene preoccupare.
    vector<T> v;
    size_t cols;
public:

    //todo: in C++ i costruttori UNARI sono OPERATORI DI CONVERSIONE. Se io ho un size_t od un qualsiasit tipo
    // convertibile a size_t. Mettiamo che chiamo una funzione che ha come parametro una matrix. se io chiamo la
    // funzione f(7) converte automaticamente ???. Risolvo la situazione aggiungendo EXPLICIT prima della firma della
    // funzione permettendo l'utilizzo di quello specifico costruttore solo ESPLICITAMENTE quindi da parte del
    // programmatore
    matrix() : v(){}
    matrix(const matrix<T>& m) : v(m.v) {}
    matrix(size_t rows, size_t _cols): cols(_cols), v(rows*cols) {}
    explicit matrix(size_t dim): matrix(dim,dim) {}

    //todo: non serve distruttore perchè ho solo vector e distruttore di v viene chiamato automaticamente.

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

};

/*int main(){
    matrix<int> a(10);
    a(1,1)=2;

}*/

//todo: INLINING->invece della chiamata a funzione, sposti il suo corpo dove c'è la chiamata cosi non c'è bisogno della
// jump alla funzione

//todo: C++ introduce la Value-Oriented programming. gli oggetti che io creo devono comportarsi come dei valori non come
// degli oggetti ovvero si comportano come i data type nativi quindi c'è la convenzione di inserire sempre
// Default constructor, Copy constructor e operatore di Assegnamento


//todo: l'operatore [] tecnicamente si chiama SUBSCRIPT