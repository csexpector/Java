#include "MediaDescription.hpp"
#include <vector>
#include <string>
#include <unordered_set>
#include <unordered_map>

using namespace std;


class MediaLibrary
{
	
public:
    MediaLibrary();
    ~MediaLibrary();
    bool add(MediaDescription aClip);
    bool remove(const string& aTitle);
    MediaDescription get(string);
    std::vector<string> getTitles();
    std::vector<string> getMusicTitles();
    std::vector<string> getVideoTitles();
    template <class T>
    void deleteFromVector(std::vector<T>&, T item);
    
private:
    const bool DEBUG;
    void log(string message) const;
    unordered_map<string, MediaDescription> * media;
    std::vector<string> * musicTitles;
    std::vector<string> * videoTitles;
    
};
