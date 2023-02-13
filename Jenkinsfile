void setBuildStatus(String message, String state) {
  step([
      $class: "GitHubCommitStatusSetter",
      reposSource: [$class: "ManuallyEnteredRepositorySource", url: "https://github.com/republic5/Helper"],
      contextSource: [$class: "ManuallyEnteredCommitContextSource", context: "ci/jenkins/build-status"],
      errorHandlers: [[$class: "ChangingBuildStatusErrorHandler", result: "UNSTABLE"]],
      statusResultSource: [ $class: "ConditionalStatusResultSource", results: [[$class: "AnyBuildResult", message: message, state: state]] ]
  ]);
}

pipeline {
    agent any

    triggers {
        githubPush()
    }
	
    tools {
        maven "Maven-3.8.5"
    }

    stages {
        stage('Build') {
            steps {
                sh "mvn clean package"

            }
            
            post {
                success {
                    archiveArtifacts 'target/*.jar'
		    setBuildStatus("Build succeeded", "SUCCESS");
		}
		   
                failure {
        	        setBuildStatus("Build failed", "FAILURE");
                }                
            }

        }
        
        stage('Build image') {
            steps {
                sh 'docker build -t nightfoxx/helper:latest .'
            }
        }
    
        stage('Push to docker registry') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'Password', usernameVariable: 'Username')]) {
                    sh "docker login -u ${env.Username} -p ${env.Password}"
                    sh 'docker push nightfoxx/helper:latest'
                }
                
            }
            
            post {
                success {
		            setBuildStatus("Push succeeded", "SUCCESS");
                }
		   
                failure {
        	        setBuildStatus("Push failed", "FAILURE");
                }                
            }
            
	}
	    
	stage('webhook to discord') {
	        steps {
		    withCredentials([string(credentialsId: 'WEBHOOK', variable: 'url')]) {
		      	discordSend description: "", 
                  		footer: "", 
				link: "${env.JOB_URL}",
		                result: currentBuild.currentResult, 
                		title: "${env.buildName}", 
				webhookURL: '${env.url}', 
                  		successful: currentBuild.resultIsBetterOrEqualTo('SUCCESS'),
                  		thumbnail: ""
	         }
	     }
	}    
    }
}
