;;; Sierra Script 1.0 - (do not remove this comment)
(script# 253)
(include game.sh)
(use Main)
(use AutoDoor)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	rm253 0
)
(synonyms
	(barrel can barrel)
	(basin handle faucet fountain)
)

(local
	egoX
	egoY
)
(instance rm253 of Room
	(properties
		picture 253
		west 250
	)
	
	(method (init)
		(Load VIEW 254)
		(if playingAsPatti
			(= egoX 45)
			(= egoY 139)
		else
			(= egoX 43)
			(= egoY 137)
		)
		(if (and (ego has: iGrass) (== ((Inventory at: iGrass) view?) 23))
			(Load VIEW 707)
			(Load VIEW 701)
			(Load VIEW 23)
		)
		(if (ego has: iWineBottle)
			(Load VIEW 13)
		)
		(if (not (Btst fCredits253))
			(aCredit1 init:)
			(aCredit2 init:)
		)
		(if
			(or
				(Btst fFlag15)
				(and
					(not (Random 0 4))
					(> machineSpeed 16)
					(not playingAsPatti)
					(not (Btst fBillAndJodi))
					(Btst fCredits253)
				)
			)
			(aJodi init:)
			(aBill init:)
			(alsHead init:)
			(alsFeet init:)
		)
		(super init:)
		(self setScript: RoomScript)
		(if (InRoom iSoap)
			(aSoap init:)
		)
		(aDoor setPri: 10 ignoreActors: init:)
		(ego posn: 1 171)
		(NormalEgo)
		(ego init:)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(cond 
			(
				(and
					(& (ego onControl:) cBLUE)
					(or (< (ego heading?) 90) (> (ego heading?) 269))
				)
				(aDoor doorCtrl: cBLUE loop: 0)
			)
			(
				(and
					(& (ego onControl:) cRED)
					(> (ego heading?) 89)
					(< (ego heading?) 271)
				)
				(aDoor doorCtrl: cRED loop: 1)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(Ok)
				(ego illegalBits: 0 setMotion: MoveTo egoX egoY self)
			)
			(2
				(ego view: 254 loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(3
				(aSoap dispose:)
				(ego get: 5 loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(4
				(NormalEgo 0)
				(theGame changeScore: 12)
				(Print 253 31)
			)
			(5
				(HandsOff)
				(Ok)
				(ego illegalBits: 0 setMotion: MoveTo egoX egoY self)
			)
			(6
				(soundFX number: 253 loop: 11 play:)
				(ego
					view: (+ 254 playingAsPatti)
					loop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(7
				(ego loop: 1 setCycle: Forward)
				(= seconds 5)
			)
			(8
				(ego loop: 0 setCel: 255 setCycle: BegLoop self)
			)
			(9
				(soundFX stop:)
				(if (not (Btst fDrankFountainWater))
					(Bset fDrankFountainWater)
					(theGame changeScore: 2)
				)
				(Print 253 32 #at -1 10)
				(NormalEgo 0)
			)
			(10
				(HandsOff)
				(Ok)
				(ego illegalBits: 0 setMotion: MoveTo egoX egoY self)
			)
			(11
				(soundFX number: 253 loop: 1 play:)
				(ego
					view: (+ 254 playingAsPatti)
					loop: 2
					cel: 0
					setCycle: EndLoop self
				)
				(Print 253 33 #icon 13 0 0)
			)
			(12
				(ego loop: 3 setCycle: Forward)
				(= seconds 5)
			)
			(13
				(ego loop: 2 setCel: 255 setCycle: BegLoop self)
			)
			(14
				(soundFX stop:)
				((Inventory at: iWineBottle) view: 29)
				(Format ((Inventory at: iWineBottle) name?) 253 34)
				(if (not (Btst fFilledBottleWithWater))
					(Bset fFilledBottleWithWater)
					(theGame changeScore: 37)
				)
				(NormalEgo loopE)
			)
			(15
				(HandsOff)
				(Ok)
				(if (not (Btst fWoreGrassSkirt))
					(Bset fWoreGrassSkirt)
					(theGame changeScore: 10)
				)
				(ego
					illegalBits: 0
					cycleSpeed: 1
					view: 701
					loop: 2
					cel: 0
					setCycle: EndLoop self
				)
			)
			(16
				(= currentEgoView 707)
				(= currentStatus egoNATIVE)
				(NormalEgo 3)
				(Print 253 35 #icon 23 0 0)
			)
			(17
				(Ok)
				(HandsOff)
				(ego
					illegalBits: 0
					cycleSpeed: 1
					view: 701
					loop: 2
					setCel: 255
					setCycle: BegLoop self
				)
			)
			(18
				(= currentEgoView 700)
				(= currentStatus egoNORMAL)
				(NormalEgo loopN)
				(if (ego has: iMoney)
					(PutInRoom iGrass -1)
					(Print 253 36)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			(
				(or
					(Said 'drink')
					(Said 'drag/basin,water')
					(Said 'get/drink/water')
					(Said 'get/drink')
				)
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (& (ego onControl:) cLGREY))
						(Print 253 0)
					)
					(else
						(self changeState: 5)
					)
				)
			)
			((Said 'leak')
				(if (not (& (ego onControl:) cRED))
					(Print 253 1)
				else
					(Print 253 2)
				)
			)
			(
				(or
					(Said 'fill/bottle')
					(Said 'backdrop/water/bottle')
					(Said 'get/water')
				)
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea))
					((not (& (ego onControl:) cLGREY))
						(Print 253 0)
					)
					((not (ego has: iWineBottle))
						(Print 253 3)
					)
					((!= ((Inventory at: iWineBottle) view?) 28)
						(Print 253 4)
					)
					(else
						(self changeState: 10)
					)
				)
			)
			((Said 'get/soap')
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (InRoom iSoap))
						(AlreadyTook)
					)
					((not (& (ego onControl:) cLGREY))
						(NotClose)
					)
					(else
						(self changeState: 1)
					)
				)
			)
			(
				(or
					(Said 'wear,(alter<in),(backdrop<on)/blade,skirt')
					(Said '(alter<from,out),(get<off),drain/cloth,cloth')
				)
				(cond 
					((& (ego onControl:) cBROWN)
						(Print 253 5)
					)
					((not (& (ego onControl:) cRED))
						(Print 253 1)
					)
					((not (ego has: iGrass))
						(Print 253 6)
					)
					((!= ((Inventory at: iGrass) view?) 23)
						(Print 253 7)
					)
					((and (< filthLevel 3) (aDoor cel?))
						(Print 253 8)
					)
					((== currentStatus egoNATIVE)
						(self changeState: 17)
					)
					(else
						(self changeState: 15)
					)
				)
			)
			(
				(or
					(Said 'wear,(alter<in),(backdrop<on)/cloth,cloth')
					(Said '(alter<from,out),(get<off),drain/blade,skirt')
				)
				(cond 
					((== currentStatus egoNORMAL)
						(Print 253 6)
					)
					((& (ego onControl:) cBROWN)
						(Print 253 5)
					)
					((not (& (ego onControl:) cRED))
						(Print 253 1)
					)
					((!= currentStatus egoNATIVE)
						(Print 253 9)
					)
					((and (< filthLevel 3) (aDoor cel?))
						(Print 253 8)
					)
					(else
						(self changeState: 17)
					)
				)
			)
			((Said 'clean/eye,body,i,bracelet')
				(if (or (Btst fNotShower) (Btst fNotUseSoap))
					(Print 253 10)
				else
					(Print 253 11)
				)
			)
			((Said 'look>')
				(cond 
					((Said '/bathroom,building')
						(Print 253 12)
					)
					((Said '/basin')
						(Printf 253 13
							(if (InRoom iSoap)
								{ A bar of soap hangs over the sink, suspended by a rope looped over a nail.}
							else
								{}
							)
						)
					)
					((and (InRoom iSoap) (Said '/soap,hemp'))
						(Print 253 14)
					)
					((and (not (ego has: iWineBottle)) (Said '/water'))
						(Print 253 15)
					)
					((Said '/wall,clovis')
						(if (& (ego onControl:) cRED)
							(Print 253 16)
						else
							(Print 253 17)
						)
					)
					((Said '/clovis')
						(Print 253 18)
					)
					((Said '/nail,board,hemp')
						(Print 253 19)
					)
					((Said '/barstool,hole')
						(if (& (ego onControl:) cRED)
							(Print 253 20)
						else
							(NotClose)
						)
					)
					((Said '/barrel')
						(if (& (ego onControl:) cGREY)
							(Print 253 21)
							(Print 253 22)
						else
							(NotClose)
						)
					)
					((Said '/backstage,casino')
						(Print 253 23)
					)
					((Said '/door')
						(cond 
							((& (ego onControl:) cRED)
								(Print 253 24)
							)
							((& (ego onControl:) cBLUE)
								(Print 253 25)
							)
							((& (ego onControl:) cGREEN)
								(Print 253 26)
							)
							((& (ego onControl:) cCYAN)
								(Print 253 27)
							)
							(else
								(NotClose)
							)
						)
					)
					((Said '[/area]')
						(Print 253 28)
					)
				)
			)
			((Said '/door')
				(Print 253 29)
			)
			((Said '/barrel')
				(Print 253 30)
			)
		)
	)
)

(instance aDoor of AutoDoor
	(properties
		y 126
		x 93
		view 253
		roomCtrl 0
	)
)

(instance aSoap of View
	(properties
		y 111
		x 64
		view 253
		loop 2
	)
	
	(method (init)
		(super init:)
		(self setPri: 10 ignoreActors: stopUpd:)
	)
)

(instance BillScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aBill setCel: 0)
				(= seconds (Random 2 9))
			)
			(1
				(aBill setLoop: 5 setCycle: Forward)
				(= state -1)
				(= seconds (Random 2 5))
			)
			(2
				(aBill setLoop: 6 cel: 0 setCycle: EndLoop self)
				(= seconds 0)
			)
			(3
				(Print 253 42)
				(Print 253 43)
				(= seconds 3)
			)
			(4
				(aBill setCycle: BegLoop self)
				(= state -1)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'address/bill,man')
				(Print 253 37)
			)
			((Said 'address/al')
				(Print 253 38)
			)
			((Said 'look/al')
				(Print 253 39)
			)
			((Said 'look/bill')
				(Print 253 40)
			)
			((Said 'look/man')
				(Print 253 41)
			)
		)
	)
)

