pipeline {
    agent any

    stages {
        stage('checkout') {
            steps {
                git 'https://github.com/DonLofto/DevOpsProject.git'
            }
        }

        stage('Execute Maven') {
            steps {
                // run maven on agent
                sh 'mvn clean package'
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
