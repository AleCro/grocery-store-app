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
        // Windows Jenkins => use bat
        bat 'mvn -B clean test'
      }
    }

    stage('Package') {
      steps {
        bat 'mvn -B clean package'
        archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
      }
    }
  }
}