import re

def validate_phone(number):
    pattern = r"^\d{10}$" # Matches exactly 10 digits
    return bool(re.match(pattern, number))

# Test
phone=input("Enter your phone number: ")
print(validate_phone(phone)) # True
