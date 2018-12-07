
package to.garazuj.exception;

public class FileResizeException extends RuntimeException {

    public FileResizeException(String message) {
        super(message);
    }

    public FileResizeException(String message, Throwable cause) {
        super(message, cause);
    }
}