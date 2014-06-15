package com.scrum

import grails.rest.RestfulController
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*
import static com.scrum.ScrumUtil.EXCLUDE_FIELD_LIST

/**
 * Controller expose the API to support CRUD operation on @code Project
 */
class ProjectController extends RestfulController<Project>{

    static responseFormats = ['html', 'json', 'xml']

    def springSecurityService;

    /**
     * Default constructor with 0 parameter.
     */
    ProjectController() {
        super(Project)
    }

    /**
     * Lists all the projects.
     *
     * @param max  maximum projects to return
     * @return  xml/json accordint to mime type.
     */
    @Override
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Project.list(params), view: 'index', model:[params:params, projectInstanceCount: Project.count()]
    }

    @Transactional
    @Override
    def save() {
        log.info("Project save action called......");
        def projectInstance = createResource(params);
        if (projectInstance == null) {
            notFound()
            return
        }

        projectInstance.createdBy = springSecurityService.getCurrentUser()
        projectInstance.validate()
        if (projectInstance.hasErrors()) {
            respond projectInstance.errors, view:'create'
            return
        }

        projectInstance.save flush:true

        flash.message = "Project has been created."

        respond listAllResources(params), view: 'index'
    }

    def list(){
        respond Project.list(), model:[projectInstanceCount: Project.count()], view: 'index'
    }

    def show(Project project) {

        if(!project){
            def model = [ success: false, msg: [ code: 404, text: "The Project could not be found"]]
            // 404 status for all, JSON/XML/HTML appropriate response with detail
            respond model as Object, [model: model, status: 404, view: 'error404']
        }else{
            respond (project, model: [projectInstance: project], view: 'show')
            return
        }

        render status: NOT_FOUND
    }

    @Transactional
    def delete() {
        println("delete is being called.....");
        if(handleReadOnly()) {
            return
        }

        def instance = queryForResource(params.id)
        if (instance == null) {
            notFound()
            return
        }

        instance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: "${resourceClassName}.label".toString(), default: resourceClassName), instance.name])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT } // NO CONTENT STATUS CODE
        }
    }



}
