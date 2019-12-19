package dargrall.semestertimetable

import grails.gorm.services.Service

@Service(SemesterModule)
interface SemesterModuleService {

    SemesterModule get(Long id)

    List<SemesterModule> list(Map args)

    Long count()

    void delete(Long id)

    SemesterModule save(SemesterModule semesterModule)

}