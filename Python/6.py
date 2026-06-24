import re

class UserDataError(Exception):
    """Base exception for all user data errors."""
    pass

class UsernameNotUniqueError(UserDataError):
    def __str__(self):
        return "Username is not unique (already exists in directory)."

class InvalidAgeError(UserDataError):
    def __str__(self):
        return "Age is not a positive integer."

class UnderageError(UserDataError):
    def __str__(self):
        return "User is under 16 years old."

class InvalidEmailError(UserDataError):
    def __str__(self):
        return "Email is not valid (must contain @ and a domain name)."

directory = {}

def validate_and_add(username, email, age):
    if username in directory:
        raise UsernameNotUniqueError()

    if not isinstance(age, int) or age <= 0:
        raise InvalidAgeError()

    if age < 16:
        raise UnderageError()

    email_pattern = r"^[^@]+@[^@]+\.[^@]+$"
    if not re.match(email_pattern, email):
        raise InvalidEmailError()

    # All checks passed
    directory[username] = {"email": email}
    print(f" '{username}' added to directory.")

print("Enter user data. Type 'done' as username to finish.\n")

users = []
while True:
    uname = input("  Username (or 'done'): ").strip()
    if uname.lower() == "done":
        break
    email = input("  Email   : ").strip()
    age_input = input("  Age     : ").strip()

    try:
        age = int(age_input)
    except ValueError:
        age = age_input   # keep as non-int so InvalidAgeError triggers

    users.append((uname, email, age))

print("\n--- Processing Users ---")
for username, email, age in users:
    try:
        validate_and_add(username, email, age)
    except UsernameNotUniqueError as e:
        print(f"    [{username}] {e}")
    except InvalidAgeError as e:
        print(f"    [{username}] {e}")
    except UnderageError as e:
        print(f"    [{username}] {e}")
    except InvalidEmailError as e:
        print(f"    [{username}] {e}")

print(f"\nFinal Directory: {directory}")