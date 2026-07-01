package my_project.DistanceOnEarth.exceptions;

public class AmbiguousAddressException extends RuntimeException{
    public AmbiguousAddressException(String message){
        super("Для адреса: " + message + " найдено несколько координат или 0");
    }
}
