;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64019)
(include sci.sh)
(use Main)
(use GenDialog)
(use String)
(use System)

(public
	DeathDialog 0
)

(class DeathDialog of Obj
	(properties
		scratch 0
	)
	
	(method (show param1 param2 param3 theTheCurRoomNum &tmp theCurRoomNum temp1 temp2 temp3 temp4)
		(if (< argc 3) (MonoOut LOOKUP_ERROR) (return 1))
		(if (< argc 4)
			(= theCurRoomNum curRoomNum)
		else
			(= theCurRoomNum theTheCurRoomNum)
		)
		(= temp1
			(MakeMessageText param1 param2 param3 1 theCurRoomNum)
		)
		(= temp4 (Str with: LOOKUP_ERROR))
		(= temp2
			(MakeMessageText param1 param2 param3 2 theCurRoomNum)
		)
		(= temp3 (Str with: LOOKUP_ERROR))
		(temp1 cat: temp4)
		(temp1 cat: temp2)
		(if temp1
			(TextDialog temp1 temp3)
			(temp1 dispose:)
			(temp4 dispose:)
			(temp2 dispose:)
			(temp3 dispose:)
		)
		(return 1)
	)
)
