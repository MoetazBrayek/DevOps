pipeline {
	agent any
	stages{
		stage('clone and clean repo'){
	

	           steps {
                cleanWs()
		       	bat "git clone -b MoetazBrayek https://github.com/MoetazBrayek/DevOps.git"
		       			      dir("DevOps") {
		          	bat "mvn clean "
}

			 }

		}
		stage('Testing'){

		   steps{ 
		dir("DevOps") {
		   bat "mvn test -Dtest=!EmployeServiceImplTest"
                        }
			}}
		stage('Deploy'){
		   steps {
         dir("DevOps") {
			bat "mvn package -Dmaven.test.skip"
         
  
			bat "mvn sonar:sonar -Dmaven.test.skip"

			bat "mvn deploy -Dmaven.test.skip"
       
		
}
	}
}
		stage('Docker'){
		   steps {
		dir("DevOps") {
			bat "docker build -t dev ."
			// 
			bat "docker tag dev  moetazbrayek/devop:1"
			bat"docker login -u moetazbrayek -p bouba13A*"
			bat "docker push  moetazbrayek/devop:1"			
			
			
        }
        
    }}
        stage('email'){
            	           steps {

            mail bcc: '', body: 'tazzzzzzz', cc: '', from: '', replyTo: '', subject: 'hello update from aaa ', to: 'moetazbusiness@gmail.com'
    }}
    
}}
