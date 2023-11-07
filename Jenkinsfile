pipeline {
    agent any

    stages {
        stage('checkout') {
            steps {
               sh 'git clone https://github.com/DonLofto/DevOpsProject.git'
               sh 'cd DevOpsProject'
            }
        }

        stage('Execute compile package Maven') {
            steps {
                // run maven on agent
                sh 'rm -rf *'
                sh 'mvn clean compile install package'
                sh 'mvn spring-boot:run'


            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t petition:${BUILD_NUMBER} .'
            }
        }

        stage('Run tomcat container') {
            steps {
                sh 'docker run -d -p 9090:8080 petition'
            }
        }
    }
}
