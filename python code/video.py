import sys
from pytube import YouTube
from com.arthenica.mobileffmpeg import FFmpeg
import os
from com.chaquo.python import Python
from android.os import Environment
global location

def download_video(Link , Resolution):

    link  = YouTube(Link)
    title = str(link.title).replace(" ", "_")
    
    # files_dir = str(Python.getPlatform().getApplication().getFilesDir())
    files_dir = str(Environment.getExternalStorageDirectory())
    
    # audio_ = link.streams.get_audio_only().download("src\\main\\res\\raw\\audio_files")
    audio = link.streams.filter(only_audio = True , mime_type="audio/mp4").all()
    # audio_ = audio[0].download("src\\main\\res\\raw\\audio_files")

    audio_ = audio[0].download(files_dir + "/" + ".download_files")
 
    # audio_title = "storage\\emulated\\0\\.download_files\\{}_audio.mp4".format(title)
    audio_title = str(files_dir + "/" + ".download_files"  + "/" + title +  "_audio.mp4")

    os.rename(audio_, audio_title)
    
    # video_ = link.streams.filter(resolution = str(Resolution) , mime_type="video/mp4").first().download("storage\\emulated\\0\\.download_files")
    video_ = link.streams.filter(resolution = str(Resolution) , mime_type="video/mp4").first().download(files_dir + "/" + ".download_files")

    video_title = str(files_dir + "/" + ".download_files"  + "/" + title +  "_video.mp4")

    os.rename(video_, video_title)
    # input_video = ffmpeg.input(video_)
    # input_audio = ffmpeg.input(audio_)
    # input_audio = "src\\main\\res\\raw\\audio_files\\{}.mp4".format(title)
    # input_video = "src\\main\\res\\raw\\video_files\\{}.mp4".format(title)
    input_video = video_title
    input_audio = audio_title
    

    # file_location = "storage\\emulated\\0\\Download\\{}.mp4".format(title)
    # file_location = str(files_dir + "/Download/" + title + ".mp4")
    file_location = str(files_dir + "/" + ".video_files" + "/" +  title + ".mp4")
    
    
    # name = FFmpeg.execute("-i \""+ input_video +"\" -i \""+ input_audio +"\" -c:v copy -c:a aac " + file_location)
    FFmpeg.execute("-i \""+ input_video +"\" -i \""+ input_audio +"\" -c:v copy -c:a aac " + file_location)
    
    files = os.listdir(files_dir + "/" + ".video_files")
    for file in files:
        location = str(file)
    
    second_file_location = str(files_dir + "/" + ".video_files/" + str(file))
    # FFmpeg.execute("-i " + input_video + " -i " + input_audio + " -c copy -map 1:v:0 -map 0:a:0 -shortest " + file_location)

    # new_filename = file_location.replace("_" , " ")
    new_filename = str(files_dir + "/" + "Download/" + title.replace("_", " ") + ".mp4")

    # os.rename(file_location, new_filename)
    os.rename(second_file_location,new_filename)

    # os.remove(input_video)
    # os.remove(input_audio)
    
download_video("https://youtu.be/xvhEHpETvTo", "240p")
