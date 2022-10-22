;;; Sierra Script 1.0 - (do not remove this comment)
(script# 340)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm340 0
)

(local
	[local0 45]
	jokeNum
	theDrinker
	egoSittingLoop
	comedianOnStage
	[wantToSayStr 30]
	[ethnic1Str 10]
	[ethnic2Str 10]
	[ethnic3Str 10]
	local109
	local110
	local111
)
(instance rm340 of Room
	(properties
		picture 340
		horizon 1
		south 240
	)
	
	(method (init)
		(Load TEXT 341)
		(Load VIEW 705)
		(Load VIEW 344)
		(LoadMany SOUND 21 22 23 24 25 26 27 10 340 341)
		(= currentStatus egoINCOMEDYCLUB)
		(super init:)
		(addToPics
			add: atpChair
			add: atpManUR
			add: atpManLR
			add: atpManUL_Bottom
			add: atpLadyLR_Bottom
			add: atpLadyUL_Bottom
			doit:
		)
		(aManUL_Top init:)
		(aLadyUL_Top init:)
		(aLadyLR_Top init:)
		(aDrummer init:)
		(aComic init:)
		(aSign init:)
		(if (and (InRoom iWineBottle) (ego has: iPenthouseKey))
			(aBottle setPri: 10 ignoreActors: init:)
		)
		(self setScript: RoomScript)
		(NormalEgo 3 (+ 705 larryBuffed))
		(ego posn: 159 188 init:)
		(if (Btst fAlAndBill)
			(aAl loop: 5 cel: 4 init: stopUpd:)
			(aBill loop: 4 cel: 4 init: stopUpd:)
		else
			(aAlTop init:)
			(aBillTop init:)
			(aAl init:)
			(aBill init:)
		)
	)
	
	(method (newRoom n)
		(if comedianOnStage
			(Print 340 0)
		)
		(= currentStatus egoNORMAL)
		(super newRoom: n)
	)
)

