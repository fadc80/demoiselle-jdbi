#!groovy
stage("SCM Checkout") {
    node {
        checkout scm
    }
}

stage("Unit Tests") {
    node {
        mvn '-f backend clean package'
        junit '**/target/surefire-reports/TEST-*.xml'
    }
}

milestone 1
stage("Deploy Integration Environment") {
    lock(resource: 'integration-server', inversePrecedence: true) {
        node {
            mvn '-f backend jboss-as:deploy-only'
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
