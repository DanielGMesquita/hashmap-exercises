# HashMap Interview Problems

This project contains a series of common HashMap-related interview problems and their solutions. Each problem demonstrates a different aspect of HashMap behavior and potential pitfalls.

## Problem 1: Incorrect hashCode() and equals() Implementation

### Overview
This problem demonstrates the importance of proper hashCode() and equals() implementations when using objects as keys in a HashMap.

### Key Concepts
- **hashCode() Contract**: Objects that are equal must have the same hashCode
- **equals() Contract**: Reflexive, symmetric, transitive, and consistent
- **HashMap Bucketing**: How HashMap uses hashCode to distribute entries
- **Null Handling**: Proper handling of null values in equals() and hashCode()

### Test Cases
1. **Missing hashCode() Implementation**
   - Demonstrates that equal objects won't be found in HashMap without hashCode()
   - Shows how HashMap relies on hashCode for bucket placement

2. **Inconsistent hashCode()**
   - Shows problems when hashCode changes for the same object
   - Illustrates why hashCode must be consistent

3. **Incorrect equals() Implementation**
   - Demonstrates issues with partial equality checks
   - Shows how incorrect equals can lead to duplicate keys

4. **Correct Implementation**
   - Shows proper implementation of both hashCode() and equals()
   - Demonstrates correct HashMap behavior

5. **Equals Contract in Inheritance**
   - Shows how to maintain equals contract in class hierarchies
   - Demonstrates symmetry and transitivity requirements

6. **Null Handling**
   - Shows proper handling of null values
   - Demonstrates defensive programming in equals() and hashCode()

### Solution Strategy
- Always implement both hashCode() and equals() together
- Use Objects.hash() for consistent hashCode implementation
- Follow the equals contract strictly
- Handle null values properly
- Consider inheritance implications

## Problem 2: Concurrent Modification Issues

### Overview
This problem demonstrates the challenges of concurrent access to HashMap and how to handle them safely.

### Key Concepts
- **ConcurrentModificationException**: When and why it occurs
- **Thread Safety**: Different approaches to thread-safe collections
- **Atomic Operations**: Safe concurrent modifications
- **Iteration Safety**: Safe ways to modify during iteration

### Test Cases
1. **Basic Concurrent Modification**
   - Shows ConcurrentModificationException during iteration
   - Demonstrates why direct modification during iteration is unsafe

2. **ConcurrentHashMap Usage**
   - Shows safe concurrent modifications
   - Demonstrates thread-safe iteration

3. **Iterator's remove() Method**
   - Shows safe removal during iteration
   - Demonstrates proper modification patterns

4. **Multi-threaded Access**
   - Shows problems with concurrent HashMap access
   - Demonstrates race conditions and inconsistencies

5. **Collections.synchronizedMap**
   - Shows limitations of synchronized wrappers
   - Demonstrates why synchronization alone isn't enough

6. **CopyOnWriteArrayList**
   - Shows alternative approach for safe iteration
   - Demonstrates snapshot-based iteration

7. **ConcurrentHashMap Compute Operations**
   - Shows atomic compute operations
   - Demonstrates thread-safe value updates

8. **ConcurrentHashMap Merge Operations**
   - Shows atomic merge operations
   - Demonstrates safe value combination

### Solution Strategy
- Use ConcurrentHashMap for concurrent access
- Prefer atomic operations over manual synchronization
- Use iterator's remove() for safe removal
- Consider CopyOnWriteArrayList for read-heavy scenarios
- Use compute/merge operations for atomic updates

## Problem 3: Hash Collisions and Performance

### Overview
This problem demonstrates how hash collisions affect HashMap performance and how to handle them.

### Key Concepts
- **Hash Collisions**: When different keys produce the same hash code
- **Bucket Distribution**: How HashMap distributes entries across buckets
- **Performance Impact**: How collisions affect lookup time
- **Load Factor**: Relationship between size and capacity

### Test Cases
1. **Basic Hash Collision**
   - Shows how collisions occur
   - Demonstrates bucket distribution

2. **Performance Impact**
   - Shows how collisions affect lookup time
   - Demonstrates O(1) vs O(n) behavior

3. **Load Factor Impact**
   - Shows how load factor affects collisions
   - Demonstrates resizing behavior

4. **Custom Hash Function**
   - Shows how to implement better hash functions
   - Demonstrates improved distribution

### Solution Strategy
- Implement good hashCode() methods
- Monitor collision rates
- Choose appropriate initial capacity
- Consider load factor impact
- Use immutable keys when possible

## Problem 4: equals() vs == Operator

### Overview
This problem demonstrates the difference between equals() and == when working with HashMap keys.

### Key Concepts
- **Reference Equality**: == operator compares object references
- **Value Equality**: equals() compares object contents
- **HashMap Key Lookup**: How HashMap uses equals() for key comparison
- **String Interning**: Impact on string comparisons

### Test Cases
1. **Reference vs Value Equality**
   - Shows difference between == and equals()
   - Demonstrates impact on HashMap lookups

