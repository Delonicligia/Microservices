pipeline {
    agent any

    environment {
        IMAGE_NAME = "buku-service"
        CONTAINER_NAME = "buku-service-deploy"
        CONTAINER_PORT = "8084"
        HOST_PORT = "5084"
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
