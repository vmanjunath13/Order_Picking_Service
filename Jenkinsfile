node {
    stage("clean workspace") {
        deleteDir()
    }
     stage("git checkout") {
        checkout scm

        def GIT_COMMIT = sh(returnStdout: true, script: "git rev-parse HEAD").trim().take(7)
        DOCKER_IMAGE_VERSION = "${BUILD_NUMBER}-${GIT_COMMIT}"
    }
    stage('Maven Build'){
        sh "/usr/bin/mvn clean install"
    }
     stage("Run tests"){
        sh 'mvn test -Dtest=OrderPickingServiceTests'
    }
    stage('docker Build Image'){
     sh 'docker build -t vaishnavi1319/order-picking-service-egen .'
    }
    stage('docker run'){
        sh 'docker container run --network order-picking-service --name order-picking-service$(DOCKER_IMAGE_VERSION) -d -p 8081:8080  vaishnavi1319/order-picking-service-egen'
    }
}