package gabrielle.freville.mareu1.model;


import androidx.annotation.NonNull;

import java.io.Serializable;

import gabrielle.freville.mareu1.R;

public enum Room implements Serializable {
    MARIO(R.color.brightred, "Mario"),
    TOAD(R.color.lightred, "Toad"),
    BOWSER(R.color.lightorange, "Bowser"),
    DAISY(R.color.lightyellow, "Daisy"),
    LUIGI(R.color.brightgreen, "Luigi"),
    YOSHI(R.color.lightgreen, "Yoshi"),
    MELODY(R.color.lightblue, "Melody"),
    WARIO(R.color.lightpurple, "Wario"),
    PEACH(R.color.lightpink, "Peach"),
    LUMA(R.color.luma, "Luma");
    int color;
    String label;

    Room(int color, String label) {
        this.color = color;
        this.label = label;
    }

    @NonNull
    @Override
    public String toString() {
        return label;
    }

    public int getColor() {
        return color;
    }
}
