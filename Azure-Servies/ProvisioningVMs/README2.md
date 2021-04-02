
Creating Virtual Machines using AZURE CLI.

# Installing Azure CLI 
brew update && brew install azure-cli

# Installing for other OSs is available here:
https://docs.microsoft.com/en-us/cli/azure/install-azure-cli?view=azure-cli-latest

# Login interactively and set a subscription to be the current active subscription

When we are working with Azure CLI, we need to ensure that we are logged into our account and we do that with the below command.

az login --subscription "Account name"

## Lets create a Linux VM, starting off with creating a Resource Group.

# Step 1: Create a resource group

The first thing we're gonna do is to create a resource group, by using the following commands.

az group create \
    --name "demo-rg" \
    --location "eastus"

# Below command give us a nice table view of the output, so here we can see AZ group lists fields of the current resource gourps in current subscription.

az group list -o table

# Step 2: Create virtual network (vnet) and subnet

az network vnet create \
    --resource-group "demo-rg" \
    --name "demo-vnet-1" \
    --address-prefix "10.1.0.0/16" \
    --subnet-name "psdemo-subnet-1" \
    --subnet-prefix "10.1.1.0/24"

# Below command give us a nice table view of the output, so here we can see number of vnet lists in current resource group list.

az network vnet list --output table

# Step 3: Create public IP address

az network public-ip create \
    --resource-group "demo-rg" \
    --name "demo-linux-1-pip-1"

# Step 4: Create network security group

az network nsg create \
    --resource-group "demo-rg" \
    --name "demo-linux-nsg-1"

# Below command list out the NSG table 

az network nsg list --output table

# Step 5: Create a virtual network interface and associate with public IP address and NSG.

az network nic create \
    --resource-group "demo-rg" \
    --name "demo-linux-1-nic-1" \
    --vnet-name "demo-vnet-1" \
    --subnet "demo-subnet-1" \
    --network-security-group "demo-linux-nsg-1" \
    --public-ip-address "demo-linux-1-pip-1"

# Below command list out the nic table

az network nic list --output table

# Step 6: Create a Virtual Machine (Linux)

az vm create \
    --resource-group "demo-rg" \
    --location "eastus" \
    --name "demo-linux-1" \
    --nics "demo-linux-1-nic-1" \
    --image "rhel" \
    --admin-username "demoadmin" \
    --authentication-type "ssh" \
    --ssh-key-value ~/.ssh/id_rsa.pub

VM may take a few minutes to create.

# Step 7: Open port 22 to allow SSH traffic to host

az vm open-port \
    --resource-group "demo-rg"
    --name "demo-linux-1" \
    --port "22"

# Step 8: Grab the public IP of the virtual machine to connect with it

az vm list-ip-addresses --name "demo-linux-1" --output table

# lets ssh into the VM by using public IP address

ssh -l demoadmin (enter public IP address here which we got through the procees of above step)

# logout from virtual machine by using below one

exit

-----------------------------------------------------------------------------------------------------------

## Lets create a Windows VM using Azure CLI

# Step1: we are going to place this server in the exisiting the resource group.

# Step2: we are going to place this server in the same vnet.

# Step3: Create public IP address.

az network public-ip create \
    --resource-group "demo-rg" \
    --name "demo-win-1-pip-1"

# Step4: Create network security group, so we can have seperate security policies

az network nsg create \
    --resource-group "demo-rg" \
    --name "demo-win-nsg-1"

# Step5: Create a virtual network card and associate with public IP address and NSG

az network nic create \
    --resource-group "demo-rg" \
    --name "demo-win-1-nic-1" \
    --vnet-name "demo-vent-1" \
    --subnet "demo-subnet-1" \
    --network-security-group "demo-win-nsg-1" \
    --public-ip-address "demo-win-1-pip-1"

# Step6: Create a windows virtual machine

az vm create \
    --resource-group "demo-rg" \
    --name "demo-win-1" \
    --location "eastus" \
    --nics "demo-win-1-nic-1" \
    --image "win2016datacenter" \
    --admin-username "demoadmin" \
    --admin-password "password123$"

# Step7: Open port 3389 to allow RDP traffic to host

az vm open-port \
    --port "3389" \
    --resource-group "demo-rg" \
    --name "demo-win-1"


# lets list output the table of above created windows vm by using below command

az vm list-ip-addresses --name "demo-win-1" --output table

# Use Remote Desktop to connect to this VM

