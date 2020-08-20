#!/bin/bash

RES_DIR=$1
SUB_DIR=$2

#declare -a exprs=(200ns 400ns 800ns 1us 10us)
declare -a exprs=(solo 100ns 1us 1ms 10ms no-reg)
#declare -a exprs=(solo 200ns 3.2us 100us 10ms no-reg)
#declare -a exprs=(solo 100ns 10us 1ms no-reg)
#declare -a exprs=(solo 100ns 800ns 10us 100us 1ms 10ms no-reg)
#declare -a exprs=(100ns 100us)

#cnt=0

for e in "${exprs[@]}"
do
  awk '/duration/{print $3}' "$RES_DIR"/"$SUB_DIR"-"$e"/results/"$e".txt > "$e"
  #awk '{sum+=$1} END {print sum/NR}' "$e"
  #awk '/duration/{print $3}' "$RES_DIR"/"$SUB_DIR"-"$cnt"/results/$e.txt > $e
  #awk '/duration/{print $3}' "$RES_DIR"/results/$e.txt > $e
  #cnt=$(($cnt + 1))
done

$(dirname "$0")/cdf.py "${exprs[@]}" > wcet.txt
