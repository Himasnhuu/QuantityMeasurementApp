"# QuantityMeasurementApp

## 📂 Repository Structure

### Branch Structure
```
QuantityMeasurementApp/
├── main (default branch)
├── dev
├── feature/UC1-FeetEquality
├── feature/UC2-InchEquality
├── feature/UC3-GenericQuantityClassForDRYPrinciple
├── feature/UC4-ExtendedUnitSupport
├── feature/UC5-UnitConversion
├── feature/UC6-UnitAddition
├── feature/UC7-TargetUnitAddition
├── feature/UC8-StandAloneUnit
├── feature/UC9-WeightMeasurement
├── feature/UC10-Unit-Interface-For-Multi-Category-Support
├── feature/UC11-Volume-Measurement-Equality-Conversion-Addition
├── feature/UC12-Subtraction-and-Division
├── feature/UC13-CentralizedArithmeticLogic
├── feature/UC14-TemperatureMeasurement
├── feature/UC15-N-Tier-Architecture
└── feature/UC16-Database-Integration
```

### Project Directory Structure
```
QuantityMeasurementApp/
├── .classpath
├── .mvn/
├── .project
├── .settings/
├── pom.xml
├── quantity_measurement_repo.ser
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       ├── controller/
│   │   │       ├── core/
│   │   │       ├── dto/
│   │   │       ├── entity/
│   │   │       ├── exception/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       ├── unit/
│   │   │       ├── util/
│   │   │       └── QuantityMeasurementApp.java
│   │   └── resources/
│   └── test/
│       └── java/
│           └── com/
│               ├── ArithematicEnumTest.java
│               ├── IMeasurableAndGenericImpl.java
│               ├── QuantityMeasurementAppTest.java
│               ├── QuantitySubtractionAndDivisionTest.java
│               ├── TemperatureTest.java
│               ├── VolumeUnitTest.java
│               └── WeightImplTest.java
└── target/
```

## Use Cases

### UC1: Feet Measurement Equality

The QuantityMeasurementApp class is responsible for checking the equality of two numerical values measured in feet within the Quantity Measurement Application. It ensures accurate comparisons and handles various edge cases.

**GitHub Branch:** https://github.com/Himasnhuu/QuantityMeasurementApp/tree/feature/UC1-FeetEquality

---

### UC2: Inch Measurement Equality

This Use Case extends UC1 to accommodate the Equality Check for Inches along with Feet. This use case is in no way trying to compare two entities, Feet and Inches. They are still treated separately. Please ensure like UC1 the test cases ensure complete test coverage to accurately compare and handle various edge cases.

**GitHub Branch:** https://github.com/Himasnhuu/QuantityMeasurementApp/tree/feature/UC2-InchEquality

---

### UC3: GenericQuantityClassForDRYPrinciple

UC3 is designed to overcome the Disadvantage of using Feet and Inches which starts violating the DRY principle, where both Feet and Inches classes contain nearly identical code, having the same constructor pattern, Identical equals() method implementation.

