def is_prime(num):
    if num < 2:
        return False
    for i in range(2, int(num ** 0.5) + 1):
        if num % i == 0:
            return False
    return True

m = int(input("Enter the lower limit to generate primes up to: "))
n = int(input("Enter the upper limit to generate primes up to: "))

primes = []
for num in range(m, n + 1):
    if is_prime(num):
        primes.append(num)
print(f"Primes up to {n}: {primes}")