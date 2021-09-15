;;; Sierra Script 1.0 - (do not remove this comment)
(script# 230)
(include game.sh)
(use Main)
(use n021)
(use Intrface)
(use DCIcon)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm230 0
)
(synonyms
	(man guard man man man bouncer)
)

(local
	local0
	confirmBJ
	[str 200]
)
(procedure (DoormanSays theView theLoop theCel)
	(Print @str
		#at -1 10
		#title {the Doorman says}
		#mode teJustCenter
		#icon theView theLoop theCel
	)
	(= str 0)
)

(instance rm230 of Room
	(properties
		picture 230
		east 240
		south 220
	)
	
	(method (init)
		(if
			(and
				(InRoom iGrass)
				(ego has: iKnife)
				(== 21 ((Inventory at: iKnife) view?))
			)
			(Load VIEW 231)
			(Load VIEW 21)
		)
		(super init:)
		(self setScript: RoomScript)
		(addToPics add: atpSign doit:)
		(aDoor init:)
		(if playingAsPatti
			(aSign init:)
			(aDoorman init:)
			(Load SOUND 11)
			(if (ego has: iMoney)
				(Load VIEW 25)
			)
		)
		(NormalEgo)
		(ego init:)
		(cond 
			((== prevRoomNum 220)
				(ego loop: 3)
				(switch (Random 1 3)
					(1 (ego posn: 52 188))
					(2 (ego posn: 135 188))
					(3 (ego posn: 212 188))
				)
			)
			((== prevRoomNum 240)
				(ego posn: 317 135 loop: 1)
			)
			((== prevRoomNum 235)
				(ego posn: 100 128 loop: 0)
			)
			((== prevRoomNum 330)
				(= currentStatus egoNORMAL)
				(HandsOff)
				(aDoorman posn: 149 53 setCycle: Walk)
				(ego ignoreActors: illegalBits: 0 posn: 179 48 loop: 2)
				(aDoor setCel: 255)
				(DoormanScript changeState: 23)
			)
			(else
				(ego posn: 212 188)
			)
		)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cBLUE)
			(curRoom newRoom: 235)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(Ok)
				(ego setMotion: MoveTo 201 123 self)
			)
			(2
				(ego
					view: 231
					setLoop: (if (ego loop?) 1 else 0)
					setCel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(3
				(ego setLoop: (+ (ego loop?) 2) setCycle: Forward)
				(= cycles 20)
			)
			(4
				(Print 230 16 #at -1 10 #icon 21 0 0)
				(= cycles 20)
			)
			(5
				(ego get: 4 setLoop: (+ (ego loop?) 2) setCycle: EndLoop)
				(= cycles 10)
			)
			(6
				(NormalEgo (- (ego loop?) 4))
				(theGame changeScore: 20)
				(Print 230 17 #at -1 10)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'drag,break,get,break/blade')
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					(playingAsPatti
						(Print 230 0)
					)
					((not (InRoom iGrass))
						(AlreadyTook)
					)
					((not (& (ego onControl:) cRED))
						(NotClose)
					)
					(else
						(Print 230 0)
					)
				)
			)
			((Said 'carve/blade>')
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					(playingAsPatti
						(Print 230 0)
					)
					((not (InRoom iGrass))
						(AlreadyTook)
					)
					((not (& (ego onControl:) cRED))
						(NotClose)
					)
					((or (not (Said '//ginsu')) (not (ego has: iKnife)))
						(Print 230 1)
					)
					((!= ((Inventory at: iKnife) view?) 21)
						(Print 230 2)
					)
					(else
						(self changeState: 1)
					)
				)
				(event claimed: TRUE)
			)
			((Said 'use/ginsu>')
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (ego has: iKnife))
						(DontHave)
					)
					((not (InRoom iGrass))
						(AlreadyTook)
					)
					((not (& (ego onControl:) cRED))
						(NotClose)
					)
					((not (Said '//blade<carve'))
						(Print 230 3)
					)
					((!= ((Inventory at: iKnife) view?) 21)
						(Print 230 2)
					)
					(else
						(self changeState: 1)
					)
				)
				(event claimed: TRUE)
			)
			((Said 'look>')
				(cond 
					((Said '/door,awning,club')
						(Print 230 4 #mode teJustCenter)
						(if (not playingAsPatti)
							(Print 230 5)
						else
							(Print 230 6 #mode teJustCenter)
						)
					)
					((Said '/boulder,cliff')
						(Print 230 7)
					)
					((and (InRoom iGrass) (Said '/blade'))
						(Print 230 8)
						(Print 230 9 #at -1 144)
					)
					((Said '/flower')
						(Print 230 10)
					)
					((Said '/palm')
						(Print 230 11)
						(Print 230 12 #at -1 144)
					)
					((Said '[/area]')
						(if (cast contains: aDoorman)
							(Print 230 13)
						else
							(Print 230 14)
							(Print 230 15)
						)
					)
				)
			)
		)
	)
)

(instance aDoor of Prop
	(properties
		y 50
		x 179
		view 230
	)
	
	(method (init)
		(super init:)
		(self setPri: 1 stopUpd:)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look/door')
			(if playingAsPatti
				(Print 230 18)
			else
				(Print 230 19)
			)
		)
	)
)

