package org.semstertimetable

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SemesterModuleServiceSpec extends Specification {

    SemesterModuleService semesterModuleService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SemesterModule(...).save(flush: true, failOnError: true)
        //new SemesterModule(...).save(flush: true, failOnError: true)
        //SemesterModule semesterModule = new SemesterModule(...).save(flush: true, failOnError: true)
        //new SemesterModule(...).save(flush: true, failOnError: true)
        //new SemesterModule(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //semesterModule.id
    }

    void "test get"() {
        setupData()

        expect:
        semesterModuleService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SemesterModule> semesterModuleList = semesterModuleService.list(max: 2, offset: 2)

        then:
        semesterModuleList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        semesterModuleService.count() == 5
    }

    void "test delete"() {
        Long semesterModuleId = setupData()

        expect:
        semesterModuleService.count() == 5

        when:
        semesterModuleService.delete(semesterModuleId)
        sessionFactory.currentSession.flush()

        then:
        semesterModuleService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SemesterModule semesterModule = new SemesterModule()
        semesterModuleService.save(semesterModule)

        then:
        semesterModule.id != null
    }
}
