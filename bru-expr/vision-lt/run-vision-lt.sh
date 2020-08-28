#!/bin/bash

set -euo pipefail

./vision-lt-parse.sh $1 > parsed.txt
./vision-lt.py
