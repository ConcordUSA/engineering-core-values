# Operationally Ready

A challenge in the world of consulting is that you will often work on developing a system, but only infrequently have direct experience with what is required to care for a system once it has moved to production. Experiencing production support allows you to see and understand dimensions of software development that you cannot experience firsthand during an exclusively development-oriented engagement.

Our goal here is to explain some challenges of supporting production code, with an eye towards helping you understand how to write better code that can function well in a production environment without introducing an undue operational burden. Some key areas of focus are:

1. Consider your documentation needs
2. Write code that is serviceable in a production environment
3. Write code that is maintainable over time
4. Write code that can meet your availability and capacity needs

## Documentation

The intent of the Software Engineering Principles is to focus on the delivery of quality software, but we would be remiss to not address some of the particularly acute needs of documentation for production delivery.

There are two dimensions of the documentation space for production code:

1. Change Management requirements peculiar to a particular customer
2. Runbook and Playbook information needed for operational support processes

**Change Management** documentation will vary from customer customer site. Sometimes this documentation is genuinely useful and well considered, sometimes this documentation is less useful for the particulars of your impelementation. In either case, it is something that is typically reqiured to move your code into production. Check with your delivery manager to ensure that the level of documentation required is within the scope of Concord's engagement, but generally this documentation is a blocker to deployment and therefore required -- it is rare that you will be able to get an exemption at a large client from this procedural documentation. The best course of action is to (a) ensure the documentation is within Concord's scope, and (b) understand the intended need and target audience of the document to produce a useful artifact.

Version control history (Git logs) can be an easy way to document your changes. Here's a few guidelines:
- Change commits should be granular (limiting code changes to support a logical change).
- Message should describe the granular change being committed.
- History should make it easy for reviewers and newcomers to follow the rationalle of changes.

**Runbook and Playbook information** documents the correct way to operate the system. How do you gracefully stop the system? Are there any order dependencies you need to consider when you are starting the software? Are there operational scripts for managing the system, and where are they located? Runbooks and Playbooks explain the proper operation of your system, and help ensure consistency in the operational environment. 

1. Runbooks explain the proper way to start the system, stop the system, and perform any routine maintenance such as data backups or archival
2. Playbooks explain operational responses in exceptional circumstances. If an error occurs, where do you look for diagnostic information? If a particular known error condition occurs, how do you recover?

Without this information, the system will suffer from inconsistent operating procedures and errors introduced as an artifact of improper usage. Even if the core software is well-written, the reputaton of the system will be damaged by outages and errors that could have been avoided by providing proper operating instructions.

## Serviceability

Serviceability addresses the needs of an Operations team (or the DevOps engineers in a more modern shop) to be able to configure and understand the health and state of the software system. This includes several key areas:

1. Externalized configuration information
2. Logging and other forensics data
3. Monitoring and Metric information

**Externalized configuration information*** is one key area that separates a hobbyist coder from a professional. Production code runs in a different environment than development efforts, and generally has different target endpoints for data and other services. Moving your code from a development environment into testing, or from testing to a staging or production environment, should not involve a code change. In fact, changing the code and rebuilding would effectively invalidate your prior acceptance test effort.

With this in mind, you should design your code to allow for an external (i.e. not requiring code changes) mechanism for configuration. This is often a property file, or in some environments a 12-factor application style environment variable injection. Examples of commonly externalized information include:

1. Endpoint connectivity details for database or other data storage mechanisms
2. Credential information needed for endpoint connectivity (note that this generally also requires a secure mechanism for externalizing configuration)
3. Behavioral parameters such as log-level verbosity or other forensics flags
4. In some cases, key 'constants' needed for execution (these are often 'code sets' needed for external interfaces)
5. In some cases, external language translation to meet l12n (localization) or i18n (internationalization) requirements.

It is important that this configuration information is managed in a safe location, ideally versioned (to allow repeatable deployments). Some kinds of configuration information (credentials, for example) must be managed securely; when externalizing configuration information, be sure to consider the sensitivity of the information and the security of the managing system.

**Logging and other forensic data** gives you a view of what has happened in the system. Without logging information, you are generally blind to problems within your applicaiton, and severly limited in your ability to debug and resolve problematic behavior. Logging is a careful balance among a few different considerations:

1. The amount of information you need to locate and troubleshoot problems
2. The sensitivity of some information (such as payload information) that passes through your application
3. The cost of storing large amounts of log information
4. The ratio of noise to signal you need to wade through to find meaningful insights within the logs

To help balance the above considerations, here are recommended best practices:

1. Use your framework's support of different levels of log severity, such as TRACE, DEBUG, INFO, WARN, and ERROR, to facilitate log configuration and filtering
2. Log entry/exit points and key waypoints at an appropriate log severity level, such as TRACE or DEBUG.
3. Avoid logging payload contents in bulk. In cases where some amount of payload logging is necessary for forensics, consider which elements are truly necessary and which elements are sensitive and should be masked or otherwise protected.
4. Ensure that your framework, or your log statements, provide you enough information to follow the thread of execution of a particular flow even if it is interleaved with output from other threads of execution.
5. When dealing with exceptions, ensure that you log enough information to debug and understand the problem that has occured. 
6. Be sure to log your exceptions at an appropriate severity level, such as ERROR (or in some cases WARN -- for example when something has failed but is being retried).

**Monitoring and Metric information** is similar in nature to logging information, discussed above. Logging generally deals with the operational flow of the system and requests, and Monitoring and Metrics is useful to give you quick insights to the health and processing status of the system. Consider the information you might want to know to determine if your system is in a healthy state or requires some sort of intervention or forensics - you may want to know answers to questions such as:

1. How many errors have I encountered in the past hour?
2. How quickly am I responding to client requests?
3. How quickly is the database responding to my requests?
4. Are any of my servers running out of memory?
5. Are my CPU loads aberrantly high?
6. Is my application currently running?
7. How many requests have I processed successfully in the last hour?

To answer questions such as this, you will need to have instrumentaiton to provide you this information, and monitoring mechanisms in place to report history, provide you easy-to-interpret dashboards, and alert you to active problems.

Key points to emphasize on that last point, alerting, are:

1. Ensure alerts are actionable. Avoid alerting for strictly informational purposes; alerting fatigue from routine alerts will numb operations staff and cause them to miss or respond more slowly to true events.
2. Ensure alerts are throttled. Emitting a new alert each time an error occurs can result in an overwhelming number of alerts within a short period of time during heavy loads.
3. Ensure your alerting mechanism is at least as reliable as the system being observed. Alerting via e-mail (which may not be deemed mission-critical from an enterprise tiering perspective) is not acceptable for a core mission critical business system.
4. Remember that your monitoring solution should be less complicated than the system you are monitoring. Monitoring and Alerting systems seem prone to over-engineering, and this complexity should be scrutinized carefully.

## Maintainability


## Availability and Capacity

