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
	ropePath = [46 107 48 96 83 93 118 96 120 105 83 114 -1]
)
(instance demoRope of Room
	(properties
		picture rTightrope
		style IRISIN
	)
	
	(method (init &tmp i theX theY)
		(LoadMany VIEW rTightrope vEgoTightrope vAdAvisRope)
		(super init:)
		(ego
			view: vEgoTightrope
			setLoop: 0
			cel: 3
			cycleSpeed: 1
			moveSpeed: 1
			posn: 249 149
			setCycle: 0
			init:
		)
		(adAvis init:)
		(= i 0)
		(while (!= [ropePath (* i 2)] -1)
			(= theX [ropePath (+ (* i 2) 0)])
			(= theY [ropePath (+ (* i 2) 1)])
			((PicView new:)
				view: rTightrope
				loop: 0
				x: theX
				y: theY
				init:
			)
			(if (!= i 3)
				((Flame new:)
					view: rTightrope
					loop: 1
					x: (- theX 2)
					y: theY
					z: 10
					init:
				)
			)
			(++ i)
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
		view vAdAvisRope
	)
)

(instance rmScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print ROPE 0
					#at -1 12
					#dispose
				)
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
			(6
				(cls)
				(curRoom newRoom: RITUAL)
			)
		)
	)
)
