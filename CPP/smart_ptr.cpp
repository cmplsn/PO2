//
// Created by Alessandro on 16/05/24.
//
#include <iostream>


//todo: nativi STL si chiamano shared_ptr<T>. Pointer speciali che hanno Reference-counting, ovvero tiene il conto del numero
// di volte che viene referenced.
// Posso fare esattamente le stesse cose che posso fare con puntatori normali
template<class T>
class smart_ptr{
private:

    T* pt;
    const T* orig;
    unsigned int* count;

public:

    explicit smart_ptr(T* _pt): pt(_pt), orig(_pt), count(new unsigned int(1)) {}

    smart_ptr(const smart_ptr<T>& sp):pt(sp.pt),count(sp.count) {
        ++ *count;
    }

    ~smart_ptr(){
        if(-- *count==0) delete pt; delete count;

    }

     const T& operator*() const{
        return *pt;
    }

    T& operator*() {
        return *pt;
    }

    smart_ptr<T>& operator++(){
        ++pt;
        return *this;

    }

    smart_ptr<T> operator++(int){
        smart_ptr<T> r(*this);
        ++pt;
        return r;
    }

};

/*int main(){
    int* p;
    smart_ptr<int> sp(p);
    return 0;
}*/