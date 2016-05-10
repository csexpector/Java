#include "MediaLibrary.hpp"
#include <iostream>
#include <vector>
#include <string>


using namespace std;

void printTitles(string mediaType, std::vector<string> titles)
{
    cout << mediaType << " titles in the library are ";
    for (int index = 0; index < titles.size(); ++index)
    {
        cout << titles.at(index) << ", ";
    }
    cout << "\n";
}

int main()
{
    MediaLibrary myLib;
    myLib.add(MediaDescription("Music","Come Monday","Jimmy Buffett","Greatest Hits","Island","ComeMonday.mp3"));
    myLib.add(MediaDescription("Music","Fins","Jimmy Buffett","Greatest Hits","Island","Fins.mp3"));
    myLib.add(MediaDescription("Video","Banana Song","Minions","","Animation","minionsbananasong.mp4"));
    myLib.add(MediaDescription("Video","Banana","Minions","","Animation","minionsbanana.mp4"));
    printTitles("Music", myLib.getMusicTitles());
    printTitles("Video", myLib.getVideoTitles());
    cout <<  myLib.get("Fins").toString() << "\n";
    cout <<  myLib.get("Come Monday").toString() << "\n";
    cout <<  myLib.get("Banana Song").toString() << "\n";
    cout <<  myLib.get("Banana").toString() << "\n";
    myLib.remove("Fins");
    printTitles("All", myLib.getTitles());
}