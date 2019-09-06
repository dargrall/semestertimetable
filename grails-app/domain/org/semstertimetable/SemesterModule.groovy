package org.semstertimetable

class SemesterModule {
    String name
    int semesterId
    int credits    

    static constraints = {
        name()
        semesterId()
        credits()
    }
}
