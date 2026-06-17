def prime_generator(limit):
    primes = []
    for num in range(2, limit + 1):
        is_prime = True
        # Check if 'num' is divisible by any prime found so far
        for p in primes:
            # Optimization: only check primes up to the square root of num
            if p * p > num:
                break
            if num % p == 0:
                is_prime = False
                break
        
        if is_prime:
            primes.append(num)
            yield num

# Example: Generate and print primes up to 50
for prime in prime_generator(50):
    print(prime, end=" ")
print()