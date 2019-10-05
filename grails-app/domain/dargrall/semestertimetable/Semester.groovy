package dargrall.semestertimetable

class Semester {
    Date start
    Date end
    static hasMany = [modules: SemesterModule]
    static constraints = {
        id()
        start nullable: true
        end nullable: true
        modules nullable: true
    }
}
