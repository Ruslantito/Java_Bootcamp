mkdir target

find src/java/edu.school21.printer/ -name "*.java" | xargs javac -d target

java -classpath target edu.school21.printer.app.Program ../it.bmp . o

