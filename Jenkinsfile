pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Test') {
      steps {
        bat 'mvn -B clean test'
      }
    }

    stage('Package') {
      steps {
        bat 'mvn -B clean package'
        archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
      }
    }

    stage('Docker Build') {
      steps {
        bat 'docker version'
        bat 'docker build -t grocery-app:%BUILD_NUMBER% .'
      }
    }
  }
}