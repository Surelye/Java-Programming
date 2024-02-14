package task3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class B3 {

    private final File workingDirectory;
    private final String targetString;

    class FileIsNotADirectoryException extends RuntimeException {
        public FileIsNotADirectoryException(String errorMessage) {
            super(errorMessage);
        }
    }

    public B3(String directoryName, String targetString) {
        this.workingDirectory = new File(directoryName);
        if (!workingDirectory.isDirectory()) {
            throw new FileIsNotADirectoryException("Путь \"%s\" не указывает на директорию"
                    .formatted(directoryName));
        }
        this.targetString = targetString;
    }

    private boolean isCorrectName(File entity) {
        return entity.getName()
                .toLowerCase()
                .contains(targetString);
    }

    private void
    zipEachEntity(ZipOutputStream zout, File entity, boolean archiveForSure, String properName)  {
        System.out.printf("> Архивирование директории '%s'%n", entity.getName());
        for (File subentity : Objects.requireNonNull(entity.listFiles())) {
            if (subentity.isDirectory()) {
                if (isCorrectName(subentity)) {
                    zipEachEntity(zout, subentity, true, "%s%s\\"
                            .formatted(properName, subentity.getName()));
                    continue;
                }
            }
            if (!(isCorrectName(subentity) || archiveForSure)) {
                continue;
            }
            System.out.printf("> Архивирование файла '%s'%n", subentity.getName());

            try (FileInputStream fis = new FileInputStream(subentity)) {
                zout.putNextEntry(new ZipEntry(properName + subentity.getName()));
                byte[] buffer = new byte[256];
                int size;
                while ((size = fis.read()) > 0) {
                    zout.write(buffer, 0, size);
                }
                zout.closeEntry();
            } catch (IOException e) {
                System.out.printf("[ERROR] Добавить сущность %s в архив не удалось%n%n",
                        subentity.getName());
            }
        }
    }

    public boolean zipEntitiesWithNameContainingTargetString() {
        try (FileOutputStream fout = new FileOutputStream("%s.7z"
                .formatted(workingDirectory.getName()));
             ZipOutputStream zout = new ZipOutputStream(fout)) {
            zipEachEntity(zout, workingDirectory, false, "");
        } catch (IOException e) {
            System.out.printf("[ERROR] Создать архив %s.7z не удалось%n",
                    workingDirectory.getName());
            return false;
        }

        return true;
    }
}
