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
        npm 'install --prefix frontend frontend'
        npm 'run test-once --prefix frontend'
    }
}

milestone 1
stage("Deploy Integration") {
    lock(resource: 'integration-server', inversePrecedence: true) {
        node {
            mvn '-f backend jboss-as:deploy-only'
        }
    }
}

def mvn(args) {
    withEnv(["PATH+MAVEN=${tool 'Maven 3.x'}/bin"]) {

      configFileProvider(
        [configFile(fileId: 'simple-contact-list-settings', variable: 'MAVEN_SETTINGS')]) {
        sh "mvn -s $MAVEN_SETTINGS $args"
    }
}

def npm(args) {
    withEnv(["PATH+NODE=${tool 'NodeJS 7.x'}/bin"]) {
        sh "npm $args"
    }
}
