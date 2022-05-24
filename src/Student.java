public class Student {
    private int nrow; // номер строки
    private String lesson; // Предмет
    private String name; // ФИО
    private String status; // Статус участия
    private String school; // Образорвательное Учреждение
    private int nclass; // Класс
    private String district; // Район

    public Student() {
    }

    public Student(int nrow, String lesson, String name, String status, String school, int nclass, String district) {
        this.nrow = nrow;
        this.lesson = lesson;
        this.name = name;
        this.status = status;
        this.school = school;
        this.nclass = nclass;
        this.district = district;
    }

    @Override
    public String toString() {
        return "Student{" +
                "nrow=" + nrow +
                ", lesson='" + lesson + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", school='" + school + '\'' +
                ", nclass=" + nclass +
                ", district='" + district + '\'' +
                "}";
    }

    public int getNrow() {
        return nrow;
    }

    public void setNrow(int nrow) {
        this.nrow = nrow;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getNclass() {
        return nclass;
    }

    public void setNclass(int nclass) {
        this.nclass = nclass;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
