;;; Sierra Script 1.0 - (do not remove this comment)
(script# 73)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Extra)
(use Motion)
(use Game)
(use System)

(public
	rm73 0
)

(instance rm73 of Room
	(properties
		picture 73
		style WIPEDOWN
		south 77
	)
	
	(method (init)
		(LoadMany VIEW 18 274 0 2)
		(LoadMany SOUND 59 42)
		(super init:)
		(ego
			view: 18
			setLoop: 0
			posn: 182 -42
			cycleSpeed: 0
			priority: 6
			signal: (| ignrHrz fixPriOn)
			moveSpeed: 0
			ignoreControl: cWHITE
			init:
		)
		(if (!= howFast 0)
			(d1 setPri: 14 init:)
			(d2 setPri: 15 init:)
			(d3 setPri: 14 init:)
			(d4 setPri: 13 init:)
			(d5 setPri: 14 init:)
			(d6 setPri: 13 init:)
		)
		(HandsOff)
		(self setScript: fallScript)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
	)
)

(instance fallScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego yStep: 11 setMotion: MoveTo 182 133 self)
			)
			(1
				(DisplayNewGraphics)
				(ego setLoop: 1 cycleSpeed: 1 setCycle: CycleTo 2 1 self)
			)
			(2
				((ScriptID 0 5) number: 59 loop: 1 play:)
				((ScriptID 0 6) loop: 1 stop:)
				(self cue:)
			)
			(3
				(ShakeScreen 6)
				(ego setCycle: EndLoop)
				(= seconds 3)
			)
			(4
				(Print 73 0 #at 25 20 #width 260 #mode 1 #dispose)
				((ScriptID 0 5) stop: dispose:)
				(ego setLoop: 2 setCel: 0 posn: 181 132)
				(= cycles 2)
			)
			(5
				((ScriptID 0 6) number: 42 loop: -1 play:)
				(ego cycleSpeed: 2 setCycle: EndLoop self)
			)
			(6
				(NormalEgo)
				(ego
					loop: 2
					observeControl: -32768
					setMotion: MoveTo 180 190 self
				)
			)
			(7
				(cls)
				(ego setMotion: MoveTo 180 210)
			)
		)
	)
)

(instance d1 of Extra
	(properties
		x 128
		y 34
		view 274
		loop 2
		cel 3
		cycleType 1
		minPause 30
		maxPause 40
		minCycles 1
		maxCycles 1
	)
)

(instance d2 of Extra
	(properties
		x 227
		y 40
		view 274
		loop 2
		cel 1
		cycleType 1
		minPause 40
		maxPause 50
		minCycles 1
		maxCycles 1
	)
)

(instance d3 of Extra
	(properties
		x 254
		y 40
		view 274
		loop 2
		cel 5
		cycleType 1
		minPause 50
		maxPause 60
		minCycles 1
		maxCycles 1
	)
)

(instance d4 of Extra
	(properties
		x 210
		y 53
		view 274
		loop 2
		cel 4
		cycleType 1
		minPause 36
		maxPause 40
		minCycles 1
		maxCycles 1
	)
)

(instance d5 of Extra
	(properties
		x 289
		y 57
		view 274
		loop 2
		cel 6
		cycleType 1
		minPause 40
		maxPause 48
		minCycles 1
		maxCycles 1
	)
)

(instance d6 of Extra
	(properties
		x 266
		y 53
		view 274
		loop 2
		cel 1
		cycleType 1
		minPause 20
		minCycles 1
		maxCycles 1
	)
)
