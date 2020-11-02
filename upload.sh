
#!/usr/bin/env sh
file=spring-cloud-function-demo-0.0.1-SNAPSHOT-aws.jar
aws s3 cp target/${file} s3://staging-04ff/lambda/${file}
cmd="aws s3 ls s3://staging-04ff/lambda/${file}"
echo "Executing: $cmd"
eval "${cmd}"