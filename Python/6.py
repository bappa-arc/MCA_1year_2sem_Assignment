class UserValidationError(Exception):
    """Base exception for user validation errors."""
    pass

class DuplicateUsernameError(UserValidationError):
    """Raised when the username already exists in the directory."""
    pass

class InvalidAgeError(UserValidationError):
    """Raised when the age is not a positive integer."""
    pass

class UnderageUserError(UserValidationError):
    """Raised when the user is under 16 years old."""
    pass

class InvalidEmailError(UserValidationError):
    """Raised when the email format is invalid."""
    pass


def process_user_data(user_list):
    # Directory to store valid users {username: email}
    user_directory = {}
    
    print("--- Processing User Registrations ---")
    
    for record in user_list:
        try:
            username, email, age = record
            print(f"\nProcessing: {username}...")

            # Check 1: Is age a positive integer?
            if not isinstance(age, int) or age <= 0:
                raise InvalidAgeError(f"Error: Age '{age}' must be a positive integer.")
            
            # Check 2: Is user at least 16 years old?
            if age < 16:
                raise UnderageUserError(f"Error: User '{username}' is under 16 (Age: {age}).")
            
            # Check 3: Is username unique?
            if username in user_directory:
                raise DuplicateUsernameError(f"Error: Username '{username}' is already taken.")
            
            # Check 4: Simple valid email check (contains local part, '@', and domain)
            if "@" not in email:
                raise InvalidEmailError(f"Error: Email '{email}' is missing the '@' symbol.")
            
            parts = email.split("@")
            if len(parts) != 2 or not parts[0] or not parts[1] or "." not in parts[1]:
                raise InvalidEmailError(f"Error: Email '{email}' must have a username and valid domain.")

            # If all validations pass, add to the directory (without storing age)
            user_directory[username] = email
            print(f"Success: {username} successfully added to the directory!")

        except DuplicateUsernameError as e:
            print(e)
        except InvalidAgeError as e:
            print(e)
        except UnderageUserError as e:
            print(e)
        except InvalidEmailError as e:
            print(e)
        except Exception as e:
            print(f"An unexpected error occurred: {e}")
            
    return user_directory


sample_data = [
    ("alice_99", "alice@example.com", 22),       # Valid
    ("bob_smith", "bob@domain.com", -5),        # Invalid Age (Negative)
    ("charlie_k", "charlie@web.com", 14),       # Underage (14)
    ("alice_99", "alice2@example.com", 25),     # Duplicate Username
    ("david_r", "david_at_domain.com", 19),     # Invalid Email (Missing @)
    ("emily_b", "emily@", 30),                  # Invalid Email (Missing domain)
    ("frank_m", "frank@gmail.com", 18)          # Valid
]

# Run the program
final_directory = process_user_data(sample_data)

print("\n==========================================")
print("Final Registered Directory:")
print(final_directory)
