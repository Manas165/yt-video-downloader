import sys
from pytube import YouTube

def download_video(Video_link ,Resolution,Format , Value)
    link =YouTube(str(Video_link))
    video = link.streams.filter(res=str(Resolution) , file_extension=str(Format)).first()
    video.download("storage/emulated/0/Download")
    Value = 1
    Value = 0