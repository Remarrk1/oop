public class InvalidCourseException extends RuntimeException{
    public InvalidCourseException(int Course) {
        super("Course cannot be negative! You have entered = " + Course);
    }
}
