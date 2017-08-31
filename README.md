# GP-Classification


## Compiling Java files using Eclipse IDE

1. Download this repository as ZIP
2. Create new `Java Project` in `Eclipse`
3. Right click on your `Java Project` --> `Import`
4. Choose `General` --> `Archive File`
5. Put directory where you downloaded ZIP in `From archive file`
6. Put `ProjectName/src` in `Into folder`
7. Click `Finish`

### Linking JGAP library dependencies

8. Download JGAP from <a href='https://sourceforge.net/projects/jgap/files/jgap/JGAP%203.6.3/jgap_3.6.3_full.zip/download'>here</a>
9. Extract the `jgap.jar` from the `{root}` of the zip
10. Extract the `commons-lang-2.1.jar` and `log4j.jar` from `{root/lib}` of the zip
11. Right click on your `Java Project` --> `Build Path` --> `Add External Archives`
12. Find the `jgap.jar`, `commons-lang-2.1.jar` and `log4j.jar`
13. Click `Open`

## Running the program

1. Right click on your `Java Project` --> `Run As` --> `Java Application`
2. Program will ask for directory of where the text files are
3. Choose `data` directory
4. Program will look for `cancer-test.txt` and `cancer-training.txt` inside that chosen folder

## About the Data File

The data set was obtained from the UCI machine learning repository <a href='http://mlearn.ics.uci.edu/MLRepository.html'>here</a>. See `data/readme.txt` for more information
