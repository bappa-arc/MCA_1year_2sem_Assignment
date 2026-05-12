#!/bin/bash

echo "$PATH" | tr ":" "\n" | while read dir
do
    echo "Directory: $dir"

    if [ -d "$dir" ]
    then
        ls -ld "$dir" | awk '{print $1 "\t\t" $6 " " $7 " " $8 "\t\t" $9}'
    else
        echo "Directory does not exist"
    fi

    echo "----------------------"
done
