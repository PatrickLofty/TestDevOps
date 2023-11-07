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
                        sh 'mvn clean compile package'

                    }
                }
                post {
                    success {
                        archiveArtifacts allowEmptyArchive: true, artifacts: '**/target/*.war'
                    }
                }
            }

            stage('Docker Build') {
                steps {
                    dir("${WORKSPACE_DIR}") {
                        script {
                        // Check if any Docker container is using port 9090
                        def existingContainer = sh(script: "docker ps --filter 'publish=9090' -q", returnStdout: true).trim()
                        if (existingContainer) {
                            echo "Stopping and removing the existing container using port 9090."
                            sh 'docker stop ${existingContainer} && docker rm ${existingContainer}'
                        }
                        // Build Docker image
                        sh "docker build -t petition:${BUILD_NUMBER} ."
                        echo "Running new container from image petition:${BUILD_NUMBER}."
                        }
                    }
                }
            }

            stage('Run tomcat container') {
                steps {
                    // Run Docker container. This may require volume mounting if you need to persist data.

                    sh 'docker run -d -p 9090:9090 petition:${BUILD_NUMBER}'
                    sh 'apt update'
                    sh 'apt install lsof'
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
