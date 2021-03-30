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
                 sh "docker run --rm -e SELENIUM_HUB=${seleniumHub} -e BROWSER=chrome -e MODULE=testng.xml -v ${WORKSPACE}/suite:/usr/share/tag/test-output  --network ${network} registry.hub.docker.com/130619852016/containertest"
                  //archive all the files under 'search' directory
                  archiveArtifacts artifacts: 'Suite/**', fingerprint: true
               }      
            ) 
         }
      }
      stage('Tearing Down Selenium Grid') {
          steps {
             //remove all the containers and volumes
             sh "docker rm -vf ${chrome}"
             sh "docker rm -vf ${firefox}"
             sh "docker rm -vf ${seleniumHub}"
             sh "docker network rm ${network}"
          }
        }   
   }
}
