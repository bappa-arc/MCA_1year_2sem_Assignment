def flatten_dict(d, parent_key="", sep="."):
    items = {}

    for k, v in d.items():
        new_key = parent_key + sep + k if parent_key else k

        if isinstance(v, dict):
            items.update(flatten_dict(v, new_key, sep=sep))

        elif isinstance(v, list):
            for i, item in enumerate(v):
                if isinstance(item, dict):
                    items.update(flatten_dict(item, f"{new_key}[{i}]", sep=sep))
                else:
                    items[f"{new_key}[{i}]"] = item
        else:
            items[new_key] = v

    return items

fullname = input("Enter full name: ")
age = int(input("Enter age: "))
phone_count = int(input("How many phone numbers? "))

phones = []
for i in range(phone_count):
    phones.append(input(f"Enter phone number #{i+1}: "))

first_line = input("Enter first-line address: ")
second_line = input("Enter second-line address: ")
zip_code = input("Enter ZIP code: ")

nested = {
    "fullname": fullname,
    "age": age,
    "phone-numbers": phones,
    "residence": {
        "address": {"first-line": first_line, "second-line": second_line},
        "zip": zip_code,
    },
}

print("\nNested Dictionary:")
print(nested)

flat = flatten_dict(nested)

print("\nFlattened Dictionary:")
for k, v in flat.items():
    print(f"{k} : {v}")

