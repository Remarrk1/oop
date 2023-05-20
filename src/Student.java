import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
public interface Student {

    String getName();
    String getSurname();
    int getAge();
    int getCourse();
    String getSpecialization();
    int getYears_of_study();

    void setName(String Name);
    void setSurname(String Surname);
    void setAge(int Age) throws InvalidAgeException;
    void setCourse(int Course);
    void setSpecialization(String Specialization);
    void setYears_of_study(int Years_of_study);
    int Years_left();

    /*Writer и OutputStream - это классы в Java, предназначенные для записи данных в потоки.
    Writer: Это абстрактный класс, представляющий символьный поток. Он предоставляет методы для записи 
    символьных данных в поток.*/

    //Этот метод принимает объект OutputStream в качестве параметра.
    // Он записывает информацию о студенте в байтовый поток
    void output(OutputStream out) throws IOException;
    //Этот метод принимает объект Writer в качестве параметра. 
    //Он записывает информацию о студенте в символьный поток. 
    void write(Writer out);
    
}
