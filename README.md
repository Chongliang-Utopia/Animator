# The Easy Animator
A Java swing GUI application which allows for creation of 2D animation of shapes. 
It's created following the model-view-controller design pattern.

# Model
The overal design of the model consist of two parts, the Data managing part and individual parts that represent 2D shapes and
animations.
The following concrete classes/abstract classes/interfaces are created for the model of the easy animator. 


- **Data Managing Part**

    - **IModel**: This interface represents the operations that can be called on the model. 
    It defines methods to add a shape, delete a shape and add an animation. 
    It also defines methods to get a deep copy of all the updated shapes at a given time. 
    
    - **ModelImpl**: This class implements the IModel interface and represents the concrete model of the animator.
    *Internally*, it stores all the shapes in a list. 
    It also stores all the animations in a Map, using the start time of the animation as the key since the order of the 
    animation matters.
    *Externally*, it allows to get a deep copy of all the shapes and a deep copy of the animations sorted 
    by starting time. It also include methods to add a shape, delete a shape and add an animation. Additionally,
    it offers a method to get a copy of all the updated shapes at a given time. 
    It contains a public method to return a string representation of all the shapes and animations.
    
 
- **Animations**   

    - **IAnimation**: This interface represents a motion to transform the original shape, it declares methods that
    a motion needs, to run the motion and return a shape with updated states, and return information
    about the motion.
        
    - **AbstactAnimation**: This abstract class represents an abstract animation in the model. It contains the common           attributes of
    common attributes and methods, including the animation type, shape name, start time
    and end time. It includes common getter of these attributes, as well as a calculateState to
    calculate the current state according to the current time.
    
    - **AnimationOperation**: This class extends AbstractAnimation, represents a motion for a shape. It stores the initial and transformed state of the shape. It includes a method to run the animation and get an updated shape according to the given time.
    
    - **AnimationType**: An enum to represent an animation type.
    
- **Shapes** 

    - **IShape**: The IShape interface extends the IReadOnlyShapes interface to add setter methods which can be used in the     model. The AbstractShape implements this interface to have a full functionality with getters and setters.
    
    - **IReadOnlyShapes**: This interface IReadOnlyShapes is created to prevent mutation of the original shapes. It only
    contains getter methods. The shapes received by the view will be all IReadOnlyShapes to make the information passed to       view immutable.
 
    - **AbstractShape**: This abstract class implements IShape and it respresents an abstract shape in the model, which           contains all common attributes of shapes: a name, type, color, position, display, appear time, disappear time. It can       provide some useful details of shape for others to retrieve. It also tracks all of the properties in the shape that         can be modified, the operations that will be performed on it, and its own text representation. 
    
    - **Oval**: This class extends the AbstractShape and represents an oval object in an animation. It has all of the fields     of an AbstractShape and its dimensions consist of its x radius and y radius. It has overriden equals and hashCode method     so that it can be used to compare with other objects. It also has a detailed implementation for the textRender method,       which returns a string representation of the shape details. 

    - **Rectangle**: This calss represents a rectangle object in an animation. It has all of the fields of an AbstractShape     and its dimenstions consist of its width and height. It has overriden equals and hashCode method so that it can be           used to compare with other objects. It also has a detailed implementation for the textRender method, which returns a         string representation of the shape details. 
    
    - **ShapeFactory**: This class is used as a factory pattern to generate an abstract shape according to the given             paramenters.  

    - **ShapeType**: An enum to represent a shape type. Currently, it includes rectangle and oval and it also has toString       method.
    
    - **ColorType**: This class represents a color using three int values, which are in the order of Red, Green, and Blue.       It contains the methods to get the attributes properly, as well as the overriden toString, equals, and hashCode.
    
    - **Position2D**: This class represents a position, which uses two decimal numbers as x and y of some objects in the         background of animation. It contains the methods to get the attributes properly, as well as the overriden toString,         equals, and hashCode.
    
    - **Screen**: A class that represents the bounding box of the canvas, namely leftmost x value, the topmost y
    value, width, and height of the canvas. It takes in x, y coordinate values and width/height of the bounding box. It also     includes getter methods and toString to get the information of the screen object.
    
    
# Controller
- **Design of Controller**

    - **IController**: A controller interface that enables the program to output the description of the shapes, or draws
    the animation on a canvas.
    
    - **ControllerImpl**: A controller class that enables the program to output the description
    of the shapes, draws the animation on a canvas.

# View
- **Design of View**

    - **IView**: An IView interface that enables two different views including text view and canvas view. It includes           renderText and renderImage methods to render the list of shapes and animation information to the output. It also             includes setCanvas set the canvas size to the given value and getTempo method to get the speed specified for this view.
    
    - **AbstractView**: An Abstract class for various types of views, that extends extends JFrame and implements IView. It       takes in the given tempo in the constructor and the speed has to be positive value. It includes method                       implementations for setCanvas and getTempo to reduce the code redundancy in the concrete views that extend it.
    
    - **FactoryView**: This class is used as a factory pattern to generate an IView according to the given                       paramenters of view type and tempo.
    
    - **TextView**: TextView class extends AbstractView. It renders the text of the motions of all the shapes. It has the       implementation of the method renderText, which takes in shapes and animations and creates a text output with three parts     of the information: 1. shape creation information including all shape's details 2. shape appear and disappear time (in       second) 3. animations on the shape 
    
        An example is as follows:
        
        - Create rectangle R with color (255,0,0) with corner at (200.0,200.0), width: 50 and height 100
        - Create oval C with color (0,0,255) with center at (440.0,70.0), radius: 120 and 60
  
        - R appears at time t=0 and disappear at time t=50 
        - C appears at time t=3 and disappear at time t=50
        
        - R moves from (200.0,200.0) to (300.0,300.0) from time t=5 to t=25
        - C moves from (440.0,70.0) to (440.0,250.0) from time t=10 to t=25
        - C changes color from (0,0,255) to (0,170,85) from time t=25 to t=35
        - R changes width from 50 to 25 from time t=25 to t=35
        - R moves from (300.0,300.0) to (200.0,200.0) from time t=35to t=50
    
    - **ImageView**: ImageView class extends AbstractView. It renders the animation of the motions of all the shapes on
    the canvas according to the given list of shapes. It has a private field called panel, which is a Draw object. In the       implementation of the renderImage method, it calls the Draw object panel to draw the given list of shapes on the canvas.
    
    - **IDraw**: The IDraw interface will draw all the shapes correspondingly in their positions, colors, and sizes on a         canvas.
    
    - **Draw**: A drawing class that will draw all the shapes correspondingly in their positions, colors, and sizes on a         canvas with the given size. It extends JPanel and override the paintComponent method, and it also implements IDraw           interface and override the draw method.
    
    
    
    
