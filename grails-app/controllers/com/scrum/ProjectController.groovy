package com.scrum

import grails.rest.RestfulController
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*
import static com.scrum.ScrumUtil.EXCLUDE_FIELD_LIST

class ProjectController extends RestfulController<Project>{

    static responseFormats = ['json', 'xml']

    def springSecurityService;

    ProjectController() {
        super(Project)
    }

    @Override
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Project.list(params), model:[projectInstanceCount: Project.count()]
    }

    @Transactional
    def save() {
        log.info("Project save action called......");
        def projectInstance = createResource(params);
        if (projectInstance == null) {
            notFound()
            return
        }

        projectInstance.createdBy = springSecurityService.getCurrentUser()
        if (projectInstance.hasErrors()) {
            respond projectInstance.errors, view:'create'
            return
        }

        projectInstance.save flush:true

        //respond projectInstance, [status: CREATED]
        respond queryForResource(projectInstance.id), [excludes: EXCLUDE_FIELD_LIST]
    }

    def list(){
        respond Project.list(), model:[projectInstanceCount: Project.count()]
    }

    def show(Project prj) {
        if(prj){
            respond queryForResource(prj.id), [excludes: EXCLUDE_FIELD_LIST]
            //respond prj
        }

        render status: NOT_FOUND

    }
}
