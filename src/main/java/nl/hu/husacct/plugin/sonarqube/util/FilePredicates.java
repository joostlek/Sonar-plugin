package nl.hu.husacct.plugin.sonarqube.util;


import org.sonar.api.batch.fs.FilePredicate;
import org.sonar.api.batch.fs.InputFile;

public class FilePredicates {

    private FilePredicates() {}

    public static class XmlPredicate implements FilePredicate {

        @Override
        public boolean apply(InputFile inputFile) {
            if(inputFile.file().getName().endsWith(".xml")) {
                return true;
            }
            return false;
        }
    }

    public static class fileWithPath implements  FilePredicate {
        private String absolutePath;
        private String suffix;
        public fileWithPath(String absolutePath, String suffix) { this.absolutePath = absolutePath; this.suffix = suffix;}

        @Override
        public boolean apply(InputFile inputFile) {
            String inputFileAbsolutePath = FileFormatter.formatFilePath(
                    inputFile.absolutePath().substring(0, inputFile.absolutePath().lastIndexOf('.')));
            if(inputFileAbsolutePath.endsWith(absolutePath + suffix)) {
                return true;
            }
            return false;
        }
    }

    public static class FileWithName implements FilePredicate {
        private String fileName;
        public FileWithName(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public boolean apply(InputFile inputFile) {
            if(inputFile.file().getName().equals(fileName)) {
                return true;
            }
            return false;
        }
    }
}
