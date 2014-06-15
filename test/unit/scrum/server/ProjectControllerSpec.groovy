package scrum.server

import com.scrum.Project
import com.scrum.ProjectController
import com.scrum.auth.User
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created with IntelliJ IDEA.
 * User: techtalk
 * Date: 6/12/14
 * Time: 6:36 PM
 * To change this template use File | Settings | File Templates.
 */
@TestFor(ProjectController)
@Mock([Project, User])
class ProjectControllerSpec extends Specification {

    void "Test show method"() {
        given:"A project instance"
        def projectInstance = new Project(name: "Project", description:"desc", createdBy: new User(username: "user1", password: "test123#")).save(flush: true)

        when : "Make a get request"
        response.format = 'json'
        request.method = 'GET'
        controller.params.id = projectInstance.id
        controller.show()

        then:"A project response should be returned in json format"
        response.json.name == "Project"
    }

    void "Test save method"() {
        given:"A project instance with logged in user"
        def loggedInUser = new User(username: "user1", password: "test123#").save()
        controller.springSecurityService = [
                encodePassword: 'test123#',
                reauthenticate: { String u -> true},
                loggedIn: true,
                getCurrentUser: { loggedInUser }]

        when : "Make a get request"
        request.method = 'POST'
        controller.params.name = "Project xyz"
        controller.params.description = "This is project description"
        controller.save()

        then:"A project response should be returned in json format"
        //response.status = 203
        response.json.name == "Project xyz"
        response.json.description == "This is project description"
    }
}