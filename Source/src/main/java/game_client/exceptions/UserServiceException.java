package game_client.exceptions;

public class UserServiceException extends RuntimeException {

    private static final long serialVersionUID = -6328286661536343936L;

    public UserServiceException() {
        super();
    }

    public UserServiceException(String message) {
        super(message);
    }

}
