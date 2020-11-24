#Fast Feedback

Fast Feedback is all about getting feedback on your code quickly so that problems can be fixed right away. This feedback can take many forms and is important in every stage of your code's lifecycle. Getting feedback quickly and consistently during development ensures that issues are identified before too many of them stack up. This makes it easier to find a root cause when something goes wrong. Fast feedback can be even more important in production environments, where identifying and fixing problems quickly is critical to maintaining a good user experience. Listed below are different ways you can obtain fast feedback.

##Unit Testing

Unit testing is a method of testing in which individual components of code are isolated and tested to validate that they behave as expected. Unit testing is most effective as a form of fast feedback when it is done at the same time as writing the actual code. You shouldn't move on to adding a new feature until you have tested and validated the behavior of the current feature you're working on. Following this method of testing allows for obtaining quick feedback on small, manageable chunks of code. The tests themselves also run faster when they are testing less code overall. To learn more about how to write unit tests and best practices, read our guide on Test Driven Development [here](practices/tdd/readme.md).


##Daily Meetings

Some teams use Daily Scrum (or Daily Standup) meetings to regularly check in on progress. Being able to quickly voice your progress and ask simple questions to your whole team is an excellent way to consistently share feedback. Simply mentioning what you are working on could spark a teammate to mention potential problems you may want to avoid. Feedback like this could end up saving you a lot of time and pain down the road.

##User Feedback

Make sure you are consistently updating clients/users and asking for feedback. This helps ensure that you don't stray too far from what they have in mind and allows for constant management of expectations.

##Code Reviews

For more specific and in-depth feedback, it's a good idea to have colleagues review your code. One way to formally ask for a code review is to make a pull request in Git. Pull requests can help ensure that your code doesn’t get merged and deployed before it is ready. They also give the reviewer a convenient way to add comments to specific parts of your code for you to look at. Feedback from your team is essential to finding potential problems before you ship your code. Ideally, a pull request should be made every time that you want to merge.

##Production Monitoring

Even while using all of the practices above to minimize potential problems, issues can still slip through to the production environment. When this happens, the situation can be made a lot less stressful if there are still measures in place to provide fast feedback. There are application monitoring services that can be used for this purpose. For example, if you're using a lot of AWS resources, setting up alerts with Amazon's CloudWatch service is a good idea.