#!/usr/bin/env python3.6
# https://matplotlib.org/3.1.1/gallery/lines_bars_and_markers/barchart.html#sphx-glr-gallery-lines-bars-and-markers-barchart-py

import matplotlib
import matplotlib.pyplot as plt
import numpy as np
import sys

parsed = np.loadtxt("parsed.txt")

labels = ["disparity", "mser", "texture_syn"]

domain1 = parsed[0:3] / parsed[6:9]
domain3 = parsed[3:6] / parsed[6:9]

x = np.arange(len(labels))  # the label locations
width = 0.12  # the width of the bars

fig, ax = plt.subplots(figsize=(3.5, 2.3))
rects1 = ax.bar(x - width/2, domain1, width, label='1 domain', edgecolor='black')
rects2 = ax.bar(x + width/2, domain3, width, label='3 domains', edgecolor='black')

# Add some text for labels, title and custom x-axis tick labels, etc.
ax.set_ylabel('Normalized Execution Time')
plt.ylim((0,7.5))
plt.xlim((-0.4,2.4))
plt.yticks(np.arange(0, 8))
ax.set_xticks(x)
ax.set_xticklabels(labels)
#ax.legend(loc='upper center', ncol=5, bbox_to_anchor=(0.5, 1.2))
ax.legend()
ax.grid(linestyle=':')
ax.set_axisbelow(True)

fig.tight_layout()

plt.savefig('vision-lt.pdf')
