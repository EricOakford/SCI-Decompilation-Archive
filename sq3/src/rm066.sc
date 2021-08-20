;;; Sierra Script 1.0 - (do not remove this comment)
(script# 66)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	rm066 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
)
(instance rm066 of Room
	(properties
		picture 66
		horizon 88
		north 60
		east 67
		south 69
	)
	
	(method (init &tmp [temp0 50] theJumpTo)
		(User canInput: 1 canControl: 1)
		(self setRegions: 600)
		(Load SOUND 33)
		(if forceBeamDestroyed
			(Load PICTURE 65)
			(Load VIEW 87)
			(Load SCRIPT JUMP)
			(= theJumpTo JumpTo)
		else
			(Load VIEW 90)
			(Load VIEW 95)
			(Load SOUND 78)
		)
		(super init:)
		(if forceBeamDestroyed
			(curRoom drawPic: 65)
		else
			(rock1 init: stopUpd:)
			(rock2 init: stopUpd:)
		)
		(switch prevRoomNum
			(60
				(ego
					posn:
						(if (> (ego x?) 159) 159 else (ego x?))
						(+ (curRoom horizon?) 2)
					init:
				)
			)
			(61
				(ego posn: 240 (+ (curRoom horizon?) 2) init:)
			)
			(67
				(ego
					posn:
						317
						(if (< (ego y?) (curRoom horizon?))
							(+ (curRoom horizon?) 2)
						else
							(ego y?)
						)
					init:
				)
			)
			(69
				(ego posn: 169 187 init:)
				(if (and forceBeamDestroyed (not rockSankInLava))
					(self setScript: MyMy)
				)
			)
		)
		(TheMenuBar draw:)
		(StatusLine enable:)
	)
	
	(method (doit &tmp egoOnControl [temp1 50])
		(super doit:)
		(if (== (ego view?) 95)
			(if
				(!=
					local5
					(= local4
						(cond 
							(
								(or
									(== (ego heading?) 315)
									(== (ego heading?) 0)
									(== (ego heading?) 45)
								)
								0
							)
							(
							(or (== (ego heading?) 90) (== (ego heading?) 270)) 2)
							(
								(or
									(== (ego heading?) 135)
									(== (ego heading?) 180)
									(== (ego heading?) 225)
								)
								1
							)
						)
					)
				)
				(switch local4
					(0 (ego setLoop: 1))
					(1 (ego setLoop: 0))
					(2
						(ego setLoop: (if (== local5 0) 1 else 0))
					)
				)
			)
			(= local5 local4)
		)
		(if (not (curRoom script?))
			(cond 
				((== (= egoOnControl (ego onControl:)) 3)
					(ego setPri: 3 posn: (ego x?) (+ (ego y?) 2))
					(= fallingIntoLava TRUE)
				)
				(
					(or
						(== egoOnControl 5)
						(== egoOnControl 7)
						(== egoOnControl 69)
						(== egoOnControl 133)
						(== egoOnControl 197)
					)
					(ego setPri: 3 posn: (+ (ego x?) 6) (ego y?))
					(= fallingIntoLava TRUE)
				)
				((or (== egoOnControl 9) (== egoOnControl 13))
					(ego setPri: 5 posn: (ego x?) (- (ego y?) 2))
					(= fallingIntoLava TRUE)
				)
				(
					(or
						(== egoOnControl 17)
						(== egoOnControl 49)
						(== egoOnControl 273)
					)
					(ego setPri: 7 posn: (ego x?) (+ (ego y?) 2))
					(= fallingIntoLava TRUE)
				)
				(
				(or (== egoOnControl 33) (== egoOnControl 161))
					(ego setPri: 7 posn: (- (ego x?) 12) (ego y?))
					(= fallingIntoLava TRUE)
				)
				(
				(or (== egoOnControl 257) (== egoOnControl 769))
					(ego setPri: 5 posn: (- (ego x?) 12) (ego y?))
					(= fallingIntoLava TRUE)
				)
				(
				(or (== egoOnControl 513) (== egoOnControl 1537))
					(ego setPri: 5 posn: (- (ego x?) 6) (ego y?))
					(= fallingIntoLava TRUE)
				)
				(
					(or
						(== egoOnControl 2049)
						(== egoOnControl 2065)
						(== egoOnControl 2053)
					)
					(ego setPri: 5 posn: (ego x?) (+ (ego y?) 2))
					(= fallingIntoLava TRUE)
				)
				((== egoOnControl 1025)
					(ego setPri: 3 posn: (- (ego x?) 6) (- (ego y?) 2))
					(= fallingIntoLava 1)
				)
			)
		)
		(if (and global219 local6) (-- local6))
		(if (== local6 1)
			(= local6 -1)
			(curRoom setScript: DeadMeat)
		)
	)
	
	(method (dispose)
		(if forceBeamDestroyed (DisposeScript 991))
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'leap,jump/canyon[<across,above]')
				(cond 
					((not forceBeamDestroyed) (Print 66 0))
					((ego has: iMetalPole) (self setScript: PoleVault))
					(else (Print 66 1))
				)
			)
			((Said 'pole/leap')
				(cond 
					((not forceBeamDestroyed) (Print 66 0))
					((ego has: iMetalPole) (self setScript: PoleVault))
					(else (Print 66 1))
				)
			)
			((Said 'use/pole')
				(cond 
					((not forceBeamDestroyed) (Print 66 2))
					((ego has: iMetalPole) (self setScript: PoleVault))
					(else (DontHave))
				)
			)
			((Said 'drop/pole/canyon[<across,above]')
				(cond 
					((not forceBeamDestroyed) (Print 66 3))
					((ego has: iMetalPole) (Print 66 4))
					(else (DontHave))
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (== (curRoom script?) 0)
			(switch (ego edgeHit?)
				(1
					(if (< (ego x?) 159)
						(= newRoomNumber 60)
					else
						(= newRoomNumber 61)
					)
				)
				(2 (= newRoomNumber 67))
				(3 (= newRoomNumber 69))
			)
			(if forceBeamDestroyed (++ ortegaPostBeamRooms))
			(super newRoom: newRoomNumber)
		)
	)
)

