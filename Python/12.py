letters = [chr(c) for c in range(ord('a'), ord('z') + 1)]

print("Index  Letter")
print("─" * 16)
for index, letter in enumerate(letters, start=1):
    print(f"  {index:2d}     {letter}")