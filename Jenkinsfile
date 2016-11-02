stage("SCM Checkout") {
    node {
        checkout scm
    }
}

stage("UnitTets") {
    node {
        mvn 'test'
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
