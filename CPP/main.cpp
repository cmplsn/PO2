#include <iostream>
using namespace std;

class animal{
private:
    int weight;
    double speed;

public:
    animal(int w, double sp) : weight(w), speed(sp){} //todo: costruttore: nome campo che vogliamo inizializzare e tra parentesi il valore che vogliamo assegnargli
    animal(const animal& a): weight(a.weight),speed(a.speed){}

    int get_weight(){
        return /*this->*/weight; //todo: omettiamo this
    }

    //todo: aggiungendo VIRTUAL alla firma del metodo PERMETTO OVERRIDE
    virtual void eat(const animal& a){//todo: parametro meglio sempre provare prima a metterlo const & poi al massimo si toglie il const
        weight += a.weight;
    }
};

//todo: dog avrebbe potuto ereditare in maniera public, private o protected.
// Se eredito in maniera PRIVATE -> subsumo solo io ma nessun altro può subsumere animal da dog
// Se eredito PUBLIC -> tutti possono subsumere animal da DOG
// se eredito Private-> subsumono solo i miei figli

class dog : public animal{


};

int get_weight(const animal& a){}//todo: se io passo come parametro CONST -> posso invocare su di esso solo metodi o
                                 // campi const

int main() {

    cout << "Hello, World!" << endl;
    return 0;
}
