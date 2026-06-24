N = 50

numbers = range(1, N + 1)

multiples_of_five = list(filter(lambda x: x % 5 == 0, numbers))

print(f"Multiples of 5 up to N={N}:")
print(multiples_of_five)
