package ex02;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Process {
    public Process() {}  

    public void ReadAll(String[] args) throws java.io.IOException {
        if(args.length == 1) {
            String parametrFirst = "--current-folder";
            String[] inputParameter = new String[3];
            inputParameter = args[0].split("=");
            String[] commandLine = new String[5];
            Scanner in = new Scanner(System.in);

            if(inputParameter.length == 2 && inputParameter[0].equals(parametrFirst)) {
                Path dir = Paths.get(inputParameter[1]);
                dir = dir.normalize();
                System.out.println(dir.toAbsolutePath());
                while (true) {
                    System.out.print("-> ");
                    commandLine = in.nextLine().split(" ");
                    if(commandLine.length == 0) continue;
                    switch (commandLine[0].toLowerCase()) {
                        case "":
                            continue;
                        case "exit":
                            System.exit(0);
                        break;
                        case "mv":
                            commandLineMV(commandLine, dir);
                        break;
                        case "ls":
                            commandLineLS(commandLine, dir);
                        break;
                        case "cd":
                            dir = commandLineCD(commandLine, dir);
                        break;
                        default:
                            printSmthngWrong();
                        break;
                    }
                }    
            }
            in.close();
        }        
    }

    private void commandLineMV(String[] commandLine, Path dir) throws IOException {
        try {
            if(commandLine.length == 3) {
                Path par1 = Paths.get(dir.toAbsolutePath() + "/" + commandLine[1]).normalize();
                Path par2 = Paths.get(dir.toAbsolutePath() + "/" + commandLine[2]).normalize();
                if(Files.isDirectory(par2)) {
                    par2 = Paths.get(par2.toString() + "/" + par1.getFileName());
                }
                Files.move(par1, par2, StandardCopyOption.REPLACE_EXISTING);
            } else {
                printSmthngWrong();
            }
        } catch (Exception e) {
            printNoFileOrDir(commandLine[0], commandLine[1], commandLine[2]);
        }
    }

    private void commandLineLS(String[] commandLine, Path dir) throws IOException {
        if(commandLine.length == 1) {
            if(Files.isDirectory(dir)) {
                try(DirectoryStream<Path> entries = Files.newDirectoryStream(dir)) {
                    for(Path item : entries) {
                        if(Files.isDirectory(item)) {
                            long dirSize = getFolderSize(item);
                            printStrSize(item.getFileName().toString(), dirSize);
                        } else {
                            printStrSize(item.getFileName().toString(), Files.size(item));
                        }
                    }
                } catch (Exception e) {
                    printSmthngWrong();
                }
            } else {
                printSmthngWrong();
            }
        } else {
            printNoFileOrDir(commandLine[0], commandLine[1]);
        }
    }

    private long getFolderSize(Path inputParameter) throws IOException {
        long length = 0;
        try(DirectoryStream<Path> entries = Files.newDirectoryStream(inputParameter)) {
            for(Path item : entries) {
                if(Files.isDirectory(item)){
                    length += getFolderSize(item);
                } else{
                    length += Files.size(item);
                }
            }
        } catch (Exception e) {
            printSmthngWrong();
        }
        return length;
    }

    private void printStrSize(String name, long length) {
        System.out.print(name + " ");
        System.out.printf(" %.0f", Math.ceil(length/1024.f));
        System.out.println(" KB");
    }

    private Path commandLineCD(String[] commandLine, Path dir) {
        if(commandLine.length == 2) {
            dir = Paths.get(dir.toAbsolutePath() + "/" + commandLine[1]);
            dir = dir.normalize();
            if(!Files.isDirectory(dir)) {
                dir = dir.getParent();
                printNoFileOrDir(commandLine[0], commandLine[1]);
            }
            System.out.println(dir.toAbsolutePath());
        }
        return dir;
    }

    private void printSmthngWrong() {
        System.out.println("Something wrong, please try again!");
    }
    private void printNoFileOrDir(String commandName, String parameter) {
        System.out.println(commandName + ": no such file or directory: " + parameter);
    }
    private void printNoFileOrDir(String commandName, String parameter1, String parameter2) {
        System.out.println(commandName + ": no such file or directory: " + parameter1 + " " + parameter2);
    }
}
