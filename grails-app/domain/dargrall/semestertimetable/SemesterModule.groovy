package dargrall.semestertimetable

class SemesterModule {
    String name
    int credits
    Boolean completed
    Float grade
    static hasOne = [semester: Semester]
    static mapping = {
        sort name: "desc"
    }
    static constraints = {
        name()
        credits()
        completed nullable: true, default: false
        grade nullable: true
    }
}
