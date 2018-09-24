import numpy as np
import scipy as sp
import matplotlib.pyplot as plt
import scipy.fftpack as spf
from scipy.io.wavfile import read

y = read("KyokoSampling.wav")

Fs = y[0]
delta = 1./Fs
Nmax =256
fdelta = 1./(Nmax*delta)
t = np.arange(Nmax)*delta
f = np.arange(-Nmax/2,Nmax/2)*fdelta

def SpecGram(x):
 yl = x[:Nmax,0]
 Yl = spf.fft(yl)
 return np.abs(np.log(Yl))


z = [SpecGram(y[1][:Nmax])]
for i in range(900):
 z += [SpecGram(y[1][Nmax*(i+1):Nmax*(i+2)])]

z2 = np.array(z)
z3 = z2.transpose()

plt.figure()
plt.subplot(2,1,1)
plt.imshow(z3)
plt.ylim(210,0)
plt.show()
