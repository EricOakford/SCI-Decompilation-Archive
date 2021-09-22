;;; Sierra Script 1.0 - (do not remove this comment)
(script# 520)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm520 0
)

(local
	drownCycles
	[msgBuf 50]
	[titleBuf 22]
)
(instance rm520 of Room
	(properties
		picture 520
		east 523
		south 510
	)
	
	(method (init)
		(Load VIEW 511)
		(Load VIEW 812)
		(Load VIEW 813)
		(Load VIEW 521)
		(Load SOUND 4)
		(Load SOUND 6)
		(if (ego has: iPantyhose)
			(Load VIEW 15)
		)
		(super init:)
		(self setScript: RoomScript)
		(aRock1 init:)
		(aRock2 init:)
		(aRock3 init:)
		(aRock4 init:)
		(aRock5 init:)
		(cond 
			((== currentStatus egoSWIMMING)
				(ego posn: 69 188)
				(= currentStatus egoDROWNING)
				(RoomScript changeState: 1)
			)
			((== prevRoomNum 523)
				(NormalEgo loopW))
			(else
				(ego posn: 181 188)
				(NormalEgo loopN)
			)
		)
		(ego init:)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(cond 
			((and (& (ego onControl: origin) cLBLUE) (== currentStatus egoNORMAL))
				(self changeState: 1)
			)
			((and (== currentStatus egoDROWNING) (< 8 (++ drownCycles)))
				(= drownCycles 0)
				(ego
					setLoop: (+ (Random 0 1) (* 2 (< (ego y?) 107)))
				)
			)
			((and (& (ego onControl:) cBLUE) (== currentStatus egoNORMAL))
				(self changeState: 12)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(if (!= currentStatus egoDROWNING)
					(= currentStatus egoDROWNING)
					(soundFX stop:)
					(music number: 6 loop: -1 play:)
				)
				(HandsOff)
				(ego
					view: 812
					setLoop: 0
					setStep: 1 3
					setCycle: Forward
					setPri: 8
					illegalBits: 0
				)
				(if (> (ego y?) 137)
					(ego posn: 58 (ego y?) setMotion: MoveTo 58 137 self)
				else
					(ego posn: (- (ego x?) 20) (ego y?))
					(self cue:)
				)
			)
			(2
				(if (> (ego y?) 100)
					(ego setMotion: MoveTo 86 100 self)
				else
					(self cue:)
				)
			)
			(3
				(ego setMotion: MoveTo 105 83 self)
			)
			(4
				(= currentStatus egoDYING)
				(soundFX stop:)
				(music number: 4 loop: 1 play:)
				(ego setPri: 2 setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(5
				(ego setStep: 1 8 setMotion: MoveTo (ego x?) 180 self)
			)
			(6
				(cls)
				(if debugging
					(NormalEgo)
					(= currentStatus egoNORMAL)
					(ego posn: 178 100)
				else
					(theGame setScript: (ScriptID DYING))
					((ScriptID DYING)
						caller: 522
						register: (Format @msgBuf 520 30)
						next: (Format @titleBuf 520 31)
					)
				)
			)
			(7
				(HandsOff)
				(= currentStatus egoDRINKWATER)
				(ego
					view: 511
					cycleSpeed: 2
					setLoop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(8
				(ego setLoop: 1 cel: 0 setCycle: Forward)
				(= cycles
					(* 2 (ego cycleSpeed?) 4 (- (NumCels ego) 1))
				)
			)
			(9
				(ego setLoop: 0 setCel: 255 setCycle: BegLoop self)
			)
			(10
				(= seconds 3)
			)
			(11
				(theGame changeScore: 42)
				(Bset fDrankRiverWater)
				(NormalEgo loopW)
				(= currentStatus egoNORMAL)
				(Print 520 32)
			)
			(12
				(HandsOff)
				(Print
					(Format @msgBuf 520 33 expletive)
					#at -1 10
					#dispose
				)
				(= currentStatus egoFALLING)
				(soundFX stop:)
				(music number: 4 loop: 1 play:)
				(ego
					view: 813
					setLoop: 0
					cel: 0
					illegalBits: 0
					ignoreActors:
					setPri: 2
					setCycle: EndLoop self
				)
			)
			(13
				(ego setStep: 1 8 setMotion: MoveTo (ego x?) 188 self)
				(if debugging
					(= state 5)
				)
			)
			(14
				(curRoom newRoom: 525)
			)
			(15
				(HandsOff)
				(Ok)
				(theGame changeScore: 15)
				(Bset fRemovedPantyhose)
				(ego
					view: 521
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(16
				(ego cel: 3 setCycle: BegLoop self)
			)
			(17
				(NormalEgo)
			)
			(18
				(HandsOff)
				(Ok)
				(theGame changeScore: 40)
				(= currentStatus egoREMOVEPANTYHOSE)
				(ego illegalBits: 0 setMotion: MoveTo 213 104 self)
			)
			(19
				(ego
					view: 521
					loop: 1
					cel: 0
					cycleSpeed: 1
					setPri: 10
					setCycle: EndLoop self
				)
			)
			(20
				(= cycles 11)
			)
			(21
				(ego loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(22
				(= cycles 11)
			)
			(23
				(Print 520 34 #icon 15 0 0 #at -1 10)
				(ego setLoop: 3 cel: 0 posn: 212 71 setCycle: EndLoop self)
			)
			(24
				(ego setPri: 2)
				(= cycles 5)
			)
			(25
				(ego
					setStep: 1 1
					setMotion: MoveTo (ego x?) (+ 30 (ego y?)) self
				)
			)
			(26
				(Print 520 35 #at -1 10)
				(curRoom newRoom: 525)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((or (Said 'get/drink,water') (Said 'drink') (Said 'drink/water'))
				(cond 
					((Btst fDrankRiverWater)
						(Print 520 0)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (& (ego onControl:) cLBLUE))
						(NotClose)
					)
					(else
						(self changeState: 7)
					)
				)
			)
			((Said 'use,attach/bra')
				(cond 
					((not (ego has: iBra))
						(DontHave)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					(else
						(Print 520 1)
					)
				)
			)
			((Said 'use/hose')
				(if (not (ego has: iPantyhose))
					(DontHave)
				else
					(Print 520 2)
				)
			)
			((Said 'drain,(off<get),(get<off)/hose')
				(cond 
					((InRoom iPantyhose 484)
						(Print 520 3)
					)
					((InRoom iPantyhose -1)
						(DontHave)
					)
					((Btst fRemovedPantyhose)
						(Print 520 4)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					(else
						(self changeState: 15)
					)
				)
			)
			((Said '(backdrop<on),wear/hose')
				(cond 
					((InRoom iPantyhose 484)
						(Print 520 3)
					)
					((InRoom iPantyhose -1)
						(DontHave)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (Btst fRemovedPantyhose))
						(Print 520 5)
					)
					(else
						(Print 520 6)
						(theGame changeScore: -15)
						(Bclr fRemovedPantyhose)
					)
				)
			)
			((Said 'attach/hose>')
				(cond 
					((not (ego has: iPantyhose))
						(Print 520 7)
					)
					((not (Btst fRemovedPantyhose))
						(Print 520 8)
					)
					((Said '//noword')
						(Print 520 9)
					)
					((not (Said '//boulder'))
						(Print 520 10)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (& (ego onControl:) cRED))
						(Print 520 11)
					)
					(else
						(self changeState: 18)
					)
				)
				(event claimed: TRUE)
			)
			((Said 'look>')
				(cond 
					((Said '/palm')
						(Print 520 12)
					)
					((Said '/creek')
						(Print 520 13)
					)
					((Said '/boulder,boob')
						(if (& (ego onControl:) cRED)
							(Print 520 14)
						else
							(Print 520 15)
						)
					)
					((Said '/ledge,cliff,canyon')
						(Print 520 16)
					)
					((Said '/cascade,cascade,water')
						(Print 520 17)
						(Print 520 18 #at -1 144)
					)
					((Said '[/area]')
						(Print 520 19)
					)
				)
			)
			((Said '(up<climb),climb/boulder,arch')
				(Print 520 20)
			)
			((or (Said '(climb,go)<(down,above)') (Said 'decrease/i'))
				(Print 520 21)
			)
			((Said 'climb')
				(Print 520 22)
				(Print 520 23 #at -1 144)
			)
			((Said 'drag,grasp,get/bush,hemp')
				(Print 520 24)
			)
			((Said 'get,use/palm')
				(Print 520 25)
			)
			((Said '/bush')
				(Print 520 26)
			)
			((Said '/arch')
				(Print 520 27)
			)
			((Said '/flower')
				(Print 520 28)
			)
			((Said 'jump')
				(Print 520 29)
			)
		)
	)
)

(instance aRock1 of Prop
	(properties
		y 160
		x -18
		view 520
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward isExtra: TRUE ignoreActors:)
	)
)

(instance aRock2 of Prop
	(properties
		y 187
		x 56
		view 520
		loop 1
		cel 1
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward isExtra: TRUE ignoreActors:)
	)
)

(instance aRock3 of Prop
	(properties
		y 179
		x 37
		view 520
		loop 1
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward isExtra: TRUE ignoreActors:)
	)
)

(instance aRock4 of Prop
	(properties
		y 118
		x -5
		view 520
		loop 2
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward isExtra: TRUE ignoreActors:)
	)
)

(instance aRock5 of Prop
	(properties
		y 176
		x 12
		view 520
		loop 2
		cel 1
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward isExtra: TRUE ignoreActors:)
	)
)
