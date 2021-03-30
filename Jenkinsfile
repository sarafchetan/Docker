#!/usr/bin/env bash

   
pipeline {
  
   agent any

   stages{
      stage('pull latest code') {
         steps{        
           git 'https://github.com/sarafchetan/Docker.git'
           }
      }
      stage('Spinning up docker images') {
         steps{
            sh 'docker-compose up -d'
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
