
import org.json.simple.*;

public class MediaDescription {
       private final String mMediaType;
    
    private final String mTitle;
    
    private final String mAuthor;
   
    private final String mAlbum;
    
    private final String mGenre;
    
    private final String mFilename;
    
    public MediaDescription() {
        mMediaType = null;
        mTitle = null;
        mAuthor = null;
        mAlbum = null;
        mGenre = null;
        mFilename = null;
    }
    // constructor from JSONObject
    public MediaDescription(JSONObject jsonObj)
    {
        // use get method of JSONObject class to assign value to private variables
        mMediaType = (String) jsonObj.get("mediaType").toString();
        mTitle = (String) jsonObj.get("title");
        mAuthor = (String) jsonObj.get("author");
        mAlbum = (String) jsonObj.get("album");
        mGenre = (String) jsonObj.get("genre");
        mFilename = (String) jsonObj.get("filename");
    }
    // convert to JSONObject using put method
    public JSONObject toJson()
    {
        JSONObject media = new JSONObject();
        media.put("mediaType", mMediaType);
        media.put("title", mTitle);
        media.put("author", mAuthor);
        media.put("album", mAlbum);
        media.put("genre", mGenre);
        media.put("filename", mFilename);
        return media;
    }
    
    public MediaDescription(String mediaType, String title, String author, String album, String genre, String filename) {
        mMediaType = mediaType;
        mTitle = title;
        mAuthor = author;
        mAlbum = album;
        mGenre = genre;
        mFilename = filename;
    }
    
    /**
     * Returns a media type of either music or video.
     * @return a media type.
     */
    public String getMediaType() {
        return mMediaType;
    }
    
    /**
     * Returns a title of a song or video/movie.
     *
     * @return a title of the media.
     */
    public String getTitle() {
        return mTitle;
    }
    
    /**
     * Returns a name for the author of the media or leading actor/actress of video.
     *
     * @return a name of the author/actor/actress
     */
    public String getAuthor() {
        return mAuthor;
    }
    
    /**
     * Returns a name for the album of the song.
     *
     * @return a name of the album.
     */
    public String getAlbum() {
        return mAlbum ;
    }
    
    /**
     * Returns a genre of the video/movie.
     *
     * @return a genre of the video.
     */
    public String getGenre() {
        return mGenre ;
    }
    
    /**
     * Returns a filename of the media file.
     *
     * @return a filename of the media.
     */
    public String getExtras() {
        return mFilename ;
    }
    
    @Override
    public String toString() {
        String theFile = "\"fileName\":\"" + mFilename + "\"";
        String theAuthor = "\"author\":\"" + mAuthor + "\"";
        String theAlbum = "\"album\":\"" + mAlbum + "\"";
        String theGenre = "\"genre\":\"" + mGenre + "\"";
        String theType = "\"mediaType\":" + mMediaType;
        String theTitle = "\"title\":\"" + mTitle + "\"";
        return "{" + theFile + "," + theAuthor + "," + theAlbum + "," + theGenre + "," + theType + "," + theTitle + "}";
    }
    
}