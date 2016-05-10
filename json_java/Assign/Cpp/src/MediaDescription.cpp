#include "MediaDescription.hpp"
#include <sstream>
#include <iostream>

MediaDescription::MediaDescription(string mediaType, string title, string author,string album, string genre, string filename):
mediaType(mediaType),
title(title),
author(author),
album(album),
genre(genre),
filename(filename)
{}

MediaDescription::MediaDescription():
mediaType(""),
title(""),
author(""),
album(""),
genre(""),
filename("")
{}

string MediaDescription::str() const
{
    return this->toString();
}

string MediaDescription::toString() const
{
    std::stringstream stri;
    stri << "{" <<"\"fileName\": " << "\"" << valueOrNull(this->filename) << "\"" <<
    ", \"author\": " << "\"" << valueOrNull(this->author) << "\"" <<
    ", \"album\": " << "\"" << valueOrNull(this->album) << "\"" <<
    ", \"genre\": " << "\"" << valueOrNull(this->genre) << "\"" <<
    ", \"mediaType\": " << "\"" << valueOrNull(this->mediaType) << "\"" <<
    ", \"title\": " << "\"" << valueOrNull(this->title) << "\"" <<"}";
    return stri.str();
}

string MediaDescription::valueOrNull(const string &value) const
{
    return string("").compare(value) == 0 ? string("null") : value;
}

void MediaDescription::setMediaType(string mediaType)
{
    this->mediaType = mediaType;
}

void MediaDescription::setAuthor(string author)
{
    this->author = author;
}

void MediaDescription::setTitle(string title)
{
    this->title = title;
}

void MediaDescription::setAlbum(string album)
{
    this->album = album;
}

void MediaDescription::setGenre(string genre)
{
    this->genre = genre;
}

void MediaDescription::setFilename(string filename)
{
    this->filename = filename;
}

string MediaDescription::getMediaType() const
{
    return this->mediaType;
}

string MediaDescription::getAuthor() const
{
    return this->author;
}

string MediaDescription::getTitle() const
{
    return this->title;
}

string MediaDescription::getGenre() const
{
    return this->genre;
}

string MediaDescription::getFilename() const
{
    return this->filename;
}
