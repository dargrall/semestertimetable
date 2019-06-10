package org.semstertimetable

class SemesterModule {
    int id
    String name
    int semesterId
    int credits

    static constraints = {
        name()
        semesterId()
        credits()
    }
}
