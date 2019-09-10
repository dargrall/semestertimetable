package dargrall.semestertimetable

class Semester {
    int number
    String name
    Date start
    Date end
    static hasMany = [modules: SemesterModule]
    static constraints = {
        name()
        number()
        start nullable: true
        end nullable: true
        modules nullable: true
    }
}
