import urllib
import json

def Forecast(city="",day="today"):
 src = "http://weather.livedoor.com/forecast/webservice/json/v1?city="+city
 f=urllib.urlopen( src )
 str = f.read()
 f.close()

 q = json.loads(str)

 if day=="today":
  i=0
 elif day=="tomorrow":
  i=1

 return q["forecasts"][i]["date"],":",q["forecasts"][i]["telop"]

forecast = Forecast(city="400040")
print "Today:",forecast[0],forecast[1],forecast[2]

forecast = Forecast(city="400040",day="tomorrow")
print "Tommorow:",forecast[0],forecast[1],forecast[2]
