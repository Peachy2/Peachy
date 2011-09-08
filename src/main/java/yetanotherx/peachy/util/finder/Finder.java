package yetanotherx.peachy.util.finder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import yetanotherx.peachy.util.UsefulMethods;

/*
 * 
 * PEACHYTODO
 */
public class Finder {

    protected FinderType type = FinderType.FILE;
    protected ArrayList<String> names = new ArrayList<String>();
    protected ArrayList<String> prunes = new ArrayList<String>();
    protected ArrayList<String> discards = new ArrayList<String>();
    protected int mindepth = 0;
    protected ArrayList<String> sizes = new ArrayList<String>();
    protected int maxdepth = 1000000;
    protected boolean relative = false;
    protected boolean follow_link = false;
    protected boolean sort = false;
    protected boolean ignore_vc = true;

    public Finder maxdepth(int maxdepth) {
        this.maxdepth = maxdepth;
        return this;
    }

    public Finder mindepth(int mindepth) {
        this.mindepth = mindepth;
        return this;
    }

    public FinderType getType() {
        return type;
    }

    public static Finder type(FinderType type) {
        return new Finder().setType(type);
    }

    public Finder setType(FinderType type) {
        this.type = type;
        return this;
    }

    protected String glob_to_regex(String glob) {
        if (glob.matches("^(!)?([^a-zA-Z0-9\\\\]).+?\\2[ims]?$")) {
            return glob;
        }
        return GlobToRegex.parse(glob);
    }

    @SuppressWarnings("unchecked")
    public Finder name(String... names) {
        List<String> namesList = Arrays.asList(names);
        this.names = UsefulMethods.arrayUniqueMerge(this.names, new ArrayList<String>(namesList));
        return this;
    }

    public Finder notName(String... names) {
        for (String name : names) {
            if (this.names.contains(name)) {
                this.names.remove(name);
            }
        }
        return this;
    }
    
    public Finder size(String ... sizes) {
        for( String size : sizes ) {
            //this.sizes = UsefulMethods.arrayUniqueMerge(this.sizes, new NumberCompare(size));
        }
        return this;
    }
}
