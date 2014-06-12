class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')

        // RESTService api
        "/v1/api/project"(resources: 'project')
        "/v1/api/comment"(resources: 'comment')
        "/v1/api/task"(resources: 'task')
	}
}
