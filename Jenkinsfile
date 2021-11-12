pipeline {

    environment { 
        registry = "skanderbac/timesheet" 
        registryCredential = 'skanderbac' 
        dockerImage = '' 
    }
    
	agent any

	stages{
		       
			stage('Clean Install'){
				steps{
					bat "mvn clean install -DskipTests"
				}				
			}


			stage('Sonar Analyse'){
				steps{
                    bat "mvn sonar:sonar -DskipTests"
                  }
            }
            
            stage('Nexus Deploy'){
				steps{
                    bat "mvn deploy -DskipTests"
                  }
            }
            
            stage('Building our image') { 
                steps { 
                    script { 
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                    }
                } 
            }

           stage('Deploy our image') { 
                steps { 
                    script { 
                    docker.withRegistry( '', registryCredential ) { 
                        dockerImage.push() 
                    }
                } 
             }
           } 
          
           stage('Cleaning up') { 
                steps { 
                    bat "docker rmi $registry:$BUILD_NUMBER" 
                }
           } 

		    stage('Pulling from docker hub') { 
                steps { 
                    script { 
                    docker.withRegistry( '', registryCredential ) { 
                        dockerImage.pull() 
                    }
                } 
             }
           } 

        
        	stage('mysql') { 
                steps { 
                    bat "docker container run --name mysqldb --network devops-nt  -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=timesheet-spring -d mysql:5.6"

                }
           } 
           stage('run images') { 
                steps { 
                    bat "docker container run --network devops-nt --name timesheet-container -p 8082:8082 -d $registry:$BUILD_NUMBER"
                }
           } 
	}
	
	  post{
            always{        
                cleanWs()
        }
    }
	 

}