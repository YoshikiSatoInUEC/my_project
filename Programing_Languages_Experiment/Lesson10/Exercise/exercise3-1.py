import numpy as np
import scipy as sp
import scipy.fftpack as spf
from scipy.io.wavfile import read
import matplotlib.pyplot as plt

class ShowWav(object):
  def __init__(self,fname):
   self.fname = fname
   y = read(fname)
   Fs = y[0]
   self.ydat = y[1]
   self.Nmax = self.ydat.shape[0]
      
   if(self.ydat.ndim>1):
       self.channel = self.ydat.shape[1]
   else:
       self.channel = 1
       self.ydat = self.ydat.reshape((self.Nmax,1))
   self.Nmax = int(pow(2,int(np.log2(self.Nmax))))
   
   self.delta = 1./Fs
   self.fdelta = 1./(self.Nmax*self.delta)
   self.t = np.arange(self.Nmax)*self.delta
   self.f = np.arange(-self.Nmax/2,self.Nmax/2)*self.fdelta


  def plot(self):
    for k in range(self.channel):
        plt.subplot(2,self.channel,2*k+1)
        plt.plot(self.t,self.ydat[:self.Nmax,k])
        plt.xlim(0,self.t[-1])
        plt.title("Amplitude Ch.%d" % k)
        plt.grid()

        Y = spf.fft(self.ydat[:self.Nmax,k])
        plt.subplot(2,self.channel,2*k+2)
        plt.xlim(self.f[0],self.f[-1])
        plt.semilogy(self.f,np.abs(spf.fftshift(Y)))
        plt.title("Power Ch.%d" % k)
        plt.grid()
    plt.show()

fname = "KyokoSampling.wav"

s = ShowWav(fname)
s.plot()
