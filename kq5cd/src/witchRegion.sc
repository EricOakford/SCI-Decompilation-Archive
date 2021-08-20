;;; Sierra Script 1.0 - (do not remove this comment)
(script# 200)
(include game.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use PolyPath)
(use Block)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	witchRegion 0
	poofOutScript 1
)

(local
	bigView
	local1
	local2
	local3
	local4 = [1010 100 62 4 11 32 30 32 35]
	local13 = [1 980 0 981 1 982 0 983 1 984 0 985 1 986]
	local29 = [1003 130 62 4 11 25 23 31 31]
)
(procedure (localproc_0012)
	(= [local4 1] (Min (Max 0 (- (witch x?) 40)) 239))
	(= [local29 1] (Min (Max 0 (- (ego x?) 40)) 239))
)

(instance witchRegion of Region
	
	(method (init)
		(super init:)
		(if (== curRoomNum 22)
			(LoadMany VIEW 447 446 454 440 442)
			(= bigView 0)
		else
			(= bigView 1)
		)
		(if (== prevRoomNum 208)
			(self setScript: witchMsgScript)
		)
		(if
			(or
				(and
					(== curRoomNum 22)
					(!= ((inventory at: iBottle) owner?) 200)
				)
				(and
					(!= curRoomNum 19)
					(or
						(and (Btst fWitchCastSpell) (!= ((inventory at: iBottle) owner?) 200))
						(and (not (Btst fWitchCastSpell)) (< (Random 1 1000) 500))
					)
				)
			)
			(witch view: (+ 440 bigView) init:)
			(Bset fWitchHere)
		)
		(cond 
			((== curRoomNum 22)
				(theMusic number: 41 loop: -1 vol: 127 playBed:)
			)
			((Btst fWitchHere)
				(if (!= (theMusic number?) 20)
					(theMusic number: 20 loop: -1 vol: 127 playBed:)
				)
			)
			(
				(and
					(== curRoomNum 24)
					(== ((inventory at: iBottle) owner?) 200)
					(not (Btst fCaughtElf))
				)
				(theMusic number: 28 loop: -1 vol: 127 playBed:)
			)
			(
				(and
					(== curRoomNum 21)
					(== ((inventory at: 9) owner?) 21)
				)
				(theMusic number: 34 loop: -1 vol: 127 playBed:)
			)
			((or (!= (theMusic number?) 4) (== curRoomNum 19))
				(theMusic number: 4 loop: -1 vol: 127 playBed:)
			)
		)
	)
	
	(method (doit)
		(if script
			(script doit:)
		)
	)
	
	(method (dispose)
		(Bclr fWitchHere)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
	
	(method (newRoom)
		(super newRoom:)
	)
)

(instance witchMsgScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(localproc_0012)
				(proc762_1 @local29 990 self)
			)
			(2 (client setScript: 0))
		)
	)
)

(instance poofOutScript of Script

	(method (changeState newState &tmp egoX egoY)
		(switch (= state newState)
			(0
				(HandsOff)
				(= egoX (ego x?))
				(= egoY (ego y?))
				(switch register
					(1 (-= egoY 0))
					(3 (+= egoY 60))
					(2 (+= egoX 20))
					(4 (-= egoX 20))
				)
				(ego ignoreActors: TRUE setMotion: MoveTo egoX egoY self)
				(if (Btst fWitchHere)
					(witch
						view: (+ 442 bigView)
						loop: 0
						cel: 6
						cycleSpeed: 2
						setCycle: BegLoop
					)
					(theAudio number: 8095 loop: 1 play:)
				)
			)
			(1
				(if (Btst fWitchHere)
					(switch register
						(1 (= cycles 25))
						(3 (= cycles 1))
						(else  (= cycles 15))
					)
				else
					(= cycles 1)
				)
			)
			(2
				(curRoom newRoom: (curRoom edgeToRoom: register))
				(HandsOn)
			)
		)
	)
)

