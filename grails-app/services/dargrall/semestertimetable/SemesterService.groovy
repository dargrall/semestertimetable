package dargrall.semestertimetable

import grails.gorm.services.Service

@Service(Semester)
interface SemesterService {

    Semester get(Long id)

    List<Semester> list(Map args)

    Long count()

    void delete(Long id)

    Semester save(Semester semester)

}