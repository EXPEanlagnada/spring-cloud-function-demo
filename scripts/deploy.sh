#!/usr/bin/env zsh
#file=spring-cloud-function-demo-0.0.1-SNAPSHOT-aws.jar
#aws s3 cp target/${file} s3://staging-04ff/lambda/${file}
#cmd="aws s3 ls s3://staging-04ff/lambda/${file}"
#echo "Executing: $cmd"
#eval "${cmd}"

# aws lambda list-functions --region us-east-1
project_version=0.0.1
basename=spring-cloud-function-demo
archive=${basename}-${project_version}-aws.jar
archive_md5=$(md5 -q target/${archive})
ts="$(date +%Y-%m-%d)-${archive_md5}"
archive_file=${basename}-${project_version}-${ts}.jar

s3Region=us-west-2
s3Bucket=staging-04ff
s3Key=lambda/${archive_file}
s3Uri=s3://${s3Bucket}/${s3Key}

deploy_s3=no

function uploadAndDeployS3() {
    echo "Uploading Lambda to S3 Bucket ${s3Uri} ..." && \
    local lscmd="aws s3 ls ${s3Bucket}"
    local cmd="aws s3 cp target/${archive} ${s3Uri}"
    echo Executing: ${cmd}
    eval ${cmd}

    echo "Updating lambda function..."
    local cmd="aws lambda update-function-code"
    cmd="${cmd} --region ${s3Region}"
    cmd="${cmd} --function-name ${basename}"
    cmd="${cmd} --s3-bucket ${s3Bucket} --s3-key ${s3Key}"
    echo Executing: ${cmd}
    eval ${cmd}
}

function deployLocalFile() {
    echo "Updating lambda function..."
    local cmd="aws lambda update-function-code"
    cmd="${cmd} --region ${s3Region}"
    cmd="${cmd} --function-name ${basename}"
    cmd="${cmd} --zip-file fileb://target/${archive}"
    echo Executing: ${cmd}
    eval ${cmd}
}


if [[ "${1}" == "s3" ]]; then
    deploy_s3=yes
fi


if [[ "${deploy_s3}" == "yes" ]]; then
    cmd="./mvnw package -q -Pshade -DskipTests && uploadAndDeployS3"
else
    cmd="./mvnw package -q -Pshade -DskipTests && deployLocalFile"
fi

echo "Executing: ${cmd}"
eval "${cmd}"
