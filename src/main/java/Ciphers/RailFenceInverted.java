package Ciphers;

public class RailFenceInverted extends RailFence {
    @Override
    protected void next_position() {
        if (intKey == 1) {
            col++;
        } else if (desc) {
            if (row >= intKey - 1) {
                desc = false;
                row--;
                col++;
            } else {
                row++;
            }
        } else {
            if (row <= 0) {
                desc = true;
                row++;
                col++;
            } else {
                row--;
            }
        }
    }
}
