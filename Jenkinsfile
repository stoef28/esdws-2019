

pipeline {
     agent any
     tools { // this is the jdk specified in global configurations
        jdk 'JDK11'
      }

    stages {
        stage('Clean') {
            steps {
                    sh "chmod +x gradlew"
                    sh "./gradlew clean"
            }
        }
        stage('Build') {
            steps {
                    sh "chmod +x gradlew"
                    sh "./gradlew build"
            }
        }
        stage('Test') {
            steps {
                    sh "chmod +x gradlew"
                    sh "./gradlew check"
            }
        }
         stage('Deploy') {
                    steps {
                        sh 'chmod +x var/jenkins_home/workspace/emerging-software-design-workshop/backend/build/libs/backend-0.1.0-SNAPSHOT.jar'
                        sh 'java var/jenkins_home/workspace/emerging-software-design-workshop/backend/build/libs/backend-0.1.0-SNAPSHOT.jar'
                    }
                }
    }
}