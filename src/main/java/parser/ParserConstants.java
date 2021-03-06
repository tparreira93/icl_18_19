/* Generated By:JavaCC: Do not edit this line. ParserConstants.java */
package parser;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface ParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int QUOTED = 9;
  /** RegularExpression Id. */
  int Num = 10;
  /** RegularExpression Id. */
  int DOT = 11;
  /** RegularExpression Id. */
  int PLUS = 12;
  /** RegularExpression Id. */
  int RECORD_BEGIN = 13;
  /** RegularExpression Id. */
  int RECORD_END = 14;
  /** RegularExpression Id. */
  int MINUS = 15;
  /** RegularExpression Id. */
  int TIMES = 16;
  /** RegularExpression Id. */
  int DIV = 17;
  /** RegularExpression Id. */
  int LPAR = 18;
  /** RegularExpression Id. */
  int RPAR = 19;
  /** RegularExpression Id. */
  int EL = 20;
  /** RegularExpression Id. */
  int let = 21;
  /** RegularExpression Id. */
  int PRINTLN = 22;
  /** RegularExpression Id. */
  int LAMBDA = 23;
  /** RegularExpression Id. */
  int BINDING = 24;
  /** RegularExpression Id. */
  int COLON = 25;
  /** RegularExpression Id. */
  int ASSIGNMENT = 26;
  /** RegularExpression Id. */
  int IN = 27;
  /** RegularExpression Id. */
  int END = 28;
  /** RegularExpression Id. */
  int function = 29;
  /** RegularExpression Id. */
  int COMMA = 30;
  /** RegularExpression Id. */
  int REFERENCE = 31;
  /** RegularExpression Id. */
  int DEREFERENCE = 32;
  /** RegularExpression Id. */
  int SEQUENCE = 33;
  /** RegularExpression Id. */
  int WHILE = 34;
  /** RegularExpression Id. */
  int DO = 35;
  /** RegularExpression Id. */
  int BOOLVALUE = 36;
  /** RegularExpression Id. */
  int RECORD = 37;
  /** RegularExpression Id. */
  int INT = 38;
  /** RegularExpression Id. */
  int BOOL = 39;
  /** RegularExpression Id. */
  int REF = 40;
  /** RegularExpression Id. */
  int STRING = 41;
  /** RegularExpression Id. */
  int EQUAL = 42;
  /** RegularExpression Id. */
  int NOT_EQUAL = 43;
  /** RegularExpression Id. */
  int GREATER = 44;
  /** RegularExpression Id. */
  int GREATEROREQUAL = 45;
  /** RegularExpression Id. */
  int SMALLER = 46;
  /** RegularExpression Id. */
  int SMALLEROREQUAL = 47;
  /** RegularExpression Id. */
  int AND = 48;
  /** RegularExpression Id. */
  int OR = 49;
  /** RegularExpression Id. */
  int NOT = 50;
  /** RegularExpression Id. */
  int IF = 51;
  /** RegularExpression Id. */
  int THEN = 52;
  /** RegularExpression Id. */
  int ELSE = 53;
  /** RegularExpression Id. */
  int Id = 54;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int ML_COMMENT_STATE = 1;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\r\"",
    "\"\\n\"",
    "<token of kind 5>",
    "\"/*\"",
    "\"*/\"",
    "<token of kind 8>",
    "<QUOTED>",
    "<Num>",
    "\".\"",
    "\"+\"",
    "\"[\"",
    "\"]\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "\"(\"",
    "\")\"",
    "\";;\"",
    "\"let\"",
    "\"println\"",
    "\"->\"",
    "\"=\"",
    "\":\"",
    "<ASSIGNMENT>",
    "\"in\"",
    "\"end\"",
    "\"fun\"",
    "\",\"",
    "\"new\"",
    "\"!\"",
    "\";\"",
    "\"while\"",
    "\"do\"",
    "<BOOLVALUE>",
    "\"record\"",
    "\"int\"",
    "\"bool\"",
    "\"ref\"",
    "\"string\"",
    "\"==\"",
    "\"<>\"",
    "\">\"",
    "\">=\"",
    "\"<\"",
    "\"<=\"",
    "\"&&\"",
    "\"||\"",
    "\"~\"",
    "\"if\"",
    "\"then\"",
    "\"else\"",
    "<Id>",
  };

}
