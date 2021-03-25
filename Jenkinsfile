pipeline {
    environment{
    PATH = "$PATH:/usr/local/bin"
    }
    agent any 
    stages { 	
        stage('pull latest code') {
            steps {
            	//Get some code from git repository
                sh  'git clone https://github.com/sarafchetan/Docker.git'
            }
        }
        stage('Spinning up docker images') {
            steps {
                	echo "PATH is: $PATH"
                    sh '/usr/local/bin/docker-compose up -d'
                
                }
            }
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
                }
            }
         stage('Test'){
           	 steps(){
           	 	sh 'mvn test'
           	 } 
           }
          stage('Destroy - After running test container'){
             steps(){
             	sh 'docker stop $(docker ps -a -q)'
          		sh 'docker rm $(docker ps -a -q)'
             }
           }
        }        
}
