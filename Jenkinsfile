#!/usr/bin/env bash

   
pipeline {
   environment {
    PATH = "$PATH:/usr/local/bin"
  }
   agent any
	tools {
        maven 'apache-maven-3.0.1' 
    }
   stages{
      stage('pull latest code') {
         steps{        
           git branch: 'main', url: 'https://github.com/sarafchetan/Docker.git'
           }
      }
      stage('Spinning up docker images') {
         steps{
			
		 	echo "PATH is: $PATH"
			sh  "/usr/local/bin/docker-compose up -d"
         }
      }
      stage('Build') {
          steps {
          	  sh 'mvn --version'
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
