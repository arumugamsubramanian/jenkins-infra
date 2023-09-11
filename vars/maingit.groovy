#!groovy

import io.aru.utils.CommonUtils

def call(Map body = [:]) {
    def config = []

    pipeline {
        agent {
            label 'any'
        }

        triggers {
            pollSCM('* * * * *')
        }

        stages {
            stage('Prepare') {
                steps {
                    milestone 1
                    script {
                        try {
                            withCredentials([usernamePassword(credentialsId: 'jira-username-api-token-for-jenkins-int', passwordVariable: 'JIRA_API_TOKEN', usernameVariable: 'JIRA_USER')]) {

                            }
                            CommonUtils.printLog(this, 'arumugam')
                        } catch (e) {
                            buildError = "Failure during build preparation: \n\n${e.message}"
                            error "${buildError}"
                        }
                    }
                }
                post {
                    changed {
                        echo 'Build result changed'
                    }

                    success {
                        echo 'Stage Prepare was a success'
                    }
                }
            }
        }
    }
}