(instance poofIn of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(witch
					view: (+ 442 bigView)
					loop: 0
					cel: 0
					cycleSpeed: 2
					setCycle: CycleTo 1 1 self
				)
			)
			(1
				(witch setCycle: EndLoop self)
				(theAudio number: 8095 loop: 1 play:)
			)
			(2
				(witch view: (+ 440 bigView))
				(Face witch ego 5)
				(self cue:)
			)
			(3
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance zapHim of Script

	(method (doit)
		(super doit:)
		(if
			(and
				(!= (theAudio number?) 8096)
				(!= (theMusic3 number?) 96)
				(== state 2)
				(== (witch cel?) 2)
			)
			(fireball
				init:
				x: (+
					(witch x?)
					(switch (witch loop?)
						(0 10)
						(1 -10)
						(else  0)
					)
				)
				y: (- (witch y?) 25)
				setLoop: (witch loop?)
				cel: 0
				setCycle: Walk
				setStep: 5 5
				setMotion: MoveTo (ego x?) (- (ego y?) 25) self
			)
			(theAudio number: 8096 loop: 1 play:)
		)
	)
	
	(method (changeState newState &tmp ang1To2)
		(switch (= state newState)
			(0
				(= ang1To2
					(GetAngle (witch x?) (witch y?) (ego x?) (ego y?))
				)
				(witch
					view: (+ 442 bigView)
					loop: (cond 
						((< ang1To2 135) 0)
						((< ang1To2 225) 2)
						(else 1)
					)
					cel: 0
					cycleSpeed: 2
					setCycle: CycleTo 1 1 self
				)
			)
			(1
				(witch setCycle: EndLoop self)
				(theAudio number: 8095 loop: 1 play:)
			)
			(2
				(Bset fWitchCastSpell)
				(= ang1To2
					(GetAngle (witch x?) (witch y?) (ego x?) (ego y?))
				)
				(witch
					view: (+ 446 bigView)
					loop:
						(cond 
							((< ang1To2 45) 3)
							((< ang1To2 135) 0)
							((< ang1To2 225) 2)
							((< ang1To2 315) 1)
							(else 3)
						)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(witch setCycle: EndLoop)
			)
			(4
				(if (and (ego has: iAmulet) (Btst fWearingAmulet))
					(fireball loop: 9 cel: 0 setCycle: EndLoop self)
					(theAudio number: 8082 loop: 1 play:)
					(+= state 4)
				else
					(fireball dispose:)
					((ego head?) hide:)
					(ego
						normal: 0
						view: (+ 454 bigView)
						cel: 0
						setCycle: EndLoop self
					)
					(theMusic3 number: 79 priority: 15 loop: 1 vol: 127 play:)
					(theMusic stop:)
					(theMusic2 stop:)
					(theMusic4 stop:)
				)
			)
			(5 (= cycles 1))
			(6
				(theAudio number: 9117 loop: 1 play: self)
			)
			(7
				(if (not register)
					(-= state 2)
					(++ register)
					(= seconds 2)
				else
					(= seconds 3)
				)
			)
			(8
				(= deathMessage 852)
				(EgoDead 249)
			)
			(9
				(fireball dispose:)
				(= cycles 1)
			)
			(10
				(SpeakAudio 853)
				(= cycles 1)
			)
			(11
				(= register (witch loop?))
				(witch
					view: (+ 448 bigView)
					loop: (if (not register) 1 else 0)
					cel:
					(switch register
						(2 3)
						(3 0)
						(else  1)
					)
					setCycle: CycleTo 6 1 self
				)
			)
			(12
				(witch setCycle: EndLoop self)
				(theAudio number: 8083 loop: 1 play:)
			)
			(13
				(switch register
					(2 (= register 3))
					(3 (= register 0))
					(else  (= register 1))
				)
				(witch setCycle: CycleTo register -1 self)
			)
			(14
				(= local1 1)
				(= ang1To2
					(GetAngle (witch x?) (witch y?) (ego x?) (ego y?))
				)
				(witch
					view: (+ 440 bigView)
					loop:
						(cond 
							((< ang1To2 45) 3)
							((< ang1To2 135) 0)
							((< ang1To2 225) 2)
							((< ang1To2 315) 1)
							(else 3)
						)
				)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance talkToWitch of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(cls)
				(localproc_0012)
				(proc762_0 @local29 @local4 @local13 self)
			)
			(1
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance killTheBitch of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: iBottle 200)
				(SolvePuzzle 4)
				(HandsOff)
				(ego
					setMotion: PolyPath (- (witch x?) 25) (+ (witch y?) 5) self
				)
			)
			(1
				(ego
					setMotion: MoveTo (+ (ego x?) 1) (- (ego y?) 1) self
				)
			)
			(2
				(if (== curRoomNum 22)
					(ego view: 434 loop: 5)
				else
					(ego view: 408 loop: 1)
				)
				((ego head?) hide:)
				(ego normal: 0 cel: 0 cycleSpeed: 2 setCycle: CycleTo 3 1 self)
			)
			(3
				(if (== curRoomNum 22)
					(bottle
						view: 434
						loop: 6
						posn: (+ (ego x?) 8) (- (ego y?) 1)
						init:
					)
				else
					(bottle
						view: 414
						loop: 0
						posn: (+ (ego x?) 9) (- (ego y?) 3)
						init:
					)
				)
				(ego setCycle: EndLoop self)
			)
			(4
				(= globalCedric 200)
				(= gGEgoX (ego x?))
				(= gGEgoY (ego y?))
				(localproc_0012)
				(proc762_1 @local4 987 208 0)
			)
		)
	)
)

