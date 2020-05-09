pipeline {
  agent any
  stages {
    stage('init') {
      steps {
        git(url: 'https://github.com/simpleAdminDeveloper/crowler.git', branch: 'master', changelog: true)
      }
    }

    stage('Build image') {
      steps {
        script {
          dockerImage=docker.build registry + ":$BUILD_NUMBER"
        }

      }
    }

    stage('Push Image') {
      steps {
        script {
          docker.withRegistry("") {
            dockerImage.push()
          }
        }

      }
    }

    stage('Deploy App') {
      steps {
        script {
          kubernetesDeploy(config:"crowlerKube.yaml", kubeConfigId:"mykubeconfig")
        }

      }
    }

  }
  environment {
    registry = 'http://101.53.158.226:5000/v2/_catalog'
    dockerImage = 'crowler'
  }
}