# CI/CD

- **Continuous Integration (CI)**: Automated integration of code
- **Continuous Deployment (CD)**: Automated deployment of software

## The What and the Why

Proper CI/CD automates the manual stuff and ensures that code is merged and released correctly.

- 🏎 Team velocity
- 💻 Code accuracy
- 🤓 Developer happiness

## CI

CI is the task of regularly merging code changes. It allows for common tasks to be run to ensure code quality and integrity. Some common tasks/checks that happen at this stage are:

- Testing (unit, integration, &/or end-to-end)
- Linting (code format checks)
- Building/compiling
- Manual Code Reviews

If any issues or errors come up, they can be addressed before merging code into higher branches or environments. CI systems can also **require** that these checks pass prior to proceeding.

[What is Continuous Integration](https://www.youtube.com/watch?v=1er2cjUq1UI)

## CD

CD is the task of automating release and deployment of code to running environments. Common tasks/checks that happen at this stage are:

- Testing (unit, integration, &/or end-to-end)
- Linting (code format checks)
- Building/compiling
- Release versioning
- Deployment

[What is Continuous Delivery](https://www.youtube.com/watch?v=2TTU5BB-k9U)

## Tools & Services

Some common tools and services in this space are:

- [Github Actions](https://docs.github.com/en/free-pro-team@latest/actions)
- [Bitbucket Pipelines](https://bitbucket.org/product/features/pipelines)
- [Jira Pipelines](https://www.atlassian.com/software/jira/guides/developers/ci-cd)
- [AWS Code Pipeline](https://aws.amazon.com/codepipeline/)
- [Jenkins](https://www.jenkins.io/)
- [CircleCI](https://circleci.com/)
- [CodeShip](https://codeship.com/)

## Resources

- [5 Ways to Automate Github Actions](https://www.youtube.com/watch?v=eB0nUzAI7M8)
- [DevOps CI/CD Explained in 100 Seconds](https://www.youtube.com/watch?v=scEDHsr3APg)
