import java.io.*;
import java.util.Objects;
import java.io.Serializable;
import java.io.IOException;
//Чтобы класс применил интерфейс, надо использовать ключевое слово implements
//класс Бакалавр реализует интерфейс Студент
public class Bachelor implements Student, Serializable {

    private String Name;
    private String Surname;
    private int Age;
    private int Course;
    private String Specialization;
    private int Years_of_study;


    // Конструкторы
    //конструктор по умолчанию
    public Bachelor() {
        this.Name="Sasha";
        this.Surname="Galchenko";
        this.Age=20;
        this.Course=3;
        this.Specialization="MOAIS";
        this.Years_of_study=4;

    }
    //конструктор с параметрами
    public Bachelor(String Name, String Surname,int Age, int Course, String Specialization, int Years_of_study){
        this.Name=Name;
        this.Surname=Surname;
        this.Age=Age;
        this.Course=Course;
        this.Specialization=Specialization;
        this.Years_of_study=Years_of_study;
    }
    //методы доступа
    @Override
    public String getName() {
        return Name;
    }
    @Override
    public String getSurname() {
        return Surname;
    }
    @Override
    public int getAge() {
        return Age;
    }
    @Override
    public int getCourse() {
        return Course;
    }
    @Override
    public String getSpecialization() {
        return Specialization;
    }

    @Override
    public int getYears_of_study() {
        return Years_of_study;
    }

    @Override
    public void setName(String Name) {
        this.Name=Name;
    }

    @Override
    public void setSurname(String Surname) {
        this.Surname=Surname;
    }

    @Override
    // Объявляемое исключение. Возраст не может быть отрицательным
    public void setAge(int Age) throws InvalidAgeException {
        if (Age < 0) {
            throw new InvalidAgeException(Age);
        }
        this.Age=Age;
    }
    // Необъявляемое исключение. Курс не может быть отрицательным
    @Override
    public void setCourse(int Course){
        if (Course < 0) {
            throw new InvalidCourseException(Course);
        }
        this.Course=Course;
    }

    @Override
    public void setSpecialization(String Specialization) {
        this.Specialization=Specialization;
    }

    @Override
    public void setYears_of_study(int Years_of_study) {
        this.Years_of_study=Years_of_study;

    }

    // Функциональный метод возвращает число лет, которое осталось учиться студенту
    public int Years_left() {
        return (int) Years_of_study-Course;
    }

    // реализация метода output из интерфейса Student
    /*Метод output(OutputStream out) использует класс ObjectOutputStream 
    для записи текущего объекта Bachelor в байтовый поток out. */
    @Override
    public void output(OutputStream out) throws IOException  {
        ObjectOutputStream oos=new ObjectOutputStream(out);
        oos.writeObject(this);
    }
    // реализация метода write из интерфейса Student
    /*Метод write(Writer out) использует класс BufferedWriter для записи каждого 
    атрибута студента в символьный поток out, разделяя их с помощью пробела.*/
    @Override
    public void write(Writer out) {
       PrintWriter wr=new PrintWriter(out);
       wr.print(Name + " ");
       wr.print(Surname + " ");
       wr.print(Age + " ");
       wr.print(Specialization + " ");
       wr.print(Course + " ");
       wr.print(Years_of_study + " ");
       wr.flush();
    }

    @Override
    //The toString() метод возвращает строковое представление объекта.
    //переопределяем toString() метод для получения желаемого строкового представления
    public String toString() {
        return "Bachelor{" +
                "Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", Age=" + Age +
                ", Course=" + Course +
                ", Specialization='" + Specialization + '\'' +
                ", Years_of_study=" + Years_of_study +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bachelor bachelor = (Bachelor) o;
        return Age == bachelor.Age && Course == bachelor.Course && Double.compare(bachelor.Years_of_study, Years_of_study) == 0 && java.util.Objects.equals(Name, bachelor.Name) && java.util.Objects.equals(Surname, bachelor.Surname) && java.util.Objects.equals(Specialization, bachelor.Specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, Surname, Age, Course, Specialization, Years_of_study);
    }

    // Статический метод записи в байтовый поток
    /*Созданный ObjectOutputStream используется для записи объекта student в поток с помощью метода writeObject */
    public static void outputST(Student student, OutputStream out) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(student);
        // Метод writeObject используется для записи объекта в поток. 
        //Он преобразует объект в последовательность байтов и записывает их в поток.
    }

    // Статический метод чтения из байтового потока
    public static Student inputST(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(in);
        return (Student) ois.readObject();
        // Метод readObject используется для чтения объекта из потока. 
        //Он считывает последовательность байтов из потока и восстанавливает объект из этих данных.
    }

    // Статический метод записи в символьный поток
    public static void writeST(Student student, Writer out) throws IOException {
        student.write(out);
        //Метод write обычно используется для записи объекта в символьный поток (Writer).
        // Он выполняет преобразование объекта в текстовый формат и записывает его в поток.
    }
    public static Bachelor readST(Reader in) throws IOException {
        StreamTokenizer st = new StreamTokenizer(in);
        st.nextToken();
        String name = st.sval;
        st.nextToken();
        String surname = st.sval;
        st.nextToken();
        int age = (int) st.nval;
        st.nextToken();
        int course = (int) st.nval;
        st.nextToken();
        String specialization = st.sval;
        st.nextToken();
        int yearsOfStudy = (int) st.nval;
        return new Bachelor(name, surname, age, course, specialization, yearsOfStudy);
        /*StreamTokenizer - это класс в Java, который используется для разбора потока символов на токены 
        (например, слова, числа, знаки препинания и т. д.).
        Он предоставляет простой способ чтения и разделения символов или строк на более мелкие элементы. 
        sval - поле типа String, которое содержит значение текущего токена, если токен является строкой (словом).
        nval - поле типа double, которое содержит значение текущего токена, если токен является числом.*/
    }

}
