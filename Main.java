package com.company;
import java.util.*;

/* Negative harmony is a concept conceived by Swiss musicologist Ernst Levy. The idea is to reflect notes over an
axis to get new chords and, hopefully, interesting chord progressions. Source code by Patrick Park a.k.a Vres. */

public class Main {
    
    public static void main (String[] args) {
        Scanner user = new Scanner(System.in);

        LinkedHashMap<String, String> cs = new LinkedHashMap();
        cs.put("C", "07 00"); cs.put("Db","05 02"); cs.put("D", "03 04"); cs.put("Eb","01 06");
        cs.put("E", "11 08"); cs.put("F", "09 10"); cs.put("Gb", "07 00"); cs.put("G", "05 02");
        cs.put("Ab", "03 04"); cs.put("A", "01 06"); cs.put("Bb", "11 08"); cs.put("B", "09 10");

        /* This map represents the chromatic scale. Each note is paired with a string of two numbers:
        The first number denotes the distance in half-steps, from note in question to desired inverted
        note in the key of C, i.e over the axis of C/G.
        Second number is a half-step shift in the key of note in question, relative to the key of C; it
        is added to first number during inversion.
        For example, if you want to invert F in the key of C, i.e over the axis of C/G, the inverted note
        of F is (9 + 0) semitones up (in other words 3 semitones down) from F, which is D.
        */

        while (true) {

            System.out.print("Which notes do you want to invert?\n>> ");
            String notes = user.nextLine();

            if (notes.equals("0") || notes.equals("")) break;

            String[] givenNotes = ((String[]) toTitleCase(notes.split(" ")));

            System.out.print("Which Major key do you want to invert these in?\n>> ");
            String key = String.valueOf(toTitleCase(user.nextLine()));

            while (!cs.keySet().contains(key)) {
                System.out.println("Key not recognized. Acceptable note names: Cb, cb, f, dB, G, etc");
                System.out.println("Also, any unrecognizable notes you gave before this will not be inverted.");
                System.out.print("Input a new key:\n>> ");
                key = String.valueOf(toTitleCase(user.nextLine()));
            }

            System.out.println("Negative notes of " + Arrays.toString(givenNotes) +
            " over the axis of " + key + "/" +  getNote(cs, key, 7) + ":");

            for (int i = 0;i<givenNotes.length;i++)
                if (cs.containsKey(givenNotes[i]))
                    System.out.print(getNote(cs, givenNotes[i],
                                Integer.valueOf(cs.get(givenNotes[i]).substring(0,2)) +
                                        Integer.valueOf(cs.get(key).substring(3,5)))
                        + " "
                );
            System.out.println("\n");
        }
    }

    public static Object toTitleCase(Object o) {
        // Returns properly formatted note names.

        if (o instanceof String) {
            return ((String) o).substring(0, 1).toUpperCase() + ((String) o).substring(1).toLowerCase();

        } else if (o instanceof Cloneable) {
            String[] givenObjectToArray = ((String[]) o);
            String[] capitalizedNotes = new String[((String[])o).length];

            for (int i = 0; i<givenObjectToArray.length; i++)
                capitalizedNotes[i] = givenObjectToArray[i].substring(0, 1).toUpperCase() +
                        givenObjectToArray[i].substring(1).toLowerCase();

            return capitalizedNotes;
        }
        
        return null;
    }

    public static String getNote(LinkedHashMap map, String note, int interval) {
        // This method returns note from the chromatic scale a given number of half-steps away from provided note.

        List keys = new ArrayList(map.keySet());
        if (keys.contains(note))
            return String.valueOf(
                    keys.get((keys.indexOf(note)+interval)%12)
            );
        
        return null;
    }
}
