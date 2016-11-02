stage("SCM Checkout") {
    node {
        checkout scm
    }
}

stage("Unit Tests") {
    node {
        mvn 'test'
        junit '**/target/surefire-reports/TEST-*.xml'
    }
}

milestone 1
stage("Deploy Integration Environment") {
    lock(resource: 'integration-server', inversePrecedence: true) {
        node {
            mvn 'jboss-as:deploy'
        }
    }
}

def mvn(args) {
    withEnv(["PATH+MAVEN=${tool 'Maven 3.x'}/bin"]) {
        sh "mvn $args"
    }
}

def npm(args) {
    withEnv(["PATH+NODE=${tool 'NodeJS 7.x'}/bin"]) {
        sh "npm $args"
    }
}
