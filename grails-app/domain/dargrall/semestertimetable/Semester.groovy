package dargrall.semestertimetable

class Semester {
    Date start
    Date end
    List<SemesterModule> modules
    static embedded = ['modules']
    static constraints = {
        id()
        start nullable: true
        end nullable: true
        modules nullable: true
    }

    String toString() {
        String name = id.toString() + ". " + "Semester"
        return name
    }
}
