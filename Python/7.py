import os
import tempfile
def findfiles(directory):
    """
    Recursively descends a directory tree and yields 
    the full path of every file found.
    """
    try:
        # List all items in the current directory
        for item in os.listdir(directory):
            full_path = os.path.join(directory, item)
            
            # If it's a directory, recursively descend into it
            if os.path.isdir(full_path):
                yield from findfiles(full_path)
            # If it's a file, yield its path
            elif os.path.isfile(full_path):
                yield full_path
    except PermissionError:
        # Safely skip folders that require admin permissions
        pass

with tempfile.TemporaryDirectory() as tmpdir:
    # Create a dummy nested structure
    subfolder = os.path.join(tmpdir, "projects")
    os.makedirs(subfolder)
    
    # Write a few dummy files
    with open(os.path.join(tmpdir, "notes.txt"), "w") as f: f.write("text")
    with open(os.path.join(subfolder, "script.py"), "w") as f: f.write("code")
    
    print(f"Searching for files recursively inside: {tmpdir}\n")
    
    # Run the generator
    for file_path in findfiles(tmpdir):
        print(f"Found file: {file_path}")
