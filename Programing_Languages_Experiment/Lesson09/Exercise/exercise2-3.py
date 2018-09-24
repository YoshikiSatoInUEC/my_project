import numpy as np
import scipy as sp
import matplotlib.pyplot as plt

import scipy.fftpack as spf
from scipy.io.wavfile import read

y = read("KyokoSampling.wav")

Fs = y[0]
delta = 1./Fs
Nmax = 65536
fdelta = 1./(Nmax*delta)

t = np.arange(Nmax)*delta
f = np.arange(-Nmax/2,Nmax/2)*fdelta

yl=y[1][:Nmax,0]
Yl = spf.fft(yl)

plt.figure()

plt.subplot(2,1,1)
plt.plot(t,yl)
plt.xlim(0,t[-1])
plt.title("Amplitude")
plt.grid()

plt.subplot(2,1,2)
plt.semilogy(f,np.abs(spf.fftshift(Yl)))
plt.xlim(f[0],f[-1])
plt.title("Power(Semi log)")
plt.grid()

plt.show()
