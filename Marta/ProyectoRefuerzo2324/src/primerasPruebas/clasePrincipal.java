package primerasPruebas;

public class clasePrincipal {

    public static void main(String[] args) {
        ClaseA miObjetoA1 = new ClaseA();
        ClaseA miObjetoA2 = new ClaseA();
        miObjetoA1.setMensaje("Soy el objeto 1");
        miObjetoA2.setMensaje("Soy el objeto 2");
        miObjetoA1.saludar();
        miObjetoA2.saludar();
        miObjetoA1.mensaje = "Cambio de mensaje en el objeto 1";
        miObjetoA1.saludar();
    }

}