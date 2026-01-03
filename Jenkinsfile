pipeline {
    agent any

    tools {
        jdk 'jdk-17'
        maven 'maven-3.9'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'java -version'
                sh 'mvn -version'
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
    }

    post {
        success {
            echo '✅ Build successful'
        }
        failure {
            echo '❌ Build failed'
        }
        always {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
    }
}