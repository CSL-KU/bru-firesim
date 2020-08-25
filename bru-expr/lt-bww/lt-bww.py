#!/usr/bin/env python3.6
# https://matplotlib.org/3.1.1/gallery/lines_bars_and_markers/barchart.html#sphx-glr-gallery-lines-bars-and-markers-barchart-py

import matplotlib
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import sys

labels = np.genfromtxt("labels.txt",dtype='str')
domain1 = np.loadtxt("1-domain.txt")
domain3 = np.loadtxt("3-domain.txt")

domain1 = np.delete(domain1 / domain1[0], [0])
domain3 = np.delete(domain3 / domain3[0], [0])

x = np.arange(len(labels))  # the label locations
width = 0.3  # the width of the bars

fig, ax = plt.subplots(figsize=(5, 3))
rects1 = ax.bar(x - width/2, domain1, width, label='1 domain', edgecolor='black')
rects2 = ax.bar(x + width/2, domain3, width, label='3 domains', edgecolor='black')

# Add some text for labels, title and custom x-axis tick labels, etc.
ax.set_ylabel('Normalized Execution Time')
ax.set_xlabel('Co-runners Bandwidth Budget (MB/s)')
#ax.set_xticks(x)
plt.xticks(x, rotation=-50, ha="left", rotation_mode="anchor")
ax.set_xticklabels(labels)
ax.legend()
ax.grid(linestyle=':')
ax.set_axisbelow(True)
ax.axhline(y=1, ls='--', color='black', alpha=0.7)
plt.ylim((0,3.8))

fig.tight_layout()

plt.savefig('lt-bww.pdf')
