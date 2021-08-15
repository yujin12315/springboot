pipeline {
    agent none
    stages {
      stage('Maven构建') {
        agent {
             docker {
              args '-v /root/.m2:/root/.m2'
              image 'maven:3-alpine'
             }
          }
        steps {
          sh 'mvn-gs setting.xml clean package -Dmaven.test.skip=true'
          sh 'pwd'
          sh 'ls'
       }
      }

     stage('Docker构建') {
       steps {
          // One or more steps need to be included within the steps block.
        }
      }

     stage('Dockerswiming部署') {
       steps {
         // One or more steps need to be included within the steps block.
       }
     }

    }

}
