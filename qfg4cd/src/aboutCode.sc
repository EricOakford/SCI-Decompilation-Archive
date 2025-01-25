;;; Sierra Script 1.0 - (do not remove this comment)
(script# GLORY_ABOUT)
(include game.sh) (include "19.shm")
(use Main)
(use String)
(use Print)
(use System)

(public
	aboutCode 0
)

(local
	oldCur
)
(procedure (AboutPrint theNoun theSeq &tmp temp0 str msgExists)
	(if (= msgExists (Message MsgSize GLORY_ABOUT theNoun 0 0 theSeq))
		(= str (String new: msgExists))
		(Message MsgGet GLORY_ABOUT theNoun 0 0 theSeq (str data?))
		(Print
			font: SYSFONT
			largeAlp: 0
			mode: teJustCenter
			addText: (str data?)
			init:
		)
		(str dispose:)
	)
)

(instance aboutCode of Code
	(method (doit &tmp savedCur [temp1 2])
		((= savedCur (= oldCur (theIconBar getCursor:)))
			view: 999
		)
		(theGame setCursor: savedCur)
		(AboutPrint N_CREDITS 1)
		(AboutPrint N_CREDITS 2)
		(AboutPrint N_CREDITS 3)
		(AboutPrint N_CREDITS 4)
		(AboutPrint N_CREDITS 5)
		(AboutPrint N_CREDITS 6)
		(AboutPrint N_CREDITS 7)
		(AboutPrint N_CREDITS 8)
		(AboutPrint N_CREDITS 9)
		(AboutPrint N_CREDITS 10)
		(AboutPrint N_CREDITS 11)
		(AboutPrint N_CREDITS 12)
		(AboutPrint N_CREDITS 13)
		(AboutPrint N_CREDITS 14)
		(AboutPrint N_ABOUT 1)
		(AboutPrint N_ABOUT 2)
		(AboutPrint N_ABOUT 3)
		(AboutPrint N_ABOUT 4)
		(AboutPrint N_ABOUT 5)
		(AboutPrint N_ABOUT 6)
		(AboutPrint N_ABOUT 7)
		(AboutPrint N_ABOUT 8)
		(AboutPrint N_ABOUT 9)
		(AboutPrint N_ABOUT 10)
		(AboutPrint N_ABOUT 11)
		(AboutPrint N_RETURN 1)
		(self dispose:)
	)
	
	(method (dispose)
		(theGame setCursor: oldCur)
		(DisposeScript GLORY_ABOUT)
	)
)
