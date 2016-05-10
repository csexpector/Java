
import java.io.IOException;
//import org.json.JSONException;


public class Main {

    public static void main(String[] args) throws IOException {
        // Please change the name of these files!!!
        String jsonInput = "/Users/dttung/NetBeansProjects/Media/src/media.json";
        String jsonOutput = "/Users/dttung/NetBeansProjects/Media/src/media2.json";
        
        // Test open media with json input file
        MediaLibrary myLib = new MediaLibrary(jsonInput);
        boolean addTest = myLib.add(new MediaDescription("Music","Come Monday","Jimmy Buffett","Uptown Special","Island","ComeMonday.mp3"));
        addTest = myLib.add(new MediaDescription("Music","Fins","Jimmy Buffett","Greatest Hits","Island","Fins.mp3"));
        addTest = myLib.add(new MediaDescription("Video","Banana Song","Minions","","Animation","minionsbananasong.mp4"));
        addTest = myLib.add(new MediaDescription("Video","Banana","Minions","","Animation","minionsbanana.mp4"));
        
        System.out.print("Music titles in the library are ");
        for (String s: myLib.getMusicTitles()) {
            System.out.print(s + ", ");
        }
        
        System.out.print("\n");
        System.out.print("Video titles in the library are ");
        for (String s: myLib.getVideoTitles()) {
            System.out.print(s + ", ");
        }
        
        System.out.print("\n");
        for (MediaDescription m: myLib.getTheMedia()) {
            System.out.println(m);
        }
        
        boolean removeTest = myLib.remove("Fins");
        
        System.out.print("Titles after removing Fins are ");
        for (String s: myLib.getTitles()) {
            System.out.print(s + ", ");
        }
        
        // Test write library to a new file
        myLib.toJsonFile(jsonOutput);
    }
}
