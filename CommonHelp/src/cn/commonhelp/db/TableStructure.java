package cn.commonhelp.db;

public class TableStructure
{
	// Column Name Data Type Primary Key Auto Increment Not Null Default Value
	String ColumnName;
	Boolean isPk;
	Boolean isAutoIncrement;
	Boolean ifNullEnable;
	String defaultValue;
}

enum ColType
{
	// NULL、INTEGER、REAL（浮点数字）、TEXT(字符串文本)和BLOB(二进制对象)数据类型
	INTEGER, REAL, TEXT, BLOB, NULL
}
