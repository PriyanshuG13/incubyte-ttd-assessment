# String Calculator (Java, TDD Kata)

A simple implementation of the classic String Calculator kata in Java.

Built step by step following TDD principles — starting from the simplest case and expanding functionality gradually.

## How to run

```bash
mvn clean test
```

## Features
- Empty input returns 0
- Comma or newline as delimiters
- Custom delimiter syntax: `//[delimiter]\n[numbers]`
- Supports multiple or multi-character delimiters like `//[***][%]\n1***2%3`
- Throws an exception when negatives are found, listing them all

## Example

```java
StringCalculator calc = new StringCalculator();
System.out.println(calc.add("1\n2,3")); // 6
```

---
