# Vertical Slicing

Ensures that business value has been delivered at the end of every feature/story. This is as opposed to "horizontal slicing" i.e. creating two different user stories for the frontend and backend work required to deliver some business value.

## The What and The Why

### The What

What is vertical slicing? Vertical slicing is a method for writing user stories that enables the end result to deliver business value upon completion. Consider a piece of cake with three layers: the user interface (UI), the API layer and the database. Yum! Maximum deliciousness is achieved when a slice of cake contains all its layers and stays in a cohesive unit. Thus Business value from stories, or maximum deliciousness is delivered when the user story contains the necessary content from all layers and these layers function cohesively. If you slice a piece of cake vertically, the slice still contains all internal layers. In development, vertical slicing refers to splitting user stories vertically such that the story still contains the necessary components for the UI, API layer, and database. This is as opposed to horizontal slicing which splits up the stories so that only one layer of the development cake is changed at a time.

### The Why

Why vertical slicing?

Vertical slicing has a number of benefits:

1. Improve consistency throughout feature/component development

2. Improve cross-functional teamwork

3. Improve the accuracy of time estimates

4. Minimize merge conflicts among developers

5. Minimize possible bugs and errors

## Examples

### Feature

The story:
As a user and plant lover I would like to be able to monitor all of my 200 plant's soil moisture so that I can use the least amount of water possible and keep all my plants alive.

The layers of the development cake:

- A user interface that shows all the plants current soil moisture
- A api layer that interacts with the moisture sensors and the database
- A database that holds the information on all plants.

### Vertical Slices

User story #1 (similar for each plant):
As a user and plant lover I would like to see the moisture level of my single basil plant so that I can water it appropriately.

The components:

- Create a simple webpage to display information on the basil plant
- Expose a GET endpoint to get the current soil moisture of the basil plant
- Create a plant class for basil that includes necessary plant information
- Create a plant service that receives information from a soil moisture sensor

Upon completion of the vertical slice, we will have the ability to monitor the basil plant and the user can water their basil plant when needed. This provides the user with the necessary functionality to take care of one plant while keeping the work load for this story manageable.

### Horizontal Slices

User story #1:
As a user, I would like to see all of my plants soil moisture levels on a plant hub webpage.

The components:

- Create a plant hub page
- Display plant information on small cards
- Display 10 plants each page
- Capture GET Requests from an API layer

Upon completion of the horizontal slice, we will have a built out UI but no API layer to get information on the plants and no information on how the API will send the data to the UI or its format since it has not been built.

User story #2:
As a user, I would like my webpage to pull information from my plants records so I can see the most up to date information on my plants.

The components:

- Build out a complete API layer that will interface with the UI and database
- Create Plant Group classes that includes all necessary plant information for that plant type
- Create a Plant service that will get information from all the soil moisture sensors
- Expose POST, GET and PUT endpoints

Upon completion of the horizontal slice, we will have an API layer that can interface with the database and UI but assumptions may have been made that conflict with UI implementation.

### Wrap Up

In short, vertical slicing delivers functionality faster and creates more consistent code since the developers only focuses on a small piece and work with all layers related to that piece. While this example highlights a single story that delivers functionality it is still possible to have multiple stories that roll up to a single piece of functionality when doing vertical slicing. But the responsibility for delivering that functionality would still fall to one person or pair in a vertical slice as opposed to the horizontal approach.

## Resources

1. [Vertical Slicing vs Horizontal Slicing](https://www.visual-paradigm.com/scrum/user-story-splitting-vertical-slice-vs-horizontal-slice/)
2. [More Examples](https://appliedframeworks.com/user-stories-making-the-vertical-slice/)
