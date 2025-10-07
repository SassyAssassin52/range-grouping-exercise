# Range Grouping Exercise

A Java implementation that produces a comma-delimited list of numbers, grouping sequential numbers into ranges.

## Overview

This project implements a solution that takes an array of integers and converts it into a comma-delimited string where consecutive numbers are grouped into ranges using the format `start-end`. Individual numbers that are not part of a sequence are displayed as single values.

### Examples
- `[1, 2, 3, 5, 7, 8, 9]` → `"1-3,5,7-9"`
- `[1, 3, 5, 7, 9]` → `"1,3,5,7,9"`
- `[1, 2, 3, 4, 5]` → `"1-5"`
- `[]` → `""`
- `[42]` → `"42"`

## Requirements Met

**Java 8+**: Uses Java 8 features including streams, lambdas, and method references  
**Interface Implementation**: Implements the `RangeGrouper` interface  
**Comprehensive Testing**: Full JUnit 5 test suite with 24+ test cases  
**GitHub Ready**: Complete project structure ready for GitHub submission  
**Maven Build**: Professional build configuration with dependencies  

## Project Structure

```
range-grouping-exercise/
├── src/
│   ├── main/java/com/example/rangegrouping/
│   │   ├── RangeGrouper.java          # Interface definition
│   │   ├── RangeGrouperImpl.java      # Main implementation
│   │   └── RangeGrouperDemo.java      # Demonstration class
│   └── test/java/com/example/rangegrouping/
│       └── RangeGrouperImplTest.java  # Comprehensive test suite
├── pom.xml                            # Maven configuration
└── README.md                          # This file
```

## Key Features

### Algorithm Efficiency
- Handles duplicate removal automatically
- Processes unsorted input correctly

### Java 8+ Features Used
- **Streams API**: For data processing and transformations
- **Method References**: Clean, readable code style
- **Optional**: Safe handling of edge cases

### Robust Error Handling
- Null input validation with meaningful error messages
- Edge case handling (empty arrays, single elements)
- Support for negative numbers and extreme values
- Comprehensive boundary condition testing

## Installation & Usage

### Prerequisites
- Java 8 or higher
- Maven 3.6+ (optional, for building)

### Building the Project
```bash
# Clone the repository (when available on GitHub)
git clone <repository-url>
cd range-grouping-exercise

# Build with Maven
mvn clean compile

# Run tests
mvn test

# Run demo
java -cp target/classes com.example.rangegrouping.RangeGrouperDemo
```

### Using the Implementation

```java
import com.example.rangegrouping.RangeGrouper;
import com.example.rangegrouping.RangeGrouperImpl;

// Create an instance
RangeGrouper rangeGrouper = new RangeGrouperImpl();

// Use the implementation
int[] numbers = {1, 2, 3, 5, 7, 8, 9};
String result = rangeGrouper.groupIntoRanges(numbers);
System.out.println(result); // Output: "1-3,5,7-9"
```

## Testing

The project includes comprehensive testing:

### Basic Functionality
- Empty arrays
- Single elements
- Two non-sequential numbers
- Sequential number ranges

### Complex Scenarios
- Mixed ranges and individual numbers
- Unsorted input arrays
- Duplicate number handling
- Negative number support
- Large range processing

### Edge Cases
- Null input validation
- Integer boundary values (MIN_VALUE, MAX_VALUE)
- Scattered single elements


Run tests with detailed output:
```bash
mvn test -Dtest=RangeGrouperImplTest
```

## Performance Characteristics

The implementation is optimized for real-world usage:
- Efficiently handles arrays up to 10,000+ elements
- Automatic duplicate removal reduces processing overhead
- Stream-based processing leverages JVM optimizations
- Memory-efficient algorithm with minimal object creation


## Dependencies

- **Java 8+**: Core language features
- **JUnit 5**: Testing framework
- **Hamcrest**: Enhanced assertions
- **Maven**: Build and dependency management

## Author

Created as part of a Java programming exercise demonstrating:
- Java 8+ feature proficiency
- Test-driven development
- Clean code practices
- GitHub workflow preparation
