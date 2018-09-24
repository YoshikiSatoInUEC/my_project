#! python3
#automateTweet.py 自動でツイート

from selenium import webdriver
import sys

#firefox立ち上げてアクセス
browser = webdriver.Firefox()
browser.get('https://twitter.com/login')

#ログイン
uname_elm = browser.find_element_by_class_name('js-username-field')
uname_elm.send_keys('mlb_yos')
pass_elm  = browser.find_element_by_class_name('js-password-field')
pass_elm.send_keys('kershawnaruto')
pass_elm.submit()

#ツイート
time.sleep(1)
tweet = browser.find_element_by_id("tweet-box-home-timeline")
tweet.send_keys("test")
browser.find_element_by_class_name("tweeting-text").click()
