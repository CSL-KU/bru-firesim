# BRU: Bandwidth Regulation Unit for Real-Time Multicore Processors
This repository contains all the necessary files to replicate the experiments in the BRU paper.

## Environment Setup
The simulation environment runs on FireSim. To set up the environment, follow the [FireSim documentation version 1.6.0](http://docs.fires.im/en/1.6.0) through the single-node simulation section. When cloning FireSim, use the URL of this repository instead.

## Running Experiments
###  Effect of Regulation Period in Protecting Real-time Tasks
```
cd bru-firesim/sw/firesim-software
./marshal -v build workloads/bw-periodic-disparity-6.json
./marshal install workloads/bw-periodic-disparity-6.json
firesim launchrunfarm -c config_runtime6.ini && firesim infrasetup -c config_runtime6.ini && firesim runworkload -c config_runtime6.ini && firesim terminaterunfarm -q -c config_runtime6.ini
```
This will run the simulation and store the result. Look for the result directory printed on the screen:
```
...
FireSim Simulation Exited Successfully. See results in:
/home/centos/firesim/deploy/results-workload/[time-tag]-bw-periodic-disparity-6/
...
```
