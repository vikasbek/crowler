pipeline {
  agent any
  stages {
    stage('init') {
      steps {
        git(url: 'https://github.com/simpleAdminDeveloper/crowler.git', branch: 'master', changelog: true)
        sh 'mvn clean compile install -DskipTests'
      }
    }

  }
}