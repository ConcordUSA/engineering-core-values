
Creating Virtual Machine using POWERSHELL

# Setup

1. Logged into Azure Powershell, with Connect-AzureRmAccount

2. Ensure you are in a PowerShell Integrated terminal session.

# This module outline

1. Create a Linux VM, specifying individual resources

2. Create a Windows VM, using PowerShell to feed all the required parameters.

# Install AzureRm.Netcore in PowerShell Core

sudo pwsh

Install-Module AzureRM.Netcore

# Exit out the elevated pwsh

exit 

# Installing AzureRm for Windows

Launch an elevated windows PowerShell

Install-Module -Name AzureRM

More information is available here

https://docs.microsoft.com/en-us/powershell/azure/install-azurerm-ps?view=azurermps-6.7.0

# Start a connection with Azure

Connect-AzureRmAccount -Subscription 'Account name'

## Create a Linux Virtual Machine with PowerShell

# Step 1: Get resource group which we already created or existed RG by using below commands

$rg = Get-AzureRmResourceGroup `
  -Name 'demo-rg' `
  -Location 'centralus'

$rg

# Step 2: Create a subnet configuration

$subnetConfig = New-AzureRmVirtualNetworkSubnetConfig `
    -Name 'demo-subnet-2' `
    -AddressPrefix '10.2.1.0/24'

 
#Create a Virtual network

$vnet = New-AzureRmVirtualNetwork `
    -ResourceGroupName $rg.ResourceGroupName `
    -Location $rg.Location `
    -Name 'demo-vnet-2' `
    -AddressPrefix '10.2.0.0/16' `
    -Subnet $subnetConfig

$vnet

# Step 3: Create public IP address

$pip = New-AzureRmPublicIpAddress `
   -ResourceGroupName $rg.ResourceGroupName `
   -Location $rg.Location `
   -Name 'demo-linux-2-pip-1' `
   -AllocationMethod Static

$pip


# Step 4: Create network security group rule for ssh

$rule1 = New-AzureRmNetworkSecurtiyRuleConfig `
    -Name ssh-rule `
    -Description 'Allow SSH' `
    -Access Allow `
    -Protocol Tcp `
    -Direction Inbound `
    -Priority 100 `
    -SourceAddressPrefix Internet `
    -SourcePortRange * `
    -DestinationAddressPrefix * `
    -DestinationPortRange 22

$rule1

# Create network security group, with the newly created rule.

$nsg = New-AzureRmNetworkSecurityGroup `
    -ResourceGroupName $rg.ResourceGroupName `
    -Location $rg.Location `
    -Name 'demo-linux-nsg-2' `
    -SecurityRules $rule1

$nsg | more

# Step 5: Create a virtual network card and associate with public IP address and NSG

#First, lets get an object representing our current subnet  

$subnet = $vnet.Subnets | Where-Object { $_.Name -eq 'demo-subnet-2' }

$nic = New-AzureRmNetworkInterface `
    -ResourceGroupName $rg.ResourceGroupName `
    -Location $rg.Location `
    -Name 'demo-lin-2-nic-1' `
    -Subnet $subnet `
    -PublicIpAddress $pip `
    -NetworkSecurityGroup $nsg

$nic

# Step 6: Create a virtual machine configuration

$LinuxVmConfig = New-AzureRmVMConfig `
    -VMName 'demo-linux-2' `
    -VMSize 'Standard_D1'

#Set the computer name, OS type and, auth methods.

$password = ConvertTo-SecureString 'password123$%*' -AsPlainText -Force
$LinuxCred = New-Object System.Managment.Automation.PSCredential ('demoadmin', $password)


$LinuxVmConfig = Set-AzureRmVMOperatingSystem `
    -VM $LinuxVmConfig `
    -Linux `
    -ComputeName 'demo-linux-2' `
    -DisablePasswordAuthentication `
    -Credential $LinuxCred


#Read in our SSh Keys and add to the VM config

$sshPublicKey = Get-Content "~/.ssh/id_rsa.pub"
Add-AzureRmVMSshPublicKey `
     -VM $LinuxVmConfig `
     -KeyData $sshPublicKey `
     -Path "/home/demoadmin/.ssh/authorized_keys"


#Get the VM image name, and set it in the VM Config in this case RHEL/Latest

Get-AzureRmVMImageSku -Location -PublisherName "Redhat" -Offer "rhel"

$LinuxVmConfig = Set-AzureRmVMSourceImage `
    -VM $LinuxVmConfig `
    -PublisherName 'Redhat' `
    -Offer 'rhel' `
    -Skus '7.4' `
    -Version 'latest'

#Assign the created network interface to the vm

$LinuxVmConfig = Add-AzureRmVMNetworkInterface `
   -VM $LinuxVmConfig `
   -Id $nic.Id

# Create a virtual machine passing in the VM Configuration, network, image etc are in the configuration.

New-AzureRmVM `
    -ResourceGroupName $rg.ResourceGroupName `
    -Location $rg.Location `
    -VM $LinuxVmConfig

So now we need to make a connection to that provisioned VM.

$MyIP = Get-AzureRmPublicIpAddress `
   -ResourceGroupName $rg.ResourceGroupName `
   -Name $pip.Name | Select-Object -ExpandProperty IpAddress

$MyIP

# Connect to our VM via SSH
ssh -l demoadmin $MyIP 

---------------------------------------------------------------------

## Create a windows Virtual Machine using PowerShell with a little less code using PowerShell Splatting.

# Create PSCredential objevt, this will be used for the Windows username/password.

$password -ConvertTo-SecureString 'password123$%*' -AsPlainText -Force
$WindowsCred = New-Object System.Managment.Automation.PSCredential ('demoadmin', $password)

# We are using the ImageName parameter for a list of images look here
#https://docs.microsoft.com/en-us/powershell/module/azurerm.compute/new-azurermvm?view=azurermps

# Use tab complete to help find your image name, eneter into the Terminal
New-AzureRMVM -Image

$vmParams = @{
    ResourceGroupName ='demo-rg'
    Name = 'demo-win-2'
    Location = 'eastus'
    Size = 'Standard_D1'
    Image = 'Win2016Datacenter'
    PublicIpAddressName = 'demo-win-2-pip-1'
    Credential = $WindowsCred
    VirtualNetworkName = 'demo-vnet-2'
    SubnetName = 'demo-subnet-2
    SecurityGroupName = 'demo-win-nsg-2'
    OpenPorts = 3389
}

# we can see the provisioning state of VM by using below command.

New-AzureRmVM @vmParams

# To find out the public IP

Get-AzureRmPublicIpAddress `
    -ResourceGroupName 'demo-rg' `
    -Name 'demo-win-2-pip-1' | Select-Object -ExpandProperty IpAddress


# Launch RDP session to new VM