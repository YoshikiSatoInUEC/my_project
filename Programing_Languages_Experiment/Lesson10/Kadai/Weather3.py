import urllib
import json

class WeatherHacks(object):
    def __init__( self, cityid ):
       self.cityid = cityid
       # weather hacks からの文字の読み込み，クラス内変数 wstring に保持
       src ="http://weather.livedoor.com/forecast/webservice/json/v1?city="+cityid
       f = urllib.urlopen(src)
       self.wstring = f.read()
       f.close()

    def Forecast( self, day='today'):
       # すでに保持されている wstring を解析することで天気を予報
        
        if(day=="today"):
            i=0
        elif(day=="tomorrow"):
            i=1

        self.q = json.loads(self.wstring)
        return self.q["forecasts"][i]["date"],":",q["forecasts"][i]["telop"]

    def Description( self ):
       # すでに保持されている wstring を解析することで概況を表示
        return json.loads(self.wstring)["description"]["text"]
        
        
