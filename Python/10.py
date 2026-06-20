# 1. Define an initial sequence of numbers
numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

# 2. Use map() to square every number in the list
squares = map(lambda x: x**2, numbers)

# 3. Use filter() to filter out odd squares (meaning we keep only the even ones)
# x % 2 == 0 checks if a number is even
even_squares = filter(lambda x: x % 2 == 0, squares)

# 4. Convert the final evaluation structure into a concrete list
result_list = list(even_squares)

# ==========================================
# Runnable Output
# ==========================================
print(f"Original numbers: {numbers}")
print(f"Even squares list: {result_list}")
