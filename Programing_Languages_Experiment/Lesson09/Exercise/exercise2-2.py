import numpy as np
import scipy as sp
import matplotlib.pyplot as plt

from scipy.io.wavfile import write

Fs=22050.
Fc=1000.
d=5.
Amp=4000.
N=Fs*d

t = np.arange(N)*1./Fs
x = Amp*np.sin(2*np.pi*Fc*t)

write("hogehoge.wav",Fs,np.int16(x).reshape(N,1))

