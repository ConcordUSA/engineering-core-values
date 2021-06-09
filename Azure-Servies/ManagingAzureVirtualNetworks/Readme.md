# Sources

<https://app.pluralsight.com/course-player?clipId=1878102d-27c1-4c36-9343-706faf1eb59e>

<https://app.pluralsight.com/course-player?clipId=3f69f293-063d-4c60-8c09-aab657338408>

## This module talks about Azure virtual network components, i.e deploy and configure a VNet

## Azure Wire Server

The azure wire server service is the virtual IP 168.63.129.16 and this is a important virtual IP that you should be aware of because it does a lot of heavy lifting in your Azure Virtual Networks.

DHCP: wireserver is responsible for DHCP, this allows your resources placed on those subnets in your Vnet to recieve private non routable IP addresses automatically.

Default gateway: Wireserver also is the default gateway and unless you take actions to restrict outbound internet connections, your virtual machines can infect get out onto the internet and they'll use the wireserver as its default gateway by default.

DNS: Wireserver also takes care of Azure provided domain name system or DNss host name resolution.

VM Agent Communication: Wireserver is responsible for VM agent communication, between your virtual machines and the azure backplane.

## Network Security Group (NSG)

NSG works at OSI layer 4 the transport layer this is the stateful firewall that you can associate at the subnet or the VNIC level to control ingress thats inbound and egress thats outbound network traffic. OSI layer 4 means that the NSG operates with five properties, source and destination IP address, source and destination port number, and protocol typically TCP and UDP.

## Public IP address

Our VMs don't need public IP address to get out to the internet. A public IP address allows inbound internet connections to that VM, so dont put public IP on the VM unless you have a defined business reason for doing so.

## Azure DNS

Azure has Azure-provided DNS but Azure also has a seperate DNS service where you can stand up both public, internet routetable and private, non-internet accessible DNS zones.

## Public and Private IP addresses

Microsoft azure is a public cloud, therefore all azure servcies are available on public IP addresses and DNS names on microsoft owned domains.

Service tags: Microsoft aggregates their services and their associated public IPS into what are called service tags, and microsoft publishes their service tag address every month and they do this so that local security teams who need to have those IPS in constructing firewall rules know what those IPS.

Azure load Balancer and Application Gateway: Load balancer and application gateway allows you to use network address translation to come through the device and access management ports on your VMs.

## Simple VNet deployment using - Azure CLI

```sh
az network vnet create \
   --name MyVnet \
   --resource-group $RgName \
   --location $Location \
   --address-prefix 10.0.0.0/16 \
   --subnet-name Mysubnet-FrontEnd \
   --subnet-prefix 10.0.1.0/24 
```

## Application Security Groups (ASG)

I you NSG you have actions like allow, deny and you have a name of the rule that you give, a user friendly name, you have your source and destinations, and these can be IP addresses, IP ranges. ASG allows you to cretae your own logical groups your web servers, app servers and you DB servers.

## Creating a NSG using Powershell

## Steps: Authenticate to Azure

```sh
Connect-AzureRmAccount
```

## Create a resource group

```sh
New-AzureRmResourceGroup -ResourceGroupName myResourceGroup -Location Eastus
```

## Create two ASGs

```sh
$webAsg = New-AzureRmApplicationSecurityGroup `
   -ResourceGroupName myResourceGroup `
   -Name myAsgWebServers `
   -Location eastus
```

```sh
$mgmtAsg = New-AzureRmApplicationSecurityGroup `
  -ResourceGroupName myResourceGroup `
  -Name myAsgMgmtServers `
  -Location eastus
```

## Create two rules

```sh
$webRule = New-AzureRmNetworkSecurityRuleConfig `
  -Name "Allow-Web-All" `
  -Access Allow `
  -Protocol Tcp `
  -Direction Inbound `
  -Priority 100 `
  -SourceAddressPrefix Internet `
  -SourcePortRange * `
  -DestinationApplicationSecurityGroupId $webAsg.id `
  -DestinationPortRange 80,443
```

```sh
$mgmtRule = New-AzureRmNetworkSecurityRuleConfig `
   -Name "Allow-RDP-All" `
   -Access Allow `
   -Protocol Tcp `
   -Direction Inbound `
   -Priority 110 `
   -SourceAddressPrefix Internet `
   -SourcePortRange * `
   -DestinationApplicationSecurityGroupId $mgmtAsg.id `
   -DestinationPortRange 3389
```

## Create a network security group (NSG) that includes ASG Rules

```sh
$nsg = New-AzureRmNetworkSecurityGroup `
-ResourceGroupName myResourceGroup `
-Location eastus `
-Name myNsg `
-SecurityRules $webRule,$mgmtRule
```

## Create a virtual network

```sh
$virtualNetwork = New-AzureRmVirtualNetwork `
  -ResourceGroupName myResourceGroup `
  -Location Eastus `
  -Name myVirtualNetwork `
  -AddressPrefix 10.0.0.0/16 
```

## Add a subnet

```sh
Add-AzureRmVirtualNetworkSubnetConfig `
 -Name mySubnet `
 -VirtualNetwork $virtualNetwork `
 -AddressPrefix "10.0.2.0/24" `
 -NetworkSecurityGroup $nsg
```

```sh
$virtualNetwork | Set-AzureRmVirtualNetwork
```

## Get reference to Vnet

```sh
$virtualNetwork = Get-AzureRmVirtualNetwork `
  -Name myVirtualNetwork `
  -Resourcegroupname myResourceGroup 
```

## Create two public IP addresses

```sh
$publicIpWeb = New-AzureRmPublicIpAddress `
  -AllocationMethod Dynamic `
  -ResourceGroupName myResourceGroup `
  -Location eastus `
  -Name myVmWeb
```

```sh
$publicIpmgmt = New-AzureRmPublicIpAddress `
  -AllocationMethod Dynamic `
  -ResourceGroupName myResourceGroup `
  -Location eastus `
  -Name myVmmgmt
```

## Create two vNICs

```sh
$webNic = New-AzureRmNetworkInterface `
  -Location eastus `
  -Name myVmWeb `
  -ResourceGroupName myResourceGroup `
  -SubnetId $virtualNetwork.Subnets[0].Id `
  -ApplicationSecurityGroupId $webAsg.Id `
  -PublicIpAddressId $publicIpWeb.Id
```

```sh
$mgmtNic = New-AzureRmNetworkInterface `
  -Location eastus `
  -Name myVmMgmt `
  -ResourceGroupName myResourceGroup `
  -SubnetId $virtualNetwork.Subnets[0].Id `
  -ApplicationSecurityGroupId $mgmtAsg.Id `
  -PublicIpAddressId $publicIpMgmt.Id
```
