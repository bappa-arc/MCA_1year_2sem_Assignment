#!/bin/bash

while true
do
    echo "Enter first value (userv1): "
    read userv1

    echo "Enter second value (userv2): "
    read userv2

    # Function to check if input is numeric (int or float)
    is_number() {
        [[ $1 =~ ^-?[0-9]+([.][0-9]+)?$ ]]
    }

    echo "--------------------------------------"

    # ADDITION
    if is_number "$userv1" && is_number "$userv2"; then
        sum=$(echo "$userv1 + $userv2" | bc)
        echo "The sum of '$userv1' and '$userv2' is $sum"
    else
        echo "Addition not possible: Non-numeric input"
    fi

    # SUBTRACTION
    if is_number "$userv1" && is_number "$userv2"; then
        sub=$(echo "$userv1 - $userv2" | bc)
        echo "The difference of '$userv1' and '$userv2' is $sub"
    else
        echo "Subtraction not possible: Non-numeric input"
    fi

    # MULTIPLICATION
    if is_number "$userv1" && is_number "$userv2"; then
        mul=$(echo "$userv1 * $userv2" | bc)
        echo "The product of '$userv1' and '$userv2' is $mul"
    else
        echo "Multiplication not possible: Non-numeric input"
    fi

    # DIVISION
    if is_number "$userv1" && is_number "$userv2"; then
        if [ "$userv2" == "0" ]; then
            echo "Division not possible: Cannot divide by zero"
        else
            div=$(echo "scale=2; $userv1 / $userv2" | bc)
            echo "The division of '$userv1' and '$userv2' is $div"
        fi
    else
        echo "Division not possible: Non-numeric input"
    fi

    echo "--------------------------------------"

    # Ask user to continue
    echo "Do you want to continue? (y/n): "
    read choice

    if [[ "$choice" != "y" && "$choice" != "Y" ]]; then
        echo "Exiting program..."
        break
    fi

done