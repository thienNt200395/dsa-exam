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
├── src/                   # Java reference solutions + WebServer.java (web playground)
├── web/index.html         # web playground page
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

## Run the web playground
Try all three questions in the browser: each shows the question, the pseudocode and the Java
source, plus a form to run the algorithm with your own input.
```bash
mkdir -p out
javac -d out src/*.java
java -cp out WebServer
```
Run these from the repository root, then open **http://localhost:9000**.
- If port 9000 is busy, the server uses the next free port and prints the address.
- To choose a port: `java -cp out WebServer 9100`.
- Stop the server with `Ctrl+C`.
- The server only accepts connections from your own machine.
- Input limits: factorial `n` from 0 to 20, arrays up to 1000 elements.

## Run the tests
```bash
mkdir -p out
javac -d out src/*.java test/*.java
java -cp out AlgorithmsTest
```
The test runner prints the number of checks and exits with a non-zero status on failure.
