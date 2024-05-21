# echo "---===== START =====---"

rm -rf target
mkdir target
jar xf lib/jcommander-1.78.jar com && jar xf lib/JColor-5.5.1.jar com && mv com target
find src/java/edu.school21.printer/ -name "*.java" | xargs javac -d target -cp .:lib/JColor-5.5.1.jar:lib/jcommander-1.78.jar | cp -r src/resources target/
jar cfm target/images-to-chars-printer.jar src/manifest.txt -C target/ .
java -jar target/images-to-chars-printer.jar --white=YELLOW --black=CYAN

# echo "---===== FINISH =====---"


# "RED"
# "GREEN"
# "YELLOW"
# "BLUE"
# "MAGENTA"
# "CYAN"