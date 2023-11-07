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
                sh 'rm -rf * ${WORKSPACE_DIR}'
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
                    sh 'mvn clean compile package'

                }
            }
            post {
                success {
                    archiveArtifacts allowEmptyArchive: true, artifacts: '**/target/*.war'
                }
            }
        }

       //stage('Spring boot run') {
     //       steps {
       //             sh 'mvn spring-boot:run'
       //         }
       //     }
       // }

        stage('Docker Build') {
            steps {
                dir("${WORKSPACE_DIR}") {
                    script {
                        // Check if the Docker container is running
                        def containerRunning = sh(script: "docker ps -q --filter 'name=petition'", returnStdout: true).trim()
                        // If the container ID is not empty, it's running
                        if (containerRunning) {
                            // Stop and remove the running container
                            sh "docker stop ${containerRunning} && docker rm ${containerRunning}"
                        } else {
                            // Build the Docker image if the container is not running
                            sh "docker build -t petition:${BUILD_NUMBER} ."
                            // Run the new Docker container
                            sh "docker run -d --name petition -p 9090:9090 petition:${BUILD_NUMBER}"
                        }
                    }
                }
            }
        }




        stage('Run tomcat container') {
            steps {
                // Run Docker container. This may require volume mounting if you need to persist data.
                sh 'docker run -d -p 9090:9090 petition:${BUILD_NUMBER}'
            }
        }
    }
    post {
        always {
            // Clean up the workspace after the build is done
            sh 'rm -rf * ${WORKSPACE_DIR}'
        }
    }
}
