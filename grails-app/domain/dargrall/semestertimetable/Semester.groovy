package dargrall.semestertimetable

class Semester {
    Long id
    Date start
    Date end
    List<Long> moduleIds = []
    static constraints = {
        id()
        start nullable: true
        end nullable: true
        moduleIds nullable: true
    }

    String toString() {
        String name = id.toString() + ". " + "Semester"
        return name
    }
}
