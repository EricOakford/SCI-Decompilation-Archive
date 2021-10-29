;;; Sierra Script 1.0 - (do not remove this comment)
(script# 100)
(include sci.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm100 0
)

(local
	chopper
	printObj
	oldSpeed
)
(instance chopperMusic of Sound
	(properties
		number 4
	)
)

(instance rm100 of Room
	(properties
		picture 100
		style $0008
	)
	
	(method (init)
		(HandsOff)
		(Load rsVIEW 273)
		(Load rsVIEW 275)
		(Load rsSOUND 4)
		((View new:)
			view: 273
			posn: 113 116
			loop: 0
			cel: 0
			setPri: 1
			init:
			addToPic:
		)
		((View new:)
			view: 273
			posn: 152 152
			loop: 0
			cel: 1
			setPri: 1
			init:
			addToPic:
		)
		((View new:)
			view: 273
			posn: 68 113
			loop: 0
			cel: 3
			setPri: 0
			init:
			addToPic:
		)
		((View new:)
			view: 273
			posn: 189 112
			loop: 0
			cel: 2
			setPri: 0
			init:
			addToPic:
		)
		((View new:)
			view: 273
			posn: 261 105
			loop: 3
			cel: 0
			setPri: 0
			init:
			addToPic:
		)
		((View new:)
			view: 273
			posn: 19 146
			loop: 2
			cel: 0
			setPri: 0
			init:
			addToPic:
		)
		((View new:)
			view: 273
			posn: 304 184
			loop: 2
			cel: 1
			setPri: 0
			init:
			addToPic:
		)
		((View new:)
			view: 273
			posn: 85 158
			loop: 1
			cel: 0
			setPri: 0
			init:
			addToPic:
		)
		((View new:)
			view: 273
			posn: 39 191
			loop: 4
			cel: 0
			setPri: 0
			init:
			addToPic:
		)
		(super init:)
		(= oldSpeed speed)
		(if (< speed 4) (theGame setSpeed: 4))
		(self setScript: rm100Script)
	)
)

(instance rm100Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(chopperMusic play:)
				(= seconds 1)
			)
			(1
				((= chopper (Actor new:))
					view: 275
					setLoop: 9
					cel: 7
					posn: 210 77
					cycleSpeed: 2
					setCycle: BegLoop self
					setMotion: MoveTo 216 74
					init:
					ignoreActors:
					illegalBits: 0
				)
			)
			(2
				(chopper
					setLoop: 7
					setCycle: BegLoop
					setMotion: MoveTo 218 74 self
				)
			)
			(3
				(chopper
					setLoop: 5
					setStep: 8 4
					setCycle: Forward
					setMotion: MoveTo 330 100 self
				)
			)
			(4
				(= printObj (Print 100 0 #mode 1 #at -1 140 #dispose))
				(self cue:)
			)
			(5
				(chopper
					setLoop: 4
					setPri: 0
					posn: 331 80
					setStep: 10
					cycleSpeed: 0
					setMotion: MoveTo -11 110 self
				)
			)
			(6
				(chopper
					setLoop: 5
					setStep: 8
					posn: -11 136
					setPri: 2
					setMotion: MoveTo 144 101 self
				)
			)
			(7
				(chopper
					setLoop: 3
					setCycle: Forward
					ignoreHorizon: 1
					setMotion: MoveTo 255 -11 self
				)
			)
			(8
				(chopper
					posn: 131 -11
					setLoop: 8
					setStep: 1 1
					setCycle: Forward
					setMotion: MoveTo 131 28 self
				)
			)
			(9
				(cls)
				(chopper stopUpd:)
				(Print 100 1 #at -1 140 #mode 1 #dispose)
				(= seconds 5)
			)
			(10
				(if (!= (chopperMusic prevSignal?) -1)
					(-- state)
					(= cycles 2)
				else
					(cls)
					(if (< oldSpeed 4) (theGame setSpeed: oldSpeed))
					(curRoom newRoom: 101)
				)
			)
		)
	)
)
