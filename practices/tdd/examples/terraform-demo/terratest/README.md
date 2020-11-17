# Terraform AWS Example

The Terraform module in this example deploys an EC2 instance and gives that Instance a `Name` tag with the value specified in the
`instance_name` variable.

A test for this module written with Terratest is under terraform-demo/terratest/test/terraform_aws_example_test.go


## Running this module manually

1. Sign up for AWS.
1. Configure your AWS credentials. If you have the AWS CLI installed, you can set these with the `aws configure` command in your terminal.
1. Install Terraform and make sure it's on your `PATH`.
1. Run `terraform init`.
1. Run `terraform apply`.
1. When you're done, run `terraform destroy`.


## Running automated tests against this module

1. Sign up for AWS.
1. Configure your AWS credentials. If you have the AWS CLI installed, you can set these with the `aws configure` command in your terminal.
1. Install Terraform and make sure it's on your `PATH`.
1. Install Golang and make sure this code is checked out into your `GOPATH`.
1. `cd test`
1. `dep ensure`
1. `go test -v -run TestTerraformAwsExample`