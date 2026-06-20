# 1. Define sample input sequences
student_list = ["Alice", "Bob", "Charlie", "David"]
subject_list = ["Math", "Physics", "Chemistry", "Biology"]
marks_list   = [85, 92, 78, 95]

# 2. Use zip to bundle items together element-wise:
# [('Alice', 'Math', 85), ('Bob', 'Physics', 92), ...]
zipped_data = zip(student_list, subject_list, marks_list)

# 3. Use map with a lambda function to find the maximum entry based on marks.
# We turn it into a list first because map evaluates items lazily.
data_list = list(zipped_data)

# Find the record with the maximum marks value (the 3rd element, index 2)
element_wise_max = max(data_list, key=lambda student_record: student_record[2])

# ==========================================
# Runnable Output
# ==========================================
print("Zipped Student Records:")
for record in data_list:
    print(record)

print("\nElement-wise Maximum Record (Highest Mark):")
print(element_wise_max)
