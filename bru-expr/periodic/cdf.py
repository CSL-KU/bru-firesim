#!/usr/bin/env python3.6

# https://stackoverflow.com/questions/24575869/read-file-and-plot-cdf-in-python

import sys
import numpy as np
import matplotlib.pyplot as plt

p_wcet = 0.9999

def cdf(data):
  data_size = len(data)
  
  # Set bins edges
  data_set = sorted(data)
  bins = np.append(data_set, data_set[-1]+1)
  bins = np.insert(bins, 0, data_set[0]-0.001)
  #print(bins)
 
  # Use the histogram function to bin the data
  counts, bin_edges = np.histogram(data, bins=bins, density=False)
  
  counts=counts.astype(float)/data_size
  
  # Find the cdf
  cdf = np.cumsum(counts)

  return cdf, bin_edges[0:-1]

sys.argv.pop(0)
plt.figure(figsize=(5, 3))

for filename in sys.argv:
  data = np.loadtxt(filename)
  data = np.delete(data, [0])
  cdf_data, bin_edges = cdf(data)
  i_wcet = next(x for x, val in enumerate(cdf_data) if val >= p_wcet)
  #print(cdf_data)
  #print(bin_edges)
  print(bin_edges[i_wcet])
  plt.plot(bin_edges/1000, cdf_data)

plt.ylim((0,1))
#plt.xlim((0.25, 0.5))
#plt.xlim((1.2, 2.7))
#plt.xlim((0.4, 1.5))
#plt.xlim((1.4, 2.4))
#plt.xticks(np.arange(1.2, 2.7, step=0.2))
plt.ylabel("CDF")
plt.xlabel("Response Time (ms)")
#plt.legend(sys.argv, loc='upper center', ncol=3, bbox_to_anchor=(0.5, 1.25), frameon=True)
plt.legend(sys.argv, loc='lower right')
#plt.legend(sys.argv, loc='upper center', ncol=3, bbox_to_anchor=(0.5, 1.5), frameon=True)
plt.grid(linestyle=':')
plt.savefig('cdf.pdf', bbox_inches = 'tight')
