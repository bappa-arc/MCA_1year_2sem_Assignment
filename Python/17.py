import heapq

pq = []

n = int(input("How many elements do you want to insert? "))

for i in range(n):
    item = input("Enter item: ")
    priority = int(input("Enter priority (smaller number = higher priority): "))

    heapq.heappush(pq, (priority, item))

print("\nItems removed based on priority:")

while pq:
    priority, item = heapq.heappop(pq)
    print(item, "with priority", priority)