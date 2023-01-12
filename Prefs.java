import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Saves/Reads preferences
 *
 * @author Adams
 */
public class Prefs
{
    private ArrayList<PrefItem> prefs;

    public void set(String key, String value) { 
        PrefItem i = new PrefItem(key, value);
        prefs.add(i);
    }

    public String get(String key) { 
        for (int i = 0; i < prefs.size(); i++) {
            if (prefs.get(i).key.equals(key)) {
                return prefs.get(i).value;
            }
        }
        return null;
    }

    public void save() { 
        FileOutputStream fileStream = null;
        PrintWriter outFS = null;

        try {
            fileStream = new FileOutputStream("prefs.txt");
            outFS = new PrintWriter(fileStream);

            for (int i=0; i<prefs.size(); i++) {
                outFS.println( prefs.get(i).key + ":" + prefs.get(i).value );
            }

            outFS.flush();
            fileStream.close();
        }
        catch (IOException e) {
            System.out.println("error");
        }
        // go here
    }

    public void load() { }

    public void clear() { prefs.clear(); } 

    public int size() { return prefs.size(); } 

    public Prefs() { prefs = new ArrayList<PrefItem>(); }

    public static void main(String[] a) {
        Prefs p = new Prefs();

        p.set("volume", "10"); 
        p.set("graphics", "high");
        p.save();

        p.clear();

        p.load();
        p.size();
        p.get("volume");
        p.get("graphics");
    }
}













/*
private ArrayList<PrefItem> prefs;

public Prefs() {
prefs = new ArrayList<PrefItem>();
}

public void clear() { prefs.clear(); }

public void load() { 
FileInputStream fileByteStream = null; // File input stream
Scanner inFS = null;                   // Scanner object

try {
fileByteStream = new FileInputStream("prefs.txt");
inFS = new Scanner(fileByteStream);
inFS.useDelimiter("[:\r\n]+");

while (inFS.hasNext()) {
String key = inFS.next();
String value = inFS.next();
set(key, value);
}

fileByteStream.close();
}
catch (IOException error) {
System.err.println(error);
}
}

public void save() { 
FileOutputStream fileStream = null;
PrintWriter outFS = null;

try {
fileStream = new FileOutputStream("prefs.txt");
outFS = new PrintWriter(fileStream);

for (int i=0; i<prefs.size(); i++) {
outFS.println( prefs.get(i).getKey() + ":" + prefs.get(i).getValue() );
}

outFS.flush();
fileStream.close();
}
catch (IOException e) {
System.err.println(e);
}
}

public String get(String key) { 
for (int i=0; i<prefs.size(); i++) {
PrefItem item = prefs.get(i);
if (item.getKey().equals(key)) {
return item.getValue();
}
}
return null; 
}

public void set(String key, String value) { 
PrefItem item = new PrefItem(key, value);
prefs.add(item);
}

public int size() {
return prefs.size();
}

public static void main(String[] args) {
Prefs p = new Prefs();
assert p.size() == 0 : "1";

p.set("aKey", "aValue");
assert p.size() == 1 : "2";
assert p.get("aKey").equals("aValue") : "3";

p.set("bKey", "bValue");
assert p.size() == 2 : "4";
assert p.get("bKey").equals("bValue") : "5";

p.save();
p.clear();
assert p.size() == 0 : "6";

p.load();
assert p.size() == 2 : "7";
assert p.get("aKey").equals("aValue") : "8";
assert p.get("bKey").equals("bValue") : "9";
}
 */