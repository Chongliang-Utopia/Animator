# The Easy Animator
A Java swing GUI application which allows for creation of 2D animation of shapes. 
It's created following the model-view-controller design pattern.

# Model
The overal design of the model consist of two parts, the Data managing part and individual parts that represent 2D shapes and
animations.
The following concrete classes/abstract classes/interfaces are created for the model of the easy animator. 


- **Data Managing Part**

    - **IModel**: This interface represents the operations that can be called on the model. It predefines methods to get a 
    deep copy of all the shapes and a deep copy of the animations in sorted order by starting time or at a given time. 
    It also defines methods to add a shape, delete a shape and add an animation.
    
    - **ModelImpl**: This class implements the IModel interface and represents the concrete model of the animator.
    *Internally*, it stores all the shapes in a Map, and uses the name of the shape as the key to enforce fast look up. 
    It has stores all the animations in a Map, using the start time of the animation as the key since the order of the 
    animation matters.
    *Externally*, it allows to get a deep copy of all the shapes and a deep copy of the animations sorted 
    by starting time. It also include methods to add a shape, delete a shape and add an animation. Additionally,
    it offers a method to get a copy of all the invisible shapes at a given time and a method to get a copy of all the 
    animation which start before a given time. It contains a public method to return a string representation of all the
    shapes and animations.
    
 
- **Animations**   

    - **AbstactAnimation**: This abstract class represents an abstract animation in the model. It contains the common attributes of
    common attributes and methods, including the animation type, shape name, start time
    and end time. It includes common getter of these attributes, as well as a calculateState to
    calculate the current state according to the current time.
    
    - **MoveAnimation**: This class represents an animation to move the shape, it extends the AbstractAnimation
    class. It stores the start position and end position of the animation.
    Apart from methods to return a string representation of the animation, equals and hashcode methods, 
    it also include a method to run the animation and get an updated Abstract shape according to the given time.
    
    - **ChangeColorAnimation**: This class represents an animation to change the color, it extends the AbstractAnimation
    class. It stores the start color and end color of the animation. 
    Apart from methods to return a string representation of the animation, equals and hashcode methods, 
    it also include a method to run the animation and get an updated Abstract shape according to the given time.
    
    - **ScaleAnimation**: A class represents an animation to scale the shape in width or height, it extends
    the AbstractAnimation class. It stores the start dimension and end dimension of the animation.
    Apart from methods to return a string representation of the animation, equals and hashcode methods, 
    it also include a method to run the animation and get an updated Abstract shape according to the given time.
    
    - **AnimationType**: An enum to represent an animation type.
    
- **Shapes** 
    
    - **AbstractShape**: This abstract class respresents an abstract shape in the model, which contains all common               attributes of shapes: a name, type, color, position, display, appear time, disappear time. It can provide some useful       details of shape for others to retrieve. It also tracks all of the properties in the shape that can be modified, the         operations that will be performed on it, and its own text representation. 
    
    - **Oval**: This class extends the AbstractShape and represents an oval object in an animation. It has all of the fields    of an AbstractShape and its dimensions consist of its x radius and y radius. It has overriden equals and hashCode method      so that it can be used to compare with other objects. It also has a detailed implementation for the textRender method,      which returns a string representation of the shape details. 

    - **Rectangle**: This calss represents a rectangle object in an animation. It has all of the fields of an AbstractShape     and its dimenstions consist of its width and height. It has overriden equals and hashCode method so that it can be           used to compare with other objects. It also has a detailed implementation for the textRender method, which returns a         string representation of the shape details. 
    
    - **ShapeFactory**: This class is used as a factory pattern to generate an abstract shape according to the given             paramenters.  

    - **ShapeType**: An enum to represent a shape type. Currently, it includes rectangle and oval and it also has toString       method.
    
    - **ColorType**: This class represents a color using three float values, which are in the order of Red, Green, and Blue.     It contains the methods to get the attributes properly, as well as the overriden toString, equals, and hashCode.
    
    - **Position2D**: This class represents a position, which uses two decimal numbers as x and y of some objects in the         background of animation. It contains the methods to get the attributes properly, as well as the overriden toString,         equals, and hashCode.
