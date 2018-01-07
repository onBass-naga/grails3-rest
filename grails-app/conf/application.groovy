grails.plugin.springsecurity.userLookup.userDomainClassName = 'grails3.rest.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'grails3.rest.UserRole'
grails.plugin.springsecurity.authority.className = 'grails3.rest.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [
                pattern: '/**',
                access : ['ROLE_USER', 'ROLE_ADMIN']
        ]
]
grails.plugin.springsecurity.filterChain.chainMap = [
        //Stateless chain
        [
                pattern: '/api/**',
                filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'
        ]
]