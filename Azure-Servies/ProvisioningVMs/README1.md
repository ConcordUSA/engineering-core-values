
## Virtual Machine Components

Virtual machine have three fundamnental elements 

1. Image: This could be an opertaing system like Linux or Windows or an application infrastructure image like Citrix Net scaler or a cisco.

2. Network: This is network connectivity for your virtual machine to the rest of your environment or to the internet if needed, you'll have an IP address for internal communications and if required a public IP address that reachable from the internet.

3. Storage: Each virtual machine will have a block storage device associated with it to store the operating system and also addtional block storage devices if required for application data.

## Create Azure Account

IF you don't have Azure account go head and create an account for yourself by using the following resource

https://azure.microsoft.com/en-us/account/

User account should have ability to create resources in Azure Portal such as:
. Resource Groups
. Virtual Machines
. Storage Accounts
. Networking Elements (Vnets, subnets)

## Before creating a virtual machine 

After login to the Azure Portal, the steps you want to take prior to creating your virtual machine.

1. Resource Group - Before creating a Virtual Machine, you'll want to create a Resource Group for the solution that your'e working in.

2. Create a Virtual Network - Then you'll want to define a virtual network for your virtual machines and on that vnet you'll want to define a subnet to deploy the virtual machine. If you don't specify Vnet and Subnet Azure will create them for you by default, it will generate the IP schema for your vnet and subnet automatically. When provisioning this configuration ahead of time you get to decide on which IP space you'll have for your solution. 

3. Use managed storage.

## Creating virtual machine in Azure Portal

1. Find a virtual machine image in Azure marketplace this could be windows or Linux Image.

2. Give the VM a name it could be uniquely identified within azure portal
3. Disk type could be Premium SSD or Standard HDD.

4. Username and Password - If it is a windows system we can use username and password. If it is a Linux system we can use user name and password or pass in an SSH public key.

5. Select a subscription for this virtual machine to be built against.

6. Assign it to a resource group.

7. Select a location or azure region that this virtual machine will live in.

8. Then we get to select of virtual machine size, this gonna be in terms of CPU disk and memory.


## Virtual Machine Settings

Before deploying virtual machine we have final configurations that we can make with regards to its settings.

1. HA and Replication - Here we can assign this particular VM to Availability Zone or in Availability set.

2. Storage type - We can define storage type here we will select managed disks.

3. Networking - We also can define our networking, here where we will assign the virtual machine to the define Vnet and Subnet that we created in the first phase of this process. 

4. Public IP - Also we have the option to create additional vnets and subnets, if needed we can assign a public IP at this point.

5. Network Security Groups (NSG)- This is essentially a basic firewall configuration, allowing access to the server on specified TCP ports for network services. 