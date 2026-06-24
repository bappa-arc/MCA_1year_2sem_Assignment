import os

def findfiles(directory):
    """
    Recursively finds and generates paths of all files
    under the given directory tree.
    """
    for root, dirs, files in os.walk(directory):
        for filename in files:
            yield os.path.join(root, filename)   # generator — yields one path at a time

path = input("Enter a directory path to scan (e.g. /home or .): ").strip()

if not os.path.isdir(path):
    print(f"'{path}' is not a valid directory.")
else:
    print(f"\nFiles found under '{path}':\n")
    count = 0
    for filepath in findfiles(path):
        print(f"  {filepath}")
        count += 1

    print(f"\nTotal: {count} file(s) found.")