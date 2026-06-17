from itertools import compress, count, islice

# Generate first 20 numbers to ensure we get 10 of each
numbers = list(islice(count(0), 20))

# Selectors (masks)
evens_mask = [1, 0] * 10
odds_mask = [0, 1] * 10

evens = list(compress(numbers, evens_mask))
odds = list(compress(numbers, odds_mask))

print("Evens:", evens)
print("Odds:", odds)
