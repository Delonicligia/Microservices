pipeline {
    agent any

    environment {
        IMAGE_NAME = "api-gateway"  // Ganti sesuai nama service
        CONTAINER_NAME = "api-gateway-deploy"
        CONTAINER_PORT = "9002"
        HOST_PORT = "5002"
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

        stage('Deploy to Local Prod') {
            steps {
                script {
                    echo "Mendeploy ke port ${HOST_PORT}..."
                    
                    // 1. Hapus container lama jika ada (biar update)
                    // "|| true" agar tidak error jika container belum ada
                    sh "docker rm -f ${CONTAINER_NAME} || true"
                    
                    // 2. Jalankan container baru
                    sh """
                        docker run -d \
                        --name ${CONTAINER_NAME} \
                        --restart unless-stopped \
                        -p ${HOST_PORT}:${CONTAINER_PORT} \
                        ${IMAGE_NAME}:latest
                    """
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
