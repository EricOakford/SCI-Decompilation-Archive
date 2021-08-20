;;; Sierra Script 1.0 - (do not remove this comment)
(script# 75)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm075 0
)

(local
	local0
	local1
	local2
	local3
	egoX
	egoY
)
(instance rm075 of Room
	(properties
		picture 75
	)
	
	(method (init &tmp [temp0 50])
		(User canInput: TRUE canControl: TRUE)
		(self setRegions: ORTEGA)
		(Load VIEW 94)
		(Load VIEW 99)
		(Load VIEW 100)
		(Load SOUND 33)
		(Load SOUND 45)
		(super init:)
		(theMusic number: 50 loop: -1 priority: 0 play:)
		(curRoom setScript: UpLadder)
	)
	
	(method (doit &tmp egoOnControl [temp1 50])
		(super doit:)
		(if local0 (self newRoom: 74))
		(if
			(and
				(or (== (ego onControl:) 8) (== (ego onControl:) 24))
				(not (curRoom script?))
				(== (ego x?) egoX)
				(== (ego y?) egoY)
				(not local1)
			)
			(= local1 1)
			(ego setMotion: MoveTo (+ (ego x?) 20) 229)
		)
		(= egoX (ego x?))
		(= egoY (ego y?))
		(if (== (curRoom script?) 0)
			(= egoOnControl (ego onControl:))
			(self
				setScript:
					(cond 
						((== egoOnControl cGREEN) walkGreen)
						((== egoOnControl cCYAN) walkCyan)
						((== egoOnControl cRED) fallRed)
						((or (== egoOnControl 2) (== egoOnControl 66)) fallBlue)
						((or (== egoOnControl 40) (== egoOnControl 56)) TopLadder)
						(else 0)
					)
			)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look>')
				(cond 
					((Said '[/area,around]') (if forceBeamDestroyed (Print 75 0) else (Print 75 1)))
					((Said '/down,cavity,edge')
						(if (== (ego onControl:) 64)
							(if forceBeamDestroyed (Print 75 2) else (Print 75 3))
						else
							(Print 75 4)
						)
					)
					((Said '/moon,pestulon') (Print 75 5))
					((Said '/ladder') (Print 75 6))
					((Said '/device,building,generator')
						(cond 
							((not forceBeamDestroyed) (if decodedMessage (Print 75 7) else (Print 75 8)))
							(decodedMessage (Print 75 9))
							(else (Print 75 10))
						)
					)
					((Said '/beam')
						(if (not forceBeamDestroyed)
							(if decodedMessage (Print 75 11) else (Print 75 12))
						else
							(Print 75 13)
						)
					)
					((Said '/lava') (Print 75 14))
				)
			)
			((Said 'jump,enter,go/generator,cavity') (Print 75 15))
			((Said '(climb[<up]),ladder') (Print 75 16))
			((Said 'down,descend,(climb[<down])[/ladder]')
				(if (ego inRect: 157 98 183 106)
					(curRoom setScript: DownLadder)
				else
					(Print 75 17)
				)
			)
			((Said 'throw,drop/detonator,bomb')
				(cond 
					(forceBeamDestroyed (AlreadyDone))
					((not (ego has: 10)) (Print 75 18))
					(
					(or (== (ego onControl:) 64) (== (ego onControl:) 66)) (curRoom setScript: DropBomb))
					(else (Print 75 19))
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (not script)
			(if forceBeamDestroyed (++ ortegaPostBeamRooms))
			(super newRoom: newRoomNumber)
		)
	)
)

(instance UpLadder of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= inCartoon 1)
				(ego
					illegalBits: 0
					posn: 313 219
					view: 99
					setStep: 3 2
					setLoop: 0
					setMotion: MoveTo 173 113 self
					init:
				)
			)
			(1
				(ego
					view: 0
					illegalBits: cWHITE
					posn: 169 105
					setStep: 3 2
					setLoop: -1
				)
				(= cycles 2)
			)
			(2
				(ego loop: 1)
				(HandsOn)
				(= inCartoon 0)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance TopLadder of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= inCartoon 1)
				(ego illegalBits: 0 setMotion: MoveTo 173 113 self)
			)
			(1
				(ego
					view: 0
					illegalBits: -32768
					posn: 169 105
					setStep: 3 2
					setLoop: -1
				)
				(= cycles 2)
			)
			(2
				(ego loop: 1)
				(= local2 0)
				(= local3 1)
				(HandsOn)
				(= inCartoon 0)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance DownLadder of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= inCartoon 1)
				(ego illegalBits: 0 setMotion: MoveTo 169 105 self)
			)
			(1
				(ego
					posn: 173 113
					view: 99
					setLoop: 0
					setMotion: MoveTo 313 219 self
				)
			)
			(2
				(= local0 1)
				(curRoom setScript: 0)
				(= inCartoon 0)
				(HandsOn)
			)
		)
	)
)

