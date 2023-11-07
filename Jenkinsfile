pipeline {
    agent any

    environment {
        // Define a unique directory name for the checkout using the BUILD_ID
        WORKSPACE_DIR = "build_workspace_${env.BUILD_ID}"
    }

    stages {
        stage('Prepare Workspace') {
            steps {
                // Create a new directory based on the BUILD_ID
                sh 'mkdir -p ${WORKSPACE_DIR}'
            }
        }

        stage('checkout') {
            steps {
               // Clone the repository into the unique directory
               sh 'git clone https://github.com/DonLofto/DevOpsProject.git ${WORKSPACE_DIR}'
            }
        }

        stage('Execute compile package Maven') {
            steps {
                // Change directory to the unique workspace
                dir("${WORKSPACE_DIR}") {
                    // Run maven commands in the unique directory
                    sh 'mvn clean compile install package'
                    sh 'mvn spring-boot:run'
                }
            }
        }

        stage('Spring boot run') {
            steps {
                    sh 'mvn spring-boot:run'
                }
            }
        }

        stage('Docker Build') {
            steps {
                dir("${WORKSPACE_DIR}") {
                    // Build Docker image in the unique directory
                    sh 'docker build -t petition:${BUILD_NUMBER} .'
                }
            }
        }

        stage('Run tomcat container') {
            steps {
                // Run Docker container. This may require volume mounting if you need to persist data.
                sh 'docker run -d -p 9090:8080 petition:${BUILD_NUMBER}'
            }
        }
    }
    post {
        always {
            // Clean up the workspace after the build is done
            sh 'rm -rf ${WORKSPACE_DIR}'
        }
    }
}
