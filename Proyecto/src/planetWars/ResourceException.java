package planetWars;

public class ResourceException extends Exception {

    /**
     * Constructor que acepta un mensaje descriptivo que explica la razón de la excepción.
     * @param message Mensaje descriptivo sobre la causa de la excepción.
     */
    public ResourceException(String message) {
        super(message);  // Llama al constructor de la superclase Exception.
    }
}