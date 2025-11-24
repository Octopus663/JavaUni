# Laboratory Work #2 - Stream API & Gatherers

**Author:** Kunda Andrii IA-31 

**Variant:** 2 (Cars)

**Java Version:** 24 (Preview features enabled)

## Description
This project generates a stream of random cars, filters them using a custom Gatherer (to skip the first N cars of a specific make), calculates statistics (Min, Max, Mean, StdDev) using a custom Collector, and identifies price outliers using the IQR method.

## Prerequisites
- JDK 24 or newer.
- Maven or Gradle

## How to Run
`Gatherer` is a preview feature (depending on exact JDK build status), you might need `--enable-preview`.

1. **Compile:**
   ```bash
   javac --release 24 --enable-preview -d out src/org/example/**/*.java
    ```
2. **Run:**
   ```bash
   java --enable-preview -cp out com.lab2.Main
   ```