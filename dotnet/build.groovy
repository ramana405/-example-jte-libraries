void call() {
echo "starting msbuild"
stage('build') {
       echo "Helloooooooooooooooooo"
       node {
              bat '"C:\\ProgramData\\chocolatey\\lib\\NuGet.CommandLine\\tools\\nuget.exe" restore DancingGoat.sln'

              bat "\"${tool 'MSBuild'}\" DancingGoat.sln /T:Clean;Build;  /p:Configuration=Release /p:Platform=\"Any CPU\" /p:ProductVersion=1.0.0.${env.BUILD_NUMBER}"
       }
  } 
echo "ending msbuild"
       
echo "Starting Static Code Analysis"       
stage('SCA_Sonar'){
       node{
              bat '"C:\\ProgramData\\chocolatey\\bin\\SonarScanner.MSBuild.exe" begin /k:DancingGoat'
        	bat '"C:\\Program Files (x86)\\Microsoft Visual Studio\\2019\\Community\\MSBuild\\Current\\Bin\\MSBuild.exe" DancingGoat.sln /t:Rebuild'
        	bat '"C:\\ProgramData\\chocolatey\\bin\\SonarScanner.MSBuild.exe" end'
       }
} 
echo "Ending Static Code Analysis"
       
echo "Starting Unit Test"
stage("Unit_Test"){
       node{
              bat returnStatus: true, script: "\"C:\\Program Files\\dotnet\\dotnet.exe\" test \"${workspace}/DancingGoat.sln\" --logger \"trx;LogFileName=unit_tests.xml\" --no-build"
		step([$class: 'MSTestPublisher', testResultsFile:"**/*.trx", failOnError: true, keepLongStdio: true])
       }       
} 
echo "Ending Unit Test"
       
echo "Starting Package Stage"
Stage("Package"){
       node{
              bat "\"${tool 'MSBuild'}\" DancingGoat.sln /T:Clean;Build;Package  /p:Configuration=Release /p:Platform=\"Any CPU\" /p:DeployOnBuild=true /p:ProductVersion=1.0.0.${env.BUILD_NUMBER}"
              bat "rename C:\\Users\\venkataramana.t\\.jenkins\\workspace\\DancingGoat\\DancingGoat\\obj\\Release\\Package\\DancingGoat.zip DancingGoat-${env.BUILD_NUMBER}.zip" 
       }
}
echo "Ending Package Stage"       
}


def test1() {
 stage('build') {
       echo "Helloooooooooooooooooo"
 
  } 
}
