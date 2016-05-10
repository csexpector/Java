

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.json.JSONException;
import org.json.simple.JSONObject;
//import org.json.simple.*;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class MediaLibrary{
    ArrayList<MediaDescription> theMedia;
    int musicCount, videoCount;
    
    public MediaLibrary() {
        theMedia = new ArrayList<MediaDescription>();
    }

    public MediaLibrary(String jsonFileName) 
    {
        theMedia = new ArrayList<MediaDescription>();
        try {
            JSONParser parser = new JSONParser();
            Map medias = (Map) parser.parse(new FileReader(jsonFileName));
            Iterator keys = medias.entrySet().iterator();
            
            while (keys.hasNext())
            {                
                Map.Entry entry = (Map.Entry) keys.next();
                JSONObject media = (JSONObject)entry.getValue();
                //org.json.simple.JSONObject media = (org.json.simple.JSONObject) parser.parse(value);
                MediaDescription mdesc = new MediaDescription(media);
                theMedia.add(mdesc);
            }
        } catch (IOException ex) {
            Logger.getLogger(MediaLibrary.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(jsonFileName + " not found");
        } catch (ParseException ex) {
            Logger.getLogger(MediaLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void toJsonFile(String jsonFileName) throws IOException
    {
        FileWriter jsonFile = new FileWriter(jsonFileName);
        jsonFile.write("{\n");
        for (int i = 0; i < theMedia.size() - 1; i++)
        {
            MediaDescription mdesc = theMedia.get(i);
            jsonFile.write("\"" + mdesc.getTitle() + "\":");
            org.json.simple.JSONObject obj = mdesc.toJson();
            jsonFile.write(obj.toJSONString() + ",\n");
            
        }
        MediaDescription mdesc = theMedia.get(theMedia.size() - 1);
        jsonFile.write("\"" + mdesc.getTitle() + "\":");
        org.json.simple.JSONObject obj = mdesc.toJson();
        jsonFile.write(obj.toJSONString() + "\n");
        jsonFile.write("}\n");
        jsonFile.close();
    }
    
    public boolean add(MediaDescription aClip) {
        theMedia.add(aClip);
        if (aClip.getMediaType() != null && aClip.getMediaType().equals("Music")) {
            musicCount++;
        } else if (aClip.getMediaType() != null && aClip.getMediaType().equals("Video")) {
            videoCount++;
        }
        System.out.println("Adding: " + aClip.getTitle());
        return true;
        // A method to add a new song or video the library. True is returned when the request is successful.
    }
    
    public boolean remove(String aTitle) {
        MediaDescription removal = null;
        for (MediaDescription m : theMedia) {
            if (m.getTitle() != null && m.getTitle().equals(aTitle)) {
                System.out.println("Removing: " + m.getTitle());
                removal = m;
                break;
            }
        }
        theMedia.remove(removal);
        return true;
        // A method to remove the named MediaDescription from the library.
    }
    
    public MediaDescription get(String aTitle) {
        for (MediaDescription m : theMedia) {
            if (m.getTitle() != null && m.getTitle().equals(aTitle)) {
                return m;
            }
        }
        // Returns the media description with title aTitle.
        return null;
    }
    
    public String[] getTitles() {
        String[] mediaArray = new String[theMedia.size()];
        int count = 0;
        
        for (MediaDescription m : theMedia) {
            if (m.getMediaType() != null) {
                mediaArray[count] = m.getTitle();
                count++;
            }
        }
        
        return mediaArray;
        // Returns an array of strings, which are all of the titles in the library.
    }
    
    public String[] getMusicTitles() {
        String[] musicArray = new String[musicCount];
        int count = 0;
        
        for (MediaDescription m : theMedia) {
            if (m.getMediaType() != null && m.getMediaType().equals("Music")) {
                musicArray[count] = m.getTitle();
                count++;
            }
        }
        
        return musicArray;
        // Returns an array of strings, which are all of the music titles in the library.
    }
    
    public String[] getVideoTitles() {
        String[] videoArray = new String[videoCount];
        int count = 0;
        
        for (MediaDescription m : theMedia) {
            if (m.getMediaType() != null && m.getMediaType().equals("Video")) {
                videoArray[count] = m.getTitle();
                count++;
            }
        }
        
        return videoArray;
        // Returns an array of strings, which are all of the video titles in the library.
    }
    
    public ArrayList<MediaDescription> getTheMedia() {
        return theMedia;
    }
}