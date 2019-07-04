void call(){
echo "Starting Package Stage"
Stage("Package"){
       node{
              bat "\"${tool 'MSBuild'}\" DancingGoat.sln /T:Clean;Build;Package  /p:Configuration=Release /p:Platform=\"Any CPU\" /p:DeployOnBuild=true /p:ProductVersion=1.0.0.${env.BUILD_NUMBER}"
              bat "rename C:\\Users\\venkataramana.t\\.jenkins\\workspace\\DancingGoat\\DancingGoat\\obj\\Release\\Package\\DancingGoat.zip DancingGoat-${env.BUILD_NUMBER}.zip" 
       }
}
echo "Ending Package Stage"      
}
