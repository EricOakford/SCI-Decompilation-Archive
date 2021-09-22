;;; Sierra Script 1.0 - (do not remove this comment)
(script# 330)
(include game.sh)
(use Main)
(use Intrface)
(use Jump)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm330 0
)
(synonyms
	(man man dale)
)

(local
	local0
	local1
	local2
	theDrinker
	local4
	local5
)
(instance rm330 of Room
	(properties
		picture 330
		east 230
	)
	
	(method (init)
		(Bset fBeenInChipAndDales)
		(= currentStatus 17)
		(Load VIEW 330)
		(Load VIEW 333)
		(Load VIEW 332)
		(Load VIEW 337)
		(Load VIEW 336)
		(Load VIEW 14)
		(Load SOUND 330)
		(Load SOUND 331)
		(Load SOUND 332)
		(Load SOUND 10)
		(Load SCRIPT JUMP)
		(Load SCRIPT REVERSE)
		(super init:)
		(addToPics add: atpPchair add: atpDchair doit:)
		(aDale init:)
		(aClothes init:)
		(aDrinker1 init:)
		(aDrinker2 init:)
		(aDrinker3 init:)
		(aDrinker4 init:)
		(aCurtain init:)
		(aPanties init:)
		(self setScript: RoomScript)
		(if (== prevRoomNum 335)
			(HandsOff)
			(ego
				view: 332
				setLoop: 4
				setCel: 255
				posn: 281 140
				illegalBits: 0
				init:
			)
			(RoomScript changeState: 3)
			(aDale
				view: 337
				setLoop: 4
				setCel: 255
				posn: 283 121
				cycleSpeed: 1
			)
			(DaleScript changeState: 24)
		else
			(NormalEgo)
			(ego
				view: 332
				loop: 1
				posn: 317 129
				baseSetter: squareBase
				init:
			)
		)
	)
)