(instance aJodi of Actor
	(properties
		y 143
		x -30
		view 256
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setCycle: Walk setScript: JodiScript)
	)
)

(instance JodiScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 5 10))
			)
			(1
				(aJodi setMotion: MoveTo 184 143 self)
			)
			(2
				(aJodi setMotion: MoveTo 184 137 self)
			)
			(3
				(= seconds 3)
			)
			(4
				(aJodi loop: 4 cel: 0 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(5
				(Bset fBillAndJodi)
				(if
					(or
						(& (ego onControl:) cBROWN)
						(& (ego onControl:) cCYAN)
					)
					(Print 253 48)
				)
				(= seconds 3)
			)
			(6
				(aJodi
					cycleSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 184 143 self
				)
			)
			(7
				(aJodi setMotion: MoveTo (aBill x?) 143 self)
			)
			(8
				(BillScript changeState: 2)
				(aJodi setMotion: MoveTo -10 143 self)
			)
			(9
				(aJodi dispose:)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '/body')
				(Print 253 44 #at -1 144)
			)
			((Said '/babe,blond')
				(cond 
					((< (aJodi y?) 0)
						(Print 253 45)
					)
					((and (> state 3) (< state 6))
						(Print 253 46)
					)
					(else
						(Print 253 47)
					)
				)
			)
		)
	)
)

