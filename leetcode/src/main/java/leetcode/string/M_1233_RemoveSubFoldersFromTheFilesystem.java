package leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_1233_RemoveSubFoldersFromTheFilesystem {
    public static void main(String[] args) {
        System.out.println(new M_1233_RemoveSubFoldersFromTheFilesystem().removeSubfolders(new String[]{"/a/b/c", "/a/b/ca", "/a/b/d"}));
        System.out.println(new M_1233_RemoveSubFoldersFromTheFilesystem().removeSubfolders(new String[]{"/a", "/a/b", "/c/d", "/c/d/e", "/c/f"}));
    }

    public List<String> removeSubfolders(String[] folders) {
        Arrays.sort(folders);

        List<String> result = new ArrayList<>();
        result.add(folders[0]);

        for (int i = 1; i < folders.length; ++i) {
            String cur = folders[i];
            String prev = result.get(result.size() - 1);
            if (!cur.startsWith(prev + "/")) result.add(cur);
        }

        return result;
    }
}
