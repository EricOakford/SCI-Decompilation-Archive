;;; Sierra Script 1.0 - (do not remove this comment)
(script# 28)
(include game.sh)
(use Main)
(use Intrface)
(use DCIcon)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room28 0
)

(local
	local0
)
(instance Room28 of Room
	(properties
		picture 28
	)
	
	(method (init)
		(super init:)
		(= horizon 124)
		(= east 29)
		(= west 27)
		(= north 22)
		(= south 1)
		(addToPics
			add: Sign Bird1 Bird2
			eachElementDo: #init
			doit:
		)
		(self
			setRegions: 206 207
			setFeatures: Sign House Statue Bird1 Bird2
		)
		(Load VIEW 9)
		(Thunder number: 17 loop: 0)
		(if howFast
			(smoke1 cycleSpeed: 2 setCycle: Forward init:)
			(smoke2 cycleSpeed: 2 setCycle: Forward init:)
			(light1 init:)
			(light2 init: setScript: showers)
		)
		(switch prevRoomNum
			(1 (ego loop: 3 posn: 157 188))
			(22 (ego posn: 160 127))
			(29
				(if (< (ego y?) 129) (ego y: 129))
			)
		)
		(ego view: 0 init:)
		(HandsOn)
	)
	
	(method (doit)
		(if (FirstEntry)
			(Print 28 0)
		)
		(if (and (& (ego onControl: 0) cGREEN) (== local0 0))
			(= local0 1)
			(= north (= south 0))
			(self setScript: falling)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room]')
								(Print 28 0)
							)
							((Said '/path')
								(Print 28 1)
							)
							((Said '<below/dock')
								(Print 28 2)
							)
							((Said '/dock')
								(Print 28 3)
							)
							((Said '/door,(ignite[<gallery])')
								(Print 28 4)
							)
							((Said '/smoke,chimney')
								(Print 28 5)
							)
						)
					)
					(
						(or
							(Said 'bathe,dive,wade')
							(Said 'enter,go,hop,dive,(get<in)/water,brook')
						)
						(Print 28 6)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance showers of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (= state 3)))
			(1
				(light1 setCycle: Forward)
				(light2 setCycle: Forward)
				(= cycles 7)
			)
			(2
				(light1 setCycle: EndLoop)
				(light2 setCycle: EndLoop self)
			)
			(3 (Thunder loop: 1 play: self))
			(4
				(if (< (Random 1 100) 15) (= state 0))
				(= cycles 7)
			)
			(5 (= state 3) (= seconds 5))
		)
	)
)

(instance falling of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 9 setLoop: 0 setCycle: EndLoop self)
			)
			(1
				(ego setLoop: 1 setCycle: Forward)
				(= cycles 3)
			)
			(2
				(ego
					setLoop: 2
					setPri: 15
					setCycle: EndLoop self
					illegalBits: 0
				)
			)
			(3
				(ego
					setCel: 9
					setCycle: 0
					setStep: 10 10
					setMotion: MoveTo (ego x?) 200 self
				)
			)
			(4
				(Splash play:)
				(ego posn: (ego x?) 190 setLoop: 6 setCycle: EndLoop self)
			)
			(5
				(ego hide:)
				(= cIcon myIcon)
				(= deathLoop 0)
				(= deathCel 0)
				(= cyclingIcon TRUE)
				(EgoDead 28 7)
			)
		)
	)
)

(instance light1 of Prop
	(properties
		y 42
		x 86
		view 128
		loop 2
		cel 1
	)
)

(instance light2 of Prop
	(properties
		y 51
		x 157
		view 128
		loop 3
		cel 1
	)
)

(instance smoke1 of Prop
	(properties
		y 25
		x 142
		view 128
		loop 8
		cel 2
	)
)

(instance smoke2 of Prop
	(properties
		y 25
		x 179
		view 128
		loop 8
	)
)

(instance Sign of RPicView
	(properties
		y 135
		x 145
		view 128
		loop 1
		cel 1
		priority 12
		signal ignrAct
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/sign')
				(Print 28 8)
			)
			((or (MousedOn self event shiftDown) (Said 'examine,read/sign'))
				(if (> (ego y?) 160)
					(Print 28 9)
				else
					(Print 28 10)
				)
				(event claimed: TRUE)
			)
		)
	)
)

(instance Bird1 of RPicView
	(properties
		y 103
		x 111
		view 128
		loop 1
		priority 12
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get,detach/bird')
				(Print 28 11)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/bird'))
				(event claimed: TRUE)
				(Print 28 12)
			)
		)
	)
)

(instance Bird2 of RPicView
	(properties
		y 103
		x 207
		view 128
		loop 1
		priority 12
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 28 12)
		)
	)
)

(instance House of RFeature
	(properties
		nsTop 34
		nsLeft 96
		nsBottom 82
		nsRight 226
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {house})
		)
	)
)

(instance Statue of RFeature
	(properties
		nsTop 80
		nsLeft 157
		nsBottom 96
		nsRight 163
	)
	
	(method (handleEvent event)
		(if (or (MousedOn self event shiftDown) (Said 'examine/monument'))
			(event claimed: TRUE)
			(Print 28 13)
		)
	)
)

(instance myIcon of DCIcon
	(properties
		view 652
	)
	
	(method (init)
		((= cycler (Forward new:)) init: self)
	)
)

(instance Thunder of Sound)

(instance Splash of Sound
	(properties
		number 82
	)
)
