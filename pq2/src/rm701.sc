;;; Sierra Script 1.0 - (do not remove this comment)
(script# 701)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use Menu)
(use System)

(public
	rm701 0
)

(local
	[str 40]
)
(procedure (ClearEvents &tmp evt)
	(while ((= evt (Event new:)) type?)
		(evt dispose:)
	)
	(evt dispose:)
)

(procedure (localproc_0031 &tmp temp0 temp1)
	(= temp0 0)
	(while (= temp1 (StrAt @str temp0))
		(if (and (>= temp1 97) (<= temp1 122))
			(StrAt @str temp0 (- temp1 32))
		)
		(++ temp0)
	)
)

;;;(instance event of Event)

(instance rm701 of Room
	(properties
		picture 701
	)
	
	(method (init)
		;skip copy proection prompt
		(TheMenuBar draw:)
		(StatusLine enable:)
		(theGame restart:)
		(super init:)
	)
	
	(method (doit &tmp theCel temp1)
		(= theCel (& (GetTime TRUE) 7))
		(= str 0)
		(ClearEvents)
		(Print 701 0 #icon 701 0 theCel #edit @str 20)
		(= quit TRUE)
		(localproc_0031)
		(switch theCel
			(0
				(if (not (StrCmp @str {GRANANDEZ})) (= quit 0))
			)
			(1
				(if (not (StrCmp @str {SIMMS})) (= quit 0))
			)
			(2
				(if (not (StrCmp @str {TASELLI})) (= quit 0))
			)
			(3
				(if (not (StrCmp @str {COLBY})) (= quit 0))
			)
			(4
				(if (not (StrCmp @str {BAINS})) (= quit 0))
			)
			(5
				(if (not (StrCmp @str {SNIDER})) (= quit 0))
			)
			(6
				(if (not (StrCmp @str {JONES})) (= quit 0))
			)
			(else 
				(if (not (StrCmp @str {DICKEY})) (= quit 0))
			)
		)
		(if quit
			(Print 701 1)
		else
			(TheMenuBar draw:)
			(StatusLine enable:)
			(theGame restart:)
		)
	)
)
