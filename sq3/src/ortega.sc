;;; Sierra Script 1.0 - (do not remove this comment)
(script# ORTEGA)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	ortega 0
)

(local
	local0
)
(instance ortega of Region
	(method (init)
		(self keep: FALSE)
		(super init:)
		(Load VIEW 94)
		(Load VIEW 89)
		(Load SOUND 45)
		(Load SOUND 78)
	)
	
	(method (doit)
		(super doit:)
		(if fallingIntoLava
			(= fallingIntoLava FALSE)
			(curRoom setScript: FallDown)
		)
		(if (and global219 (> fryToDeathTimer 1))
			(-- fryToDeathTimer)
		)
		(if
			(and
				(== fryToDeathTimer 1)
				(!= curRoomNum 62)
				(== (IsObjectOnControl ego 15) 1)
				(== (curRoom script?) 0)
			)
			(curRoom setScript: FryToDeath)
		)
		(if (and forceBeamDestroyed (not (-- shakeTimer)))
			(boom play:)
			(ShakeScreen 20 shakeSDiagonal)
			(= shakeTimer (Random 10 600))
			(if (not ortegaEarthquakeWarning)
				(Print 600 0)
				(= ortegaEarthquakeWarning TRUE)
			)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (or (event claimed?) (!= (event type?) saidEvent))
			(return)
		)
		(cond 
			((Said 'look>')
				(cond 
					((Said '[/area,around,moon,dirt,ortega]')
						(if (== curRoomNum 70)
							(if ortegaWorkersLeft
								(Print 600 1)
							else
								(Print 600 2)
							)
						else
							(Print 600 3)
						)
					)
					((Said '/volcano')
						(Print 600 4)
					)
					((Said '/lava')
						(Print 600 5)
					)
					((Said '/rock,boulder')
						(Print 600 6)
					)
					((Said '/lake')
						(Print 600 7)
					)
					((Said '/air')
						(Print 600 8)
					)
					((Said '/ledge,cliff,edge')
						(Print 600 9)
					)
					((Said '/crack')
						(Print 600 10)
					)
					((Said '/cavity,canyon')
						(Print 600 11)
					)
					((Said '/partition')
						(if
							(or
								(== curRoomNum 73)
								(== curRoomNum 74)
								(== curRoomNum 75)
							)
							(Print 600 12)
						else
							(Print 600 13)
						)
					)
					((Said '/craft')
						(switch curRoomNum
							(62
								(Print 600 14)
							)
							(67
								(Print 600 15)
							)
							(70
								(if ortegaWorkersLeft
									(Print 600 16)
								else
									(Print 600 17)
								)
							)
							(else
								(Print 600 16)
							)
						)
					)
				)
			)
			((Said 'open/door')
				(Print 600 18)
			)
			((Said 'jump<off/cliff')
				(Print 600 19)
			)
			((Said 'get/ladder')
				(if (or (== curRoomNum 74) (== curRoomNum 75))
					(Print 600 20)
				else
					(Print 600 21)
				)
			)
			((Said 'get/rock')
				(Print 600 22)
			)
			((Said 'get/dirt')
				(Print 600 23)
			)
			((Said 'use/pole')
				(if (!= curRoomNum 66)
					(CantDo)
				)
			)
			((Said '(turn<on),press,use/detonator')
				(if (ego has: iThermalDetonator)
					(Print 600 24)
				else
					(DontHave)
				)
			)
			((Said 'wear/panties')
				(if (ego has: iThermoUnderwear)
					(if wearingUnderwear
						(Print 600 25)
					else
						(Print 600 26)
					)
				else
					(Print 600 27)
				)
			)
			((Said 'remove/panties')
				(if wearingUnderwear
					(Print 600 28)
				else
					(Print 600 29)
				)
			)
			((and (Said 'climb') (< curRoomNum 73))
				(Print 600 30)
			)
		)
	)
)

(instance FryToDeath of Script	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= inCartoon TRUE)
				(= fryToDeathTimer 0)
				(Print 600 31)
				(ego
					view: 89
					setLoop: 0
					setCel: 0
					setMotion: 0
					setCycle: EndLoop self
				)
			)
			(1
				(ego setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(2
				(ego setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(3
				(= cycles 10)
			)
			(4
				(Print 600 32)
				(EgoDead 0 0 8 11)
				(= inCartoon FALSE)
				(HandsOn)
			)
		)
	)
)

(instance FallDown of Script
	(method (doit)
		(super doit:)
		(if (== (fallSound prevSignal?) -1)
			(= local0 1)
		)
		(if (or local0 (== (fallSound state?) 0))
			(= local0 0)
			(self changeState: 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(fallSound play:)
				(ego setLoop: setCel: 0 setStep: 6 12 setCycle: 0)
				(RedrawCast)
				(ego setMotion: MoveTo (ego x?) 229)
				(ohnoScript changeState: 0)
			)
			(1
				(EgoDead 0 0 1 2)
			)
		)
	)
)

(instance ohnoScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ohno init: setCycle: EndLoop self)
			)
			(1
				(ohno dispose:)
			)
		)
	)
)

(instance ohno of Prop
	(method (init)
		(super init:)
		(self
			view: 94
			setLoop: 0
			setCel: 0
			cycleSpeed: 1
			setPri: (ego priority?)
			posn: (ego x?) (- (ego y?) 40)
			ignoreActors: TRUE
		)
	)
)

(instance fallSound of Sound
	(properties
		number 45
		priority 3
	)
)

(instance boom of Sound
	(properties
		number 78
		priority 1
	)
)
