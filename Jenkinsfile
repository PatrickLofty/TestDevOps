pipeline {
    agent any

    stages {
        stage('checkout') {
            steps {
                git 'https://github.com/DonLofto/DevOpsProject.git'
            }
        }

        stage('Execute compile package Maven') {
            steps {
                // run maven on agent
                sh 'mvn clean'
                sh 'mvn compile'
                sh 'mvn install'
                sh 'mvn package'
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
