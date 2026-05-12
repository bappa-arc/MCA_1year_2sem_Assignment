#!/bin/bash

echo "Enter the directory name: "
read dir

if [ ! -d "$dir" ]
then
    echo "Invalid directory!!"
    exit 1;
fi

total=$(find "$dir" -type f -exec du -cb {} + | tail -1 | awk '{print $1}')

today=$(find "$dir" -type f -mtime 0 -exec du -cb {} + | tail -1 | awk '{print $1}')

echo "Total bytes used: $total bytes"
echo "Total size of today's files: $today bytes"
