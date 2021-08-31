fun main(args: Array<String>) {
    var fileReaderEx = FileReaderEx()
    fileReaderEx.getBothNames("C:\\Users\\Yakud\\Desktop\\Ta7Tewij\\test files\\source\\original files","C:\\Users\\Yakud\\Desktop\\Ta7Tewij\\test files\\source\\bad files")
    fileReaderEx.compareWithBadFiles("C:\\Users\\Yakud\\Desktop\\Ta7Tewij\\test files\\source\\bad files")
}