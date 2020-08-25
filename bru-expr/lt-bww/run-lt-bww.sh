#!/bin/bash

awk '/average/{print $2}' $1/lt-bww-2-1-domain/results/lt-bww-1domain.txt > 1-domain.txt
awk '/average/{print $2}' $1/lt-bww-2-3-domain/results/lt-bww-3domain.txt > 3-domain.txt
./lt-bww.py
