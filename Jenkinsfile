pipeline {
  
  environment {
    registry = "http://101.53.158.226:5000/crowler"
    dockerRepoUrl= "101.53.158.226:5000"
    dockerImageName="crowler"
    dockerImage = ''
    dockerImageTag = "${dockerRepoUrl}/${dockerImageName}:${env.BUILD_NUMBER}"
    mvnHome=tool 'maven3'
  }
  agent any
  stages {
    stage('init') {
      steps {
        git(url: 'https://github.com/simpleAdminDeveloper/crowler.git', branch: 'master', changelog: true)
      }
    }
    stage('Build Project') {
      // build project via maven
      steps{
        sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean compile install -DskipTests"
      }
    }
    
    stage('Build image') {
      steps {
        script {
          sh "whoami"
          sh "ls -all /var/run/docker.sock"
          sh "cp ./target/*.jar ./data" 
          sh "cp ./target/*.jar ."
          dockerImage = docker.build("crowler")
          //dockerImage=docker.build registry + ":$BUILD_NUMBER"
        }

      }
    }

    stage('Push Image') {
      steps {
        script {
          echo "Docker Image Tag Name: ${dockerImageTag}"
          sh "docker tag ${dockerImageName} ${dockerImageTag}"
          sh "docker push ${dockerImageTag}"
          //docker.withRegistry("") {
            //dockerImage.push()
          //}
        }

      }
    }

    stage('Deploy App') {
      steps {
        script {
          kubernetesDeploy(configs:"crowlerKube.yaml", kubeconfigId:"mykubeconfig")
        }

      }
    }

  }
}
