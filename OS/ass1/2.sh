#!/bin/bash

echo "Enter Employee Name:"
read name

echo "Enter Basic Salary:"
read basic

echo "Enter House Rent Allowance (HRA):"
read hra

echo "Enter Dearness Allowance (DA):"
read da

# Calculate Net Salary
net_salary=$((basic + hra + da))

echo "------------------------------"
echo "        SALARY SLIP"
echo "------------------------------"
echo "Employee Name : $name"
echo "Basic Salary  : $basic"
echo "HRA           : $hra"
echo "DA            : $da"
echo "------------------------------"
echo "Net Salary    : $net_salary"
echo "------------------------------"