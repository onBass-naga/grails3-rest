package grails3.rest

import com.google.common.base.CaseFormat
import grails.converters.JSON
import grails.core.GrailsDomainClassProperty
import grails.util.Holders

class BootStrap {

    def init = { servletContext ->
        println("=========================================")
        println("======    BootStrap           ===========")
        println("=========================================")

        def adminRole = new Role('ROLE_ADMIN').save(flush: true)
        def adminUser = new User('demo', 'demo').save(flush: true)
        UserRole.create adminUser, adminRole
        UserRole.withSession {
            it.flush(); it.clear()
        }

        def userRole = new Role('ROLE_USER').save(flush: true)
        def testUser = new User('me', 'password').save(flush: true)
        UserRole.create testUser, userRole
        UserRole.withSession {
            it.flush(); it.clear()
        }

        assert User.count() == 2
        assert Role.count() == 2
        assert UserRole.count() == 2

        new Customer([
            firstName: 'Cornelia',
            lastName: 'Bryant',
            email: 'cornelia_bryant@example.com',
            birthday: new Date('1980/09/13 00:00:00')
        ]).save(flush: true)

        new Customer([
            firstName: 'Ann',
            lastName: 'Miles',
            email: 'ann_miles@example.com',
            birthday: new Date('1991/03/21 00:00:00')
        ]).save(flush: true)

        new Customer([
            firstName: 'Rosalie',
            lastName: 'Curtis',
            email: 'rosalie_curtis@example.com',
            birthday: new Date('1985/07/07 00:00:00')
        ]).save(flush: true)


        def grailsApplication = Holders.applicationContext.getBean('grailsApplication')

        JSON.registerObjectMarshaller(SnakeCase, { SnakeCase o ->

            def result = [:]
            if (grailsApplication.isDomainClass(o.class)) {
                result['id'] = o['id']
                def domainClass=  grailsApplication.getDomainClass(o.class.name)
                domainClass.persistentProperties.each { GrailsDomainClassProperty p ->
                    def name = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, p.name)
                    result[name] = o[p.name]
                }
            } else {
                o.properties.each {
                    def name = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, it.key as String)
                    result[name] = it.value
                }
            }

            return result
        })
    }

    def destroy = {
    }
}
