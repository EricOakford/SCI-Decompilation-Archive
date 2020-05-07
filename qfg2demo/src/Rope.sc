;;; Sierra Script 1.0 - (do not remove this comment)
(script# ROPE) ;10
(include game.sh)
(use Main)
(use Flame)
(use Intrface)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demoRope 0
)

(local
	[ropePath 13] = [46 107 48 96 83 93 118 96 120 105 83 114 -1]
)
(instance demoRope of Room
	(properties
		picture 490
		style IRISIN
	)
	
	(method (init &tmp temp0 theX theY)
		(LoadMany VIEW 490 285 495)
		(super init:)
		(ego
			view: 285
			setLoop: 0
			cel: 3
			cycleSpeed: 1
			moveSpeed: 1
			posn: 249 149
			setCycle: 0
			init:
		)
		(adAvis init:)
		(= temp0 0)
		(while (!= [ropePath (* temp0 2)] -1)
			(= theX [ropePath (+ (* temp0 2) 0)])
			(= theY [ropePath (+ (* temp0 2) 1)])
			((PicView new:)
				view: 490
				loop: 0
				x: theX
				y: theY
				init:
			)
			(if (!= temp0 3)
				((Flame new:)
					view: 490
					loop: 1
					x: (- theX 2)
					y: theY
					z: 10
					init:
				)
			)
			(++ temp0)
		)
		(addToPics doit:)
		(self setScript: rmScript)
	)
	
	(method (doit)
		(if (== (globalSound prevSignal?) 20)
			(cls)
			(curRoom newRoom: RITUAL)
		else
			(super doit:)
		)
	)
)

(instance adAvis of Prop
	(properties
		x 152
		y 111
		view 495
	)
)

(instance rmScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print ROPE 0 #at -1 12 #dispose)
				(= seconds 2)
			)
			(1
				(ego cel: 0 setMotion: MoveTo 179 141 setCycle: EndLoop self)
			)
			(2
				(ego loop: 1 cel: 0 setMotion: 0 setCycle: EndLoop self)
			)
			(4
				(ego
					loop: 0
					cel: 0
					setMotion: MoveTo 179 141
					setCycle: EndLoop self
				)
			)
			(3
				(ego loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(5
				(ego
					moveSpeed: 0
					cycleSpeed: 0
					loop: 3
					cel: 0
					setMotion: 0
					setCycle: Forward
				)
				(adAvis cycleSpeed: 2 setCycle: EndLoop)
				(= seconds 4)
			)
			(6 (cls) (curRoom newRoom: RITUAL))
		)
	)
)
