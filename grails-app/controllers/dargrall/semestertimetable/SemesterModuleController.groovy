package dargrall.semestertimetable

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import groovy.json.JsonSlurper

class SemesterModuleController {
    SemesterModuleService semesterModuleService

    /* Should be moved into a file after figuring out where to put that file and parse it in grails */
    def importedSemesterModules =
            [
                    "modules":  [
                        [
                            name: "Programmieren 1",
                            credits: 6
                        ],
                        [
                            name: "Algorithmen und Datenstrukturen",
                            credits: 8
                        ],
                        [
                            name: "Lineare Algebra",
                            credits: 10
                        ],
                        [
                            name: "Diskrete Mathematik",
                            credits: 5
                        ],
                        [
                            name: "Programmieren 2",
                            credits: 6
                        ],
                        [
                            name: "Logik",
                            credits: 5
                        ],
                        [
                            name: "Analysis",
                            credits: 10
                        ],
                        [
                            name: "Algebra",
                            credits: 5
                        ],
                        [
                            name: "Wissenschaftliches Arbeiten",
                            credits: 2
                        ],
                        [
                            name: "Ethik",
                            credits: 2
                        ],
                        [
                            name: "Software Engineering 1",
                            credits: 5
                        ],
                        [
                            name: "Theoretische Informatik 1",
                            credits: 5
                        ],
                        [
                            name: "Betriebssysteme",
                            credits: 5
                        ],
                        [
                            name: "Relationale Datenbanksysteme 1",
                            credits: 5
                        ],
                        [
                            name: "Numerik",
                            credits: 5
                        ],
                        [
                            name: "Unternehmensführung und Marketing",
                            credits: 6
                        ],
                        [
                            name: "SEP",
                            credits: 7
                        ],
                        [
                            name: "Theoretische Informatik 2",
                            credits: 6
                        ],
                        [
                            name: "Computernetze 1",
                            credits: 5
                        ],
                        [
                            name: "Technische Informatik",
                            credits: 5
                        ],
                        [
                            name: "Seminar",
                            credits: 5
                        ],
                        [
                            name: "Teamprojekt",
                            credits: 5
                        ],
                        [
                            name: "Einführung i.d. IT-Sicherheit",
                            credits: 5
                        ],
                        [
                            name: "Bachelorarbeit",
                            credits: 12
                        ],
                        [
                            name: "Literaturrecherche",
                            credits: 2
                        ]
                    ]
            ]
            

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

    def importSemesterModules() {
        importedSemesterModules.modules.each {
            def newSemesterModule = new SemesterModule(it)
            newSemesterModule.save()
        }
        redirect controller: "semester", action: "timetable"
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

}
