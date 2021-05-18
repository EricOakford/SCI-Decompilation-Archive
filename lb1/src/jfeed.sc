;;; Sierra Script 1.0 - (do not remove this comment)
(script# 239)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Jump)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	jfeed 0
)

(local
	dogState
	local1
	local2
	local3
)
(instance jfeed of Region
	
	(method (init)
		(super init:)
		(Load FONT 4)
		(LoadMany SCRIPT AVOIDER)
		(Load VIEW 520)
		(if (> currentAct 1)
			(= global155 17)
		)
		(if (ego has: iSoupBone)
			(Load VIEW 48)
			(Load SCRIPT JUMP)
		)
		(if (>= global155 2)
			(Dish init: stopUpd:)
		else
			(Dish init: hide:)
		)
		(= gBdoor Dish)
		(if (>= currentAct 1)
			(if (or (> global155 1) (> currentAct 1))
				(= global162 1)
				(= dogState 2)
				(Rover
					view: 520
					illegalBits: 0
					posn: 163 155
					loop: 2
					cel: 0
					ignoreActors: TRUE
					init:
					setScript: dogActions
					stopUpd:
				)
			else
				(Rover view: 520 posn: 340 160 init:)
			)
			(= gMySound Rover)
		)
		(if (and (< global155 2) (== currentAct 1))
			(LoadMany VIEW 440 445 526)
			(if (== global155 0)
				(Jeeves loop: 2 posn: 128 117)
			else
				(Jeeves init:)
			)
		)
		(if
			(and
				(> [global368 2] 1)
				(== global155 16)
				(== currentAct 1)
			)
			(= [global368 2] 90)
			(Jeeves view: 440 x: 300 y: 53 init:)
			(self setScript: (ScriptID 387 0))
		)
		(= gDoor Jeeves)
	)
	
	(method (doit)
		(if
			(and
				(& (ego onControl: origin) cBLACK)
				(< global155 2)
				(== currentAct 1)
				(not script)
			)
			(DisposeScript SAVE)
			(self setScript: (ScriptID 386 0))
		)
		(if
			(and
				(>= global155 2)
				(>= currentAct 1)
				(not (Rover script?))
			)
			(Rover setScript: dogActions)
		)
		(if (and (== [global368 2] 100) (== currentAct 1))
			(if (and (& (ego onControl: origin) cBLACK) (User controls?))
				(Jeeves view: 440 x: 235 y: 53)
				(self setScript: (ScriptID 387 0))
			else
				(= [global368 2] 110)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript JUMP)
		(DisposeScript AVOIDER)
	)
	
	(method (handleEvent event &tmp temp0)
		(DisposeScript SAVE)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'hold/bone[<beauregard]')
						(if (ego has: iSoupBone)
							(switch dogState
								(2
									(Print 239 0)
									(= local3 1)
								)
								(else
									(Print 239 1)
								)
							)
						else
							(DontHave)
						)
					)
					(
						(or
							(Said 'feed,deliver,drop,throw/bone')
							(Said 'feed,deliver,drop,throw/bone[/(beauregard,doghouse)<away,from]')
						)
						(if (Rover script?)
							(if (ego has: iSoupBone)
								(if (and (> (ego y?) 95) (> (ego x?) 45))
									(HandsOff)
									(self setScript: toss)
								else
									(Print 239 2)
								)
							else
								(DontHave)
							)
						else
							(Print 239 3)
						)
					)
					(
						(or
							(Said 'deliver/*/beauregard')
							(Said 'deliver/*<beauregard')
						)
						(Print 239 4)
					)
					(
						(or
							(Said 'get/back<bone[<from]')
							(Said 'get/*/beauregard')
							(Said 'get/bone')
						)
						(cond 
							((== dogState 3)
								(Print 239 5)
							)
							((== ((inventory at: iSoupBone) owner?) 12)
								(Print 239 6)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance toss of Script

	(method (doit)
		(super doit:)
		(if (and (== state 1) (== (ego cel?) 6))
			(ego put: iSoupBone 12)
			(Bone
				cycleSpeed: 1
				setLoop: 4
				setCycle: Forward
				moveSpeed: 2
				setMotion: JumpTo 293 155 self
				init:
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ego inRect: 285 150 300 159)
					(ego setMotion: MoveTo 270 145 self)
				else
					(= cycles 1)
				)
			)
			(1
				(DirLoop ego (GetAngle (ego x?) (ego y?) 293 155))
				(Ok)
				(if (ego inRect: 242 152 320 200)
					(ego setPri: 14)
				)
				(ego view: 48 cel: 0 setCycle: EndLoop)
				(Bone
					setPri: 13
					ignoreActors: TRUE
					illegalBits: 0
					posn:
						(switch (ego loop?)
							(0 (+ (ego x?) 11))
							(1 (- (ego x?) 11))
							(2 (- (ego x?) 7))
							(else
								(+ (ego x?) 4)
							)
						)
						(- (ego y?) 26)
				)
			)
			(2
				(DisposeScript JUMP)
				(Bone setCycle: 0 cel: 0 setPri: -1 stopUpd:)
				(ego view: 0 setLoop: -1 setPri: -1 setCycle: Walk)
				(= cycles 1)
			)
			(3
				(++ local2)
				(client setScript: 0)
			)
		)
	)
)

(instance dogActions of Script
	
	(method (doit)
		(super doit:)
		(cond 
			(local1
				(if (< state 5)
					(= state 6)
					(= cycles 1)
				)
			)
			(local2
				(= local2 0)
				(= state 1)
				(= cycles 2)
				(= dogState 4)
			)
			(local3
				(switch dogState
					(2 (= state 0) (= cycles 1))
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Rover
					view: 520
					loop: 2
					cel: 0
					setCycle: 0
					setAvoider: 0
					ignoreActors: TRUE
					stopUpd:
				)
				(= dogState 2)
			)
			(1
				(= local3 0)
				(Rover
					view: 527
					setLoop: 0
					cel: 0
					posn: 159 155
					setCycle: EndLoop
				)
				(= state -1)
				(= seconds 3)
			)
			(2
				(Rover loop: 6 posn: 159 155 setCycle: BegLoop self)
			)
			(3
				(Rover
					view: 520
					setLoop: 0
					ignoreActors: TRUE
					setCycle: Walk
					setMotion: MoveTo 194 155 self
				)
			)
			(4
				(Rover setMotion: MoveTo 275 155 self)
			)
			(5
				(Rover
					view: 527
					loop: 4
					cel: (- (NumCels Rover) 1)
					ignoreActors: 0
					setCycle: BegLoop self
				)
				(= global162 0)
			)
			(6
				(HandsOn)
				(= cycles 2)
			)
			(7
				(= dogState 3)
				(Bone dispose:)
				(Rover view: 522 loop: 4 setCycle: Forward)
				(= seconds 5)
			)
			(8
				(Rover setCycle: 0)
				(= seconds 5)
				(= state 6)
			)
		)
	)
)

(instance PetDog of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setAvoider: (Avoider new:)
					setMotion: MoveTo (+ (Rover x?) 26) (+ (Rover y?) 5) self
				)
			)
			(1
				(ego view: 22 loop: 0 setAvoider: 0 setCycle: EndLoop self)
			)
			(2
				(ego loop: 5 cel: 0 setCycle: EndLoop self)
			)
			(3
				(ego loop: 7 setCycle: Forward)
				(= seconds 3)
			)
			(4
				(ego
					loop: 0
					cel: (- (NumCels ego) 1)
					setCycle: BegLoop self
				)
			)
			(5
				(HandsOn)
				(ego view: 0 loop: 1 setCycle: Walk)
				(client setScript: 0)
			)
		)
	)
)

