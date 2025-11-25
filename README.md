# Laboratory Work #2 - Stream API & Gatherers

**Author:** Kunda Andrii IA-31  
**Variant:** 2 (Cars)  
**Java Version:** 24 (Preview features enabled)

## Description

This project generates a stream of random cars, filters them using a custom Gatherer (to skip the first N cars of a specific make), calculates statistics (Min, Max, Mean, StdDev) using a custom Collector, and identifies price outliers using the IQR method.

## Prerequisites

- JDK 24 or newer
- Maven or GradleФ

## How to Run

Since `Gatherer` is a preview feature, you need to enable preview features with `--enable-preview`.

### 1. Compile

```bash
javac --release 24 --enable-preview -d out src/org/example/**/*.java
```

### 2. Run

```bash
java --enable-preview -cp out org.example.Main
```

## Troubleshooting

### 1. 'javac' is not recognized

**Error:**
```
The term 'javac' is not recognized as the name of a cmdlet...
```

**Solution:** The path to Java is not added to your system environment variables.

**Fix:** Use the full path to your JDK executable. Example:

```bash
"C:\Program Files\Java\jdk-24\bin\javac" --release 24 --enable-preview -d out src/org/example/**/*.java
```

### 2. Preview Features Disabled

**Error:**
```
java.lang.UnsupportedClassVersionError: Preview features are not enabled...
```

**Solution:** You forgot the `--enable-preview` flag during the run step.

**Fix:** Ensure you add `--enable-preview` before `-cp`:

```bash
java --enable-preview -cp out org.example.Main
```

### 3. Class Not Found

**Error:**
```
Could not find or load main class org.example.Main
```

**Solution:** The classpath (-cp) is incorrect or the code wasn't compiled.

**Fix:**
- Make sure the `out` folder was created after compilation
- Ensure you are running the command from the project root folder
- Check if the package name in `Main.java` matches the command (e.g., `package org.example;`)

### 4. File Not Found during Compilation

**Error:**
```
error: file not found: src/org/example/*.java
```

**Solution:** The path to your source files is incorrect.

**Fix:** Check your folder structure. If you created a standard IntelliJ IDEA project, the path is likely:

```bash
javac --release 24 --enable-preview -d out src/main/java/org/example/**/*.java
```

Then run with:

```bash
java --enable-preview -cp out org.example.Main
```

## Project Structure

```
project-root/
├── src/
│   └── org/
│       └── example/
│           ├── Main.java
│           ├── Car.java
│           └── ...
└── out/
    └── org/
        └── example/
            └── *.class
```

## Notes

- All commands should be executed from the project root directory
- Ensure your `Main.java` file contains `package org.example;` at the top
- The `out` folder will be created automatically during compilation
