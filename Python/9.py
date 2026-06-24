def get_list(name):
    raw = input(f"Enter {name} (space-separated numbers): ")
    return list(map(int, raw.split()))
student_list = get_list("Student List")
subject_list = get_list("Subject List")
marks_list   = get_list("Marks List  ")

result = list(map(lambda trio: max(trio), zip(student_list, subject_list, marks_list)))

print(f"\nStudent List    : {student_list}")
print(f"Subject List    : {subject_list}")
print(f"Marks List      : {marks_list}")
print(f"Element-wise Max: {result}")