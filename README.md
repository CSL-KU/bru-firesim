# BRU: Bandwidth Regulation Unit for Real-Time Multicore Processors
This repository contains all the necessary files to reproduce the experiment results in the BRU paper.

## Environment Setup
The simulation environment runs on FireSim. To set up the environment, follow the [FireSim documentation version 1.6.0](http://docs.fires.im/en/1.6.0) through the Initial Setup/Installation section. When cloning FireSim, use the URL of this repository instead.

Then, run the following commands to complete the installation:
```
sudo pip3 install numpy matplotlib
cd bru-firesim/sw/firesim-software/workloads/farzad/overlay/root/riscv-hpmcounters
make
cd bru-firesim/sw/firesim-software/workloads/farzad/overlay/root/isolbench/bench
make
```

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
#### Figure 12
To run the simulation:
```
cd bru-firesim/sw/firesim-software
./marshal -v build workloads/lt-bww-2.json
./marshal install workloads/lt-bww-2.json
firesim launchrunfarm -c config_runtime2.ini && firesim infrasetup -c config_runtime2.ini && firesim runworkload -c config_runtime2.ini && firesim terminaterunfarm -q -c config_runtime2.ini
```
To parse the result and create Figure 12:
```
cd bru-firesim/bru-expr/lt-bww
./run-lt-bww.sh [path-to-the-result-directory]
```
This will create the figure in `lt-bww.pdf`.

#### Figure 13
To run the simulation:
```
cd bru-firesim/sw/firesim-software
./marshal -v build workloads/vision-lt-3.json
./marshal install workloads/vision-lt-3.json
firesim launchrunfarm -c config_runtime3.ini && firesim infrasetup -c config_runtime3.ini && firesim runworkload -c config_runtime3.ini && firesim terminaterunfarm -q -c config_runtime3.ini
```
To parse the result and create Figure 13:
```
cd bru-firesim/bru-expr/vision-lt
./run-vision-lt.sh [path-to-the-result-directory]
```
This will create the figure in `vision-lt.pdf`.

## Where is the BRU source code?
The BRU source code is located [here](https://github.com/farzadfch/rocket-chip/blob/bf45db0dae0925ba86482d6ab8fa5bc37b158a93/src/main/scala/subsystem/BwRegulator.scala).

## Publication
Farzad Farshchi, Qijing Huang, and Heechul Yun, **"BRU: Bandwidth Regulation Unit for Real-Time Multicore Processors,"** IEEE Intl. Conference on Real-Time and Embedded Technology and Applications Symposium (RTAS), April 2020. [[paper](http://www.ittc.ku.edu/~farshchi/papers/bru-rtas2020-paper.pdf)] [[slides](http://www.ittc.ku.edu/~farshchi/papers/bru-rtas2020-slides.pdf)]
