;;; Sierra Script 1.0 - (do not remove this comment)
(script# 30)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room30 0
)

(local
	thisControl
	fallControl
	aHench1
	aHench2
	local4
	fallToY
)
(instance hTheme of Sound
	(properties
		number 29
	)
)

(instance fallSound of Sound
	(properties
		number 51
	)
)

(instance cuckooSound of Sound
	(properties
		number 80
	)
)

(instance Room30 of Room
	(properties
		picture 30
	)
	
	(method (init)
		(= north 24)
		(= east 79)
		(= west 29)
		(= horizon 85)
		(= isIndoors FALSE)
		(ego edgeHit: 0)
		(super init:)
		(if isNightTime
			(curRoom overlay: 130)
		)
		(self setRegions: FOREST MOUNTAIN)
		(Load VIEW 17)
		(Load VIEW 18)
		(Load VIEW 21)
		(Load VIEW 33)
		(Load SOUND 80)
		(switch prevRoomNum
			(west
				(ego x: 2)
			)
			(24
				(ego posn: 112 (+ horizon 2))
			)
			(0
				(ego x: 183 y: 123)
			)
			(92
				(ego view: 80 setCycle: Forward setScript: henchFlyIn)
				(= horizon -1000)
				(henchFlyIn changeState: 1)
			)
			(79
				(if (== (ego view?) 80)
					(= horizon -1000)
					(ego ignoreHorizon: setScript: henchFlyIn)
					(henchFlyIn changeState: 1)
				else
					(ego x: 318 y: 100)
				)
			)
			(else
				(ego x: 183 y: 123)
			)
		)
		(if (== (ego script?) 0)
			(ego view: 2 xStep: 3 yStep: 2 init:)
		)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(!= (= thisControl (ego onControl:)) fallControl)
				(== (curRoom script?) 0)
			)
			(= fallControl thisControl)
			(cond 
				((& thisControl cRED)
					(self setScript: shortFall)
				)
				((& thisControl cGREEN)
					(self setScript: deadFall)
				)
				((& thisControl cMAGENTA)
					(self setScript: deadMagenta)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'climb/boulder')
						(Print 30 0)
					)
					((Said 'look>')
						(cond 
							((Said '/path')
								(Print 30 1)
							)
							((Said '/boulder')
								(Print 30 2)
							)
							((Said '/goon,man,person')
								(if (cast contains: aHench1)
									(Print 30 3)
								else
									(Print 30 4)
								)
							)
							((Said '[<around][/room]')
								(Print 30 5)
							)
						)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(if (!= (ego view?) 80)
			(super newRoom: n)
		)
	)
)

(instance shortFall of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fallSound play:)
				(HandsOff)
				(cond 
					((< (ego x?) 183)
						(= fallToY 146)
					)
					((> (ego x?) 195)
						(= fallToY 165)
					)
					(else
						(= fallToY 154)
					)
				)
				(ego
					yStep: 6
					yStep: 6
					illegalBits: 0
					loop: (& (ego loop?) 1)
					setCel: 0
					view: 17
					setCycle: Forward
					setMotion: MoveTo (ego x?) fallToY self
				)
			)
			(1
				(ego xStep: 3 yStep: 2 view: 18 loop: 0 setCycle: Forward)
				(Timer setReal: self 5)
				(cuckooSound play:)
			)
			(2
				(ego view: 21 loop: 2 cel: 4 setCycle: BegLoop self)
			)
			(3
				(ego setCycle: Walk view: 2 illegalBits: cWHITE)
				(HandsOn)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance deadFall of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fallSound play:)
				(HandsOff)
				(ego
					yStep: 10
					illegalBits: 0
					loop: (& (ego loop?) 1)
					setCel: 0
					view: 17
					setCycle: Forward
					setMotion: MoveTo (ego x?) 175 self
				)
			)
			(1
				(ego view: 33 loop: 0)
				(Animate (cast elements?) FALSE)
				(ShakeScreen 10 shakeSDown)
				(Timer setReal: self 3)
			)
			(2
				(Print 30 6)
				(Timer setReal: self 5)
			)
			(3
				(HandsOn)
				(= dead TRUE)
			)
		)
	)
)

(instance deadMagenta of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fallSound play:)
				(HandsOff)
				(ego
					yStep: 10
					illegalBits: 0
					loop: (& (ego loop?) 1)
					setCel: 0
					view: 17
					setCycle: Forward
					setMotion: MoveTo (ego x?) 175 self
				)
			)
			(1
				(ego view: 33 loop: 0)
				(Timer setReal: self 3)
				(Animate (cast elements?) FALSE)
				(ShakeScreen 10 shakeSDown)
			)
			(2
				(Print 30 6)
				(Timer setReal: self 5)
			)
			(3
				(= dead TRUE)
				(HandsOn)
			)
		)
	)
)

(instance henchFlyIn of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(Load VIEW 144)
				(Load VIEW 60)
				(Load VIEW 143)
				(HandsOff)
				(hTheme play:)
				(ego
					view: 80
					setLoop: 4
					ignoreHorizon:
					yStep: 2
					illegalBits: 0
					setPri: 12
					setCycle: Forward
					posn: 168 -30
					init:
					setMotion: MoveTo 160 121 self
				)
			)
			(2
				(ego
					view: 60
					setLoop: 3
					cel: 255
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(= aHench1 (Actor new:))
				(= aHench2 (Actor new:))
				(aHench1
					view: 144
					posn: (- (ego x?) 15) (ego y?)
					xStep: 6
					yStep: 3
					cycleSpeed: 0
					setCycle: Forward
					setScript: h1Actions
				)
				(aHench2
					view: 144
					posn: (+ (ego x?) 15) (ego y?)
					xStep: 6
					yStep: 3
					cycleSpeed: 0
					setCycle: Forward
					setScript: h2Actions
				)
			)
			(3
				(ego
					view: 2
					ignoreActors: 0
					setLoop: -1
					loop: 1
					illegalBits: cWHITE
					setPri: -1
					cycleSpeed: 0
					setCycle: Walk
				)
				(ego xStep: 3 yStep: 2)
				(HandsOn)
			)
		)
	)
)

(instance h1Actions of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aHench1
					init:
					ignoreHorizon:
					illegalBits: 0
					setPri: 12
					setCycle: Walk
					setMotion: MoveTo 145 40 self
				)
			)
			(1
				(aHench1 view: 143 setMotion: MoveTo 145 -30 self)
				(= local4 1)
			)
			(2
				(curRoom horizon: 85)
				(aHench1 dispose:)
			)
		)
	)
)

(instance h2Actions of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aHench2
					init:
					ignoreHorizon:
					illegalBits: 0
					setPri: 12
					setCycle: Walk
					setMotion: MoveTo 175 40 self
				)
			)
			(1
				(aHench2 view: 143 setMotion: MoveTo 175 -30 self)
			)
			(2
				(aHench2 dispose:)
			)
		)
	)
)
