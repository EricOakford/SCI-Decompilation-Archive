;;; Sierra Script 1.0 - (do not remove this comment)
(script# 87)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm87 0
)

(local
	local0
	local1
	local2 = [297 200 241 175 194 155 168 143 150 132 134 126 119 116]
)
(instance reflection of Actor
	(properties
		view vWater
	)
	
	(method (doit)
		(self
			loop: (ego loop?)
			cel: (ego cel?)
			x: (ego x?)
			y: (ego y?)
			forceUpd:
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(Said '/reflection,monster')
					(MouseClaimed self event shiftDown)
				)
				(HighPrint 87 0)
				;You see your own reflection in the crystal-clear waters.
			)
		)
	)
	
	(method (canBeHere)
		(return TRUE)
	)
)

(instance waterfall of Prop
	(properties
		y 46
		x 300
		view vWater
		loop 4
		cycleSpeed 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
			(or (Said '/cascade') (MouseClaimed self event shiftDown))
			(HighPrint 87 1)
			;You see a lovely waterfall in the distance, feeding the lake with ice cold mountain water.
			)
		)
	)
)

(instance ripple1 of Actor
	(properties
		y 78
		x 290
		yStep 1
		view vWater
		loop 5
		cycleSpeed 2
		illegalBits $0000
		xStep 1
	)
)

(instance ripple2 of Actor
	(properties
		y 78
		x 290
		yStep 1
		view vWater
		loop 5
		cycleSpeed 2
		illegalBits $0000
		xStep 1
	)
)

(instance geese of Actor
	(properties
		y 135
		x 341
		view vWater
		loop 6
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((or (Said '/sky') (Said 'look<up/'))
				(HighPrint 87 2)
				;You see a flock of geese as it passes overhead.
				)
			(
				(or
					(Said '/flock,bird')
					(MouseClaimed self event shiftDown)
				)
				(HighPrint 87 3)
				;You see the reflection of a flock of geese as it passes overhead.
			)
		)
	)
)

(instance periscope of Actor
	(properties
		y 241
		x 399
		view vWater
		loop 7
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed self event shiftDown)
				(HighPrint 87 4)
				;It must be your imagination.  What would a submarine be doing in a fantasy adventure?
				;Perhaps you'd rather be playing `Code Name: Iceman' -- it has a submarine.
				)
		)
	)
)

(instance tromp of Sound
	(properties
		number 66
		priority 1
	)
)

(instance rm87 of Room
	(properties
		picture 87
		style HSHUTTER
		horizon 64
		north 81
	)
	
	(method (init)
		(if (and (Btst fBeenIn87) (not (Btst LAKE_EASTER_EGG)))
			(Load SOUND (SoundFX 66))
		)
		(Load VIEW vWater) ;CI: add vEgoWaterReflection
		(super init:)
		(SolvePuzzle POINTS_VISITLAKE 1)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(ChangeGait MOVE_WALK 0)
		(ego posn: 105 65 init: setMotion: MoveTo 105 75)
		(reflection
			ignoreActors: 1
			ignoreHorizon: 1
			setPri: 1
			posn: (ego x?) (ego y?)
			init:
		)
		(waterfall setLoop: 4 init: setCycle: Forward)
		(if (!= howFast 0)
			(ripple1
				setLoop: 5
				ignoreActors: TRUE
				init:
				setScript: rip1Script
			)
			(ripple2
				setLoop: 5
				ignoreActors: TRUE
				init:
				setScript: rip2Script
			)
		)
		(cond 
			((and (Btst fBeenIn87) (not (Btst LAKE_EASTER_EGG)))
				(periscope setLoop: 7 init: setScript: periScript)
				(Bset LAKE_EASTER_EGG)
			)
			((!= howFast slow) (geese setLoop: 6 init: setScript: flockScript))
		)
	)
	
	(method (dispose)
		(Bset fBeenIn87)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((or (Said '[use]/stealth') (Said 'run,sneak'))
						(HighPrint 87 5)
						;You don't want to do that.  A walking pace is more appropriate for enjoying the majestic beauty of this scene.
						)
					(
					(or (Said 'swim') (Said 'enter,go/water,lake'))
					(HighPrint 87 6)
					;Swimming in the Spiegelsee is a bad idea.  The lake is fed from mountain run-off, and the water is ice cold.
					)
					((Said 'drink[/water]')
						(HighPrint 87 7)
						;The lake water tastes pretty good, if a bit flat.
						(if (Btst DISPEL_LEARNED_RECIPE)
							(HighPrint 87 8)
							;... but this is not the water you want.
							)
						)
					(
						(or
							(Said 'use/bottle')
							(Said 'get,put/water[/bottle]')
							(Said 'get/bottle/water')
						)
						(if (ego has: iFlask)
							(ego get: iWater use: iFlask 1)
							(Bset fHaveLakeWater)
							(HighPrint 87 9)
							;You fill a flask with the clear lake water.
						else
							(HighPrint 87 10)
							;You don't have anything in which to carry the water.
						)
					)
					((Said 'look>')
						(cond 
							((Said '/periscope,submarine')
								(HighPrint 87 4)
								;It must be your imagination.  What would a submarine be doing in a fantasy adventure?
								;Perhaps you'd rather be playing `Code Name: Iceman' -- it has a submarine.
								)
							((or (Said '/lake,water') (Said '/[!*]'))
								(HighPrint 87 11)
								;It's the beautiful Spiegelsee (Mirror Lake).  You pause for some peaceful reflection.
								)
						)
					)
				)
			)
		)
	)
)

(instance periScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< local1 7)
					(client
						setCel: local1
						setMotion:
							MoveTo
							[local2 (+ local1 local1)]
							[local2 (+ local1 local1 1)]
							self
					)
				else
					(self changeState: 2)
				)
			)
			(1
				(++ local1)
				(self changeState: 0)
			)
			(2
				(client setCel: local1)
				(tromp number: (SoundFX 66) init: play:)
				(ShakeScreen 8)
				(= seconds 2)
			)
			(3
				(client setCel: -1 cycleSpeed: 3 setCycle: EndLoop self)
			)
			(4
				(ShakeScreen 4)
				(= seconds 2)
			)
			(5
				(ShakeScreen 2)
				(client dispose:)
			)
		)
	)
)

(instance flockScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 15)))
			(1
				(client setCycle: Forward setMotion: MoveTo 167 221 self)
			)
			(2 (client dispose:))
		)
	)
)

(instance rip1Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client posn: 300 300 setCycle: 0 stopUpd:)
				(if (not local0) (= local0 1) (self cue:))
			)
			(1
				(client
					posn: (+ 290 (Random 0 10)) (Random 78 90)
					setCycle: Forward
					setMotion: MoveTo (+ 245 (Random 0 10)) 145 self
				)
			)
			(2
				(rip2Script changeState: 1)
				(client
					setMotion: MoveTo (+ 200 (Random 0 24)) 189 self
				)
			)
			(3 (self changeState: 0))
		)
	)
)

(instance rip2Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client posn: 400 400 setCycle: 0 stopUpd:)
			)
			(1
				(client
					posn: (+ 290 (Random 0 10)) (Random 78 90)
					setCycle: Forward
					setMotion: MoveTo (+ 245 (Random 0 10)) 145 self
				)
			)
			(2
				(rip1Script changeState: 1)
				(client
					setMotion: MoveTo (+ 200 (Random 0 24)) 189 self
				)
			)
			(3 (self changeState: 0))
		)
	)
)
