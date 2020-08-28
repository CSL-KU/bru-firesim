#!/bin/bash

set -euo pipefail

res=$1

grep Time $res/vision-lt-3-1-domain/results/1domain/disparity.txt | awk '{print $4}'
grep Time $res/vision-lt-3-1-domain/results/1domain/mser.txt | awk '{sum+=$4} END {print sum / NR}'
grep Time $res/vision-lt-3-1-domain/results/1domain/texture_synthesis.txt | awk '{sum+=$4} END {print sum / NR}'
grep Time $res/vision-lt-3-3-domain/results/3domain/disparity.txt | awk '{print $4}'
grep Time $res/vision-lt-3-3-domain/results/3domain/mser.txt | awk '{sum+=$4} END {print sum / NR}'
grep Time $res/vision-lt-3-3-domain/results/3domain/texture_synthesis.txt | awk '{sum+=$4} END {print sum / NR}'
grep Time $res/vision-lt-3-noreg-solo/results/noreg-solo/disparity.txt | awk '{print $4}'
grep Time $res/vision-lt-3-noreg-solo/results/noreg-solo/mser.txt | awk '{print $4}'
grep Time $res/vision-lt-3-noreg-solo/results/noreg-solo/texture_synthesis.txt | awk '{print $4}'
#grep Time $res/3domain-solo/* | awk '{print $4}'