(instance RoomScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(Ok)
				(HandsOff)
				(if (< (ego x?) 165)
					(ego illegalBits: 0 setMotion: MoveTo 153 145 self)
					(= egoSittingLoop 4)
				else
					(ego illegalBits: 0 setMotion: MoveTo 176 145 self)
					(= egoSittingLoop 5)
				)
			)
			(2
				(= cycles (= seconds 0))
				(ego
					view: 705
					loop: egoSittingLoop
					cel: 0
					illegalBits: 0
					setCycle: EndLoop self
				)
			)
			(3
				(if (== (ComicScript state?) 0)
					(ComicScript changeState: 1)
				)
				(User canInput: TRUE)
				(= currentStatus egoSITTING)
			)
			(4
				(ego setCycle: BegLoop self)
			)
			(5
				(= currentStatus egoINCOMEDYCLUB)
				(NormalEgo loopN (+ 705 larryBuffed))
				(if comedianOnStage
					(Print 340 39)
				)
			)
			(6
				(HandsOff)
				(= wantToSayStr 0)
				(while (> 5 (StrLen @wantToSayStr))
					(GetInput @wantToSayStr 50
						{Just say what you want to say:}
						#title {Author Interface}
					)
				)
				(aAlTop loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(7
				(aAlTop loop: 3 setCycle: Forward)
				(= seconds 3)
			)
			(8
				(Printf 340 40 @wantToSayStr)
				(aAlTop loop: 2 setCel: 255 setCycle: BegLoop self)
			)
			(9
				(aBillTop show:)
				(= seconds 3)
			)
			(10
				(Printf 340 41 @wantToSayStr)
				(= seconds 3)
			)
			(11
				(aBillTop hide:)
				(aAlTop setCycle: EndLoop self)
			)
			(12
				(aAlTop loop: 3 setCycle: Forward)
				(= seconds 2)
			)
			(13
				(Printf 340 42 @wantToSayStr)
				(aAl dispose:)
				(aBill dispose:)
				(aAlTop
					posn: 99 191
					loop: 5
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(aBillTop
					posn: 70 190
					loop: 4
					cel: 0
					cycleSpeed: 1
					show:
					setCycle: EndLoop self
				)
			)
			(14
				(HandsOn)
				(aAlTop stopUpd:)
				(aBillTop stopUpd:)
				(if (not (Btst fAlAndBill))
					(Bset fAlAndBill)
					(theGame changeScore: 5)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'boo')
				(if comedianOnStage
					(Print 340 1)
					(Printf 340 2 expletive)
					(Print 340 3 #at -1 144)
				else
					(Print 340 4)
				)
			)
			((Said 'applaud')
				(if comedianOnStage
					(Print 340 5)
				else
					(Print 340 4)
				)
			)
			((and debugging (Said 'test/joke'))
				(= jokeNum
					(GetNumber {First joke (from 1 to LAST JOKE):})
				)
				(Printf 340 6 jokeNum)
			)
			((Said 'address/comedian')
				(if comedianOnStage
					(Print 340 7)
					else (Print 340 8)
				)
			)
			((Said 'address/bob')
				(Print 340 9)
			)
			(
				(or
					(Said 'get/microphone')
					(Said 'nightstand,get,jump,climb<on/backstage')
				)
				(Print 340 10)
			)
			((Said 'address/man,bill,babe,al')
				(if
					(or
						(& (ego onControl:) cCYAN)
						(& (ego onControl:) cGREEN)
					)
					(if (Btst fAlAndBill)
						(Print 340 11)
					else
						(self changeState: 6)
					)
				else
					(Printf 340 12 currentEgo)
				)
			)
			((Said 'get/bottle,beer')
				(cond 
					((and (!= currentStatus egoINCOMEDYCLUB) (!= currentStatus egoSITTING))
						(GoodIdea)
					)
					((or (not (InRoom iWineBottle)) (not (ego has: iPenthouseKey)))
						(Print 340 11)
					)
					((not (& (ego onControl:) cLGREY))
						(NotClose)
					)
					(else
						(Ok)
						(aBottle dispose:)
						(theGame changeScore: 15)
						(Print 340 13)
						(ego get: iWineBottle)
					)
				)
			)
			(
				(or
					(Said 'nightstand,(get<off),(get<up),(nightstand<up)')
					(Said 'exit/barstool')
				)
				(cond 
					((== currentStatus egoINCOMEDYCLUB)
						(YouAre)
					)
					((!= currentStatus egoSITTING)
						(GoodIdea)
					)
					(else
						(self changeState: 4)
					)
				)
			)
			((Said 'lie')
				(cond 
					((not (& (ego onControl:) cBLUE))
						(Print 340 14)
					)
					((== currentStatus egoSITTING)
						(YouAre)
					)
					((!= currentStatus egoINCOMEDYCLUB) (GoodIdea))
					(else (self changeState: 1))
				)
			)
			((Said 'look>')
				(cond 
					((Said '/barstool')
						(Print 340 15)
					)
					((Said '/backstage')
						(Print 340 16)
					)
					((Said '/mask')
						(Print 340 17)
					)
					((Said '/awning')
						(Print 340 18)
					)
					((Said '/cigarette,smoke')
						(Print 340 19)
					)
					((Said '/burn,burn')
						(Print 340 20)
					)
					(
						(or
							(Said 'buy/beer,drink,beer')
							(Said 'buy//beer,drink,beer')
							(Said '/attendant,attendant,attendant')
							(Said '//attendant,attendant,attendant')
						)
						(Print 340 21)
					)
					((Said '/door')
						(Print 340 22)
					)
					((Said '/comedian')
						(if comedianOnStage
							(Print 340 23)
						else
							(Print 340 24)
						)
					)
					((Said '/babe')
						(Print 340 25)
					)
					((Said '/man,couple')
						(if
							(and
								(not (Btst fAlAndBill))
								(or
									(& (ego onControl:) cCYAN)
									(& (ego onControl:) cGREEN)
								)
							)
							(Print 340 26)
						else
							(Print 340 27)
						)
					)
					((Said '/bottle')
						(if (and (InRoom iWineBottle) (ego has: iPenthouseKey))
							(Print 340 28)
						else
							(Print 340 11)
						)
					)
					((Said '/al')
						(cond 
							((Btst fAlAndBill)
								(Print 340 29)
								(Print 340 30 #at -1 144)
							)
							((& (ego onControl:) cCYAN)
								(Print 340 31)
							)
							(else
								(Print 340 32)
							)
						)
					)
					((Said '/bill')
						(cond 
							((Btst fAlAndBill)
								(event claimed: FALSE)
							)
							((& (ego onControl:) cGREEN)
								(Print 340 33)
							)
							(else
								(Print 340 34)
							)
						)
					)
					((Said '/buffet')
						(if
							(and
								(& (ego onControl:) cLGREY)
								(InRoom iWineBottle)
								(ego has: iPenthouseKey)
							)
							(Print 340 28)
						else
							(Print 340 35)
						)
					)
					((Said '/bob')
						(if comedianOnStage
							(Print 340 36)
						else
							(Print 340 37)
						)
					)
					((Said '[/area,couple]')
						(Print 340 38)
					)
				)
			)
		)
	)
)

(instance ComicScript of Script
	(method (doit)
		(super doit:)
		(if (and (== -1 (music prevSignal?)) (== state 7))
			(self cue:)
		)
	)
	
	(method (changeState newState &tmp [str 200] [len 4] temp204 temp205 temp206 temp207 i)
		;EO: this method has been successfully decompiled,
		; but has a problem noted below. Fortunately, this sequence can still
		; be completed.
		(switch (= state newState)
			(0)
			(1
				(if (> machineSpeed 39)
					(aSign setCycle: Forward)
				)
				(music number: 10 loop: -1 play:)
				(aDrummer loop: 2 setCycle: Forward)
				(= seconds 4)
			)
			(2
				(Print 340 43)
				(if (!= currentStatus 1004) (Print 340 44))
				(music stop: number: 340 loop: 1 play:)
				(aDrummer cycleSpeed: 1 loop: 1)
				(= seconds 3)
			)
			(3
				(aComic setMotion: MoveTo 160 58 self)
			)
			(4
				(aComic setPri: -1 setMotion: MoveTo 160 78 self)
			)
			(5
				(if (> machineSpeed 39) (aSign setCel: 1 stopUpd:))
				(= comedianOnStage TRUE)
				(= seconds 3)
			)
			(6
				(aComic loop: 4 viewer: talkCycler)
				(= seconds 3)
			)
			(7)
			(8
				(aDrummer cycleSpeed: 0 loop: 0 setCel: 0)
				(Print 340 45)
				(= seconds 3)
			)
			(9
				(music number: 341 loop: -1 play:)
				(if (!= currentStatus egoSITTING)
					(Print 340 46)
				)
				(Print 340 47)
				(= ethnic1Str 0)
				(= ethnic2Str 0)
				(= ethnic3Str 0)
				(while (> 3 (StrLen @ethnic1Str))
					(GetInput @ethnic1Str 15 {Ethnic group #1:})
				)
				(while (> 3 (StrLen @ethnic2Str))
					(GetInput @ethnic2Str 15 {Ethnic group #2:})
				)
				(while (> 3 (StrLen @ethnic3Str))
					(GetInput @ethnic3Str 15 {Ethnic group #3:})
				)
				(Print 340 48)
				(= seconds 3)
			)
			(10
				(aComic loop: 4 viewer: talkCycler)
				(= seconds 3)
			)
			(11
				(aComic setCycle: Walk viewer: 0)
				(= temp204 0)
				(++ temp204)
				(= temp206 (Random 0 42))
				(if jokeNum (= [local0 (= temp206 (++ jokeNum))] 0))
				(if (== [local0 temp206] 0)
					(= [local0 temp206] 1)
					(= local110 (= local109 (Random 49 51)))
					(= local111 local109)
					(if (== local109 local110)
						(= local110 (Random 49 51))
					)
					(while (or (== local111 local109) (== local111 local110))
						(= local111 (Random 49 51))
					)
					(= temp207 0)
					(while (< temp207 5)
						(= i 0)
						(Format @str 340 49 341 (+ temp207 (* temp206 5)))
						(if (!= 32 (StrAt @str 1))
							(= temp205 0)
							(while (< temp205 (StrLen @str))
								(if (== 47 (StrAt @str temp205))
									(StrAt @str temp205 37)
									(switch (StrAt @str (++ temp205))
										(local109
											(= [len i] @ethnic1Str)
											(++ i)
										)
										(local110
											(= [len i] @ethnic2Str)
											(++ i)
										)
										(local111
											(= [len i] @ethnic3Str)
											(++ i)
										)
									)
									(StrAt @str temp205 115)
									(++ temp205)
								)
								(++ temp205)
							)
							(Printf
								@str
								[len 0]
								[len 1]
								[len 2]
								[len 3]
								[len 4]
							)
						)
						(++ temp207)
					)
					(if (> jokeNum 42)
						(if jokeNum
							(Print 340 50)
							(= jokeNum 0)
						else
							(Print 340 51)
						)
					)
					(aDrummer setCycle: EndLoop)
					(if (> (DoSound NumVoices) 5)
						(soundFX number: (Random 21 27) loop: 1 play:)
					)
				else
					(>= temp204 1000)
					(= state 12)
				)
				(= seconds 2)
			)
			(12
				(aComic
					setMotion: MoveTo (Random 125 195) (Random 66 80) self
				)
				(= state 9)
			)
			(13
				(music fade:)
				(Print 340 52)
				(aComic setMotion: MoveTo 158 75 self)
			)
			(14
				(Print 340 53)
				(if (not (Btst fListenedToComedian))
					(Bset fListenedToComedian)
					(theGame changeScore: 100)
				)
				(= seconds 2)
			)
			(15
				(aComic
					view: 344
					loop: 2
					cel: 0
					setCycle: EndLoop self
					setMotion: 0
				)
			)
			(16
				(aComic setCycle: Walk setMotion: MoveTo 125 75 self)
			)
			(17
				(aComic setMotion: MoveTo 194 75 self)
			)
			(18
				(aComic setMotion: MoveTo 161 75 self)
			)
			(19
				(aComic loop: 2 setCel: 255 setCycle: BegLoop self)
			)
			(20
				(aComic view: 343 loop: 2 setCycle: Walk)
				(= seconds 2)
			)
			(21
				(Print 340 54)
				(music number: 340 loop: -1 play:)
				(if (> machineSpeed 39)
					(aSign setCycle: Forward)
				)
				(= seconds 2)
			)
			(22
				(aComic setMotion: MoveTo 160 57 self)
			)
			(23
				(Print 340 55)
				(aComic setMotion: MoveTo 241 58 self)
				(music fade:)
				(= comedianOnStage FALSE)
			)
			(24
				(aComic hide:)
				(if (> machineSpeed 39)
					(aSign setCel: 0 stopUpd:)
				)
				(aDrummer stopUpd:)
				(music number: 341 loop: musicLoop play:)
			)
		)
	)
)

(instance drinkerScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 2 10))
			)
			(1
				(switch (Random 1 3)
					(1
						(= theDrinker aLadyLR_Top)
					)
					(2
						(= theDrinker aLadyUL_Top)
					)
					(3
						(= theDrinker aManUL_Top)
					)
				)
				(theDrinker setCycle: EndLoop self)
			)
			(2
				(if (== theDrinker aLadyUL_Top)
					(= state -1)
				)
				(= cycles (Random 5 22))
			)
			(3
				(theDrinker setCycle: BegLoop self)
				(= state -1)
			)
		)
	)
)

(instance atpManUR of PicView
	(properties
		y 186
		x 247
		view 340
		cel 9
	)
)

(instance atpManLR of PicView
	(properties
		y 128
		x 249
		view 340
		cel 8
	)
)

(instance atpChair of PicView
	(properties
		y 145
		x 165
		view 340
		priority 11
	)
)

(instance aDrummer of Prop
	(properties
		y 71
		x 79
		view 345
	)
	
	(method (init)
		(super init:)
		(self stopUpd:)
	)
)

(instance aBottle of View
	(properties
		y 119
		x 165
		view 340
		cel 5
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: stopUpd:)
	)
)

(instance aBillTop of Prop
	(properties
		y 155
		x 67
		view 346
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward setPri: 14 ignoreActors: hide:)
	)
)

(instance aBill of View
	(properties
		y 190
		x 70
		view 346
		priority 14
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: stopUpd:)
	)
)

