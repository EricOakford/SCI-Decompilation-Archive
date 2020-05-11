;;; Sierra Script 1.0 - (do not remove this comment)
(script# 56)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm56 0
)

(local
	wanderingMind
	local1
	local2
	local3
	local4
	local5
)
(instance rm56 of Room
	(properties
		picture 56
		horizon 1
	)
	
	(method (init)
		(Load VIEW 522)`
		(super init:)
		(= local3 166)
		(= local4 109)
		(= local5 136)
		(cond 
			((> howFast 60) (= local2 4))
			((> howFast 40) (= local2 3))
			((> howFast 20) (= local2 2))
			(else (= local2 1))
		)
		(= local1 1)
		(while (<= local1 local2)
			((Actor new:) setScript: (eastLineScript new:))
			((Actor new:) setScript: (westLineScript new:))
			(++ local1)
		)
		((= wanderingMind (Actor new:))
			view: 522
			setLoop: 1
			setPri: 14
			setStep: 4 4
			illegalBits: 16
			posn: 175 1041
			ignoreActors:
			init:
		)
		(HandsOff)
		(= currentStatus egoNormal)
		(self setRegions: AIRPORT setScript: rm56Script)
	)
)

(instance rm56Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 10))
			(1 (Print 56 0) (= seconds 10))
			(2 (Print 56 1) (= seconds 3))
			(3
				(wanderingMind posn: 175 41 setMotion: Wander 99)
				(= seconds 10)
			)
			(4
				(wanderingMind
					ignoreControl: 16
					setMotion: MoveTo (wanderingMind x?) -20
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
	(properties)
	
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
						(- 204 (* local1 (/ local4 local2)))
						(+ 2 (* local1 (/ local3 local2)))
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
	(properties)
	
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
						(+ -58 (* local1 (/ local5 local2)))
						(+ 2 (* local1 (/ local3 local2)))
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