(instance fireball of Actor
	(properties
		view 452
		signal (| ignrAct ignrHrz)
		illegalBits $0000
	)
	
	(method (doit)
		(super doit:)
		(if
			(or
				(== curRoomNum 22)
				(and
					(== curRoomNum 25)
					(or (== prevRoomNum 19) (== prevRoomNum 26))
				)
			)
			(self setPri: 14)
		)
	)
)

(instance witchCage of Cage)

(instance witch of Actor
	(properties
		view 440
		signal ignrAct
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(switch curRoomNum
			(20 (self posn: 250 128))
			(21 (self posn: 159 160))
			(22
				(self
					posn: 143 146
					loop: 2
					observeBlocks: witchCage
					setStep: 1 1
					cel: 0
				)
			)
			(24 (self posn: 188 111))
			(25 (self posn: 156 126))
			(26 (self posn: 243 121))
		)
		(HandsOff)
		(if (Btst fWitchCastSpell)
			(= local1 1)
			(HandsOff)
			(curRoom setScript: poofIn)
		else
			(HandsOff)
			(curRoom setScript: zapHim)
		)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				local1
				(!= (curRoom script?) (ScriptID 200 1))
				(!= (curRoom script?) poofIn)
			)
			(self
				setLoop: 0
				cel:
					(Min
						7
						(/
							(*
								(GetAngle (self x?) (self y?) (ego x?) (ego y?))
								4
							)
							180
						)
					)
			)
			(if
				(and
					(== curRoomNum 22)
					(!= (self script?) killTheBitch)
					(not local2)
					(< (ego distanceTo: witch) 5)
				)
				(++ local2)
				(localproc_0012)
				(proc762_1 @local4 988)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) userEvent))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(if (== curRoomNum 22)
						(SpeakAudio 854)
					else
						(SpeakAudio 855)
					)
					(event claimed: TRUE)
				)
				(verbDo
					(if (and (ego has: iAmulet) (Btst fWitchCastSpell))
						(SpeakAudio 856)
					)
					(event claimed: TRUE)
				)
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(iBottle
							(event claimed: TRUE)
							(self setScript: killTheBitch)
						)
						(iWand
							(event claimed: FALSE)
						)
						(iAmulet
							(event claimed: TRUE)
							(SpeakAudio 857)
						)
						(else 
							(localproc_0012)
							(proc762_1 @local4 989)
							(event claimed: TRUE)
						)
					)
				)
				(verbTalk
					(if (Btst fTalkedToWitch)
						(if (not (self script?))
							(self setScript: talkToTheBitch)
						)
						(event claimed: TRUE)
					else
						(Bset fTalkedToWitch)
						(HandsOff)
						(self setScript: talkToWitch)
						(event claimed: TRUE)
					)
				)
			)
		)
	)
)

(instance talkToTheBitch of Script
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (SpeakAudio 858 self))
			(1
				(= temp0 (Random 4001 4005))
				(SpeakAudio temp0 self)
			)
			(2 (self dispose:))
		)
	)
)

(instance bottle of View
	(properties
		signal notUpd
	)
)
