# Managing Custom Images

. Copying between Azure Regions
. Finding
. Deleting

Here we are going to copy custom image to another Azure Region.

## First we need to list out the images in our Subscription by using below command

```sh
az image list --output table
```

## Step 1: Lets say we started a new Resource Group in the East US Region

```sh
az group create \
    --name"demo-rg-1" \
    --location "eastus"
```

## Step 2: Lets try to create a VM from a custom image thats in another Azure Region

```sh
az vm create \
    --location "eastus" \
    --resource-group "demo-rg-1" \
    --name "demo-linux-1e" \
    --image "demo-linux-ci-1" \
    --admin-username demouser \
    --ssh-key-value ~/.ssh/id_rsa.pub
```

## Step 3: We need to get a copy of our image into that Azure Region, we need the image copy extension

```sh
az extension add \
    --name image-copy-extension
```

## Step 4: Then we can copy our image between our Resource Groups and Regions. Creatra Temporary Storage

## May take 5-10 mims

```sh
az image copy \
   --source-object-name "demo-linux-ci-1" \
   --source-resource-group "demo-rg" \
   --target-location "eastus" \
   --target-resource-group "demo-rg-1" \
   --target-name "demo-linux-ci-1-east" \
   --cleanup
```

## List all images in our subscription

```sh
az image list \
    --output table
```

## Step 5: Retry, creating a vm in the new RG, in the East US Region where our image has been copied

```sh
az vm create \
   --location "eastus" \
   --resource-group "demo-rg-1" \
   --name "demo-linux-1e" \
   --image "demo-linux-ci-1-esat" \
   --admin-username demouser \
   --ssh-key-value ~/.ssh/id_rsa.pub
```

## Step 6: Look at our currently provisioned Vms

```sh
az vm list \
     --output table
```

## Step 7: Delete an specific image

```sh
az image delete \
    --resource-group "demo-rg-1" \
    --name "demo-linux-ci-1-east"
```
