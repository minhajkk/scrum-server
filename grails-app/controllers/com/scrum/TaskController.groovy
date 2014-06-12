package com.scrum

import grails.rest.RestfulController
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*
import static com.scrum.ScrumUtil.EXCLUDE_FIELD_LIST

class TaskController extends RestfulController<Task>{
    static responseFormats = ['json', 'xml']

    def springSecurityService;

    TaskController() {
        super(Task)
    }

    @Override
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Project.list(params), model:[projectInstanceCount: Project.count()]
    }

    @Transactional
    def save() {
        def taskInstance = createResource(params);

        if (taskInstance == null) {
            notFound()
            return
        }

        taskInstance.reportedBy = springSecurityService.getCurrentUser();

        taskInstance.validate()
        if (taskInstance.hasErrors()) {
            respond taskInstance.errors
            return
        }

        taskInstance.save flush:true

        respond queryForResource(taskInstance.id), [status: CREATED, excludes: EXCLUDE_FIELD_LIST]
    }

    def list(){
        respond Project.list(), model:[projectInstanceCount: Project.count()]
    }

    def show(Project prj) {
        if(prj){
            respond queryForResource(prj.id), [excludes: EXCLUDE_FIELD_LIST]
        }

        render status: NOT_FOUND

    }
}
