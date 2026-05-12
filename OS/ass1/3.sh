#!/bin/bash

echo "Enter a four digit number:"
read num

sign=""

if [ $num -lt 0 ]
then
    sign="-"
    num=$(( -1 * num ))
fi

d1=$((num / 1000))
d2=$(((num % 1000) / 100))
d3=$(((num % 100) / 10))
d4=$((num % 10))

sum=$((d1 + d2 + d3 + d4))

reverse="$sign$d4$d3$d2$d1"

echo "Sum of digits = $sum"
echo "Reverse number = $reverse"