void call(){

ehco "Uploading Artifacts to S3 bucket"
stage("Upload_Artifact"){
    node{
        withAWS(region:'us-east-2',credentials:'<Credentials_Id>') {
            s3Upload(file:"C:\\users\\venkataramana.t\\.jenkins\\workspace\\dotnet_project\\DemoApp\\obj\\release\\package\\DemoApp-${env.BUILD_NUMBER}.zip", bucket:'<bucket-name>')
        }
    }
}
echo "Artifacts Uploaded to S3 bucket"
}
