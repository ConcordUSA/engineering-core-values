#Fast Feedback

Fast Feedback is all about getting feedback on your code quickly so that problems can be fixed right away. This feedback can take many forms and is important in every stage of your code's lifecycle. Getting feedback quickly and consistently during development ensures that issues are identified before too many of them stack up. This makes it easier to find a root cause when something goes wrong. Fast feedback can be even more important in production environments, where identifying and fixing problems quickly is critical to maintaining a good user experience. Listed below are different ways you can obtain fast feedback.

##Unit Testing

Unit testing is a method of testing in which individual components of code are isolated and tested to validate that they behave as expected. Unit testing is most effective as a form of fast feedback when it is done at the same time as writing the actual code. You shouldn't move on to adding a new feature until you have tested and validated the behavior of the current feature you're working on. Following this method of testing allows for obtaining quick feedback on small, manageable chunks of code. The tests themselves also run faster when they are testing less code overall. To learn more about how to write unit tests and best practices, read our guide on Test Driven Development [here](practices/tdd/readme.md).

##Continuous Integration/Continuous Delivery (CI/CD)
Practicing CI/CD also helps teams obtain fast feedback. Continuous Integration (CI) is a practice that encourages teams to make small changes and check in code to version control repositories frequently. This is done by establishing a consistent and automated way to build, package, and test applications. When a process like this is in place, people are more likely to commit changes more frequently, which leads to faster feedback on those changes through both automated tests and peer feedback.

Continuous Delivery (CD) picks up where CI ends. The goal of CD is to automate the delivery of applications to infrastructure environments. Similar to CI, CD helps teams obtain fast feedback when testing is included in the automation of the delivery. If any tests fail during delivery, the team can know right away instead of waiting to see if something goes wrong in production. To learn more about CI/CD, visit this [guide](practices/cicd/CICD.md).

##Daily Meetings

Some teams use Daily Scrum (or Daily Standup) meetings to regularly check in on progress. Being able to quickly voice your progress and ask simple questions to your whole team is an excellent way to consistently share feedback. Simply mentioning what you are working on could spark a teammate to mention potential problems you may want to avoid. Feedback like this could end up saving you a lot of time and pain down the road.

##Client and User Feedback

Make sure you are consistently updating clients and asking for feedback. This helps ensure that you don't stray too far from what they have in mind and allows for constant management of expectations. Another way to get feedback from stakeholders outside of the development team is to gather usage statistics from actual users. For example, it can be helpful to know how many users are using a new feature that you added, or what web pages are visited the most. There are a variety of monitoring tools that can be used for this purpose.

##Code Reviews

For more specific and in-depth feedback, it's a good idea to have colleagues review your code. One way to formally ask for a code review is to make a pull request in Git. Pull requests can help ensure that your code doesn’t get merged and deployed before it is ready. They also give the reviewer a convenient way to add comments to specific parts of your code for you to look at. Feedback from your team is essential to finding potential problems before you ship your code. Ideally, a pull request should be made every time that you want to merge.

##Production Monitoring

Even while using all of the practices above to minimize potential problems, issues can still slip through to the production environment. When this happens, the situation can be made a lot less stressful if there are still measures in place to provide fast feedback. There are application monitoring services that can be used for this purpose. For example, if you're using a lot of AWS resources, setting up alerts with Amazon's CloudWatch service is a good idea.