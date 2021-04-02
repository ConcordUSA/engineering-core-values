This module will talk about Generalizing and creating a custom image using Powershell

# Setup - Pre-stage the RDP connection for the windows vm = 'demo-win-1'
# Ensure we are in the Powershell Integrated Console

# You can use Azure CLi or Powershell on windows or Linux systems.

# Start a connection with Azure

Connect-AzureRmAccount -Subscription 'Account Name'

# Open and RDP session to this Windows VM and run this command in a command prompt
# This will generalize the VM and Shutdown

%WINDIR%\system32\sysprep\sysprep.exe /generalize /shutdown /oobe

# Lets get the status of our VM and ensure its shutdown first.

Get-AzureRmVM `
    -ResourceGroupName 'demo-rg' `
    -Name 'demo-win-1' `
    -Status

# Find our Resource Group

$rg = Get-AzureRmResourceGroup `
    -Name 'demo-rg' `
    -Location 'eastus'

# Find our VM in our Resource Group

$vm = Get-AzureRmVm `
    -ResourceGroupName $rg.ResourceGroupName `
    -Name "demo-win-1"

# Deallocate the virtual machine

Stop-AzureRmVM `
    -ResourceGroupName $rg.ResourceGroupName `
    -Name $vm.Name `
    -Force

# Check the status of the VM to see if it is deallocated

Get-AzureRmVM `
   -ResourceGroupName $rg.ResourceGroupName `
   -Name $vm.Name `
   -Status

# Mark the virtual machine as "Generalized"

Set-AzureRmVM `
    -ResourceGroupName $rg.ResourceGroupName `
    -Name $vm.Name `
    -Generalized

# Start an Image configuration from our source virtual machine $vm

$image = New-AzureRmImageConfig `
   -Location $rg.location `
   -SourceVirtualMachineId $vm.ID

# Create a VM from the Custom image config we just created, simply specify the image config

New-AzureRmImage `
    -ResourceGroupName $rg.ResourceGroupName `
    -Image $image `
    -ImageName "demo-win-ci-1"

# Summary image information, you will see two images one Linux and one Windows.

Get-AzureRmImage `
    -ResourceGroupName $rg.ResourceGroupName

# Create user object this will be used for the Windows username/password

$password = ConvertTo-SecureString 'password123$*' -AsPlainText -Force 
$WindowsCred = New-object Syestem.Management.Automation.PSCredential ('demoadmin' , $password)

# Lets Create a VM from our new image

New-AzureRmVM `
    -ResourceGroupName $rg.ResourceGroupName `
    -Name "demo-win-1c" `
    -ImageName "demo-win-ci-1" `
    -Location 'eastus' `
    -Credential $WindowsCred `
    -VirtualNetworkName 'demo-vnet-2' `
    -SubnetName 'demo-subnet-2' `
    -SecurityGroupName 'demo-win-nsg-2' `
    -OpenPorts 3389

# Check the status of our provisioned VM from the Image

Get-AzureRmVm `
    -ResourceGroupName $rg.ResourceGroupName `
    -Name "demo-win-lc"

# You can delete the deallocated source VM

Remove-AzureRmVm `
    -ResourceGroupName $rg.ResourceGroupName `
    -Name "demo-win-1" `
    -Force

# That still leaves the image in our Resource Group

Get-AzureRmImage `
     -ResourceGroupName $rg.ResourceGroupName `
     -ImageName 'demo-win-ci-1'

