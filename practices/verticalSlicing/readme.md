# Vertical Slicing

Ensures that business value has been delivered at the end of every feature/story. This is as opposed to "horizontal slicing" i.e. creating two different user stories for the frontend and backend work required to deliver some business value.

## The What and The Why

### The What

What is vertical slicing? Vertical slicing is a method for writing user stories that enables the end result to deliver business value upon completion. Consider a piece of cake with three layers: the user interface (UI), the API layer and the database. Yum! Maximum deliciousness is acheived when a slice of cake contains all its layers and stays in a cohesive unit. Thus Business value from stories, or maximum deliciousness is delivered when the user story contains the necessary content from all layers and these layers function cohesively. If you slice a piece of cake vertically, the slice still contains all internal layers. In developement, vertical slicing refers to splitting user stories vertically such that the story still contains the necessary components for the UI, API layer, and database. This is as opposed to horizontal slicing which splits up the stories so that only one layer of the developement cake is changed at a time.

### The Why

Why vertical slicing? 

Vertical slicing has a number of benefits:

1. Improve consistency throughout feature/component developement

2. Improve cross-functional teamwork

3. Improve the accuracy of time estimates

4. Minimize merge conflicts among developers 

5. Minimize possible bugs and errors

## Examples

# Large User Story:

The story: 
As a user and plant lover I would like to be able to monitor all of my 200 plant's soil moisture so that I can use the least amount of water possible and keep all my plants alive.

The layers of the developement cake:
- A user interface that shows all the plants current soil moisture
- A api layer that interacts with the moisture sensors and the database
- A database that holds the information on all plants.

# Vertical Slice:

The story:
As a user and plant lover I would like to see the moisture level of my single basil plant so that I can water it appropriately.

The components:
- Display the soil moisture of the basil plant on a webpage
- Expose a POST endpoint to save the basil's soil moisture to the database
- Expose a GET endpoint to get the current soil moisture of the basil plant
- Save a record of the basil plant to the database

Upon completion of the vertical slice, we will have the ability to monitor the basil plant and the user can water their basil plant when needed. This provides the user with the necessary functionality to take care of one plant while keeping the work load for this story managable.

# Horizontal Slice:

The story:
As a user, I would like to see all of my plants soil moisture levels on a single webpage.

The components:
- Create a user interface that displays all plant's soil moisture level on a single webpage

Upon completion of the horizontal slice, we will have a built out UI but no API layer or database to support it.

## Resources

1. [Vertical Slicing vs Horizontal Slicing](https://www.visual-paradigm.com/scrum/user-story-splitting-vertical-slice-vs-horizontal-slice/)
2. [More Examples](https://appliedframeworks.com/user-stories-making-the-vertical-slice/)