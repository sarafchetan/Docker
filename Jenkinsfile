#!/usr/bin/env bash

   
pipeline {
   environment {
    PATH = "$PATH:/usr/bin"
  }
   agent any

   stages{
      stage('pull latest code') {
         steps{        
           git branch: 'main', url: 'https://github.com/sarafchetan/Docker.git'
           }
      }
      stage('Spinning up docker images') {
         steps{
			
		 	echo "PATH is: $PATH"
			sh  "/var/lib/jenkins/workspace/Pipeline/docker-compose up -d"
         }
      }
      stage('Build') {
          steps {
            sh 'mvn clean package -DskipTests'
          }
        } 
	  stage('Destroy - after docker tests on container'){
		  steps{
			sh 'docker stop $(docker ps -a -q)'
			sh 'docker rm $(docker ps -a -q)'
		  }
	  }
   }
}
