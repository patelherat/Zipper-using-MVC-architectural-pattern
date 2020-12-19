# Zipper using MVC architectural pattern
Command Line Interface(CLI) for a utility that allows users to compress and uncompress archives using ZIP compression.

<br />

**Commands** <br />
• help - displays a list of available commands. <br />
• add <file path> - add file to the list of files to be archived when the archive command is executed. <br />
• archive <file path> - creates the archive in the specified file and adds each added file as a separate entry. <br />
• clear - clears the list of files to archive. <br />
• files - prints a list of the files to be archived. <br />
• list <file path> - displays a list of files in the specified directory. <br />
• quit - quits the Zipper Utility. <br />
• unarchive <source path> <destination path> - extracts the archive at the source path and saves the entries into the directory at the destination path. <br />
  
enter command >> list data <br />
Listing files in 'data'... <br />
D:\ClassMaterials\605Materials\HW\Files\data\books.png <br />
D:\ClassMaterials\605Materials\HW\Files\data\buttercup.jpg <br />
D:\ClassMaterials\605Materials\HW\Files\data\cutie.jpg <br />
D:\ClassMaterials\605Materials\HW\Files\data\tacos.jpg <br />
D:\ClassMaterials\605Materials\HW\Files\data\words.txt <br />
enter command >> add data/books.png <br />
data/books.png added to list of files to be archived. <br />
enter command >> add data/buttercup.jpg <br />
data/buttercup.jpg added to list of files to be archived. <br />
enter command >> files <br />
Files to be archived: <br />
D:\ClassMaterials\605Materials\HW\Files\data\books.png <br />
D:\ClassMaterials\605Materials\HW\Files\data\buttercup.jpg <br />
enter command >> archive zipper.zip <br />
Archive successfully created: zipper.zip(6387114 bytes) <br />
enter command >> unarchive zipper.zip temp <br />
Extracting the archive 'zipper.zip' and saving entries in directory 'temp'... <br />
D:\ClassMaterials\605Materials\HW\Files\temp\books.png <br />
D:\ClassMaterials\605Materials\HW\Files\temp\buttercup.jpg <br />
enter command >> list temp <br />
Listing files in 'temp'... <br />
D:\ClassMaterials\605Materials\HW\Files\temp\books.png <br />
D:\ClassMaterials\605Materials\HW\Files\temp\buttercup.jpg <br />
enter command >> quit <br />
Quitting... <br />

See the output folder in the provided files for additional examples.
