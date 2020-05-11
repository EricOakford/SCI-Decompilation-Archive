;;; Sierra Script 1.0 - (do not remove this comment)
(script# 99)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Menu)

(public
	rm99 0
)

(local
	endTime
)
(instance rm99 of Room
	(properties
		picture 178
		style IRISIN
	)
	
	(method (init)
		(HandsOff)
		(super init:)
		(ego
			view: 253
			setLoop: 2
			posn: 20 100
			setStep: 1 1
			setMotion: MoveTo 300 100
			setCycle: Forward
			init:
		)
		(theGame setSpeed: 0)
	)
	
	(method (doit)
		(super doit:)
		(if (== (++ howFast) 1) (= endTime (+ 60 (GetTime))))
		(if (< endTime (GetTime))
			(if speedTestQA
				(Print (Format @str 99 0 howFast))
				(Print 99 1 #at -1 152)
			)
			(theGame setSpeed: 6)
			(TheMenuBar draw:)
			(StatusLine enable:)
			(curRoom newRoom: 23)
		)
	)
)