(instance walkCyan of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not local2)
					(= local2 1)
					(= local3 0)
					(= local1 0)
					(ego view: 99 setAvoider: Avoider setLoop: 0)
				)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance walkGreen of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not local3)
					(= local3 1)
					(= local2 0)
					(ego
						view: 0
						setAvoider: 0
						loop:
							(switch (ego heading?)
								(0 3)
								(45 0)
								(90 0)
								(135 0)
								(180 2)
								(225 1)
								(270 1)
								(315 1)
							)
					)
				)
				(RedrawCast)
				(ego setLoop: -1)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance fallRed of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (not (ego mover?)) (== (falling prevSignal?) -1))
			(self changeState: 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= inCartoon 1)
				(falling play:)
				(ego
					view: 99
					setLoop: 1
					setStep: 6 12
					setCycle: 0
					posn: (ego x?) (ego y?)
					illegalBits: 0
				)
				(if (> (ego x?) 180)
					(ego setStep: 12 12 setCel: 1 setMotion: MoveTo 340 189)
				else
					(ego setCel: 0 setMotion: MoveTo (+ (ego x?) 20) 229)
				)
				(ohnoScript changeState: 0)
			)
			(1 (EgoDead 0 0 0 1))
		)
	)
)

(instance fallBlue of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== (falling prevSignal?) -1)
			(self changeState: 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(falling play:)
				(ego
					setLoop:
					setCel:
					setStep: 6 12
					setCycle: 0
					setPri: 2
					illegalBits: 0
				)
				(RedrawCast)
				(ego setMotion: MoveTo (ego x?) 229)
				(ohnoScript changeState: 0)
			)
			(1 (EgoDead 0 0 0 1))
		)
	)
)

(instance ohnoScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(OhNo init: setCycle: EndLoop self)
			)
			(1 (OhNo dispose:))
		)
	)
)

(instance DropBomb of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= inCartoon 1)
				(ego view: 100 setLoop: 0 setCel: 0)
				(bomb init:)
				;(ego put: (= cycles 10))	;EO: This seems to be a decompiler error
				(ego put: iThermalDetonator)
				(= cycles 10)
			)
			(1
				(ego setCel: 1)
				(bomb setMotion: MoveTo (bomb x?) 150 self)
			)
			(2
				(ego setCel: 2)
				(bomb dispose:)
				(blowUp play:)
				(flash init: setCycle: EndLoop self)
			)
			(3
				(ShakeScreen 20 3)
				(blowUp play:)
				(flash setCycle: EndLoop self)
			)
			(4
				(ShakeScreen 20 3)
				(theMusic stop:)
				(RedrawCast)
				(theMusic number: 71 loop: -1 priority: 0 play:)
				(if decodedMessage (Print 75 20) else (Print 75 21))
				(ego view: 0 setLoop: -1 setCel: -1 cel: 0 loop: 1)
				(theGame changeScore: 20)
				(= forceBeamDestroyed TRUE)
				(= shakeTimer 300)
				(HandsOn)
				(= inCartoon 0)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance OhNo of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 94
			setLoop: 0
			setCel: 0
			cycleSpeed: 1
			setPri: (ego priority?)
			posn: (ego x?) (- (ego y?) 50)
			ignoreActors: 1
		)
	)
)

(instance bomb of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 100
			setLoop: 0
			setCel: 3
			setPri: 2
			posn: (- (ego x?) 10) (- (ego y?) 21)
			setStep: 1 4
			ignoreControl: 1
			ignoreActors: 1
			illegalBits: 0
		)
	)
)

(instance flash of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 100
			setLoop: 1
			setCel: 0
			setPri: 3
			posn: 82 79
			ignoreActors: 1
		)
	)
)

(instance falling of Sound
	(properties
		number 45
		priority 1
	)
)

(instance blowUp of Sound
	(properties
		number 33
		priority 1
	)
)
