#include <string>

using namespace std;

class MediaDescription
{
    public:
    MediaDescription(string, string, string, string, string, string);
    MediaDescription();
    string str() const;
    string toString() const;
    
    void setMediaType(string);
    void setTitle(string);
    void setAuthor(string);
    void setAlbum(string);
    void setGenre(string);
    void setFilename(string);
    
    string getMediaType() const;
    string getTitle() const;
    string getAuthor() const;
    string getAlbum() const;
    string getGenre() const;
    string getFilename() const;
    
    private:
    string valueOrNull(const string&) const;
    string mediaType;
    string title;
    string author;
    string album;
    string genre;
    string filename;
};
