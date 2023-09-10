#!groovy

import io.aru.utils.CommonUtils

def call(Map body = [:]) {
    def config = []

    pipeline {
        agent {
            label 'any'
        }

        stages {
            stage('Prepare') {
                steps {
                    milestone 1
                    script {
                        try {
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