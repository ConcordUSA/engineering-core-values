
This module contains high level introduction of Azure fundamentals:

## Accounts

1. Cloud based services: Users utilize to build infrastructure and platforms in Microsoft operated data centers.
2. Azure Account: To access microsoft services the highest level mechanism that you're going to need to do that is an account this uniquely identifies you as an azure user inside of portal.

     . Uniquely identifies you in Azure. 
     . And you can apply security policies and control access to resources in azure and azure administrator.
## Subscriptions 
Subscriptions are used to provide user access to Microsoft.
1. Subscriptions grant you access to microsoft azure resources.
2. You can have several subscriptions associated with an account
3. Types of subscriptions: 
   Azure free account - which is time and cost bounded free subscription to get you started.
   Pay-As-You-Go - allows you to be invoiced and also paid for by credit card.
   Visual Studio - Which provides free azure credits to those consumers of the visual studio product.
   Enterprise Agreements - These are pre negotiated agreements for access to and payment for azure services between Microsoft and their customers.

## Resources in Azure
Manageable items that is available in Azure
1. Virtual machine
2. Networking
3. Database
4. These resources are assigned to a subscription  


## Resource Manager

1. Resource Manager: Allows you to build resource groups that group elements of a solution together, and with that we can reuse templates for subsequent deployments and resource manager also provides a consistent management layer in tooling things like Azure portal, Cloudshell, Powershell, Azure CLI and some APIs and SDKs are all available to us and our toolbox for managing our azure solutions. 
2. We can also leverage resource managers resource groups to establish a security boundary for role based access controls around particular solutions so we can assign the responsibility for particular solution which is logical group inside of resource manager, and provide the correct level of access to the group that needs potentially administrative access for that particular solution.
3. we can also leverage reosurce manager and resorce groups to establish the grouping of costs associated with a particular set of resources and we can roll that up and provide the costs associated with a particular resource manager or resource group solution and bill that back to appropriate business group inside of our organization.

## Azure Regions 

1. A Region is a set of data centers deployed within a latency defined perimeter.
2. Many regions to choose from, currently there are 42 Regions around the world with plans announced for 12 additional regions.

## Availability Zones

1. Physically seperate - Availability zones are physically seperate locations within an Azure Region. 
2. Redundant and Independent - Each availability zone is made up of one or more data centers equipped with independent power, cooling and networking.
3. Mission Critical, Low Latency - Availability zones allow us to do run those mission critical applications in Azure with guarantees around high availability and low latency for those very performance sensitive and mission critical applications.

## Placing Resources in Regions and Availability Zones

1. Services Avaliability - when coming to deploying the services within regions, we need to make sure that the service is available in the region  that we want to deploy to. As new services come online, they're rolled out region by region, so we need to make sure that we're looking for in our solution is available in the region that we want to deploy to.
2. Redundancy -  we want to ensure that applications are redundant, for example if I am going to deploy an sql server I certainly would want the number of a cluster to be deployed in seperate availability zones because I want to take advantage of that redundant, low latency, high bandwidth network that availability zones provide within a region.
3. Latency - When we're deploying an application, we want to ensure that latency into account when placing resource is in regions and availability zones. If I am writing an application with the relational database back and like sql server, we want to ensure that those services are deployed on high bandwidth lowly its connections between those two different resources and that really gonna your apps the most optimal application performance.

## Azure Services

When we are provisiong services in Microsoft Azure, there are two fundamental services. 

1. IAAS (Infrastructure as a Service) - In this model compute and networking resources are managed by the user inside of Microsoft Azure Essentially, which means virtual machines that you control, In this case your'e handed off the operations of the data center, physical network and the storage to your cloud provider (Azure). 
2. PAAS (Platform as a Service) - In this model you are consuming the resources directly without having maintain the application or the service or the operating system underneath that you are consuming the resource in its entirety as a service, this could be a database platform, development tools are even a web server platform itself. Patching and Maintenance are all handled for you by your cloud provider azure.

## Azure Compute

1. Virtual Machines - Virtual machines are scalable compute resources available in Azure, scalable means in terms of the size of the virtual machine where we can increase the CPU, RAM and Disk resources assocaited with that Virtual Machine. But it's also scalable in the number of virtual machines where we can increase or decrease the number of VMs in our solution very easily.
2. As a resource the thing that you get to consume in Azure is the virtual machine and the operating system itself, so that platform whether it's Linux or Windows

## Virtual Machine Images

1. Inside of Microsoft Azure you have Azure marketplace images and with these are a collection of prepackaged applications or services provided by Microsoft and other third parties for you to consume.
2. Most commonly we will see opertaing systems like Windows Server, Windows 7/8 images to deploy on virtual machines inside of Azure and also we have RedHat Linux, Ubuntu Linux and many available as a virtual machine marketplace image.
3. In addition to operating system we also have application infrastructure here we have third party networking components like net scaler from Citrix or VPN.
4. Finally we have custom images and this allows you to pacakge applications and settings into the virtual machine image that could be used as a base image for deployment of additional virtual machines inside of Azure for you.