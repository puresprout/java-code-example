package bitwiseoperation;

/**
 * Created by sunghyun on 2016. 12. 19..
 */
public class Screen {
    public static Screen createScreen(int width, int height) {
        if (width % 8 != 0) {
            throw new IllegalArgumentException("넓이는 8의 배수가 되어야 합니다.");
        }

        byte[] bytes = new byte[width / 8 * height];
        return new Screen(bytes, width, height);
    }

    private int width;
    private int height;
    private byte[] bytes;

    private Screen(byte[] bytes, int width, int height) {
        this.width = width;
        this.height = height;
        this.bytes = bytes;
    }

    public void setPoint(int x, int y) {
        int index = y * width / 8 + x / 8;
        int offset = x % 8;

        int mask = 0x1 << 7 - offset;

        bytes[index] |= mask;
    }

    public void drawHorizontalLine(int sx, int ex, int y) {
        int sxi = y * width / 8 + sx / 8;
        int exi = y * width / 8 + ex / 8;

        int smask = 0xff >> (sx % 8);
        int emask = 0xff << (7 - (ex % 8));

        for (int i = sxi + 1; i < exi; i++) {
            bytes[i] = (byte) 0xff;
        }

        if (sxi == exi) {
            int mask = smask & emask;
            bytes[sxi] |= mask;
        } else {
            bytes[sxi] |= smask;
            bytes[exi] |= emask;
        }
    }

    public void printScreen() {
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 0; j < 8; j++) {
                if ((bytes[i] & (0x1 << 7 - j)) > 0) {
                    System.out.print(1);
                } else {
                    System.out.print(0);
                }
            }

            if ((i + 1) * 8 % width == 0) {
                System.out.println();
            }
        }
    }
}
