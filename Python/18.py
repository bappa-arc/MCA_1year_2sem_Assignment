import heapq

numbers = []

n = int(input("Enter number of elements: "))

for i in range(n):
    num = int(input("Enter number: "))
    numbers.append(num)

k = int(input("Enter value of N: "))

largest = heapq.nlargest(k, numbers)
smallest = heapq.nsmallest(k, numbers)

print("Largest", k, "items:", largest)
print("Smallest", k, "items:", smallest)