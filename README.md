# BRU: Bandwidth Regulation Unit for Real-Time Multicore Processors
This repository contains all the necessary files to replicate the experiments in the BRU paper.

## Environment Setup
The simulation environment runs on FireSim. To set up the environment, follow the [FireSim documentation version 1.6.0](http://docs.fires.im/en/1.6.0) through the single-node simulation section. When cloning FireSim, use the URL of this repository instead.

## Running Experiments
Make sure to source `sourceme-f1-manager.sh` before running any commands.

###  Effect of Regulation Period in Protecting Real-time Tasks
This will run the simulation and store the result on the manager:
```
cd bru-firesim/sw/firesim-software
./marshal -v build workloads/bw-periodic-disparity-6.json
./marshal install workloads/bw-periodic-disparity-6.json
firesim launchrunfarm -c config_runtime6.ini && firesim infrasetup -c config_runtime6.ini && firesim runworkload -c config_runtime6.ini && firesim terminaterunfarm -q -c config_runtime6.ini
```
Look for the result directory printed on the screen:
```
...
FireSim Simulation Exited Successfully. See results in:
/home/centos/firesim/deploy/results-workload/[time-tag]-bw-periodic-disparity-6/
...
```
To parse the results and create Figure 9:
```
cd bru-firesim/bru-expr/periodic
./run-cdf.sh [path-to-the-result-directory] bw-periodic-disparity-6
```
This will create the figure in `cdf.pdf`.

###  Effect of Group Bandwidth Regulation
To run the simulation:
```
cd bru-firesim/sw/firesim-software
./marshal -v build workloads/lt-bww-2.json
./marshal install workloads/lt-bww-2.json
firesim launchrunfarm -c config_runtim2.ini && firesim infrasetup -c config_runtime2.ini && firesim runworkload -c config_runtime2.ini && firesim terminaterunfarm -q -c config_runtime2.ini
```
To parse the result and create Figure 12:
```
cd bru-firesim/bru-expr/lt-bww
./run-lt-bww.sh [path-to-the-result-directory]
```
This will create the figure in `lt-bww.pdf`.
