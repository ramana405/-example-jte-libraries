@AfterStep
void call(context){
echo "context step " + context.step
echo "context status "+ context.status
    if (context.step.equals("build") && context.status.equals("FAILURE")){
        //slackSend color: '#ff0000', message: "Build Failure: ${env.JOB_URL}"
        echo "inside method of notification"
    }
}
