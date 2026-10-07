# Data Structures and Algorithms: Exam

Data Structures and Algorithms exam: linear search, bubble sort and recursive factorial, with pseudocode, Java reference solutions and tests.

| # | Question | Reference solution |
|---|----------|--------------------|
| 1 | Linear search | [`src/LinearSearch.java`](src/LinearSearch.java) |
| 2 | Bubble sort | [`src/BubbleSort.java`](src/BubbleSort.java) |
| 3 | Factorial (recursion) | [`src/Factorial.java`](src/Factorial.java) |

Each question asks for (a) the steps in pseudocode and (b) an implementation in any language.

## Repository layout
```
.
├── README.md
├── docs/
│   ├── exam-overview.md   # purpose, format, topics, learning outcomes
│   ├── questions.md       # the exam questions
│   └── solutions.md       # pseudocode, notes and complexity for each question
├── src/                   # Java reference solutions
└── test/                  # dependency-free tests
```

## Requirements
JDK 11 or newer. Check with `java -version`.

## Get the project
```bash
git clone git@github.com:thienNt200395/dsa-exam.git
cd dsa-exam
```

## Run a solution
Each file has a `main` method with sample data:
```bash
java src/LinearSearch.java
java src/BubbleSort.java
echo 5 | java src/Factorial.java
```

## Run the tests
```bash
mkdir -p out
javac -d out src/*.java test/*.java
java -cp out AlgorithmsTest
```
The test runner prints the number of checks and exits with a non-zero status on failure.
