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
                        echo "Stop all running containers"
                            sh "docker stop \$(docker ps -aq)"
                        echo "Remove all containers"
                            sh "docker rm -f \$(docker ps -aq)"
                        echo "Build Docker image"
                            sh "docker build -t petition:${BUILD_NUMBER} ."
                        echo "Built new image: petition:${BUILD_NUMBER}"
                        }
                    }
                }
            }

            stage('Run tomcat container') {
                steps {
                    script{
                        echo "Run tomcat container"
                         sh 'docker run -d -p 9090:9090 petition:${BUILD_NUMBER}'
                        // Check if the container is running
                        def isRunning = sh(script: "docker ps | grep petition:${BUILD_NUMBER}", returnStatus: true)
                        if (isRunning) {
                            echo "Container is running"
                        } else {
                            error "Container is not running"
                        }
                    }
                }
            }

            stage('Verify Deployment') {
                steps {
                    script {
                        // Check if endpoint is responding
                            def responseCode = sh(script: "curl -s -o /dev/null -w '%{http_code}' http://localhost:9090/project", returnStdout: true).trim()
                        echo "Response Code: ${responseCode}"
                            if (responseCode == '404') {
                                error "Container running but endpoint not reached, check URL"
                            } else if (responseCode == '302') {
                                echo "Redirect received, check if this is expected behavior"
                            } else {
                                echo "Container running and endpoint reached"
                            }
                    }
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
