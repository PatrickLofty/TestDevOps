// this is a work in progress jenkinsfile
pipeline {
    agent any

    tools {
        // Define the JDK and Maven versions
        jdk 'JDK 17'
        maven 'Maven 3.9.5'
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from the Git repository
                git 'https://github.com/DonLofto/DevOpsCICD.git'
            }
        }

        stage('Build') {
            steps {
                // Build the project using Maven
                sh 'mvn clean install'
            }
        }

        stage('Generate War') {
            steps {
                // Generate the War file using Maven
                sh 'mvn package'
            }
        }

        stage('Archive') {
            steps {
                // Archive the War file
                archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
            }
        }
    }
}
