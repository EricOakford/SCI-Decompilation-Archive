;;; Sierra Script 1.0 - (do not remove this comment)
(script# 54)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room54 0
)

(instance lightning of Prop
	(properties)
)

(instance thunder of Sound
	(properties)
)

(instance explosion of Sound
	(properties)
)

(instance Room54 of Room
	(properties
		picture 54
	)
	
	(method (init)
		(= south prevRoomNum)
		(Load PICTURE 540)
		(Load VIEW 79)
		(Load SOUND 31)
		(Load SOUND 32)
		(Load SOUND 33)
		(super init:)
		(NormalEgo)
		(ego init:)
		(lightning
			view: 69
			loop: 8
			cel: 0
			posn: 99 75
			setScript: flash
			init:
		)
		(= global104 0)
		(ego posn: 157 188 view: 0 loop: 3)
		(curRoom setScript: Actions)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if
			(and (== (event type?) saidEvent) (Said 'look>'))
				(cond 
					(
						(or
							(Said '/area')
							(Said '/around')
							(Said '[<around][/!*]')
						)
						(Print 54 0)
					)
					((Said '/butte,toe') (Print 54 1))
					((or (Said '/cloud,air') (Said '<up')) (Print 54 2))
					((Said '/desert') (Print 54 3))
					((Said '/dirt,dirt') (Print 54 4))
					((Said '/lightning') (Print 54 5))
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(DisposeScript EXTRA)
		(if (not isHandsOff) (super newRoom: newRoomNumber))
	)
)

(instance Actions of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(if
			(and
				(== state 0)
				(or (!= (ego x?) 157) (<= (ego y?) 185))
			)
			(= seconds 0)
			(self changeState: 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 8))
			(1
				(HandsOff)
				(explosion number: 33 priority: 34 play:)
				(curRoom overlay: 540 WIPEDOWN)
				(ego
					view: 79
					loop: 1
					cel: 255
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(= cycles 1)
			)
			(2
				(ShakeScreen 8)
				(curRoom drawPic: 54 4)
			)
			(3 (= seconds 7))
			(4 (EgoDead 901 0 11 18))
		)
	)
)

(instance flash of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 15)))
			(1
				(lightning cel: 255 setCycle: EndLoop self)
			)
			(2
				(lightning cel: 0)
				(= seconds (Random 2 5))
			)
			(3
				(thunder priority: 2 number: (Random 31 32) play: self)
			)
			(4 (= state -1))
		)
	)
)
