limit = int(input("Enter max value for short sides (e.g. 10): "))
triples_comp = [
    (a, b, c)
    for a in range(1, limit)
    for b in range(a, limit)
    for c in range(1, 200)
    if a ** 2 + b ** 2 == c ** 2
]
candidates = [
    (a, b, c)
    for a in range(1, limit)
    for b in range(a, limit)
    for c in range(1, 200)
]
triples_filter = list(
    filter(lambda t: t[0] ** 2 + t[1] ** 2 == t[2] ** 2, candidates)
)
print(f"\nPythagorean Triples with short sides < {limit}:")
print(f"  Using Comprehension : {triples_comp}")
print(f"  Using filter()      : {triples_filter}")