(instance aSign of Prop
	(properties
		x 179
		view 230
		loop 1
		cycleSpeed 3
	)
	
	(method (init)
		(super init:)
		(self setPri: 5 setCycle: Forward)
	)
)

(instance atpSign of PicView
	(properties
		x 179
		view 230
		loop 2
		priority 4
		signal ignrAct
	)
)

(instance aDoorman of Actor
	(properties
		y 53
		x 179
		view 422
		loop 2
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setScript: DoormanScript setPri: 1 stopUpd:)
	)
)

(instance DoormanScript of Script
	(method (changeState newState &tmp temp0)
		(ChangeScriptState self newState 2 2)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(aDoorman setLoop: 2 setCycle: Forward)
				(= seconds 3)
			)
			(2
				(aDoorman setCel: 0 stopUpd:)
				(if (not str) (Format @str 230 44))
				(DoormanSays 422 3 0)
				(HandsOn)
			)
			(3
				(HandsOff)
				(theGame changeScore: 43)
				(Print 230 45 #icon 25 0 0)
				(= dollars 0)
				(PutInRoom iMoney)
				(aDoorman setLoop: 2 setCycle: Forward)
				(= cycles 0)
				(= seconds 3)
			)
			(4
				(aDoorman setCel: 0 stopUpd:)
				(Format @str 230 46)
				(DoormanSays 422 3 1)
				(= seconds 2)
			)
			(5
				(User canControl: TRUE)
				(aDoorman
					illegalBits: 0
					setLoop: 1
					setCycle: Forward
					setMotion: MoveTo 149 53 self
				)
				(aDoor setCycle: EndLoop)
			)
			(6
				(HandsOff)
				(aDoorman setLoop: 2 setCel: 0)
				(ego
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 179 57 self
				)
			)
			(7
				(ego setMotion: MoveTo 179 48 self setPri: 0)
				(if (Btst fGaveHeadToDoorman)
					(Print 230 47)
				)
			)
			(8
				(aDoor setCycle: BegLoop self)
			)
			(9
				(soundFX number: 11 loop: 1 play:)
				(= cycles 5)
			)
			(10
				(curRoom newRoom: 330)
			)
			(11
				(HandsOff)
				(Print 230 48)
				(aDoorman setLoop: 2 setCel: 0)
				(= cycles 0)
				(= seconds 3)
			)
			(12
				(Format @str 230 49)
				(DoormanSays 422 3 1)
				(= seconds 3)
			)
			(13
				(Format @str 230 50)
				(DoormanSays 422 3 6)
				(= seconds 3)
			)
			(14
				(User canControl: TRUE)
				(aDoorman
					illegalBits: 0
					setLoop: 1
					setCycle: Forward
					setMotion: MoveTo 144 53 self
				)
			)
			(15
				(HandsOff)
				(aDoorman setLoop: 2 setCel: 0 stopUpd:)
				(ego
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 144 57 self
				)
			)
			(16
				(ego
					setLoop: 3
					setCel: 0
					setPri:
					setMotion: MoveTo 144 77 self
				)
			)
			(17
				(= seconds 3)
			)
			(18
				(BJicon view: 422 loop: 3)
				(Print 230 51
					#at -1 10
					#title {the Doorman says}
					#mode teJustCenter
					#icon BJicon
				)
				(= seconds 3)
			)
			(19
				(ego setMotion: MoveTo 144 57 self)
			)
			(20
				(Print 230 52)
				(= seconds 3)
			)
			(21
				(Format @str 230 53)
				(DoormanSays 422 3 9)
				(aDoorman
					setLoop: -1
					setCel: -1
					setCycle: Walk
					setMotion: MoveTo 179 53 self
				)
			)
			(22
				(Bset fGaveHeadToDoorman)
				(Print 230 54)
				(ego setLoop: -1 setCel: -1 setPri: -1 setCycle: Walk)
				(= cycles 12)
				(= state 4)
			)
			(23
				(ego setMotion: MoveTo 186 60 self)
			)
			(24
				(aDoor setCycle: BegLoop self)
			)
			(25
				(soundFX number: 11 loop: 1 play:)
				(aDoor stopUpd:)
				(aDoorman setMotion: MoveTo 179 53 self)
			)
			(26
				(aDoorman loop: 2 setCycle: Forward)
				(= seconds 3)
			)
			(27
				(aDoorman setCel: 0 stopUpd:)
				(NormalEgo)
				(Print 230 55)
			)
		)
	)
	
	(method (handleEvent event &tmp i)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			(
				(or
					(Said 'give/job<blow')
					(Said 'give/blow<job')
					(Said 'give/head')
					(Said 'affirmative')
					(Said 'give/head<job')
					(Said 'eat,blow,eat,eat/man,ball,coconut,ball')
				)
				(cond 
					((not (& (ego onControl:) cMAGENTA))
						(NotClose)
					)
					((Btst fBeenInChipAndDales)
						(Print 230 20)
					)
					((not confirmBJ)
						(= confirmBJ TRUE)
						(Print 230 21)
					)
					((< filthLevel 3)
						(Print 230 22)
						(Print 230 23 #at -1 144)
					)
					(else
						(self changeState: 11)
					)
				)
			)
			(
				(or
					(Said 'board/club,area,backstage')
					(Said 'ask/dale,chip,show')
					(Said 'look/show')
				)
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (& (ego onControl:) cMAGENTA))
						(Print 230 24)
					)
					(else
						(Printf 230 25)
						(Format @str 230 26)
						(self changeState: 1)
					)
				)
			)
			(
				(or
					(Said 'bracelet,give,show/pass/man')
					(Said 'bracelet,give,show/man/pass')
				)
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (& (ego onControl:) cMAGENTA))
						(Print 230 27)
					)
					(else
						(Print 230 28)
						(Format @str 230 29)
						(self changeState: 1)
					)
				)
			)
			(
				(or
					(Said 'bracelet,give,show/buck,500,bill/man')
					(Said 'buy,tip/man')
					(Said 'bracelet,give,show/man/buck,500,bill')
				)
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((Btst fBeenInChipAndDales)
						(Print 230 20)
					)
					((not (ego has: iMoney))
						(Print 230 30)
					)
					((not (& (ego onControl:) cMAGENTA))
						(Print 230 27)
					)
					(else
						(self changeState: 3)
					)
				)
			)
			((Said 'address/man')
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (& (ego onControl:) cMAGENTA))
						(Print 230 31)
					)
					((Btst fBeenInChipAndDales)
						(Print 230 32)
					)
					(else
						(Print 230 33)
						(Format @str 230 34)
						(self changeState: 1)
					)
				)
			)
			((Said 'look/man')
				(Print 230 35)
				(Print 230 36 #at -1 144)
			)
			((Said 'give>')
				(= i (inventory saidMe:))
				(event claimed: FALSE)
				(cond 
					((not (& (ego onControl:) cMAGENTA))
						(NotClose)
					)
					((Said '[/noword]')
						(Print 230 37)
					)
					((not i)
						(Print 230 38)
					)
					((not (i ownedBy: ego))
						(DontHave)
					)
					((== i (inventory at: iPenthouseKey))
						(Print 230 39)
						(Format @str 230 40)
						(self changeState: 1)
					)
					(
						(or
							(== i (inventory at: iPanties))
							(== i (inventory at: iPantyhose))
							(== i (inventory at: iBra))
							(== i (inventory at: iDress))
						)
						(Format @str 230 41)
						(self changeState: 1)
					)
					((== i (inventory at: iMarker))
						(Print 230 42)
					)
					((not (== i (inventory at: iMoney)))
						(Print 230 43)
					)
					(else
						(self changeState: 3)
					)
				)
				(event claimed: TRUE)
			)
		)
	)
)

(instance BJicon of DCIcon
	(method (init)
		(super init:)
		(if cycler
			(cycler dispose:)
		)
		((= cycler (EndLoop new:)) init: self)
	)
)
