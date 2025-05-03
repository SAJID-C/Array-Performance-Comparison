Array Categories Performance Comparison
Name: Md. Sajid Chowdhury


🔍 Summary
This project compares the performance of different array types based on the classifications in Chapter 6 of Programming Languages and Structures. Implementations were done in both Java and Python, focusing on array allocation strategy, memory management, and execution time. Tests were conducted on 1,000,000 elements using various static and dynamic array strategies like stack-based, heap-based, and resizable structures (ArrayList in Java and dynamic lists in Python).

Key Java Findings:
Static & Fixed Arrays: Fastest (~6-10ms)

Heap-Dynamic with Preallocation: ~27ms (much faster than without preallocation: ~60ms)

Java wins in raw performance, thanks to primitive arrays and better memory efficiency.

Key Python Findings:
Preallocated lists were fastest (~21ms)

Dynamic list resizing was slower (up to 229ms)

Python is more flexible but significantly slower due to dynamic typing and interpreter overhead