2. **String Comparison**
   - Shows string interning effects
   - Demonstrates proper string comparison

3. **Custom Object Comparison**
   - Shows proper equals() implementation
   - Demonstrates consistent behavior

### Solution Strategy
- Always use equals() for object comparison
- Implement proper equals() methods
- Consider string interning effects
- Document comparison behavior
- Test edge cases

## Problem 5: Concurrent Modification

### Overview
This problem demonstrates concurrent modification issues in HashMap and how to handle them.

### Key Concepts
- **ConcurrentModificationException**: When and why it occurs
- **Thread Safety**: Different approaches to thread-safe collections
- **Atomic Operations**: Safe concurrent modifications
- **Iteration Safety**: Safe ways to modify during iteration

### Test Cases
1. **Basic Concurrent Modification**
   - Shows ConcurrentModificationException
   - Demonstrates unsafe modification

2. **Thread Safety Solutions**
   - Shows different thread-safe approaches
   - Demonstrates proper synchronization

3. **Atomic Operations**
   - Shows atomic update operations
   - Demonstrates safe concurrent access

### Solution Strategy
- Use ConcurrentHashMap for concurrent access
- Implement proper synchronization
- Use atomic operations when possible
- Consider read/write patterns
- Document thread safety guarantees

## Problem 6: Null Key Handling

### Overview
This problem demonstrates how HashMap handles null keys and values.

### Key Concepts
- **Null Keys**: How HashMap handles null as a key
- **Null Values**: How HashMap handles null as a value
- **Null Safety**: Proper null handling in implementations
- **Default Behavior**: HashMap's default null handling

### Test Cases
1. **Null Key Behavior**
   - Shows how HashMap handles null keys
   - Demonstrates lookup behavior

2. **Null Value Behavior**
   - Shows how HashMap handles null values
   - Demonstrates storage behavior

3. **Custom Null Handling**
   - Shows how to implement custom null handling
   - Demonstrates safe null usage

### Solution Strategy
- Document null handling behavior
- Implement proper null checks
- Consider null safety in equals()/hashCode()
- Use Optional when appropriate
- Test null edge cases

## Problem 7: Load Factor and Performance Issues

### Overview
This problem demonstrates how load factor and initial capacity affect HashMap performance and memory usage.

### Key Concepts
- **Load Factor**: Ratio of entries to capacity that triggers resizing
- **Initial Capacity**: Starting size of the HashMap
- **Resizing**: Process of increasing capacity when load factor threshold is reached
- **Performance Impact**: How load factor affects operation speed and memory usage

### Test Cases
1. **Default Load Factor Behavior**
   - Shows how HashMap resizes at default load factor (0.75)
   - Demonstrates resizing threshold calculation

2. **Custom Load Factor**
   - Shows how to use custom load factors
   - Demonstrates resizing with different thresholds

3. **High Load Factor Performance**
   - Shows performance impact of high load factors
   - Demonstrates increased collision rates

4. **Initial Capacity Impact**
   - Shows how initial capacity affects performance
   - Demonstrates importance of proper capacity sizing

5. **Memory Usage**
   - Shows memory implications of different load factors
   - Demonstrates trade-off between memory and performance

### Solution Strategy
- Choose appropriate initial capacity based on expected size
- Consider memory vs performance trade-offs when setting load factor
- Monitor collision rates in performance-critical applications
- Use higher load factors for memory-constrained environments
- Use lower load factors for performance-critical applications

## Problem 8: Key Ordering and Map Implementations

### Overview
This problem demonstrates different Map implementations and their ordering characteristics.

### Key Concepts
- **HashMap Ordering**: No guaranteed order
- **LinkedHashMap**: Maintains insertion or access order
- **TreeMap**: Maintains natural or custom ordering
- **Performance Characteristics**: Different implementations have different performance profiles

### Test Cases
1. **HashMap Ordering**
   - Shows that HashMap doesn't maintain order
   - Demonstrates random key distribution

2. **LinkedHashMap Ordering**
   - Shows insertion order preservation
   - Demonstrates ordered iteration

3. **LinkedHashMap Access Order**
   - Shows access-based ordering
   - Demonstrates LRU-like behavior

4. **TreeMap Natural Ordering**
   - Shows natural ordering of keys
   - Demonstrates sorted iteration

5. **TreeMap Custom Ordering**
   - Shows custom comparator usage
   - Demonstrates flexible sorting

6. **Performance Comparison**
   - Shows performance differences between implementations
   - Demonstrates trade-offs between order and speed

### Solution Strategy
- Use HashMap for unordered, fast access
- Use LinkedHashMap for ordered iteration
- Use TreeMap for sorted access
- Consider performance implications of ordering
- Choose implementation based on access patterns

## Problem 9: Serialization and Cloning

### Overview
This problem demonstrates HashMap serialization, cloning, and deep copying.

