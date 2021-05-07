;;; Sierra Script 1.0 - (do not remove this comment)
(script# 99)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Actor)

(public
	rm99 0
)

(local
	doneTime
)
(instance Speedy of Actor)

(instance rm99 of Room
	(properties
		picture 992
		style IRISIN
	)
	
	(method (init)
		(HandsOff)
		(super init:)
		(= howFast -1)
		(if (!= possibleScore 1)
			(Load VIEW 899)
			(LoadMany 143 409)
		)
		(Speedy
			view: 991
			posn: 20 99
			setStep: 1 1
			setMotion: MoveTo 300 100
			setCycle: Forward
			init:
		)
		(theGame setSpeed: 0)
	)
	
	(method (doit)
		(super doit:)
		(if (== howFast -1)
			(if (== (++ machineSpeed) 1)
				(= doneTime (+ 60 (GetTime)))
			)
			(if (< doneTime (GetTime))
				(cond 
					((<= machineSpeed 40)
						(= howFast slow)
					)
					((<= machineSpeed 70)
						(= howFast medium)
					)
					(else
						(= howFast fast)
					)
				)
				(theGame setSpeed: 6)
				(HandsOn)
				(if
					(and
						(not possibleScore)
						(not (StrCmp {zz} (+ version 7)))
					)
					(self setScript: (ScriptID 409 0))
				else
					(curRoom newRoom:
						(switch possibleScore
							(1 44)
							(else  414)
						)
					)
				)
			)
		)
	)
)
