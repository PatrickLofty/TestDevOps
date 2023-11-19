pipeline {
    agent any

    environment {
        // Define a unique directory name for the checkout
        WORKSPACE_DIR = "build_workspace_${env.BUILD_ID}"
        // Define a static tag for Docker image
        DOCKER_IMAGE_TAG = "latest"
    }

    stages {
        stage('Prepare Workspace') {
            steps {
                // Create a new directory
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
                        // Check if a Docker image with the name 'petition' and the specified tag already exists
                        def imageExists = sh(script: "docker images -q petition:${DOCKER_IMAGE_TAG}", returnStdout: true).trim()

                        if (imageExists) {
                            echo "Image petition:${DOCKER_IMAGE_TAG} already exists, skipping build."
                        } else {
                            // Build Docker image with a static tag
                            // Pull the latest Tomcat base image
                            sh "docker pull tomcat:10.1.15"
                            sh "docker build -t petition:${DOCKER_IMAGE_TAG} ."
                            echo "Built new image: petition:${DOCKER_IMAGE_TAG}"
                        }
                    }
                }
            }
        }

       stage('Build tomcat container') {
           steps {
               script {
                   // Check if a container with the name 'petition' exists
                   def existingContainer = sh(script: "docker ps -a --filter 'name=petition' -q", returnStdout: true).trim()
                   if (existingContainer) {
                       // Stop the existing container if it is running
                       sh "docker stop ${existingContainer}"
                       // Remove the existing container
                       sh "docker rm ${existingContainer}"
                       echo "Removed existing container with the name 'petition', container ID: ${existingContainer}"
                   }

                   // Run a new container since it doesn't exist or was removed
                   sh 'docker run -d --name petition -p 9091:9090 petition:${DOCKER_IMAGE_TAG}'
                   // Sleep to allow the application to initialize
                   echo "Waiting for the application to start..."
                   sleep(time: 15, unit: 'SECONDS')

                   // Check if the container is running
                   def runningContainer = sh(script: "docker ps --filter 'name=petition' -q", returnStdout: true).trim()
                   if (runningContainer) {
                       echo "Container 'petition' is now running, container ID: ${runningContainer}"
                       // Sleep again if needed after confirming the container is running
                       echo "Container started. Waiting for the application to become fully operational..."
                       sleep(time: 15, unit: 'SECONDS')
                   } else {
                       error "Container 'petition' did not start successfully"
                   }
               }
           }
       }

        stage('Verify Deployment') {
            steps {
                script {
                    def maxRetries = 5
                    def retryDelay = 10 // seconds
                    def success = false

                    for (int i = 0; i < maxRetries; i++) {
                        // Check if endpoint is responding
                        def responseCode = sh(script: "curl -m 10 -s -o response.txt -w '%{http_code}' http://localhost:9090/project", returnStdout: true).trim()
                        echo "Response Code: ${responseCode}"

                        if (responseCode.toInteger() >= 200 && responseCode.toInteger() < 300) {
                            echo "Container running and endpoint reached successfully."
                            success = true
                            break
                        } else if (responseCode == '404') {
                            echo "Container running but endpoint not reached, check URL."
                        } else if (responseCode == '302') {
                            echo "Redirect received, check if this is expected behavior."
                        } else {
                            echo "Unexpected response code. Response body:"
                            sh "cat response.txt"
                        }

                        if (i < maxRetries - 1) {
                            echo "Retrying in ${retryDelay} seconds..."
                            sleep(retryDelay)
                        }
                    }

                    if (!success) {
                        error "Deployment verification failed after ${maxRetries} attempts."
                    }
                }
            }
        }
    }

    post {
        always {
            // Clean up the workspace after the build is done
            sh "rm -rf ${WORKSPACE_DIR}"
        }
    }
}
