pipeline {
    agent any

    environment {
        IMAGE_NAME = "buku-service"
    }

    stages {
        stage('Checkout') {
            steps {
                echo "📥 Checkout branch: ${env.BRANCH_NAME}"
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def imageTag = "${IMAGE_NAME}:${env.BUILD_NUMBER}"

                    sh """
                        docker build -t ${imageTag} .
                        docker tag ${imageTag} ${IMAGE_NAME}:latest
                    """

                    echo "✅ Docker image built: ${imageTag}"
                }
            }
        }
    }

    post {
        success {
            echo "✅ Build SUCCESS for ${env.BRANCH_NAME}"
        }
        failure {
            echo "❌ Build FAILED for ${env.BRANCH_NAME}"
        }
    }
}
