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
}

def test1() {
 stage('build') {
       echo "Helloooooooooooooooooo"
 
  } 
}
