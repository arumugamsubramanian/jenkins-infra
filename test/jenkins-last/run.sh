#!/bin/bash

set -e

function usage() {
    echo "usage: $0 [-keepalive] [-port <port>]"
}

KEEPALIVE_ARG=
PORT_ARG=

[ $# -gt 3 ] && echo "ERROR too many parameters" && usage && exit 1
while [ $# -gt 0 ]; do
    case "$1" in
        -keepalive) KEEPALIVE_ARG=$1; shift;;
        -port) PORT_ARG="-p $2:8443"; shift 2;;
        *) echo "ERROR unknown parameter $1"; usage; exit 1;;
    esac
done

cd $(dirname $0)
IMAGE_NAME=$(basename $(dirname $0))

docker build -t ${IMAGE_NAME} .
export GITHUB_WORKSPACE=/workspace
export GITHUB_SHA=$(git rev-parse --verify HEAD)
#export GITHUB_SHA=develop

docker run -it --rm --name jenkins-local -e GITHUB_SHA -e GITHUB_WORKSPACE -v "$(pwd -P)/../../":"/workspace" -v /var/run/docker.sock:/var/run/docker.sock -it ${PORT_ARG} ${IMAGE_NAME} ${KEEPALIVE_ARG}