(instance aAlTop of Prop
	(properties
		y 148
		x 102
		view 346
		loop 3
	)
	
	(method (init)
		(super init:)
		(self setPri: 14 ignoreActors: stopUpd:)
	)
)

(instance aAl of View
	(properties
		y 191
		x 99
		view 346
		cel 1
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: stopUpd:)
	)
)

(instance aLadyUL_Top of Prop
	(properties
		y 104
		x 45
		view 340
		loop 2
	)
	
	(method (init)
		(super init:)
		(self setPri: 9 stopUpd:)
	)
)

(instance atpLadyUL_Bottom of PicView
	(properties
		y 133
		x 52
		view 340
		cel 2
	)
)

(instance aLadyLR_Top of Prop
	(properties
		y 156
		x 291
		view 340
		loop 4
	)
	
	(method (init)
		(super init:)
		(self setPri: 14 setScript: drinkerScript stopUpd:)
	)
)

(instance atpLadyLR_Bottom of PicView
	(properties
		y 185
		x 283
		view 340
		cel 4
	)
)

(instance aManUL_Top of Prop
	(properties
		y 104
		x 281
		view 340
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 9 stopUpd:)
	)
)

(instance atpManUL_Bottom of PicView
	(properties
		y 132
		x 275
		view 340
		cel 1
	)
)

(instance talkCycler of Code
	(properties)
	
	(method (doit)
		(if (Random 0 3)
			(aComic cel: (Random 0 2))
		)
	)
)

(instance aComic of Actor
	(properties
		y 58
		x 241
		view 343
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setScript: ComicScript setCycle: Walk stopUpd:)
	)
)

(instance aSign of Prop
	(properties
		y 50
		x 223
		view 340
		loop 5
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 3 stopUpd:)
	)
)
