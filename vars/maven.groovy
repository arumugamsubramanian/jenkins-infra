#!groovy
def call(body = {}) {
    def config = [
            buildTool: 'maven',
            language: 'java',
            multi: false,
            deployable: false,
            deploy: [:],
            docker: false,
            feature: [],
            documentation: [:]
    ]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    if (env.BRANCH_NAME == 'master') {
        master(config)
    } else if (env.BRANCH_NAME == 'main') {
        maingit(config)
    } else if (env.BRANCH_NAME == 'develop') {
        develop(config)
    } else if (env.BRANCH_NAME ==~ 'feature/.*') {
        feature(config)
    } else if (env.BRANCH_NAME ==~ 'release/.*') {
        release(config)
    } else if (env.BRANCH_NAME ==~ 'support/.*') {

    } else if (env.BRANCH_NAME ==~ 'hotfix/.*') {
        hotfix(config)
    } else if (env.BRANCH_NAME ==~ 'PR-.*') {
        if (env.CHANGE_TARGET == 'develop' || env.CHANGE_TARGET ==~ 'release/.*') {
            feature(config)
        } else {
            echo "This <$env.CHANGE_URL|Pull Request> is not supported for build and PR merge validation"
        }
    } else {
        currentBuild.result = 'FAILURE'
        error("Unsupported branch: ${env.BRANCH_NAME}")
    }
}
