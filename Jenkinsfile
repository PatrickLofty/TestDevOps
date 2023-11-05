pipeline {
    agent any

   tools
    {
       maven "Maven"
    }
    stages {
        stage('checkout') {
            steps {
                git 'https://github.com/DonLofto/DevOpsProject.git'
            }
        }

        stage('Execute Maven') {
            steps {
                 // run maven on agent
                sh 'mvn package'

                //to run on windows use mvn clean and mvn compile

            }
        }

        stage('Docker Build') {
                   steps {

                        sh 'docker build -t devopsapp:latest .'

                  }
        }
        stage('Run Docker container on Jenkins Agent') {

                    steps
           {
                        sh "docker run -d -p 9090:8080 devopsapp"
}
