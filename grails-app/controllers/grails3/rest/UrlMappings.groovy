package grails3.rest

class UrlMappings {

    static mappings = {
        "/api/customers"(resources:"customer")
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
