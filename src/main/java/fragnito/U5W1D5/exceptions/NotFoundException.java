package fragnito.U5W1D5.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        System.out.println("Elemento con id: " + id + " non trovato.");
    }
}
