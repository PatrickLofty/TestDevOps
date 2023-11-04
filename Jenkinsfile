// this is a work in progress jenkinsfile
pipeline {
    agent any

    stages {
        stage('GetProject') {
            steps {
                git 'https://github.com/DonLofto/DevOpsProject.git'
            }
        }

        stage('Build') {
            steps {
                 // run maven on agent
                sh "mvn clean:clean"
                sh "mvn dependency:copy-dependencies"
                sh "mvn compiler:compile"

                //to run on windows use mvn clean and mvn compile

            }
        }

        stage('Execute') {
            steps {
                sh "mvn exec:java"
            }
        }
    }
}
