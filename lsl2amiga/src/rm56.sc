;;; Sierra Script 1.0 - (do not remove this comment)
(script# 56)
(include game.sh)
(use Main)
(use Intrface)
(use Wander)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm56 0
)

(local
	lineIndex
	numLines
	lineY
	lineXEast
	lineXWest
)
(instance rm56 of Room
	(properties
		picture 56
		horizon 1
	)
	
	(method (init)
		(Load VIEW 522)
		(super init:)
		(= lineY 166)
		(= lineXEast 109)
		(= lineXWest 136)
		(cond 
			((> machineSpeed 60)
				(= numLines 4)
			)
			((> machineSpeed 40)
				(= numLines 3)
			)
			((> machineSpeed 20)
				(= numLines 2)
			)
			(else
				(= numLines 1)
			)
		)
		(for ((= lineIndex 1)) (<= lineIndex numLines) ((++ lineIndex))
			((Actor new:) setScript: (eastLineScript new:))
			((Actor new:) setScript: (westLineScript new:))
		)
		(aMind
			setLoop: 1
			setPri: 14
			setStep: 4 4
			illegalBits: 16
			init:
		)
		(HandsOff)
		(= currentStatus egoINTERMINAL)
		(self setRegions: AIRPORT setScript: rm56Script)
	)
	
	(method (dispose)
		(DisposeScript WANDER)
		(super dispose:)
	)
)

(instance rm56Script of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 10)
			)
			(1
				(Print 56 0)
				(= seconds 10)
			)
			(2
				(Print 56 1)
				(= seconds 3)
			)
			(3
				(aMind posn: 175 41 setMotion: Wander 99)
				(= seconds 10)
			)
			(4
				(aMind
					ignoreControl: cRED
					setMotion: MoveTo (aMind x?) -20
				)
				(Print 56 2 #draw)
				(= seconds 10)
			)
			(5
				(Print 56 3)
				(curRoom newRoom: 57)
			)
		)
	)
)

(instance eastLineScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					view: 522
					setLoop: 0
					setCel: 0
					setPri: 5
					setStep: 1 1
					illegalBits: 0
					ignoreHorizon:
					ignoreActors:
					posn:
						(- 204 (* lineIndex (/ lineXEast numLines)))
						(+ 2 (* lineIndex (/ lineY numLines)))
					init:
				)
				(self changeState: 1)
			)
			(1
				(client setMotion: MoveTo 204 2 self)
			)
			(2
				(client posn: 95 168)
				(self changeState: 1)
			)
		)
	)
)

(instance westLineScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					view: 522
					setLoop: 0
					setCel: 1
					setPri: 5
					setStep: 1 1
					illegalBits: 0
					ignoreHorizon:
					ignoreActors:
					posn:
						(+ -58 (* lineIndex (/ lineXWest numLines)))
						(+ 2 (* lineIndex (/ lineY numLines)))
					init:
				)
				(self changeState: 1)
			)
			(1
				(client setMotion: MoveTo -58 2 self)
			)
			(2
				(client posn: 78 168)
				(self changeState: 1)
			)
		)
	)
)

(instance aMind of Actor
	(properties
		y 1041
		x 175
		view 522
		signal ignrAct
	)
)
