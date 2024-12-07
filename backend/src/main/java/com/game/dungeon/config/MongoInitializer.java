package com.game.dungeon.config;

import com.game.dungeon.model.Question;
import com.game.dungeon.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

@Configuration
public class MongoInitializer {
        Logger logger = LoggerFactory.getLogger(MongoInitializer.class);

        @Bean
        CommandLineRunner init(QuestionRepository questionRepository) {
                return args -> {
                        questionRepository.deleteAll();
                        logger.info("Deleted all questions");

                        // this code can be optimized by using csv file or mongodb seeding
                        List<Question> questions = Arrays.asList(
                                        createQuestion(
                                                        "Which of the following best describes inheritance in OOP?",
                                                        "OOP Basics",
                                                        "EASY",
                                                        Arrays.asList(
                                                                        "A mechanism where one class acquires the properties of another",
                                                                        "A technique to ensure code runs faster",
                                                                        "A pattern of creating objects without a class",
                                                                        "A way to bundle data and methods"),
                                                        "A mechanism where one class acquires the properties of another",
                                                        10,
                                                        "Inheritance allows a class to reuse attributes and methods of another, promoting code reuse and extensibility."),

                                        createQuestion(
                                                        "What is the main purpose of the 'Open/Closed Principle' in SOLID?",
                                                        "SOLID Principles",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "A class should be open for extension but closed for modification",
                                                                        "A class should have one reason to change",
                                                                        "A class should use interfaces over implementations",
                                                                        "A subclass should be substitutable for its superclass"),
                                                        "A class should be open for extension but closed for modification",
                                                        15,
                                                        "The Open/Closed Principle encourages writing code that can be extended with new features without altering existing, tested code."),

                                        createQuestion(
                                                        "In OOP, what is abstraction primarily used for?",
                                                        "OOP Concepts",
                                                        "EASY",
                                                        Arrays.asList(
                                                                        "To hide unnecessary details and show essential features",
                                                                        "To allow classes to inherit from multiple superclasses",
                                                                        "To run code on different operating systems",
                                                                        "To improve code performance"),
                                                        "To hide unnecessary details and show essential features",
                                                        10,
                                                        "Abstraction focuses on the relevant aspects of an object, making complex logic simpler and reducing complexity."),

                                        createQuestion(
                                                        "Which design pattern is used to ensure a class has only one instance?",
                                                        "Design Patterns",
                                                        "EASY",
                                                        Arrays.asList(
                                                                        "Singleton Pattern",
                                                                        "Abstract Factory Pattern",
                                                                        "Adapter Pattern",
                                                                        "Facade Pattern"),
                                                        "Singleton Pattern",
                                                        10,
                                                        "The Singleton Pattern restricts a class to a single instance, ensuring controlled access to that instance."),

                                        createQuestion(
                                                        "Which SOLID principle emphasizes programming to interfaces, not implementations?",
                                                        "SOLID Principles",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "Interface Segregation Principle",
                                                                        "Open/Closed Principle",
                                                                        "Dependency Inversion Principle",
                                                                        "Single Responsibility Principle"),
                                                        "Dependency Inversion Principle",
                                                        15,
                                                        "The Dependency Inversion Principle reduces coupling by relying on abstractions rather than concrete classes, making code more flexible."),

                                        createQuestion(
                                                        "What is the main advantage of using the Adapter pattern?",
                                                        "Design Patterns",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "It allows otherwise incompatible interfaces to work together",
                                                                        "It ensures only one instance of a class is created",
                                                                        "It allows for lazy initialization of objects",
                                                                        "It observes and notifies objects of changes"),
                                                        "It allows otherwise incompatible interfaces to work together",
                                                        15,
                                                        "The Adapter Pattern converts one interface into another, enabling classes with otherwise incompatible interfaces to collaborate seamlessly."),