(instance RoomScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 281 140 self
				)
			)
			(2
				(ego setLoop: 4 setPri: 3 setCel: 0 setCycle: EndLoop self)
			)
			(3
				(User canInput: TRUE)
				(= currentStatus egoSITTING)
				(if local1
					(= cycles 0)
					(= seconds 3)
				else
					(= local1 1)
					(DaleScript changeState: 1)
				)
			)
			(4
				(if (== local2 2)
					(ego loop: 5 setCycle: Forward)
					(= cycles (Random 11 33))
				else
					(self changeState: 6)
				)
			)
			(5
				(ego setLoop: 4 setCel: 255)
				(= cycles (Random 11 33))
			)
			(6
				(ego setLoop: 6 cel: 0 setCycle: EndLoop self)
			)
			(7
				(= cycles (Random 33 66))
			)
			(8
				(ego setCycle: BegLoop self)
				(= state 3)
			)
			(9
				(HandsOff)
				(= cycles (= seconds 0))
				(ego view: 332 setLoop: 4 setCel: 255 setCycle: BegLoop self)
			)
			(10
				(ego
					setCycle: Walk
					setLoop: 1
					setMotion: MoveTo 282 132 self
				)
			)
			(11
				(= currentStatus egoSITTING)
				(NormalEgo loopW 332)
				(ego baseSetter: squareBase)
			)
			(12
				(Ok)
				(Print 330 38)
				(= cycles (= seconds 0))
				(HandsOff)
				(ego
					view: 336
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(13
				(ego setLoop: 1 cel: 0 setCycle: Forward)
				(= cycles 15)
			)
			(14
				(ego cycleSpeed: 0)
				(= cycles 15)
			)
			(15
				(= cycles 10)
			)
			(16
				(Print 330 39 #icon 14 0 0 #at -1 10)
				(ego cycleSpeed: 1 setLoop: 2 cel: 0 setCycle: EndLoop)
				(PutInRoom iPanties)
				(aPanties
					posn: (ego x?) (ego y?)
					setMotion: JumpTo 42 96 self
				)
			)
			(17
				(Print 330 40)
				(= cycles (= seconds 0))
				(aPanties stopUpd:)
				(theGame changeScore: 100)
				(User canInput: TRUE)
				(ego view: 332)
				(if (== currentStatus egoSITTING)
					(self changeState: 2)
				else
					(NormalEgo 1 332)
					(ego baseSetter: squareBase)
				)
			)
			(18
				(Ok)
				(HandsOff)
				(= currentStatus egoSITTING)
				(if (not (Btst fMetDale))
					(Print 330 41)
					(= seconds 3)
				else
					(self cue:)
				)
			)
			(19
				(if (not (Btst fMetDale))
					(Bset fMetDale)
					(theGame changeScore: 1)
					(Print 330 42)
				)
				(curRoom newRoom: 335)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if local2
			(cond 
				((Said 'caress,get,grab,caress/man')
					(Print 330 0)
				)
				((Said 'applaud')
					(Print 330 1)
					(if (== currentStatus egoSITTING)
						(self changeState: 4)
					)
				)
				((Said '(get<off),drain/panties')
					(Print 330 2)
				)
				((Said 'give,throw/panties>')
					(cond 
						((not (ego has: iPanties))
							(DontHave)
						)
						((Said '/[/noword]')
							(Print 330 3)
						)
						((not (Said '//backstage,man'))
							(Print 330 4)
						)
						((!= local2 2)
							(Print 330 5)
						)
						(else
							(self changeState: 12)
						)
					)
					(event claimed: TRUE)
				)
				((Said 'look/man,eye')
					(cond 
						((== local2 0)
							(Print 330 6)
						)
						((< local2 6)
							(Print 330 7)
							(Print 330 8 #at -1 144)
						)
						((> local2 6)
							(Print 330 9)
						)
						((!= currentStatus egoSITTING)
							(Print 330 10)
						)
						(else
							(self changeState: 18)
						)
					)
				)
				((Said 'address/man')
					(switch local2
						(2
							(Print 330 11)
						)
						(3
							(Print 330 12)
						)
						(4
							(Print 330 13)
						)
						(5
							(Print 330 14)
							(= local5 1)
						)
						(1
							(Print 330 12)
						)
						(6
							(Print 330 15)
						)
						(else
							(Print 330 16)
						)
					)
				)
				((Said 'address')
					(Print 330 17)
				)
				(
					(and
						(== local2 6)
						(or
							(Said 'eat,eat,eat/man')
							(Said 'caress,caress,caress/man')
						)
					)
					(Print 330 18)
				)
			)
		)
		(cond 
			((and local0 (Said 'get,(up<pick)/cloth'))
				(Print 330 19)
			)
			((Said 'get,buy/drink,cup')
				(Print 330 20)
			)
			(
				(or
					(Said 'go,climb,(get<on)/backstage,buffet,barstool')
					(Said 'whistle,holler')
				)
				(Print 330 21)
			)
			((Said 'lie')
				(cond 
					((& (ego onControl:) cGREEN)
						(Print 330 22)
					)
					((not (& (ego onControl:) cBLUE))
						(NotClose)
					)
					((== currentStatus egoSITTING)
						(YouAre)
					)
					((!= currentStatus 17)
						(GoodIdea)
					)
					(else
						(Ok)
						(self changeState: 1)
					)
				)
			)
			(
				(or
					(Said 'nightstand,(get<off),(get<up),(nightstand<up)')
					(Said 'exit/barstool')
				)
				(cond 
					((== currentStatus 17)
						(YouAre)
					)
					((!= currentStatus egoSITTING)
						(GoodIdea)
					)
					((== local2 6)
						(Print 330 23)
					)
					(else
						(self changeState: 9)
					)
				)
			)
			((Said 'throw/buck,tip,buck')
				(Print 330 24)
			)
			((Said 'look,caress<below/buffet,barstool')
				(Print 330 25)
			)
			((Said 'look>')
				(cond 
					((and local0 (Said '/cloth,buffet'))
						(Print 330 26)
					)
					((Said '/backstage')
						(Print 330 27)
					)
					((Said '/barstool')
						(Print 330 28)
						(if (== currentStatus egoSITTING)
							(Print 330 29)
						)
						(if (== local2 6)
							(Print 330 30)
							(Print 330 31 #at -1 144)
						)
					)
					((Said '/burn')
						(Print 330 32)
					)
					((Said '/curtain')
						(if (aCurtain cel?)
							(Print 330 33)
						else
							(Print 330 34)
						)
					)
					((Said '/buffet,babe,man,cup,drink,couple')
						(Print 330 35)
					)
					((Said '[/area]')
						(Print 330 36)
						(Print 330 37
							#at -1 144
						)
					)
				)
			)
		)
	)
)

(instance aDale of Actor
	(properties
		y 86
		x -86
		view 333
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward setScript: DaleScript)
	)
)

(instance DaleScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(= seconds (Random 2 5))
			)
			(2
				(music number: 10 loop: -1 play:)
				(Print 330 43)
				(Print 330 44 #at -1 144)
				(aCurtain setCycle: EndLoop self)
			)
			(3
				(= saveSpeed (theGame setSpeed: 6))
				(aCurtain stopUpd:)
				(music number: 330 loop: -1 play:)
				(aDale setLoop: 0 setMotion: MoveTo 89 86 self)
				(= local2 1)
			)
			(4
				(aCurtain setCycle: BegLoop self)
				(aDale setLoop: 1 setCel: 0)
				(= local2 2)
			)
			(5
				(aCurtain stopUpd:)
				(aDale cycleSpeed: 1 setCycle: EndLoop self)
			)
			(6
				(aDale setLoop: 2 setCel: 0 setCycle: CycleTo 6 1 self)
			)
			(7
				(aDale setCycle: EndLoop)
				(aClothes posn: 105 83 setMotion: JumpTo 248 133 self)
			)
			(8
				(aClothes stopUpd:)
				(= local0 1)
				(= cycles 20)
			)
			(9
				(aDale
					cycleSpeed: 1
					setLoop: 3
					setCel: 0
					setCycle: EndLoop self
				)
				(= local4 0)
			)
			(10
				(aDale setCycle: Forward setLoop: 4)
				(= cycles 22)
			)
			(11
				(if (> 3 (++ local4))
					(= state 9)
				)
				(aDale
					setLoop: 5
					setMotion: MoveTo (Random 63 68) (Random 110 110)
				)
				(= cycles (Random 11 22))
			)
			(12
				(aDale setMotion: MoveTo 89 86 self)
			)
			(13
				(aDale setLoop: 6 cel: 0 setCycle: EndLoop)
				(music number: 331 loop: 1 play:)
				(= cycles 33)
			)
			(14
				(Print 330 45 #at -1 10)
				(Print 330 46 #at -1 10)
				(= cycles (Random 11 44))
			)
			(15
				(aCurtain setCycle: EndLoop)
				(aDale setCycle: CycleTo 8 -1)
				(music number: 330 play:)
				(= cycles 22)
			)
			(16
				(aCurtain stopUpd:)
				(aDale
					cycleSpeed: 0
					setLoop: 5
					setCycle: Forward
					setMotion: MoveTo 42 86 self
				)
				(= local2 3)
			)
			(17
				(if (InRoom iPanties 330)
					(aPanties hide:)
					((Inventory at: iPanties) owner: 335)
				)
				(aDale setMotion: MoveTo -40 86 self)
			)
			(18
				(aDale stopUpd:)
				(aCurtain setCycle: BegLoop)
				(music fade:)
				(= local2 4)
				(= seconds 5)
			)
			(19
				(theGame setSpeed: saveSpeed)
				(aCurtain dispose:)
				(music stop: number: 332 loop: musicLoop play:)
				(= seconds (Random 7 15))
			)
			(20
				(= local2 5)
				(aDale
					view: 337
					setLoop: -1
					setCycle: Walk
					ignoreActors: 0
					illegalBits: 0
					posn: 245 -10
					baseSetter: squareBase
					setMotion: MoveTo 316 129 self
				)
				(= cycles 20)
			)
			(21
				(Print 330 47)
			)
			(22
				(if (or local5 (InRoom 14 335))
					(= local2 6)
					(aDale
						ignoreActors:
						illegalBits: 0
						setMotion: MoveTo 283 121 self
					)
					(if (InRoom iPanties 335)
						(Print 330 48)
					else
						(Print 330 49)
					)
				else
					(self changeState: 26)
				)
			)
			(23
				(aDale
					posn: 283 121
					cycleSpeed: 1
					setLoop: 4
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(24
				(= seconds 11)
			)
			(25
				(if (!= currentStatus egoSITTING)
					(= seconds 5)
					(-- state)
				else
					(Print 330 50)
					(= local2 5)
					(aDale setCycle: BegLoop self)
				)
			)
			(26
				(aDale
					cycleSpeed: 0
					setStep: 3 2
					setCycle: Walk
					ignoreActors: 0
					illegalBits: 0
					setLoop: -1
					baseSetter: squareBase
					setMotion: MoveTo 333 143 self
				)
			)
			(27
				(= local2 7)
				(aDale dispose:)
			)
		)
	)
)

(instance aDrinker1 of Prop
	(properties
		y 8
		x 221
		view 330
		loop 2
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: stopUpd:)
	)
)

(instance aDrinker2 of Prop
	(properties
		y 44
		x 239
		view 330
		loop 3
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: stopUpd:)
	)
)

(instance aDrinker3 of Prop
	(properties
		y 77
		x 225
		view 330
		loop 4
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: stopUpd:)
	)
)

(instance aDrinker4 of Prop
	(properties
		y 166
		x 264
		view 330
		loop 5
	)
	
	(method (init)
		(super init:)
		(self setScript: drinkerScript ignoreActors: stopUpd:)
	)
)

(instance drinkerScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 2 5))
			)
			(1
				(switch (Random 1 4)
					(1 (= theDrinker aDrinker1))
					(2 (= theDrinker aDrinker2))
					(3 (= theDrinker aDrinker3))
					(4 (= theDrinker aDrinker4))
				)
				(theDrinker setCycle: EndLoop self)
			)
			(2
				(= cycles (Random 15 29))
			)
			(3
				(theDrinker setCycle: BegLoop self)
			)
			(4
				(self changeState: 0)
			)
		)
	)
)

(instance atpPchair of PicView
	(properties
		y 140
		x 281
		view 330
		loop 1
		priority 1
		signal ignrAct
	)
)

(instance atpDchair of PicView
	(properties
		y 121
		x 281
		view 330
		loop 1
		cel 1
		priority 1
		signal ignrAct
	)
)

(instance aCurtain of Prop
	(properties
		y 88
		view 330
		cycleSpeed 2
	)
	
	(method (init)
		(super init:)
		(self setPri: 3 ignoreActors:)
	)
)

(instance aPanties of Actor
	(properties
		y 1142
		x 283
		view 336
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 3
			setPri: 15
			setCycle: Forward
			setStep: 10 6
			ignoreActors:
		)
	)
)

(instance aClothes of Actor
	(properties
		y 1083
		x 105
		view 333
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 7
			setPri: 15
			setCycle: Forward
			setStep: 5 5
			ignoreActors:
		)
	)
)

(instance squareBase of Code
	(method (doit theActor)
		(theActor brBottom: (+ (theActor y?) 5))
		(theActor brTop: (- (theActor brBottom?) 9))
		(theActor brLeft: (- (theActor x?) 8))
		(theActor brRight: (+ (theActor x?) 8))
	)
)