(instance Dish of Prop
	(properties
		y 159
		x 206
		view 112
		loop 2
		cel 2
	)
	
	(method (handleEvent event)
		(cond 
			((or (MousedOn self event shiftDown) (Said 'examine/dish'))
				(event claimed: TRUE)
				(Print 239 7)
			)
			((Said 'get/dish')
				(Print 239 8)
			)
		)
	)
)

(instance Rover of Actor
	
	(method (handleEvent event)
		(cond 
			((or (MousedOn self event shiftDown) (Said 'examine/beauregard'))
				(switch dogState
					(0 (Print 239 9))
					(1 (Print 239 10))
					(2 (Print 239 11))
					(3 (Print 239 12))
					(4 (Print 239 13))
				)
				(event claimed: TRUE)
			)
			((Said '/beauregard>')
				(cond 
					((Said 'get,move,drag,get')
						(switch dogState
							(2 (Print 239 14))
							(else  (Print 239 15))
						)
					)
					((Said 'pat')
						(switch dogState
							(0 (Print 239 16))
							(1 (Print 239 16))
							(3 (Print 239 16))
							(2
								(if (< (ego distanceTo: Rover) 40)
									(ego setScript: PetDog)
								else
									(NotClose)
								)
							)
						)
					)
					((Said 'converse') (Print 239 17))
					((Said 'call,call') (Print 239 18))
					((Said 'kill') (Print 239 19))
					((Said 'kiss') (Print 239 20))
					((Said 'feed')
						(switch dogState
							(0 (Print 239 21))
							(1 (Print 239 1))
							(2 (Print 239 22))
							(3 (Print 239 23))
						)
					)
				)
			)
		)
	)
)

(instance Jeeves of Actor
	(properties
		y 161
		x 256
		view 451
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'converse,ask,tell/butler')
				(Print 239 24)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/butler'))
				(if (& global207 $0400)
					(Print 239 25)
				else
					(|= global207 $0400)
					(= theTalker talkJEEVES)
					(Say 0 239 26)
				)
				(event claimed: TRUE)
			)
		)
	)
)

(instance Bone of Actor
	(properties
		y 159
		x 176
		view 48
		loop 4
	)
)
