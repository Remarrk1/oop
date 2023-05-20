public class InvalidAgeException extends Exception {
    public InvalidAgeException(int Age) {
        super("Age cannot be negative! You have entered = " + Age);
    }
}
