;;; Sierra Script 1.0 - (do not remove this comment)
(script# 44)
(include sci.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm44 0
)

(local
	planeX
	airplane
	newView
	newView_2
	local4
	fire1
	fire2
	fire3
)
(instance explosion of Sound
	(properties
		number 30
	)
)

(instance rm44 of Room
	(properties
		picture 88
		style $0000
	)
	
	(method (init)
		(Load rsVIEW 259)
		(Load rsSOUND 30)
		(super init:)
		(HandsOff)
		(= planeX (Random 55 274))
		((View new:)
			view: 259
			loop: 4
			cel: 0
			posn: 123 150
			setPri: 11
			addToPic:
		)
		((View new:)
			view: 259
			loop: 4
			cel: 0
			posn: 101 146
			setPri: 10
			addToPic:
		)
		((View new:)
			view: 259
			loop: 5
			cel: 0
			posn: 117 118
			setPri: 8
			addToPic:
		)
		((View new:)
			view: 259
			loop: 5
			cel: 0
			posn: 319 127
			setPri: 9
			addToPic:
		)
		((View new:)
			view: 259
			loop: 6
			cel: 0
			posn: 218 181
			setPri: 14
			addToPic:
		)
		((View new:)
			view: 259
			loop: 4
			cel: 0
			posn: 200 145
			setPri: 10
			addToPic:
		)
		((View new:)
			view: 259
			loop: 4
			cel: 0
			posn: 18 137
			setPri: 10
			addToPic:
		)
		((View new:)
			view: 259
			loop: 4
			cel: 0
			posn: 241 125
			setPri: 8
			addToPic:
		)
		((View new:)
			view: 259
			loop: 5
			cel: 0
			posn: 317 120
			setPri: 8
			addToPic:
		)
		((View new:)
			view: 259
			loop: 6
			cel: 0
			posn: 80 189
			setPri: 14
			addToPic:
		)
		((= airplane (Actor new:))
			view: 259
			posn: -15 82
			setStep: 2 1
			setLoop: 1
			setCel: 1
			setPri: 15
			init:
			ignoreActors:
			illegalBits: 0
			setCycle: 0
		)
		(self setScript: blowUp)
	)
	
	(method (doit)
		(if
			(and
				(== (mod (+ (airplane x?) 43) 22) 0)
				(> (- planeX (airplane x?)) 16)
			)
			((= newView (View new:))
				view: 259
				posn: (airplane x?) 82
				loop: 1
				cel: 0
				setPri: 15
				init:
				addToPic:
			)
			((= newView_2 (View new:))
				view: 259
				posn: (airplane x?) 79
				loop: 1
				cel: 3
				setPri: 0
				init:
				addToPic:
			)
		)
		(super doit:)
	)
)

(instance blowUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(airplane setMotion: MoveTo (- planeX 9) 82 self)
			)
			(1
				(sounds eachElementDo: #dispose)
				(explosion play:)
				(airplane
					setLoop: -1
					loop: 0
					setCel: -1
					posn: planeX 110
					setCycle: EndLoop
				)
				((= fire1 (Actor new:))
					view: 259
					setLoop: 2
					cel: 0
					posn: (+ planeX 11) 100
					setPri: 15
					setStep: 2 2
					ignoreActors:
					illegalBits: 0
					init:
					setCycle: Forward
					setMotion: MoveTo (+ planeX 20) 220 self
				)
				((= fire2 (Actor new:))
					view: 259
					setLoop: 3
					cel: 0
					posn: (+ planeX 26) 99
					setPri: 15
					setStep: 1 3
					init:
					ignoreActors:
					illegalBits: 0
					setCycle: Forward
					setMotion: MoveTo (+ planeX 34) 220
				)
				((= fire3 (Actor new:))
					view: 259
					setLoop: 3
					cel: 0
					posn: (+ planeX 29) 89
					setStep: 2 4
					setPri: 15
					init:
					ignoreActors:
					illegalBits: 0
					setCycle: Forward
					setMotion: MoveTo (+ planeX 39) 220
				)
			)
			(2
				(EgoDead
					{Looks like you BLEW it, Sonny!\nYou and 49 other people are now dead!}
					80
					{Jim is terribly disappointed, and says:}
				)
			)
		)
	)
)
