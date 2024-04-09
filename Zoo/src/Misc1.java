public class Misc1 {
    public interface I{
        void a();
        void b();

        default void c(){   //todo: default in interfaccia corrisponde in creare metodo
                            // NON astratto in classe astratta
            a();
            b();
        }
    }

    //todo: nel caso in cui io implementi I devono obbligatoriamente implementare solo metodi NON default

    public static abstract class J{ //todo: classe astratta con soli metodi astratti = interfaccia

        public abstract void a();

        public abstract void b();

        public void c(){
            a();
            b();
        }
    }

    public static class C implements I{ // todo: C è sottotipo di I

        @Override
        public void a() {

        }

        @Override
        public void b() {

        }
    }
    public static void main(String[] args){

    }
}
