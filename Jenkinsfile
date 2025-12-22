pipeline {
    agent any

    environment {
        IMAGE_NAME = "pengembalian-service"  // Ganti sesuai nama service
        DOCKER_REGISTRY = ""         // Kosongkan jika local only
    }

    stages {
        stage('Checkout') {
            steps {
                echo "📥 Checking out code from branch: ${env.BRANCH_NAME}"
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    echo "🔨 Building Docker image..."
                    def imageName = "${IMAGE_NAME}:${env.BRANCH_NAME}-${env.BUILD_NUMBER}"

                    sh """
                        docker build -t ${imageName} .
                        docker tag ${imageName} ${IMAGE_NAME}:latest
                    """

                    echo "✅ Image built: ${imageName}"
                }
            }
        }

        stage('Cleanup Old Images') {
            steps {
                script {
                    echo "🧹 Cleaning up old images..."
                    sh """
                        docker image prune -f
                    """
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
