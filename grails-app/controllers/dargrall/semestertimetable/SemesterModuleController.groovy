package dargrall.semestertimetable

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SemesterModuleController {

    SemesterModuleService semesterModuleService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond semesterModuleService.list(params), model:[semesterModuleCount: semesterModuleService.count()]
    }

    def show(Long id) {
        respond semesterModuleService.get(id)
    }

    def create() {
        respond new SemesterModule(params)
    }

    def save(SemesterModule semesterModule) {
        if (semesterModule == null) {
            notFound()
            return
        }

        try {
            semesterModuleService.save(semesterModule)
        } catch (ValidationException e) {
            respond semesterModule.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'semesterModule.label', default: 'SemesterModule'), semesterModule.id])
                redirect semesterModule
            }
            '*' { respond semesterModule, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond semesterModuleService.get(id)
    }

    def update(SemesterModule semesterModule) {
        if (semesterModule == null) {
            notFound()
            return
        }

        try {
            semesterModuleService.save(semesterModule)
        } catch (ValidationException e) {
            respond semesterModule.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'semesterModule.label', default: 'SemesterModule'), semesterModule.id])
                redirect semesterModule
            }
            '*'{ respond semesterModule, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        semesterModuleService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'semesterModule.label', default: 'SemesterModule'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'semesterModule.label', default: 'SemesterModule'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    // Displays Semestertimetable
    def semestertable() {
            respond semesterModuleService.list(params), model:[semesterModuleCount: semesterModuleService.count()]
    }
}
