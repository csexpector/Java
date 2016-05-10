#include <iostream>
#include <unordered_map>
#include <vector>
#include "MediaLibrary.hpp"


using namespace std;

MediaLibrary::MediaLibrary():
media(new unordered_map<string, MediaDescription>()),
musicTitles(new std::vector<string>()),
videoTitles(new std::vector<string>()),
DEBUG(true)
{}

MediaLibrary::~MediaLibrary()
{
    delete media;
    delete musicTitles;
    delete videoTitles;
}

bool MediaLibrary::add(MediaDescription aClip)
{
    log(string("Adding: ") + aClip.getTitle());
    string title = aClip.getTitle();
    string mediaType = aClip.getMediaType();
    (*media)[title] = aClip;
    bool isMusic = string("Music").compare(mediaType) == 0;
    bool isVideo = string("Video").compare(mediaType) == 0;
    if (isMusic)
    {
        musicTitles->push_back(title);
    } else if (isVideo)
    {
        videoTitles->push_back(title);
    }
    else
    { }
    return true;
}

bool MediaLibrary::remove(const string& aTitle)
{
    log(string("Removing " + aTitle));
    MediaDescription title = (*media)[aTitle];
    string mediaType = title.getMediaType();
    bool isMusic = string("Music").compare(mediaType) == 0;
    bool isVideo = string("Video").compare(mediaType) == 0;
    if (isMusic)
    {
        musicTitles->push_back(title.getTitle());
    } else if (isVideo)
    {
        videoTitles->push_back(title.getTitle());
    }
    media->erase(aTitle);
    return true;
}

template <class T>
void MediaLibrary::deleteFromVector(std::vector<T>& vector, T item)
{
    typedef typename std::vector<T>::iterator iterator;
    for (iterator iter = vector.begin(); iter != vector.end(); ++iter)
    {
        if (vector->at(iter).compare(item) == 0)
        {
            vector->erase(iter);
            break;
        }
    }
}

MediaDescription MediaLibrary::get(string aTitle)
{
    return (*media)[aTitle];
}
std::vector<string> MediaLibrary::getTitles()
{
    std::vector<string> * titleVector = new std::vector<string>();;
    for (unordered_map<string, MediaDescription>::iterator keyVal = media->begin(); keyVal != media->end(); ++keyVal)
    {
        titleVector->push_back(keyVal->first);
    }
    return *titleVector;
}
std::vector<string> MediaLibrary::getMusicTitles()
{
    return *(this->musicTitles);
}
std::vector<string> MediaLibrary::getVideoTitles()
{
    return *(this->videoTitles);
}

void MediaLibrary::log(string message) const
{
    if (DEBUG)
    {
        cout << message << "\n";
    }
}
