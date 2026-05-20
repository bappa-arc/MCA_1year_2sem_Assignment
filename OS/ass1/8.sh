#!/bin/bash

echo "Process Information: "
echo "-----------------------------------"
grep "vendor_id" /proc/cpuinfo | head -1
grep "model name" /proc/cpuinfo | head -1
grep "cpu MHz" /proc/cpuinfo | head -1
grep "cache size" /proc/cpuinfo | head -1