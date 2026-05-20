#!/bin/bash

echo "Enter file name:"
read file

if [ -f $file ]
then
    lines=$(wc -l < "$file")
    echo "Total number of lines: $lines"
else
    echo "File does not exist."
fi