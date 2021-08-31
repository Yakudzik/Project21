import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class FileReaderEx {

    private int loopCount = 0; //based on maxValue
    private List<String> shortNamesGood;
    private List<String> shortNamesGoodWithoutType;
    private List<String> shortNamesBad;
    private List<String> shortNamesBadWithoutType;
    private Collection<String> similarNames;

    public void getBothNames(String originalFilesPath, String badFilesPath) {
        shortNamesGood = new ArrayList<>();
        shortNamesBad = new ArrayList<>();
        shortNamesGoodWithoutType = new ArrayList<>();
        shortNamesBadWithoutType = new ArrayList<>();

        File goodFolder = new File(originalFilesPath);
        File[] listOriginalGoodFiles = goodFolder.listFiles();
        for (int i = 0; i < listOriginalGoodFiles.length; i++) {
            String shortName = listOriginalGoodFiles[i].toString();
            String shortNameWithType = shortName.substring(65);
            String shortNameWithoutType = shortName.substring(65, 73);
            shortNamesGood.add(shortNameWithType);
            shortNamesGoodWithoutType.add(shortNameWithoutType);
        }

        File badFolder = new File(badFilesPath);
        File[] listOriginalBadFiles = badFolder.listFiles();

        for (int i = 0; i < listOriginalBadFiles.length; i++) {
            String shortName = listOriginalBadFiles[i].toString();
            String shortNameWithTipe = shortName.substring(60);
            String shortNameWithoutTipe = shortName.substring(60, 68);
            shortNamesBad.add(shortNameWithTipe);
            shortNamesBadWithoutType.add(shortNameWithoutTipe);
        }

        getSimilarNames(shortNamesGoodWithoutType);

        System.out.println("короткие имена оригинала с типами - " + shortNamesGood.get(0) + " " + shortNamesGood.size());
        System.out.println("короткие имена оригинала без типами - " + shortNamesGoodWithoutType.get(0) + " " + shortNamesGoodWithoutType.size());
        System.out.println("неправильные имена - " + shortNamesBadWithoutType.get(0) + " " + shortNamesBadWithoutType.size());
        System.out.println("неправильные имена - " + shortNamesBad.get(0) + " " + shortNamesBad.size());
        System.out.println(shortNamesGoodWithoutType.contains(shortNamesBadWithoutType.get(1)));
    }

    private void getSimilarNames(List<String> shortNamesGoodWithoutType) {
        similarNames = new HashSet<String>(shortNamesGoodWithoutType);
        similarNames.retainAll(shortNamesBadWithoutType);

        System.out.println("похожих - " + similarNames.size());
    }

    public void compareWithBadFiles(String badFilesPath) {
        File oldFile;
        String oldName, newName;
        Boolean foundSimilar = false;


        for (int loop4badFiles = 0; loop4badFiles < shortNamesBadWithoutType.size(); loop4badFiles++) {
            oldName = (shortNamesBadWithoutType.get(loop4badFiles));

            if (shortNamesGoodWithoutType.contains(oldName)) {
                foundSimilar = true;
                int newNameIndex = shortNamesGoodWithoutType.indexOf(oldName);
                newName = shortNamesGood.get(newNameIndex);
                new File(badFilesPath+"\\"+shortNamesBad.get(loop4badFiles)).renameTo(new File(badFilesPath+"\\"+newName));
                System.out.println("Name was change " + newName);

            } else {
                System.out.println("Name " + shortNamesBadWithoutType.get(loop4badFiles) + " not found ");
            }
        }
    }
}
