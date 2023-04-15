#!/usr/bin/env python3
# GrovePi Library for using the Grove - I2C ADC(http://www.seeedstudio.com/depot/Grove-I2C-ADC-p-1580.html)
# The GrovePi connects the Raspberry Pi and Grove sensors.  You can learn more about GrovePi here:  http://www.dexterindustries.com/GrovePi
#
# Have a question about this library?  Ask on the forums here:  http://forum.dexterindustries.com/c/grovepi
#

# Released under the MIT license (http://choosealicense.com/licenses/mit/).
# For more information see https://github.com/DexterInd/GrovePi/blob/master/LICENSE

import time,sys
import RPi.GPIO as GPIO
from smbus2 import SMBus , i2c_msg

#do wykresu
import random
from itertools import count
import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation

import csv

# use the bus that matches your raspi version
rev = GPIO.RPI_REVISION
if rev == 2 or rev == 3:
    bus = SMBus(1)
else:
    bus = SMBus(0)

#print(rev)

#important - set rasperry pi config.txt i2c speed to 100kHz or no communicaton with i2c


def getadc():
        mwrite = i2c_msg.write(0x04,[0x01,0x03,0x00,0x00,0x00])
        #mwrite = i2c_msg.write(0x04,[0x03,0x03,ch,0x03,0x03,0x03])
        mwrite1 = i2c_msg.write(0x04,[0x01])
        mread  = i2c_msg.read(0x04,8)
        #bus.write_byte_data(0x04,0x03,0x00)

    #data = bus.read_i2c_block_data(0x04,0x03,2)
        bus.i2c_rdwr(mwrite)
        bus.i2c_rdwr(mwrite1,mread)
        #for value in mread:
        #    print(value)
        #print(mread)
        dd = list(mread)
        #print(dd)
        #print(dd[0],dd[1],dd[2])
        return(256*dd[1] + dd[2])

#3if __name__ == "__main__":
  #adc= ADC()
"""
GPIO.setmode(GPIO.BCM)
GPIO.setup(8, GPIO.OUT)
GPIO.output(8,GPIO.LOW)
time.sleep(0.1)
GPIO.output(8,GPIO.HIGH)

time.sleep(1)
"""

decyzja = input("Czy chces zapisywac dane do pliku (tak/nie):\n")


while True:
  #	print(adc.adc_read())
    try:

      start = input("Aby rozpoczac pomiar zaloz sensor i wpisz 'start':\n")

      if start == "start" :
          #wykres
          plt.style.use('fivethirtyeight')
          x_vals = []
          y_vals = []
          index = count()

          def animate(i):
              try:
                  a = getadc()
                  if a >= 0 and a < 512 :
                    x_vals.append(next(index))
                    #y_vals.append(a)
                    y_vals.append(((1024+2*a)*10000)/(512-a))

                    if decyzja == "tak" :
                        with open('dane.csv', 'a') as file:
                            writer = csv.writer(file)
                            data = [((1024+2*a)*10000)/(512-a)]
                            writer.writerow(data)

                  plt.cla()
                  plt.plot(x_vals, y_vals)
                  plt.xlabel("nr pomiaru")
                  plt.ylabel("rezystancja [Ω]")
                  print(y_vals)
                  plt.tight_layout()
                  #time.sleep(1)

              except:
                  pass

          ani = FuncAnimation(plt.gcf(), animate, interval=300)
          plt.tight_layout()
          plt.show()

    except:
      pass
    time.sleep(0.1)
