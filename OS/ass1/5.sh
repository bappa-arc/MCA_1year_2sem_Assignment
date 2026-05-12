#!/bin/bash

echo "Enter directory name:"
read dir

if [ ! -d "$dir" ]
then
    echo "Invalid directory!!"
    exit 1;
fi

file_count=$(find "$dir" -type f | wc -l)

dir_count=$(find "$dir" -type d | wc -l)

echo "Total Files      : $file_count"
echo "Total Directories: $dir_count"

echo "----------------------"
echo "File Names:"
find "$dir" -type f

echo "----------------------"
echo "Directory Names:"
find "$dir" -type d

size=$(find "$dir" -type f -mtime -7 -exec du -cb {} + | tail -1 | awk '{print $1}')

echo "----------------------"
echo "Total size of files created in past week: $size bytes"