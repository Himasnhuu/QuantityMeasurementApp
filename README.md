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
└── feature/UC5-UnitConversion
```

### Project Directory Structure
```
QuantityMeasurementApp/
├── .mvn/
├── .settings/
├── src/
│   ├── main/
│   │   ├── QuantityMeasurementApp
│   │   └── Length
│   └── test/
│       └── QuantityMeasurementAppTest
├── target/
└── pom.xml
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

**GitHub Branch:** https://github.com/Himasnhuu/QuantityMeasurementApp/tree/feature/UC5-UnitConversion" 
