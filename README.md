# The Easy Animator
A Java swing GUI application which allows for creation of 2D animation of shapes. 
It's created following the model-view-controller design pattern.

# Model
The overral design of the model consist of two parts, the Data managing part and individual parts that represent 2D shapes and
animations.
The following concrete classes/abstract classes/interfaces are created for the model of the easy animator. 


- **Data Managing Part**

    - **IModel**: This interface represents the operations that can be called on the model.It predefines methods to get a 
    deep copy of all the shapes and a deep copy of the animations in sorted order by starting time. It also defines methods to 
    add a shape, delete a shape and add an animation.
    
    - **ModelImpl**: This class implements the IModel interface and represents the concrete model of the animator.
    *Internally*, it stores all the shapes in a Map, and uses the name of the shape as the key to enforce fast look up. 
    It has stores all the animations in a Map, using the start time of the animation as the key since the order of the 
    animation matters.
    *Externally*, it allows to get a deep copy of all the shapes and a deep copy of the animations sorted 
    by starting time. It also include methods to add a shape, delete a shape and add an animation.
    
 
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
    
