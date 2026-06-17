import re

def validate_phone(number):
    pattern = r"^\d{10}$" # Matches exactly 10 digits
    return bool(re.match(pattern, number))

# Test
print(validate_phone("1234567890")) # True