                                        createQuestion(
                                                        "In OOP, what does an 'is-a' relationship represent?",
                                                        "OOP Basics",
                                                        "EASY",
                                                        Arrays.asList(
                                                                        "Inheritance",
                                                                        "Encapsulation",
                                                                        "Composition",
                                                                        "Polymorphism"),
                                                        "Inheritance",
                                                        10,
                                                        "'Is-a' indicates an inheritance relationship, where a subclass is a specialized form of its superclass."),

                                        createQuestion(
                                                        "What is the primary benefit of the Strategy pattern?",
                                                        "Design Patterns",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "It enables selecting an algorithm at runtime",
                                                                        "It ensures a class has only one instance",
                                                                        "It converts one interface into another",
                                                                        "It notifies observers of state changes"),
                                                        "It enables selecting an algorithm at runtime",
                                                        15,
                                                        "The Strategy Pattern allows you to interchange algorithms without changing the code that uses them, enhancing flexibility."),

                                        createQuestion(
                                                        "Which principle in SOLID encourages splitting interfaces into smaller ones?",
                                                        "SOLID Principles",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "Interface Segregation Principle",
                                                                        "Single Responsibility Principle",
                                                                        "Liskov Substitution Principle",
                                                                        "Open/Closed Principle"),
                                                        "Interface Segregation Principle",
                                                        15,
                                                        "The Interface Segregation Principle prevents clients from depending on methods they don’t use, promoting more focused and cohesive interfaces."),

                                        createQuestion(
                                                        "Which OOP concept allows methods to be redefined in subclasses?",
                                                        "OOP Concepts",
                                                        "EASY",
                                                        Arrays.asList(
                                                                        "Polymorphism",
                                                                        "Encapsulation",
                                                                        "Abstraction",
                                                                        "Aggregation"),
                                                        "Polymorphism",
                                                        10,
                                                        "Polymorphism lets subclasses override or provide different implementations for methods defined in their superclass."),

                                        createQuestion(
                                                        "What does the Factory Method pattern achieve?",
                                                        "Design Patterns",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "It delegates object creation to subclasses",
                                                                        "It ensures only one instance of a class",
                                                                        "It provides a simplified interface to a complex subsystem",
                                                                        "It notifies objects of changes"),
                                                        "It delegates object creation to subclasses",
                                                        15,
                                                        "The Factory Method Pattern shifts object creation logic to subclasses, making it easier to introduce new product types without modifying existing code."),

                                        createQuestion(
                                                        "Which SOLID principle ensures that derived classes can be substituted for their base classes?",
                                                        "SOLID Principles",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "Liskov Substitution Principle",
                                                                        "Open/Closed Principle",
                                                                        "Single Responsibility Principle",
                                                                        "Interface Segregation Principle"),
                                                        "Liskov Substitution Principle",
                                                        15,
                                                        "The Liskov Substitution Principle ensures subclasses behave like their superclasses, allowing them to be used interchangeably without surprises."),

                                        createQuestion(
                                                        "Which pattern provides a unified interface to a set of interfaces in a subsystem?",
                                                        "Design Patterns",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "Facade Pattern",
                                                                        "Decorator Pattern",
                                                                        "Bridge Pattern",
                                                                        "Visitor Pattern"),
                                                        "Facade Pattern",
                                                        15,
                                                        "The Facade Pattern provides a simpler, high-level interface to a complex subsystem, making it easier to use."),

                                        createQuestion(
                                                        "What is the purpose of the Bridge pattern?",
                                                        "Design Patterns",
                                                        "HARD",
                                                        Arrays.asList(
                                                                        "Decouple an abstraction from its implementation",
                                                                        "Ensure a class has only one instance",
                                                                        "Allow objects to be created without specifying their concrete type",
                                                                        "Convert an interface into another interface"),
                                                        "Decouple an abstraction from its implementation",
                                                        20,
                                                        "The Bridge Pattern separates abstractions from their implementations, allowing each to vary independently."),

                                        createQuestion(
                                                        "In OOP, what does a 'has-a' relationship indicate?",
                                                        "OOP Basics",
                                                        "EASY",
                                                        Arrays.asList(
                                                                        "Composition or Aggregation",
                                                                        "Inheritance",
                                                                        "Polymorphism",
                                                                        "Abstraction"),
                                                        "Composition or Aggregation",
                                                        10,
                                                        "'Has-a' represents that one class contains or uses another, forming a composition or aggregation relationship."),

                                        createQuestion(
                                                        "Which pattern allows you to add responsibilities to objects dynamically?",
                                                        "Design Patterns",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "Decorator Pattern",
                                                                        "Adapter Pattern",
                                                                        "Flyweight Pattern",
                                                                        "Composite Pattern"),
                                                        "Decorator Pattern",
                                                        15,
                                                        "The Decorator Pattern attaches new behaviors to objects without modifying their code, enhancing flexibility and reusability."),

                                        createQuestion(
                                                        "What does the Single Responsibility Principle (SRP) aim to achieve?",
                                                        "SOLID Principles",
                                                        "EASY",
                                                        Arrays.asList(
                                                                        "Each class should have only one reason to change",
                                                                        "Classes should be open for extension but closed for modification",
                                                                        "Derived classes should be interchangeable with base classes",
                                                                        "Clients should not depend on details; details should depend on abstractions"),
                                                        "Each class should have only one reason to change",
                                                        10,
                                                        "SRP ensures that each class focuses on a single responsibility, making code easier to understand, maintain, and extend."),

                                        createQuestion(
                                                        "What is the primary use of the Composite pattern?",
                                                        "Design Patterns",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "To treat individual objects and groups of objects uniformly",
                                                                        "To ensure only one instance of a class",
                                                                        "To delegate object creation to subclasses",
                                                                        "To monitor and react to events in other objects"),
                                                        "To treat individual objects and groups of objects uniformly",
                                                        15,
                                                        "The Composite Pattern allows you to work with individual objects and collections in a consistent way, simplifying client code."),

                                        createQuestion(
                                                        "What is the main goal of the Dependency Inversion Principle?",
                                                        "SOLID Principles",
                                                        "HARD",
                                                        Arrays.asList(
                                                                        "Depend on abstractions, not on concrete implementations",
                                                                        "A class should have only one reason to change",
                                                                        "Classes should be open for extension but closed for modification",
                                                                        "Interfaces should be segregated"),
                                                        "Depend on abstractions, not on concrete implementations",
                                                        20,
                                                        "The Dependency Inversion Principle reduces coupling by ensuring high-level modules don’t rely on low-level details."),

                                        createQuestion(
                                                        "Which pattern separates the construction of a complex object from its representation?",
                                                        "Design Patterns",
                                                        "HARD",
                                                        Arrays.asList(
                                                                        "Builder Pattern",
                                                                        "Prototype Pattern",
                                                                        "Command Pattern",
                                                                        "Interpreter Pattern"),
                                                        "Builder Pattern",
                                                        20,
                                                        "The Builder Pattern allows you to create complex objects step-by-step, enabling varied representations using the same construction process."),

                                        createQuestion(
                                                        "In OOP, what is the purpose of an interface?",
                                                        "OOP Basics",
                                                        "EASY",
                                                        Arrays.asList(
                                                                        "To define a contract that classes must follow",
                                                                        "To store class instances",
                                                                        "To implement code reuse",
                                                                        "To wrap groups of objects"),
                                                        "To define a contract that classes must follow",
                                                        10,
                                                        "An interface outlines methods and behaviors that implementing classes must provide, ensuring consistent APIs."),

                                        createQuestion(
                                                        "Which pattern lets you traverse elements of a collection without exposing its representation?",
                                                        "Design Patterns",
                                                        "HARD",
                                                        Arrays.asList(
                                                                        "Iterator Pattern",
                                                                        "Memento Pattern",
                                                                        "Observer Pattern",
                                                                        "State Pattern"),
                                                        "Iterator Pattern",
                                                        20,
                                                        "The Iterator Pattern provides a standard way to loop through collections without revealing their underlying representation."),

                                        createQuestion(
                                                        "What is the main reason to use the Proxy pattern?",
                                                        "Design Patterns",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "To control access to an object",
                                                                        "To ensure only one instance of a class",
                                                                        "To define a family of algorithms",
                                                                        "To convert one interface into another"),
                                                        "To control access to an object",
                                                        15,
                                                        "The Proxy Pattern acts as a stand-in, adding control, caching, or security before the real object is accessed."),

                                        createQuestion(
                                                        "Which SOLID principle is violated if a subclass cannot stand in for its parent class?",
                                                        "SOLID Principles",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "Liskov Substitution Principle",
                                                                        "Single Responsibility Principle",
                                                                        "Open/Closed Principle",
                                                                        "Interface Segregation Principle"),
                                                        "Liskov Substitution Principle",
                                                        15,
                                                        "If a subclass cannot fully replace its parent class, the Liskov Substitution Principle is broken, reducing reliability."),

                                        createQuestion(
                                                        "What is the key concept of aggregation in OOP?",
                                                        "OOP Basics",
                                                        "EASY",
                                                        Arrays.asList(
                                                                        "A class can have references to other classes without ownership",
                                                                        "One class derives from another class",
                                                                        "A single object handles multiple responsibilities",
                                                                        "Hiding the internal state of an object"),
                                                        "A class can have references to other classes without ownership",
                                                        10,
                                                        "Aggregation represents a 'weak has-a' relationship, where the contained object can exist independently."),

                                        createQuestion(
                                                        "What advantage does the Abstract Factory pattern provide?",
                                                        "Design Patterns",
                                                        "HARD",
                                                        Arrays.asList(
                                                                        "It creates families of related objects without specifying their concrete classes",
                                                                        "It ensures an object’s creation is restricted to one instance",
                                                                        "It allows an object to alter its behavior when its internal state changes",
                                                                        "It enables communication between objects without them knowing each other's classes"),
                                                        "It creates families of related objects without specifying their concrete classes",
                                                        20,
                                                        "The Abstract Factory Pattern provides a way to create related objects as a family, ensuring compatibility and consistency."),

                                        createQuestion(
                                                        "What is the primary use of the Command pattern?",
                                                        "Design Patterns",
                                                        "HARD",
                                                        Arrays.asList(
                                                                        "To encapsulate a request as an object",
                                                                        "To ensure a class has only one instance",
                                                                        "To provide a simplified interface to a set of interfaces",
                                                                        "To decouple an abstraction from its implementation"),
                                                        "To encapsulate a request as an object",
                                                        20,
                                                        "The Command Pattern packages an action as an object, allowing queuing, logging, and undo operations."),

                                        createQuestion(
                                                        "In OOP, what does method overriding achieve?",
                                                        "OOP Concepts",
                                                        "EASY",
                                                        Arrays.asList(
                                                                        "Allows a subclass to provide a specific implementation of a method already defined in its superclass",
                                                                        "Creates multiple methods with the same name but different parameters in the same class",
                                                                        "Hides the details of a method from users",
                                                                        "Converts one interface into another"),
                                                        "Allows a subclass to provide a specific implementation of a method already defined in its superclass",
                                                        10,
                                                        "Method overriding lets subclasses tailor inherited methods to better fit their specific behavior."),

                                        createQuestion(
                                                        "Which pattern allows objects to notify others of state changes without tightly coupling them?",
                                                        "Design Patterns",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "Observer Pattern",
                                                                        "Chain of Responsibility Pattern",
                                                                        "Flyweight Pattern",
                                                                        "Decorator Pattern"),
                                                        "Observer Pattern",
                                                        15,
                                                        "The Observer Pattern enables an object to publish updates, letting subscribed observers react without direct dependencies."),

                                        createQuestion(
                                                        "What is the key idea behind the State pattern?",
                                                        "Design Patterns",
                                                        "HARD",
                                                        Arrays.asList(
                                                                        "An object changes its behavior when its internal state changes",
                                                                        "It ensures only one instance of a class",
                                                                        "It allows the creation of families of related objects",
                                                                        "It provides a global point of access to a resource"),
                                                        "An object changes its behavior when its internal state changes",
                                                        20,
                                                        "The State Pattern encapsulates states as separate objects, enabling an object’s behavior to shift as its state changes."),

                                        createQuestion(
                                                        "Which OOP concept involves using simpler interfaces to reduce coupling between classes?",
                                                        "OOP Concepts",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "Interface Segregation",
                                                                        "Encapsulation",
                                                                        "Polymorphism",
                                                                        "Inheritance"),
                                                        "Interface Segregation",
                                                        15,
                                                        "Interface Segregation advocates smaller, more specific interfaces so that classes only depend on what they actually need."),

                                        createQuestion(
                                                        "What problem does the Flyweight pattern solve?",
                                                        "Design Patterns",
                                                        "HARD",
                                                        Arrays.asList(
                                                                        "Reduces memory usage by sharing common parts of objects",
                                                                        "Ensures only one instance of a class",
                                                                        "Adds responsibilities dynamically to an object",
                                                                        "Provides a simplified interface to a complex system"),
                                                        "Reduces memory usage by sharing common parts of objects",
                                                        20,
                                                        "The Flyweight Pattern minimizes memory usage by reusing common object data across many instances."),

                                        createQuestion(
                                                        "What is the main benefit of applying SOLID principles?",
                                                        "SOLID Principles",
                                                        "EASY",
                                                        Arrays.asList(
                                                                        "Makes code more maintainable and flexible",
                                                                        "Increases code execution speed",
                                                                        "Adds unnecessary complexity",
                                                                        "Allows infinite inheritance chains"),
                                                        "Makes code more maintainable and flexible",
                                                        10,
                                                        "SOLID principles lead to cleaner architecture, making code easier to maintain, extend, and test."),

                                        createQuestion(
                                                        "Which pattern allows extending an object’s functionality without changing its code?",
                                                        "Design Patterns",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "Decorator Pattern",
                                                                        "Bridge Pattern",
                                                                        "Visitor Pattern",
                                                                        "Memento Pattern"),
                                                        "Decorator Pattern",
                                                        15,
                                                        "The Decorator Pattern adds new responsibilities at runtime, promoting open-ended enhancement without altering existing code."),

                                        createQuestion(
                                                        "What is a key outcome of adhering to the Open/Closed Principle?",
                                                        "SOLID Principles",
                                                        "EASY",
                                                        Arrays.asList(
                                                                        "Code is more resilient to changes in requirements",
                                                                        "Code always runs faster",
                                                                        "No new classes are ever needed",
                                                                        "All classes can be modified freely at any time"),
                                                        "Code is more resilient to changes in requirements",
                                                        10,
                                                        "By not modifying existing classes, you add new features without breaking old ones, leading to more stable code."),

                                        createQuestion(
                                                        "Which pattern provides a way to add new operations to classes without changing them?",
                                                        "Design Patterns",
                                                        "HARD",
                                                        Arrays.asList(
                                                                        "Visitor Pattern",
                                                                        "Chain of Responsibility Pattern",
                                                                        "Proxy Pattern",
                                                                        "Interpreter Pattern"),
                                                        "Visitor Pattern",
                                                        20,
                                                        "The Visitor Pattern separates operations from the object structure, allowing you to add behaviors by adding new visitors."),

                                        createQuestion(
                                                        "In OOP, what does composition imply?",
                                                        "OOP Basics",
                                                        "EASY",
                                                        Arrays.asList(
                                                                        "A 'strong has-a' relationship where a part cannot exist independently of the whole",
                                                                        "A 'weak has-a' relationship",
                                                                        "Classes inheriting from multiple parent classes",
                                                                        "Reducing memory footprint"),
                                                        "A 'strong has-a' relationship where a part cannot exist independently of the whole",
                                                        10,
                                                        "Composition binds the lifecycle of the part to the whole, implying stronger ownership compared to aggregation."),

                                        createQuestion(
                                                        "Which pattern allows you to avoid coupling the sender of a request to its receiver by giving multiple objects a chance to handle it?",
                                                        "Design Patterns",
                                                        "HARD",
                                                        Arrays.asList(
                                                                        "Chain of Responsibility Pattern",
                                                                        "Mediator Pattern",
                                                                        "Prototype Pattern",
                                                                        "Singleton Pattern"),
                                                        "Chain of Responsibility Pattern",
                                                        20,
                                                        "The Chain of Responsibility Pattern passes requests along a chain, allowing multiple handlers to attempt to process them."),

                                        createQuestion(
                                                        "What does the Interface Segregation Principle suggest?",
                                                        "SOLID Principles",
                                                        "EASY",
                                                        Arrays.asList(
                                                                        "Classes should not be forced to implement interfaces they do not use",
                                                                        "A class should have only one reason to change",
                                                                        "A class should be open for extension, closed for modification",
                                                                        "Derived classes must be substitutable for their base classes"),
                                                        "Classes should not be forced to implement interfaces they do not use",
                                                        10,
                                                        "Interface Segregation Principle ensures smaller, more focused interfaces, preventing bloated contracts."),

                                        createQuestion(
                                                        "Which pattern defines a family of algorithms and makes them interchangeable at runtime?",
                                                        "Design Patterns",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "Strategy Pattern",
                                                                        "Command Pattern",
                                                                        "Decorator Pattern",
                                                                        "Observer Pattern"),
                                                        "Strategy Pattern",
                                                        15,
                                                        "The Strategy Pattern allows you to switch algorithms easily at runtime, adapting behavior without altering client code."),

                                        createQuestion(
                                                        "In OOP, what is method overloading?",
                                                        "OOP Concepts",
                                                        "EASY",
                                                        Arrays.asList(
                                                                        "Having multiple methods in the same class with the same name but different parameters",
                                                                        "Replacing a method in a subclass",
                                                                        "Hiding internal implementation details",
                                                                        "Allowing a class to have multiple superclasses"),
                                                        "Having multiple methods in the same class with the same name but different parameters",
                                                        10,
                                                        "Method overloading lets you define methods with the same name but different arguments, improving readability and flexibility."),

                                        createQuestion(
                                                        "What does the Mediator pattern accomplish?",
                                                        "Design Patterns",
                                                        "HARD",
                                                        Arrays.asList(
                                                                        "Encapsulates how a set of objects interact by using a mediator object",
                                                                        "Ensures only one instance of a class",
                                                                        "Allows object creation without specifying the exact class",
                                                                        "Adds responsibilities dynamically to objects"),
                                                        "Encapsulates how a set of objects interact by using a mediator object",
                                                        20,
                                                        "The Mediator Pattern centralizes complex communication logic in a mediator, reducing coupling between interacting objects."),

                                        createQuestion(
                                                        "Which principle discourages large, monolithic classes?",
                                                        "SOLID Principles",
                                                        "EASY",
                                                        Arrays.asList(
                                                                        "Single Responsibility Principle",
                                                                        "Open/Closed Principle",
                                                                        "Liskov Substitution Principle",
                                                                        "Dependency Inversion Principle"),
                                                        "Single Responsibility Principle",
                                                        10,
                                                        "SRP reduces monoliths by ensuring each class handles a single concern, making code simpler and more maintainable."),

                                        createQuestion(
                                                        "Which pattern provides a surrogate for another object to control access to it?",
                                                        "Design Patterns",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "Proxy Pattern",
                                                                        "Factory Method Pattern",
                                                                        "Builder Pattern",
                                                                        "Composite Pattern"),
                                                        "Proxy Pattern",
                                                        15,
                                                        "The Proxy Pattern introduces a stand-in object that manages access to a target object, adding control and flexibility."),

                                        createQuestion(
                                                        "What does the Liskov Substitution Principle ensure?",
                                                        "SOLID Principles",
                                                        "EASY",
                                                        Arrays.asList(
                                                                        "Subclasses can stand in for their base classes without altering correctness",
                                                                        "Classes should depend on abstractions",
                                                                        "Interfaces should be broken down into smaller ones",
                                                                        "Classes should have only one reason to change"),
                                                        "Subclasses can stand in for their base classes without altering correctness",
                                                        10,
                                                        "LSP ensures subclasses fully behave like their parents, maintaining correctness and consistency."),

                                        createQuestion(
                                                        "Which pattern helps when creating complex objects by specifying a construction process step-by-step?",
                                                        "Design Patterns",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "Builder Pattern",
                                                                        "Iterator Pattern",
                                                                        "Memento Pattern",
                                                                        "Bridge Pattern"),
                                                        "Builder Pattern",
                                                        15,
                                                        "The Builder Pattern assembles complex objects incrementally, offering more control over the construction process."),

                                        createQuestion(
                                                        "Which principle suggests that classes should depend on abstractions rather than concrete implementations?",
                                                        "SOLID Principles",
                                                        "EASY",
                                                        Arrays.asList(
                                                                        "Dependency Inversion Principle",
                                                                        "Single Responsibility Principle",
                                                                        "Open/Closed Principle",
                                                                        "Interface Segregation Principle"),
                                                        "Dependency Inversion Principle",
                                                        10,
                                                        "By depending on abstractions, you make code less brittle and more adaptable to change."),

                                        createQuestion(
                                                        "Which pattern allows restoring an object's state to a previous state?",
                                                        "Design Patterns",
                                                        "HARD",
                                                        Arrays.asList(
                                                                        "Memento Pattern",
                                                                        "Iterator Pattern",
                                                                        "Template Method Pattern",
                                                                        "Prototype Pattern"),
                                                        "Memento Pattern",
                                                        20,
                                                        "The Memento Pattern captures an object’s internal state, allowing it to be restored later without exposing its internals."),

                                        createQuestion(
                                                        "What is the main idea behind abstraction in OOP?",
                                                        "OOP Concepts",
                                                        "EASY",
                                                        Arrays.asList(
                                                                        "Focusing on essential qualities while hiding unnecessary details",
                                                                        "Ensuring a class has only one instance",
                                                                        "Notifying objects when another object changes state",
                                                                        "Adapting one interface to another"),
                                                        "Focusing on essential qualities while hiding unnecessary details",
                                                        10,
                                                        "Abstraction simplifies complexity by modeling concepts relevant to the problem while excluding extraneous details."),

                                        createQuestion(
                                                        "Which pattern defines the skeleton of an algorithm, letting subclasses redefine certain steps?",
                                                        "Design Patterns",
                                                        "HARD",
                                                        Arrays.asList(
                                                                        "Template Method Pattern",
                                                                        "Facade Pattern",
                                                                        "Command Pattern",
                                                                        "State Pattern"),
                                                        "Template Method Pattern",
                                                        20,
                                                        "The Template Method Pattern outlines a generic algorithm structure and allows subclasses to customize specific steps."),

                                        createQuestion(
                                                        "What do SOLID principles generally help with?",
                                                        "SOLID Principles",
                                                        "EASY",
                                                        Arrays.asList(
                                                                        "Improving code quality, maintainability, and scalability",
                                                                        "Guaranteeing the fastest possible code",
                                                                        "Eliminating the need for testing",
                                                                        "Ensuring no bugs can exist"),
                                                        "Improving code quality, maintainability, and scalability",
                                                        10,
                                                        "SOLID principles provide a foundation for building robust, adaptable, and testable code bases."),

                                        createQuestion(
                                                        "Which pattern creates new objects by copying existing ones?",
                                                        "Design Patterns",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "Prototype Pattern",
                                                                        "Decorator Pattern",
                                                                        "Flyweight Pattern",
                                                                        "Chain of Responsibility Pattern"),
                                                        "Prototype Pattern",
                                                        15,
                                                        "The Prototype Pattern clones existing objects, providing a flexible alternative to traditional object creation."),

                                        createQuestion(
                                                        "In OOP, what is the difference between an abstract class and an interface?",
                                                        "OOP Concepts",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "An abstract class can have implemented methods, while an interface typically cannot",
                                                                        "An interface can have fields, while an abstract class cannot",
                                                                        "There is no difference, they are the same concept",
                                                                        "An abstract class cannot be subclassed, while an interface can"),
                                                        "An abstract class can have implemented methods, while an interface typically cannot",
                                                        15,
                                                        "Abstract classes provide a partial implementation, while interfaces usually define a contract with no default implementations."),

                                        createQuestion(
                                                        "Which pattern separates an object’s algorithm from the object structure on which it operates?",
                                                        "Design Patterns",
                                                        "HARD",
                                                        Arrays.asList(
                                                                        "Visitor Pattern",
                                                                        "Factory Pattern",
                                                                        "Command Pattern",
                                                                        "Observer Pattern"),
                                                        "Visitor Pattern",
                                                        20,
                                                        "The Visitor Pattern lets you add new operations to existing object structures without modifying the objects themselves."),

                                        createQuestion(
                                                        "Which principle helps prevent classes from doing too many things?",
                                                        "SOLID Principles",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "Single Responsibility Principle",
                                                                        "Liskov Substitution Principle",
                                                                        "Open/Closed Principle",
                                                                        "Dependency Inversion Principle"),
                                                        "Single Responsibility Principle",
                                                        15,
                                                        "The Single Responsibility Principle keeps classes focused, making them simpler, cleaner, and easier to maintain."),

                                        createQuestion(
                                                        "Which pattern allows an object to change its behavior when its internal state changes?",
                                                        "Design Patterns",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "State Pattern",
                                                                        "Factory Pattern",
                                                                        "Observer Pattern",
                                                                        "Builder Pattern"),
                                                        "State Pattern",
                                                        15,
                                                        "By encapsulating state-specific behavior in separate objects, the State Pattern dynamically alters an object's actions."),

                                        createQuestion(
                                                        "Which OOP concept ensures that the internal representation of an object is hidden from the outside?",
                                                        "OOP Concepts",
                                                        "EASY",
                                                        Arrays.asList(
                                                                        "Encapsulation",
                                                                        "Inheritance",
                                                                        "Polymorphism",
                                                                        "Composition"),
                                                        "Encapsulation",
                                                        10,
                                                        "Encapsulation protects the internal state by exposing only controlled interfaces, reducing unwanted dependencies."),

                                        createQuestion(
                                                        "Which pattern encapsulates a request as an object, allowing you to parameterize clients with different requests?",
                                                        "Design Patterns",
                                                        "MEDIUM",
                                                        Arrays.asList(
                                                                        "Command Pattern",
                                                                        "Facade Pattern",
                                                                        "Adapter Pattern",
                                                                        "Iterator Pattern"),
                                                        "Command Pattern",
                                                        15,
                                                        "The Command Pattern turns actions into objects, facilitating undo operations, logging, and dynamic command handling."));

                        questionRepository.saveAll(questions);
                        logger.info("Questions saved: " + questions.size());
                };
        }

        private Question createQuestion(
                        String questionText,
                        String category,
                        String difficulty,
                        List<String> options,
                        String correctAnswer,
                        int points,
                        String explanation) {
                Question question = new Question();
                question.setQuestion(questionText);
                question.setCategory(category);
                question.setDifficulty(difficulty);
                question.setOptions(options);
                question.setCorrectAnswer(correctAnswer);
                question.setPoints(points);
                question.setExplanation(explanation);
                return question;
        }
}