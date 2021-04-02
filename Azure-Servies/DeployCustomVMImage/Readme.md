
This module will talk about Using Custom Images, Creating Custom Images, Deploying VMs from a custom image and Managing custom images.

# With custom images you could install in service your own applications and use those custom images as a source for additional virtual machine deployments in your Azure environments.

# Creating Custom Images

. First we can start with a marketplace image and make the modifications with that base image that you need, then take that and turn it into a custom image. 

. Second way we could build a custom image is you can upload a VHD from a local VM

# Custom Image Deployment Proces.

Here we will know about, how we can go from a virtual machine to a custom image that we can use the source additional virtual machine deployments and we will going to start with a base virtual machine. So purpose of this base VM, we are going to deploy a marketplace image and then we will get that virtual machine up and running from there we are going to make our modifications to it so we can install our applications i.e softwares and any configuration changes needed inside that up and running VM 

# Creating Custom Images.

1. Generalize and Deprovision VMs
2. Stopping and Deallocating VMs
3. Marking VMs as Generalized
4. Create Custom Images

# Deprovisioning and Creating a Linux custom image using Azure CLI

# Ensure we are using bash for this Demo

# Login interactively and set a subscription to the current active subscription

az login --subscription "subscriptionname"

# Find the IP of the VM we want to build a custom image from

az vm list-ip-address --name "demo-linux-1" --output table

# By using the above step we will get the output of the IP addrees, use the IP address and Connect to the virtual machine via SSH 

ssh demoadmin@168.61.212.180

# Deprovisioning the Virtual Machine, so behind this process we can see from the ouput it deletes all the SSH host keys and delete any cached DHCP leases will be deleted and password will be disabled and delete demoadmin account and entire home directory will be deleted

sudo waagent -deprovision+user -force

# logout of the VM

exit

# In Azure CLI, deallocate the Virtual Machine

az vm deallocate \
    --resource-group "demo-rg" \
    --name "demo-linux-1"


# Checkout the status of our virtual machine

az vm list \
   --show-details \
   --output table


# lets mark this virtual machine as generalized that we are about to convert this to a custom image.

az vm generalize \
    --resource-group "demo-rg" \
    --name "demo-linux-1"


# Create a VM from the custom image we just created, simply specify the image as a source.
# Defaults to LRS, add the --zone-resilient option for ZRS if supported in your Region.

az image create \
    --resource-group "demo-rg" \
    --name "demo-linux-ci-1" \
    --source "demo-linux-1"

# Summary image information

az image list \
     --output table

# More detailed image information, specifically this is a managed disk.

az image list


# Create a vm specifying the image we want to use

az vm create \
    --resource-group "demo-rg" \
    --location "eastus" \
    --name "demo-linux-lc" \
    --image "demo-linux-ci-1" \
    --admin-username "demoadmin" \
    --authentication-type "ssh" \
    --ssh-key-value ~/.ssh/id_rsa.pub


# Checkout the status of our provisioned VM from the Image and also our source VM is still deallocated

az vm list \
    --show-details \
    --output table

    
# Try to start our generalized image, you cannit
# If you want to keep the source VM around .. then copy the VM, generalize the copy and continue

az vm start \
    --name "demo-linux-1" \
    --resource-group "demo-rg"

# You can delete the deallocated source VM

az vm delete \
    --name "demo-linux-1" \
    --resource-group "demo-rg"

# WHich will leave just the Image in our Resource Group as a managed resource

az resource list \
    --resource-type=Microsoft.Compute/images \
    --output table