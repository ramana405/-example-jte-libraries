void call(){

echo "Starting Deployment"
stage("Deploy"){
    node{
        options{
              timeout(time : 30, unit: 'MINUTES')
        		}
        steps{
              mail to: 'venkataramana.thalari@cummins.com',
              subject: "Job '${env.JOB_NAME}' (${env.BUILD_NUMBER}) is waiting for input",
              body: "Please go to ${env.BUILD_URL}console and verify the build"
				      input "Deploy to Production?"
				      //Deployment Script
        }
        post{
             success{
                 echo 'Deployemnt is Successful . . .'
             }
             failure{
                 echo 'Deployment is failed'
             } 
        }
    }
}
}
