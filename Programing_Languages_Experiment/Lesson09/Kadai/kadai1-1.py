import urllib
import json

def Forecast(city=""):
 src = "http://weather.livedoor.com/forecast/webservice/json/v1?city="+city
 f=urllib.urlopen( src )
 str = f.read()
 f.close()

 q = json.loads(str)

 return q["description"]["text"]

print Forecast(city="400040")