### Key Concepts
- **Serialization**: Converting objects to byte streams
- **Cloning**: Creating shallow copies
- **Deep Copying**: Creating independent copies of all objects
- **Transient Fields**: Fields excluded from serialization
- **Custom Serialization**: Implementing custom serialization logic

### Test Cases
1. **Basic Serialization**
   - Shows standard HashMap serialization
   - Demonstrates proper deserialization

2. **Custom Object Serialization**
   - Shows serialization of custom objects
   - Demonstrates Serializable interface usage

3. **HashMap Cloning**
   - Shows shallow copy behavior
   - Demonstrates shared object references

4. **Deep Copy**
   - Shows how to create independent copies
   - Demonstrates proper object copying

5. **Transient Fields**
   - Shows how transient fields are handled
   - Demonstrates field exclusion from serialization

6. **Custom Serialization**
   - Shows custom serialization methods
   - Demonstrates advanced serialization control

### Solution Strategy
- Implement Serializable for custom objects
- Use transient for non-serializable fields
- Consider deep copying for mutable objects
- Implement custom serialization when needed
- Handle versioning with serialVersionUID

## Running the Tests

To run the tests, use the following Maven command:

```bash
mvn test
```

To run specific test classes:

```bash
mvn test -Dtest=IncorrectHashCodeEqualsProblemTest
mvn test -Dtest=ConcurrentModificationProblemTest
```

## Best Practices

1. **For HashMap Keys**:
   - Always implement both hashCode() and equals()
   - Make keys immutable when possible
   - Use Objects.hash() for consistent hashCode
   - Handle null values properly

2. **For Concurrent Access**:
   - Use ConcurrentHashMap for concurrent scenarios
   - Prefer atomic operations over manual synchronization
   - Use iterator's remove() for safe removal
   - Consider read/write patterns when choosing collection type

3. **For Testing**:
   - Test both success and failure cases
   - Include edge cases (null, empty collections)
   - Test concurrent scenarios
   - Verify equals contract compliance
   - Test hashCode consistency

## Common Pitfalls

1. **HashMap Keys**:
   - Forgetting to implement hashCode()
   - Inconsistent hashCode implementation
   - Incorrect equals implementation
   - Not handling null values
   - Mutable keys causing inconsistency

2. **Concurrent Access**:
   - Direct modification during iteration
   - Insufficient synchronization
   - Assuming Collections.synchronizedMap is enough
   - Not using atomic operations when available
   - Ignoring concurrent modification exceptions

## Running the Project and Exercising Concepts

This project is designed to help you understand and practice various HashMap-related concepts through coding exercises and tests. Follow the steps below to run the project and explore the concepts:

### Prerequisites
- Ensure you have [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or higher installed on your machine.
- Install [Maven](https://maven.apache.org/download.cgi) for dependency management and project building.

### Cloning the Repository
1. Clone the repository to your local machine:
   ```bash
   git clone <repository-url>
   cd <repository-directory>
   ```

### Building the Project
2. Build the project using Maven:
   ```bash
   mvn clean install
   ```

### Running the Tests
3. To run all tests and verify the implementation:
   ```bash
   mvn test
   ```

4. To run specific test classes, use:
   ```bash
   mvn test -Dtest=IncorrectHashCodeEqualsProblemTest
   mvn test -Dtest=ConcurrentModificationProblemTest
   ```

### Exploring the Concepts
5. Each problem is implemented in its respective class under the `src/main/java/com/interview/hashmapdemo/problemX` directory. You can explore the following:
   - **Problem 1**: Understand the importance of `hashCode()` and `equals()` methods.
   - **Problem 2**: Learn about concurrent modification issues and how to handle them.
   - **Problem 3**: Explore hash collisions and their impact on performance.
   - **Problem 4**: Differentiate between `equals()` and `==` operator.
   - **Problem 5**: Investigate concurrent modification scenarios.
   - **Problem 6**: Handle null keys and values in HashMap.
   - **Problem 7**: Analyze load factor and performance issues.
   - **Problem 8**: Understand key ordering in different Map implementations.
   - **Problem 9**: Learn about serialization and cloning of HashMap.

### Modifying and Testing
6. Feel free to modify the implementation classes to test different scenarios or edge cases. You can add new test cases in the corresponding test classes located in `src/test/java/com/interview/hashmapdemo/problemX`.

### Using the Project in Interviews
7. This project can be used as a coding exercise in interviews. You can:
   - Discuss the problems and their solutions with the interviewer.
   - Demonstrate your understanding of HashMap concepts by explaining the code.
   - Solve problems live by modifying the code and running tests.

By following these steps, you can effectively use this project to deepen your understanding of HashMap concepts and prepare for coding interviews.

## Additional Resources

- [Java HashMap Documentation](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/HashMap.html)
- [Java ConcurrentHashMap Documentation](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/ConcurrentHashMap.html)
- [Java Collections Framework](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/package-summary.html)
- [Java Concurrency Tutorial](https://docs.oracle.com/javase/tutorial/essential/concurrency/)