This Use Case refactors the existing Feet and Inches classes into a single generic Quantity Length class that eliminates code duplication while maintaining all functionality from UC1 and UC2. The Quantity Length class represents any measurement with a value and unit type, applying the DRY (Don't Repeat Yourself) principle. This reduces maintenance burden and makes the codebase more scalable for adding new units in the future.

**GitHub Branch:** https://github.com/Himasnhuu/QuantityMeasurementApp/tree/feature/UC3-GenericQuantityClassForDRYPrinciple

---

### UC4: Extended Unit Support

UC4 extends UC3 by introducing Yards and Centimeters as additional length units to the QuantityLength class. This use case demonstrates how the generic Quantity class design scales effortlessly to accommodate new units without code duplication. Yards will be added to the LengthUnit enum with the appropriate conversion factor (1 yard = 3 feet) and (1cm = 0.393701in), and all equality comparisons will work seamlessly across feet, inches, yards, and cms.

**GitHub Branch:** https://github.com/Himasnhuu/QuantityMeasurementApp/tree/feature/UC4-ExtendedUnitSupport

---

### UC5: Unit-to-Unit Conversion

UC5 extends UC4 by providing explicit conversion operations between length units (e.g., feet → inches, yards → inches, centimeters → feet). Instead of only comparing equality, the Quantity Length API exposes a conversion method that returns a numeric value converted from a source unit to a target unit using the centralized conversion factors.

**GitHub Branch:** https://github.com/Himasnhuu/QuantityMeasurementApp/tree/feature/UC5-UnitConversion

---

### UC6: Addition of Two Length Units

UC6 extends UC5 by introducing addition operations between length measurements. This use case enables the Quantity Length API to add two lengths of potentially different units (but same category—length) and return the result in the unit of the first operand. Essentially adding another length to the current length. For example, adding 1 foot and 12 inches should yield 2 feet (based on the unit of the first operand).

**GitHub Branch:** https://github.com/Himasnhuu/QuantityMeasurementApp/tree/feature/UC6-UnitAddition

---

### UC7: Addition with Target Unit Specification

UC7 extends UC6 by providing flexibility in specifying the unit for the addition result. Instead of defaulting to the unit of the first operand, this use case allows the caller to explicitly specify any supported unit as the target unit for the result. This provides greater flexibility in use cases where the result must be expressed in a specific unit regardless of the operands' units. For example, adding 1 foot and 12 inches with a target unit of yards should yield approximately 0.667 yards.

**GitHub Branch:** https://github.com/Himasnhuu/QuantityMeasurementApp/tree/feature/UC7-TargetUnitAddition

---

### UC8: Refactoring Unit Enum to Standalone with Conversion Responsibility

UC8 refactors the design from UC1–UC7 to overcome the disadvantage of embedding the LengthUnit enum within the QuantityLength class. This design flaw creates circular dependencies when scaling to multiple measurement categories (length, weight, volume, etc.) and violates the Single Responsibility Principle by not centralizing unit-related conversion logic.

**GitHub Branch:** https://github.com/Himasnhuu/QuantityMeasurementApp/tree/feature/UC8-StandAloneUnit

---

### UC9: Weight Measurement Equality, Conversion, and Addition

UC9 extends the Quantity Measurement Application to support weight measurements alongside length measurements. This use case introduces a new measurement category—weight—that operates independently from length. Similar to how length measurements (feet, inches, yards, centimeters) are compared for equality, converted between units, and added together, weight measurements in different units (kilograms, grams, pounds) will support the same operations.

**GitHub Branch:** https://github.com/Himasnhuu/QuantityMeasurementApp/tree/feature/UC9-WeightMeasurement

---

### UC10: Generic Quantity Class with Unit Interface for Multi-Category Support

UC10 addresses the architectural and design disadvantages introduced by UC9 by refactoring the design into a single, generic Quantity class that works with any measurement category through a common IMeasurable interface. This use case eliminates code duplication across parallel QuantityLength and QuantityWeight classes, consolidates unit enum patterns, and simplifies the QuantityMeasurementApp class to adhere to the Single Responsibility Principle.

**GitHub Branch:** https://github.com/Himasnhuu/QuantityMeasurementApp/tree/feature/UC10-Unit-Interface-For-Multi-Category-Support

---

### UC11: Volume Measurement Equality, Conversion, and Addition (Litre, Millilitre, Gallon)

UC11 extends the Quantity Measurement Application to support volume measurements alongside length and weight measurements. This use case introduces a new measurement category—volume—that operates independently from length and weight through the generic Quantity class and IMeasurable interface established in UC10.

**GitHub Branch:** https://github.com/Himasnhuu/QuantityMeasurementApp/tree/feature/UC11-Volume-Measurement-Equality-Conversion-Addition

---

### UC12: Subtraction and Division Operations on Quantity Measurements

UC12 extends the Quantity Measurement Application by introducing two new arithmetic operations—subtraction and division—to the generic Quantity class. Building on the foundation of equality comparison, unit conversion, and addition from UC1–UC11, this use case enables more comprehensive arithmetic manipulation of measurements.

**GitHub Branch:** https://github.com/Himasnhuu/QuantityMeasurementApp/tree/feature/UC12-Subtraction-and-Division

---

### UC13: Centralized Arithmetic Logic to Enforce DRY in Quantity Operations

UC13 refactors the arithmetic operations (addition, subtraction, division) implemented in UC12 to eliminate code duplication and enforce the DRY (Don't Repeat Yourself) principle. Instead of repeating unit conversion, base-unit normalization, and validation logic across multiple arithmetic methods, this use case introduces a centralized private helper method that encapsulates all common arithmetic logic.

**GitHub Branch:** https://github.com/Himasnhuu/QuantityMeasurementApp/tree/feature/UC13-CentralizedArithmeticLogic

---

### UC14: Temperature Measurement with Selective Arithmetic Support and IMeasurable Refactoring

UC14 extends the Quantity Measurement Application to support temperature measurements alongside length, weight, and volume. Unlike these three categories, which support full arithmetic operations (addition, subtraction, division), temperature presents a unique challenge: temperature differences can be added or subtracted, but individual temperature values cannot be meaningfully multiplied or divided in most practical contexts.

**GitHub Branch:** https://github.com/Himasnhuu/QuantityMeasurementApp/tree/feature/UC14-TemperatureMeasurement

---

### UC15: N-Tier Architecture Refactoring for Quantity Measurement Application

UC15 refactors the monolithic Quantity Measurement Application into a professional N-Tier architecture by separating concerns into distinct layers: Controller Layer, Service Layer, and Entity/Model Layer. This architectural shift transforms the standalone application from a single-responsibility class into a scalable, maintainable system that adheres to SOLID principles and industry best practices.

**GitHub Branch:** https://github.com/Himasnhuu/QuantityMeasurementApp/tree/feature/UC15-N-Tier-Architecture

---

### UC16: Database Integration with JDBC for Quantity Measurement Persistence

UC16 extends the Quantity Measurement Application by introducing persistent database storage through JDBC (Java Database Connectivity). Building upon the N-Tier architecture established in UC15, this use case implements a QuantityMeasurementDatabaseRepository class that replaces the in-memory QuantityMeasurementCacheRepository for long-term data persistence. The application now supports storing and retrieving quantity measurement operation history from a relational database, enabling audit trails, reporting, and historical analysis.

**GitHub Branch:** https://github.com/Himasnhuu/QuantityMeasurementApp/tree/feature/UC16-Database-Integration
