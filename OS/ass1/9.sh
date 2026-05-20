#!/bin/bash

num=$1

echo "Multiplication Table of $num"

for i in {1..10}
do
    echo "$num x $i = $((num * i))"
done