(instance aBill of Prop
	(properties
		y 140
		x 160
		view 253
		loop 5
	)
	
	(method (init)
		(super init:)
		(self setPri: 10 setScript: BillScript stopUpd:)
	)
)

(instance alsHead of View
	(properties
		y 96
		x 138
		view 253
		loop 3
		priority 6
		signal (| ignrAct staticView stopUpdOn)
	)
)

(instance alsFeet of View
	(properties
		y 132
		x 138
		view 253
		loop 4
		priority 9
		signal (| ignrAct staticView stopUpdOn)
	)
)

(instance aCredit1 of Prop
	(properties
		y 131
		x 288
		view 257
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 ignoreActors:)
	)
)

(instance aCredit2 of Prop
	(properties
		y 154
		x 288
		view 257
		loop 1
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 ignoreActors: setScript: CreditsScript)
	)
)

(instance CreditsScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 4)
			)
			(1
				(aCredit1 setCycle: EndLoop)
				(= cycles 16)
			)
			(2
				(aCredit2 setCycle: EndLoop)
				(= cycles 22)
			)
			(3
				(Bset fCredits253)
				(aCredit1 setCycle: BegLoop)
				(aCredit2 setCycle: BegLoop self)
			)
			(4
				(aCredit1 dispose:)
				(aCredit2 dispose:)
			)
		)
	)
)