(instance MyMy of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 66 5)
				(= rockSankInLava TRUE)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance PoleVault of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= inCartoon 1)
				(ego setMotion: MoveTo 229 206 self)
			)
			(1
				(ego
					view: 87
					setLoop: 0
					setCel: 0
					setMotion: 0
					setCycle: Forward
				)
				(Print 66 6)
				(= seconds 2)
			)
			(2
				(ego setMotion: MoveTo 195 172 self)
			)
			(3
				(ego setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(4
				(pole init: setCycle: EndLoop)
				(ego
					setLoop: 3
					setCel: 0
					posn: 177 152
					setCycle: 0
					setMotion: JumpTo 108 143 self
				)
			)
			(5
				(if (ego has: iThermalDetonator)
					(HandsOff)
					(kaboom play:)
					(blast init: setCycle: EndLoop self)
					(ego dispose:)
				else
					(self changeState: 7)
				)
			)
			(6
				(Print 66 7)
				(EgoDead 0 0 16 20)
			)
			(7
				(ego
					view: 0
					setLoop: -1
					loop: 1
					setCycle: Walk
					setMotion: MoveTo 98 133 self
				)
			)
			(8
				(pole dispose:)
				(ego put: iMetalPole)
				(theGame changeScore: 20)
				(Print 66 8)
				(HandsOn)
				(= inCartoon 0)
				(client setScript: 0)
			)
		)
	)
)

(instance DeadMeat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(kaboom play:)
				(blast init: setCycle: EndLoop self)
				(ego dispose:)
			)
			(1
				(Print 66 9)
				(EgoDead 0 0 16 20)
			)
		)
	)
)

(instance pole of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 87
			setLoop: 2
			setCel: 0
			setPri: (ego priority?)
			posn: 177 172
			ignoreActors:
		)
	)
)

(instance blast of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 87
			setLoop: 4
			setCel: 0
			setPri: (+ (ego priority?) 1)
			posn: (ego x?) (ego y?)
			ignoreActors:
		)
	)
)

(instance rock1 of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 90
			setLoop: 1
			setCel: 0
			setPri: 6
			posn: 158 153
			ignoreActors:
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(
				(and
					(== (ego onControl: 1) 64)
					(not (curRoom script?))
				)
				(cond 
					((not (ego mover?)) (if (not local2) (= local2 1) (ego setCycle: Forward)))
					(local2 (= local2 0) (ego setCycle: Walk))
				)
				(if (not local0)
					(= local0 1)
					(quake play:)
					(self setCycle: Forward)
					(ego view: 95 setStep: 1 1)
				)
			)
			(local0
				(= local0 0)
				(self setCel: 0 setCycle: 0 stopUpd:)
				(if (and (not local1) (not (curRoom script?)))
					(ego
						view: 0
						setStep: 3 2
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
					(RedrawCast)
					(ego setLoop: -1)
				)
			)
		)
	)
)

(instance rock2 of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 90
			setLoop: 2
			setCel: 0
			setPri: 6
			posn: 162 170
			ignoreActors:
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(
				(and
					(== (ego onControl: 1) 128)
					(not (curRoom script?))
				)
				(cond 
					((not (ego mover?)) (if (not local3) (= local3 1) (ego setCycle: Forward)))
					(local3 (= local3 0) (ego setCycle: Walk))
				)
				(if (not local1)
					(= local1 1)
					(quake play:)
					(self setCycle: Forward)
					(ego view: 95 setStep: 1 1)
				)
				(if (and (ego has: iThermalDetonator) (not local6))
					(HandsOff)
					(= local6 2)
				)
			)
			(local1
				(= local1 0)
				(self setCel: 0 setCycle: 0 stopUpd:)
				(if (and (not local0) (not (curRoom script?)))
					(ego
						view: 0
						setStep: 3 2
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
					(RedrawCast)
					(if (not steppedOnUnstableRock) (Print 66 10) (= steppedOnUnstableRock TRUE))
					(ego setLoop: -1)
				)
			)
		)
	)
)

(instance kaboom of Sound
	(properties
		number 33
		priority 3
	)
)

(instance quake of Sound
	(properties
		number 78
		priority 2
	)
)
