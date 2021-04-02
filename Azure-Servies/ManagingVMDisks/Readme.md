
This module is about understanding Azure disk storage and we are gonna look at various types of storage and storage accounts are availabe inside of azure. Then we are going to look at virtual machines disk topology so that we can understand how those disks presented up into the operating system and then we gonna take a look at some common administrative tasks like adding a virtual disk, removing a disk and resizing a disk and also snapshots.



# Azure Disk Storage

. Disks - VHDs attached to VMs
. Different types of storage available : Standard and Premium, Managed and Unmanaged Disks
. Storage Account

# Types of Storage

You have a choice regarding to the performance and costs of your storage in Azure when configuring the storage in Azure. When creating a storage account that choice is the type of storage and there is two types of storage in Azure first is Standard and second one is Premium Storage. 

1. Standard Storage : Standard storage uses magnetic media to store data.
                      Magnetic based - HDD.
                      Solid state based - SSD (Preview).
                      Fixed in terms of IOPs and throughput.
                      Tied to the Tier of the VM.
                      Cost sensitive and latency insensitive workloads.
                      Non - production, backup and big data.

2. Premium Storage : This storage provides high performance storage for your performance sensistive workloads. Premium storage uses SSD to store data.
                      Solid state based - SSD.
                      Latency sensitive workloads.
                      Higher costs.
                      IOPs and throughout increase with disk capacity not the VM.
                      Performance limits associated with VMs size.
                      Use on nearly all production workloads.


# Lets see common Administrative disk operations 

. Creating a disk
. Attaching a disk
. Preparing the disk for the OS
. Resizing a disk
. Removing a disk
. Snapshots

Our implementation of above operations will be Azure CLI

# In Azure CLI

1. Attach a dsik to an exisitng VM
2. Resizing a dsik
3. Removing a disk
4. Snapshot an OS volume and creating a VM from a snapshot

# Setup
# Requires a Linux VM we are going to use demo-linux-1c from our privious VM creation.

# login interactively and set a subscription to be the current active subscription 
 
az login --subscription "Azure Account name"

# Creating a new data disk with Azure CLI and attach it to our VM

# Step 1: Attache the new disk

az vm disk attach \
    --resource-group "demo-rg" \
    --vm-name "demo-linux-1c" \
    --disk "demo-linux-1c-st0" \
    --new \
    --size-gb 25 \
    --sku "Premium_LRS" 

# Step 2: Prepare the disk for use by the operating system

az vm list-ip-addresses \
    --name "demo-linux-1c" \
    --output table

ssh -l demoadmn w.x.y.z (IP address)

# Step 3: Find the new block device, we know /dev/sda is th OS, and /dev/sdb is the temporary disk.
# we also know it is 25 gb, so /dev/sdc it is.

lsblk

# we can also use dmesg, like docs.microsoft.com says

dmesg | grep SCSI

# Step 4: Partition the disk with fdisk and use the following commands to name a new primary partition
sudo fdisk /dev/sdc
m
n
p
1
w

# Step 5: Format the new partition with ext4

sudo mkfs -t ext4 /dev/sdc1

# Step 6: Make a directory to mount the new disk under

sudo mkdir /data1

# Step 7: Add the following line to /etc/fstab. First find the UUID for this device.

sudo -i blkid | grep sdc1

# Copy the output of the UID which we received by above step.
UUID= paste ID number here
sudo vi /etc/fstab

# Step 8: Mount the volume and verify the file system is mounted.

sudo mount -a
df -h

# Step 9: Exit from the Linux VM

exit

---------------------------------------------------

# Resizing a dsik

# Step 1: Stop and deallocate the VM this has to be an offline operation.

az vm deallocate \
   --resource-group "demo-rg" \
   --name "demo-linux-1c"

# Step 2: Find the disks name we want to expand

az disk list \
    --output table

# Step 3: Update the disks size to the desired size

az disk update \
   --resource-group "demo-rg" \
   --name "demo-linux-1c-st0" \
   --size-gb 100

# Step 4: Start up the VM again

az vm start \
   --resource-group "demo-rg" \
   --name "demo-linux-1c" 

# Step 5: Log into the guest OS and resize the volume

az vm list-ip-addresses \
   --name "demo-linux-1c" \
   --output table

ssh -l demoadmin (enter the output IP here)

# Step 6: Unmount filesystem and expand the partition

sudo vi /etc/fstab 

sudo umount /data1
sudo parted /dev/sdc

# Use print to find the size of the new disk, partititon 1, resize, set the size to 107, quit

print
resizepart
1
107GB
quit

# If the resize part option is not available update the parted pacakage

# Step 7: fsck, expand and mount the filesystem

sudo e2fsck -f /dev/sdc1
sudo resize2fs /dev/sdc1
sudo mount /dev/sdc1 /data1
sudo vi /etc/fstab
sudo mount -a

# Step 8: Verify the added space is available

df -h | grep data1



------------------------------------------------------------------------------------------

# Remove a disk

# Step 1: Umount the disk in the OS, remove the disk we added above from fstab

ssh -l demoadmin (IP address)
sudo vi /etc/fstab
sudo umount /data1
df -h | grep /data1
exit


# Step 2: Detaching the disk from the virtual machine. This can be done online too.

az vm disk detach \
     --resource-group "demo-rg" \
     --vm-name "demo-linux-1c" \
     --name "demo-linux-1c-st0"


# Step 3: Delete the disk

az disk delete
   --reource-group "demo-rg" \
   --name "demo-linux-1c-st0" 


------------------------------------------------------------------------------------------------

# Snapshoting the OS disk

# Step 1: Find the disk we want to snapshot and create a snapshot of the disk

az disk list --output table | grep demo-linux-1c

# Update the source parameter with the disk from the last command

az snapshot create \
    --resource-group "demo-rg" \
    --source "demo-linux-1c_OsDisk_1_13242353465747" \
    --name "demo-linux-1c-OSDisk-1-snap-1"

# Step 2: Getting a list of the snapshots available

az snapshot list \
    --output table

# Step 3: Create a new disk from the snapshot we just created 
# If this was a data disk, we could just attach and mount this disk to a VM

az disk create \
    --resource-group "demo-rg" \
    --name "demo-linux-1f-OSDisk-1" \
    --source "demo-linux-1c-OSDisk-1-snap-1" \
    --size-gb "40"

# Step 4: Create a VM from the disk we just created 

az vm create \
     --resource-group "demo-rg" \
     --name "demo-linux-1f" \
     --attach-os-disk "demo-linux-1f-OSDisk-1" \
     --os-type "Linux"

# Step 5: If we want we can delete a snapshot when we are finished

az snapshot delete \
      --resource-group "demo-rg" \
      --name "demo-linux-1c-OSDisk-1-snap-1"


# Logically, the steps on windows are the same for adding, expanding and removing a dsik.
