stage("SCM Checkout") {
    node {
        checkout scm
    }
}

def mvn(args) {
    withEnv(["PATH+MAVEN=${tool 'Maven 3.x'}/bin"]) {
        sh "mvn $args"
    }
}

def npm(args) {
    withEnv(["PATH+NODE=${tool 'NodeJS'}/bin"]) {
        sh "npm $args"
    }
}
