package org.semstertimetable

import grails.gorm.services.Service

@Service(SemesterModule)
interface SemesterModuleService {

    SemesterModule get(Serializable id)

    List<SemesterModule> list(Map args)

    Long count()

    void delete(Serializable id)

    SemesterModule save(SemesterModule semesterModule)

}