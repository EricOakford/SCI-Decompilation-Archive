;;; Sierra Script 1.0 - (do not remove this comment)
(script# ABOUT) ;811
(include game.sh)
(use Main)
(use Sq4Dialog)
(use System)

(public
	aboutCode 0
)

(instance aboutCode of Code
	(properties)
	
	(method (doit &tmp oldCur [str 600] [str2 600])
		(= oldCur (theGame setCursor: 999 1))
		(Message MsgGet ABOUT 97 0 1 1 @str)
		(Format @str2 @str version)
		(SQ4Print @str2)
		(Message MsgGet ABOUT 97 0 2 1 @str)
		(SQ4Print @str)
		(Message MsgGet ABOUT 97 0 3 1 @str)
		(SQ4Print @str)
		(Message MsgGet ABOUT 97 0 4 1 @str)
		(SQ4Print @str)
		(theGame setCursor: oldCur TRUE)
	)
)
