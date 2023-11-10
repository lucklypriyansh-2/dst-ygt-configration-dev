# ADF pipeline specific parameters and tags

```
When you define CloudFormation templates as artifacts to push through a pipeline you might want to have a set of parameters associated with the templates. You can utilize the params folder in your repository to add in parameters as you see fit. To avoid having to create a parameter file for each of the stacks you wish to deploy to, you can create a parameter file called global.yml (or .json) any parameters defined in this file will be merged into the parameters for any specific account parameter file at build time. For example you might have a single parameter for a template called CostCenter the value of this will be the same across every deployment of your application however you might have another parameter called InstanceType that you want to be different per account.

https://github.com/awslabs/aws-deployment-framework/blob/master/docs/user-guide.md#cloudformation-parameters-and-tagging
```
