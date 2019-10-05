package dargrall.semestertimetable

import grails.validation.ValidationException
import org.springframework.http.HttpStatus

import static org.springframework.http.HttpStatus.*

class SemesterController {

    SemesterService semesterService
    SemesterModuleService semesterModuleService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond semesterService.list(params), model:[semesterCount: semesterService.count()]
    }

    def show(Long id) {
        respond semesterService.get(id)
    }

    def create() {
        respond new Semester(params)
    }

    def save(Semester semester) {
        if (semester == null) {
            notFound()
            return
        }

        try {
            semesterService.save(semester)
        } catch (ValidationException e) {
            respond semester.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'semester.label', default: 'Semester'), semester.id])
                redirect semester
            }
            '*' { respond semester, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond semesterService.get(id)
    }

    def update(Semester semester) {
        if (semester == null) {
            notFound()
            return
        }

        try {
            semesterService.save(semester)
        } catch (ValidationException e) {
            respond semester.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'semester.label', default: 'Semester'), semester.id])
                redirect semester
            }
            '*'{ respond semester, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        semesterService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'semester.label', default: 'Semester'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    def timetable(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def semesterList = semesterService.list(params)
        Set assignedModules = []
        def totalCredits = 0
        semesterList.each{
            def semesterCredits = 0
            it.modules.each{
                assignedModules.add(it.id)
                semesterCredits += it.credits
            }
            totalCredits += semesterCredits
            it.credits = semesterCredits
        }
        def semesterModuleList = SemesterModule.list()
        def availableModules = []

        semesterModuleList.each {
            if (!(it.id in assignedModules)) {
                availableModules.push(it)
            }
        }

        respond semesterList, model:[semesterCount: semesterService.count(), semesterModuleList: availableModules, totalCredits: totalCredits], view: '/timetable'
    }

    def addModule() {
        def semester = semesterService.get(params.semesterId)
        def module = semesterModuleService.get(params.moduleId)
        semester?.addToModules(module)
        try {
            semesterService.save(semester)
            render contentType: "application/json", text: '{"response": "Module successfully added"}', status: OK
            return
        } catch(Exception e) {
            render contentType: "application/json", text:  '{"response": "Module could not be added"}', status: BAD_REQUEST
        }
    }

    def removeModule() {
        def semester = semesterService.get(params.semesterId)
        def module = semesterModuleService.get(params.moduleId)
        semester?.removeFromModules(module)
        try {
            render contentType: "application/json", text: '{"response": "Module successfully removed"}', status: OK
            return
        } catch(Exception e) {
            render contentType: "application/json", text:  '{"response": "Module could not be removed"}', status: BAD_REQUEST
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'semester.label', default: 'Semester'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
