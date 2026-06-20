# Set the upper limit
N = 50

# 1. Generate numbers from 1 to N (inclusive)
numbers = range(1, N + 1)

# 2. Use filter() with an anonymous (lambda) function
# The lambda checks if a number leaves a remainder of 0 when divided by 5
multiples_of_five = list(filter(lambda x: x % 5 == 0, numbers))

print(f"Multiples of 5 up to N={N}:")
print(multiples_of_five)
