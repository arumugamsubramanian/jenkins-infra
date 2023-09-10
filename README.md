# jenkins-infra
A setup repo for local Jenkins pipeline development

### Notes
* To test individual pipeline, use branch name as develop in 
```
$ ./test/jenkins-lts/run.sh

#export GITHUB_SHA=$(git rev-parse --verify HEAD)
export GITHUB_SHA=develop
```

* to test the pipeline with multi-branch pipeline
```
use the main branch for development
```