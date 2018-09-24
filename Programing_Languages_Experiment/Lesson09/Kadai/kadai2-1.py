import numpy as np
import scipy as sp
from scipy.io.wavfile import write

def GenFreq(Fc,Fs,dur):
 Amp=4000.
 N = Fs*dur
 delta = 1./Fs
 t = np.arange(N)*delta
 x = Amp*np.sin(2*np.pi*Fc*t)
 return x

Fs = 22050.
Fc =(262,294,330,349,392,440,494,523)
d = 3.
N = Fs*d

for i in Fc:
 x = GenFreq(i,Fs,d)
 if i==Fc[0]:  
  list=[x]
 else:
  list+=[x]

write("Generated.wav",Fs,np.int16(list).reshape(N*8,1))

