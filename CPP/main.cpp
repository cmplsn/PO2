#include <iostream>
using namespace std;

class animal{
protected:
    int weight;
    double speed;

public:

    animal() : weight(),speed() {}

    //todo: in  C++ non assegno campi, li costruisco.
    //todo: costruttore: nome campo che vogliamo inizializzare e tra parentesi il valore che vogliamo assegnargli
    animal(int w, double sp){ //: weight(w), speed(sp){
        weight=w;
        speed=sp;
    }
    //todo: il costruttore scritto così, non chiama copy constructor dei campi ma fa un assegnamente.
    // Senza initializer list chiama comunque chiama default constructor dei vari campi e poi chiama operatori di assegnamento.
    // quindi invece di utilizzare direttamente copy constructor, usa default constructor mette i campi a 0 e poi cambia
    // da 0 al valore che io ho indicato. Quindi oltre ad essere più "righe di codice" è anche meno performante dell'utilizzo di initializer list


    animal(const animal& a): weight(a.weight),speed(a.speed){}
    //todo: per i campi in copy constructor di animal sto usando i copy constructor dei campi.
    // weight(a.weight) chiama il copy constructor di int.

    const int& weight() const { return weight;} //todo: Getter solo in posizione di right-value
    int& weight() {return weight;} //todo: posso usarlo anche come setter in posizione di left value


    int get_weight() const{ //todo: metto const su this perchè voglio funzione read-only che non modifichi niente
        return weight; //todo: omettiamo this
    }

    //todo: aggiungendo VIRTUAL alla firma del metodo PERMETTO OVERRIDE
    virtual void eat(const animal& a) { //todo: parametro meglio sempre provare prima a metterlo const & poi al massimo si toglie il const
        weight += a.weight;
    }

    //todo: avessi aggiunto al metodo il const dopo le tonde dei parametri, quel const si riferisce a this quindi mi
    // impedisce di modificare il this
    /*virtual void eat(const animal& a) const {
        weight += a.weight;
    }*/

};

//todo: dog avrebbe potuto ereditare in maniera public, private o protected.
// Se eredito in maniera PRIVATE -> subsumo solo io ma nessun altro può subsumere animal da dog
// Se eredito PUBLIC -> tutti possono subsumere animal da DOG
// se eredito Private-> subsumono solo i miei figli

class dog : public animal{
private:
    bool has_pedigree;
public:
    dog(int w, double sp, bool ped): animal(w,sp), has_pedigree(ped) {}

    //todo: ogni volta che metto virtual sulla firma permetto alle mie sottoclasse di overridare. non avendolo messo
    // qui significa che sto impedendo alle mie future sottoclassi di ereditare.
    void eat(const animal& a) override{
        weight += a.get_weight()/2; //todo: anche se è protected non posso richiamare a.weight perchè ???
    }
};


/*int get_weight(const animal& a){}*/ //todo: se io passo come parametro CONST -> posso invocare su di esso solo metodi o
                                      // campi const

int main() {

    //todo: posso costruire nuovi oggetti in due maniere tramite new quindi type* a = new type()
    animal* a1 = new animal(24,12.5);  //todo animal con new deve essere pointer perchè null ritorna pointer. IN HEAP
    int x; //todo: IN STACK
    animal a2(24,12.5);

    animal a3= animal(a2); //todo: risoluzione dell'overloading context-independent. Costruttore di default
    animal* a4 = new animal(a2);
    animal a5;
    a2.eat(*a1);
    free(a1);
    free(a4);
    return 0;
}

//todo: in C++ la sintassi di dichiarazione int x; ha la stessa sintassi di dichiarazione di C ma ha semantica diversa.
// in C++ anche quando dichiaro e basta COSTRUISCO -> int x; viene creato tramite costruttore di default ( invoca un
// costruttore diversamente da C) senza parametri ed inizializza a 0. IN STACK.
// sintassi di dichiarazione C tipo x; in C++ diventa tipo x(); invocando il default constructor