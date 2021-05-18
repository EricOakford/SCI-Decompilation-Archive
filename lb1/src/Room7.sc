;;; Sierra Script 1.0 - (do not remove this comment)
(script# 7)
(include game.sh)
(use Main)
(use Intrface)
(use DCIcon)
(use Wander)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room7 0
)

(local
	dying
)
(instance Room7 of Room
	(properties
		picture 7
	)
	
	(method (init)
		(= south 8)
		(= west 6)
		(= horizon 86)
		(super init:)
		(LoadMany VIEW 5 13 35)
		(LoadMany SOUND 67 82 92)
		(self setRegions: 205)
		(self setFeatures: Bridge)
		(if howFast
			(wave1
				setPri: 1
				cycleSpeed: 2
				ignoreActors: TRUE
				setCycle: Forward
				init:
			)
			(wave2
				setPri: 1
				cycleSpeed: 2
				ignoreActors: TRUE
				setCycle: Forward
				init:
			)
			(flyCage init:)
			(Fly
				setLoop: 5
				cel: 0
				setStep: 3 3
				observeBlocks: flyCage
				ignoreHorizon: TRUE
				cycleSpeed: 2
				setCycle: Forward
				setMotion: Wander 5
				init:
			)
			(Fly2
				setLoop: 5
				cel: 1
				setStep: 3 3
				observeBlocks: flyCage
				ignoreHorizon: TRUE
				cycleSpeed: 2
				setCycle: Forward
				setMotion: Wander 5
				init:
			)
			(Fly3
				setLoop: 5
				cel: 2
				setStep: 3 3
				observeBlocks: flyCage
				ignoreHorizon: TRUE
				cycleSpeed: 2
				setCycle: Forward
				setMotion: Wander 5
				init:
			)
			(Fly4
				setLoop: 5
				cel: 3
				setStep: 3 3
				observeBlocks: flyCage
				ignoreHorizon: TRUE
				cycleSpeed: 2
				setCycle: Forward
				setMotion: Wander 5
				init:
			)
			(Fly5
				setLoop: 5
				cel: 4
				setStep: 3 3
				observeBlocks: flyCage
				ignoreHorizon: TRUE
				cycleSpeed: 2
				setCycle: Forward
				setMotion: Wander 5
				init:
			)
		)
		(if (and (== currentAct 3) (< global115 3))
			(self setRegions: 203)
		)
		(if (== prevRoomNum 6)
			(ego posn: 1 188)
		else
			(ego posn: 35 188)
		)
		(ego view: 0 init:)
	)
	
	(method (doit)
		(if (FirstEntry)
			(Print 7 0)
		)
		(if (and (& (ego onControl: origin) cBLUE) (== dying FALSE))
			(= dying TRUE)
			(self setScript: sink)
		)
		(if (and (& (ego onControl: origin) cCYAN) (== dying FALSE))
			(= dying TRUE)
			(myMusic number: 67 loop: 1 play:)
			(self setScript: falling)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 976)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(if (== (event type?) saidEvent)
			(cond 
				((Said 'examine>')
					(cond 
						((Said '<(below,below)/bridge')
							(Print 7 1)
						)
						((Said '<(across,on)/brook,water')
							(Print 7 2)
						)
						((Said '/brook,water')
							(Print 7 3)
						)
						((Said '<down')
							(ParseName {ground})
						)
						((Said '[/bridge,room,around]')
							(Print 7 0)
						)
					)
				)
				(
					(or
						(Said 'cross/bridge')
						(Said 'go<(above,across)/bridge')
					)
					(Print 7 4)
				)
				((Said 'climb[<(across,above)]/oak,log')
					(Print 7 5)
				)
				((Said 'hop[<(across,above)][/bridge,brook]')
					(Print 7 6)
				)
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance falling of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 5
					setLoop: 0
					cel: 0
					setCycle: EndLoop
					yStep: 5
					illegalBits: 0
					setMotion: MoveTo 115 130 self
				)
			)
			(1
				(myMusic number: 82 loop: 1 play:)
				(ego loop: 2 posn: 116 149 cel: 0 setCycle: EndLoop self)
			)
			(2
				(ego view: 13 loop: 0 posn: 92 157 setCycle: Forward)
				(= seconds 3)
			)
			(3
				(ego view: 13 loop: 2 setCycle: EndLoop self)
			)
			(4
				(= seconds 3)
			)
			(5
				(= cIcon myIcon)
				(= deathLoop 5)
				(= deathCel 0)
				(= cyclingIcon TRUE)
				(EgoDead 7 7)
			)
		)
	)
)

(instance sink of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(myMusic number: 92 loop: 1 play:)
				(ego
					view: 35
					cel: 0
					cycleSpeed: 3
					setMotion: MoveTo (+ (ego x?) 30) (ego y?)
					setCycle: EndLoop self
				)
			)
			(1
				(ego hide:)
				(= seconds 3)
			)
			(2
				(= cIcon myIcon)
				(= deathLoop 5)
				(= deathCel 0)
				(= cyclingIcon TRUE)
				(EgoDead 7 7)
			)
		)
	)
)

(instance myIcon of DCIcon
	(properties
		view 13
		loop 5
	)
	
	(method (init)
		((= cycler (EndLoop new:)) init: self)
	)
)

(instance myMusic of Sound)

(instance wave1 of Prop
	(properties
		y 173
		x 235
		view 207
	)
)

(instance wave2 of Prop
	(properties
		y 166
		x 104
		view 207
		loop 1
	)
)

(instance Fly of Actor
	(properties
		y 123
		x 274
		view 5
		cel 1
	)
)

(instance Fly2 of Actor
	(properties
		y 179
		x 297
		view 5
		cel 1
	)
)

(instance Fly3 of Actor
	(properties
		y 139
		x 207
		view 5
	)
)

(instance Fly4 of Actor
	(properties
		y 179
		x 197
		view 5
	)
)

(instance Fly5 of Actor
	(properties
		y 139
		x 217
		view 5
	)
)

(instance flyCage of Cage
	(properties
		top 120
		left 135
		bottom 202
		right 322
	)
)

(instance Bridge of RFeature
	(properties
		nsTop 106
		nsLeft 40
		nsBottom 180
		nsRight 155
	)
	
	(method (handleEvent event)
		(if(or (MousedOn self event shiftDown) (Said 'examine/bridge'))
			(event claimed: TRUE)
			(Print 7 0)
		)
	)
)
