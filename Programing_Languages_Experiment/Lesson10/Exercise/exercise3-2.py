import numpy as np
import scipy as sp
import matplotlib.pylab as plt

from scipy.io.wavfile import write


class SoundObj(object):
    def __init__(self,Fs,dur):
        self.Fcs = {"c":262.,"D":294.,"E":330.,"F":349,"G":392.,"A":440.,"B":494.}
        self.Fs = Fs
        self.dur = dur
        self.N = int(Fs*dur)
        self.x = np.zeros(self.N)
        self.Amp=2000.
        delta = 1./Fs
        self.t = np.arange(self.N)*delta

    def setTone(self,code="C"):
        f = self.Fcs[code]
        self.x=self.Amp*np.sin(2*np.pi*f*self.t)



    def getLen(self):
        return self.N



    def getSound(self):
        return self.x



Fs = 22100.
dur = 1.0

s = SoundObj(Fs,dur)
s.setTone("E")

fname="OOPmksnd.wav"
write(fname,Fs,np.int16(s.getSound()).reshape(s.getLen(),1))
        
