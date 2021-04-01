#!/usr/bin/env bash
//variables
def network='jenkins-${BUILD_NUMBER}'
def seleniumHub='selenium-hub-${BUILD_NUMBER}'
def chrome='chrome-${BUILD_NUMBER}'
//def firefox='firefox-${BUILD_NUMBER}'
def containertest='conatinertest-${BUILD_NUMBER}'
   
pipeline {
  
   agent any
   stages{
	stage('Build Jar') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v $HOME/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn clean package -DskipTests'
            }	
		}
	stage('Build Image') {
            steps {
                script {
                      
                      app = docker.build("130619852016/containertest")
                }
            }
        }
    stage('Push Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
                        app.push("${BUILD_NUMBER}")
                        app.push("latest")
                    }
                }
            }
        }  
	stage('Setting Up Selenium Grid') {
         steps{        
            sh "docker network create ${network}"
            sh "docker run -d -p 4444:4444 --name ${seleniumHub} --network ${network} selenium/hub"
            sh "docker run -d -e HUB_PORT_4444_TCP_ADDR=${seleniumHub} -e HUB_PORT_4444_TCP_PORT=4444 --network ${network} --name ${chrome} selenium/node-chrome"
           }
      }
	 stage('Run Test') {
         steps{
            parallel(
               "suite":{
                  // a directory 'search' is created for container test-output
                 sh "docker run --rm -e SELENIUM_HUB=${seleniumHub} -e BROWSER=chrome -e MODULE=testng.xml -v ${WORKSPACE}/suite:/usr/share/tag/test-output  --network ${network} 130619852016/containertest"
                  //archive all the files under 'search' directory
                  archiveArtifacts artifacts: 'target/**', fingerprint: true
               }      
            ) 
         }
      }
	  stage('Tearing Down Selenium Grid') {
          steps {
             //remove all the containers and volumes
             sh "docker rm -vf ${chrome}"
             sh "docker rm -vf ${seleniumHub}"
             sh "docker network rm ${network}"
          }
        }   
   
   }
}
