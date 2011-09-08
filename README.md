# Peachy 

Peachy is a general-purpose bot framework written in [Java][1]. It is a powerful, full-featured framework designed to facilitate the creation of [internet robots, or bots][2]. 

Peachy comes packed with many pre-existing libraries that people need to write effective code with, including JSON, JSoup, Apache Commons, JCommander, SnakeYAML, Guava, JUnit. It also included many tools that many developers will find useful, such as Finder (OOP based file search tool), EasyArrayList/EasyHashMap, EventDispatcher, Timer, and more. 

Peachy is heavily influenced by pre-existing frameworks, such as [Symfony][3] and [Pillar][4]. It even contains Java ports of various useful Symfony tools, such as Finder and EventDispatcher, neither of which have a very good alternative in Java. That is, until now. 

It it currently under active development, and is likely to break at any time. 

# Usage 

Usage is simple. Just put Peachy.jar in your [class path][5], and it's installed! 

To write a Peachy script, extend the PeachyCode class from your main script, call the `startMain()` method from the main method, and override the `configure()` method: 

<pre>
public class ExampleScript extends PeachyCore {

    public static void main(String[] args) {
        startMain(ExampleScript.class, args);
    }
    
    @Override
    public void configure() {
        this.setConfig("wiki", ConfigType.YAML, "/path/to/config.yml");
    }
}
</pre>

# License 

Peachy is licensed under the [MIT License][6].

 [1]: http://en.wikipedia.org/wiki/Java_(programming_language)
 [2]: http://en.wikipedia.org/wiki/Internet_bot
 [3]: http://symfony.com
 [4]: http://sourceforge.net/projects/pillar/
 [5]: http://download.oracle.com/javase/1.3/docs/tooldocs/win32/classpath.html
 [6]: http://www.opensource.org/licenses/mit-license.php