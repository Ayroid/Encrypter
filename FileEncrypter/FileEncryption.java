package FileEncrypter;

import java.util.*;

import MessageEncrypter.Endeco;

import java.io.*;

public class FileEncryption extends Endeco {

    public boolean encrypt(String filename, String extension) throws IOException {
        File obj = new File(filename);
        String encoded = "";
        Scanner reader = null;
        try {
            reader = new Scanner(obj);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return false;
        }
        FileWriter writer = null;
        writer = new FileWriter("FileWindow\\encoded.txt", false);
        writer.append(encoder(extension) + "\n");
        Random random = new Random();
        while (reader.hasNextLine()) {
            int turns = random.nextInt(10);
            String data = reader.nextLine();
            int copy = turns;
            while (copy >= 0) {
                encoded = encoder(data);
                data = encoded;
                copy--;
            }
            if (encoded.length() % 2 == 0)
                encoded = encoded + turns;
            else
                encoded = turns + encoded;
            writer.append(encoded + "\n");
        }
        reader.close();
        writer.close();
        return true;
    }

    public boolean decrypt(String filename) throws IOException {
        File obj = new File(filename);
        String decoded = "";
        int turns = 0;
        Scanner reader = null;
        try {
            reader = new Scanner(obj);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return false;
        }
        String extension = decoder(reader.nextLine());
        FileWriter writer = null;
        writer = new FileWriter("FileWindow\\decoded." + extension, false);
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            if (data.length() > 0) {
                if ((data.length() - 1) % 2 == 0) {
                    turns = Character.getNumericValue(data.charAt(data.length() - 1));
                    data = data.substring(0, data.length() - 1);
                } else {
                    turns = Character.getNumericValue(data.charAt(0));
                    data = data.substring(1, data.length());
                }
            }

            if (!(turns >= 0 && turns <= 9))
                turns = 0;

            while (turns >= 0) {
                decoded = decoder(data);
                data = decoded;
                turns--;
            }
            writer.append(data + "\n");
        }
        reader.close();
        writer.close();
        return true;
    }

    public static void main(String[] args) {
        FileEncryption f1 = new FileEncryption();
        Scanner sc = new Scanner(System.in);
        String filename = "";
        System.out.println(
                "Instructions:\nEncoded & Decoded Files can be collected from:\n\"Text-Decoder-Encoder\\FileWindow\\\"");
        System.out.println("Press:\nE: Encrypt\nD: Decrypt");
        String ch = sc.next().toUpperCase();
        String extension = "";
        sc.nextLine();
        switch (ch) {
            case "E":
                System.out.print("Enter File Path: ");
                filename = sc.nextLine();
                if (filename.charAt(0) == '"') {
                    filename = filename.substring(1, filename.length() - 1);
                    extension = filename.substring(filename.indexOf(".") + 1, filename.length());
                }
                try {
                    if (!f1.encrypt(filename, extension))
                        break;
                } catch (IOException e) {
                    System.out.println("IOException");
                    break;
                }
                System.out.println(
                        "File Encrypted Successfully!\nFile Location: \"Text-Decoder-Encoder\\FileWindow\\encoded.txt\"");
                break;

            case "D":
                System.out.print("Enter File Path: ");
                filename = sc.nextLine();
                try {
                    f1.decrypt(filename);
                } catch (IOException e) {
                    System.out.println("IOException");
                    break;
                }
                System.out.println(
                        "File Decrypted Successfully\nFile Location: \"Text-Decoder-Encoder\\FileWindow\\decoded.txt\"");
                break;
            default:
                System.out.println("Wrong Choice!");
        }
        sc.close();
    }
}
