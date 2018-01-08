package grails3.rest

import grails.rest.Resource

@Resource
class Customer {
    String firstName
    String lastName
    String email
    Date birthday

    static constraints = {
        firstName size: 1..255, blank: false
        lastName size: 1..255, blank: false
        email email: true, blank: false, unique: true
        birthday nullable: true
    }
}
