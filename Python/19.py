stock1 = {}
stock2 = {}

n1 = int(input("Enter number of stocks in first dictionary: "))

for i in range(n1):
    name = input("Enter stock name: ")
    price = int(input("Enter stock price: "))
    stock1[name] = price

print("\nFirst Dictionary:", stock1)

print("\nMinimum Price:", min(stock1.values()))
print("Maximum Price:", max(stock1.values()))

sorted_stock = dict(sorted(stock1.items(), key=lambda x: x[1]))

print("\nSorted by Price:")
print(sorted_stock)

n2 = int(input("\nEnter number of stocks in second dictionary: "))

for i in range(n2):
    name = input("Enter stock name: ")
    price = int(input("Enter stock price: "))
    stock2[name] = price

print("\nSecond Dictionary:", stock2)

print("\nStocks only in first dictionary:")

for key in stock1:
    if key not in stock2:
        print(key)

print("\nStocks with different prices:")

for key in stock1:
    if key in stock2 and stock1[key] != stock2[key]:
        print(key, ":", stock1[key], stock2[key])

print("\nIncrementing prices by 500:")

for key in stock1:
    stock1[key] += 500

print(stock1)

print("\nGrouping stocks by price multiple of 500:")

group = {}

for key, value in stock1.items():
    grp = (value // 500) * 500

    if grp not in group:
        group[grp] = []

    group[grp].append(key)

print(group)
