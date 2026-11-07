import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.stream.Stream;

public class FileSystemTask {
    public static void main(String[] args) throws IOException {
        String surname = "Vasilev";
        String name = "Vladislav";

        //1.Создаем директорию <surname>
        Path surnameDir = Paths.get(surname);
        Files.createDirectories(surnameDir);

        //2.Внутри создаём файл <name>
        Path nameFile = surnameDir.resolve(name);
        Files.createFile(nameFile);

        //3.Создаем вложенные директории dir1/dir2/dir3 и копируем туда файл <name>
        Path dir3 = surnameDir.resolve("dir1/dir2/dir3");
        Files.createDirectories(dir3);
        Path copiedFile = dir3.resolve(name);
        Files.copy(nameFile, copiedFile, StandardCopyOption.REPLACE_EXISTING);

        //4.Внутри dir1 создаем файл file1
        Path dir1 = surnameDir.resolve("dir1");
        Path file1 = dir1.resolve("file1");
        Files.createFile(file1);

        //5.Внутри dir2 создаем файл file2
        Path dir2 = surnameDir.resolve("dir1/dir2");
        Path file2 = dir2.resolve("file2");
        Files.createFile(file2);

        //6.Рекурсивный обход директории <surname> с метками D: и F:
        System.out.println(" Рекурсивный обход ");
        try (Stream<Path> paths = Files.walk(surnameDir)) {
            paths.forEach(path -> {
                if (Files.isDirectory(path)) {
                    System.out.println("D: " + path.getFileName());
                } else {
                    System.out.println("F: " + path.getFileName());
                }
            });
        }

        //7.Удаляем директорию dir1 со всем содержимым (обратный порядок)
        try (Stream<Path> paths = Files.walk(dir1)) {
            paths.sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }

        System.out.println("\n После удаления dir1 ");
        try (Stream<Path> paths = Files.walk(surnameDir)) {
            paths.forEach(path -> {
                if (Files.isDirectory(path)) {
                    System.out.println("D: " + path.getFileName());
                } else {
                    System.out.println("F: " + path.getFileName());
                }
            });
        }
    }
}