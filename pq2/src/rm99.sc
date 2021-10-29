;;; Sierra Script 1.0 - (do not remove this comment)
(script# 99)
(include sci.sh)
(use Main)
(use Motion)
(use Game)
(use Menu)
(use Intrface)

(public
	rm99 0
)

(local
	doneTIme
)
(instance rm99 of Room
	(properties
		picture 450
		style $0006
	)
	
	(method (init)
		(HandsOff)
		(super init:)
		(ego
			view: 450
			setLoop: 0
			posn: 20 100
			setStep: 1 1
			setMotion: MoveTo 300 100
			setCycle: Forward
			init:
		)
		(= howFast 0)
		(theGame setSpeed: 0)
	)
	
	(method (doit)
		(super doit:)
		(if (== (++ howFast) 1)
			(= doneTIme (+ 60 (GetTime)))
		)
		(if (< doneTIme (GetTime))
			(if (Btst 167) ;fGameIsRestarting
				(theGame setSpeed: 6)
				(MenuBar draw:)
				(StatusLine enable:)
				(NormalEgo)
				(curRoom newRoom: 1);1
			else
				(curRoom newRoom: 200);200
			)
		)
	)
)
